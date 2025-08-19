package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: _UCollections.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u001a\u001a\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001a\u0010\u0013\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001a\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\n0\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"sum", "Lkotlin/UInt;", "", "Lkotlin/UByte;", "sumOfUByte", "(Ljava/lang/Iterable;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Ljava/lang/Iterable;)J", "Lkotlin/UShort;", "sumOfUShort", "toUByteArray", "Lkotlin/UByteArray;", "", "(Ljava/util/Collection;)[B", "toUIntArray", "Lkotlin/UIntArray;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "(Ljava/util/Collection;)[S", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/collections/UCollectionsKt")
/* loaded from: classes.dex */
class UCollectionsKt___UCollectionsKt {
    public static final byte[] toUByteArray(Collection<UByte> toUByteArray) {
        Intrinsics.checkParameterIsNotNull(toUByteArray, "$this$toUByteArray");
        byte[] result = UByteArray.m69constructorimpl(toUByteArray.size());
        int index = 0;
        Iterator<UByte> it = toUByteArray.iterator();
        while (it.hasNext()) {
            byte element = it.next().getData();
            UByteArray.m80setVurrAj0(result, index, element);
            index++;
        }
        return result;
    }

    public static final int[] toUIntArray(Collection<UInt> toUIntArray) {
        Intrinsics.checkParameterIsNotNull(toUIntArray, "$this$toUIntArray");
        int[] result = UIntArray.m138constructorimpl(toUIntArray.size());
        int index = 0;
        Iterator<UInt> it = toUIntArray.iterator();
        while (it.hasNext()) {
            int element = it.next().getData();
            UIntArray.m149setVXSXFK8(result, index, element);
            index++;
        }
        return result;
    }

    public static final long[] toULongArray(Collection<ULong> toULongArray) {
        Intrinsics.checkParameterIsNotNull(toULongArray, "$this$toULongArray");
        long[] result = ULongArray.m207constructorimpl(toULongArray.size());
        int index = 0;
        Iterator<ULong> it = toULongArray.iterator();
        while (it.hasNext()) {
            long element = it.next().getData();
            ULongArray.m218setk8EXiF4(result, index, element);
            index++;
        }
        return result;
    }

    public static final short[] toUShortArray(Collection<UShort> toUShortArray) {
        Intrinsics.checkParameterIsNotNull(toUShortArray, "$this$toUShortArray");
        short[] result = UShortArray.m302constructorimpl(toUShortArray.size());
        int index = 0;
        Iterator<UShort> it = toUShortArray.iterator();
        while (it.hasNext()) {
            short element = it.next().getData();
            UShortArray.m313set01HTLdE(result, index, element);
            index++;
        }
        return result;
    }

    public static final int sumOfUInt(Iterable<UInt> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        int sum2 = 0;
        Iterator<UInt> it = sum.iterator();
        while (it.hasNext()) {
            int element = it.next().getData();
            sum2 = UInt.m93constructorimpl(sum2 + element);
        }
        return sum2;
    }

    public static final long sumOfULong(Iterable<ULong> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        long sum2 = 0;
        Iterator<ULong> it = sum.iterator();
        while (it.hasNext()) {
            long element = it.next().getData();
            sum2 = ULong.m162constructorimpl(sum2 + element);
        }
        return sum2;
    }

    public static final int sumOfUByte(Iterable<UByte> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        int sum2 = 0;
        Iterator<UByte> it = sum.iterator();
        while (it.hasNext()) {
            byte element = it.next().getData();
            sum2 = UInt.m93constructorimpl(UInt.m93constructorimpl(element & UByte.MAX_VALUE) + sum2);
        }
        return sum2;
    }

    public static final int sumOfUShort(Iterable<UShort> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        int sum2 = 0;
        Iterator<UShort> it = sum.iterator();
        while (it.hasNext()) {
            short element = it.next().getData();
            sum2 = UInt.m93constructorimpl(UInt.m93constructorimpl(65535 & element) + sum2);
        }
        return sum2;
    }
}
