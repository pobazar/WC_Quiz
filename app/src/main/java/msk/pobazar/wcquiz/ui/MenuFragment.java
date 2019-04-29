package msk.pobazar.wcquiz.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import msk.pobazar.wcquiz.MainActivity;
import msk.pobazar.wcquiz.MainFragmentListener;
import msk.pobazar.wcquiz.R;
import msk.pobazar.wcquiz.presenter.MenuPresenter;
import msk.pobazar.wcquiz.view.MenuView;


public class MenuFragment extends MvpAppCompatFragment implements MenuView {
    @InjectPresenter
    MenuPresenter presenter;
    private static final int LAYOUT = R.layout.fragment_menu;
    private View view;
    private MainFragmentListener listener;

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
        initView();
        Log.d(MainActivity.LOG, "Start menu fragment");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void initView()
    {
        Button buttonGame, buttonRecord, buttonExit;
        buttonExit=view.findViewById(R.id.button_exit);
        buttonGame=view.findViewById(R.id.button_game);
        buttonRecord=view.findViewById(R.id.button_record);

        buttonRecord.setOnClickListener(v ->
        {
            recordClick();
        });

        buttonGame.setOnClickListener(v ->
        {
            gameClick();
        });

        buttonExit.setOnClickListener(v ->
        {
            ExitClick();
        });
    }

    @Override
    public void gameClick() {
        listener.startGame();
    }

    @Override
    public void ExitClick() {
        System.exit(0);
    }

    @Override
    public void recordClick() {
        listener.startRecord();
    }
}
