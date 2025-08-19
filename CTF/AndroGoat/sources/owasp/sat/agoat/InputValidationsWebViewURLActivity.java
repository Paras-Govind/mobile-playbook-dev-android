package owasp.sat.agoat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InputValidationsWebViewURLActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lowasp/sat/agoat/InputValidationsWebViewURLActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class InputValidationsWebViewURLActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_validations_web_view_url);
        Button load = (Button) findViewById(R.id.load);
        View findViewById = findViewById(R.id.webview1);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.webview1)");
        final WebView webview11 = (WebView) findViewById;
        final TextView url = (TextView) findViewById(R.id.url);
        load.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.InputValidationsWebViewURLActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                WebSettings webSettings1 = webview11.getSettings();
                Intrinsics.checkExpressionValueIsNotNull(webSettings1, "webSettings1");
                webSettings1.setJavaScriptEnabled(true);
                TextView url2 = url;
                Intrinsics.checkExpressionValueIsNotNull(url2, "url");
                webview11.loadUrl(url2.getText().toString());
            }
        });
    }
}
