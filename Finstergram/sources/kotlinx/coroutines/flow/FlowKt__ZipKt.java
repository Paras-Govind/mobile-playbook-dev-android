package kotlinx.coroutines.flow;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: Zip.kt */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aq\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00030\u00012*\b\u0004\u0010\u0006\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\n\u001ae\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u000b2*\b\u0004\u0010\u0006\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\f\u001aº\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u0011\"\u0004\b\u0005\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00012:\u0010\u0006\u001a6\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0017ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a \u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u000124\u0010\u0006\u001a0\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0019ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0088\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000120\b\u0001\u0010\u0006\u001a*\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a\u008a\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012F\u0010\u0006\u001aB\b\u0001\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\u000e¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001dø\u0001\u0000¢\u0006\u0002\u0010\"\u001a\u0082\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00030\u00012;\b\u0005\u0010\u0006\u001a5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001d¢\u0006\u0002\b&H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010'\u001av\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u000b2;\b\u0005\u0010\u0006\u001a5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001d¢\u0006\u0002\b&H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010(\u001aÍ\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u0011\"\u0004\b\u0005\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00012M\b\u0001\u0010\u0006\u001aG\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0)¢\u0006\u0002\b&ø\u0001\u0000¢\u0006\u0002\u0010*\u001a³\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u00012G\b\u0001\u0010\u0006\u001aA\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0017¢\u0006\u0002\b&ø\u0001\u0000¢\u0006\u0002\u0010+\u001a\u0099\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00012A\b\u0001\u0010\u0006\u001a;\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0019¢\u0006\u0002\b&ø\u0001\u0000¢\u0006\u0002\u0010,\u001a\u009d\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012Y\b\u0001\u0010\u0006\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\u000e¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001b¢\u0006\u0002\b&ø\u0001\u0000¢\u0006\u0002\u0010-\u001a\u0084\u0001\u0010.\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00030\u00012;\b\u0005\u0010\u0006\u001a5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001d¢\u0006\u0002\b&H\u0082\bø\u0001\u0000¢\u0006\u0004\b/\u0010'\u001as\u00100\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00030\u00012*\b\u0004\u0010\u0006\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007H\u0082\bø\u0001\u0000¢\u0006\u0004\b1\u0010\n\u001a!\u00102\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u000503\"\u0004\b\u0000\u0010\u0003H\u0002¢\u0006\u0002\b4\u001a\u008a\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012F\u0010\u0006\u001aB\b\u0001\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\u000e¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001dH\u0007ø\u0001\u0000¢\u0006\u0004\b5\u0010\"\u001a\u009d\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012Y\b\u0001\u0010\u0006\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\u000e¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001b¢\u0006\u0002\b&H\u0007ø\u0001\u0000¢\u0006\u0004\b6\u0010-\u001ah\u00107\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u00108\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012(\u0010\u0006\u001a$\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001dø\u0001\u0000¢\u0006\u0002\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u00069"}, d2 = {"combine", "Lkotlinx/coroutines/flow/Flow;", "R", "T", "flows", "", "transform", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "([Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "T1", "T2", "T3", "T4", "T5", "flow", "flow2", "flow3", "flow4", "flow5", "Lkotlin/Function6;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function6;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function5;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function5;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function4;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "a", "b", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "combineTransform", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/ExtensionFunctionType;", "([Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function7;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function7;)Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function6;)Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function5;)Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "combineTransformUnsafe", "combineTransformUnsafe$FlowKt__ZipKt", "combineUnsafe", "combineUnsafe$FlowKt__ZipKt", "nullArrayFactory", "Lkotlin/Function0;", "nullArrayFactory$FlowKt__ZipKt", "flowCombine", "flowCombineTransform", "zip", "other", "kotlinx-coroutines-core"}, k = 5, mv = {1, 5, 1}, xi = 48, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: classes.dex */
public final /* synthetic */ class FlowKt__ZipKt {
    public static final <T1, T2, R> Flow<R> flowCombine(final Flow<? extends T1> flow, final Flow<? extends T2> flow2, final Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        return new Flow<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super R> flowCollector, Continuation<? super Unit> continuation) {
                Function0 nullArrayFactory$FlowKt__ZipKt;
                Flow[] flowArr = {Flow.this, flow2};
                nullArrayFactory$FlowKt__ZipKt = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
                Object combineInternal = CombineKt.combineInternal(flowCollector, flowArr, nullArrayFactory$FlowKt__ZipKt, new FlowKt__ZipKt$combine$1$1(function3, null), continuation);
                return combineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? combineInternal : Unit.INSTANCE;
            }
        };
    }

    public static final <T1, T2, R> Flow<R> combine(Flow<? extends T1> flow, Flow<? extends T2> flow2, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        return FlowKt.flowCombine(flow, flow2, function3);
    }

    public static final <T1, T2, R> Flow<R> flowCombineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, Function4<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Flow[] flows$iv = {flow, flow2};
        return FlowKt.flow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$1(flows$iv, null, function4));
    }

    public static final <T1, T2, R> Flow<R> combineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, Function4<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Flow[] flows$iv = {flow, flow2};
        return FlowKt.flow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$2(flows$iv, null, function4));
    }

    public static final <T1, T2, T3, R> Flow<R> combine(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, final Function4<? super T1, ? super T2, ? super T3, ? super Continuation<? super R>, ? extends Object> function4) {
        final Flow[] flows$iv = {flow, flow2, flow3};
        return new Flow<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector collector, Continuation $completion) {
                Function0 nullArrayFactory$FlowKt__ZipKt;
                Flow[] flowArr = flows$iv;
                nullArrayFactory$FlowKt__ZipKt = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
                Object combineInternal = CombineKt.combineInternal(collector, flowArr, nullArrayFactory$FlowKt__ZipKt, new AnonymousClass2(null, function4), $completion);
                return combineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? combineInternal : Unit.INSTANCE;
            }

            /* compiled from: Zip.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¨\u0006\u0007"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "kotlinx/coroutines/flow/FlowKt__ZipKt$combineUnsafe$1$1"}, k = 3, mv = {1, 5, 1}, xi = 48)
            @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2", f = "Zip.kt", i = {}, l = {333, 333}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2, reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass2 extends SuspendLambda implements Function3<FlowCollector<? super R>, Object[], Continuation<? super Unit>, Object> {
                final /* synthetic */ Function4 $transform$inlined;
                private /* synthetic */ Object L$0;
                /* synthetic */ Object L$1;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(Continuation continuation, Function4 function4) {
                    super(3, continuation);
                    this.$transform$inlined = function4;
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(FlowCollector<? super R> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation, this.$transform$inlined);
                    anonymousClass2.L$0 = flowCollector;
                    anonymousClass2.L$1 = objArr;
                    return anonymousClass2.invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
                /* JADX WARN: Removed duplicated region for block: B:12:0x0065 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:13:0x0066  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r15) {
                    /*
                        r14 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r14.label
                        r2 = 2
                        r3 = 0
                        switch(r1) {
                            case 0: goto L25;
                            case 1: goto L18;
                            case 2: goto L13;
                            default: goto Lb;
                        }
                    Lb:
                        java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r15.<init>(r0)
                        throw r15
                    L13:
                        r0 = r14
                        kotlin.ResultKt.throwOnFailure(r15)
                        goto L68
                    L18:
                        r1 = r14
                        r3 = 0
                        java.lang.Object r4 = r1.L$0
                        kotlinx.coroutines.flow.FlowCollector r4 = (kotlinx.coroutines.flow.FlowCollector) r4
                        kotlin.ResultKt.throwOnFailure(r15)
                        r8 = r3
                        r3 = r1
                        r1 = r15
                        goto L56
                    L25:
                        kotlin.ResultKt.throwOnFailure(r15)
                        r1 = r14
                        java.lang.Object r4 = r1.L$0
                        kotlinx.coroutines.flow.FlowCollector r4 = (kotlinx.coroutines.flow.FlowCollector) r4
                        java.lang.Object r5 = r1.L$1
                        java.lang.Object[] r5 = (java.lang.Object[]) r5
                        r6 = r1
                        kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                        r7 = r5
                        r8 = 0
                        kotlin.jvm.functions.Function4 r9 = r1.$transform$inlined
                        r3 = r7[r3]
                        r10 = 1
                        r11 = r7[r10]
                        r12 = r7[r2]
                        r1.L$0 = r4
                        r1.label = r10
                        r6 = 6
                        kotlin.jvm.internal.InlineMarker.mark(r6)
                        java.lang.Object r3 = r9.invoke(r3, r11, r12, r1)
                        r6 = 7
                        kotlin.jvm.internal.InlineMarker.mark(r6)
                        if (r3 != r0) goto L52
                        return r0
                    L52:
                        r13 = r1
                        r1 = r15
                        r15 = r3
                        r3 = r13
                    L56:
                        r5 = r3
                        kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                        r6 = 0
                        r3.L$0 = r6
                        r3.label = r2
                        java.lang.Object r15 = r4.emit(r15, r5)
                        if (r15 != r0) goto L66
                        return r0
                    L66:
                        r15 = r1
                        r0 = r3
                    L68:
                        kotlin.Unit r1 = kotlin.Unit.INSTANCE
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }
        };
    }

    public static final <T1, T2, T3, R> Flow<R> combineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Function5<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super T3, ? super Continuation<? super Unit>, ? extends Object> function5) {
        Flow[] flows$iv = {flow, flow2, flow3};
        return FlowKt.flow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3(flows$iv, null, function5));
    }

    public static final <T1, T2, T3, T4, R> Flow<R> combine(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, final Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super Continuation<? super R>, ? extends Object> function5) {
        final Flow[] flows$iv = {flow, flow2, flow3, flow4};
        return new Flow<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector collector, Continuation $completion) {
                Function0 nullArrayFactory$FlowKt__ZipKt;
                Flow[] flowArr = flows$iv;
                nullArrayFactory$FlowKt__ZipKt = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
                Object combineInternal = CombineKt.combineInternal(collector, flowArr, nullArrayFactory$FlowKt__ZipKt, new AnonymousClass2(null, function5), $completion);
                return combineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? combineInternal : Unit.INSTANCE;
            }

            /* compiled from: Zip.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¨\u0006\u0007"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "kotlinx/coroutines/flow/FlowKt__ZipKt$combineUnsafe$1$1"}, k = 3, mv = {1, 5, 1}, xi = 48)
            @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2", f = "Zip.kt", i = {}, l = {333, 333}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2, reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass2 extends SuspendLambda implements Function3<FlowCollector<? super R>, Object[], Continuation<? super Unit>, Object> {
                final /* synthetic */ Function5 $transform$inlined;
                private /* synthetic */ Object L$0;
                /* synthetic */ Object L$1;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(Continuation continuation, Function5 function5) {
                    super(3, continuation);
                    this.$transform$inlined = function5;
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(FlowCollector<? super R> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation, this.$transform$inlined);
                    anonymousClass2.L$0 = flowCollector;
                    anonymousClass2.L$1 = objArr;
                    return anonymousClass2.invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000a. Please report as an issue. */
                /* JADX WARN: Removed duplicated region for block: B:12:0x007a A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:13:0x007b  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r17) {
                    /*
                        r16 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        r1 = r16
                        int r2 = r1.label
                        r3 = 2
                        r4 = 0
                        switch(r2) {
                            case 0: goto L2d;
                            case 1: goto L1e;
                            case 2: goto L15;
                            default: goto Ld;
                        }
                    Ld:
                        java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                        java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                        r0.<init>(r2)
                        throw r0
                    L15:
                        r0 = r16
                        r2 = r17
                        kotlin.ResultKt.throwOnFailure(r2)
                        goto L7d
                    L1e:
                        r2 = r16
                        r5 = r17
                        r4 = 0
                        java.lang.Object r6 = r2.L$0
                        kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                        kotlin.ResultKt.throwOnFailure(r5)
                        r7 = r6
                        r6 = r5
                        goto L6b
                    L2d:
                        kotlin.ResultKt.throwOnFailure(r17)
                        r2 = r16
                        r5 = r17
                        java.lang.Object r6 = r2.L$0
                        kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                        java.lang.Object r7 = r2.L$1
                        r13 = r7
                        java.lang.Object[] r13 = (java.lang.Object[]) r13
                        r7 = r2
                        kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                        r8 = r13
                        r14 = r8
                        r15 = 0
                        kotlin.jvm.functions.Function5 r8 = r2.$transform$inlined
                        r4 = r14[r4]
                        r9 = 1
                        r10 = r14[r9]
                        r11 = r14[r3]
                        r12 = 3
                        r12 = r14[r12]
                        r2.L$0 = r6
                        r2.label = r9
                        r7 = 6
                        kotlin.jvm.internal.InlineMarker.mark(r7)
                        r7 = r8
                        r8 = r4
                        r9 = r10
                        r10 = r11
                        r11 = r12
                        r12 = r2
                        java.lang.Object r4 = r7.invoke(r8, r9, r10, r11, r12)
                        r7 = 7
                        kotlin.jvm.internal.InlineMarker.mark(r7)
                        if (r4 != r0) goto L68
                        return r0
                    L68:
                        r7 = r6
                        r6 = r4
                        r4 = r15
                    L6b:
                        r4 = r2
                        kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                        r8 = 0
                        r2.L$0 = r8
                        r2.label = r3
                        java.lang.Object r3 = r7.emit(r6, r4)
                        if (r3 != r0) goto L7b
                        return r0
                    L7b:
                        r0 = r2
                        r2 = r5
                    L7d:
                        kotlin.Unit r3 = kotlin.Unit.INSTANCE
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }
        };
    }

    public static final <T1, T2, T3, T4, R> Flow<R> combineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, Function6<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super T3, ? super T4, ? super Continuation<? super Unit>, ? extends Object> function6) {
        Flow[] flows$iv = {flow, flow2, flow3, flow4};
        return FlowKt.flow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$4(flows$iv, null, function6));
    }

    public static final <T1, T2, T3, T4, T5, R> Flow<R> combine(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, Flow<? extends T5> flow5, final Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super Continuation<? super R>, ? extends Object> function6) {
        final Flow[] flows$iv = {flow, flow2, flow3, flow4, flow5};
        return new Flow<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector collector, Continuation $completion) {
                Function0 nullArrayFactory$FlowKt__ZipKt;
                Flow[] flowArr = flows$iv;
                nullArrayFactory$FlowKt__ZipKt = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
                Object combineInternal = CombineKt.combineInternal(collector, flowArr, nullArrayFactory$FlowKt__ZipKt, new AnonymousClass2(null, function6), $completion);
                return combineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? combineInternal : Unit.INSTANCE;
            }

            /* compiled from: Zip.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¨\u0006\u0007"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "kotlinx/coroutines/flow/FlowKt__ZipKt$combineUnsafe$1$1"}, k = 3, mv = {1, 5, 1}, xi = 48)
            @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2", f = "Zip.kt", i = {}, l = {333, 333}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2, reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass2 extends SuspendLambda implements Function3<FlowCollector<? super R>, Object[], Continuation<? super Unit>, Object> {
                final /* synthetic */ Function6 $transform$inlined;
                private /* synthetic */ Object L$0;
                /* synthetic */ Object L$1;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(Continuation continuation, Function6 function6) {
                    super(3, continuation);
                    this.$transform$inlined = function6;
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(FlowCollector<? super R> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation, this.$transform$inlined);
                    anonymousClass2.L$0 = flowCollector;
                    anonymousClass2.L$1 = objArr;
                    return anonymousClass2.invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000a. Please report as an issue. */
                /* JADX WARN: Removed duplicated region for block: B:12:0x0080 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:13:0x0081  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r18) {
                    /*
                        r17 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        r1 = r17
                        int r2 = r1.label
                        r3 = 2
                        r4 = 0
                        switch(r2) {
                            case 0: goto L2d;
                            case 1: goto L1e;
                            case 2: goto L15;
                            default: goto Ld;
                        }
                    Ld:
                        java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                        java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                        r0.<init>(r2)
                        throw r0
                    L15:
                        r0 = r17
                        r2 = r18
                        kotlin.ResultKt.throwOnFailure(r2)
                        goto L83
                    L1e:
                        r2 = r17
                        r5 = r18
                        r4 = 0
                        java.lang.Object r6 = r2.L$0
                        kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                        kotlin.ResultKt.throwOnFailure(r5)
                        r7 = r6
                        r6 = r5
                        goto L71
                    L2d:
                        kotlin.ResultKt.throwOnFailure(r18)
                        r2 = r17
                        r5 = r18
                        java.lang.Object r6 = r2.L$0
                        kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                        java.lang.Object r7 = r2.L$1
                        r14 = r7
                        java.lang.Object[] r14 = (java.lang.Object[]) r14
                        r7 = r2
                        kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                        r8 = r14
                        r15 = r8
                        r16 = 0
                        kotlin.jvm.functions.Function6 r8 = r2.$transform$inlined
                        r4 = r15[r4]
                        r9 = 1
                        r10 = r15[r9]
                        r11 = r15[r3]
                        r12 = 3
                        r12 = r15[r12]
                        r13 = 4
                        r13 = r15[r13]
                        r2.L$0 = r6
                        r2.label = r9
                        r7 = 6
                        kotlin.jvm.internal.InlineMarker.mark(r7)
                        r7 = r8
                        r8 = r4
                        r9 = r10
                        r10 = r11
                        r11 = r12
                        r12 = r13
                        r13 = r2
                        java.lang.Object r4 = r7.invoke(r8, r9, r10, r11, r12, r13)
                        r7 = 7
                        kotlin.jvm.internal.InlineMarker.mark(r7)
                        if (r4 != r0) goto L6d
                        return r0
                    L6d:
                        r7 = r6
                        r6 = r4
                        r4 = r16
                    L71:
                        r4 = r2
                        kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                        r8 = 0
                        r2.L$0 = r8
                        r2.label = r3
                        java.lang.Object r3 = r7.emit(r6, r4)
                        if (r3 != r0) goto L81
                        return r0
                    L81:
                        r0 = r2
                        r2 = r5
                    L83:
                        kotlin.Unit r3 = kotlin.Unit.INSTANCE
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, R> Flow<R> combineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, Flow<? extends T5> flow5, Function7<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super Continuation<? super Unit>, ? extends Object> function7) {
        Flow[] flows$iv = {flow, flow2, flow3, flow4, flow5};
        return FlowKt.flow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5(flows$iv, null, function7));
    }

    public static final /* synthetic */ Flow combine(Flow[] flows, Function2 transform) {
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$2(flows, transform);
    }

    public static final /* synthetic */ Flow combineTransform(Flow[] flows, Function3 transform) {
        Intrinsics.needClassReification();
        return FlowKt.flow(new FlowKt__ZipKt$combineTransform$6(flows, transform, null));
    }

    private static final /* synthetic */ Flow combineUnsafe$FlowKt__ZipKt(Flow[] flows, Function2 transform) {
        return new FlowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1(flows, transform);
    }

    public static final <T> Function0<T[]> nullArrayFactory$FlowKt__ZipKt() {
        return new Function0() { // from class: kotlinx.coroutines.flow.FlowKt__ZipKt$nullArrayFactory$1
            @Override // kotlin.jvm.functions.Function0
            public final Void invoke() {
                return null;
            }
        };
    }

    public static final /* synthetic */ Flow combine(Iterable flows, Function2 transform) {
        Collection $this$toTypedArray$iv = CollectionsKt.toList(flows);
        Object[] array = $this$toTypedArray$iv.toArray(new Flow[0]);
        if (array != null) {
            Flow[] flowArray = (Flow[]) array;
            return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$3(flowArray, transform);
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final /* synthetic */ Flow combineTransform(Iterable flows, Function3 transform) {
        Collection $this$toTypedArray$iv = CollectionsKt.toList(flows);
        Object[] array = $this$toTypedArray$iv.toArray(new Flow[0]);
        if (array != null) {
            Flow[] flowArray = (Flow[]) array;
            Intrinsics.needClassReification();
            return FlowKt.flow(new FlowKt__ZipKt$combineTransform$7(flowArray, transform, null));
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final <T1, T2, R> Flow<R> zip(Flow<? extends T1> flow, Flow<? extends T2> flow2, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        return CombineKt.zipImpl(flow, flow2, function3);
    }
}
