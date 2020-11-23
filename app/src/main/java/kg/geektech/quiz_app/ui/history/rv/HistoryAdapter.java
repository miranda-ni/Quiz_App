package kg.geektech.quiz_app.ui.history.rv;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.quiz_app.R;
import kg.geektech.quiz_app.models.Result;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryVH> {


    private List<Result> results = new ArrayList<>();


    @NonNull
    @Override
    public HistoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryVH(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()), R.layout.item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryVH holder, int position) {
        holder.bind(results.get(position));

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void addData(List<Result> result) {
        results = result;
        notifyDataSetChanged();
    }
}



