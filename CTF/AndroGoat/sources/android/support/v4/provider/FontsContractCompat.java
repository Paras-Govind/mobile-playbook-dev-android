package android.support.v4.provider;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.provider.SelfDestructiveThread;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;
import android.support.v4.util.SimpleArrayMap;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class FontsContractCompat {
    public static final String PARCEL_FONT_RESULTS = "font_results";
    public static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    public static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    private static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);
    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS);
    private static final Object sLock = new Object();
    private static final SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>>> sPendingReplies = new SimpleArrayMap<>();
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() { // from class: android.support.v4.provider.FontsContractCompat.5
        @Override // java.util.Comparator
        public int compare(byte[] l, byte[] r) {
            if (l.length != r.length) {
                return l.length - r.length;
            }
            for (int i = 0; i < l.length; i++) {
                if (l[i] != r[i]) {
                    return l[i] - r[i];
                }
            }
            return 0;
        }
    };

    /* loaded from: classes.dex */
    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    private FontsContractCompat() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Typeface getFontInternal(Context context, FontRequest request, int style) {
        try {
            FontFamilyResult result = fetchFonts(context, null, request);
            if (result.getStatusCode() != 0) {
                return null;
            }
            return TypefaceCompat.createFromFontInfo(context, null, result.getFonts(), style);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static Typeface getFontSync(final Context context, final FontRequest request, final TextView targetView, int strategy, int timeout, final int style) {
        final String id = request.getIdentifier() + "-" + style;
        Typeface cached = sTypefaceCache.get(id);
        if (cached != null) {
            return cached;
        }
        boolean isBlockingFetch = strategy == 0;
        if (isBlockingFetch && timeout == -1) {
            return getFontInternal(context, request, style);
        }
        Callable<Typeface> fetcher = new Callable<Typeface>() { // from class: android.support.v4.provider.FontsContractCompat.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Typeface call() throws Exception {
                Typeface typeface = FontsContractCompat.getFontInternal(context, request, style);
                if (typeface != null) {
                    FontsContractCompat.sTypefaceCache.put(id, typeface);
                }
                return typeface;
            }
        };
        if (isBlockingFetch) {
            try {
                return (Typeface) sBackgroundThread.postAndWait(fetcher, timeout);
            } catch (InterruptedException e) {
                return null;
            }
        }
        final WeakReference<TextView> textViewWeak = new WeakReference<>(targetView);
        SelfDestructiveThread.ReplyCallback<Typeface> reply = new SelfDestructiveThread.ReplyCallback<Typeface>() { // from class: android.support.v4.provider.FontsContractCompat.2
            @Override // android.support.v4.provider.SelfDestructiveThread.ReplyCallback
            public void onReply(Typeface typeface) {
                TextView textView = (TextView) textViewWeak.get();
                if (textView != null) {
                    targetView.setTypeface(typeface, style);
                }
            }
        };
        synchronized (sLock) {
            if (sPendingReplies.containsKey(id)) {
                sPendingReplies.get(id).add(reply);
                return null;
            }
            ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>> pendingReplies = new ArrayList<>();
            pendingReplies.add(reply);
            sPendingReplies.put(id, pendingReplies);
            sBackgroundThread.postAndReply(fetcher, new SelfDestructiveThread.ReplyCallback<Typeface>() { // from class: android.support.v4.provider.FontsContractCompat.3
                @Override // android.support.v4.provider.SelfDestructiveThread.ReplyCallback
                public void onReply(Typeface typeface) {
                    synchronized (FontsContractCompat.sLock) {
                        try {
                            try {
                                ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>> replies = (ArrayList) FontsContractCompat.sPendingReplies.get(id);
                                FontsContractCompat.sPendingReplies.remove(id);
                                for (int i = 0; i < replies.size(); i++) {
                                    replies.get(i).onReply(typeface);
                                }
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
            });
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        public FontInfo(Uri uri, int ttcIndex, int weight, boolean italic, int resultCode) {
            this.mUri = (Uri) Preconditions.checkNotNull(uri);
            this.mTtcIndex = ttcIndex;
            this.mWeight = weight;
            this.mItalic = italic;
            this.mResultCode = resultCode;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public int getResultCode() {
            return this.mResultCode;
        }
    }

    /* loaded from: classes.dex */
    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        @interface FontResultStatus {
        }

        public FontFamilyResult(int statusCode, FontInfo[] fonts) {
            this.mStatusCode = statusCode;
            this.mFonts = fonts;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }
    }

    /* loaded from: classes.dex */
    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        @interface FontRequestFailReason {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }

        public void onTypefaceRequestFailed(int reason) {
        }
    }

    public static void requestFont(final Context context, final FontRequest request, final FontRequestCallback callback, Handler handler) {
        final Handler callerThreadHandler = new Handler();
        handler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    FontFamilyResult result = FontsContractCompat.fetchFonts(context, null, request);
                    if (result.getStatusCode() != 0) {
                        int statusCode = result.getStatusCode();
                        if (statusCode == 1) {
                            callerThreadHandler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    callback.onTypefaceRequestFailed(-2);
                                }
                            });
                            return;
                        } else if (statusCode == 2) {
                            callerThreadHandler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    callback.onTypefaceRequestFailed(-3);
                                }
                            });
                            return;
                        } else {
                            callerThreadHandler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    callback.onTypefaceRequestFailed(-3);
                                }
                            });
                            return;
                        }
                    }
                    FontInfo[] fonts = result.getFonts();
                    if (fonts == null || fonts.length == 0) {
                        callerThreadHandler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.5
                            @Override // java.lang.Runnable
                            public void run() {
                                callback.onTypefaceRequestFailed(1);
                            }
                        });
                        return;
                    }
                    for (FontInfo font : fonts) {
                        if (font.getResultCode() != 0) {
                            final int resultCode = font.getResultCode();
                            if (resultCode < 0) {
                                callerThreadHandler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.6
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        callback.onTypefaceRequestFailed(-3);
                                    }
                                });
                                return;
                            } else {
                                callerThreadHandler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.7
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        callback.onTypefaceRequestFailed(resultCode);
                                    }
                                });
                                return;
                            }
                        }
                    }
                    final Typeface typeface = FontsContractCompat.buildTypeface(context, null, fonts);
                    if (typeface == null) {
                        callerThreadHandler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.8
                            @Override // java.lang.Runnable
                            public void run() {
                                callback.onTypefaceRequestFailed(-3);
                            }
                        });
                    } else {
                        callerThreadHandler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.9
                            @Override // java.lang.Runnable
                            public void run() {
                                callback.onTypefaceRetrieved(typeface);
                            }
                        });
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    callerThreadHandler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            callback.onTypefaceRequestFailed(-1);
                        }
                    });
                }
            }
        });
    }

    public static Typeface buildTypeface(Context context, CancellationSignal cancellationSignal, FontInfo[] fonts) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fonts, 0);
    }

    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fonts, CancellationSignal cancellationSignal) {
        HashMap<Uri, ByteBuffer> out = new HashMap<>();
        for (FontInfo font : fonts) {
            if (font.getResultCode() == 0) {
                Uri uri = font.getUri();
                if (!out.containsKey(uri)) {
                    ByteBuffer buffer = TypefaceCompatUtil.mmap(context, cancellationSignal, uri);
                    out.put(uri, buffer);
                }
            }
        }
        return Collections.unmodifiableMap(out);
    }

    public static FontFamilyResult fetchFonts(Context context, CancellationSignal cancellationSignal, FontRequest request) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfo = getProvider(context.getPackageManager(), request, context.getResources());
        if (providerInfo == null) {
            return new FontFamilyResult(1, null);
        }
        FontInfo[] fonts = getFontFromProvider(context, request, providerInfo.authority, cancellationSignal);
        return new FontFamilyResult(0, fonts);
    }

    public static ProviderInfo getProvider(PackageManager packageManager, FontRequest request, Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = request.getProviderAuthority();
        ProviderInfo info = packageManager.resolveContentProvider(providerAuthority, 0);
        if (info == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        }
        if (!info.packageName.equals(request.getProviderPackage())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + request.getProviderPackage());
        }
        PackageInfo packageInfo = packageManager.getPackageInfo(info.packageName, 64);
        List<byte[]> signatures = convertToByteArrayList(packageInfo.signatures);
        Collections.sort(signatures, sByteArrayComparator);
        List<List<byte[]>> requestCertificatesList = getCertificates(request, resources);
        for (int i = 0; i < requestCertificatesList.size(); i++) {
            List<byte[]> requestSignatures = new ArrayList<>(requestCertificatesList.get(i));
            Collections.sort(requestSignatures, sByteArrayComparator);
            if (equalsByteArrayList(signatures, requestSignatures)) {
                return info;
            }
        }
        return null;
    }

    private static List<List<byte[]>> getCertificates(FontRequest request, Resources resources) {
        if (request.getCertificates() != null) {
            return request.getCertificates();
        }
        int resourceId = request.getCertificatesArrayResId();
        return FontResourcesParserCompat.readCerts(resources, resourceId);
    }

    private static boolean equalsByteArrayList(List<byte[]> signatures, List<byte[]> requestSignatures) {
        if (signatures.size() != requestSignatures.size()) {
            return false;
        }
        for (int i = 0; i < signatures.size(); i++) {
            if (!Arrays.equals(signatures.get(i), requestSignatures.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatures) {
        List<byte[]> shas = new ArrayList<>();
        for (Signature signature : signatures) {
            shas.add(signature.toByteArray());
        }
        return shas;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0174  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static android.support.v4.provider.FontsContractCompat.FontInfo[] getFontFromProvider(android.content.Context r23, android.support.v4.provider.FontRequest r24, java.lang.String r25, android.os.CancellationSignal r26) {
        /*
            Method dump skipped, instructions count: 378
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.FontsContractCompat.getFontFromProvider(android.content.Context, android.support.v4.provider.FontRequest, java.lang.String, android.os.CancellationSignal):android.support.v4.provider.FontsContractCompat$FontInfo[]");
    }
}
