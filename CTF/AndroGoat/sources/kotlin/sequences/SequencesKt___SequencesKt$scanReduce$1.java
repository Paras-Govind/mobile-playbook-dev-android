package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Add missing generic type declarations: [S] */
/* compiled from: _Sequences.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "S", "T", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$scanReduce$1", f = "_Sequences.kt", i = {0, 0, 0, 1, 1, 1}, l = {1492, 1495}, m = "invokeSuspend", n = {"$this$sequence", "iterator", "accumulator", "$this$sequence", "iterator", "accumulator"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* loaded from: classes.dex */
final class SequencesKt___SequencesKt$scanReduce$1<S> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super S>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $operation;
    final /* synthetic */ Sequence $this_scanReduce;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private SequenceScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$scanReduce$1(Sequence sequence, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_scanReduce = sequence;
        this.$operation = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SequencesKt___SequencesKt$scanReduce$1 sequencesKt___SequencesKt$scanReduce$1 = new SequencesKt___SequencesKt$scanReduce$1(this.$this_scanReduce, this.$operation, completion);
        sequencesKt___SequencesKt$scanReduce$1.p$ = (SequenceScope) obj;
        return sequencesKt___SequencesKt$scanReduce$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$scanReduce$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0068  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L3c
            r4 = 0
            if (r1 == r3) goto L2a
            if (r1 != r2) goto L22
            r1 = r4
            r3 = r4
            java.lang.Object r3 = r8.L$2
            java.lang.Object r5 = r8.L$1
            r4 = r5
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r8.L$0
            r1 = r5
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            kotlin.ResultKt.throwOnFailure(r9)
            r5 = r8
            goto L81
        L22:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2a:
            r1 = r4
            r3 = r4
            java.lang.Object r3 = r8.L$2
            java.lang.Object r5 = r8.L$1
            r4 = r5
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r8.L$0
            r1 = r5
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L61
        L3c:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.sequences.SequenceScope r1 = r8.p$
            kotlin.sequences.Sequence r4 = r8.$this_scanReduce
            java.util.Iterator r4 = r4.iterator()
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L82
            java.lang.Object r5 = r4.next()
            r8.L$0 = r1
            r8.L$1 = r4
            r8.L$2 = r5
            r8.label = r3
            java.lang.Object r3 = r1.yield(r5, r8)
            if (r3 != r0) goto L60
            return r0
        L60:
            r3 = r5
        L61:
            r5 = r8
        L62:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L83
            kotlin.jvm.functions.Function2 r6 = r5.$operation
            java.lang.Object r7 = r4.next()
            java.lang.Object r3 = r6.invoke(r3, r7)
            r5.L$0 = r1
            r5.L$1 = r4
            r5.L$2 = r3
            r5.label = r2
            java.lang.Object r6 = r1.yield(r3, r5)
            if (r6 != r0) goto L81
            return r0
        L81:
            goto L62
        L82:
            r5 = r8
        L83:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$scanReduce$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
