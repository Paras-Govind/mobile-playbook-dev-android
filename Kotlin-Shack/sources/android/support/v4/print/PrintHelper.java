package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class PrintHelper {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    private final PrintHelperVersionImpl mImpl;

    @Retention(RetentionPolicy.SOURCE)
    private @interface ColorMode {
    }

    public interface OnPrintFinishCallback {
        void onFinish();
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface Orientation {
    }

    interface PrintHelperVersionImpl {
        int getColorMode();

        int getOrientation();

        int getScaleMode();

        void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback);

        void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException;

        void setColorMode(int i);

        void setOrientation(int i);

        void setScaleMode(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface ScaleMode {
    }

    public static boolean systemSupportsPrint() {
        return Build.VERSION.SDK_INT >= 19;
    }

    private static final class PrintHelperStub implements PrintHelperVersionImpl {
        int mColorMode;
        int mOrientation;
        int mScaleMode;

        private PrintHelperStub() {
            this.mScaleMode = 2;
            this.mColorMode = 2;
            this.mOrientation = 1;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setScaleMode(int scaleMode) {
            this.mScaleMode = scaleMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getScaleMode() {
            return this.mScaleMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getColorMode() {
            return this.mColorMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setColorMode(int colorMode) {
            this.mColorMode = colorMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setOrientation(int orientation) {
            this.mOrientation = orientation;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getOrientation() {
            return this.mOrientation;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void printBitmap(String jobName, Bitmap bitmap, OnPrintFinishCallback callback) {
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void printBitmap(String jobName, Uri imageFile, OnPrintFinishCallback callback) {
        }
    }

    @RequiresApi(19)
    private static class PrintHelperApi19 implements PrintHelperVersionImpl {
        private static final String LOG_TAG = "PrintHelperApi19";
        private static final int MAX_PRINT_SIZE = 3500;
        final Context mContext;
        int mOrientation;
        BitmapFactory.Options mDecodeOptions = null;
        private final Object mLock = new Object();
        int mScaleMode = 2;
        int mColorMode = 2;
        protected boolean mPrintActivityRespectsOrientation = true;
        protected boolean mIsMinMarginsHandlingCorrect = true;

        PrintHelperApi19(Context context) {
            this.mContext = context;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setScaleMode(int scaleMode) {
            this.mScaleMode = scaleMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getScaleMode() {
            return this.mScaleMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setColorMode(int colorMode) {
            this.mColorMode = colorMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setOrientation(int orientation) {
            this.mOrientation = orientation;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getOrientation() {
            if (this.mOrientation == 0) {
                return 1;
            }
            return this.mOrientation;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getColorMode() {
            return this.mColorMode;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isPortrait(Bitmap bitmap) {
            return bitmap.getWidth() <= bitmap.getHeight();
        }

        protected PrintAttributes.Builder copyAttributes(PrintAttributes other) {
            PrintAttributes.Builder b = new PrintAttributes.Builder().setMediaSize(other.getMediaSize()).setResolution(other.getResolution()).setMinMargins(other.getMinMargins());
            if (other.getColorMode() != 0) {
                b.setColorMode(other.getColorMode());
            }
            return b;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void printBitmap(final String jobName, final Bitmap bitmap, final OnPrintFinishCallback callback) {
            PrintAttributes.MediaSize mediaSize;
            if (bitmap == null) {
                return;
            }
            final int fittingMode = this.mScaleMode;
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            if (isPortrait(bitmap)) {
                mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
            } else {
                mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
            }
            PrintAttributes attr = new PrintAttributes.Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build();
            printManager.print(jobName, new PrintDocumentAdapter() { // from class: android.support.v4.print.PrintHelper.PrintHelperApi19.1
                private PrintAttributes mAttributes;

                @Override // android.print.PrintDocumentAdapter
                public void onLayout(PrintAttributes oldPrintAttributes, PrintAttributes newPrintAttributes, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
                    this.mAttributes = newPrintAttributes;
                    PrintDocumentInfo info = new PrintDocumentInfo.Builder(jobName).setContentType(1).setPageCount(1).build();
                    boolean changed = true ^ newPrintAttributes.equals(oldPrintAttributes);
                    layoutResultCallback.onLayoutFinished(info, changed);
                }

                @Override // android.print.PrintDocumentAdapter
                public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
                    PrintHelperApi19.this.writeBitmap(this.mAttributes, fittingMode, bitmap, fileDescriptor, cancellationSignal, writeResultCallback);
                }

                @Override // android.print.PrintDocumentAdapter
                public void onFinish() {
                    if (callback != null) {
                        callback.onFinish();
                    }
                }
            }, attr);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Matrix getMatrix(int imageWidth, int imageHeight, RectF content, int fittingMode) {
            float scale;
            Matrix matrix = new Matrix();
            float scale2 = content.width() / imageWidth;
            if (fittingMode == 2) {
                scale = Math.max(scale2, content.height() / imageHeight);
            } else {
                scale = Math.min(scale2, content.height() / imageHeight);
            }
            matrix.postScale(scale, scale);
            float translateX = (content.width() - (imageWidth * scale)) / 2.0f;
            float translateY = (content.height() - (imageHeight * scale)) / 2.0f;
            matrix.postTranslate(translateX, translateY);
            return matrix;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference failed for: r11v0, types: [android.support.v4.print.PrintHelper$PrintHelperApi19$2] */
        public void writeBitmap(final PrintAttributes attributes, final int fittingMode, final Bitmap bitmap, final ParcelFileDescriptor fileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintAttributes build;
            if (this.mIsMinMarginsHandlingCorrect) {
                build = attributes;
            } else {
                build = copyAttributes(attributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
            }
            final PrintAttributes pdfAttributes = build;
            new AsyncTask<Void, Void, Throwable>() { // from class: android.support.v4.print.PrintHelper.PrintHelperApi19.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Throwable doInBackground(Void... params) {
                    RectF contentRect;
                    try {
                        if (cancellationSignal.isCanceled()) {
                            return null;
                        }
                        PrintedPdfDocument pdfDocument = new PrintedPdfDocument(PrintHelperApi19.this.mContext, pdfAttributes);
                        Bitmap maybeGrayscale = PrintHelperApi19.this.convertBitmapForColorMode(bitmap, pdfAttributes.getColorMode());
                        if (cancellationSignal.isCanceled()) {
                            return null;
                        }
                        try {
                            PdfDocument.Page page = pdfDocument.startPage(1);
                            if (PrintHelperApi19.this.mIsMinMarginsHandlingCorrect) {
                                contentRect = new RectF(page.getInfo().getContentRect());
                            } else {
                                PrintedPdfDocument dummyDocument = new PrintedPdfDocument(PrintHelperApi19.this.mContext, attributes);
                                PdfDocument.Page dummyPage = dummyDocument.startPage(1);
                                RectF contentRect2 = new RectF(dummyPage.getInfo().getContentRect());
                                dummyDocument.finishPage(dummyPage);
                                dummyDocument.close();
                                contentRect = contentRect2;
                            }
                            Matrix matrix = PrintHelperApi19.this.getMatrix(maybeGrayscale.getWidth(), maybeGrayscale.getHeight(), contentRect, fittingMode);
                            if (!PrintHelperApi19.this.mIsMinMarginsHandlingCorrect) {
                                matrix.postTranslate(contentRect.left, contentRect.top);
                                page.getCanvas().clipRect(contentRect);
                            }
                            page.getCanvas().drawBitmap(maybeGrayscale, matrix, null);
                            pdfDocument.finishPage(page);
                            if (!cancellationSignal.isCanceled()) {
                                pdfDocument.writeTo(new FileOutputStream(fileDescriptor.getFileDescriptor()));
                                pdfDocument.close();
                                if (fileDescriptor != null) {
                                    try {
                                        fileDescriptor.close();
                                    } catch (IOException e) {
                                    }
                                }
                                if (maybeGrayscale != bitmap) {
                                    maybeGrayscale.recycle();
                                }
                                return null;
                            }
                            pdfDocument.close();
                            if (fileDescriptor != null) {
                                try {
                                    fileDescriptor.close();
                                } catch (IOException e2) {
                                }
                            }
                            if (maybeGrayscale != bitmap) {
                                maybeGrayscale.recycle();
                            }
                            return null;
                        } finally {
                        }
                    } catch (Throwable t) {
                        return t;
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(Throwable throwable) {
                    if (cancellationSignal.isCanceled()) {
                        writeResultCallback.onWriteCancelled();
                    } else if (throwable == null) {
                        writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                    } else {
                        Log.e(PrintHelperApi19.LOG_TAG, "Error writing printed content", throwable);
                        writeResultCallback.onWriteFailed(null);
                    }
                }
            }.execute(new Void[0]);
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void printBitmap(String jobName, Uri imageFile, OnPrintFinishCallback callback) throws FileNotFoundException {
            int fittingMode = this.mScaleMode;
            PrintDocumentAdapter printDocumentAdapter = new AnonymousClass3(jobName, imageFile, callback, fittingMode);
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            PrintAttributes.Builder builder = new PrintAttributes.Builder();
            builder.setColorMode(this.mColorMode);
            if (this.mOrientation == 1 || this.mOrientation == 0) {
                builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
            } else if (this.mOrientation == 2) {
                builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
            }
            PrintAttributes attr = builder.build();
            printManager.print(jobName, printDocumentAdapter, attr);
        }

        /* renamed from: android.support.v4.print.PrintHelper$PrintHelperApi19$3, reason: invalid class name */
        class AnonymousClass3 extends PrintDocumentAdapter {
            private PrintAttributes mAttributes;
            Bitmap mBitmap = null;
            AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
            final /* synthetic */ OnPrintFinishCallback val$callback;
            final /* synthetic */ int val$fittingMode;
            final /* synthetic */ Uri val$imageFile;
            final /* synthetic */ String val$jobName;

            AnonymousClass3(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback, int i) {
                this.val$jobName = str;
                this.val$imageFile = uri;
                this.val$callback = onPrintFinishCallback;
                this.val$fittingMode = i;
            }

            /* JADX WARN: Type inference failed for: r0v3, types: [android.support.v4.print.PrintHelper$PrintHelperApi19$3$1] */
            @Override // android.print.PrintDocumentAdapter
            public void onLayout(final PrintAttributes oldPrintAttributes, final PrintAttributes newPrintAttributes, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
                synchronized (this) {
                    this.mAttributes = newPrintAttributes;
                }
                if (cancellationSignal.isCanceled()) {
                    layoutResultCallback.onLayoutCancelled();
                } else {
                    if (this.mBitmap != null) {
                        PrintDocumentInfo info = new PrintDocumentInfo.Builder(this.val$jobName).setContentType(1).setPageCount(1).build();
                        boolean changed = true ^ newPrintAttributes.equals(oldPrintAttributes);
                        layoutResultCallback.onLayoutFinished(info, changed);
                        return;
                    }
                    this.mLoadBitmap = new AsyncTask<Uri, Boolean, Bitmap>() { // from class: android.support.v4.print.PrintHelper.PrintHelperApi19.3.1
                        @Override // android.os.AsyncTask
                        protected void onPreExecute() {
                            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.support.v4.print.PrintHelper.PrintHelperApi19.3.1.1
                                @Override // android.os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    AnonymousClass3.this.cancelLoad();
                                    cancel(false);
                                }
                            });
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // android.os.AsyncTask
                        public Bitmap doInBackground(Uri... uris) {
                            try {
                                return PrintHelperApi19.this.loadConstrainedBitmap(AnonymousClass3.this.val$imageFile);
                            } catch (FileNotFoundException e) {
                                return null;
                            }
                        }

                        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
                            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:22:0x004f
                            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
                            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
                            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
                            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
                            */
                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // android.os.AsyncTask
                        public void onPostExecute(android.graphics.Bitmap r12) {
                            /*
                                r11 = this;
                                super.onPostExecute(r12)
                                r0 = 0
                                if (r12 == 0) goto L52
                                android.support.v4.print.PrintHelper$PrintHelperApi19$3 r1 = android.support.v4.print.PrintHelper.PrintHelperApi19.AnonymousClass3.this
                                android.support.v4.print.PrintHelper$PrintHelperApi19 r1 = android.support.v4.print.PrintHelper.PrintHelperApi19.this
                                boolean r1 = r1.mPrintActivityRespectsOrientation
                                if (r1 == 0) goto L16
                                android.support.v4.print.PrintHelper$PrintHelperApi19$3 r1 = android.support.v4.print.PrintHelper.PrintHelperApi19.AnonymousClass3.this
                                android.support.v4.print.PrintHelper$PrintHelperApi19 r1 = android.support.v4.print.PrintHelper.PrintHelperApi19.this
                                int r1 = r1.mOrientation
                                if (r1 != 0) goto L52
                            L16:
                                monitor-enter(r11)
                                android.support.v4.print.PrintHelper$PrintHelperApi19$3 r1 = android.support.v4.print.PrintHelper.PrintHelperApi19.AnonymousClass3.this     // Catch: java.lang.Throwable -> L4f
                                android.print.PrintAttributes r1 = android.support.v4.print.PrintHelper.PrintHelperApi19.AnonymousClass3.access$500(r1)     // Catch: java.lang.Throwable -> L4f
                                android.print.PrintAttributes$MediaSize r1 = r1.getMediaSize()     // Catch: java.lang.Throwable -> L4f
                                monitor-exit(r11)     // Catch: java.lang.Throwable -> L4a
                                if (r1 == 0) goto L52
                                boolean r2 = r1.isPortrait()
                                boolean r3 = android.support.v4.print.PrintHelper.PrintHelperApi19.access$600(r12)
                                if (r2 == r3) goto L52
                                android.graphics.Matrix r2 = new android.graphics.Matrix
                                r2.<init>()
                                r3 = 1119092736(0x42b40000, float:90.0)
                                r2.postRotate(r3)
                                r4 = 0
                                r5 = 0
                                int r6 = r12.getWidth()
                                int r7 = r12.getHeight()
                                r9 = 1
                                r3 = r12
                                r8 = r2
                                android.graphics.Bitmap r12 = android.graphics.Bitmap.createBitmap(r3, r4, r5, r6, r7, r8, r9)
                                goto L52
                            L4a:
                                r0 = move-exception
                                r10 = r1
                                r1 = r0
                                r0 = r10
                                goto L50
                            L4f:
                                r1 = move-exception
                            L50:
                                monitor-exit(r11)     // Catch: java.lang.Throwable -> L4f
                                throw r1
                            L52:
                                android.support.v4.print.PrintHelper$PrintHelperApi19$3 r1 = android.support.v4.print.PrintHelper.PrintHelperApi19.AnonymousClass3.this
                                r1.mBitmap = r12
                                if (r12 == 0) goto L7d
                                android.print.PrintDocumentInfo$Builder r1 = new android.print.PrintDocumentInfo$Builder
                                android.support.v4.print.PrintHelper$PrintHelperApi19$3 r2 = android.support.v4.print.PrintHelper.PrintHelperApi19.AnonymousClass3.this
                                java.lang.String r2 = r2.val$jobName
                                r1.<init>(r2)
                                r2 = 1
                                android.print.PrintDocumentInfo$Builder r1 = r1.setContentType(r2)
                                android.print.PrintDocumentInfo$Builder r1 = r1.setPageCount(r2)
                                android.print.PrintDocumentInfo r1 = r1.build()
                                android.print.PrintAttributes r3 = r3
                                android.print.PrintAttributes r4 = r4
                                boolean r3 = r3.equals(r4)
                                r2 = r2 ^ r3
                                android.print.PrintDocumentAdapter$LayoutResultCallback r3 = r5
                                r3.onLayoutFinished(r1, r2)
                                goto L82
                            L7d:
                                android.print.PrintDocumentAdapter$LayoutResultCallback r1 = r5
                                r1.onLayoutFailed(r0)
                            L82:
                                android.support.v4.print.PrintHelper$PrintHelperApi19$3 r1 = android.support.v4.print.PrintHelper.PrintHelperApi19.AnonymousClass3.this
                                r1.mLoadBitmap = r0
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.print.PrintHelper.PrintHelperApi19.AnonymousClass3.AnonymousClass1.onPostExecute(android.graphics.Bitmap):void");
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // android.os.AsyncTask
                        public void onCancelled(Bitmap result) {
                            layoutResultCallback.onLayoutCancelled();
                            AnonymousClass3.this.mLoadBitmap = null;
                        }
                    }.execute(new Uri[0]);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void cancelLoad() {
                synchronized (PrintHelperApi19.this.mLock) {
                    if (PrintHelperApi19.this.mDecodeOptions != null) {
                        PrintHelperApi19.this.mDecodeOptions.requestCancelDecode();
                        PrintHelperApi19.this.mDecodeOptions = null;
                    }
                }
            }

            @Override // android.print.PrintDocumentAdapter
            public void onFinish() {
                super.onFinish();
                cancelLoad();
                if (this.mLoadBitmap != null) {
                    this.mLoadBitmap.cancel(true);
                }
                if (this.val$callback != null) {
                    this.val$callback.onFinish();
                }
                if (this.mBitmap != null) {
                    this.mBitmap.recycle();
                    this.mBitmap = null;
                }
            }

            @Override // android.print.PrintDocumentAdapter
            public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
                PrintHelperApi19.this.writeBitmap(this.mAttributes, this.val$fittingMode, this.mBitmap, fileDescriptor, cancellationSignal, writeResultCallback);
            }
        }

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:52:0x0069
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
            */
        /* JADX INFO: Access modifiers changed from: private */
        public android.graphics.Bitmap loadConstrainedBitmap(android.net.Uri r11) throws java.io.FileNotFoundException {
            /*
                r10 = this;
                if (r11 == 0) goto L6e
                android.content.Context r0 = r10.mContext
                if (r0 != 0) goto L8
                goto L6e
            L8:
                android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
                r0.<init>()
                r1 = 1
                r0.inJustDecodeBounds = r1
                r10.loadBitmap(r11, r0)
                int r2 = r0.outWidth
                int r3 = r0.outHeight
                r4 = 0
                if (r2 <= 0) goto L6d
                if (r3 > 0) goto L1d
                goto L6d
            L1d:
                int r5 = java.lang.Math.max(r2, r3)
                r6 = r5
                r5 = r1
            L23:
                r7 = 3500(0xdac, float:4.905E-42)
                if (r6 <= r7) goto L2c
                int r6 = r6 >>> 1
                int r5 = r5 << 1
                goto L23
            L2c:
                if (r5 <= 0) goto L6c
                int r7 = java.lang.Math.min(r2, r3)
                int r7 = r7 / r5
                if (r7 > 0) goto L36
                goto L6c
            L36:
                java.lang.Object r7 = r10.mLock
                monitor-enter(r7)
                android.graphics.BitmapFactory$Options r8 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Throwable -> L69
                r8.<init>()     // Catch: java.lang.Throwable -> L69
                r10.mDecodeOptions = r8     // Catch: java.lang.Throwable -> L69
                android.graphics.BitmapFactory$Options r8 = r10.mDecodeOptions     // Catch: java.lang.Throwable -> L69
                r8.inMutable = r1     // Catch: java.lang.Throwable -> L69
                android.graphics.BitmapFactory$Options r1 = r10.mDecodeOptions     // Catch: java.lang.Throwable -> L69
                r1.inSampleSize = r5     // Catch: java.lang.Throwable -> L69
                android.graphics.BitmapFactory$Options r1 = r10.mDecodeOptions     // Catch: java.lang.Throwable -> L69
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L64
                android.graphics.Bitmap r7 = r10.loadBitmap(r11, r1)     // Catch: java.lang.Throwable -> L59
                java.lang.Object r8 = r10.mLock
                monitor-enter(r8)
                r10.mDecodeOptions = r4     // Catch: java.lang.Throwable -> L56
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
                return r7
            L56:
                r4 = move-exception
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
                throw r4
            L59:
                r7 = move-exception
                java.lang.Object r8 = r10.mLock
                monitor-enter(r8)
                r10.mDecodeOptions = r4     // Catch: java.lang.Throwable -> L61
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L61
                throw r7
            L61:
                r4 = move-exception
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L61
                throw r4
            L64:
                r4 = move-exception
                r9 = r4
                r4 = r1
                r1 = r9
                goto L6a
            L69:
                r1 = move-exception
            L6a:
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L69
                throw r1
            L6c:
                return r4
            L6d:
                return r4
            L6e:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r1 = "bad argument to getScaledBitmap"
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.print.PrintHelper.PrintHelperApi19.loadConstrainedBitmap(android.net.Uri):android.graphics.Bitmap");
        }

        private Bitmap loadBitmap(Uri uri, BitmapFactory.Options o) throws FileNotFoundException {
            if (uri == null || this.mContext == null) {
                throw new IllegalArgumentException("bad argument to loadBitmap");
            }
            InputStream is = null;
            try {
                is = this.mContext.getContentResolver().openInputStream(uri);
                return BitmapFactory.decodeStream(is, null, o);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException t) {
                        Log.w(LOG_TAG, "close fail ", t);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Bitmap convertBitmapForColorMode(Bitmap original, int colorMode) {
            if (colorMode != 1) {
                return original;
            }
            Bitmap grayscale = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(grayscale);
            Paint p = new Paint();
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0.0f);
            ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
            p.setColorFilter(f);
            c.drawBitmap(original, 0.0f, 0.0f, p);
            c.setBitmap(null);
            return grayscale;
        }
    }

    @RequiresApi(20)
    private static class PrintHelperApi20 extends PrintHelperApi19 {
        PrintHelperApi20(Context context) {
            super(context);
            this.mPrintActivityRespectsOrientation = false;
        }
    }

    @RequiresApi(23)
    private static class PrintHelperApi23 extends PrintHelperApi20 {
        @Override // android.support.v4.print.PrintHelper.PrintHelperApi19
        protected PrintAttributes.Builder copyAttributes(PrintAttributes other) {
            PrintAttributes.Builder b = super.copyAttributes(other);
            if (other.getDuplexMode() != 0) {
                b.setDuplexMode(other.getDuplexMode());
            }
            return b;
        }

        PrintHelperApi23(Context context) {
            super(context);
            this.mIsMinMarginsHandlingCorrect = false;
        }
    }

    @RequiresApi(24)
    private static class PrintHelperApi24 extends PrintHelperApi23 {
        PrintHelperApi24(Context context) {
            super(context);
            this.mIsMinMarginsHandlingCorrect = true;
            this.mPrintActivityRespectsOrientation = true;
        }
    }

    public PrintHelper(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.mImpl = new PrintHelperApi24(context);
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            this.mImpl = new PrintHelperApi23(context);
            return;
        }
        if (Build.VERSION.SDK_INT >= 20) {
            this.mImpl = new PrintHelperApi20(context);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.mImpl = new PrintHelperApi19(context);
        } else {
            this.mImpl = new PrintHelperStub();
        }
    }

    public void setScaleMode(int scaleMode) {
        this.mImpl.setScaleMode(scaleMode);
    }

    public int getScaleMode() {
        return this.mImpl.getScaleMode();
    }

    public void setColorMode(int colorMode) {
        this.mImpl.setColorMode(colorMode);
    }

    public int getColorMode() {
        return this.mImpl.getColorMode();
    }

    public void setOrientation(int orientation) {
        this.mImpl.setOrientation(orientation);
    }

    public int getOrientation() {
        return this.mImpl.getOrientation();
    }

    public void printBitmap(String jobName, Bitmap bitmap) {
        this.mImpl.printBitmap(jobName, bitmap, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String jobName, Bitmap bitmap, OnPrintFinishCallback callback) {
        this.mImpl.printBitmap(jobName, bitmap, callback);
    }

    public void printBitmap(String jobName, Uri imageFile) throws FileNotFoundException {
        this.mImpl.printBitmap(jobName, imageFile, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String jobName, Uri imageFile, OnPrintFinishCallback callback) throws FileNotFoundException {
        this.mImpl.printBitmap(jobName, imageFile, callback);
    }
}
