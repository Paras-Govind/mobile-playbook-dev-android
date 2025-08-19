package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public final class CompoundButtonCompat {
    private static final CompoundButtonCompatBaseImpl IMPL;

    static {
        if (Build.VERSION.SDK_INT >= 23) {
            IMPL = new CompoundButtonCompatApi23Impl();
        } else if (Build.VERSION.SDK_INT >= 21) {
            IMPL = new CompoundButtonCompatApi21Impl();
        } else {
            IMPL = new CompoundButtonCompatBaseImpl();
        }
    }

    /* loaded from: classes.dex */
    static class CompoundButtonCompatBaseImpl {
        private static final String TAG = "CompoundButtonCompat";
        private static Field sButtonDrawableField;
        private static boolean sButtonDrawableFieldFetched;

        CompoundButtonCompatBaseImpl() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setButtonTintList(CompoundButton compoundButton, ColorStateList tint) {
            if (compoundButton instanceof TintableCompoundButton) {
                ((TintableCompoundButton) compoundButton).setSupportButtonTintList(tint);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ColorStateList getButtonTintList(CompoundButton compoundButton) {
            if (compoundButton instanceof TintableCompoundButton) {
                return ((TintableCompoundButton) compoundButton).getSupportButtonTintList();
            }
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setButtonTintMode(CompoundButton compoundButton, PorterDuff.Mode tintMode) {
            if (compoundButton instanceof TintableCompoundButton) {
                ((TintableCompoundButton) compoundButton).setSupportButtonTintMode(tintMode);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public PorterDuff.Mode getButtonTintMode(CompoundButton compoundButton) {
            if (compoundButton instanceof TintableCompoundButton) {
                return ((TintableCompoundButton) compoundButton).getSupportButtonTintMode();
            }
            return null;
        }

        public Drawable getButtonDrawable(CompoundButton button) {
            if (!sButtonDrawableFieldFetched) {
                try {
                    Field declaredField = CompoundButton.class.getDeclaredField("mButtonDrawable");
                    sButtonDrawableField = declaredField;
                    declaredField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    Log.i(TAG, "Failed to retrieve mButtonDrawable field", e);
                }
                sButtonDrawableFieldFetched = true;
            }
            Field field = sButtonDrawableField;
            if (field != null) {
                try {
                    return (Drawable) field.get(button);
                } catch (IllegalAccessException e2) {
                    Log.i(TAG, "Failed to get button drawable via reflection", e2);
                    sButtonDrawableField = null;
                }
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    static class CompoundButtonCompatApi21Impl extends CompoundButtonCompatBaseImpl {
        CompoundButtonCompatApi21Impl() {
        }

        @Override // android.support.v4.widget.CompoundButtonCompat.CompoundButtonCompatBaseImpl
        public void setButtonTintList(CompoundButton button, ColorStateList tint) {
            button.setButtonTintList(tint);
        }

        @Override // android.support.v4.widget.CompoundButtonCompat.CompoundButtonCompatBaseImpl
        public ColorStateList getButtonTintList(CompoundButton button) {
            return button.getButtonTintList();
        }

        @Override // android.support.v4.widget.CompoundButtonCompat.CompoundButtonCompatBaseImpl
        public void setButtonTintMode(CompoundButton button, PorterDuff.Mode tintMode) {
            button.setButtonTintMode(tintMode);
        }

        @Override // android.support.v4.widget.CompoundButtonCompat.CompoundButtonCompatBaseImpl
        public PorterDuff.Mode getButtonTintMode(CompoundButton button) {
            return button.getButtonTintMode();
        }
    }

    /* loaded from: classes.dex */
    static class CompoundButtonCompatApi23Impl extends CompoundButtonCompatApi21Impl {
        CompoundButtonCompatApi23Impl() {
        }

        @Override // android.support.v4.widget.CompoundButtonCompat.CompoundButtonCompatBaseImpl
        public Drawable getButtonDrawable(CompoundButton button) {
            return button.getButtonDrawable();
        }
    }

    private CompoundButtonCompat() {
    }

    public static void setButtonTintList(CompoundButton button, ColorStateList tint) {
        IMPL.setButtonTintList(button, tint);
    }

    public static ColorStateList getButtonTintList(CompoundButton button) {
        return IMPL.getButtonTintList(button);
    }

    public static void setButtonTintMode(CompoundButton button, PorterDuff.Mode tintMode) {
        IMPL.setButtonTintMode(button, tintMode);
    }

    public static PorterDuff.Mode getButtonTintMode(CompoundButton button) {
        return IMPL.getButtonTintMode(button);
    }

    public static Drawable getButtonDrawable(CompoundButton button) {
        return IMPL.getButtonDrawable(button);
    }
}
