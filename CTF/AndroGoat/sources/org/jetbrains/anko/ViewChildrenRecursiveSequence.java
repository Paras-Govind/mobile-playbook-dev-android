package org.jetbrains.anko;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.Sequence;

/* compiled from: viewChildrenSequences.kt */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u000f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006H\u0096\u0002R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/jetbrains/anko/ViewChildrenRecursiveSequence;", "Lkotlin/sequences/Sequence;", "Landroid/view/View;", "view", "(Landroid/view/View;)V", "iterator", "", "RecursiveViewIterator", "commons_release"}, k = 1, mv = {1, 1, 5})
/* loaded from: classes.dex */
final class ViewChildrenRecursiveSequence implements Sequence<View> {
    private final View view;

    public ViewChildrenRecursiveSequence(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.view = view;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<View> iterator() {
        return !(this.view instanceof ViewGroup) ? CollectionsKt.emptyList().iterator() : new RecursiveViewIterator(this.view);
    }

    /* compiled from: viewChildrenSequences.kt */
    @Metadata(bv = {1, 0, 1}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0010!\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\t\u0010\n\u001a\u00020\u000bH\u0096\u0002J\t\u0010\f\u001a\u00020\u0002H\u0096\u0002J\"\u0010\r\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000e*\u00020\u000f*\b\u0012\u0004\u0012\u0002H\u000e0\u0010H\u0083\b¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0006\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\b0\u0007j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/anko/ViewChildrenRecursiveSequence$RecursiveViewIterator;", "", "Landroid/view/View;", "view", "(Landroid/view/View;)V", "current", "sequences", "Ljava/util/ArrayList;", "Lkotlin/sequences/Sequence;", "Lkotlin/collections/ArrayList;", "hasNext", "", "next", "removeLast", "T", "", "", "(Ljava/util/List;)Ljava/lang/Object;", "commons_release"}, k = 1, mv = {1, 1, 5})
    /* loaded from: classes.dex */
    private static final class RecursiveViewIterator implements Iterator<View>, KMappedMarker {
        private Iterator<? extends View> current;
        private final ArrayList<Sequence<View>> sequences;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public RecursiveViewIterator(View view) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            ArrayList<Sequence<View>> arrayListOf = CollectionsKt.arrayListOf(ViewChildrenSequencesKt.childrenSequence(view));
            this.sequences = arrayListOf;
            ArrayList<Sequence<View>> $receiver$iv = arrayListOf;
            if (!$receiver$iv.isEmpty()) {
                this.current = $receiver$iv.remove($receiver$iv.size() - 1).iterator();
                return;
            }
            throw new NoSuchElementException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public View next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            View view = this.current.next();
            if ((view instanceof ViewGroup) && ((ViewGroup) view).getChildCount() > 0) {
                this.sequences.add(ViewChildrenSequencesKt.childrenSequence(view));
            }
            return view;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (!this.current.hasNext() && (!this.sequences.isEmpty())) {
                List $receiver$iv = this.sequences;
                if (!$receiver$iv.isEmpty()) {
                    this.current = $receiver$iv.remove($receiver$iv.size() - 1).iterator();
                } else {
                    throw new NoSuchElementException();
                }
            }
            return this.current.hasNext();
        }

        private final <T> T removeLast(List<T> list) {
            if (list.isEmpty()) {
                throw new NoSuchElementException();
            }
            return list.remove(list.size() - 1);
        }
    }
}
