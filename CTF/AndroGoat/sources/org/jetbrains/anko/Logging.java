package org.jetbrains.anko;

import android.util.Log;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import owasp.sat.agoat.BuildConfig;

/* compiled from: Logging.kt */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\u0086\b\u001a\u0012\u0010\u0000\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0014\u0010\b\u001a\u00020\u00072\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002\u001ag\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\u00122\u001e\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n0\u0014H\u0082\b\u001a\u001d\u0010\u0015\u001a\u00020\n*\u00020\u00012\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016H\u0086\b\u001a \u0010\u0015\u001a\u00020\n*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u001d\u0010\u0017\u001a\u00020\n*\u00020\u00012\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016H\u0086\b\u001a \u0010\u0017\u001a\u00020\n*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\r\u0010\u0018\u001a\u00020\u0007*\u00020\u000eH\u0086\b\u001a\u001d\u0010\u0019\u001a\u00020\n*\u00020\u00012\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016H\u0086\b\u001a \u0010\u0019\u001a\u00020\n*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u001d\u0010\u001a\u001a\u00020\n*\u00020\u00012\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016H\u0086\b\u001a \u0010\u001a\u001a\u00020\n*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u001d\u0010\u001b\u001a\u00020\n*\u00020\u00012\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016H\u0086\b\u001a \u0010\u001b\u001a\u00020\n*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a \u0010\u001c\u001a\u00020\n*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¨\u0006\u001d"}, d2 = {"AnkoLogger", "Lorg/jetbrains/anko/AnkoLogger;", "T", "", "clazz", "Ljava/lang/Class;", "tag", "", "getTag", "log", "", "logger", "message", "thr", "", "level", "", "f", "Lkotlin/Function2;", "fThrowable", "Lkotlin/Function3;", BuildConfig.BUILD_TYPE, "Lkotlin/Function0;", "error", "getStackTraceString", "info", "verbose", "warn", "wtf", "commons_release"}, k = 2, mv = {1, 1, 5})
/* loaded from: classes.dex */
public final class Logging {
    public static final AnkoLogger AnkoLogger(final Class<?> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        return new AnkoLogger(clazz) { // from class: org.jetbrains.anko.Logging$AnkoLogger$1
            final /* synthetic */ Class $clazz;
            private final String loggerTag;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                String tag;
                this.$clazz = clazz;
                tag = Logging.getTag(clazz);
                this.loggerTag = tag;
            }

            @Override // org.jetbrains.anko.AnkoLogger
            public String getLoggerTag() {
                return this.loggerTag;
            }
        };
    }

    public static final AnkoLogger AnkoLogger(final String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return new AnkoLogger(tag) { // from class: org.jetbrains.anko.Logging$AnkoLogger$2
            final /* synthetic */ String $tag;
            private final String loggerTag;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.$tag = tag;
                boolean z = tag.length() <= 23;
                if (!_Assertions.ENABLED || z) {
                    this.loggerTag = tag;
                    return;
                }
                throw new AssertionError("The maximum tag length is 23, got " + this.$tag);
            }

            @Override // org.jetbrains.anko.AnkoLogger
            public String getLoggerTag() {
                return this.loggerTag;
            }
        };
    }

    private static final <T> AnkoLogger AnkoLogger() {
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoLogger((Class<?>) Object.class);
    }

    public static /* bridge */ /* synthetic */ void verbose$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        if ((i & 2) != 0) {
            th = (Throwable) null;
        }
        verbose(ankoLogger, obj, th);
    }

    public static final void verbose(AnkoLogger receiver, Object message, Throwable thr) {
        String obj;
        String obj2;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        String tag$iv = receiver.getLoggerTag();
        if (!Log.isLoggable(tag$iv, 2)) {
            return;
        }
        String str = "null";
        if (thr != null) {
            if (message != null && (obj2 = message.toString()) != null) {
                str = obj2;
            }
            String msg = str;
            String tag = tag$iv;
            Log.v(tag, msg, thr);
            return;
        }
        if (message != null && (obj = message.toString()) != null) {
            str = obj;
        }
        String msg2 = str;
        String tag2 = tag$iv;
        Log.v(tag2, msg2);
    }

    public static /* bridge */ /* synthetic */ void debug$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        if ((i & 2) != 0) {
            th = (Throwable) null;
        }
        debug(ankoLogger, obj, th);
    }

    public static final void debug(AnkoLogger receiver, Object message, Throwable thr) {
        String obj;
        String obj2;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        String tag$iv = receiver.getLoggerTag();
        if (!Log.isLoggable(tag$iv, 3)) {
            return;
        }
        String str = "null";
        if (thr != null) {
            if (message != null && (obj2 = message.toString()) != null) {
                str = obj2;
            }
            String msg = str;
            String tag = tag$iv;
            Log.d(tag, msg, thr);
            return;
        }
        if (message != null && (obj = message.toString()) != null) {
            str = obj;
        }
        String msg2 = str;
        String tag2 = tag$iv;
        Log.d(tag2, msg2);
    }

    public static /* bridge */ /* synthetic */ void info$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        if ((i & 2) != 0) {
            th = (Throwable) null;
        }
        info(ankoLogger, obj, th);
    }

    public static final void info(AnkoLogger receiver, Object message, Throwable thr) {
        String obj;
        String obj2;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        String tag$iv = receiver.getLoggerTag();
        if (!Log.isLoggable(tag$iv, 4)) {
            return;
        }
        String str = "null";
        if (thr != null) {
            if (message != null && (obj2 = message.toString()) != null) {
                str = obj2;
            }
            String msg = str;
            String tag = tag$iv;
            Log.i(tag, msg, thr);
            return;
        }
        if (message != null && (obj = message.toString()) != null) {
            str = obj;
        }
        String msg2 = str;
        String tag2 = tag$iv;
        Log.i(tag2, msg2);
    }

    public static /* bridge */ /* synthetic */ void warn$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        if ((i & 2) != 0) {
            th = (Throwable) null;
        }
        warn(ankoLogger, obj, th);
    }

    public static final void warn(AnkoLogger receiver, Object message, Throwable thr) {
        String obj;
        String obj2;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        String tag$iv = receiver.getLoggerTag();
        if (!Log.isLoggable(tag$iv, 5)) {
            return;
        }
        String str = "null";
        if (thr != null) {
            if (message != null && (obj2 = message.toString()) != null) {
                str = obj2;
            }
            String msg = str;
            String tag = tag$iv;
            Log.w(tag, msg, thr);
            return;
        }
        if (message != null && (obj = message.toString()) != null) {
            str = obj;
        }
        String msg2 = str;
        String tag2 = tag$iv;
        Log.w(tag2, msg2);
    }

    public static /* bridge */ /* synthetic */ void error$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        if ((i & 2) != 0) {
            th = (Throwable) null;
        }
        error(ankoLogger, obj, th);
    }

    public static final void error(AnkoLogger receiver, Object message, Throwable thr) {
        String obj;
        String obj2;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        String tag$iv = receiver.getLoggerTag();
        if (!Log.isLoggable(tag$iv, 6)) {
            return;
        }
        String str = "null";
        if (thr != null) {
            if (message != null && (obj2 = message.toString()) != null) {
                str = obj2;
            }
            String msg = str;
            String tag = tag$iv;
            Log.e(tag, msg, thr);
            return;
        }
        if (message != null && (obj = message.toString()) != null) {
            str = obj;
        }
        String msg2 = str;
        String tag2 = tag$iv;
        Log.e(tag2, msg2);
    }

    public static /* bridge */ /* synthetic */ void wtf$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        if ((i & 2) != 0) {
            th = (Throwable) null;
        }
        wtf(ankoLogger, obj, th);
    }

    public static final void wtf(AnkoLogger receiver, Object message, Throwable thr) {
        String obj;
        String obj2;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        String str = "null";
        if (thr != null) {
            String loggerTag = receiver.getLoggerTag();
            if (message != null && (obj2 = message.toString()) != null) {
                str = obj2;
            }
            Log.wtf(loggerTag, str, thr);
            return;
        }
        String loggerTag2 = receiver.getLoggerTag();
        if (message != null && (obj = message.toString()) != null) {
            str = obj;
        }
        Log.wtf(loggerTag2, str);
    }

    public static final void verbose(AnkoLogger receiver, Function0<? extends Object> message) {
        String str;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        String tag = receiver.getLoggerTag();
        if (Log.isLoggable(tag, 2)) {
            Object invoke = message.invoke();
            if (invoke == null || (str = invoke.toString()) == null) {
                str = "null";
            }
            Log.v(tag, str);
        }
    }

    public static final void debug(AnkoLogger receiver, Function0<? extends Object> message) {
        String str;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        String tag = receiver.getLoggerTag();
        if (Log.isLoggable(tag, 3)) {
            Object invoke = message.invoke();
            if (invoke == null || (str = invoke.toString()) == null) {
                str = "null";
            }
            Log.d(tag, str);
        }
    }

    public static final void info(AnkoLogger receiver, Function0<? extends Object> message) {
        String str;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        String tag = receiver.getLoggerTag();
        if (Log.isLoggable(tag, 4)) {
            Object invoke = message.invoke();
            if (invoke == null || (str = invoke.toString()) == null) {
                str = "null";
            }
            Log.i(tag, str);
        }
    }

    public static final void warn(AnkoLogger receiver, Function0<? extends Object> message) {
        String str;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        String tag = receiver.getLoggerTag();
        if (Log.isLoggable(tag, 5)) {
            Object invoke = message.invoke();
            if (invoke == null || (str = invoke.toString()) == null) {
                str = "null";
            }
            Log.w(tag, str);
        }
    }

    public static final void error(AnkoLogger receiver, Function0<? extends Object> message) {
        String str;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        String tag = receiver.getLoggerTag();
        if (Log.isLoggable(tag, 6)) {
            Object invoke = message.invoke();
            if (invoke == null || (str = invoke.toString()) == null) {
                str = "null";
            }
            Log.e(tag, str);
        }
    }

    public static final String getStackTraceString(Throwable receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        String stackTraceString = Log.getStackTraceString(receiver);
        Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(this)");
        return stackTraceString;
    }

    private static final void log(AnkoLogger logger, Object message, Throwable thr, int level, Function2<? super String, ? super String, Unit> function2, Function3<? super String, ? super String, ? super Throwable, Unit> function3) {
        String obj;
        String obj2;
        String tag = logger.getLoggerTag();
        if (Log.isLoggable(tag, level)) {
            String str = "null";
            if (thr != null) {
                if (message != null && (obj2 = message.toString()) != null) {
                    str = obj2;
                }
                function3.invoke(tag, str, thr);
                return;
            }
            if (message != null && (obj = message.toString()) != null) {
                str = obj;
            }
            function2.invoke(tag, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getTag(Class<?> cls) {
        String tag = cls.getSimpleName();
        if (tag.length() <= 23) {
            Intrinsics.checkExpressionValueIsNotNull(tag, "tag");
            return tag;
        }
        if (tag != null) {
            String substring = tag.substring(0, 23);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
