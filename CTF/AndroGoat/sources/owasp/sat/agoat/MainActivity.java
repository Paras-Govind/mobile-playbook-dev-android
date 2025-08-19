package owasp.sat.agoat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.util.HashMap;
import kotlin.Metadata;

/* compiled from: MainActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lowasp/sat/agoat/MainActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class MainActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main);
        ((Button) _$_findCachedViewById(R.id.rootButton1)).setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.MainActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) RootDetectionActivity.class));
            }
        });
        ((Button) _$_findCachedViewById(R.id.EmulatorButton)).setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.MainActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) EmulatorDetectionActivity.class));
            }
        });
        ((Button) _$_findCachedViewById(R.id.InsecureStorage)).setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.MainActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) InsecureStorageActivity.class));
            }
        });
        ((Button) _$_findCachedViewById(R.id.InputValidations)).setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.MainActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) InputValidationsActivity.class));
            }
        });
        ((Button) _$_findCachedViewById(R.id.SideChannelLeakage)).setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.MainActivity$onCreate$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) SideChannelDataLeakageActivity.class));
            }
        });
        ((Button) _$_findCachedViewById(R.id.AccessControl1)).setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.MainActivity$onCreate$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) AccessControlIssue1Activity.class));
            }
        });
        ((Button) _$_findCachedViewById(R.id.Hardcode)).setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.MainActivity$onCreate$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) HardCodeActivity.class));
            }
        });
        ((Button) _$_findCachedViewById(R.id.Traffic)).setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.MainActivity$onCreate$8
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) TrafficActivity.class));
            }
        });
        ((Button) _$_findCachedViewById(R.id.BinaryPatching)).setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.MainActivity$onCreate$9
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) BinaryPatchingActivity.class));
            }
        });
    }
}
