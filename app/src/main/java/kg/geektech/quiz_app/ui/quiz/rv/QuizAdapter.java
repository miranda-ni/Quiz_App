package kg.geektech.quiz_app.ui.quiz.rv;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.quiz_app.R;
import kg.geektech.quiz_app.databinding.ItemQuizBinding;
import kg.geektech.quiz_app.models.Quiz;
import kg.geektech.quiz_app.utils.IListener;

public class QuizAdapter extends RecyclerView.Adapter<QuizVH> {


    private ArrayList<Quiz> items = new ArrayList<>();

    ItemQuizBinding binding;
    IListener ilistener;

    public QuizAdapter(IListener ilistener) {
        this.ilistener = ilistener;
    }




    public void setData(List<Quiz> quiz) {
        items.clear();
        items.addAll(quiz);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public QuizVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Log.e("ololo", "onCreateViewHolder: layot");
        ItemQuizBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_quiz, parent, false);
        return new QuizVH(binding, ilistener);
    }



    @Override
    public void onBindViewHolder(@NonNull QuizVH holder, int position) {
        holder.bind(items.get(position), position);
        Log.e("ololo", "onBindViewHolder: " + items);
        holder.setiListener(ilistener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
