package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subjects.UnicastSubject;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ObservableWindowBoundarySupplier<T, B> extends AbstractObservableWithUpstream<T, Observable<T>> {
    final int bufferSize;
    final Callable<? extends ObservableSource<B>> other;

    public ObservableWindowBoundarySupplier(ObservableSource<T> source, Callable<? extends ObservableSource<B>> other, int bufferSize) {
        super(source);
        this.other = other;
        this.bufferSize = bufferSize;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Observable<T>> t) {
        this.source.subscribe(new WindowBoundaryMainObserver(new SerializedObserver(t), this.other, this.bufferSize));
    }

    static final class WindowBoundaryMainObserver<T, B> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable {
        static final Object NEXT = new Object();
        final AtomicReference<Disposable> boundary;
        final int bufferSize;
        final Callable<? extends ObservableSource<B>> other;
        Disposable s;
        UnicastSubject<T> window;
        final AtomicLong windows;

        WindowBoundaryMainObserver(Observer<? super Observable<T>> actual, Callable<? extends ObservableSource<B>> other, int bufferSize) {
            super(actual, new MpscLinkedQueue());
            this.boundary = new AtomicReference<>();
            this.windows = new AtomicLong();
            this.other = other;
            this.bufferSize = bufferSize;
            this.windows.lazySet(1L);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.s, s)) {
                this.s = s;
                Observer<? super V> observer = this.actual;
                observer.onSubscribe(this);
                if (this.cancelled) {
                    return;
                }
                try {
                    ObservableSource<B> p = (ObservableSource) ObjectHelper.requireNonNull(this.other.call(), "The first window ObservableSource supplied is null");
                    UnicastSubject<T> w = UnicastSubject.create(this.bufferSize);
                    this.window = w;
                    observer.onNext(w);
                    WindowBoundaryInnerObserver<T, B> inner = new WindowBoundaryInnerObserver<>(this);
                    if (this.boundary.compareAndSet(null, inner)) {
                        this.windows.getAndIncrement();
                        p.subscribe(inner);
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    s.dispose();
                    observer.onError(e);
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (fastEnter()) {
                UnicastSubject<T> w = this.window;
                w.onNext(t);
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(NotificationLite.next(t));
                if (!enter()) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.error = t;
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            if (this.windows.decrementAndGet() == 0) {
                DisposableHelper.dispose(this.boundary);
            }
            this.actual.onError(t);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            if (this.windows.decrementAndGet() == 0) {
                DisposableHelper.dispose(this.boundary);
            }
            this.actual.onComplete();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.cancelled = true;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v0, types: [io.reactivex.subjects.UnicastSubject<T>] */
        void drainLoop() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) this.queue;
            Observer<? super V> observer = this.actual;
            int i = 1;
            UnicastSubject<T> unicastSubject = this.window;
            while (true) {
                boolean z = this.done;
                Object poll = mpscLinkedQueue.poll();
                boolean z2 = poll == null;
                if (z && z2) {
                    DisposableHelper.dispose(this.boundary);
                    Throwable th = this.error;
                    if (th != null) {
                        unicastSubject.onError(th);
                        return;
                    } else {
                        unicastSubject.onComplete();
                        return;
                    }
                }
                if (!z2) {
                    if (poll == NEXT) {
                        unicastSubject.onComplete();
                        if (this.windows.decrementAndGet() == 0) {
                            DisposableHelper.dispose(this.boundary);
                            return;
                        }
                        if (this.cancelled) {
                            continue;
                        } else {
                            try {
                                ObservableSource observableSource = (ObservableSource) ObjectHelper.requireNonNull(this.other.call(), "The ObservableSource supplied is null");
                                unicastSubject = (UnicastSubject<T>) UnicastSubject.create(this.bufferSize);
                                this.windows.getAndIncrement();
                                this.window = unicastSubject;
                                observer.onNext(unicastSubject);
                                WindowBoundaryInnerObserver windowBoundaryInnerObserver = new WindowBoundaryInnerObserver(this);
                                if (this.boundary.compareAndSet(this.boundary.get(), windowBoundaryInnerObserver)) {
                                    observableSource.subscribe(windowBoundaryInnerObserver);
                                }
                            } catch (Throwable th2) {
                                Exceptions.throwIfFatal(th2);
                                DisposableHelper.dispose(this.boundary);
                                observer.onError(th2);
                                return;
                            }
                        }
                    } else {
                        unicastSubject.onNext(NotificationLite.getValue(poll));
                    }
                } else {
                    i = leave(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }

        void next() {
            this.queue.offer(NEXT);
            if (enter()) {
                drainLoop();
            }
        }
    }

    static final class WindowBoundaryInnerObserver<T, B> extends DisposableObserver<B> {
        boolean done;
        final WindowBoundaryMainObserver<T, B> parent;

        WindowBoundaryInnerObserver(WindowBoundaryMainObserver<T, B> parent) {
            this.parent = parent;
        }

        @Override // io.reactivex.Observer
        public void onNext(B t) {
            if (this.done) {
                return;
            }
            this.done = true;
            dispose();
            this.parent.next();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
            } else {
                this.done = true;
                this.parent.onError(t);
            }
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.parent.onComplete();
        }
    }
}
