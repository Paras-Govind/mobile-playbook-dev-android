package android.support.v7.widget;

import android.os.Build;
import android.view.View;

/* loaded from: classes.dex */
public class TooltipCompat {
    private static final ViewCompatImpl IMPL;

    /* loaded from: classes.dex */
    private interface ViewCompatImpl {
        void setTooltipText(View view, CharSequence charSequence);
    }

    /* loaded from: classes.dex */
    private static class BaseViewCompatImpl implements ViewCompatImpl {
        private BaseViewCompatImpl() {
        }

        @Override // android.support.v7.widget.TooltipCompat.ViewCompatImpl
        public void setTooltipText(View view, CharSequence tooltipText) {
            TooltipCompatHandler.setTooltipText(view, tooltipText);
        }
    }

    /* loaded from: classes.dex */
    private static class Api26ViewCompatImpl implements ViewCompatImpl {
        private Api26ViewCompatImpl() {
        }

        @Override // android.support.v7.widget.TooltipCompat.ViewCompatImpl
        public void setTooltipText(View view, CharSequence tooltipText) {
            view.setTooltipText(tooltipText);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 26) {
            IMPL = new Api26ViewCompatImpl();
        } else {
            IMPL = new BaseViewCompatImpl();
        }
    }

    public static void setTooltipText(View view, CharSequence tooltipText) {
        IMPL.setTooltipText(view, tooltipText);
    }

    private TooltipCompat() {
    }
}
