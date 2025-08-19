package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Arrays.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a!\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0001¢\u0006\u0004\b\t\u0010\n\u001a?\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a+\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00030\u0003¢\u0006\u0002\u0010\u0016\u001a8\u0010\u0017\u001a\u0002H\u0018\"\u0010\b\u0000\u0010\u0019*\u0006\u0012\u0002\b\u00030\u0003*\u0002H\u0018\"\u0004\b\u0001\u0010\u0018*\u0002H\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0087\b¢\u0006\u0002\u0010\u001c\u001a)\u0010\u001d\u001a\u00020\u0001*\b\u0012\u0002\b\u0003\u0018\u00010\u0003H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0002\u0010\u001e\u001aG\u0010\u001f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00150 \"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0018*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00180 0\u0003¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"contentDeepEqualsImpl", "", "T", "", "other", "contentDeepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepToStringImpl", "", "contentDeepToString", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringInternal", "", "result", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "processed", "", "contentDeepToStringInternal$ArraysKt__ArraysKt", "([Ljava/lang/Object;Ljava/lang/StringBuilder;Ljava/util/List;)V", "flatten", "", "([[Ljava/lang/Object;)Ljava/util/List;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNullOrEmpty", "([Ljava/lang/Object;)Z", "unzip", "Lkotlin/Pair;", "([Lkotlin/Pair;)Lkotlin/Pair;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/collections/ArraysKt")
/* loaded from: classes.dex */
public class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
    public static final <T> List<T> flatten(T[][] flatten) {
        Intrinsics.checkParameterIsNotNull(flatten, "$this$flatten");
        T[][] $this$sumBy$iv = flatten;
        int sum$iv = 0;
        for (Object[] element$iv : $this$sumBy$iv) {
            Object[] it = element$iv;
            sum$iv += it.length;
        }
        ArrayList result = new ArrayList(sum$iv);
        for (T[] tArr : flatten) {
            CollectionsKt.addAll(result, tArr);
        }
        return result;
    }

    public static final <T, R> Pair<List<T>, List<R>> unzip(Pair<? extends T, ? extends R>[] unzip) {
        Intrinsics.checkParameterIsNotNull(unzip, "$this$unzip");
        ArrayList listT = new ArrayList(unzip.length);
        ArrayList listR = new ArrayList(unzip.length);
        for (Pair<? extends T, ? extends R> pair : unzip) {
            listT.add(pair.getFirst());
            listR.add(pair.getSecond());
        }
        return TuplesKt.to(listT, listR);
    }

    private static final boolean isNullOrEmpty(Object[] $this$isNullOrEmpty) {
        if ($this$isNullOrEmpty != null) {
            if (!($this$isNullOrEmpty.length == 0)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Incorrect types in method signature: <C:[Ljava/lang/Object;:TR;R:Ljava/lang/Object;>(TC;Lkotlin/jvm/functions/Function0<+TR;>;)TR; */
    private static final Object ifEmpty(Object[] $this$ifEmpty, Function0 defaultValue) {
        return $this$ifEmpty.length == 0 ? defaultValue.invoke() : $this$ifEmpty;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> boolean contentDeepEquals(T[] contentDeepEqualsImpl, T[] other) {
        Intrinsics.checkParameterIsNotNull(contentDeepEqualsImpl, "$this$contentDeepEqualsImpl");
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (contentDeepEqualsImpl == other) {
            return true;
        }
        if (contentDeepEqualsImpl.length != other.length) {
            return false;
        }
        int length = contentDeepEqualsImpl.length;
        for (int i = 0; i < length; i++) {
            Object[] objArr = contentDeepEqualsImpl[i];
            Object[] objArr2 = other[i];
            if (objArr != objArr2) {
                if (objArr == 0 || objArr2 == 0) {
                    return false;
                }
                if ((objArr instanceof Object[]) && (objArr2 instanceof Object[])) {
                    if (!ArraysKt.contentDeepEquals(objArr, objArr2)) {
                        return false;
                    }
                } else if ((objArr instanceof byte[]) && (objArr2 instanceof byte[])) {
                    if (!Arrays.equals((byte[]) objArr, (byte[]) objArr2)) {
                        return false;
                    }
                } else if ((objArr instanceof short[]) && (objArr2 instanceof short[])) {
                    if (!Arrays.equals((short[]) objArr, (short[]) objArr2)) {
                        return false;
                    }
                } else if ((objArr instanceof int[]) && (objArr2 instanceof int[])) {
                    if (!Arrays.equals((int[]) objArr, (int[]) objArr2)) {
                        return false;
                    }
                } else if ((objArr instanceof long[]) && (objArr2 instanceof long[])) {
                    if (!Arrays.equals((long[]) objArr, (long[]) objArr2)) {
                        return false;
                    }
                } else if ((objArr instanceof float[]) && (objArr2 instanceof float[])) {
                    if (!Arrays.equals((float[]) objArr, (float[]) objArr2)) {
                        return false;
                    }
                } else if ((objArr instanceof double[]) && (objArr2 instanceof double[])) {
                    if (!Arrays.equals((double[]) objArr, (double[]) objArr2)) {
                        return false;
                    }
                } else if ((objArr instanceof char[]) && (objArr2 instanceof char[])) {
                    if (!Arrays.equals((char[]) objArr, (char[]) objArr2)) {
                        return false;
                    }
                } else if ((objArr instanceof boolean[]) && (objArr2 instanceof boolean[])) {
                    if (!Arrays.equals((boolean[]) objArr, (boolean[]) objArr2)) {
                        return false;
                    }
                } else if ((objArr instanceof UByteArray) && (objArr2 instanceof UByteArray)) {
                    if (!kotlin.collections.unsigned.UArraysKt.m424contentEqualskdPth3s(((UByteArray) objArr).getStorage(), ((UByteArray) objArr2).getStorage())) {
                        return false;
                    }
                } else if ((objArr instanceof UShortArray) && (objArr2 instanceof UShortArray)) {
                    if (!kotlin.collections.unsigned.UArraysKt.m425contentEqualsmazbYpA(((UShortArray) objArr).getStorage(), ((UShortArray) objArr2).getStorage())) {
                        return false;
                    }
                } else if ((objArr instanceof UIntArray) && (objArr2 instanceof UIntArray)) {
                    if (!kotlin.collections.unsigned.UArraysKt.m423contentEqualsctEhBpI(((UIntArray) objArr).getStorage(), ((UIntArray) objArr2).getStorage())) {
                        return false;
                    }
                } else if ((objArr instanceof ULongArray) && (objArr2 instanceof ULongArray)) {
                    if (!kotlin.collections.unsigned.UArraysKt.m426contentEqualsus8wMrg(((ULongArray) objArr).getStorage(), ((ULongArray) objArr2).getStorage())) {
                        return false;
                    }
                } else if (!Intrinsics.areEqual(objArr, objArr2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static final <T> String contentDeepToString(T[] contentDeepToStringImpl) {
        Intrinsics.checkParameterIsNotNull(contentDeepToStringImpl, "$this$contentDeepToStringImpl");
        int length = (RangesKt.coerceAtMost(contentDeepToStringImpl.length, 429496729) * 5) + 2;
        StringBuilder $this$buildString = new StringBuilder(length);
        contentDeepToStringInternal$ArraysKt__ArraysKt(contentDeepToStringImpl, $this$buildString, new ArrayList());
        String sb = $this$buildString.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "StringBuilder(capacity).…builderAction).toString()");
        return sb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> void contentDeepToStringInternal$ArraysKt__ArraysKt(T[] tArr, StringBuilder result, List<Object[]> list) {
        if (list.contains(tArr)) {
            result.append("[...]");
            return;
        }
        list.add(tArr);
        result.append('[');
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                result.append(", ");
            }
            Object[] objArr = tArr[i];
            if (objArr == 0) {
                result.append("null");
            } else if (objArr instanceof Object[]) {
                contentDeepToStringInternal$ArraysKt__ArraysKt(objArr, result, list);
            } else if (objArr instanceof byte[]) {
                String arrays = Arrays.toString((byte[]) objArr);
                Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
                result.append(arrays);
            } else if (objArr instanceof short[]) {
                String arrays2 = Arrays.toString((short[]) objArr);
                Intrinsics.checkExpressionValueIsNotNull(arrays2, "java.util.Arrays.toString(this)");
                result.append(arrays2);
            } else if (objArr instanceof int[]) {
                String arrays3 = Arrays.toString((int[]) objArr);
                Intrinsics.checkExpressionValueIsNotNull(arrays3, "java.util.Arrays.toString(this)");
                result.append(arrays3);
            } else if (objArr instanceof long[]) {
                String arrays4 = Arrays.toString((long[]) objArr);
                Intrinsics.checkExpressionValueIsNotNull(arrays4, "java.util.Arrays.toString(this)");
                result.append(arrays4);
            } else if (objArr instanceof float[]) {
                String arrays5 = Arrays.toString((float[]) objArr);
                Intrinsics.checkExpressionValueIsNotNull(arrays5, "java.util.Arrays.toString(this)");
                result.append(arrays5);
            } else if (objArr instanceof double[]) {
                String arrays6 = Arrays.toString((double[]) objArr);
                Intrinsics.checkExpressionValueIsNotNull(arrays6, "java.util.Arrays.toString(this)");
                result.append(arrays6);
            } else if (objArr instanceof char[]) {
                String arrays7 = Arrays.toString((char[]) objArr);
                Intrinsics.checkExpressionValueIsNotNull(arrays7, "java.util.Arrays.toString(this)");
                result.append(arrays7);
            } else if (objArr instanceof boolean[]) {
                String arrays8 = Arrays.toString((boolean[]) objArr);
                Intrinsics.checkExpressionValueIsNotNull(arrays8, "java.util.Arrays.toString(this)");
                result.append(arrays8);
            } else if (objArr instanceof UByteArray) {
                result.append(kotlin.collections.unsigned.UArraysKt.m432contentToStringGBYM_sE(((UByteArray) objArr).getStorage()));
            } else if (objArr instanceof UShortArray) {
                result.append(kotlin.collections.unsigned.UArraysKt.m434contentToStringrL5Bavg(((UShortArray) objArr).getStorage()));
            } else if (objArr instanceof UIntArray) {
                result.append(kotlin.collections.unsigned.UArraysKt.m431contentToStringajY9A(((UIntArray) objArr).getStorage()));
            } else if (objArr instanceof ULongArray) {
                result.append(kotlin.collections.unsigned.UArraysKt.m433contentToStringQwZRm1k(((ULongArray) objArr).getStorage()));
            } else {
                result.append(objArr.toString());
            }
        }
        result.append(']');
        list.remove(CollectionsKt.getLastIndex(list));
    }
}
