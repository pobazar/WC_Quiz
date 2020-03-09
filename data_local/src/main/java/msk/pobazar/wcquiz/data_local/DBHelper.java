//package msk.pobazar.wcquiz.data_local;
//
//import android.content.Context;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import static msk.pobazar.wcquiz.presentation.MainActivity.dbf;
//
///**
// * Created by Артем on 10.03.2018.
// */
//
//public class DBHelper extends SQLiteOpenHelper {
//
//    public static final int DATABASE_VERSION = 16;
//    public static final String DATABASE_NAME = "DB_question.db";
//    public static String DATABASE_PATH = "";
//    public static final String TABLE_QUESTION_STANDART = "standart";
//    public static final String TABLE_QUESTION_TWO_ANSWER = "two_answer";
//    private final Context mContext;
//    private boolean mNeedUpdate = false;
//    private SQLiteDatabase mDataBase;
//    public static final String TAG ="my_Log";
//
//    public static final String KEY_ID = "_id";
//    public static final String KEY_QUESTION = "question";
//    public static final String KEY_ANSWER1 = "answer1";
//    public static final String KEY_ANSWER2 = "answer2";
//    public static final String KEY_ANSWER3 = "answer3";
//    public static final String KEY_ANSWER4 = "answer4";
//    public static final String KEY_ANSWERR = "answerR";
//    public static final String KEY_PICTURE = "picture";
//
//
//    public DBHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";
//        this.mContext = context;
//
//        copyDataBase();
//
//        this.getReadableDatabase();
//    }
//
//    public void updateDataBase() throws IOException {
//        if (mNeedUpdate) {
//            File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
//            if (dbFile.exists())
//                dbFile.delete();
//
//            copyDataBase();
//
//            mNeedUpdate = false;
//        }
//    }
//
//    private boolean checkDataBase() {
//        File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
//        return dbFile.exists();
//    }
//
//    private void copyDataBase() {
//        if (!checkDataBase()) {
//            this.getReadableDatabase();
//            this.close();
//            try {
//                copyDBFile();
//            } catch (IOException mIOException) {
//                throw new Error("ErrorCopyingDataBase");
//            }
//        }
//    }
//
//    private void copyDBFile() throws IOException {
//        // File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
//        //dbFile.delete();
//        try {
//            InputStream mInput = new FileInputStream(MainActivity.dbf);
//            //InputStream mInput = mContext.getResources().openRawResource(R.raw.DB_question);
//            OutputStream mOutput = new FileOutputStream(DATABASE_PATH + DATABASE_NAME);
//            byte[] mBuffer = new byte[1024];
//            int mLength;
//            while ((mLength = mInput.read(mBuffer)) > 0)
//                mOutput.write(mBuffer, 0, mLength);
//            mOutput.flush();
//            mOutput.close();
//            mInput.close();
//        }
//        catch (Exception e)
//        {
//            Log.d(TAG,""+e);
//        }
//    }
//
//    public boolean openDataBase() throws SQLException {
//        mDataBase = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
//        return mDataBase != null;
//    }
//
//    @Override
//    public synchronized void close() {
//        if (mDataBase != null)
//            mDataBase.close();
//        super.close();
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (newVersion > oldVersion)
//            mNeedUpdate = true;
//    }
//}