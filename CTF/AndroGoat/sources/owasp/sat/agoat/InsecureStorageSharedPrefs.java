package owasp.sat.agoat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InsecureStorageSharedPrefs.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lowasp/sat/agoat/InsecureStorageSharedPrefs;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class InsecureStorageSharedPrefs extends AppCompatActivity {
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
        setContentView(R.layout.activity_insecure_storage_shared_prefs);
        Button savebutton = (Button) findViewById(R.id.SPViewButton);
        final EditText username = (EditText) findViewById(R.id.userName);
        final EditText password = (EditText) findViewById(R.id.password);
        savebutton.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.InsecureStorageSharedPrefs$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                SharedPreferences sharedPreference = InsecureStorageSharedPrefs.this.getSharedPreferences("users", 0);
                SharedPreferences.Editor editor = sharedPreference.edit();
                EditText username2 = username;
                Intrinsics.checkExpressionValueIsNotNull(username2, "username");
                editor.putString("username", username2.getText().toString());
                EditText password2 = password;
                Intrinsics.checkExpressionValueIsNotNull(password2, "password");
                editor.putString("password", password2.getText().toString());
                if (editor.commit()) {
                    Toast.makeText(InsecureStorageSharedPrefs.this.getApplicationContext(), "Data saved", 1).show();
                } else {
                    Toast.makeText(InsecureStorageSharedPrefs.this.getApplicationContext(), "Data not saved", 1).show();
                }
            }
        });
    }
}
