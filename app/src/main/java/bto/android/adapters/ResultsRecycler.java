package bto.android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import bto.android.R;

/**
 * @author @iaindownie on 04/08/2025.
 */

public class ResultsRecycler extends RecyclerView.Adapter<ResultsRecycler.MyViewHolder> {

    private List<String> results;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView result;

        public MyViewHolder(View view) {
            super(view);
            result = view.findViewById(R.id.resultsText);
        }
    }

    public ResultsRecycler(List<String> results) {
        this.results = results;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.results_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String aResult = results.get(holder.getAdapterPosition());
        holder.result.setText(aResult);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

}
