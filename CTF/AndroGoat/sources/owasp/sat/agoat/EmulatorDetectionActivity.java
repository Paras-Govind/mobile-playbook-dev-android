package owasp.sat.agoat;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: EmulatorDetectionActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014¨\u0006\t"}, d2 = {"Lowasp/sat/agoat/EmulatorDetectionActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "isEmulator", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class EmulatorDetectionActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_emulator_detection);
        Button EmuButton = (Button) findViewById(R.id.EmulatorCheck);
        EmuButton.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.EmulatorDetectionActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                if (EmulatorDetectionActivity.this.isEmulator()) {
                    Toast.makeText(EmulatorDetectionActivity.this.getApplicationContext(), "This is Emulator", 1).show();
                } else {
                    Toast.makeText(EmulatorDetectionActivity.this.getApplicationContext(), "This is not Emulator", 1).show();
                }
            }
        });
    }

    public final boolean isEmulator() {
        String str = Build.FINGERPRINT + Build.DEVICE + Build.MODEL + Build.BRAND + Build.PRODUCT + Build.MANUFACTURER + Build.HARDWARE;
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String builddtls = str.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(builddtls, "(this as java.lang.String).toLowerCase()");
        return StringsKt.contains$default((CharSequence) builddtls, (CharSequence) "generic", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) builddtls, (CharSequence) EnvironmentCompat.MEDIA_UNKNOWN, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) builddtls, (CharSequence) "emulator", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) builddtls, (CharSequence) "sdk", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) builddtls, (CharSequence) "vbox", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) builddtls, (CharSequence) "genymotion", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) builddtls, (CharSequence) "x86", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) builddtls, (CharSequence) "goldfish", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) builddtls, (CharSequence) "test-keys", false, 2, (Object) null);
    }
}
