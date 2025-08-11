package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.Experimental;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class UnicastSubject<T> extends Subject<T> {
    final AtomicReference<Observer<? super T>> actual;
    final boolean delayError;
    volatile boolean disposed;
    volatile boolean done;
    boolean enableOperatorFusion;
    Throwable error;
    final AtomicReference<Runnable> onTerminate;
    final AtomicBoolean once;
    final SpscLinkedArrayQueue<T> queue;
    final BasicIntQueueDisposable<T> wip;

    @CheckReturnValue
    public static <T> UnicastSubject<T> create() {
        return new UnicastSubject<>(bufferSize(), true);
    }

    @CheckReturnValue
    public static <T> UnicastSubject<T> create(int capacityHint) {
        return new UnicastSubject<>(capacityHint, true);
    }

    @CheckReturnValue
    public static <T> UnicastSubject<T> create(int capacityHint, Runnable onTerminate) {
        return new UnicastSubject<>(capacityHint, onTerminate, true);
    }

    @CheckReturnValue
    @Experimental
    public static <T> UnicastSubject<T> create(int capacityHint, Runnable onTerminate, boolean delayError) {
        return new UnicastSubject<>(capacityHint, onTerminate, delayError);
    }

    @CheckReturnValue
    @Experimental
    public static <T> UnicastSubject<T> create(boolean delayError) {
        return new UnicastSubject<>(bufferSize(), delayError);
    }

    UnicastSubject(int capacityHint, boolean delayError) {
        this.queue = new SpscLinkedArrayQueue<>(ObjectHelper.verifyPositive(capacityHint, "capacityHint"));
        this.onTerminate = new AtomicReference<>();
        this.delayError = delayError;
        this.actual = new AtomicReference<>();
        this.once = new AtomicBoolean();
        this.wip = new UnicastQueueDisposable();
    }

    UnicastSubject(int capacityHint, Runnable onTerminate) {
        this(capacityHint, onTerminate, true);
    }

    UnicastSubject(int capacityHint, Runnable onTerminate, boolean delayError) {
        this.queue = new SpscLinkedArrayQueue<>(ObjectHelper.verifyPositive(capacityHint, "capacityHint"));
        this.onTerminate = new AtomicReference<>(ObjectHelper.requireNonNull(onTerminate, "onTerminate"));
        this.delayError = delayError;
        this.actual = new AtomicReference<>();
        this.once = new AtomicBoolean();
        this.wip = new UnicastQueueDisposable();
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        if (!this.once.get() && this.once.compareAndSet(false, true)) {
            observer.onSubscribe(this.wip);
            this.actual.lazySet(observer);
            if (this.disposed) {
                this.actual.lazySet(null);
                return;
            } else {
                drain();
                return;
            }
        }
        EmptyDisposable.error(new IllegalStateException("Only a single observer allowed."), observer);
    }

    void doTerminate() {
        Runnable r = this.onTerminate.get();
        if (r != null && this.onTerminate.compareAndSet(r, null)) {
            r.run();
        }
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable s) {
        if (this.done || this.disposed) {
            s.dispose();
        }
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.done || this.disposed) {
            return;
        }
        this.queue.offer(t);
        drain();
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable t) {
        ObjectHelper.requireNonNull(t, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.done || this.disposed) {
            RxJavaPlugins.onError(t);
            return;
        }
        this.error = t;
        this.done = true;
        doTerminate();
        drain();
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        if (this.done || this.disposed) {
            return;
        }
        this.done = true;
        doTerminate();
        drain();
    }

    void drainNormal(Observer<? super T> observer) {
        SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.queue;
        boolean z = !this.delayError;
        int i = 1;
        int i2 = 1;
        while (!this.disposed) {
            boolean z2 = this.done;
            T poll = this.queue.poll();
            boolean z3 = poll == null;
            if (z2) {
                if (z && i2 != 0) {
                    if (failedFast(spscLinkedArrayQueue, observer)) {
                        return;
                    } else {
                        i2 = 0;
                    }
                }
                if (z3) {
                    errorOrComplete(observer);
                    return;
                }
            }
            if (!z3) {
                observer.onNext(poll);
            } else {
                i = this.wip.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }
        this.actual.lazySet(null);
        spscLinkedArrayQueue.clear();
    }

    void drainFused(Observer<? super T> a) {
        int missed = 1;
        SpscLinkedArrayQueue<T> q = this.queue;
        boolean failFast = !this.delayError;
        while (!this.disposed) {
            boolean d = this.done;
            if (failFast && d && failedFast(q, a)) {
                return;
            }
            a.onNext(null);
            if (d) {
                errorOrComplete(a);
                return;
            } else {
                missed = this.wip.addAndGet(-missed);
                if (missed == 0) {
                    return;
                }
            }
        }
        this.actual.lazySet(null);
        q.clear();
    }

    void errorOrComplete(Observer<? super T> a) {
        this.actual.lazySet(null);
        Throwable ex = this.error;
        if (ex != null) {
            a.onError(ex);
        } else {
            a.onComplete();
        }
    }

    boolean failedFast(SimpleQueue<T> q, Observer<? super T> a) {
        Throwable ex = this.error;
        if (ex != null) {
            this.actual.lazySet(null);
            q.clear();
            a.onError(ex);
            return true;
        }
        return false;
    }

    void drain() {
        if (this.wip.getAndIncrement() != 0) {
            return;
        }
        Observer<? super T> a = this.actual.get();
        int missed = 1;
        while (a == null) {
            missed = this.wip.addAndGet(-missed);
            if (missed != 0) {
                Observer<? super T> a2 = this.actual.get();
                a = a2;
            } else {
                return;
            }
        }
        if (this.enableOperatorFusion) {
            drainFused(a);
        } else {
            drainNormal(a);
        }
    }

    @Override // io.reactivex.subjects.Subject
    public boolean hasObservers() {
        return this.actual.get() != null;
    }

    @Override // io.reactivex.subjects.Subject
    public Throwable getThrowable() {
        if (this.done) {
            return this.error;
        }
        return null;
    }

    @Override // io.reactivex.subjects.Subject
    public boolean hasThrowable() {
        return this.done && this.error != null;
    }

    @Override // io.reactivex.subjects.Subject
    public boolean hasComplete() {
        return this.done && this.error == null;
    }

    final class UnicastQueueDisposable extends BasicIntQueueDisposable<T> {
        private static final long serialVersionUID = 7926949470189395511L;

        UnicastQueueDisposable() {
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int mode) {
            if ((mode & 2) != 0) {
                UnicastSubject.this.enableOperatorFusion = true;
                return 2;
            }
            return 0;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() throws Exception {
            return UnicastSubject.this.queue.poll();
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return UnicastSubject.this.queue.isEmpty();
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            UnicastSubject.this.queue.clear();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (!UnicastSubject.this.disposed) {
                UnicastSubject.this.disposed = true;
                UnicastSubject.this.doTerminate();
                UnicastSubject.this.actual.lazySet(null);
                if (UnicastSubject.this.wip.getAndIncrement() == 0) {
                    UnicastSubject.this.actual.lazySet(null);
                    UnicastSubject.this.queue.clear();
                }
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return UnicastSubject.this.disposed;
        }
    }
}
