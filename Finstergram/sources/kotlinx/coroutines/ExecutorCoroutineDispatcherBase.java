package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.internal.ConcurrentKt;

/* compiled from: Executors.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b \u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u0007H\u0016J\u001c\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010H\u0016J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\r\u0010\u0016\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u0017J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u00102\u0006\u0010\b\u001a\u00020\tH\u0016J*\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d2\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u00102\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001e\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070 H\u0016J\b\u0010!\u001a\u00020\"H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lkotlinx/coroutines/ExecutorCoroutineDispatcherBase;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "()V", "removesFutureOnCancellation", "", "cancelJobOnRejection", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "Ljava/util/concurrent/RejectedExecutionException;", "close", "dispatch", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "equals", "other", "", "hashCode", "", "initFutureCancellation", "initFutureCancellation$kotlinx_coroutines_core", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "scheduleBlock", "Ljava/util/concurrent/ScheduledFuture;", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public abstract class ExecutorCoroutineDispatcherBase extends ExecutorCoroutineDispatcher implements Delay {
    private boolean removesFutureOnCancellation;

    @Override // kotlinx.coroutines.Delay
    public Object delay(long time, Continuation<? super Unit> continuation) {
        return Delay.DefaultImpls.delay(this, time, continuation);
    }

    public final void initFutureCancellation$kotlinx_coroutines_core() {
        this.removesFutureOnCancellation = ConcurrentKt.removeFutureOnCancel(getExecutor());
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* renamed from: dispatch */
    public void mo1579dispatch(CoroutineContext context, Runnable block) {
        try {
            Executor executor = getExecutor();
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            executor.execute(timeSource == null ? block : timeSource.wrapTask(block));
        } catch (RejectedExecutionException e) {
            AbstractTimeSource timeSource2 = AbstractTimeSourceKt.getTimeSource();
            if (timeSource2 != null) {
                timeSource2.unTrackTask();
            }
            cancelJobOnRejection(context, e);
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            Dispatchers.getIO().mo1579dispatch(context, block);
        }
    }

    @Override // kotlinx.coroutines.Delay
    /* renamed from: scheduleResumeAfterDelay */
    public void mo1580scheduleResumeAfterDelay(long timeMillis, CancellableContinuation<? super Unit> continuation) {
        ScheduledFuture future;
        if (this.removesFutureOnCancellation) {
            future = scheduleBlock(new ResumeUndispatchedRunnable(this, continuation), continuation.getContext(), timeMillis);
        } else {
            future = null;
        }
        if (future != null) {
            JobKt.cancelFutureOnCancellation(continuation, future);
        } else {
            DefaultExecutor.INSTANCE.mo1580scheduleResumeAfterDelay(timeMillis, continuation);
        }
    }

    @Override // kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long timeMillis, Runnable block, CoroutineContext context) {
        ScheduledFuture future;
        if (this.removesFutureOnCancellation) {
            future = scheduleBlock(block, context, timeMillis);
        } else {
            future = null;
        }
        return future != null ? new DisposableFutureHandle(future) : DefaultExecutor.INSTANCE.invokeOnTimeout(timeMillis, block, context);
    }

    private final ScheduledFuture<?> scheduleBlock(Runnable block, CoroutineContext context, long timeMillis) {
        try {
            Executor executor = getExecutor();
            ScheduledExecutorService scheduledExecutorService = executor instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor : null;
            r0 = scheduledExecutorService != null ? scheduledExecutorService.schedule(block, timeMillis, TimeUnit.MILLISECONDS) : null;
        } catch (RejectedExecutionException e) {
            cancelJobOnRejection(context, e);
        }
        return r0;
    }

    private final void cancelJobOnRejection(CoroutineContext context, RejectedExecutionException exception) {
        JobKt.cancel(context, ExceptionsKt.CancellationException("The task was rejected", exception));
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Executor executor = getExecutor();
        ExecutorService executorService = executor instanceof ExecutorService ? (ExecutorService) executor : null;
        if (executorService == null) {
            return;
        }
        executorService.shutdown();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return getExecutor().toString();
    }

    public boolean equals(Object other) {
        return (other instanceof ExecutorCoroutineDispatcherBase) && ((ExecutorCoroutineDispatcherBase) other).getExecutor() == getExecutor();
    }

    public int hashCode() {
        return System.identityHashCode(getExecutor());
    }
}
