package msk.pobazar.wcquiz.legacy.view;

import android.net.Uri;

import com.arellomobile.mvp.MvpView;

public interface GameView extends MvpView {
    void setImage(Uri uri);

    void setQuestion(String s);

    void setAnswer(String[] mas);

    void setQuestion(String s, String[] mas, Uri uri);

    void startResult(int right, int all, long time);

    void updateTimer(long t);
}
