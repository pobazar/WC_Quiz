package msk.pobazar.wcquiz.legacy.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import msk.pobazar.wcquiz.legacy.item.RecordItem;

public interface RecordView extends MvpView {
    void menuClick();
    void setRecordView(List<RecordItem> recordItemList);
}
