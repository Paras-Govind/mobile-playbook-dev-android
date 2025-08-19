package owasp.sat.agoat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import kotlin.Metadata;

/* compiled from: RootDetectionActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014¨\u0006\n"}, d2 = {"Lowasp/sat/agoat/RootDetectionActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "isRooted", "", "isRooted1", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RootDetectionActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_root_detection);
        Button rootBt = (Button) findViewById(R.id.rootCheck);
        rootBt.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.RootDetectionActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                if (RootDetectionActivity.this.isRooted()) {
                    Toast.makeText(RootDetectionActivity.this.getApplicationContext(), "Device is rooted", 1).show();
                } else {
                    Toast.makeText(RootDetectionActivity.this.getApplicationContext(), "Device is not rooted", 1).show();
                }
            }
        });
    }

    public final boolean isRooted() {
        String[] file = {"/system/app/Superuser/Superuser.apk", "/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su", "re.robv.android.xposed.installer-1.apk", "/data/app/eu.chainfire.supersu-1/base.apk"};
        boolean result = false;
        for (String files : file) {
            File f = new File(files);
            result = f.exists();
            if (result) {
                break;
            }
        }
        return result;
    }

    public final boolean isRooted1() {
        try {
            Runtime.getRuntime().exec(new String[]{"su", "ls /data/data/"});
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
}
