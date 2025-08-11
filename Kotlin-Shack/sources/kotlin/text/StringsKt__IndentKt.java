package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: Indent.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0002\b\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002¢\u0006\u0002\b\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001aJ\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\u0082\b¢\u0006\u0002\b\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u001a\u001e\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u001a\n\u0010\u0013\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002¨\u0006\u0015"}, d2 = {"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"}, k = 5, mv = {1, 1, 9}, xi = 1, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
class StringsKt__IndentKt {
    @NotNull
    public static /* bridge */ /* synthetic */ String trimMargin$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "|";
        }
        return StringsKt.trimMargin(str, str2);
    }

    @NotNull
    public static final String trimMargin(@NotNull String receiver, @NotNull String marginPrefix) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(marginPrefix, "marginPrefix");
        return StringsKt.replaceIndentByMargin(receiver, "", marginPrefix);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceIndentByMargin$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        if ((i & 2) != 0) {
            str3 = "|";
        }
        return StringsKt.replaceIndentByMargin(str, str2, str3);
    }

    @NotNull
    public static final String replaceIndentByMargin(@NotNull String receiver, @NotNull String newIndent, @NotNull String marginPrefix) {
        Iterator it;
        Iterable $receiver$iv$iv$iv$iv;
        Collection destination$iv$iv$iv;
        String substring;
        String invoke;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(newIndent, "newIndent");
        Intrinsics.checkParameterIsNotNull(marginPrefix, "marginPrefix");
        if (!(!StringsKt.isBlank(marginPrefix))) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
        }
        List lines = StringsKt.lines(receiver);
        int resultSizeEstimate$iv = receiver.length() + (newIndent.length() * lines.size());
        Function1 indentAddFunction$iv = getIndentFunction$StringsKt__IndentKt(newIndent);
        int lastIndex$iv = CollectionsKt.getLastIndex(lines);
        List $receiver$iv$iv = lines;
        Collection destination$iv$iv$iv2 = new ArrayList();
        Iterable $receiver$iv$iv$iv$iv2 = $receiver$iv$iv;
        int index$iv$iv$iv$iv = 0;
        Iterator it2 = $receiver$iv$iv$iv$iv2.iterator();
        int $i$a$1$forEachIndexed = 0;
        int $i$a$1$mapIndexedNotNull = 0;
        while (it2.hasNext()) {
            Object item$iv$iv$iv$iv = it2.next();
            int index$iv$iv$iv$iv2 = index$iv$iv$iv$iv + 1;
            int index$iv$iv$iv = index$iv$iv$iv$iv;
            int $i$a$1$forEachIndexed2 = $i$a$1$forEachIndexed;
            String value$iv = (String) item$iv$iv$iv$iv;
            int index$iv = index$iv$iv$iv;
            int $i$a$1$mapIndexedNotNull2 = $i$a$1$mapIndexedNotNull;
            if ((index$iv == 0 || index$iv == lastIndex$iv) && StringsKt.isBlank(value$iv)) {
                it = it2;
                $receiver$iv$iv$iv$iv = $receiver$iv$iv$iv$iv2;
                destination$iv$iv$iv = destination$iv$iv$iv2;
                value$iv = null;
            } else {
                String $receiver$iv = value$iv;
                int length = $receiver$iv.length();
                int index$iv2 = 0;
                while (true) {
                    int index$iv3 = index$iv;
                    if (index$iv2 >= length) {
                        index$iv2 = -1;
                        break;
                    }
                    char it3 = $receiver$iv.charAt(index$iv2);
                    if (!CharsKt.isWhitespace(it3)) {
                        break;
                    }
                    index$iv2++;
                    index$iv = index$iv3;
                }
                if (index$iv2 == -1) {
                    it = it2;
                    $receiver$iv$iv$iv$iv = $receiver$iv$iv$iv$iv2;
                    destination$iv$iv$iv = destination$iv$iv$iv2;
                } else {
                    it = it2;
                    $receiver$iv$iv$iv$iv = $receiver$iv$iv$iv$iv2;
                    destination$iv$iv$iv = destination$iv$iv$iv2;
                    if (StringsKt.startsWith$default(value$iv, marginPrefix, index$iv2, false, 4, (Object) null)) {
                        int length2 = marginPrefix.length() + index$iv2;
                        if (value$iv == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        substring = value$iv.substring(length2);
                        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                        if (substring != null && (invoke = indentAddFunction$iv.invoke(substring)) != null) {
                            value$iv = invoke;
                        }
                    }
                }
                substring = null;
                if (substring != null) {
                    value$iv = invoke;
                }
            }
            if (value$iv != null) {
                destination$iv$iv$iv.add(value$iv);
            }
            destination$iv$iv$iv2 = destination$iv$iv$iv;
            $receiver$iv$iv$iv$iv2 = $receiver$iv$iv$iv$iv;
            index$iv$iv$iv$iv = index$iv$iv$iv$iv2;
            $i$a$1$forEachIndexed = $i$a$1$forEachIndexed2;
            $i$a$1$mapIndexedNotNull = $i$a$1$mapIndexedNotNull2;
            it2 = it;
        }
        String sb = ((StringBuilder) CollectionsKt.joinTo$default((List) destination$iv$iv$iv2, new StringBuilder(resultSizeEstimate$iv), "\n", null, null, 0, null, null, 124, null)).toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb;
    }

    @NotNull
    public static final String trimIndent(@NotNull String receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.replaceIndent(receiver, "");
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceIndent$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        return StringsKt.replaceIndent(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0114 A[SYNTHETIC] */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String replaceIndent(@org.jetbrains.annotations.NotNull java.lang.String r28, @org.jetbrains.annotations.NotNull java.lang.String r29) {
        /*
            Method dump skipped, instructions count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__IndentKt.replaceIndent(java.lang.String, java.lang.String):java.lang.String");
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String prependIndent$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "    ";
        }
        return StringsKt.prependIndent(str, str2);
    }

    @NotNull
    public static final String prependIndent(@NotNull String receiver, @NotNull final String indent) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(indent, "indent");
        return SequencesKt.joinToString$default(SequencesKt.map(StringsKt.lineSequence(receiver), new Function1<String, String>() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final String invoke(@NotNull String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (StringsKt.isBlank(it)) {
                    return it.length() < indent.length() ? indent : it;
                }
                return indent + it;
            }
        }), "\n", null, null, 0, null, null, 62, null);
    }

    private static final int indentWidth$StringsKt__IndentKt(@NotNull String $receiver) {
        String $receiver$iv = $receiver;
        int length = $receiver$iv.length();
        int index$iv = 0;
        while (true) {
            if (index$iv >= length) {
                index$iv = -1;
                break;
            }
            if (!CharsKt.isWhitespace($receiver$iv.charAt(index$iv))) {
                break;
            }
            index$iv++;
        }
        int it = index$iv;
        return it == -1 ? $receiver.length() : it;
    }

    private static final Function1<String, String> getIndentFunction$StringsKt__IndentKt(final String indent) {
        return indent.length() == 0 ? new Function1<String, String>() { // from class: kotlin.text.StringsKt__IndentKt$getIndentFunction$1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final String invoke(@NotNull String line) {
                Intrinsics.checkParameterIsNotNull(line, "line");
                return line;
            }
        } : new Function1<String, String>() { // from class: kotlin.text.StringsKt__IndentKt$getIndentFunction$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final String invoke(@NotNull String line) {
                Intrinsics.checkParameterIsNotNull(line, "line");
                return indent + line;
            }
        };
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0067, code lost:
    
        if (r0 != null) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0075 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.lang.String reindent$StringsKt__IndentKt(@org.jetbrains.annotations.NotNull java.util.List<java.lang.String> r23, int r24, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> r25, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> r26) {
        /*
            r0 = 0
            int r1 = kotlin.collections.CollectionsKt.getLastIndex(r23)
            r3 = r23
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            r4 = 0
            r5 = r4
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Collection r6 = (java.util.Collection) r6
            r7 = r3
            r8 = r4
            r9 = r7
            r10 = r4
            r11 = 0
            java.util.Iterator r12 = r9.iterator()
            r13 = r4
            r14 = r13
        L21:
            boolean r15 = r12.hasNext()
            if (r15 == 0) goto L7c
            java.lang.Object r15 = r12.next()
            int r16 = r11 + 1
            r17 = r15
            r18 = r0
            r0 = r17
            java.lang.String r0 = (java.lang.String) r0
            r19 = r11
            r2 = r19
            if (r2 == 0) goto L41
            if (r2 != r1) goto L3e
            goto L41
        L3e:
            r20 = r1
            goto L51
        L41:
            r20 = r1
            r1 = r0
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            if (r1 == 0) goto L51
            r1 = 0
            r0 = r1
            r1 = r25
            goto L6e
        L51:
            r1 = r26
            java.lang.Object r19 = r1.invoke(r0)
            r21 = r0
            r0 = r19
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L6a
            r1 = r25
            java.lang.Object r0 = r1.invoke(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L6c
            goto L6e
        L6a:
            r1 = r25
        L6c:
            r0 = r21
        L6e:
            if (r0 == 0) goto L75
            r2 = r14
            r6.add(r0)
        L75:
            r11 = r16
            r0 = r18
            r1 = r20
            goto L21
        L7c:
            r18 = r0
            r20 = r1
            r1 = r25
            r0 = r6
            java.util.List r0 = (java.util.List) r0
            r2 = r0
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r12 = r24
            r0.<init>(r12)
            r3 = r0
            java.lang.Appendable r3 = (java.lang.Appendable) r3
            java.lang.String r0 = "\n"
            r4 = r0
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 124(0x7c, float:1.74E-43)
            r11 = 0
            java.lang.Appendable r0 = kotlin.collections.CollectionsKt.joinTo$default(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            java.lang.StringBuilder r0 = (java.lang.StringBuilder) r0
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "mapIndexedNotNull { inde…\"\\n\")\n        .toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__IndentKt.reindent$StringsKt__IndentKt(java.util.List, int, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1):java.lang.String");
    }
}
