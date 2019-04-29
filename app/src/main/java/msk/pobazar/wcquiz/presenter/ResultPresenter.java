package msk.pobazar.wcquiz.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.Calendar;

import msk.pobazar.wcquiz.MainActivity;
import msk.pobazar.wcquiz.view.ResultView;

@InjectViewState
public class ResultPresenter extends MvpPresenter<ResultView> {
    private ResultView view;

    @ProvidePresenter
    ResultPresenter newInstance() {
        return new ResultPresenter();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void attachView(ResultView view) {
        super.attachView(view);
        this.view = view;
    }

    public void showResult(int right, int all, long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        int min = calendar.get(Calendar.MINUTE);
        String sMin;
        if (min < 10)
            sMin = "0" + min;
        else
            sMin = "" + min;
        int sec = calendar.get(Calendar.SECOND);
        String sSec;
        if (sec < 10)
            sSec = "0" + sec;
        else
            sSec = "" + sec;

        //  view.setResultView("Вы ответили правильно на "+right+" из "+all+" вопросов.\n"+"Время: "+sMin+":"+sSec, MainActivity.questionsGame);
        //view.setResultView("Вы ответили правильно на "+right+"вопросов\n"+"Время: "+sMin+":"+sSec, MainActivity.questionsGame);
        if (right == 1)
            view.setResultView("Вы ответили правильно на " + right + " вопрос", MainActivity.questionsGame);
        else if ((right == 2) || (right == 3) || (right == 4))
            view.setResultView("Вы ответили правильно на " + right + " вопроса", MainActivity.questionsGame);
        else
            view.setResultView("Вы ответили правильно на " + right + " вопросов", MainActivity.questionsGame);
        //закатать рекорд в бдшку
    }
}
