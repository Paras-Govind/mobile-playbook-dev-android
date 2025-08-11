package io.reactivex.observers;

import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public abstract class ResourceCompletableObserver implements CompletableObserver, Disposable {
    private final AtomicReference<Disposable> s = new AtomicReference<>();
    private final ListCompositeDisposable resources = new ListCompositeDisposable();

    public final void add(@NonNull Disposable resource) {
        ObjectHelper.requireNonNull(resource, "resource is null");
        this.resources.add(resource);
    }

    @Override // io.reactivex.CompletableObserver
    public final void onSubscribe(@NonNull Disposable s) {
        if (EndConsumerHelper.setOnce(this.s, s, getClass())) {
            onStart();
        }
    }

    protected void onStart() {
    }

    @Override // io.reactivex.disposables.Disposable
    public final void dispose() {
        if (DisposableHelper.dispose(this.s)) {
            this.resources.dispose();
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(this.s.get());
    }
}
