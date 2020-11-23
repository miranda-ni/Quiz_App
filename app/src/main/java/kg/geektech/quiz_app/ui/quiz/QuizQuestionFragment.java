package kg.geektech.quiz_app.ui.quiz;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import java.util.ArrayList;
import kg.geektech.quiz_app.R;
import kg.geektech.quiz_app.databinding.FragmentQuizBinding;
import kg.geektech.quiz_app.models.Quiz;
import kg.geektech.quiz_app.ui.main.MainActivity;
import kg.geektech.quiz_app.ui.main.MainFragment;
import kg.geektech.quiz_app.ui.quiz.rv.QuizAdapter;
import kg.geektech.quiz_app.ui.result.ResultFragment;
import kg.geektech.quiz_app.utils.IListener;

public class QuizQuestionFragment extends Fragment implements IListener {

    public static final String RESULT_CORRECT = "correct";
    public static final String RESULT_DIFFICULTY = "difficulty";
    public static final String RESULT_AMOUNT_OF_QUESTIONS = "list";


    public FragmentQuizBinding fragmentQuizBinding;
    private QuizViewModel mViewModel;
    QuizAdapter quizAdapter;
    TextView category;
    Integer resultAmount, resultCategoryPos;
    String resultDiff, resultCategory;
    private ArrayList<Quiz> quiz = new ArrayList<>();
    RecyclerView rv;
    int amountOfCorrectAnswer;


    public static final String EXTRA_AMOUNT = "slider";
    public static final Integer EXTRA_CATEGORY = 9;
    public static final String EXTRA_DIFFICULTY = "difficulty";

    public static QuizQuestionFragment newInstance() {
        return new QuizQuestionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentQuizBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false);
        View v = fragmentQuizBinding.getRoot();
        fragmentQuizBinding.setQuizViewModel(mViewModel);
        return v;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        super.onActivityCreated(savedInstanceState);


        ((MainActivity) getActivity()).getBotNavView().setVisibility(View.INVISIBLE);

        rv = fragmentQuizBinding.quizRecyclerView;
        rv.setHasFixedSize(true);
        quizAdapter = new QuizAdapter(this);
        rv.setAdapter(quizAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv.setLayoutManager(linearLayoutManager);        // rv.setNestedScrollingEnabled(false);
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(rv);
        getQuestions();
        onSkip();

        fragmentQuizBinding.quizProgress.setProgress(0);
        fragmentQuizBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentQuizBinding.quizProgress.setProgress(fragmentQuizBinding.quizProgress.getProgress() - 1);
                fragmentQuizBinding.quizRecyclerView.scrollToPosition(fragmentQuizBinding.quizProgress.getProgress());
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            ((MainActivity) getActivity()).getBotNavView().setVisibility(View.GONE);
        }
    }

    public Fragment getQuestions() {
        Log.e("get question", "getQuestions: ");
        if (getArguments() != null) {
            resultAmount = getArguments().getInt(MainFragment.EXTRA_AMOUNT);
            fragmentQuizBinding.quizProgress.setMax(resultAmount);
            resultCategory = getArguments().getString(MainFragment.EXTRA_CATEGORY);
            resultCategoryPos = getArguments().getInt(MainFragment.EXTRA_CATEGORY_POS);

//                resultCategory = Integer.valueOf(getArguments().getString(MainFragment.EXTRA_CATEGORY));
            resultDiff = getArguments().getString(MainFragment.EXTRA_DIFFICULTY);
            category = fragmentQuizBinding.quizCategory;
            category.setText(String.valueOf(resultCategory));
            Log.e("ololo", "set text: " + resultCategory);

        }
        mViewModel.getQuestions(resultAmount, resultCategoryPos, resultDiff);

        ProgressBar progressBar = fragmentQuizBinding.progressBar;
        progressBar.setVisibility(ProgressBar.VISIBLE);


        mViewModel.itemsToPost.observeForever(quizzes -> {
            if (quizzes.size() > 0) {
                Log.e("item to post", "quizzez size " + quizzes.size());
                quiz = quizzes;
                resultAmount = quizzes.size();
                quizAdapter.setData(quizzes);
                progressBar.setVisibility(ProgressBar.INVISIBLE);
            } else {
                resultAmount = 1;

            }

        });
        mViewModel.currentQuestionPos.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                fragmentQuizBinding.quizQuestionAmount.setText(String.valueOf(integer + "/"+ resultAmount));

            }
        });

        return this;
    }


    @Override
    public void onAnswerClick(int position, int selectedAnswerPos) {
        mViewModel.onAnswerClick(position, selectedAnswerPos);
        Log.e("ololo", "onAnswerClickFR: " + position);


    }


    public void onSkip() {
        fragmentQuizBinding.quizSkip.setOnClickListener(v -> {
            if (fragmentQuizBinding.quizProgress.getProgress() < resultAmount) {
                fragmentQuizBinding.quizProgress.setProgress(fragmentQuizBinding.quizProgress.getProgress() + 1);
                fragmentQuizBinding.quizRecyclerView.scrollToPosition(fragmentQuizBinding.quizProgress.getProgress());
                mViewModel.onSkip();
                quizAdapter.notifyDataSetChanged();
            } else {
                ResultFragment resultFragment = new ResultFragment();
                Bundle result = new Bundle();
                Log.e("send result:  ", "" + result);

                result.putString(RESULT_DIFFICULTY, resultDiff);
                Log.e("ololo", "onSkip: " + resultDiff);

                result.putInt(RESULT_AMOUNT_OF_QUESTIONS, resultAmount);
                Log.d("TAG", "onSkip: put list " + quiz);

                result.putInt(RESULT_CORRECT, amountOfCorrectAnswer);
                Log.d("TAG", "onSkip: " + amountOfCorrectAnswer);

                resultFragment.setArguments(result);
                FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
                ft.addToBackStack(null).replace(R.id.container, resultFragment).commit();
                Log.e("bunle", "set arguments " + result);
//                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new ResultFragment()).commit();

            }
            if (fragmentQuizBinding.quizProgress.getProgress() == resultAmount) {
                fragmentQuizBinding.quizSkip.setText("Finish");
            }
        });


    }

    @Override
    public void isAnsweredTrue(Boolean b) {
        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                fragmentQuizBinding.quizProgress.setProgress(fragmentQuizBinding.quizProgress.getProgress() + 1);
                fragmentQuizBinding.quizRecyclerView.scrollToPosition(fragmentQuizBinding.quizProgress.getProgress());
            }
        }.start();

        if (b) {
            amountOfCorrectAnswer = mViewModel.correctAns();

            Log.e("answer", "isAnsweredTrue: " + amountOfCorrectAnswer);
        }


    }


}






