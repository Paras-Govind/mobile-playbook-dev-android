package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.widget.Toast;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Toasts.kt */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0086\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0005H\u0086\b\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0005H\u0086\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0086\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0005H\u0086\b\u001a\u0019\u0010\b\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0019\u0010\b\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0005H\u0086\b¨\u0006\t"}, d2 = {"longToast", "Landroid/widget/Toast;", "Landroid/app/Fragment;", "message", "", "", "Landroid/content/Context;", "Lorg/jetbrains/anko/AnkoContext;", "toast", "commons_release"}, k = 2, mv = {1, 1, 5})
/* loaded from: classes.dex */
public final class ToastsKt {
    public static final Toast toast(AnkoContext<?> receiver, int message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Context $receiver$iv = receiver.getCtx();
        Toast makeText = Toast.makeText($receiver$iv, message, 0);
        Toast $receiver$iv2 = makeText;
        $receiver$iv2.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast toast(Fragment receiver, int message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Context $receiver$iv = receiver.getActivity();
        Toast makeText = Toast.makeText($receiver$iv, message, 0);
        Toast $receiver$iv2 = makeText;
        $receiver$iv2.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast toast(Context receiver, int message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Toast makeText = Toast.makeText(receiver, message, 0);
        Toast $receiver = makeText;
        $receiver.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast toast(AnkoContext<?> receiver, CharSequence message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Context $receiver$iv = receiver.getCtx();
        Toast makeText = Toast.makeText($receiver$iv, message, 0);
        Toast $receiver$iv2 = makeText;
        $receiver$iv2.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast toast(Fragment receiver, CharSequence message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Context $receiver$iv = receiver.getActivity();
        Toast makeText = Toast.makeText($receiver$iv, message, 0);
        Toast $receiver$iv2 = makeText;
        $receiver$iv2.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast toast(Context receiver, CharSequence message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Toast makeText = Toast.makeText(receiver, message, 0);
        Toast $receiver = makeText;
        $receiver.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast longToast(AnkoContext<?> receiver, int message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Context $receiver$iv = receiver.getCtx();
        Toast makeText = Toast.makeText($receiver$iv, message, 1);
        Toast $receiver$iv2 = makeText;
        $receiver$iv2.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast longToast(Fragment receiver, int message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Context $receiver$iv = receiver.getActivity();
        Toast makeText = Toast.makeText($receiver$iv, message, 1);
        Toast $receiver$iv2 = makeText;
        $receiver$iv2.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast longToast(Context receiver, int message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Toast makeText = Toast.makeText(receiver, message, 1);
        Toast $receiver = makeText;
        $receiver.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast longToast(AnkoContext<?> receiver, CharSequence message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Context $receiver$iv = receiver.getCtx();
        Toast makeText = Toast.makeText($receiver$iv, message, 1);
        Toast $receiver$iv2 = makeText;
        $receiver$iv2.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast longToast(Fragment receiver, CharSequence message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Context $receiver$iv = receiver.getActivity();
        Toast makeText = Toast.makeText($receiver$iv, message, 1);
        Toast $receiver$iv2 = makeText;
        $receiver$iv2.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast longToast(Context receiver, CharSequence message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Toast makeText = Toast.makeText(receiver, message, 1);
        Toast $receiver = makeText;
        $receiver.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }
}
