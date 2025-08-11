package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Ref;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: Collect.kt */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class DistinctFlowImpl$collect$$inlined$collect$1<T> implements FlowCollector<T> {
    final /* synthetic */ FlowCollector $collector$inlined;
    final /* synthetic */ Ref.ObjectRef $previousKey$inlined;
    final /* synthetic */ DistinctFlowImpl this$0;

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1", f = "Distinct.kt", i = {}, l = {139}, m = "emit", n = {}, s = {})
    /* renamed from: kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DistinctFlowImpl$collect$$inlined$collect$1.this.emit(null, this);
        }
    }

    public DistinctFlowImpl$collect$$inlined$collect$1(DistinctFlowImpl distinctFlowImpl, Ref.ObjectRef objectRef, FlowCollector flowCollector) {
        this.this$0 = distinctFlowImpl;
        this.$previousKey$inlined = objectRef;
        this.$collector$inlined = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object emit(T r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1$1 r0 = (kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1$1 r0 = new kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1$1
            r0.<init>(r11)
        L19:
            r11 = r0
            java.lang.Object r0 = r11.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r11.label
            switch(r2) {
                case 0: goto L33;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2d:
            r10 = 0
            r10 = 0
            kotlin.ResultKt.throwOnFailure(r0)
            goto L71
        L33:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r9
            r3 = r11
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = r10
            r5 = 0
            kotlinx.coroutines.flow.DistinctFlowImpl r6 = r2.this$0
            kotlin.jvm.functions.Function1<T, java.lang.Object> r6 = r6.keySelector
            java.lang.Object r6 = r6.invoke(r4)
            kotlin.jvm.internal.Ref$ObjectRef r7 = r2.$previousKey$inlined
            T r7 = r7.element
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r7 == r8) goto L60
            kotlinx.coroutines.flow.DistinctFlowImpl r7 = r2.this$0
            kotlin.jvm.functions.Function2<java.lang.Object, java.lang.Object, java.lang.Boolean> r7 = r7.areEquivalent
            kotlin.jvm.internal.Ref$ObjectRef r8 = r2.$previousKey$inlined
            T r8 = r8.element
            java.lang.Object r7 = r7.invoke(r8, r6)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L72
        L60:
            kotlin.jvm.internal.Ref$ObjectRef r7 = r2.$previousKey$inlined
            r7.element = r6
            kotlinx.coroutines.flow.FlowCollector r3 = r2.$collector$inlined
            r7 = 1
            r11.label = r7
            java.lang.Object r10 = r3.emit(r4, r11)
            if (r10 != r1) goto L70
            return r1
        L70:
            r10 = r5
        L71:
        L72:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
