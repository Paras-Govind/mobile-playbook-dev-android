package owasp.sat.agoat;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoAsyncContext;
import org.jetbrains.anko.AsyncKt;

/* compiled from: DownloadInvoiceService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\"\u0010\u000b\u001a\u00020\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016¨\u0006\u000f"}, d2 = {"Lowasp/sat/agoat/DownloadInvoiceService;", "Landroid/app/Service;", "()V", "downloadFile", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class DownloadInvoiceService extends Service {
    @Override // android.app.Service
    public void onCreate() {
        Log.i("DOWNLOAD", "Service onCreate");
        Toast.makeText(getApplicationContext(), "Service Created", 1).show();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Log.i("DOWNLOAD", "Service onBind");
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("DOWNLOAD", "Invoice is being downloaded");
        downloadFile();
        Toast.makeText(getApplicationContext(), "Invoice is being downloaded", 1).show();
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i("DOWNLOAD", "Service onDestroy");
    }

    public final void downloadFile() {
        AsyncKt.doAsync$default(this, null, new Function1<AnkoAsyncContext<DownloadInvoiceService>, Unit>() { // from class: owasp.sat.agoat.DownloadInvoiceService$downloadFile$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AnkoAsyncContext<DownloadInvoiceService> ankoAsyncContext) {
                invoke2(ankoAsyncContext);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AnkoAsyncContext<DownloadInvoiceService> receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                Uri url = Uri.parse("https://github.com/satishpatnayak/MyTest/blob/master/AndroGoatInvoice.txt");
                Intrinsics.checkExpressionValueIsNotNull(url, "Uri.parse(url1)");
                DownloadManager.Request request = new DownloadManager.Request(url);
                String fileName = url.getLastPathSegment();
                Intrinsics.checkExpressionValueIsNotNull(fileName, "url.lastPathSegment");
                request.setAllowedNetworkTypes(3);
                request.setTitle(fileName);
                request.setDescription("The File is downloading...");
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(1);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, String.valueOf(System.currentTimeMillis()));
                Object systemService = DownloadInvoiceService.this.getSystemService("download");
                if (systemService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.app.DownloadManager");
                }
                DownloadManager manager = (DownloadManager) systemService;
                manager.enqueue(request);
            }
        }, 1, null);
    }
}
