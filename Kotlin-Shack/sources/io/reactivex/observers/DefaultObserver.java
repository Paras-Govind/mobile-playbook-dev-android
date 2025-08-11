package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EndConsumerHelper;

/* loaded from: classes.dex */
public abstract class DefaultObserver<T> implements Observer<T> {
    private Disposable s;

    @Override // io.reactivex.Observer
    public final void onSubscribe(@NonNull Disposable s) {
        if (EndConsumerHelper.validate(this.s, s, getClass())) {
            this.s = s;
            onStart();
        }
    }

    protected final void cancel() {
        Disposable s = this.s;
        this.s = DisposableHelper.DISPOSED;
        s.dispose();
    }

    protected void onStart() {
    }
}
