package org.jetbrains.anko;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidDialogs.kt */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\u001aJ\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052!\b\n\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0086\b\u001aO\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2!\b\n\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0086\b¢\u0006\u0002\u0010\r\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\u00032\u001f\b\b\u0010\u0007\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b\u001aG\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052!\b\u0002\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000b\u001aL\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\f2!\b\u0002\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000b¢\u0006\u0002\u0010\u0011\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\u000e2\u001d\u0010\u0007\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b\u001aN\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052!\b\n\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0086\b\u001aS\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0004\u001a\u00020\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2!\b\n\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0086\b¢\u0006\u0002\u0010\u0013\u001a8\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u0006\u0012\u0002\b\u00030\u00122\u001f\b\b\u0010\u0007\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b\u001aB\u0010\u0014\u001a\u00020\u0015*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0086\b\u001aG\u0010\u0014\u001a\u00020\u0015*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0086\b¢\u0006\u0002\u0010\u0016\u001a?\u0010\u0014\u001a\u00020\u0015*\u00020\u000e2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\u0002\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000b\u001aD\u0010\u0014\u001a\u00020\u0015*\u00020\u000e2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2\u001b\b\u0002\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000b¢\u0006\u0002\u0010\u0017\u001aF\u0010\u0014\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00122\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0086\b\u001aK\u0010\u0014\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00122\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0086\b¢\u0006\u0002\u0010\u0018\u001aB\u0010\u0019\u001a\u00020\u0015*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0086\b\u001aG\u0010\u0019\u001a\u00020\u0015*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0086\b¢\u0006\u0002\u0010\u0016\u001aI\u0010\u0019\u001a\u00020\u0015*\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\u0002\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0002\u001a?\u0010\u0019\u001a\u00020\u0015*\u00020\u000e2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\u0002\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000b\u001aD\u0010\u0019\u001a\u00020\u0015*\u00020\u000e2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2\u001b\b\u0002\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000b¢\u0006\u0002\u0010\u0017\u001aF\u0010\u0019\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00122\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0086\b\u001aK\u0010\u0019\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00122\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0086\b¢\u0006\u0002\u0010\u0018¨\u0006\u001c"}, d2 = {"alert", "Lorg/jetbrains/anko/AlertBuilder;", "Landroid/app/AlertDialog;", "Landroid/app/Fragment;", "message", "", "title", "init", "Lkotlin/Function1;", "Landroid/content/DialogInterface;", "", "Lkotlin/ExtensionFunctionType;", "", "(Landroid/app/Fragment;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "Landroid/content/Context;", "messageResource", "titleResource", "(Landroid/content/Context;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "Lorg/jetbrains/anko/AnkoContext;", "(Lorg/jetbrains/anko/AnkoContext;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "indeterminateProgressDialog", "Landroid/app/ProgressDialog;", "(Landroid/app/Fragment;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Landroid/app/ProgressDialog;", "(Landroid/content/Context;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Landroid/app/ProgressDialog;", "(Lorg/jetbrains/anko/AnkoContext;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Landroid/app/ProgressDialog;", "progressDialog", "indeterminate", "", "commons_release"}, k = 2, mv = {1, 1, 5})
/* loaded from: classes.dex */
public final class AndroidDialogsKt {
    public static /* bridge */ /* synthetic */ AlertBuilder alert$default(AnkoContext receiver, CharSequence message, CharSequence title, Function1 init, int i, Object obj) {
        if ((i & 2) != 0) {
            title = (CharSequence) null;
        }
        if ((i & 4) != 0) {
            init = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return alert(receiver.getCtx(), message, title, (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) init);
    }

    public static final AlertBuilder<AlertDialog> alert(AnkoContext<?> receiver, CharSequence message, CharSequence title, Function1<? super AlertBuilder<? extends DialogInterface>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return alert(receiver.getCtx(), message, title, function1);
    }

    public static /* bridge */ /* synthetic */ AlertBuilder alert$default(Fragment receiver, CharSequence message, CharSequence title, Function1 init, int i, Object obj) {
        if ((i & 2) != 0) {
            title = (CharSequence) null;
        }
        if ((i & 4) != 0) {
            init = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return alert(receiver.getActivity(), message, title, (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) init);
    }

    public static final AlertBuilder<AlertDialog> alert(Fragment receiver, CharSequence message, CharSequence title, Function1<? super AlertBuilder<? extends DialogInterface>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return alert(receiver.getActivity(), message, title, function1);
    }

    public static /* bridge */ /* synthetic */ AlertBuilder alert$default(Context context, CharSequence charSequence, CharSequence charSequence2, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            charSequence2 = (CharSequence) null;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        return alert(context, charSequence, charSequence2, (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) function1);
    }

    public static final AlertBuilder<AlertDialog> alert(Context receiver, CharSequence message, CharSequence title, Function1<? super AlertBuilder<? extends DialogInterface>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        AndroidAlertBuilder androidAlertBuilder = new AndroidAlertBuilder(receiver);
        AndroidAlertBuilder $receiver = androidAlertBuilder;
        if (title != null) {
            $receiver.setTitle(title);
        }
        $receiver.setMessage(message);
        if (function1 != null) {
            function1.invoke($receiver);
        }
        return androidAlertBuilder;
    }

    public static /* bridge */ /* synthetic */ AlertBuilder alert$default(AnkoContext receiver, int message, Integer title, Function1 init, int i, Object obj) {
        if ((i & 2) != 0) {
            title = (Integer) null;
        }
        if ((i & 4) != 0) {
            init = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return alert(receiver.getCtx(), message, title, (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) init);
    }

    public static final AlertBuilder<DialogInterface> alert(AnkoContext<?> receiver, int message, Integer title, Function1<? super AlertBuilder<? extends DialogInterface>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return alert(receiver.getCtx(), message, title, function1);
    }

    public static /* bridge */ /* synthetic */ AlertBuilder alert$default(Fragment receiver, int message, Integer title, Function1 init, int i, Object obj) {
        if ((i & 2) != 0) {
            title = (Integer) null;
        }
        if ((i & 4) != 0) {
            init = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return alert(receiver.getActivity(), message, title, (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) init);
    }

    public static final AlertBuilder<DialogInterface> alert(Fragment receiver, int message, Integer title, Function1<? super AlertBuilder<? extends DialogInterface>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return alert(receiver.getActivity(), message, title, function1);
    }

    public static /* bridge */ /* synthetic */ AlertBuilder alert$default(Context context, int i, Integer num, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = (Integer) null;
        }
        if ((i2 & 4) != 0) {
            function1 = (Function1) null;
        }
        return alert(context, i, num, (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) function1);
    }

    public static final AlertBuilder<DialogInterface> alert(Context receiver, int messageResource, Integer titleResource, Function1<? super AlertBuilder<? extends DialogInterface>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        AndroidAlertBuilder androidAlertBuilder = new AndroidAlertBuilder(receiver);
        AndroidAlertBuilder $receiver = androidAlertBuilder;
        if (titleResource != null) {
            $receiver.setTitleResource(titleResource.intValue());
        }
        $receiver.setMessageResource(messageResource);
        if (function1 != null) {
            function1.invoke($receiver);
        }
        return androidAlertBuilder;
    }

    public static final AlertBuilder<DialogInterface> alert(AnkoContext<?> receiver, Function1<? super AlertBuilder<? extends DialogInterface>, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        return alert(receiver.getCtx(), init);
    }

    public static final AlertBuilder<DialogInterface> alert(Fragment receiver, Function1<? super AlertBuilder<? extends DialogInterface>, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        return alert(receiver.getActivity(), init);
    }

    public static final AlertBuilder<DialogInterface> alert(Context receiver, Function1<? super AlertBuilder<? extends DialogInterface>, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AndroidAlertBuilder androidAlertBuilder = new AndroidAlertBuilder(receiver);
        AndroidAlertBuilder $receiver = androidAlertBuilder;
        init.invoke($receiver);
        return androidAlertBuilder;
    }

    public static /* bridge */ /* synthetic */ ProgressDialog progressDialog$default(AnkoContext receiver, Integer message, Integer title, Function1 init, int i, Object obj) {
        if ((i & 1) != 0) {
            message = (Integer) null;
        }
        if ((i & 2) != 0) {
            title = (Integer) null;
        }
        if ((i & 4) != 0) {
            init = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return progressDialog(receiver.getCtx(), message, title, (Function1<? super ProgressDialog, Unit>) init);
    }

    public static final ProgressDialog progressDialog(AnkoContext<?> receiver, Integer message, Integer title, Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return progressDialog(receiver.getCtx(), message, title, function1);
    }

    public static /* bridge */ /* synthetic */ ProgressDialog progressDialog$default(Fragment receiver, Integer message, Integer title, Function1 init, int i, Object obj) {
        if ((i & 1) != 0) {
            message = (Integer) null;
        }
        if ((i & 2) != 0) {
            title = (Integer) null;
        }
        if ((i & 4) != 0) {
            init = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return progressDialog(receiver.getActivity(), message, title, (Function1<? super ProgressDialog, Unit>) init);
    }

    public static final ProgressDialog progressDialog(Fragment receiver, Integer message, Integer title, Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return progressDialog(receiver.getActivity(), message, title, function1);
    }

    public static /* bridge */ /* synthetic */ ProgressDialog progressDialog$default(Context context, Integer num, Integer num2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        return progressDialog(context, num, num2, (Function1<? super ProgressDialog, Unit>) function1);
    }

    public static final ProgressDialog progressDialog(Context receiver, Integer message, Integer title, Function1<? super ProgressDialog, Unit> function1) {
        String str;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        String str2 = null;
        if (message != null) {
            int it = message.intValue();
            str = receiver.getString(it);
        } else {
            str = null;
        }
        String str3 = str;
        if (title != null) {
            int it2 = title.intValue();
            str2 = receiver.getString(it2);
        }
        return progressDialog(receiver, false, str3, str2, function1);
    }

    public static /* bridge */ /* synthetic */ ProgressDialog indeterminateProgressDialog$default(AnkoContext receiver, Integer message, Integer title, Function1 init, int i, Object obj) {
        if ((i & 1) != 0) {
            message = (Integer) null;
        }
        if ((i & 2) != 0) {
            title = (Integer) null;
        }
        if ((i & 4) != 0) {
            init = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return indeterminateProgressDialog(receiver.getCtx(), message, title, (Function1<? super ProgressDialog, Unit>) init);
    }

    public static final ProgressDialog indeterminateProgressDialog(AnkoContext<?> receiver, Integer message, Integer title, Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return indeterminateProgressDialog(receiver.getCtx(), message, title, function1);
    }

    public static /* bridge */ /* synthetic */ ProgressDialog indeterminateProgressDialog$default(Fragment receiver, Integer message, Integer title, Function1 init, int i, Object obj) {
        if ((i & 1) != 0) {
            message = (Integer) null;
        }
        if ((i & 2) != 0) {
            title = (Integer) null;
        }
        if ((i & 4) != 0) {
            init = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return indeterminateProgressDialog(receiver.getActivity(), message, title, (Function1<? super ProgressDialog, Unit>) init);
    }

    public static final ProgressDialog indeterminateProgressDialog(Fragment receiver, Integer message, Integer title, Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return indeterminateProgressDialog(receiver.getActivity(), message, title, function1);
    }

    public static /* bridge */ /* synthetic */ ProgressDialog indeterminateProgressDialog$default(Context context, Integer num, Integer num2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        return indeterminateProgressDialog(context, num, num2, (Function1<? super ProgressDialog, Unit>) function1);
    }

    public static final ProgressDialog indeterminateProgressDialog(Context receiver, Integer message, Integer title, Function1<? super ProgressDialog, Unit> function1) {
        String str;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        String str2 = null;
        if (message != null) {
            int it = message.intValue();
            str = receiver.getString(it);
        } else {
            str = null;
        }
        String str3 = str;
        if (title != null) {
            int it2 = title.intValue();
            str2 = receiver.getString(it2);
        }
        return progressDialog(receiver, true, str3, str2, function1);
    }

    public static /* bridge */ /* synthetic */ ProgressDialog progressDialog$default(AnkoContext receiver, CharSequence message, CharSequence title, Function1 init, int i, Object obj) {
        if ((i & 1) != 0) {
            message = (CharSequence) null;
        }
        if ((i & 2) != 0) {
            title = (CharSequence) null;
        }
        if ((i & 4) != 0) {
            init = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return progressDialog(receiver.getCtx(), message, title, (Function1<? super ProgressDialog, Unit>) init);
    }

    public static final ProgressDialog progressDialog(AnkoContext<?> receiver, CharSequence message, CharSequence title, Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return progressDialog(receiver.getCtx(), message, title, function1);
    }

    public static /* bridge */ /* synthetic */ ProgressDialog progressDialog$default(Fragment receiver, CharSequence message, CharSequence title, Function1 init, int i, Object obj) {
        if ((i & 1) != 0) {
            message = (CharSequence) null;
        }
        if ((i & 2) != 0) {
            title = (CharSequence) null;
        }
        if ((i & 4) != 0) {
            init = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return progressDialog(receiver.getActivity(), message, title, (Function1<? super ProgressDialog, Unit>) init);
    }

    public static final ProgressDialog progressDialog(Fragment receiver, CharSequence message, CharSequence title, Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return progressDialog(receiver.getActivity(), message, title, function1);
    }

    public static /* bridge */ /* synthetic */ ProgressDialog progressDialog$default(Context context, CharSequence charSequence, CharSequence charSequence2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            charSequence = (CharSequence) null;
        }
        if ((i & 2) != 0) {
            charSequence2 = (CharSequence) null;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        return progressDialog(context, charSequence, charSequence2, (Function1<? super ProgressDialog, Unit>) function1);
    }

    public static final ProgressDialog progressDialog(Context receiver, CharSequence message, CharSequence title, Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return progressDialog(receiver, false, message, title, function1);
    }

    public static /* bridge */ /* synthetic */ ProgressDialog indeterminateProgressDialog$default(AnkoContext receiver, CharSequence message, CharSequence title, Function1 init, int i, Object obj) {
        if ((i & 1) != 0) {
            message = (CharSequence) null;
        }
        if ((i & 2) != 0) {
            title = (CharSequence) null;
        }
        if ((i & 4) != 0) {
            init = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return indeterminateProgressDialog(receiver.getCtx(), message, title, (Function1<? super ProgressDialog, Unit>) init);
    }

    public static final ProgressDialog indeterminateProgressDialog(AnkoContext<?> receiver, CharSequence message, CharSequence title, Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return indeterminateProgressDialog(receiver.getCtx(), message, title, function1);
    }

    public static /* bridge */ /* synthetic */ ProgressDialog indeterminateProgressDialog$default(Fragment receiver, CharSequence message, CharSequence title, Function1 init, int i, Object obj) {
        if ((i & 1) != 0) {
            message = (CharSequence) null;
        }
        if ((i & 2) != 0) {
            title = (CharSequence) null;
        }
        if ((i & 4) != 0) {
            init = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return indeterminateProgressDialog(receiver.getActivity(), message, title, (Function1<? super ProgressDialog, Unit>) init);
    }

    public static final ProgressDialog indeterminateProgressDialog(Fragment receiver, CharSequence message, CharSequence title, Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return indeterminateProgressDialog(receiver.getActivity(), message, title, function1);
    }

    public static /* bridge */ /* synthetic */ ProgressDialog indeterminateProgressDialog$default(Context context, CharSequence charSequence, CharSequence charSequence2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            charSequence = (CharSequence) null;
        }
        if ((i & 2) != 0) {
            charSequence2 = (CharSequence) null;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        return indeterminateProgressDialog(context, charSequence, charSequence2, (Function1<? super ProgressDialog, Unit>) function1);
    }

    public static final ProgressDialog indeterminateProgressDialog(Context receiver, CharSequence message, CharSequence title, Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return progressDialog(receiver, true, message, title, function1);
    }

    static /* bridge */ /* synthetic */ ProgressDialog progressDialog$default(Context context, boolean z, CharSequence charSequence, CharSequence charSequence2, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            charSequence = (CharSequence) null;
        }
        if ((i & 4) != 0) {
            charSequence2 = (CharSequence) null;
        }
        if ((i & 8) != 0) {
            function1 = (Function1) null;
        }
        return progressDialog(context, z, charSequence, charSequence2, function1);
    }

    private static final ProgressDialog progressDialog(Context $receiver, boolean indeterminate, CharSequence message, CharSequence title, Function1<? super ProgressDialog, Unit> function1) {
        ProgressDialog progressDialog = new ProgressDialog($receiver);
        ProgressDialog $receiver2 = progressDialog;
        $receiver2.setIndeterminate(indeterminate);
        if (!indeterminate) {
            $receiver2.setProgressStyle(1);
        }
        if (message != null) {
            $receiver2.setMessage(message);
        }
        if (title != null) {
            $receiver2.setTitle(title);
        }
        if (function1 != null) {
            function1.invoke($receiver2);
        }
        $receiver2.show();
        return progressDialog;
    }
}
