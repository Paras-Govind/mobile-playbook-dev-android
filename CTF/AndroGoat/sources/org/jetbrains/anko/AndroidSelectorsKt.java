package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidSelectors.kt */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001aC\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u001a\b\b\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\bH\u0086\b\u001a>\u0010\u0000\u001a\u00020\u0001*\u00020\u000b2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\b\u001aG\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\f2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u001a\b\b\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\bH\u0086\b¨\u0006\r"}, d2 = {"selector", "", "Landroid/app/Fragment;", "title", "", "items", "", "onClick", "Lkotlin/Function2;", "Landroid/content/DialogInterface;", "", "Landroid/content/Context;", "Lorg/jetbrains/anko/AnkoContext;", "commons_release"}, k = 2, mv = {1, 1, 5})
/* loaded from: classes.dex */
public final class AndroidSelectorsKt {
    public static /* bridge */ /* synthetic */ void selector$default(AnkoContext receiver, CharSequence title, List items, Function2 onClick, int i, Object obj) {
        if ((i & 1) != 0) {
            title = (CharSequence) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        selector(receiver.getCtx(), title, (List<? extends CharSequence>) items, (Function2<? super DialogInterface, ? super Integer, Unit>) onClick);
    }

    public static final void selector(AnkoContext<?> receiver, CharSequence title, List<? extends CharSequence> items, Function2<? super DialogInterface, ? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        selector(receiver.getCtx(), title, items, onClick);
    }

    public static /* bridge */ /* synthetic */ void selector$default(Fragment receiver, CharSequence title, List items, Function2 onClick, int i, Object obj) {
        if ((i & 1) != 0) {
            title = (CharSequence) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        selector(receiver.getActivity(), title, (List<? extends CharSequence>) items, (Function2<? super DialogInterface, ? super Integer, Unit>) onClick);
    }

    public static final void selector(Fragment receiver, CharSequence title, List<? extends CharSequence> items, Function2<? super DialogInterface, ? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        selector(receiver.getActivity(), title, items, onClick);
    }

    public static /* bridge */ /* synthetic */ void selector$default(Context context, CharSequence charSequence, List list, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            charSequence = (CharSequence) null;
        }
        selector(context, charSequence, (List<? extends CharSequence>) list, (Function2<? super DialogInterface, ? super Integer, Unit>) function2);
    }

    public static final void selector(Context receiver, CharSequence title, List<? extends CharSequence> items, Function2<? super DialogInterface, ? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        AndroidAlertBuilder $receiver = new AndroidAlertBuilder(receiver);
        if (title != null) {
            $receiver.setTitle(title);
        }
        $receiver.items(items, onClick);
        $receiver.show();
    }
}
