package owasp.sat.agoat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InputValidationsOSCMDInjectionMain2Activity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lowasp/sat/agoat/InputValidationsOSCMDInjectionMain2Activity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class InputValidationsOSCMDInjectionMain2Activity extends AppCompatActivity {
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
        setContentView(R.layout.activity_input_validations_oscmdinjection_main2);
        Button runButton = (Button) findViewById(R.id.run);
        final TextView result = (TextView) findViewById(R.id.result);
        final EditText ip = (EditText) findViewById(R.id.ip);
        runButton.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.InputValidationsOSCMDInjectionMain2Activity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                StringBuilder sb = new StringBuilder();
                sb.append("ping ");
                EditText ip2 = ip;
                Intrinsics.checkExpressionValueIsNotNull(ip2, "ip");
                sb.append(ip2.getText().toString());
                String ip1 = sb.toString();
                StringBuffer output1 = new StringBuffer();
                try {
                    Process p = Runtime.getRuntime().exec(ip1, (String[]) null, (File) null);
                    Intrinsics.checkExpressionValueIsNotNull(p, "Runtime.getRuntime().exec(ip1,null,null)");
                    p.waitFor();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = reader.readLine();
                    Intrinsics.checkExpressionValueIsNotNull(line, "reader.readLine()");
                    while (line != null) {
                        output1.append(line + "n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                TextView result2 = result;
                Intrinsics.checkExpressionValueIsNotNull(result2, "result");
                result2.setText(output1.toString());
            }
        });
    }
}
