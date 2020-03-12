package msk.pobazar.wcquiz.legacy.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import msk.pobazar.wcquiz.legacy.item.RecordItem;
import msk.pobazar.wcquiz.legacy.view.RecordView;

@InjectViewState
public class RecordPresenter extends MvpPresenter<RecordView> {
    private RecordView view;
    private List<RecordItem> recordItemList;

    @ProvidePresenter
    RecordPresenter newInstance() {
        return new RecordPresenter();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void attachView(RecordView view) {
        super.attachView(view);
        this.view = view;
        //доставать рекорды из бдшки в лист
    }

    public void showResult()
    {
       //сортировать лист по очкам
       // view.setRecordView(recordItemList);
    }
}
