package msk.pobazar.wcquiz.item;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import msk.pobazar.wcquiz.MainActivity;
import msk.pobazar.wcquiz.R;


public class RecordRecyclerAdapter extends RecyclerView.Adapter<RecordRecyclerAdapter.ViewHolder> {
    private final List<RecordItem> recordItemList;
    private final Context context;
    private final LayoutInflater inflater;

    public RecordRecyclerAdapter(Context context, List<RecordItem> records) {
        this.recordItemList = records;
        this.context = context;
        inflater = LayoutInflater.from(context);
        Log.d(MainActivity.LOG, "Constructor recycler adapter record");
    }

    @Override
    public int getItemCount() {
        return recordItemList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.record_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecordItem record = recordItemList.get(position);
        holder.dateView.setText(record.getDate().toString());
        holder.scoreView.setText(record.getScore()+"");
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateView;
        private final TextView scoreView;
        private final LinearLayout linear;

        private ViewHolder(View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.text_date);
            scoreView = itemView.findViewById(R.id.text_score);
            linear = (LinearLayout) itemView.findViewById(R.id.layout_record);
        }
    }

}
