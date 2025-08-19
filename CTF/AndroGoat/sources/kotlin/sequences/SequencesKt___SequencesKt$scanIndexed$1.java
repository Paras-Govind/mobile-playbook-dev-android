package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: _Sequences.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$scanIndexed$1", f = "_Sequences.kt", i = {0, 1, 1, 1, 1}, l = {1462, 1467}, m = "invokeSuspend", n = {"$this$sequence", "$this$sequence", "index", "accumulator", "element"}, s = {"L$0", "L$0", "I$0", "L$1", "L$2"})
/* loaded from: classes.dex */
final class SequencesKt___SequencesKt$scanIndexed$1<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $initial;
    final /* synthetic */ Function3 $operation;
    final /* synthetic */ Sequence $this_scanIndexed;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private SequenceScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$scanIndexed$1(Sequence sequence, Object obj, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_scanIndexed = sequence;
        this.$initial = obj;
        this.$operation = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SequencesKt___SequencesKt$scanIndexed$1 sequencesKt___SequencesKt$scanIndexed$1 = new SequencesKt___SequencesKt$scanIndexed$1(this.$this_scanIndexed, this.$initial, this.$operation, completion);
        sequencesKt___SequencesKt$scanIndexed$1.p$ = (SequenceScope) obj;
        return sequencesKt___SequencesKt$scanIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$scanIndexed$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0090  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0087 -> B:7:0x008c). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L3c
            r4 = 0
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            r1 = r4
            r3 = r4
            r5 = 0
            java.lang.Object r6 = r11.L$3
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.lang.Object r4 = r11.L$2
            java.lang.Object r3 = r11.L$1
            int r5 = r11.I$0
            java.lang.Object r7 = r11.L$0
            r1 = r7
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            kotlin.ResultKt.throwOnFailure(r12)
            r7 = r6
            r6 = r11
            r10 = r5
            r5 = r3
            r3 = r10
            goto L8c
        L2a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L32:
            r1 = r4
            java.lang.Object r3 = r11.L$0
            r1 = r3
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            kotlin.ResultKt.throwOnFailure(r12)
            goto L4e
        L3c:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.sequences.SequenceScope r1 = r11.p$
            java.lang.Object r4 = r11.$initial
            r11.L$0 = r1
            r11.label = r3
            java.lang.Object r3 = r1.yield(r4, r11)
            if (r3 != r0) goto L4e
            return r0
        L4e:
            r3 = 0
            java.lang.Object r4 = r11.$initial
            kotlin.sequences.Sequence r5 = r11.$this_scanIndexed
            java.util.Iterator r5 = r5.iterator()
            r6 = r5
            r5 = r11
        L59:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L90
            java.lang.Object r7 = r6.next()
            kotlin.jvm.functions.Function3 r8 = r5.$operation
            int r9 = r3 + 1
            if (r3 >= 0) goto L6c
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L6c:
            java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            java.lang.Object r3 = r8.invoke(r3, r4, r7)
            r5.L$0 = r1
            r5.I$0 = r9
            r5.L$1 = r3
            r5.L$2 = r7
            r5.L$3 = r6
            r5.label = r2
            java.lang.Object r4 = r1.yield(r3, r5)
            if (r4 != r0) goto L87
            return r0
        L87:
            r4 = r7
            r7 = r6
            r6 = r5
            r5 = r3
            r3 = r9
        L8c:
            r4 = r5
            r5 = r6
            r6 = r7
            goto L59
        L90:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$scanIndexed$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
