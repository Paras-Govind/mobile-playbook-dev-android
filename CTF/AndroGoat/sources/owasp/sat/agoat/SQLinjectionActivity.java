package owasp.sat.agoat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SQLinjectionActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lowasp/sat/agoat/SQLinjectionActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "mDB", "Landroid/database/sqlite/SQLiteDatabase;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SQLinjectionActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private SQLiteDatabase mDB;

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
        setContentView(R.layout.activity_sqlinjection);
        Button SQLibutton = (Button) findViewById(R.id.SQLiBurron);
        final EditText username = (EditText) findViewById(R.id.userName);
        SQLibutton.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.SQLinjectionActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                SQLiteDatabase sQLiteDatabase;
                Cursor QryResult;
                StringBuilder strb;
                StringBuilder sb = new StringBuilder();
                sb.append("SELECT * FROM users WHERE username='");
                EditText username2 = username;
                Intrinsics.checkExpressionValueIsNotNull(username2, "username");
                sb.append(username2.getText().toString());
                sb.append("'");
                String qry = sb.toString();
                try {
                    SQLinjectionActivity.this.mDB = SQLinjectionActivity.this.openOrCreateDatabase("aGoat", 0, null);
                    sQLiteDatabase = SQLinjectionActivity.this.mDB;
                    if (sQLiteDatabase == null) {
                        Intrinsics.throwNpe();
                    }
                    QryResult = sQLiteDatabase.rawQuery(qry, null);
                    strb = new StringBuilder("");
                } catch (Exception e) {
                    Log.d("Error", "Error occurred when inserting data into database: " + e.getMessage());
                    Toast.makeText(SQLinjectionActivity.this.getApplicationContext(), "Data not saved: Error occured - " + e.getMessage(), 1).show();
                }
                if (QryResult != null && QryResult.getCount() > 0) {
                    QryResult.moveToFirst();
                    do {
                        strb.append("Username: (" + QryResult.getString(1) + ") password: (" + QryResult.getString(2) + ")\n");
                    } while (QryResult.moveToNext());
                    Toast.makeText(SQLinjectionActivity.this, strb.toString(), 1).show();
                    Log.e("Error:", strb.toString());
                    Log.v("Query", qry);
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("User: (");
                EditText username3 = username;
                Intrinsics.checkExpressionValueIsNotNull(username3, "username");
                sb2.append(username3.getText().toString());
                sb2.append(") not found");
                strb.append(sb2.toString());
                Toast.makeText(SQLinjectionActivity.this, strb.toString(), 1).show();
                Log.e("Error:", strb.toString());
                Log.v("Query", qry);
            }
        });
    }
}
