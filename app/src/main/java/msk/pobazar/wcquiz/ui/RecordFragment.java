package msk.pobazar.wcquiz.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import msk.pobazar.wcquiz.MainActivity;
import msk.pobazar.wcquiz.MainFragmentListener;
import msk.pobazar.wcquiz.R;
import msk.pobazar.wcquiz.item.RecordItem;
import msk.pobazar.wcquiz.item.RecordRecyclerAdapter;
import msk.pobazar.wcquiz.presenter.RecordPresenter;
import msk.pobazar.wcquiz.view.RecordView;

public class RecordFragment extends MvpAppCompatFragment implements RecordView {
    @InjectPresenter
    RecordPresenter presenter;
    private static final int LAYOUT = R.layout.fragment_record;
    private View view;
    private RecyclerView recyclerView;
    private MainFragmentListener listener;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainFragmentListener) {
            listener = (MainFragmentListener) context;
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(LAYOUT, container, false);
        context = getContext();
        initView();
        Log.d(MainActivity.LOG, "Start record fragment");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.showResult();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void initView()
    {
        Button  buttonMenu;
        buttonMenu = view.findViewById(R.id.button_show_answers);
        recyclerView=view.findViewById(R.id.recycler_record);

        buttonMenu.setOnClickListener(v ->
        {
            menuClick();
        });
    }

    @Override
    public void menuClick() {
        listener.deleteRecord();
    }

    @Override
    public void setRecordView(List<RecordItem> records) {
        showRecords(records);
    }

    public void showRecords(List<RecordItem> records) {
        recyclerView.setAdapter(new RecordRecyclerAdapter(context, records));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
}
