package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public final class FlowableFromArray<T> extends Flowable<T> {
    final T[] array;

    public FlowableFromArray(T[] array) {
        this.array = array;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> s) {
        if (s instanceof ConditionalSubscriber) {
            s.onSubscribe(new ArrayConditionalSubscription((ConditionalSubscriber) s, this.array));
        } else {
            s.onSubscribe(new ArraySubscription(s, this.array));
        }
    }

    static abstract class BaseArraySubscription<T> extends BasicQueueSubscription<T> {
        private static final long serialVersionUID = -2252972430506210021L;
        final T[] array;
        volatile boolean cancelled;
        int index;

        abstract void fastPath();

        abstract void slowPath(long j);

        BaseArraySubscription(T[] array) {
            this.array = array;
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public final int requestFusion(int mode) {
            return mode & 1;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public final T poll() {
            int i = this.index;
            T[] tArr = this.array;
            if (i == tArr.length) {
                return null;
            }
            this.index = i + 1;
            return (T) ObjectHelper.requireNonNull(tArr[i], "array element is null");
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public final boolean isEmpty() {
            return this.index == this.array.length;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public final void clear() {
            this.index = this.array.length;
        }

        @Override // org.reactivestreams.Subscription
        public final void request(long n) {
            if (SubscriptionHelper.validate(n) && BackpressureHelper.add(this, n) == 0) {
                if (n == LongCompanionObject.MAX_VALUE) {
                    fastPath();
                } else {
                    slowPath(n);
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public final void cancel() {
            this.cancelled = true;
        }
    }

    static final class ArraySubscription<T> extends BaseArraySubscription<T> {
        private static final long serialVersionUID = 2587302975077663557L;
        final Subscriber<? super T> actual;

        ArraySubscription(Subscriber<? super T> actual, T[] array) {
            super(array);
            this.actual = actual;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        void fastPath() {
            T[] arr = this.array;
            int f = arr.length;
            Subscriber<? super T> a = this.actual;
            for (int i = this.index; i != f; i++) {
                if (this.cancelled) {
                    return;
                }
                T t = arr[i];
                if (t == null) {
                    a.onError(new NullPointerException("array element is null"));
                    return;
                }
                a.onNext(t);
            }
            if (this.cancelled) {
                return;
            }
            a.onComplete();
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        void slowPath(long r) {
            long e = 0;
            T[] arr = this.array;
            int f = arr.length;
            int i = this.index;
            Subscriber<? super T> a = this.actual;
            while (true) {
                if (e != r && i != f) {
                    if (this.cancelled) {
                        return;
                    }
                    T t = arr[i];
                    if (t == null) {
                        a.onError(new NullPointerException("array element is null"));
                        return;
                    } else {
                        a.onNext(t);
                        i++;
                        e++;
                    }
                } else {
                    if (i == f) {
                        if (!this.cancelled) {
                            a.onComplete();
                            return;
                        }
                        return;
                    }
                    r = get();
                    if (e == r) {
                        this.index = i;
                        r = addAndGet(-e);
                        if (r == 0) {
                            return;
                        } else {
                            e = 0;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
    }

    static final class ArrayConditionalSubscription<T> extends BaseArraySubscription<T> {
        private static final long serialVersionUID = 2587302975077663557L;
        final ConditionalSubscriber<? super T> actual;

        ArrayConditionalSubscription(ConditionalSubscriber<? super T> actual, T[] array) {
            super(array);
            this.actual = actual;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        void fastPath() {
            T[] arr = this.array;
            int f = arr.length;
            ConditionalSubscriber<? super T> a = this.actual;
            for (int i = this.index; i != f; i++) {
                if (this.cancelled) {
                    return;
                }
                T t = arr[i];
                if (t == null) {
                    a.onError(new NullPointerException("array element is null"));
                    return;
                }
                a.tryOnNext(t);
            }
            if (this.cancelled) {
                return;
            }
            a.onComplete();
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        void slowPath(long r) {
            long e = 0;
            T[] arr = this.array;
            int f = arr.length;
            int i = this.index;
            ConditionalSubscriber<? super T> a = this.actual;
            while (true) {
                if (e != r && i != f) {
                    if (this.cancelled) {
                        return;
                    }
                    T t = arr[i];
                    if (t == null) {
                        a.onError(new NullPointerException("array element is null"));
                        return;
                    } else {
                        if (a.tryOnNext(t)) {
                            e++;
                        }
                        i++;
                    }
                } else {
                    if (i == f) {
                        if (!this.cancelled) {
                            a.onComplete();
                            return;
                        }
                        return;
                    }
                    r = get();
                    if (e == r) {
                        this.index = i;
                        r = addAndGet(-e);
                        if (r == 0) {
                            return;
                        } else {
                            e = 0;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
    }
}
