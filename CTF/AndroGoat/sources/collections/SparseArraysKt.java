package collections;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import java.util.ConcurrentModificationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SparseArrays.kt */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a3\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\t2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b¨\u0006\n"}, d2 = {"forEach", "", "E", "Landroid/util/SparseArray;", "action", "Lkotlin/Function2;", "", "Landroid/util/SparseBooleanArray;", "", "Landroid/util/SparseIntArray;", "commons_release"}, k = 2, mv = {1, 1, 5})
/* loaded from: classes.dex */
public final class SparseArraysKt {
    public static final <E> void forEach(SparseArray<E> receiver, Function2<? super Integer, ? super E, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        int size = receiver.size();
        int i = size - 1;
        if (i >= 0) {
            int i2 = 0;
            while (size == receiver.size()) {
                action.invoke(Integer.valueOf(receiver.keyAt(i2)), receiver.valueAt(i2));
                if (i2 == i) {
                    return;
                } else {
                    i2++;
                }
            }
            throw new ConcurrentModificationException();
        }
    }

    public static final void forEach(SparseBooleanArray receiver, Function2<? super Integer, ? super Boolean, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        int size = receiver.size();
        int i = size - 1;
        if (i >= 0) {
            int i2 = 0;
            while (size == receiver.size()) {
                action.invoke(Integer.valueOf(receiver.keyAt(i2)), Boolean.valueOf(receiver.valueAt(i2)));
                if (i2 == i) {
                    return;
                } else {
                    i2++;
                }
            }
            throw new ConcurrentModificationException();
        }
    }

    public static final void forEach(SparseIntArray receiver, Function2<? super Integer, ? super Integer, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        int size = receiver.size();
        int i = size - 1;
        if (i >= 0) {
            int i2 = 0;
            while (size == receiver.size()) {
                action.invoke(Integer.valueOf(receiver.keyAt(i2)), Integer.valueOf(receiver.valueAt(i2)));
                if (i2 == i) {
                    return;
                } else {
                    i2++;
                }
            }
            throw new ConcurrentModificationException();
        }
    }
}
