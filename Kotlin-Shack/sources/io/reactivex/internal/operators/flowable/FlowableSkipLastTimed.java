package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableSkipLastTimed<T> extends AbstractFlowableWithUpstream<T, T> {
    final int bufferSize;
    final boolean delayError;
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    public FlowableSkipLastTimed(Flowable<T> source, long time, TimeUnit unit, Scheduler scheduler, int bufferSize, boolean delayError) {
        super(source);
        this.time = time;
        this.unit = unit;
        this.scheduler = scheduler;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe((FlowableSubscriber) new SkipLastTimedSubscriber(s, this.time, this.unit, this.scheduler, this.bufferSize, this.delayError));
    }

    static final class SkipLastTimedSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -5677354903406201275L;
        final Subscriber<? super T> actual;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;

        SkipLastTimedSubscriber(Subscriber<? super T> actual, long time, TimeUnit unit, Scheduler scheduler, int bufferSize, boolean delayError) {
            this.actual = actual;
            this.time = time;
            this.unit = unit;
            this.scheduler = scheduler;
            this.queue = new SpscLinkedArrayQueue<>(bufferSize);
            this.delayError = delayError;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.s, s)) {
                this.s = s;
                this.actual.onSubscribe(this);
                s.request(LongCompanionObject.MAX_VALUE);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            long now = this.scheduler.now(this.unit);
            this.queue.offer(Long.valueOf(now), t);
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void drain() {
            boolean delayError;
            if (getAndIncrement() != 0) {
                return;
            }
            int missed = 1;
            Subscriber<? super T> a = this.actual;
            SpscLinkedArrayQueue<Object> q = this.queue;
            boolean delayError2 = this.delayError;
            TimeUnit unit = this.unit;
            Scheduler scheduler = this.scheduler;
            long time = this.time;
            while (true) {
                long r = this.requested.get();
                long e = 0;
                while (true) {
                    if (e != r) {
                        boolean d = this.done;
                        Long ts = (Long) q.peek();
                        boolean empty = ts == null;
                        long now = scheduler.now(unit);
                        if (!empty && ts.longValue() > now - time) {
                            empty = true;
                        }
                        if (checkTerminated(d, empty, a, delayError2)) {
                            return;
                        }
                        if (!empty) {
                            q.poll();
                            a.onNext(q.poll());
                            e++;
                            delayError2 = delayError2;
                        } else {
                            delayError = delayError2;
                            break;
                        }
                    } else {
                        delayError = delayError2;
                        break;
                    }
                }
                if (e != 0) {
                    BackpressureHelper.produced(this.requested, e);
                }
                missed = addAndGet(-missed);
                if (missed != 0) {
                    delayError2 = delayError;
                } else {
                    return;
                }
            }
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<? super T> a, boolean delayError) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            }
            if (d) {
                if (delayError) {
                    if (empty) {
                        Throwable e = this.error;
                        if (e != null) {
                            a.onError(e);
                        } else {
                            a.onComplete();
                        }
                        return true;
                    }
                    return false;
                }
                Throwable e2 = this.error;
                if (e2 != null) {
                    this.queue.clear();
                    a.onError(e2);
                    return true;
                }
                if (empty) {
                    a.onComplete();
                    return true;
                }
                return false;
            }
            return false;
        }
    }
}
