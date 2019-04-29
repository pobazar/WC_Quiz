package msk.pobazar.wcquiz.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import msk.pobazar.wcquiz.item.RecordItem;

public interface RecordView extends MvpView {
    void menuClick();
    void setRecordView(List<RecordItem> recordItemList);
}
