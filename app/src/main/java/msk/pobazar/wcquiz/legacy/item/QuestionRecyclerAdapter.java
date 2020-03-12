package msk.pobazar.wcquiz.legacy.item;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import msk.pobazar.wcquiz.legacy.MainActivity;
import msk.pobazar.wcquiz.R;


public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.ViewHolder> {
    private final List<QuestionItemGame> questionItemList;
    private final Context context;
    private final LayoutInflater inflater;
    private boolean fl;

    public QuestionRecyclerAdapter(Context context, List<QuestionItemGame> questions, boolean fl) {
        this.questionItemList = questions;
        this.context = context;
        this.fl = fl;
        inflater = LayoutInflater.from(context);
        Log.d(MainActivity.LOG, "Constructor recycler adapter question");
    }

    @Override
    public int getItemCount() {
        return questionItemList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (fl)
            return new ViewHolder(inflater.inflate(R.layout.question_item2, parent, false));
        else
            return new ViewHolder(inflater.inflate(R.layout.question_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QuestionItem question = questionItemList.get(position);
        holder.questionView.setText(question.getQuestion());
        if (fl)
            holder.answerView.setText(question.getAnswerRight());
        Glide.with(context).load(question.getUrlImage()).into(holder.imageView);
        if (((QuestionItemGame) question).isRight()) {
            holder.cardView.setCardBackgroundColor(0xFF37d81b);
        } else {
            holder.cardView.setCardBackgroundColor(0xFFd81b28);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView questionView;
        private final TextView answerView;
        private final CardView cardView;

        private ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_recycler);
            questionView = itemView.findViewById(R.id.question_recycler);
            cardView = itemView.findViewById(R.id.card_answer);
            if (fl)
                answerView = itemView.findViewById(R.id.answer_recycler);
            else
                answerView = null;
        }
    }

}
