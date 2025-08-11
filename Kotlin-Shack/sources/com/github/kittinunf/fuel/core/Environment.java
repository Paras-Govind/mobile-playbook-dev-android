package com.github.kittinunf.fuel.core;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: Environment.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/github/kittinunf/fuel/core/Environment;", "", "callbackExecutor", "Ljava/util/concurrent/Executor;", "getCallbackExecutor", "()Ljava/util/concurrent/Executor;", "setCallbackExecutor", "(Ljava/util/concurrent/Executor;)V", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public interface Environment {
    @NotNull
    Executor getCallbackExecutor();

    void setCallbackExecutor(@NotNull Executor executor);
}
