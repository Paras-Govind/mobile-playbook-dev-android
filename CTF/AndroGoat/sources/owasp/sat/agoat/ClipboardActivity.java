package owasp.sat.agoat;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

/* compiled from: ClipboardActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lowasp/sat/agoat/ClipboardActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ClipboardActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_clipboard);
        final EditText ccValue = (EditText) findViewById(R.id.cc);
        ((Button) _$_findCachedViewById(R.id.verifyCC)).setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.ClipboardActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                EditText ccValue2 = ccValue;
                Intrinsics.checkExpressionValueIsNotNull(ccValue2, "ccValue");
                String obj = ccValue2.getText().toString();
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                String ccValue1 = StringsKt.trim((CharSequence) obj).toString();
                if (ccValue1 != null) {
                    if (!(ccValue1.length() == 0)) {
                        int otp = ((Number) CollectionsKt.first(CollectionsKt.shuffled(new IntRange(1000, 9999)))).intValue();
                        Object systemService = ClipboardActivity.this.getSystemService("clipboard");
                        if (systemService == null) {
                            throw new TypeCastException("null cannot be cast to non-null type android.content.ClipboardManager");
                        }
                        ClipboardManager clipboard = (ClipboardManager) systemService;
                        ClipData clip = ClipData.newPlainText("CC Card", String.valueOf(otp));
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(ClipboardActivity.this, "OTP Generated and Copied: " + otp, 1).show();
                        return;
                    }
                }
                Toast.makeText(ClipboardActivity.this, "Credit Card shouldn't be blank", 1).show();
            }
        });
    }
}
