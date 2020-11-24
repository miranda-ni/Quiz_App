package kg.geektech.quiz_app.ui.history;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.App;
import kg.geektech.quiz_app.R;
import kg.geektech.quiz_app.databinding.FragmentHistoryBinding;
import kg.geektech.quiz_app.databinding.ResultFragmentBinding;
import kg.geektech.quiz_app.models.Result;
import kg.geektech.quiz_app.ui.history.rv.HistoryAdapter;
import kg.geektech.quiz_app.ui.quiz.rv.QuizAdapter;

public class HistoryFragment extends Fragment {

    FragmentHistoryBinding  historyBinding;
    HistoryAdapter historyAdapter;
    HistoryViewModel mViewModel;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        historyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        historyBinding.setHisroryViewModel(mViewModel);
        return historyBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        historyBinding.rvHistory.setHasFixedSize(true);
        historyAdapter = new HistoryAdapter();
        historyBinding.rvHistory.setAdapter(historyAdapter);
        mViewModel.getResult();
        mViewModel.resultMutableLiveData.observe(requireActivity()
                , results -> historyAdapter.addData(results));


    }

    @Override
    public void onResume() {
        mViewModel.getResult();
        mViewModel.resultMutableLiveData.observe(requireActivity(), results -> historyAdapter.addData(results));
        super.onResume();
    }

}