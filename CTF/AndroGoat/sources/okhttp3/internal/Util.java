package okhttp3.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;

/* loaded from: classes.dex */
public final class Util {
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final RequestBody EMPTY_REQUEST;
    public static final ResponseBody EMPTY_RESPONSE;
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final ByteString UTF_8_BOM = ByteString.decodeHex("efbbbf");
    private static final ByteString UTF_16_BE_BOM = ByteString.decodeHex("feff");
    private static final ByteString UTF_16_LE_BOM = ByteString.decodeHex("fffe");
    private static final ByteString UTF_32_BE_BOM = ByteString.decodeHex("0000ffff");
    private static final ByteString UTF_32_LE_BOM = ByteString.decodeHex("ffff0000");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset UTF_16_BE = Charset.forName("UTF-16BE");
    private static final Charset UTF_16_LE = Charset.forName("UTF-16LE");
    private static final Charset UTF_32_BE = Charset.forName("UTF-32BE");
    private static final Charset UTF_32_LE = Charset.forName("UTF-32LE");
    public static final TimeZone UTC = TimeZone.getTimeZone("GMT");
    public static final Comparator<String> NATURAL_ORDER = new Comparator<String>() { // from class: okhttp3.internal.Util.1
        @Override // java.util.Comparator
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    };
    private static final Pattern VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    static {
        byte[] bArr = new byte[0];
        EMPTY_BYTE_ARRAY = bArr;
        EMPTY_RESPONSE = ResponseBody.create((MediaType) null, bArr);
        EMPTY_REQUEST = RequestBody.create((MediaType) null, EMPTY_BYTE_ARRAY);
    }

    private Util() {
    }

    public static void checkOffsetAndCount(long arrayLength, long offset, long count) {
        if ((offset | count) < 0 || offset > arrayLength || arrayLength - offset < count) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }

    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e) {
                if (!isAndroidGetsocknameError(e)) {
                    throw e;
                }
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e2) {
            }
        }
    }

    public static void closeQuietly(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }

    public static boolean discard(Source source, int timeout, TimeUnit timeUnit) {
        try {
            return skipAll(source, timeout, timeUnit);
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean skipAll(Source source, int duration, TimeUnit timeUnit) throws IOException {
        long originalDuration;
        long now = System.nanoTime();
        if (source.timeout().hasDeadline()) {
            originalDuration = source.timeout().deadlineNanoTime() - now;
        } else {
            originalDuration = Long.MAX_VALUE;
        }
        source.timeout().deadlineNanoTime(Math.min(originalDuration, timeUnit.toNanos(duration)) + now);
        try {
            Buffer skipBuffer = new Buffer();
            while (source.read(skipBuffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
                skipBuffer.clear();
            }
            if (originalDuration == LongCompanionObject.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(now + originalDuration);
            }
            return true;
        } catch (InterruptedIOException e) {
            if (originalDuration == LongCompanionObject.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(now + originalDuration);
            }
            return false;
        } catch (Throwable th) {
            if (originalDuration == LongCompanionObject.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(now + originalDuration);
            }
            throw th;
        }
    }

    public static <T> List<T> immutableList(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static <T> List<T> immutableList(T... elements) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) elements.clone()));
    }

    public static ThreadFactory threadFactory(final String name, final boolean daemon) {
        return new ThreadFactory() { // from class: okhttp3.internal.Util.2
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread result = new Thread(runnable, name);
                result.setDaemon(daemon);
                return result;
            }
        };
    }

    public static String[] intersect(Comparator<? super String> comparator, String[] first, String[] second) {
        List<String> result = new ArrayList<>();
        for (String a : first) {
            int length = second.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    String b = second[i];
                    if (comparator.compare(a, b) != 0) {
                        i++;
                    } else {
                        result.add(a);
                        break;
                    }
                }
            }
        }
        return (String[]) result.toArray(new String[result.size()]);
    }

    public static boolean nonEmptyIntersection(Comparator<String> comparator, String[] first, String[] second) {
        if (first == null || second == null || first.length == 0 || second.length == 0) {
            return false;
        }
        for (String a : first) {
            for (String b : second) {
                if (comparator.compare(a, b) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String hostHeader(HttpUrl url, boolean includeDefaultPort) {
        String host;
        if (url.host().contains(":")) {
            host = "[" + url.host() + "]";
        } else {
            host = url.host();
        }
        if (includeDefaultPort || url.port() != HttpUrl.defaultPort(url.scheme())) {
            return host + ":" + url.port();
        }
        return host;
    }

    public static String toHumanReadableAscii(String s) {
        int i = 0;
        int length = s.length();
        while (i < length) {
            int c = s.codePointAt(i);
            if (c > 31 && c < 127) {
                i += Character.charCount(c);
            } else {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(s, 0, i);
                int j = i;
                while (j < length) {
                    int c2 = s.codePointAt(j);
                    buffer.writeUtf8CodePoint((c2 <= 31 || c2 >= 127) ? 63 : c2);
                    j += Character.charCount(c2);
                }
                return buffer.readUtf8();
            }
        }
        return s;
    }

    public static boolean isAndroidGetsocknameError(AssertionError e) {
        return (e.getCause() == null || e.getMessage() == null || !e.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static int indexOf(Comparator<String> comparator, String[] array, String value) {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            if (comparator.compare(array[i], value) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static String[] concat(String[] array, String value) {
        String[] result = new String[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[result.length - 1] = value;
        return result;
    }

    public static int skipLeadingAsciiWhitespace(String input, int pos, int limit) {
        for (int i = pos; i < limit; i++) {
            char charAt = input.charAt(i);
            if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                return i;
            }
        }
        return limit;
    }

    public static int skipTrailingAsciiWhitespace(String input, int pos, int limit) {
        for (int i = limit - 1; i >= pos; i--) {
            char charAt = input.charAt(i);
            if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                return i + 1;
            }
        }
        return pos;
    }

    public static String trimSubstring(String string, int pos, int limit) {
        int start = skipLeadingAsciiWhitespace(string, pos, limit);
        int end = skipTrailingAsciiWhitespace(string, start, limit);
        return string.substring(start, end);
    }

    public static int delimiterOffset(String input, int pos, int limit, String delimiters) {
        for (int i = pos; i < limit; i++) {
            if (delimiters.indexOf(input.charAt(i)) != -1) {
                return i;
            }
        }
        return limit;
    }

    public static int delimiterOffset(String input, int pos, int limit, char delimiter) {
        for (int i = pos; i < limit; i++) {
            if (input.charAt(i) == delimiter) {
                return i;
            }
        }
        return limit;
    }

    public static String domainToAscii(String input) {
        try {
            String result = IDN.toASCII(input).toLowerCase(Locale.US);
            if (result.isEmpty()) {
                return null;
            }
            if (containsInvalidHostnameAsciiCodes(result)) {
                return null;
            }
            return result;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static boolean containsInvalidHostnameAsciiCodes(String hostnameAscii) {
        for (int i = 0; i < hostnameAscii.length(); i++) {
            char c = hostnameAscii.charAt(i);
            if (c <= 31 || c >= 127 || " #%/:?@[\\]".indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    public static int indexOfControlOrNonAscii(String input) {
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (c <= 31 || c >= 127) {
                return i;
            }
        }
        return -1;
    }

    public static boolean verifyAsIpAddress(String host) {
        return VERIFY_AS_IP_ADDRESS.matcher(host).matches();
    }

    public static String format(String format, Object... args) {
        return String.format(Locale.US, format, args);
    }

    public static Charset bomAwareCharset(BufferedSource source, Charset charset) throws IOException {
        if (source.rangeEquals(0L, UTF_8_BOM)) {
            source.skip(UTF_8_BOM.size());
            return UTF_8;
        }
        if (source.rangeEquals(0L, UTF_16_BE_BOM)) {
            source.skip(UTF_16_BE_BOM.size());
            return UTF_16_BE;
        }
        if (source.rangeEquals(0L, UTF_16_LE_BOM)) {
            source.skip(UTF_16_LE_BOM.size());
            return UTF_16_LE;
        }
        if (source.rangeEquals(0L, UTF_32_BE_BOM)) {
            source.skip(UTF_32_BE_BOM.size());
            return UTF_32_BE;
        }
        if (source.rangeEquals(0L, UTF_32_LE_BOM)) {
            source.skip(UTF_32_LE_BOM.size());
            return UTF_32_LE;
        }
        return charset;
    }
}
