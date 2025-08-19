package owasp.sat.agoat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: AccessControlIssue1Activity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0006\u0010\r\u001a\u00020\u0004J\u0006\u0010\u000e\u001a\u00020\n¨\u0006\u000f"}, d2 = {"Lowasp/sat/agoat/AccessControlIssue1Activity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "createPIN", "", "pinValue", "", "hashPIN", "isPinCorrect", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "verifyIfPinSet", "verifyPINView", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AccessControlIssue1Activity extends AppCompatActivity {
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
        if (verifyIfPinSet()) {
            verifyPINView();
            return;
        }
        setContentView(R.layout.activity_access_control_issue1);
        Button setPINButton = (Button) findViewById(R.id.setPIN);
        setPINButton.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.AccessControlIssue1Activity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                EditText pinValue = (EditText) AccessControlIssue1Activity.this.findViewById(R.id.setpin);
                AccessControlIssue1Activity accessControlIssue1Activity = AccessControlIssue1Activity.this;
                Intrinsics.checkExpressionValueIsNotNull(pinValue, "pinValue");
                if (accessControlIssue1Activity.createPIN(pinValue.getText().toString())) {
                    Toast.makeText(AccessControlIssue1Activity.this.getApplicationContext(), "PIN created. Please Login", 1).show();
                    AccessControlIssue1Activity.this.verifyPINView();
                } else {
                    Toast.makeText(AccessControlIssue1Activity.this.getApplicationContext(), "PIN not created", 1).show();
                }
            }
        });
    }

    public final boolean verifyIfPinSet() {
        SharedPreferences sharedPreferences = getSharedPreferences("pinDetails", 0);
        return sharedPreferences.getBoolean("pinSet", false);
    }

    public final boolean createPIN(String pinValue) {
        Intrinsics.checkParameterIsNotNull(pinValue, "pinValue");
        SharedPreferences sharedPreferences = getSharedPreferences("pinDetails", 0);
        SharedPreferences.Editor editor1 = sharedPreferences.edit();
        editor1.putBoolean("pinSet", true);
        editor1.putString("pin", hashPIN(pinValue));
        return editor1.commit();
    }

    public final boolean isPinCorrect(String pinValue) {
        Intrinsics.checkParameterIsNotNull(pinValue, "pinValue");
        SharedPreferences sharedPreferences = getSharedPreferences("pinDetails", 0);
        return sharedPreferences.getString("pin", null).equals(hashPIN(pinValue));
    }

    public final void verifyPINView() {
        setContentView(R.layout.activity_access_verify_pin);
        Button verifyPINButton = (Button) findViewById(R.id.verifyPIN);
        verifyPINButton.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.AccessControlIssue1Activity$verifyPINView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                EditText pinValue = (EditText) AccessControlIssue1Activity.this.findViewById(R.id.pinValue);
                AccessControlIssue1Activity accessControlIssue1Activity = AccessControlIssue1Activity.this;
                Intrinsics.checkExpressionValueIsNotNull(pinValue, "pinValue");
                if (accessControlIssue1Activity.isPinCorrect(pinValue.getText().toString())) {
                    Toast.makeText(AccessControlIssue1Activity.this.getApplicationContext(), "PIN Verified", 1).show();
                    Intent myIntent = new Intent(AccessControlIssue1Activity.this, (Class<?>) AccessControl1ViewActivity.class);
                    AccessControlIssue1Activity.this.startActivity(myIntent);
                    return;
                }
                Toast.makeText(AccessControlIssue1Activity.this.getApplicationContext(), "Incorrect PIN entered", 1).show();
            }
        });
    }

    public final String hashPIN(String pinValue) {
        Intrinsics.checkParameterIsNotNull(pinValue, "pinValue");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = pinValue.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] digest = messageDigest.digest(bytes);
        Intrinsics.checkExpressionValueIsNotNull(digest, "MessageDigest.getInstanc…t(pinValue.toByteArray())");
        String md = ArraysKt.joinToString$default(digest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, String>() { // from class: owasp.sat.agoat.AccessControlIssue1Activity$hashPIN$md$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(Byte b) {
                return invoke(b.byteValue());
            }

            public final String invoke(byte it) {
                String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
                return format;
            }
        }, 30, (Object) null);
        return md;
    }
}
