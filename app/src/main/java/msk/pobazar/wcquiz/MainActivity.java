package msk.pobazar.wcquiz;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

import msk.pobazar.wcquiz.database.DBHelper;
import msk.pobazar.wcquiz.item.QuestionItemGame;
import msk.pobazar.wcquiz.ui.GameFragment;
import msk.pobazar.wcquiz.ui.MenuFragment;
import msk.pobazar.wcquiz.ui.RecordFragment;
import msk.pobazar.wcquiz.ui.ResultFragment;

public class MainActivity extends AppCompatActivity implements MainFragmentListener {
    public static final String LOG = "Logs";
    private String tag = "game";
    public DBHelper dbhelper;
    public static SQLiteDatabase database;
    public static List<QuestionItemGame> questionsGame;
    public static File dbf;
    private StorageReference mStorageRef;
    private ProgressBar progressBar;
    private TextView textView;
    private Button button;
    RecordFragment recordFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar_started);
        textView = findViewById(R.id.download_failed);
        button = findViewById(R.id.button_try);
        button.setOnClickListener(v -> {
            setVisibleElements("download");
            dbLoad();
        });
        setVisibleElements("download");
        MobileAds.initialize(this, "ca-app-pub-1267411731078735~7822896652");
        initializeAdMob();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbLoad();
    }

    @Override
    public void startGame() {
        GameFragment fragment = new GameFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void startRegame() {
        MenuFragment fragment = new MenuFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                // .addToBackStack(tag)
                .commit();

        GameFragment fragment1 = new GameFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment1)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void startRecord() {
        recordFragment = new RecordFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, recordFragment)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void startResult(int right, int all, long time) {
        ResultFragment fragment = ResultFragment.newInstance(right, all, time);

        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void startMenu() {
        MenuFragment fragment = new MenuFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, "menu")
                // .addToBackStack(tag)
                .commit();
    }

    @Override
    public void deleteRecord() {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(recordFragment)
                .commit();
        getSupportFragmentManager().popBackStack();
    }

    public void dbLoad()                    //Загрузка БД
    {
        try {
            dbf = File.createTempFile("DB_question", "db");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference dbRef = mStorageRef.child("DB_question.db");

        dbRef.getFile(dbf).addOnSuccessListener(taskSnapshot -> {
            Log.d(LOG, "БД загрузилась");
            db_connect();
            setVisibleElements("game");
            startMenu();
        }).addOnFailureListener(exception -> {
            Log.d(LOG, "БД не загрузилась:" + exception);
            setVisibleElements("error");
        });
    }

    public void setVisibleElements(String cond) {
        switch (cond) {
            case "download":
                progressBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                button.setEnabled(false);
                break;
            case "error":
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                button.setEnabled(true);
                break;
            case "game":
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                button.setEnabled(false);
                break;
        }
    }

    private void showValueApp() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=ru.alexanderklimov.crib"));
            startActivity(intent);
        } catch (Exception e) {
            Log.d(LOG, "" + e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.value_button_menu:
                showValueApp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void db_connect()                    //Подключение к БД
    {

        dbhelper = new DBHelper(this);
        try {
            dbhelper.updateDataBase();
            Log.d(LOG, "Проверка обновления БД");
        } catch (IOException mIOException) {
            Log.d(LOG, "UnableToUpdateDatabase");
        }
        try {
            database = dbhelper.getReadableDatabase();
            Log.d(LOG, "БД открылась");
        } catch (SQLException mSQLException) {
            Log.d(LOG, "Ошибка БД " + mSQLException);
        }
    }

    public void initializeAdMob() {
        ResultFragment.mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        ResultFragment.loadRewardAdMob();

        ResultFragment.mInterstitialAd = new InterstitialAd(this);
        ResultFragment.mInterstitialAd.setAdUnitId("ca-app-pub-1267411731078735/8944406635");
        ResultFragment.mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

}
