package owasp.sat.agoat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinaryPatchingActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005¨\u0006\n"}, d2 = {"Lowasp/sat/agoat/BinaryPatchingActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "isAdmin", "", "()Z", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class BinaryPatchingActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private final boolean isAdmin;

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

    /* renamed from: isAdmin, reason: from getter */
    public final boolean getIsAdmin() {
        return this.isAdmin;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_patching);
        if (this.isAdmin) {
            TextView isAdminText = (TextView) _$_findCachedViewById(R.id.isAdminText);
            Intrinsics.checkExpressionValueIsNotNull(isAdminText, "isAdminText");
            isAdminText.setText("You are Admin Now");
            Button adminButton = (Button) _$_findCachedViewById(R.id.adminButton);
            Intrinsics.checkExpressionValueIsNotNull(adminButton, "adminButton");
            adminButton.setEnabled(true);
        }
        ((Button) _$_findCachedViewById(R.id.adminButton)).setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.BinaryPatchingActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                Toast.makeText(BinaryPatchingActivity.this, "You clicked on Administartion button", 1).show();
            }
        });
    }
}
