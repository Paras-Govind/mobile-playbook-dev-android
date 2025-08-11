package kotlin.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;

/* compiled from: Locks.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\b¢\u0006\u0002\u0010\u0005\u001a&\u0010\u0006\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00072\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\b¢\u0006\u0002\u0010\b\u001a&\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\b¢\u0006\u0002\u0010\u0005¨\u0006\n"}, d2 = {"read", "T", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withLock", "Ljava/util/concurrent/locks/Lock;", "(Ljava/util/concurrent/locks/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "write", "kotlin-stdlib"}, k = 2, mv = {1, 1, 9})
@JvmName(name = "LocksKt")
/* loaded from: classes.dex */
public final class LocksKt {
    @InlineOnly
    private static final <T> T withLock(@NotNull Lock $receiver, Function0<? extends T> function0) {
        $receiver.lock();
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            $receiver.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    @InlineOnly
    private static final <T> T read(@NotNull ReentrantReadWriteLock $receiver, Function0<? extends T> function0) {
        ReentrantReadWriteLock.ReadLock rl = $receiver.readLock();
        rl.lock();
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            rl.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    @InlineOnly
    private static final <T> T write(@NotNull ReentrantReadWriteLock $receiver, Function0<? extends T> function0) {
        int i;
        ReentrantReadWriteLock.ReadLock rl = $receiver.readLock();
        int i2 = 0;
        int readCount = $receiver.getWriteHoldCount() == 0 ? $receiver.getReadHoldCount() : 0;
        int i3 = readCount - 1;
        if (i3 >= 0) {
            int i4 = 0;
            while (true) {
                rl.unlock();
                if (i4 == i3) {
                    break;
                }
                i4++;
            }
        }
        ReentrantReadWriteLock.WriteLock wl = $receiver.writeLock();
        wl.lock();
        try {
            if (i >= 0) {
                while (true) {
                    if (i2 == i) {
                        break;
                    }
                }
            }
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            i = readCount - 1;
            if (i >= 0) {
                while (true) {
                    int i5 = i2;
                    rl.lock();
                    if (i2 == i) {
                        break;
                    }
                    i2++;
                }
            }
            wl.unlock();
            InlineMarker.finallyEnd(1);
        }
    }
}
