package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class ConnectivityManagerCompat {
    private static final ConnectivityManagerCompatImpl IMPL;
    public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
    public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
    public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;

    /* loaded from: classes.dex */
    interface ConnectivityManagerCompatImpl {
        int getRestrictBackgroundStatus(ConnectivityManager connectivityManager);

        boolean isActiveNetworkMetered(ConnectivityManager connectivityManager);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface RestrictBackgroundStatus {
    }

    /* loaded from: classes.dex */
    static class ConnectivityManagerCompatBaseImpl implements ConnectivityManagerCompatImpl {
        ConnectivityManagerCompatBaseImpl() {
        }

        @Override // android.support.v4.net.ConnectivityManagerCompat.ConnectivityManagerCompatImpl
        public boolean isActiveNetworkMetered(ConnectivityManager cm) {
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null) {
                return true;
            }
            int type = info.getType();
            if (type != 1 && type != 7 && type != 9) {
                return true;
            }
            return false;
        }

        @Override // android.support.v4.net.ConnectivityManagerCompat.ConnectivityManagerCompatImpl
        public int getRestrictBackgroundStatus(ConnectivityManager cm) {
            return 3;
        }
    }

    /* loaded from: classes.dex */
    static class ConnectivityManagerCompatApi16Impl extends ConnectivityManagerCompatBaseImpl {
        ConnectivityManagerCompatApi16Impl() {
        }

        @Override // android.support.v4.net.ConnectivityManagerCompat.ConnectivityManagerCompatBaseImpl, android.support.v4.net.ConnectivityManagerCompat.ConnectivityManagerCompatImpl
        public boolean isActiveNetworkMetered(ConnectivityManager cm) {
            return cm.isActiveNetworkMetered();
        }
    }

    /* loaded from: classes.dex */
    static class ConnectivityManagerCompatApi24Impl extends ConnectivityManagerCompatApi16Impl {
        ConnectivityManagerCompatApi24Impl() {
        }

        @Override // android.support.v4.net.ConnectivityManagerCompat.ConnectivityManagerCompatBaseImpl, android.support.v4.net.ConnectivityManagerCompat.ConnectivityManagerCompatImpl
        public int getRestrictBackgroundStatus(ConnectivityManager cm) {
            return cm.getRestrictBackgroundStatus();
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 24) {
            IMPL = new ConnectivityManagerCompatApi24Impl();
        } else if (Build.VERSION.SDK_INT >= 16) {
            IMPL = new ConnectivityManagerCompatApi16Impl();
        } else {
            IMPL = new ConnectivityManagerCompatBaseImpl();
        }
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager cm) {
        return IMPL.isActiveNetworkMetered(cm);
    }

    public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager cm, Intent intent) {
        NetworkInfo info = (NetworkInfo) intent.getParcelableExtra("networkInfo");
        if (info != null) {
            return cm.getNetworkInfo(info.getType());
        }
        return null;
    }

    public static int getRestrictBackgroundStatus(ConnectivityManager cm) {
        return IMPL.getRestrictBackgroundStatus(cm);
    }

    private ConnectivityManagerCompat() {
    }
}
