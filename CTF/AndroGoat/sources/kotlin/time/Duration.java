package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: Duration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0087@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0000H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010-J\u001b\u0010)\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b.\u0010,J\u0013\u0010/\u001a\u0002002\b\u0010&\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u00020\tHÖ\u0001J\r\u00103\u001a\u000200¢\u0006\u0004\b4\u00105J\r\u00106\u001a\u000200¢\u0006\u0004\b7\u00105J\r\u00108\u001a\u000200¢\u0006\u0004\b9\u00105J\r\u0010:\u001a\u000200¢\u0006\u0004\b;\u00105J\u001b\u0010<\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b=\u0010,J\u001b\u0010>\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010,J\u0017\u0010@\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0004\bA\u0010(J\u001b\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010,J\u001b\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010-J\u008d\u0001\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2u\u0010F\u001aq\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0GH\u0086\b¢\u0006\u0004\bO\u0010PJx\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2`\u0010F\u001a\\\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0QH\u0086\b¢\u0006\u0004\bO\u0010RJc\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2K\u0010F\u001aG\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0SH\u0086\b¢\u0006\u0004\bO\u0010TJN\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E26\u0010F\u001a2\u0012\u0013\u0012\u00110V¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0UH\u0086\b¢\u0006\u0004\bO\u0010WJ\u0019\u0010X\u001a\u00020\u00032\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\b\\\u0010]J\u0019\u0010^\u001a\u00020\t2\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\b_\u0010`J\r\u0010a\u001a\u00020b¢\u0006\u0004\bc\u0010dJ\u0019\u0010e\u001a\u00020V2\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\bf\u0010gJ\r\u0010h\u001a\u00020V¢\u0006\u0004\bi\u0010jJ\r\u0010k\u001a\u00020V¢\u0006\u0004\bl\u0010jJ\u000f\u0010m\u001a\u00020bH\u0016¢\u0006\u0004\bn\u0010dJ#\u0010m\u001a\u00020b2\n\u0010Y\u001a\u00060Zj\u0002`[2\b\b\u0002\u0010o\u001a\u00020\t¢\u0006\u0004\bn\u0010pJ\u0013\u0010q\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\br\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00008Fø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0011\u0010\u0016\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0011\u0010\u0018\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0011\u0010\u001a\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u001a\u0010\u001c\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR\u001a\u0010\u001f\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\rR\u001a\u0010\"\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006t"}, d2 = {"Lkotlin/time/Duration;", "", "value", "", "constructor-impl", "(D)D", "absoluteValue", "getAbsoluteValue-impl", "hoursComponent", "", "hoursComponent$annotations", "()V", "getHoursComponent-impl", "(D)I", "inDays", "getInDays-impl", "inHours", "getInHours-impl", "inMicroseconds", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds-impl", "inMinutes", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds-impl", "inSeconds", "getInSeconds-impl", "minutesComponent", "minutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "nanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "secondsComponent$annotations", "getSecondsComponent-impl", "compareTo", "other", "compareTo-LRDsOJo", "(DD)I", "div", "scale", "div-impl", "(DD)D", "(DI)D", "div-LRDsOJo", "equals", "", "", "hashCode", "isFinite", "isFinite-impl", "(D)Z", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "plus", "plus-LRDsOJo", "precision", "precision-impl", "times", "times-impl", "toComponents", "T", "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(DLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(DLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(DLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "", "(DLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "toDouble-impl", "(DLjava/util/concurrent/TimeUnit;)D", "toInt", "toInt-impl", "(DLjava/util/concurrent/TimeUnit;)I", "toIsoString", "", "toIsoString-impl", "(D)Ljava/lang/String;", "toLong", "toLong-impl", "(DLjava/util/concurrent/TimeUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "(D)J", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(DLjava/util/concurrent/TimeUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-impl", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class Duration implements Comparable<Duration> {
    private final double value;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final double ZERO = m961constructorimpl(0.0d);
    private static final double INFINITE = m961constructorimpl(DoubleCompanionObject.INSTANCE.getPOSITIVE_INFINITY());

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Duration m959boximpl(double d) {
        return new Duration(d);
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m965equalsimpl(double d, Object obj) {
        return (obj instanceof Duration) && Double.compare(d, ((Duration) obj).getValue()) == 0;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m966equalsimpl0(double d, double d2) {
        return Double.compare(d, d2) == 0;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m979hashCodeimpl(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    public static /* synthetic */ void hoursComponent$annotations() {
    }

    public static /* synthetic */ void minutesComponent$annotations() {
    }

    public static /* synthetic */ void nanosecondsComponent$annotations() {
    }

    public static /* synthetic */ void secondsComponent$annotations() {
    }

    /* renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public int m1003compareToLRDsOJo(double d) {
        return m960compareToLRDsOJo(this.value, d);
    }

    public boolean equals(Object other) {
        return m965equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m979hashCodeimpl(this.value);
    }

    public String toString() {
        return m999toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ double getValue() {
        return this.value;
    }

    private /* synthetic */ Duration(double value) {
        this.value = value;
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static double m961constructorimpl(double value) {
        return value;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Duration duration) {
        return m1003compareToLRDsOJo(duration.getValue());
    }

    /* compiled from: Duration.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\n\u0010\u0010\u001a\u00060\u000ej\u0002`\u000fR\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE", "()D", "D", "ZERO", "getZERO", "convert", "", "value", "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final double getZERO() {
            return Duration.ZERO;
        }

        public final double getINFINITE() {
            return Duration.INFINITE;
        }

        public final double convert(double value, TimeUnit sourceUnit, TimeUnit targetUnit) {
            Intrinsics.checkParameterIsNotNull(sourceUnit, "sourceUnit");
            Intrinsics.checkParameterIsNotNull(targetUnit, "targetUnit");
            return DurationUnitKt.convertDurationUnit(value, sourceUnit, targetUnit);
        }
    }

    /* renamed from: unaryMinus-impl, reason: not valid java name */
    public static final double m1002unaryMinusimpl(double $this) {
        return m961constructorimpl(-$this);
    }

    /* renamed from: plus-LRDsOJo, reason: not valid java name */
    public static final double m985plusLRDsOJo(double $this, double other) {
        return m961constructorimpl($this + other);
    }

    /* renamed from: minus-LRDsOJo, reason: not valid java name */
    public static final double m984minusLRDsOJo(double $this, double other) {
        return m961constructorimpl($this - other);
    }

    /* renamed from: times-impl, reason: not valid java name */
    public static final double m988timesimpl(double $this, int scale) {
        double d = scale;
        Double.isNaN(d);
        return m961constructorimpl(d * $this);
    }

    /* renamed from: times-impl, reason: not valid java name */
    public static final double m987timesimpl(double $this, double scale) {
        return m961constructorimpl($this * scale);
    }

    /* renamed from: div-impl, reason: not valid java name */
    public static final double m964divimpl(double $this, int scale) {
        double d = scale;
        Double.isNaN(d);
        return m961constructorimpl($this / d);
    }

    /* renamed from: div-impl, reason: not valid java name */
    public static final double m963divimpl(double $this, double scale) {
        return m961constructorimpl($this / scale);
    }

    /* renamed from: div-LRDsOJo, reason: not valid java name */
    public static final double m962divLRDsOJo(double $this, double other) {
        return $this / other;
    }

    /* renamed from: isNegative-impl, reason: not valid java name */
    public static final boolean m982isNegativeimpl(double $this) {
        return $this < ((double) 0);
    }

    /* renamed from: isPositive-impl, reason: not valid java name */
    public static final boolean m983isPositiveimpl(double $this) {
        return $this > ((double) 0);
    }

    /* renamed from: isInfinite-impl, reason: not valid java name */
    public static final boolean m981isInfiniteimpl(double $this) {
        return Double.isInfinite($this);
    }

    /* renamed from: isFinite-impl, reason: not valid java name */
    public static final boolean m980isFiniteimpl(double $this) {
        return (Double.isInfinite($this) || Double.isNaN($this)) ? false : true;
    }

    /* renamed from: getAbsoluteValue-impl, reason: not valid java name */
    public static final double m967getAbsoluteValueimpl(double $this) {
        return m982isNegativeimpl($this) ? m1002unaryMinusimpl($this) : $this;
    }

    /* renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public static int m960compareToLRDsOJo(double $this, double other) {
        return Double.compare($this, other);
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m992toComponentsimpl(double $this, Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Integer.valueOf((int) m969getInDaysimpl($this)), Integer.valueOf(m968getHoursComponentimpl($this)), Integer.valueOf(m976getMinutesComponentimpl($this)), Integer.valueOf(m978getSecondsComponentimpl($this)), Integer.valueOf(m977getNanosecondsComponentimpl($this)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m991toComponentsimpl(double $this, Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Integer.valueOf((int) m970getInHoursimpl($this)), Integer.valueOf(m976getMinutesComponentimpl($this)), Integer.valueOf(m978getSecondsComponentimpl($this)), Integer.valueOf(m977getNanosecondsComponentimpl($this)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m990toComponentsimpl(double $this, Function3<? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Integer.valueOf((int) m973getInMinutesimpl($this)), Integer.valueOf(m978getSecondsComponentimpl($this)), Integer.valueOf(m977getNanosecondsComponentimpl($this)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m989toComponentsimpl(double $this, Function2<? super Long, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Long.valueOf((long) m975getInSecondsimpl($this)), Integer.valueOf(m977getNanosecondsComponentimpl($this)));
    }

    /* renamed from: getHoursComponent-impl, reason: not valid java name */
    public static final int m968getHoursComponentimpl(double $this) {
        double m970getInHoursimpl = m970getInHoursimpl($this);
        double d = 24;
        Double.isNaN(d);
        return (int) (m970getInHoursimpl % d);
    }

    /* renamed from: getMinutesComponent-impl, reason: not valid java name */
    public static final int m976getMinutesComponentimpl(double $this) {
        double m973getInMinutesimpl = m973getInMinutesimpl($this);
        double d = 60;
        Double.isNaN(d);
        return (int) (m973getInMinutesimpl % d);
    }

    /* renamed from: getSecondsComponent-impl, reason: not valid java name */
    public static final int m978getSecondsComponentimpl(double $this) {
        double m975getInSecondsimpl = m975getInSecondsimpl($this);
        double d = 60;
        Double.isNaN(d);
        return (int) (m975getInSecondsimpl % d);
    }

    /* renamed from: getNanosecondsComponent-impl, reason: not valid java name */
    public static final int m977getNanosecondsComponentimpl(double $this) {
        return (int) (m974getInNanosecondsimpl($this) % 1.0E9d);
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    public static final double m993toDoubleimpl(double $this, TimeUnit unit) {
        TimeUnit storageUnit;
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        storageUnit = DurationKt.getStorageUnit();
        return DurationUnitKt.convertDurationUnit($this, storageUnit, unit);
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    public static final long m996toLongimpl(double $this, TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return (long) m993toDoubleimpl($this, unit);
    }

    /* renamed from: toInt-impl, reason: not valid java name */
    public static final int m994toIntimpl(double $this, TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return (int) m993toDoubleimpl($this, unit);
    }

    /* renamed from: getInDays-impl, reason: not valid java name */
    public static final double m969getInDaysimpl(double $this) {
        return m993toDoubleimpl($this, TimeUnit.DAYS);
    }

    /* renamed from: getInHours-impl, reason: not valid java name */
    public static final double m970getInHoursimpl(double $this) {
        return m993toDoubleimpl($this, TimeUnit.HOURS);
    }

    /* renamed from: getInMinutes-impl, reason: not valid java name */
    public static final double m973getInMinutesimpl(double $this) {
        return m993toDoubleimpl($this, TimeUnit.MINUTES);
    }

    /* renamed from: getInSeconds-impl, reason: not valid java name */
    public static final double m975getInSecondsimpl(double $this) {
        return m993toDoubleimpl($this, TimeUnit.SECONDS);
    }

    /* renamed from: getInMilliseconds-impl, reason: not valid java name */
    public static final double m972getInMillisecondsimpl(double $this) {
        return m993toDoubleimpl($this, TimeUnit.MILLISECONDS);
    }

    /* renamed from: getInMicroseconds-impl, reason: not valid java name */
    public static final double m971getInMicrosecondsimpl(double $this) {
        return m993toDoubleimpl($this, TimeUnit.MICROSECONDS);
    }

    /* renamed from: getInNanoseconds-impl, reason: not valid java name */
    public static final double m974getInNanosecondsimpl(double $this) {
        return m993toDoubleimpl($this, TimeUnit.NANOSECONDS);
    }

    /* renamed from: toLongNanoseconds-impl, reason: not valid java name */
    public static final long m998toLongNanosecondsimpl(double $this) {
        return m996toLongimpl($this, TimeUnit.NANOSECONDS);
    }

    /* renamed from: toLongMilliseconds-impl, reason: not valid java name */
    public static final long m997toLongMillisecondsimpl(double $this) {
        return m996toLongimpl($this, TimeUnit.MILLISECONDS);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m999toStringimpl(double $this) {
        TimeUnit unit;
        String formatUpToDecimals;
        if (m981isInfiniteimpl($this)) {
            return String.valueOf($this);
        }
        if ($this == 0.0d) {
            return "0s";
        }
        double absNs = m974getInNanosecondsimpl(m967getAbsoluteValueimpl($this));
        boolean scientific = false;
        int maxDecimals = 0;
        if (absNs < 1.0E-6d) {
            unit = TimeUnit.SECONDS;
            scientific = true;
        } else if (absNs < 1) {
            unit = TimeUnit.NANOSECONDS;
            maxDecimals = 7;
        } else if (absNs < 1000.0d) {
            unit = TimeUnit.NANOSECONDS;
        } else if (absNs < 1000000.0d) {
            unit = TimeUnit.MICROSECONDS;
        } else if (absNs < 1.0E9d) {
            unit = TimeUnit.MILLISECONDS;
        } else if (absNs < 1.0E12d) {
            unit = TimeUnit.SECONDS;
        } else if (absNs < 6.0E13d) {
            unit = TimeUnit.MINUTES;
        } else if (absNs < 3.6E15d) {
            unit = TimeUnit.HOURS;
        } else if (absNs < 8.64E20d) {
            unit = TimeUnit.DAYS;
        } else {
            unit = TimeUnit.DAYS;
            scientific = true;
        }
        double value = m993toDoubleimpl($this, unit);
        StringBuilder sb = new StringBuilder();
        if (scientific) {
            formatUpToDecimals = FormatToDecimalsKt.formatScientific(value);
        } else {
            formatUpToDecimals = maxDecimals > 0 ? FormatToDecimalsKt.formatUpToDecimals(value, maxDecimals) : FormatToDecimalsKt.formatToExactDecimals(value, m986precisionimpl($this, Math.abs(value)));
        }
        sb.append(formatUpToDecimals);
        sb.append(DurationUnitKt.shortName(unit));
        return sb.toString();
    }

    /* renamed from: precision-impl, reason: not valid java name */
    private static final int m986precisionimpl(double $this, double value) {
        if (value < 1) {
            return 3;
        }
        if (value < 10) {
            return 2;
        }
        if (value < 100) {
            return 1;
        }
        return 0;
    }

    /* renamed from: toString-impl$default, reason: not valid java name */
    public static /* synthetic */ String m1001toStringimpl$default(double d, TimeUnit timeUnit, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return m1000toStringimpl(d, timeUnit, i);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static final String m1000toStringimpl(double $this, TimeUnit unit, int decimals) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        if (!(decimals >= 0)) {
            throw new IllegalArgumentException(("decimals must be not negative, but was " + decimals).toString());
        }
        if (m981isInfiniteimpl($this)) {
            return String.valueOf($this);
        }
        double number = m993toDoubleimpl($this, unit);
        StringBuilder sb = new StringBuilder();
        sb.append(Math.abs(number) < 1.0E14d ? FormatToDecimalsKt.formatToExactDecimals(number, RangesKt.coerceAtMost(decimals, 12)) : FormatToDecimalsKt.formatScientific(number));
        sb.append(DurationUnitKt.shortName(unit));
        return sb.toString();
    }

    /* renamed from: toIsoString-impl, reason: not valid java name */
    public static final String m995toIsoStringimpl(double $this) {
        StringBuilder $this$buildString = new StringBuilder();
        if (m982isNegativeimpl($this)) {
            $this$buildString.append('-');
        }
        $this$buildString.append("PT");
        double $this$iv = m967getAbsoluteValueimpl($this);
        int hours = (int) m970getInHoursimpl($this$iv);
        int minutes = m976getMinutesComponentimpl($this$iv);
        int seconds = m978getSecondsComponentimpl($this$iv);
        int nanoseconds = m977getNanosecondsComponentimpl($this$iv);
        boolean hasMinutes = true;
        boolean hasHours = hours != 0;
        boolean hasSeconds = (seconds == 0 && nanoseconds == 0) ? false : true;
        if (minutes == 0 && (!hasSeconds || !hasHours)) {
            hasMinutes = false;
        }
        if (hasHours) {
            $this$buildString.append(hours);
            $this$buildString.append('H');
        }
        if (hasMinutes) {
            $this$buildString.append(minutes);
            $this$buildString.append('M');
        }
        if (hasSeconds || (!hasHours && !hasMinutes)) {
            $this$buildString.append(seconds);
            if (nanoseconds != 0) {
                $this$buildString.append('.');
                String nss = StringsKt.padStart(String.valueOf(nanoseconds), 9, '0');
                if (nanoseconds % 1000000 == 0) {
                    $this$buildString.append((CharSequence) nss, 0, 3);
                    Intrinsics.checkExpressionValueIsNotNull($this$buildString, "this.append(value, startIndex, endIndex)");
                } else if (nanoseconds % 1000 == 0) {
                    $this$buildString.append((CharSequence) nss, 0, 6);
                    Intrinsics.checkExpressionValueIsNotNull($this$buildString, "this.append(value, startIndex, endIndex)");
                } else {
                    $this$buildString.append(nss);
                }
            }
            $this$buildString.append('S');
        }
        String sb = $this$buildString.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }
}
