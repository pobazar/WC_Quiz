package msk.pobazar.wcquiz.legacy.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import msk.pobazar.wcquiz.legacy.MainActivity;
import msk.pobazar.wcquiz.legacy.MainFragmentListener;
import msk.pobazar.wcquiz.R;
import msk.pobazar.wcquiz.legacy.presenter.GamePresenter;
import msk.pobazar.wcquiz.legacy.view.GameView;

public class GameFragment extends MvpAppCompatFragment implements GameView {
    @InjectPresenter
    GamePresenter presenter;
    private static final int LAYOUT = R.layout.fragment_game;
    private View view;
    private MainFragmentListener listener;
    private Context context;
    private Button button1, button2, button3, button4;
    private ImageView image;
    private TextView questionView;
    private ProgressBar progressBar;

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
        setEnabledButton(false);
        Log.d(MainActivity.LOG, "Start game fragment");
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

    public void initView() {
        button1 = view.findViewById(R.id.button_answer_1);
        button2 = view.findViewById(R.id.button_answer_2);
        button3 = view.findViewById(R.id.button_answer_3);
        button4 = view.findViewById(R.id.button_answer_4);
        questionView = view.findViewById(R.id.question);
        progressBar = view.findViewById(R.id.progressBar_time);
        image = view.findViewById(R.id.image);

        button1.setOnClickListener(v ->
        {
            answerClick(button1.getText() + "");
        });

        button2.setOnClickListener(v ->
        {
            answerClick(button2.getText() + "");
        });

        button3.setOnClickListener(v ->
        {
            answerClick(button3.getText() + "");
        });

        button4.setOnClickListener(v ->
        {
            answerClick(button4.getText() + "");
        });
    }

    public void answerClick(String answer) {
        setEnabledButton(false);
        presenter.clickAnswer(answer);
    }

    @Override
    public void setImage(Uri photoUrl) {
        if (photoUrl != null)
            Glide
                    .with(context)
                    .load(photoUrl)
                    .into(image);
        else
            image.setImageDrawable(null);
    }

    @Override
    public void setQuestion(String question, String[] mas, Uri photoUrl) {
        if (photoUrl != null)
            Glide
                    .with(context)
                    .load(photoUrl)
                    //.error(getResources().getDrawable(R.drawable.enot))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            questionView.setText(question);
                            button1.setText(mas[0]);
                            button2.setText(mas[1]);
                            button3.setText(mas[2]);
                            button4.setText(mas[3]);
                            setEnabledButton(true);
                            presenter.startTimer();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            image.setImageDrawable(null);
                            questionView.setText(question);
                            button1.setText(mas[0]);
                            button2.setText(mas[1]);
                            button3.setText(mas[2]);
                            button4.setText(mas[3]);
                            setEnabledButton(true);
                            presenter.startTimer();
                            return false;
                        }
                    })
                    .into(image);
        else {
            image.setImageDrawable(null);
            questionView.setText(question);
            button1.setText(mas[0]);
            button2.setText(mas[1]);
            button3.setText(mas[2]);
            button4.setText(mas[3]);
            setEnabledButton(true);
            presenter.startTimer();
        }
    }

    @Override
    public void setAnswer(String[] mas) {
        button1.setText(mas[0]);
        button2.setText(mas[1]);
        button3.setText(mas[2]);
        button4.setText(mas[3]);
        setEnabledButton(true);
    }

    @Override
    public void setQuestion(String question) {
        questionView.setText(question);
    }

    @Override
    public void startResult(int right, int all, long time) {
         listener.startResult(right, all, time);
    }

    @Override
    public void updateTimer(long time) {
        progressBar.setProgress((int) time);
    }

    private void setEnabledButton(boolean f) {
        button1.setEnabled(f);
        button2.setEnabled(f);
        button3.setEnabled(f);
        button4.setEnabled(f);
    }
}
