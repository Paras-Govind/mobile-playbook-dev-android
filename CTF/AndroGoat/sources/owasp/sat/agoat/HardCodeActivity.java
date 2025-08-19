package owasp.sat.agoat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: HardCodeActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lowasp/sat/agoat/HardCodeActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class HardCodeActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_hard_code);
        Button VerifyButton = (Button) findViewById(R.id.hardcode1);
        final TextView priceValue = (TextView) findViewById(R.id.price);
        final EditText promoCodeValue = (EditText) findViewById(R.id.promocode);
        final Ref.ObjectRef promoCode = new Ref.ObjectRef();
        promoCode.element = "NEW2019";
        VerifyButton.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.HardCodeActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                EditText promoCodeValue2 = promoCodeValue;
                Intrinsics.checkExpressionValueIsNotNull(promoCodeValue2, "promoCodeValue");
                if (promoCodeValue2.getText().toString().equals((String) promoCode.element)) {
                    TextView priceValue2 = priceValue;
                    Intrinsics.checkExpressionValueIsNotNull(priceValue2, "priceValue");
                    priceValue2.setText("0");
                    Toast.makeText(HardCodeActivity.this.getApplicationContext(), "Congratulations! You got this product for free", 1).show();
                    return;
                }
                Toast.makeText(HardCodeActivity.this.getApplicationContext(), "Sorry! Incorrect Promocode was entered", 1).show();
                TextView priceValue3 = priceValue;
                Intrinsics.checkExpressionValueIsNotNull(priceValue3, "priceValue");
                priceValue3.setText("2000");
            }
        });
    }
}
