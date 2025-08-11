package kotlin.internal;

import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: PlatformImplementations.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0001\u001a\b\u0010\b\u001a\u00020\u0005H\u0002\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "apiVersionIsAtLeast", "", "major", "", "minor", "patch", "getJavaVersion", "kotlin-stdlib"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class PlatformImplementationsKt {

    @JvmField
    @NotNull
    public static final PlatformImplementations IMPLEMENTATIONS;

    static {
        PlatformImplementations platformImplementations;
        Object newInstance;
        Object newInstance2;
        int version = getJavaVersion();
        if (version >= 65544) {
            try {
                newInstance = Class.forName("kotlin.internal.jdk8.JDK8PlatformImplementations").newInstance();
            } catch (ClassNotFoundException e) {
                try {
                    Object newInstance3 = Class.forName("kotlin.internal.JRE8PlatformImplementations").newInstance();
                    if (newInstance3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
                    }
                    platformImplementations = (PlatformImplementations) newInstance3;
                } catch (ClassNotFoundException e2) {
                }
            }
            if (newInstance == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
            }
            platformImplementations = (PlatformImplementations) newInstance;
            IMPLEMENTATIONS = platformImplementations;
        }
        if (version >= 65543) {
            try {
                newInstance2 = Class.forName("kotlin.internal.jdk7.JDK7PlatformImplementations").newInstance();
            } catch (ClassNotFoundException e3) {
                try {
                    Object newInstance4 = Class.forName("kotlin.internal.JRE7PlatformImplementations").newInstance();
                    if (newInstance4 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
                    }
                    platformImplementations = (PlatformImplementations) newInstance4;
                } catch (ClassNotFoundException e4) {
                }
            }
            if (newInstance2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
            }
            platformImplementations = (PlatformImplementations) newInstance2;
            IMPLEMENTATIONS = platformImplementations;
        }
        platformImplementations = new PlatformImplementations();
        IMPLEMENTATIONS = platformImplementations;
    }

    private static final int getJavaVersion() {
        String version = System.getProperty("java.specification.version");
        if (version == null) {
            return 65542;
        }
        int firstDot = StringsKt.indexOf$default((CharSequence) version, '.', 0, false, 6, (Object) null);
        if (firstDot < 0) {
            try {
                return 65536 * Integer.parseInt(version);
            } catch (NumberFormatException e) {
                return 65542;
            }
        }
        int secondDot = StringsKt.indexOf$default((CharSequence) version, '.', firstDot + 1, false, 4, (Object) null);
        if (secondDot < 0) {
            secondDot = version.length();
        }
        if (version == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String firstPart = version.substring(0, firstDot);
        Intrinsics.checkExpressionValueIsNotNull(firstPart, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        int i = firstDot + 1;
        if (version == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String secondPart = version.substring(i, secondDot);
        Intrinsics.checkExpressionValueIsNotNull(secondPart, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        try {
            return Integer.parseInt(secondPart) + (Integer.parseInt(firstPart) * 65536);
        } catch (NumberFormatException e2) {
            return 65542;
        }
    }

    @SinceKotlin(version = "1.2")
    @PublishedApi
    public static final boolean apiVersionIsAtLeast(int major, int minor, int patch) {
        return KotlinVersion.CURRENT.isAtLeast(major, minor, patch);
    }
}
