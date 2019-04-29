package msk.pobazar.wcquiz.presenter;

import android.database.Cursor;
import android.net.Uri;
import android.os.CountDownTimer;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import msk.pobazar.wcquiz.MainActivity;
import msk.pobazar.wcquiz.database.DBHelper;
import msk.pobazar.wcquiz.item.QuestionItem;
import msk.pobazar.wcquiz.item.QuestionItemGame;
import msk.pobazar.wcquiz.view.GameView;

import static msk.pobazar.wcquiz.MainActivity.LOG;

@InjectViewState
public class GamePresenter extends MvpPresenter<GameView> {
    private GameView view;
    private List<QuestionItem> questions;
    private List<QuestionItemGame> questionsGame;
    private CompositeDisposable compositeDisposable;
    private int num, countQuestion = 10;
    private int rightAnswer;
    private QuestionItem qItem;
    private long time;
    private StorageReference mStorageRef;
    private boolean timerRunning;
    private CountDownTimer countDownTimer;
    private long timeTimer =  15000;
    private Uri uriImage;

    @ProvidePresenter
    GamePresenter newInstance() {
        return new GamePresenter();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        compositeDisposable = new CompositeDisposable();
        questions = db_load_standart();
        Log.d(LOG, questions.size() + " вопросов");
        Log.d(LOG, countQuestion + " будет вопросов");
        Log.d(LOG, timeTimer + " время на вопрос");
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public void attachView(GameView view) {
        super.attachView(view);
        this.view = view;
        if (compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        Collections.shuffle(questions);
        num = 0;
        rightAnswer = 0;
        questionsGame = new ArrayList<QuestionItemGame>();
        downloadQuestion(num);
        time = System.currentTimeMillis();
    }

    @Override
    public void detachView(GameView view) {
        super.detachView(view);
        // compositeDisposable.dispose();
    }

    public void clickAnswer(String answer) {
        stopTimer();
        rightAnswer += checkAnswer(answer);
        num++;
        if (num < countQuestion) {
            downloadQuestion(num);
        } else {
            MainActivity.questionsGame = questionsGame;
            time = System.currentTimeMillis() - time;

            view.startResult(rightAnswer, num, time);
            saveResultToServer(rightAnswer, num, time);
        }
    }

    private void downloadQuestion(int num) {
        qItem = questions.get(num);
        String image = qItem.getUrlImage();
        Log.d(LOG, "image: " + image);
        ArrayList<String> a = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            a.add(qItem.getAnswer()[i]);
        }
        Collections.shuffle(a);
        uriImage=null;

        if (image != null) {
            StorageReference imageRef = mStorageRef.child("images/" + image + ".jpg");
            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Log.d(LOG, "url: " + uri);
                    uriImage=uri;
                    showQuestion(qItem.getQuestion(), a.toArray(new String[4]), uri);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.d(LOG, "" + exception);
                    showQuestion(qItem.getQuestion(), a.toArray(new String[4]), null);
                }
            });
        } else {
            showQuestion(qItem.getQuestion(), a.toArray(new String[4]), null);
        }
    }

    private void showQuestion(String question, String[] answer, Uri uri) {
        view.setQuestion(question,answer,uri);
//        view.setImage(uri);
//        view.setQuestion(question);
//        view.setAnswer(answer);

    }



    private void startStop() {
        if (timerRunning)
            stopTimer();
        else
            startTimer();

    }

    public void startTimer() {
        timeTimer = 15000;
        countDownTimer = new CountDownTimer(timeTimer, 1000) {
            @Override
            public void onTick(long l) {
                timeTimer = l;
                view.updateTimer(timeTimer);
            }

            @Override
            public void onFinish() {
                clickAnswer("");
                //сделать isRight третим значением
            }
        }.start();
        timerRunning = true;
    }

    private void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
    }


    private int checkAnswer(String answer) {
        QuestionItemGame qItemGame = new QuestionItemGame(qItem.getId(), qItem.getQuestion(), qItem.getAnswer(), qItem.getAnswerRight(), (uriImage==null)?null:uriImage.toString());
        if (answer.equals(questions.get(num).getAnswerRight())) {
            qItemGame.setRight(true);
            questionsGame.add(qItemGame);
            return 1;
        } else {
            qItemGame.setRight(false);
            questionsGame.add(qItemGame);
            return 0;
        }
    }

    private void saveResultToServer(int count, int num, long time)
    {
// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("records").child("jDfKPzoBBf9kxMrZYy6T").child("count").setValue(2);


    }

//    private void loadQuestion()
//    {
//        questions=new ArrayList<QuestionItem>();
//
//        final Disposable newsRoomDisposable = db_load_standart()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::completeLoad);
//
//        compositeDisposable.add(newsRoomDisposable);
//
//        Collections.shuffle(questions);
//    }
//
//    private Single<List<QuestionEntity>> getQuestion() {
//        return MainActivity.db.questionDao().getAll();
//    }
//
//    private void completeLoad( List<QuestionItem> newsEntities) {
//        Log.d(MainActivity.LOG, "download question from DB");
//    }
//
//    private List<QuestionItem> daoToQuestion(List<QuestionEntity> questionEntity) {
//        Log.d(MainActivity.LOG, "get " + questionEntity.size() + " question");
//        List<QuestionItem> questions = new ArrayList<>();
//        for (QuestionEntity x : questionEntity) {
//         // questions.add(x.getQuestion(),);
//        }
//        return questions;
//    }

    private List<QuestionItem> db_load_standart()                       //Загрузка вопросов из БД таблица standart
    {
        //count_levelsA=0;                                              //Кол-во вопросов
        int id;                                                  //ИД
        String question;                                        //Вопрос
        String[] answer = new String[4];                             //Ответы
        String answerRight;                                         //Правильный
        String urlImage;                                         //Картинка
        List<QuestionItem> questions = new ArrayList<QuestionItem>();


        Cursor cursor = MainActivity.database.query(DBHelper.TABLE_QUESTION_STANDART, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int questionIndex = cursor.getColumnIndex(DBHelper.KEY_QUESTION);
            int answer1Index = cursor.getColumnIndex(DBHelper.KEY_ANSWER1);
            int answer2Index = cursor.getColumnIndex(DBHelper.KEY_ANSWER2);
            int answer3Index = cursor.getColumnIndex(DBHelper.KEY_ANSWER3);
            int answer4Index = cursor.getColumnIndex(DBHelper.KEY_ANSWER4);
            int answerRIndex = cursor.getColumnIndex(DBHelper.KEY_ANSWERR);
            int pictureIndex = cursor.getColumnIndex(DBHelper.KEY_PICTURE);
            do {
                try {
                    //count_levelsA++;
                    answer = new String[4];
                    id = cursor.getInt(idIndex);
                    question = cursor.getString(questionIndex);
                    answer[0] = cursor.getString(answer1Index);
                    answer[1] = cursor.getString(answer2Index);
                    answer[2] = cursor.getString(answer3Index);
                    answer[3] = cursor.getString(answer4Index);
                    answerRight = cursor.getString(answerRIndex);
                    urlImage = cursor.getString(pictureIndex);
                    QuestionItem qItem = new QuestionItem(id, question, answer, answerRight, urlImage);
                    questions.add(qItem);
                } catch (Exception e) {
                    Log.d(LOG, "Ошибка загрузки из БД standart " + e);
                }
            }
            while (cursor.moveToNext());
            Log.d(LOG, "Вопросы из БД standart загружены");
        } else
            Log.d(LOG, "0 standart rows");

        cursor.close();
        return questions;
    }
}
