package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StringBuilderJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a-\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a/\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a\u0012\u0010\t\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b\u001a\u001d\u0010\t\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\u0003\u001a\u00020\fH\u0087\b\u001a\u001f\u0010\t\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\u0087\b\u001a\u0012\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002\u001a\u001f\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\rH\u0087\b\u001a\u001f\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u000eH\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u000fH\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0010H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\fH\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001f\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0012H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0013H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0014H\u0087\b\u001a\u001f\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0015H\u0087\b\u001a%\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000e\u0010\u0003\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\b\u001a\u0014\u0010\u0016\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0007\u001a\u001d\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u0006H\u0087\b\u001a%\u0010\u0019\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a5\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a7\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a!\u0010\u001b\u001a\u00020\u001c*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\fH\u0087\n\u001a-\u0010\u001d\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0015H\u0087\b\u001a7\u0010\u001e\u001a\u00020\u001c*\u00060\u0001j\u0002`\u00022\u0006\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u00062\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\b¨\u0006!"}, d2 = {"appendRange", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "value", "", "startIndex", "", "endIndex", "", "appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "", "Ljava/lang/StringBuffer;", "", "", "", "", "", "", "", "", "clear", "deleteAt", "index", "deleteRange", "insertRange", "set", "", "setRange", "toCharArray", "destination", "destinationOffset", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
public class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
    public static final StringBuilder clear(StringBuilder clear) {
        Intrinsics.checkParameterIsNotNull(clear, "$this$clear");
        clear.setLength(0);
        return clear;
    }

    private static final void set(StringBuilder set, int index, char value) {
        Intrinsics.checkParameterIsNotNull(set, "$this$set");
        set.setCharAt(index, value);
    }

    private static final StringBuilder setRange(StringBuilder $this$setRange, int startIndex, int endIndex, String value) {
        StringBuilder replace = $this$setRange.replace(startIndex, endIndex, value);
        Intrinsics.checkExpressionValueIsNotNull(replace, "this.replace(startIndex, endIndex, value)");
        return replace;
    }

    private static final StringBuilder deleteAt(StringBuilder $this$deleteAt, int index) {
        StringBuilder deleteCharAt = $this$deleteAt.deleteCharAt(index);
        Intrinsics.checkExpressionValueIsNotNull(deleteCharAt, "this.deleteCharAt(index)");
        return deleteCharAt;
    }

    private static final StringBuilder deleteRange(StringBuilder $this$deleteRange, int startIndex, int endIndex) {
        StringBuilder delete = $this$deleteRange.delete(startIndex, endIndex);
        Intrinsics.checkExpressionValueIsNotNull(delete, "this.delete(startIndex, endIndex)");
        return delete;
    }

    static /* synthetic */ void toCharArray$default(StringBuilder $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex, int i, Object obj) {
        if ((i & 2) != 0) {
            destinationOffset = 0;
        }
        if ((i & 4) != 0) {
            startIndex = 0;
        }
        if ((i & 8) != 0) {
            endIndex = $this$toCharArray.length();
        }
        $this$toCharArray.getChars(startIndex, endIndex, destination, destinationOffset);
    }

    private static final void toCharArray(StringBuilder $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex) {
        $this$toCharArray.getChars(startIndex, endIndex, destination, destinationOffset);
    }

    private static final StringBuilder appendRange(StringBuilder $this$appendRange, char[] value, int startIndex, int endIndex) {
        $this$appendRange.append(value, startIndex, endIndex - startIndex);
        Intrinsics.checkExpressionValueIsNotNull($this$appendRange, "this.append(value, start…x, endIndex - startIndex)");
        return $this$appendRange;
    }

    private static final StringBuilder appendRange(StringBuilder $this$appendRange, CharSequence value, int startIndex, int endIndex) {
        $this$appendRange.append(value, startIndex, endIndex);
        Intrinsics.checkExpressionValueIsNotNull($this$appendRange, "this.append(value, startIndex, endIndex)");
        return $this$appendRange;
    }

    private static final StringBuilder insertRange(StringBuilder $this$insertRange, int index, char[] value, int startIndex, int endIndex) {
        StringBuilder insert = $this$insertRange.insert(index, value, startIndex, endIndex - startIndex);
        Intrinsics.checkExpressionValueIsNotNull(insert, "this.insert(index, value…x, endIndex - startIndex)");
        return insert;
    }

    private static final StringBuilder insertRange(StringBuilder $this$insertRange, int index, CharSequence value, int startIndex, int endIndex) {
        StringBuilder insert = $this$insertRange.insert(index, value, startIndex, endIndex);
        Intrinsics.checkExpressionValueIsNotNull(insert, "this.insert(index, value, startIndex, endIndex)");
        return insert;
    }

    public static final Appendable appendln(Appendable appendln) {
        Intrinsics.checkParameterIsNotNull(appendln, "$this$appendln");
        Appendable append = appendln.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(SystemProperties.LINE_SEPARATOR)");
        return append;
    }

    private static final Appendable appendln(Appendable $this$appendln, CharSequence value) {
        Appendable append = $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(value)");
        return StringsKt.appendln(append);
    }

    private static final Appendable appendln(Appendable $this$appendln, char value) {
        Appendable append = $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(value)");
        return StringsKt.appendln(append);
    }

    public static final StringBuilder appendln(StringBuilder appendln) {
        Intrinsics.checkParameterIsNotNull(appendln, "$this$appendln");
        appendln.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkExpressionValueIsNotNull(appendln, "append(SystemProperties.LINE_SEPARATOR)");
        return appendln;
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, StringBuffer value) {
        $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, CharSequence value) {
        $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, String value) {
        $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, Object value) {
        $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, StringBuilder value) {
        $this$appendln.append((CharSequence) value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, char[] value) {
        $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, char value) {
        $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, boolean value) {
        $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, int value) {
        $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, short value) {
        $this$appendln.append((int) value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value.toInt())");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, byte value) {
        $this$appendln.append((int) value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value.toInt())");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, long value) {
        $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, float value) {
        $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    private static final StringBuilder appendln(StringBuilder $this$appendln, double value) {
        $this$appendln.append(value);
        Intrinsics.checkExpressionValueIsNotNull($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }
}
