package android.support.v7.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.app.NotificationCompat;
import android.support.v4.media.session.MediaSessionCompat;

@Deprecated
/* loaded from: classes.dex */
public class NotificationCompat extends android.support.v4.app.NotificationCompat {
    @Deprecated
    public NotificationCompat() {
    }

    @Deprecated
    public static MediaSessionCompat.Token getMediaSession(Notification notification) {
        Bundle extras = getExtras(notification);
        if (extras != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                Object tokenInner = extras.getParcelable(android.support.v4.app.NotificationCompat.EXTRA_MEDIA_SESSION);
                if (tokenInner != null) {
                    return MediaSessionCompat.Token.fromToken(tokenInner);
                }
                return null;
            }
            IBinder tokenInner2 = BundleCompat.getBinder(extras, android.support.v4.app.NotificationCompat.EXTRA_MEDIA_SESSION);
            if (tokenInner2 != null) {
                Parcel p = Parcel.obtain();
                p.writeStrongBinder(tokenInner2);
                p.setDataPosition(0);
                MediaSessionCompat.Token token = MediaSessionCompat.Token.CREATOR.createFromParcel(p);
                p.recycle();
                return token;
            }
            return null;
        }
        return null;
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static class Builder extends NotificationCompat.Builder {
        @Deprecated
        public Builder(Context context) {
            super(context);
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static class MediaStyle extends NotificationCompat.MediaStyle {
        @Deprecated
        public MediaStyle() {
        }

        @Deprecated
        public MediaStyle(NotificationCompat.Builder builder) {
            super(builder);
        }

        @Override // android.support.v4.media.app.NotificationCompat.MediaStyle
        @Deprecated
        public MediaStyle setShowActionsInCompactView(int... actions) {
            return (MediaStyle) super.setShowActionsInCompactView(actions);
        }

        @Override // android.support.v4.media.app.NotificationCompat.MediaStyle
        @Deprecated
        public MediaStyle setMediaSession(MediaSessionCompat.Token token) {
            return (MediaStyle) super.setMediaSession(token);
        }

        @Override // android.support.v4.media.app.NotificationCompat.MediaStyle
        @Deprecated
        public MediaStyle setShowCancelButton(boolean show) {
            return (MediaStyle) super.setShowCancelButton(show);
        }

        @Override // android.support.v4.media.app.NotificationCompat.MediaStyle
        @Deprecated
        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            return (MediaStyle) super.setCancelButtonIntent(pendingIntent);
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static class DecoratedCustomViewStyle extends NotificationCompat.DecoratedCustomViewStyle {
        @Deprecated
        public DecoratedCustomViewStyle() {
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static class DecoratedMediaCustomViewStyle extends NotificationCompat.DecoratedMediaCustomViewStyle {
        @Deprecated
        public DecoratedMediaCustomViewStyle() {
        }
    }
}
