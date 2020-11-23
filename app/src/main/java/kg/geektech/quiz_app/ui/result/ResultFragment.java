package kg.geektech.quiz_app.ui.result;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kg.geektech.App;
import kg.geektech.quiz_app.R;
import kg.geektech.quiz_app.databinding.FragmentQuizBinding;
import kg.geektech.quiz_app.databinding.ResultFragmentBinding;
import kg.geektech.quiz_app.models.Quiz;
import kg.geektech.quiz_app.models.Result;
import kg.geektech.quiz_app.ui.history.HistoryFragment;
import kg.geektech.quiz_app.ui.main.MainActivity;
import kg.geektech.quiz_app.ui.main.MainFragment;
import kg.geektech.quiz_app.ui.quiz.QuizQuestionFragment;

public class ResultFragment extends Fragment {
    public static final String RESULT_MODEL = "result";
    Integer resultAmount;
    Integer resultCorrect, percentage;
    String resultDiff, category;
    ArrayList<Quiz> listSize;
    public ResultFragmentBinding resultFragmentBinding;


    private ResultViewModel mViewModel;





    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        resultFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.result_fragment, container, false);
        View v = resultFragmentBinding.getRoot();

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        getResult();
        finish();


    }

    public Fragment getResult() {
        mViewModel.getRes();
        if (getArguments() != null) {
            resultCorrect = getArguments().getInt(QuizQuestionFragment.RESULT_CORRECT);
            Log.e("ololo", "onActivityCreated: " + resultCorrect);
            resultDiff = getArguments().getString(QuizQuestionFragment.RESULT_DIFFICULTY);
            resultAmount = getArguments().getInt(QuizQuestionFragment.RESULT_AMOUNT_OF_QUESTIONS);
            Log.d("TAG", "getResult: " + resultAmount);

            resultFragmentBinding.resultCorrectAnswers.setText(String.valueOf(resultCorrect + "/" + resultAmount));
            resultFragmentBinding.resultDifficulty.setText(resultDiff);
            resultFragmentBinding.resultImage.setImageResource(R.drawable.result);
            resultFragmentBinding.resultPercentage.setText(String.valueOf(resultCorrect * 100 / resultAmount + " %"));
            percentage = resultCorrect * 100 / resultAmount;
        }

        return this;

    }

    public void finish() {
        resultFragmentBinding.finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(requireActivity(), "Result is saved ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }

        });
    }
}
