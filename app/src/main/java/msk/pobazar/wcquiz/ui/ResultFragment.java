package msk.pobazar.wcquiz.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.List;

import msk.pobazar.wcquiz.MainActivity;
import msk.pobazar.wcquiz.MainFragmentListener;
import msk.pobazar.wcquiz.R;
import msk.pobazar.wcquiz.item.QuestionItem;
import msk.pobazar.wcquiz.item.QuestionItemGame;
import msk.pobazar.wcquiz.item.QuestionRecyclerAdapter;
import msk.pobazar.wcquiz.presenter.ResultPresenter;
import msk.pobazar.wcquiz.view.ResultView;

public class ResultFragment extends MvpAppCompatFragment implements ResultView {
    @InjectPresenter
    ResultPresenter presenter;
    private static final int LAYOUT = R.layout.fragment_result;
    private View view;
    private MainFragmentListener listener;
    private TextView resultView;
    private RecyclerView recyclerView;
    private int right, all;
    private long time;
    private List<QuestionItem> questions;
    private Context context;
    private static final String ARGS_1 = "right";
    private static final String ARGS_2 = "all";
    private static final String ARGS_3 = "time";
    Button buttonGame, buttonMarketing;


    static public ResultFragment newInstance(int right, int all, long time) {
        ResultFragment pageFragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGS_1, right);
        bundle.putSerializable(ARGS_2, all);
        bundle.putSerializable(ARGS_3, time);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

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

        if (getArguments() != null) {
            right = getArguments().getInt(ARGS_1);
            all = getArguments().getInt(ARGS_2);
            time = getArguments().getLong(ARGS_3);
        }

        initView();
        Log.d(MainActivity.LOG, "Start result fragment");
        setListenerAdMob();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.showResult(right, all, time);
        buttonMarketing.setEnabled(true);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void initView() {
        buttonMarketing = view.findViewById(R.id.button_show_answers);
        buttonGame = view.findViewById(R.id.button_replay);
        resultView = view.findViewById(R.id.result);
        recyclerView = view.findViewById(R.id.recycler_answer);

        buttonMarketing.setOnClickListener(v ->
        {
            showRewardAdMob();
        });

        buttonGame.setOnClickListener(v ->
        {
            gameClick();
        });
    }

    @Override
    public void gameClick() {
        showAdMob();
    }

    @Override
    public void menuClick() {
        listener.startMenu();
    }

    @Override
    public void setResultView(String s, List<QuestionItemGame> questions) {
        resultView.setText(s);
        showAnswers(questions, false);
    }

    public void showAnswers(List<QuestionItemGame> questions, boolean answer) {
        recyclerView.setAdapter(new QuestionRecyclerAdapter(context, questions, answer));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private void showRewardAdMob() {
        if (MainActivity.mRewardedVideoAd.isLoaded()) {
            Log.d(MainActivity.LOG, "ревард реклама показ");
            MainActivity.mRewardedVideoAd.show();
        } else {
            Log.d(MainActivity.LOG, "ревард реклама еще не загрузилась");
            buttonMarketing.setEnabled(false);
            showAnswers(MainActivity.questionsGame, true);
        }
    }

    private void showAdMob() {
        if (MainActivity.mInterstitialAd.isLoaded()) {
            Log.d(MainActivity.LOG, "Межстраничная реклама показ");
            MainActivity.mInterstitialAd.show();
        } else {
            Log.d(MainActivity.LOG, "Межстраничная реклама еще не загрузилась");
            listener.startRegame();
        }
    }



    private void setListenerAdMob() {
        MainActivity.mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                MainActivity.mInterstitialAd.loadAd(new AdRequest.Builder().build());
                listener.startRegame();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d(MainActivity.LOG, "реклама межстраничная не загружена errorCode=" + errorCode);
                super.onAdFailedToLoad(errorCode);
                MainActivity.mInterstitialAd.loadAd(new AdRequest.Builder().build());
                listener.startRegame();
            }

            @Override
            public void onAdLoaded() {
                Log.d(MainActivity.LOG, "Реклама межстраничная загрузилась");
            }

        });


        MainActivity.mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                Log.d(MainActivity.LOG, "Реклама ревард загрузилась");
            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                buttonMarketing.setEnabled(false);
                MainActivity.loadRewardAdMob();
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                buttonMarketing.setEnabled(false);
                MainActivity.loadRewardAdMob();
                showAnswers(MainActivity.questionsGame, true);
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
                MainActivity.loadRewardAdMob();
            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });
    }
}
