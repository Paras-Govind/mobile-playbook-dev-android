package owasp.sat.agoat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.anko.AnkoAsyncContext;
import org.jetbrains.anko.AsyncKt;

/* compiled from: TrafficActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\n¨\u0006\u0015"}, d2 = {"Lowasp/sat/agoat/TrafficActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "client", "Lokhttp3/OkHttpClient;", "httpsurl", "", "getHttpsurl", "()Ljava/lang/String;", "setHttpsurl", "(Ljava/lang/String;)V", "httpurl", "getHttpurl", "setHttpurl", "doPinning", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "run", "url", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class TrafficActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private String httpurl = "http://demo.testfire.net";
    private String httpsurl = "https://owasp.org";
    private final OkHttpClient client = new OkHttpClient();

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final String getHttpurl() {
        return this.httpurl;
    }

    public final void setHttpurl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.httpurl = str;
    }

    public final String getHttpsurl() {
        return this.httpsurl;
    }

    public final void setHttpsurl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.httpsurl = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);
        Button HttpsButton = (Button) findViewById(R.id.httpsButton);
        Button PinningButton = (Button) findViewById(R.id.PinningButton);
        Button HttpButton = (Button) findViewById(R.id.httpButton);
        HttpButton.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.TrafficActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                TrafficActivity trafficActivity = TrafficActivity.this;
                trafficActivity.run(trafficActivity.getHttpurl());
            }
        });
        HttpsButton.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.TrafficActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                TrafficActivity trafficActivity = TrafficActivity.this;
                trafficActivity.run(trafficActivity.getHttpsurl());
            }
        });
        PinningButton.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.TrafficActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                TrafficActivity.this.doPinning();
            }
        });
    }

    public final void run(String url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        try {
            Request request = new Request.Builder().url(url).build();
            Toast.makeText(this, "Request sent to " + url + " Please intercept using Proxy", 1).show();
            this.client.newCall(request).enqueue(new Callback() { // from class: owasp.sat.agoat.TrafficActivity$run$1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException e) {
                    Intrinsics.checkParameterIsNotNull(call, "call");
                    Intrinsics.checkParameterIsNotNull(e, "e");
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) {
                    Intrinsics.checkParameterIsNotNull(call, "call");
                    Intrinsics.checkParameterIsNotNull(response, "response");
                    ResponseBody body = response.body();
                    System.out.println((Object) (body != null ? body.string() : null));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void doPinning() {
        AsyncKt.doAsync$default(this, null, new Function1<AnkoAsyncContext<TrafficActivity>, Unit>() { // from class: owasp.sat.agoat.TrafficActivity$doPinning$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AnkoAsyncContext<TrafficActivity> ankoAsyncContext) {
                invoke2(ankoAsyncContext);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AnkoAsyncContext<TrafficActivity> receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                try {
                    CertificatePinner pinner1 = new CertificatePinner.Builder().add("owasp.org", "sha256/gdU/UHClHJBFbIdeKuyHm/Lq/aQvMLyuTtcvTEE/1JQ=").add("owasp.org", "sha256/YLh1dUR9y6Kja30RrAn7JKnbQG/uEtLMkBgFF2Fuihg=").add("owasp.org", "sha256/Vjs8r4z+80wjNcr1YKepWQboSIRi63WsWXhIMN+eWys=").build();
                    OkHttpClient client = new OkHttpClient.Builder().certificatePinner(pinner1).build();
                    Request request = new Request.Builder().url("https://owasp.org").build();
                    Response response = client.newCall(request).execute();
                    ResponseBody body = response.body();
                    Log.v("Response", body != null ? body.string() : null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1, null);
    }
}
