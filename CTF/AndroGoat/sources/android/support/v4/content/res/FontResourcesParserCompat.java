package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.compat.R;
import android.support.v4.provider.FontRequest;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Xml;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class FontResourcesParserCompat {
    private static final int DEFAULT_TIMEOUT_MILLIS = 500;
    public static final int FETCH_STRATEGY_ASYNC = 1;
    public static final int FETCH_STRATEGY_BLOCKING = 0;
    public static final int INFINITE_TIMEOUT_VALUE = -1;
    private static final int ITALIC = 1;
    private static final int NORMAL_WEIGHT = 400;

    /* loaded from: classes.dex */
    public interface FamilyResourceEntry {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FetchStrategy {
    }

    /* loaded from: classes.dex */
    public static final class ProviderResourceEntry implements FamilyResourceEntry {
        private final FontRequest mRequest;
        private final int mStrategy;
        private final int mTimeoutMs;

        public ProviderResourceEntry(FontRequest request, int strategy, int timeoutMs) {
            this.mRequest = request;
            this.mStrategy = strategy;
            this.mTimeoutMs = timeoutMs;
        }

        public FontRequest getRequest() {
            return this.mRequest;
        }

        public int getFetchStrategy() {
            return this.mStrategy;
        }

        public int getTimeout() {
            return this.mTimeoutMs;
        }
    }

    /* loaded from: classes.dex */
    public static final class FontFileResourceEntry {
        private final String mFileName;
        private boolean mItalic;
        private int mResourceId;
        private int mWeight;

        public FontFileResourceEntry(String fileName, int weight, boolean italic, int resourceId) {
            this.mFileName = fileName;
            this.mWeight = weight;
            this.mItalic = italic;
            this.mResourceId = resourceId;
        }

        public String getFileName() {
            return this.mFileName;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public int getResourceId() {
            return this.mResourceId;
        }
    }

    /* loaded from: classes.dex */
    public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {
        private final FontFileResourceEntry[] mEntries;

        public FontFamilyFilesResourceEntry(FontFileResourceEntry[] entries) {
            this.mEntries = entries;
        }

        public FontFileResourceEntry[] getEntries() {
            return this.mEntries;
        }
    }

    public static FamilyResourceEntry parse(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
        int type;
        do {
            type = parser.next();
            if (type == 2) {
                break;
            }
        } while (type != 1);
        if (type != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        return readFamilies(parser, resources);
    }

    private static FamilyResourceEntry readFamilies(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
        parser.require(2, null, "font-family");
        String tag = parser.getName();
        if (tag.equals("font-family")) {
            return readFamily(parser, resources);
        }
        skip(parser);
        return null;
    }

    private static FamilyResourceEntry readFamily(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
        AttributeSet attrs = Xml.asAttributeSet(parser);
        TypedArray array = resources.obtainAttributes(attrs, R.styleable.FontFamily);
        String authority = array.getString(R.styleable.FontFamily_fontProviderAuthority);
        String providerPackage = array.getString(R.styleable.FontFamily_fontProviderPackage);
        String query = array.getString(R.styleable.FontFamily_fontProviderQuery);
        int certsId = array.getResourceId(R.styleable.FontFamily_fontProviderCerts, 0);
        int strategy = array.getInteger(R.styleable.FontFamily_fontProviderFetchStrategy, 1);
        int timeoutMs = array.getInteger(R.styleable.FontFamily_fontProviderFetchTimeout, DEFAULT_TIMEOUT_MILLIS);
        array.recycle();
        if (authority != null && providerPackage != null && query != null) {
            while (parser.next() != 3) {
                skip(parser);
            }
            List<List<byte[]>> certs = readCerts(resources, certsId);
            return new ProviderResourceEntry(new FontRequest(authority, providerPackage, query, certs), strategy, timeoutMs);
        }
        List<FontFileResourceEntry> fonts = new ArrayList<>();
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                String tag = parser.getName();
                if (tag.equals("font")) {
                    fonts.add(readFont(parser, resources));
                } else {
                    skip(parser);
                }
            }
        }
        if (fonts.isEmpty()) {
            return null;
        }
        return new FontFamilyFilesResourceEntry((FontFileResourceEntry[]) fonts.toArray(new FontFileResourceEntry[fonts.size()]));
    }

    public static List<List<byte[]>> readCerts(Resources resources, int certsId) {
        List<List<byte[]>> certs = null;
        if (certsId != 0) {
            TypedArray typedArray = resources.obtainTypedArray(certsId);
            if (typedArray.length() > 0) {
                certs = new ArrayList<>();
                boolean isArrayOfArrays = typedArray.getResourceId(0, 0) != 0;
                if (isArrayOfArrays) {
                    for (int i = 0; i < typedArray.length(); i++) {
                        int certId = typedArray.getResourceId(i, 0);
                        String[] certsArray = resources.getStringArray(certId);
                        List<byte[]> certsList = toByteArrayList(certsArray);
                        certs.add(certsList);
                    }
                } else {
                    String[] certsArray2 = resources.getStringArray(certsId);
                    List<byte[]> certsList2 = toByteArrayList(certsArray2);
                    certs.add(certsList2);
                }
            }
            typedArray.recycle();
        }
        return certs != null ? certs : Collections.emptyList();
    }

    private static List<byte[]> toByteArrayList(String[] stringArray) {
        List<byte[]> result = new ArrayList<>();
        for (String item : stringArray) {
            result.add(Base64.decode(item, 0));
        }
        return result;
    }

    private static FontFileResourceEntry readFont(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
        AttributeSet attrs = Xml.asAttributeSet(parser);
        TypedArray array = resources.obtainAttributes(attrs, R.styleable.FontFamilyFont);
        int weight = array.getInt(R.styleable.FontFamilyFont_fontWeight, NORMAL_WEIGHT);
        boolean isItalic = 1 == array.getInt(R.styleable.FontFamilyFont_fontStyle, 0);
        int resourceId = array.getResourceId(R.styleable.FontFamilyFont_font, 0);
        String filename = array.getString(R.styleable.FontFamilyFont_font);
        array.recycle();
        while (parser.next() != 3) {
            skip(parser);
        }
        return new FontFileResourceEntry(filename, weight, isItalic, resourceId);
    }

    private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        int depth = 1;
        while (depth > 0) {
            int next = parser.next();
            if (next == 2) {
                depth++;
            } else if (next == 3) {
                depth--;
            }
        }
    }
}
