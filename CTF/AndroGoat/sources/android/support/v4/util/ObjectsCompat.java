package android.support.v4.util;

import android.os.Build;
import java.util.Objects;

/* loaded from: classes.dex */
public class ObjectsCompat {
    private static final ImplBase IMPL;

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            IMPL = new ImplApi19();
        } else {
            IMPL = new ImplBase();
        }
    }

    private ObjectsCompat() {
    }

    public static boolean equals(Object a, Object b) {
        return IMPL.equals(a, b);
    }

    /* loaded from: classes.dex */
    private static class ImplBase {
        private ImplBase() {
        }

        public boolean equals(Object a, Object b) {
            return a == b || (a != null && a.equals(b));
        }
    }

    /* loaded from: classes.dex */
    private static class ImplApi19 extends ImplBase {
        private ImplApi19() {
            super();
        }

        @Override // android.support.v4.util.ObjectsCompat.ImplBase
        public boolean equals(Object a, Object b) {
            return Objects.equals(a, b);
        }
    }
}
