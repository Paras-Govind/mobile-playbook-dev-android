package org.jetbrains.anko;

import kotlin.Metadata;

/* compiled from: Logging.kt */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/anko/AnkoLogger;", "", "loggerTag", "", "getLoggerTag", "()Ljava/lang/String;", "commons_release"}, k = 1, mv = {1, 1, 5})
/* loaded from: classes.dex */
public interface AnkoLogger {
    String getLoggerTag();

    /* compiled from: Logging.kt */
    @Metadata(bv = {1, 0, 1}, k = 3, mv = {1, 1, 5})
    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static String getLoggerTag(AnkoLogger $this) {
            String tag;
            tag = Logging.getTag($this.getClass());
            return tag;
        }
    }
}
