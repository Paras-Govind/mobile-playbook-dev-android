package org.jetbrains.anko;

import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.RelativeLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: RelativeLayoutLayoutParamsHelpers.kt */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001a\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0017\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\r\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\u000b\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\f\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\r\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0012\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0012\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\r\u0010\u0012\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\u0013\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\u0014\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\u0012\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b¨\u0006 "}, d2 = {"above", "", "Landroid/widget/RelativeLayout$LayoutParams;", "view", "Landroid/view/View;", "id", "", "alignEnd", "alignParentBottom", "alignParentEnd", "alignParentLeft", "alignParentRight", "alignParentStart", "alignParentTop", "alignStart", "baselineOf", "below", "bottomOf", "centerHorizontally", "centerInParent", "centerVertically", "endOf", "leftOf", "rightOf", "sameBottom", "sameEnd", "sameLeft", "sameRight", "sameStart", "sameTop", "startOf", "topOf", "commons_release"}, k = 2, mv = {1, 1, 5})
/* loaded from: classes.dex */
public final class RelativeLayoutLayoutParamsHelpersKt {
    public static final void topOf(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(2, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    public static final void above(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(2, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    public static final void bottomOf(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(3, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    public static final void below(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(3, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    public static final void leftOf(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(0, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    @RequiresApi(17)
    public static final void startOf(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(16, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    public static final void rightOf(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(1, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    @RequiresApi(17)
    public static final void endOf(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(17, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    public static final void sameLeft(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(5, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    @RequiresApi(17)
    public static final void sameStart(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(18, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    public static final void sameTop(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(6, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    public static final void sameRight(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(7, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    @RequiresApi(17)
    public static final void sameEnd(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(19, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    public static final void sameBottom(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(8, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    public static final void topOf(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(2, id);
    }

    public static final void above(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(2, id);
    }

    public static final void below(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(3, id);
    }

    public static final void bottomOf(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(3, id);
    }

    public static final void leftOf(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(0, id);
    }

    @RequiresApi(17)
    public static final void startOf(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(16, id);
    }

    public static final void rightOf(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(1, id);
    }

    @RequiresApi(17)
    public static final void endOf(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(17, id);
    }

    public static final void sameLeft(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(5, id);
    }

    @RequiresApi(17)
    public static final void sameStart(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(18, id);
    }

    public static final void sameTop(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(6, id);
    }

    public static final void sameRight(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(7, id);
    }

    @RequiresApi(17)
    public static final void sameEnd(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(19, id);
    }

    public static final void sameBottom(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(8, id);
    }

    @RequiresApi(17)
    public static final void alignStart(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(18, id);
    }

    @RequiresApi(17)
    public static final void alignEnd(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(19, id);
    }

    public static final void alignParentTop(@NotNull RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(10);
    }

    public static final void alignParentRight(@NotNull RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(11);
    }

    public static final void alignParentBottom(@NotNull RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(12);
    }

    public static final void alignParentLeft(@NotNull RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(9);
    }

    public static final void centerHorizontally(@NotNull RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(14);
    }

    public static final void centerVertically(@NotNull RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(15);
    }

    public static final void centerInParent(@NotNull RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(13);
    }

    @RequiresApi(17)
    public static final void alignParentStart(@NotNull RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(20);
    }

    @RequiresApi(17)
    public static final void alignParentEnd(@NotNull RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(21);
    }

    public static final void baselineOf(@NotNull RelativeLayout.LayoutParams receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != -1) {
            receiver.addRule(4, id);
            return;
        }
        throw new AnkoException("Id is not set for " + view);
    }

    public static final void baselineOf(@NotNull RelativeLayout.LayoutParams receiver, @IdRes int id) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(4, id);
    }
}
