package android.support.v4.graphics;

import android.graphics.Paint;
import android.os.Build;

/* loaded from: classes.dex */
public final class PaintCompat {
    public static boolean hasGlyph(Paint paint, String string) {
        if (Build.VERSION.SDK_INT >= 23) {
            return paint.hasGlyph(string);
        }
        return PaintCompatApi14.hasGlyph(paint, string);
    }

    private PaintCompat() {
    }
}
