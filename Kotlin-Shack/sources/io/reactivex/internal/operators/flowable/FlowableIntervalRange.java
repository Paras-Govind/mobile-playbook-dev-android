package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.schedulers.TrampolineScheduler;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableIntervalRange extends Flowable<Long> {
    final long end;
    final long initialDelay;
    final long period;
    final Scheduler scheduler;
    final long start;
    final TimeUnit unit;

    public FlowableIntervalRange(long start, long end, long initialDelay, long period, TimeUnit unit, Scheduler scheduler) {
        this.initialDelay = initialDelay;
        this.period = period;
        this.unit = unit;
        this.scheduler = scheduler;
        this.start = start;
        this.end = end;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super Long> s) {
        IntervalRangeSubscriber intervalRangeSubscriber = new IntervalRangeSubscriber(s, this.start, this.end);
        s.onSubscribe(intervalRangeSubscriber);
        Scheduler scheduler = this.scheduler;
        if (scheduler instanceof TrampolineScheduler) {
            Scheduler.Worker createWorker = scheduler.createWorker();
            intervalRangeSubscriber.setResource(createWorker);
            createWorker.schedulePeriodically(intervalRangeSubscriber, this.initialDelay, this.period, this.unit);
        } else {
            Disposable d = scheduler.schedulePeriodicallyDirect(intervalRangeSubscriber, this.initialDelay, this.period, this.unit);
            intervalRangeSubscriber.setResource(d);
        }
    }

    static final class IntervalRangeSubscriber extends AtomicLong implements Subscription, Runnable {
        private static final long serialVersionUID = -2809475196591179431L;
        final Subscriber<? super Long> actual;
        long count;
        final long end;
        final AtomicReference<Disposable> resource = new AtomicReference<>();

        IntervalRangeSubscriber(Subscriber<? super Long> actual, long start, long end) {
            this.actual = actual;
            this.count = start;
            this.end = end;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this, n);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            DisposableHelper.dispose(this.resource);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.resource.get() != DisposableHelper.DISPOSED) {
                long r = get();
                if (r != 0) {
                    long c = this.count;
                    this.actual.onNext(Long.valueOf(c));
                    if (c == this.end) {
                        if (this.resource.get() != DisposableHelper.DISPOSED) {
                            this.actual.onComplete();
                        }
                        DisposableHelper.dispose(this.resource);
                        return;
                    } else {
                        this.count = c + 1;
                        if (r != LongCompanionObject.MAX_VALUE) {
                            decrementAndGet();
                            return;
                        }
                        return;
                    }
                }
                this.actual.onError(new MissingBackpressureException("Can't deliver value " + this.count + " due to lack of requests"));
                DisposableHelper.dispose(this.resource);
            }
        }

        public void setResource(Disposable d) {
            DisposableHelper.setOnce(this.resource, d);
        }
    }
}
