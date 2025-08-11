package kotlin.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MutableCollections.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001f\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a&\u0010\t\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000b2\u0006\u0010\f\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\r\u001a9\u0010\u000e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0001H\u0002¢\u0006\u0002\b\u0013\u001a9\u0010\u000e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000b2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0001H\u0002¢\u0006\u0002\b\u0013\u001a(\u0010\u0014\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u0006\u0010\u0015\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010\u0016\u001a.\u0010\u0014\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0087\n¢\u0006\u0002\u0010\u0017\u001a)\u0010\u0014\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087\n\u001a)\u0010\u0014\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087\n\u001a(\u0010\u0018\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u0006\u0010\u0015\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010\u0016\u001a.\u0010\u0018\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0087\n¢\u0006\u0002\u0010\u0017\u001a)\u0010\u0018\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087\n\u001a)\u0010\u0018\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087\n\u001a-\u0010\u0019\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u001a*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0015\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u001b\u001a&\u0010\u0019\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0087\b¢\u0006\u0002\u0010\u001e\u001a-\u0010\u001f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010\u001f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010\u001f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a.\u0010\u001f\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u001a*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020 H\u0087\b\u001a*\u0010\u001f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0011\u001a*\u0010\u001f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000b2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0011\u001a-\u0010!\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010!\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010!\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a.\u0010!\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u001a*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020 H\u0087\b\u001a*\u0010!\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0011\u001a*\u0010!\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000b2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0011\u001a\u0015\u0010\"\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0003H\u0002¢\u0006\u0002\b#\u001a\u0019\u0010$\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000bH\u0087\b\u001a!\u0010$\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000b2\u0006\u0010%\u001a\u00020&H\u0087\b\u001a\u001e\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00020(\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0007\u001a&\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00020(\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0006\u0010%\u001a\u00020&H\u0007\u001a\"\u0010)\u001a\u00020\n\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020**\b\u0012\u0004\u0012\u0002H\u00020\u000bH\u0007\u001a3\u0010)\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000b2\u0018\u0010+\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u001d0,H\u0087\b\u001a5\u0010)\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000b2\u001a\u0010-\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020.j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`/H\u0087\b\u001a4\u00100\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000b2\u001a\u0010-\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020.j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`/H\u0007¨\u00061"}, d2 = {"addAll", "", "T", "", "elements", "", "(Ljava/util/Collection;[Ljava/lang/Object;)Z", "", "Lkotlin/sequences/Sequence;", "fill", "", "", "value", "(Ljava/util/List;Ljava/lang/Object;)V", "filterInPlace", "", "predicate", "Lkotlin/Function1;", "predicateResultToRemove", "filterInPlace$CollectionsKt__MutableCollectionsKt", "minusAssign", "element", "(Ljava/util/Collection;Ljava/lang/Object;)V", "(Ljava/util/Collection;[Ljava/lang/Object;)V", "plusAssign", "remove", "Lkotlin/internal/OnlyInputTypes;", "(Ljava/util/Collection;Ljava/lang/Object;)Z", "index", "", "(Ljava/util/List;I)Ljava/lang/Object;", "removeAll", "", "retainAll", "retainNothing", "retainNothing$CollectionsKt__MutableCollectionsKt", "shuffle", "random", "Ljava/util/Random;", "shuffled", "", "sort", "", "comparison", "Lkotlin/Function2;", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "sortWith", "kotlin-stdlib"}, k = 5, mv = {1, 1, 9}, xi = 1, xs = "kotlin/collections/CollectionsKt")
/* loaded from: classes.dex */
public class CollectionsKt__MutableCollectionsKt extends CollectionsKt__IteratorsKt {
    @InlineOnly
    private static final <T> boolean remove(@NotNull Collection<? extends T> collection, T t) {
        if (collection == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
        }
        return TypeIntrinsics.asMutableCollection(collection).remove(t);
    }

    @InlineOnly
    private static final <T> boolean removeAll(@NotNull Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
        }
        return TypeIntrinsics.asMutableCollection(collection).removeAll(collection2);
    }

    @InlineOnly
    private static final <T> boolean retainAll(@NotNull Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
        }
        return TypeIntrinsics.asMutableCollection(collection).retainAll(collection2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use removeAt(index) instead.", replaceWith = @ReplaceWith(expression = "removeAt(index)", imports = {}))
    @InlineOnly
    private static final <T> T remove(@NotNull List<T> list, int index) {
        return list.remove(index);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use sortWith(comparator) instead.", replaceWith = @ReplaceWith(expression = "this.sortWith(comparator)", imports = {}))
    @InlineOnly
    private static final <T> void sort(@NotNull List<T> list, Comparator<? super T> comparator) {
        throw new NotImplementedError(null, 1, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use sortWith(Comparator(comparison)) instead.", replaceWith = @ReplaceWith(expression = "this.sortWith(Comparator(comparison))", imports = {}))
    @InlineOnly
    private static final <T> void sort(@NotNull List<T> list, Function2<? super T, ? super T, Integer> function2) {
        throw new NotImplementedError(null, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> receiver, T t) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.add(t);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> receiver, Iterable<? extends T> iterable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        CollectionsKt.addAll(receiver, iterable);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> receiver, T[] tArr) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        CollectionsKt.addAll(receiver, tArr);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> receiver, Sequence<? extends T> sequence) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        CollectionsKt.addAll(receiver, sequence);
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> receiver, T t) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.remove(t);
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> receiver, Iterable<? extends T> iterable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        CollectionsKt.removeAll(receiver, iterable);
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> receiver, T[] tArr) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        CollectionsKt.removeAll(receiver, tArr);
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> receiver, Sequence<? extends T> sequence) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        CollectionsKt.removeAll(receiver, sequence);
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> receiver, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        if (elements instanceof Collection) {
            return receiver.addAll((Collection) elements);
        }
        boolean result = false;
        for (Object item : elements) {
            if (receiver.add(item)) {
                result = true;
            }
        }
        return result;
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> receiver, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        boolean result = false;
        for (Object item : elements) {
            if (receiver.add(item)) {
                result = true;
            }
        }
        return result;
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> receiver, @NotNull T[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return receiver.addAll(ArraysKt.asList(elements));
    }

    public static final <T> boolean removeAll(@NotNull Iterable<? extends T> receiver, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((Iterable) receiver, (Function1) predicate, true);
    }

    public static final <T> boolean retainAll(@NotNull Iterable<? extends T> receiver, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((Iterable) receiver, (Function1) predicate, false);
    }

    private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(@NotNull Iterable<? extends T> iterable, Function1<? super T, Boolean> function1, boolean z) {
        boolean z2 = false;
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (function1.invoke((Object) it.next()).booleanValue() == z) {
                it.remove();
                z2 = true;
            }
        }
        return z2;
    }

    public static final <T> boolean removeAll(@NotNull List<T> receiver, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((List) receiver, (Function1) predicate, true);
    }

    public static final <T> boolean retainAll(@NotNull List<T> receiver, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((List) receiver, (Function1) predicate, false);
    }

    private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(@NotNull List<T> list, Function1<? super T, Boolean> function1, boolean predicateResultToRemove) {
        if (!(list instanceof RandomAccess)) {
            if (list == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableIterable<T>");
            }
            return filterInPlace$CollectionsKt__MutableCollectionsKt(TypeIntrinsics.asMutableIterable(list), function1, predicateResultToRemove);
        }
        int writeIndex = 0;
        int lastIndex = CollectionsKt.getLastIndex(list);
        if (lastIndex >= 0) {
            int writeIndex2 = 0;
            int writeIndex3 = 0;
            while (true) {
                T t = list.get(writeIndex3);
                if (function1.invoke(t).booleanValue() != predicateResultToRemove) {
                    if (writeIndex2 != writeIndex3) {
                        list.set(writeIndex2, t);
                    }
                    writeIndex2++;
                }
                if (writeIndex3 == lastIndex) {
                    break;
                }
                writeIndex3++;
            }
            writeIndex = writeIndex2;
        }
        if (writeIndex >= list.size()) {
            return false;
        }
        int removeIndex = CollectionsKt.getLastIndex(list);
        if (removeIndex < writeIndex) {
            return true;
        }
        while (true) {
            list.remove(removeIndex);
            if (removeIndex == writeIndex) {
                return true;
            }
            removeIndex--;
        }
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> receiver, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return TypeIntrinsics.asMutableCollection(receiver).removeAll(CollectionsKt.convertToSetForSetOperationWith(elements, receiver));
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> receiver, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        HashSet set = SequencesKt.toHashSet(elements);
        return (set.isEmpty() ^ true) && receiver.removeAll(set);
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> receiver, @NotNull T[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return ((elements.length == 0) ^ true) && receiver.removeAll(ArraysKt.toHashSet(elements));
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> receiver, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return TypeIntrinsics.asMutableCollection(receiver).retainAll(CollectionsKt.convertToSetForSetOperationWith(elements, receiver));
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> receiver, @NotNull T[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        if (true ^ (elements.length == 0)) {
            return receiver.retainAll(ArraysKt.toHashSet(elements));
        }
        return retainNothing$CollectionsKt__MutableCollectionsKt(receiver);
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> receiver, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        HashSet set = SequencesKt.toHashSet(elements);
        if (!set.isEmpty()) {
            return receiver.retainAll(set);
        }
        return retainNothing$CollectionsKt__MutableCollectionsKt(receiver);
    }

    private static final boolean retainNothing$CollectionsKt__MutableCollectionsKt(@NotNull Collection<?> collection) {
        boolean result = !collection.isEmpty();
        collection.clear();
        return result;
    }

    public static final <T extends Comparable<? super T>> void sort(@NotNull List<T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.size() > 1) {
            Collections.sort(receiver);
        }
    }

    public static final <T> void sortWith(@NotNull List<T> receiver, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        if (receiver.size() > 1) {
            Collections.sort(receiver, comparator);
        }
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final <T> void fill(@NotNull List<T> list, T t) {
        Collections.fill(list, t);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final <T> void shuffle(@NotNull List<T> list) {
        Collections.shuffle(list);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final <T> void shuffle(@NotNull List<T> list, Random random) {
        Collections.shuffle(list, random);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        List $receiver = CollectionsKt.toMutableList(receiver);
        Collections.shuffle($receiver);
        return $receiver;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> receiver, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(random, "random");
        List $receiver = CollectionsKt.toMutableList(receiver);
        Collections.shuffle($receiver, random);
        return $receiver;
    }
}
