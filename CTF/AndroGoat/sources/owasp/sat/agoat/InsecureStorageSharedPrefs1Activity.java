package owasp.sat.agoat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InsecureStorageSharedPrefs1Activity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004J\u0016\u0010\f\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004J\u0006\u0010\u000e\u001a\u00020\u0004J\u0012\u0010\u000f\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014¨\u0006\u0012"}, d2 = {"Lowasp/sat/agoat/InsecureStorageSharedPrefs1Activity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "CalculateScore", "", "Score", "CheckScore", "", "DisplayScore", "", "score", "level", "UpdateScoreInSP", "Level", "getScoreFromSP", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class InsecureStorageSharedPrefs1Activity extends AppCompatActivity {
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
        setContentView(R.layout.activity_insecure_storage_shared_prefs1);
        final Button ScoreButton = (Button) findViewById(R.id.getScore);
        ScoreButton.setOnClickListener(new View.OnClickListener() { // from class: owasp.sat.agoat.InsecureStorageSharedPrefs1Activity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                int scoreValue = InsecureStorageSharedPrefs1Activity.this.getScoreFromSP();
                int levelValue = 1;
                int newScore = InsecureStorageSharedPrefs1Activity.this.CalculateScore(scoreValue);
                if (InsecureStorageSharedPrefs1Activity.this.CheckScore(newScore)) {
                    levelValue = 2;
                    Button ScoreButton2 = ScoreButton;
                    Intrinsics.checkExpressionValueIsNotNull(ScoreButton2, "ScoreButton");
                    ScoreButton2.setClickable(false);
                    Button ScoreButton3 = ScoreButton;
                    Intrinsics.checkExpressionValueIsNotNull(ScoreButton3, "ScoreButton");
                    ScoreButton3.setText("You won the game");
                    Toast.makeText(InsecureStorageSharedPrefs1Activity.this.getApplicationContext(), "Success", 1).show();
                } else {
                    Toast.makeText(InsecureStorageSharedPrefs1Activity.this.getApplicationContext(), "Score is " + newScore + ". Try hard", 0).show();
                }
                InsecureStorageSharedPrefs1Activity.this.DisplayScore(newScore, levelValue);
                InsecureStorageSharedPrefs1Activity.this.UpdateScoreInSP(newScore, levelValue);
            }
        });
    }

    public final int CalculateScore(int Score) {
        return Score + 1;
    }

    public final boolean CheckScore(int Score) {
        return Score > 10000;
    }

    public final void DisplayScore(int score, int level) {
        TextView Score = (TextView) findViewById(R.id.score);
        TextView Level = (TextView) findViewById(R.id.level);
        Intrinsics.checkExpressionValueIsNotNull(Score, "Score");
        Score.setText(String.valueOf(score));
        Intrinsics.checkExpressionValueIsNotNull(Level, "Level");
        Level.setText(String.valueOf(level));
        System.out.println((Object) ("Score " + String.valueOf(score)));
        System.out.println((Object) ("Level " + String.valueOf(level)));
    }

    public final void UpdateScoreInSP(int Score, int Level) {
        SharedPreferences sharedPreference = getSharedPreferences("score", 0);
        SharedPreferences.Editor editor1 = sharedPreference.edit();
        editor1.putInt("score", Score);
        editor1.putInt("level", Level);
        editor1.commit();
    }

    public final int getScoreFromSP() {
        int Score = 0;
        int Level = 1;
        SharedPreferences sharedPreferences = getSharedPreferences("score", 0);
        if (sharedPreferences.getInt("score", 0) != 0 && sharedPreferences.getInt("level", 0) != 0) {
            Score = sharedPreferences.getInt("score", 0);
            Level = sharedPreferences.getInt("level", 0);
        }
        System.out.println((Object) ("Score is " + Score + " and Level is" + Level));
        return Score;
    }
}
