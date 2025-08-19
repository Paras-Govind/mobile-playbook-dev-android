package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Dimensions.kt */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\u0017\u0010\t\u001a\u00020\u0001*\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\u0001H\u0086\b\u001a\u0014\u0010\t\u001a\u00020\u0001*\u00020\f2\b\b\u0001\u0010\u000b\u001a\u00020\u0001\u001a\u0017\u0010\t\u001a\u00020\u0001*\u00020\r2\b\b\u0001\u0010\u000b\u001a\u00020\u0001H\u0086\b\u001a\u001b\u0010\t\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\b\b\u0001\u0010\u000b\u001a\u00020\u0001H\u0086\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0001H\u0086\b\u001a\u0012\u0010\u000f\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0012\u0010\u000f\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0001\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0001H\u0086\b\u001a\u0019\u0010\u000f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086\b\u001a\u0019\u0010\u000f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0010\u001a\u00020\u0001H\u0086\b\u001a\u0015\u0010\u0012\u001a\u00020\u0011*\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0001H\u0086\b\u001a\u0012\u0010\u0012\u001a\u00020\u0011*\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0001\u001a\u0015\u0010\u0012\u001a\u00020\u0011*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0001H\u0086\b\u001a\u0019\u0010\u0012\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0013\u001a\u00020\u0001H\u0086\b\u001a\u0015\u0010\u0014\u001a\u00020\u0011*\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0001H\u0086\b\u001a\u0012\u0010\u0014\u001a\u00020\u0011*\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0001\u001a\u0015\u0010\u0014\u001a\u00020\u0011*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0001H\u0086\b\u001a\u0019\u0010\u0014\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0013\u001a\u00020\u0001H\u0086\b\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086\b\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0001H\u0086\b\u001a\u0012\u0010\u0015\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0012\u0010\u0015\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0001\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086\b\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0001H\u0086\b\u001a\u0019\u0010\u0015\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086\b\u001a\u0019\u0010\u0015\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0010\u001a\u00020\u0001H\u0086\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"HDPI", "", "LDPI", "MAXDPI", "MDPI", "TVDPI", "XHDPI", "XXHDPI", "XXXHDPI", "dimen", "Landroid/app/Fragment;", "resource", "Landroid/content/Context;", "Landroid/view/View;", "Lorg/jetbrains/anko/AnkoContext;", "dip", "value", "", "px2dip", "px", "px2sp", "sp", "commons_release"}, k = 2, mv = {1, 1, 5})
/* loaded from: classes.dex */
public final class DimensionsKt {
    public static final int HDPI = 240;
    public static final int LDPI = 120;
    public static final int MAXDPI = 65534;
    public static final int MDPI = 160;
    public static final int TVDPI = 213;
    public static final int XHDPI = 320;
    public static final int XXHDPI = 480;
    public static final int XXXHDPI = 640;

    public static final int dip(Context receiver, int value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (int) (value * receiver.getResources().getDisplayMetrics().density);
    }

    public static final int dip(Context receiver, float value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (int) (receiver.getResources().getDisplayMetrics().density * value);
    }

    public static final int sp(Context receiver, int value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (int) (value * receiver.getResources().getDisplayMetrics().scaledDensity);
    }

    public static final int sp(Context receiver, float value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (int) (receiver.getResources().getDisplayMetrics().scaledDensity * value);
    }

    public static final float px2dip(Context receiver, int px) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return px / receiver.getResources().getDisplayMetrics().density;
    }

    public static final float px2sp(Context receiver, int px) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return px / receiver.getResources().getDisplayMetrics().scaledDensity;
    }

    public static final int dimen(Context receiver, int resource) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.getResources().getDimensionPixelSize(resource);
    }

    public static final int dip(AnkoContext<?> receiver, int value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return dip(receiver.getCtx(), value);
    }

    public static final int dip(AnkoContext<?> receiver, float value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return dip(receiver.getCtx(), value);
    }

    public static final int sp(AnkoContext<?> receiver, int value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return sp(receiver.getCtx(), value);
    }

    public static final int sp(AnkoContext<?> receiver, float value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return sp(receiver.getCtx(), value);
    }

    public static final float px2dip(AnkoContext<?> receiver, int px) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return px2dip(receiver.getCtx(), px);
    }

    public static final float px2sp(AnkoContext<?> receiver, int px) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return px2sp(receiver.getCtx(), px);
    }

    public static final int dimen(AnkoContext<?> receiver, int resource) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return dimen(receiver.getCtx(), resource);
    }

    public static final int dip(View receiver, int value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return dip(receiver.getContext(), value);
    }

    public static final int dip(View receiver, float value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return dip(receiver.getContext(), value);
    }

    public static final int sp(View receiver, int value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return sp(receiver.getContext(), value);
    }

    public static final int sp(View receiver, float value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return sp(receiver.getContext(), value);
    }

    public static final float px2dip(View receiver, int px) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return px2dip(receiver.getContext(), px);
    }

    public static final float px2sp(View receiver, int px) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return px2sp(receiver.getContext(), px);
    }

    public static final int dimen(View receiver, int resource) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return dimen(receiver.getContext(), resource);
    }

    public static final int dip(Fragment receiver, int value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return dip((Context) receiver.getActivity(), value);
    }

    public static final int dip(Fragment receiver, float value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return dip(receiver.getActivity(), value);
    }

    public static final int sp(Fragment receiver, int value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return sp((Context) receiver.getActivity(), value);
    }

    public static final int sp(Fragment receiver, float value) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return sp(receiver.getActivity(), value);
    }

    public static final float px2dip(Fragment receiver, int px) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return px2dip(receiver.getActivity(), px);
    }

    public static final float px2sp(Fragment receiver, int px) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return px2sp(receiver.getActivity(), px);
    }

    public static final int dimen(Fragment receiver, int resource) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return dimen(receiver.getActivity(), resource);
    }
}
