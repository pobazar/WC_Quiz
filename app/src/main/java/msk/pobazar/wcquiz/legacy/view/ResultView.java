package msk.pobazar.wcquiz.legacy.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import msk.pobazar.wcquiz.legacy.item.QuestionItemGame;

public interface ResultView extends MvpView {
    void gameClick();
    void menuClick();
    void setResultView(String s, List<QuestionItemGame> questions);
}
