package kg.geektech.quiz_app.ui.history.rv;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

import kg.geektech.App;
import kg.geektech.quiz_app.databinding.ItemHistoryBinding;
import kg.geektech.quiz_app.models.Result;

public class HistoryVH extends RecyclerView.ViewHolder {
    ItemHistoryBinding itemHistoryBinding;


    public HistoryVH(ItemHistoryBinding binding) {
        super(binding.getRoot());
        this.itemHistoryBinding = binding;

    }





    public void bind(Result result) {
        if (result!=null)
        itemHistoryBinding.historyCategory.setText(result.getCategory());
        itemHistoryBinding.historyDiff.setText(result.getDifficulty());

        itemHistoryBinding.historyCorrectAnswers.setText(String.valueOf(result.getAmountOfCorAnswers()));
        Log.e("TAG", "history bind: "+ result.getAmountOfCorAnswers());


        itemHistoryBinding.historyDate.setText(String.valueOf(result.getCreateAt()));
        Log.e("TAG", "history bind: "+ result.getCreateAt());

        itemHistoryBinding.deleteHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.repository.deleteAll();

            }
        });



    }

}

  
