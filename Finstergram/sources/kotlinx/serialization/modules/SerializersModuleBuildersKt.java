package kotlinx.serialization.modules;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

/* compiled from: SerializersModuleBuilders.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u001a,\u0010\u0007\u001a\u00020\u0001\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\r\u001a#\u0010\u0007\u001a\u00020\u0001\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\rH\u0086\b\u001a'\u0010\u000e\u001a\u00020\u0005\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t*\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\rH\u0086\b\u001a[\u0010\u000f\u001a\u00020\u0005\"\b\b\u0000\u0010\u0010*\u00020\t*\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\u000b2\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u0002H\u0010\u0018\u00010\r2\u001f\b\u0002\u0010\u0002\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\u0013\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"SerializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "builderAction", "Lkotlin/Function1;", "Lkotlinx/serialization/modules/SerializersModuleBuilder;", "", "Lkotlin/ExtensionFunctionType;", "serializersModuleOf", "T", "", "kClass", "Lkotlin/reflect/KClass;", "serializer", "Lkotlinx/serialization/KSerializer;", "contextual", "polymorphic", "Base", "baseClass", "baseSerializer", "Lkotlinx/serialization/modules/PolymorphicModuleBuilder;", "kotlinx-serialization-core"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SerializersModuleBuildersKt {
    public static final <T> SerializersModule serializersModuleOf(KClass<T> kClass, KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        SerializersModuleBuilder builder$iv = new SerializersModuleBuilder();
        builder$iv.contextual(kClass, serializer);
        return builder$iv.build();
    }

    public static final /* synthetic */ <T> SerializersModule serializersModuleOf(KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.reifiedOperationMarker(4, "T");
        return serializersModuleOf(Reflection.getOrCreateKotlinClass(Object.class), serializer);
    }

    public static final SerializersModule SerializersModule(Function1<? super SerializersModuleBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        SerializersModuleBuilder builder = new SerializersModuleBuilder();
        builderAction.invoke(builder);
        return builder.build();
    }

    public static final /* synthetic */ <T> void contextual(SerializersModuleBuilder $this$contextual, KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter($this$contextual, "<this>");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.reifiedOperationMarker(4, "T");
        $this$contextual.contextual(Reflection.getOrCreateKotlinClass(Object.class), serializer);
    }

    public static /* synthetic */ void polymorphic$default(SerializersModuleBuilder $this$polymorphic_u24default, KClass baseClass, KSerializer baseSerializer, Function1 builderAction, int i, Object obj) {
        if ((i & 2) != 0) {
            baseSerializer = null;
        }
        if ((i & 4) != 0) {
            Function1 builderAction2 = new Function1<PolymorphicModuleBuilder<? super Base>, Unit>() { // from class: kotlinx.serialization.modules.SerializersModuleBuildersKt$polymorphic$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object p1) {
                    invoke((PolymorphicModuleBuilder) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(PolymorphicModuleBuilder<? super Base> polymorphicModuleBuilder) {
                    Intrinsics.checkNotNullParameter(polymorphicModuleBuilder, "$this$null");
                }
            };
            builderAction = builderAction2;
        }
        Intrinsics.checkNotNullParameter($this$polymorphic_u24default, "<this>");
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        PolymorphicModuleBuilder builder = new PolymorphicModuleBuilder(baseClass, baseSerializer);
        builderAction.invoke(builder);
        builder.buildTo($this$polymorphic_u24default);
    }

    public static final <Base> void polymorphic(SerializersModuleBuilder $this$polymorphic, KClass<Base> baseClass, KSerializer<Base> kSerializer, Function1<? super PolymorphicModuleBuilder<? super Base>, Unit> builderAction) {
        Intrinsics.checkNotNullParameter($this$polymorphic, "<this>");
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        PolymorphicModuleBuilder builder = new PolymorphicModuleBuilder(baseClass, kSerializer);
        builderAction.invoke(builder);
        builder.buildTo($this$polymorphic);
    }
}
