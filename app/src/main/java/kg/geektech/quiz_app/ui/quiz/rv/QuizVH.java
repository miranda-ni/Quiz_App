package kg.geektech.quiz_app.ui.quiz.rv;

import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import kg.geektech.quiz_app.R;
import kg.geektech.quiz_app.databinding.ItemHistoryBinding;
import kg.geektech.quiz_app.databinding.ItemQuizBinding;
import kg.geektech.quiz_app.models.Quiz;
import kg.geektech.quiz_app.utils.IListener;

public class QuizVH extends RecyclerView.ViewHolder {

    private ItemQuizBinding binding;
    public IListener iListener;
    private Quiz quizQuest;
    private int position;
    Boolean isClicked = false;

    ArrayList<String> answers = new ArrayList<>();


    public void setiListener(IListener iListener) {
        this.iListener = iListener;
    }

    public QuizVH(ItemQuizBinding binding, IListener iListener) {
        super(binding.getRoot());
        this.binding = binding;

        binding.Quiz1.setOnClickListener(v -> iListener.onAnswerClick(getAdapterPosition(), 0));
        binding.Quiz2.setOnClickListener(v -> iListener.onAnswerClick(getAdapterPosition(), 1));
        binding.Quiz3.setOnClickListener(v -> iListener.onAnswerClick(getAdapterPosition(), 2));
        binding.Quiz4.setOnClickListener(v -> iListener.onAnswerClick(getAdapterPosition(), 3));
        binding.quizYes.setOnClickListener(v -> iListener.onAnswerClick(getAdapterPosition(), 0));
        binding.quizNo.setOnClickListener(v -> iListener.onAnswerClick(getAdapterPosition(), 1));


    }


    public void enable(Boolean b) {
        ArrayList<Button> buttons = new ArrayList();
        buttons.add(binding.Quiz1);
        buttons.add(binding.Quiz2);
        buttons.add(binding.Quiz3);
        buttons.add(binding.Quiz4);
        buttons.add(binding.quizYes);
        buttons.add(binding.quizYes);
        for (Button but : buttons)
            but.setClickable(b);
    }

    public void bind(Quiz quiz, int pos) {
        clearHolder();
        binding.setQuiz(quiz);
        if (binding.getQuiz().getSelectedAnswerPos() != null) {
            enable(false);
        } else {
            enable(true);
        }


        this.position = pos;
        this.quizQuest = quiz;
//        answers.addAll(quiz.getIncorrectAnswers());
//        answers.add(quiz.getCorrectAnswers());
//        quiz.setAnswers(answers);
        binding.quizQuestion.setText(Html.fromHtml(quiz.getQuestion()));
        if (quiz.getType().equals("multiple")) {
            Log.e("ololo", "bind: quiz.getAnswers " + quiz.getAnswers());
            Log.e("ololo", "bind: correctAnswer" + quiz.getCorrectAnswers());
            Log.e("ololo", "bind: incorrect " + quiz.getIncorrectAnswers());

            binding.containerMultipleButtons.setVisibility(View.VISIBLE);
            binding.containerBooleanButtons.setVisibility(View.GONE);
            binding.Quiz1.setText(quiz.getAnswers().get(0));
            binding.Quiz2.setText(quiz.getAnswers().get(1));
            binding.Quiz3.setText(quiz.getAnswers().get(2));
            binding.Quiz4.setText(quiz.getAnswers().get(3));

        } else {
            binding.containerMultipleButtons.setVisibility(View.GONE);
            binding.containerBooleanButtons.setVisibility(View.VISIBLE);
            binding.quizYes.setText(quiz.getAnswers().get(0));
            binding.quizNo.setText(quiz.getAnswers().get(1));


        }
        clickByPos();


    }
    public void setRedBackground(Button... buttons){
        for (Button button : buttons){
            button.setBackgroundResource(R.drawable.button_skip);
            button.setTextColor(Color.WHITE);
        }
    }

    public void setGreenBackground(Button...buttons){
        for (Button button : buttons){
            button.setBackgroundResource(R.drawable.background_correct);
        }
    }

    public void clickByPos() {
        binding.Quiz4.setTextColor(Color.BLACK);

        if (binding.getQuiz().getSelectedAnswerPos() != null)
            switch (binding.getQuiz().getSelectedAnswerPos()) {
                case 0:
                    if (binding.getQuiz().getCorrectAnswers().equals(binding.getQuiz().getAnswers().get(0))) {
//                        binding.Quiz1.setBackgroundResource(R.drawable.background_correct);
//                        binding.Quiz1.setTextColor(Color.WHITE);
//                        binding.quizYes.setBackgroundResource(R.drawable.background_correct);
//                        binding.quizYes.setTextColor(Color.WHITE);
                        setGreenBackground(binding.Quiz1,binding.quizYes);
                        iListener.isAnsweredTrue(true);


                        Log.e("ololo", "onClick: correct " + getAdapterPosition());

                    } else {
                        correctAnswerByDefault();
//                        binding.Quiz1.setBackgroundResource(R.drawable.button_skip);
//                        binding.quizYes.setBackgroundResource(R.drawable.button_skip);
//                        binding.quizYes.setTextColor(Color.WHITE);
//                        binding.Quiz1.setTextColor(Color.WHITE);
                        setRedBackground(binding.Quiz1,binding.quizYes);
                        iListener.isAnsweredTrue(false);


                        Log.e("ololo", "onClick: incorrect " + getAdapterPosition());

                    }
                    break;
                case 1:

                    if (binding.getQuiz().getCorrectAnswers().equals(binding.getQuiz().getAnswers().get(1))) {
//                        binding.Quiz2.setBackgroundResource(R.drawable.background_correct);
//                        binding.Quiz2.setBackgroundResource(R.drawable.background_correct);
//                        binding.quizNo.setTextColor(Color.WHITE);
//                        binding.quizNo.setTextColor(Color.WHITE);
                        setGreenBackground(binding.Quiz2,binding.quizNo);
                        iListener.isAnsweredTrue(true);


                    } else {
                        correctAnswerByDefault();
//                        binding.Quiz2.setBackgroundResource(R.drawable.button_skip);
//                        binding.quizNo.setBackgroundResource(R.drawable.button_skip);
//                        binding.quizNo.setTextColor(Color.WHITE);
//                        binding.Quiz2.setTextColor(Color.WHITE);
                        setRedBackground(binding.Quiz2,binding.quizNo);
                        iListener.isAnsweredTrue(false);

                    }


                    break;
                case 2:

                    if (binding.getQuiz().getCorrectAnswers().equals(binding.getQuiz().getAnswers().get(2))) {
//                        binding.Quiz3.setBackgroundResource(R.drawable.background_correct);
//                        binding.Quiz3.setTextColor(Color.WHITE);
                        setGreenBackground(binding.Quiz3);
                        iListener.isAnsweredTrue(true);


                    } else {
                        correctAnswerByDefault();
//                        binding.Quiz3.setBackgroundResource(R.drawable.button_skip);
//                        binding.Quiz3.setTextColor(Color.WHITE);
                        setRedBackground(binding.Quiz3);
                        iListener.isAnsweredTrue(false);


                    }
                    break;
                case 3:
                    if (binding.getQuiz().getCorrectAnswers().equals(binding.getQuiz().getAnswers().get(3))) {
//                        binding.Quiz4.setBackgroundResource(R.drawable.background_correct);
//                        binding.Quiz4.setTextColor(Color.WHITE);
                        setGreenBackground(binding.Quiz4);
                        iListener.isAnsweredTrue(true);

                    } else {
                        correctAnswerByDefault();

//                        binding.Quiz4.setBackgroundResource(R.drawable.button_skip);
//                        binding.Quiz4.setTextColor(Color.WHITE);
                        setRedBackground(binding.Quiz4);
                        iListener.isAnsweredTrue(false);

                    }

                    break;

            }


    }


    void correctAnswerByDefault() {
        if (binding.getQuiz().getCorrectAnswers().equals(binding.getQuiz().getAnswers().get(0))) {
//            binding.Quiz1.setBackgroundResource(R.drawable.background_correct);
//            binding.quizYes.setBackgroundResource(R.drawable.background_correct);
//            binding.Quiz1.setTextColor(Color.WHITE);
//            binding.quizYes.setTextColor(Color.WHITE);
            setGreenBackground(binding.Quiz1,binding.quizYes);
        } else if
        (binding.getQuiz().getCorrectAnswers().equals(binding.getQuiz().getAnswers().get(1))) {
//            binding.Quiz2.setBackgroundResource(R.drawable.background_correct);
//            binding.Quiz2.setTextColor(Color.WHITE);
//            binding.quizNo.setBackgroundResource(R.drawable.background_correct);
//            binding.quizNo.setTextColor(Color.WHITE);
            setGreenBackground(binding.Quiz2,binding.quizNo);
        } else if
        (binding.getQuiz().getCorrectAnswers().equals(binding.getQuiz().getAnswers().get(2))) {
//            binding.Quiz3.setBackgroundResource(R.drawable.background_correct);
//            binding.Quiz3.setTextColor(Color.WHITE);
            setGreenBackground(binding.Quiz3);
        }
        else if
        (binding.getQuiz().getCorrectAnswers().equals(binding.getQuiz().getAnswers().get(3)))
//            binding.Quiz4.setBackgroundResource(R.drawable.background_correct);
//        binding.Quiz4.setTextColor(Color.WHITE);
            setGreenBackground(binding.Quiz4);
    }


    public void clearHolder() {
        binding.Quiz1.setBackgroundColor(Color.WHITE);
        binding.Quiz1.setTextColor(Color.BLACK);
        binding.Quiz2.setBackgroundColor(Color.WHITE);
        binding.Quiz2.setTextColor(Color.BLACK);
        binding.Quiz3.setBackgroundColor(Color.WHITE);
        binding.Quiz3.setTextColor(Color.BLACK);

        binding.Quiz4.setBackgroundColor(Color.WHITE);
        binding.Quiz4.setTextColor(Color.BLACK);
        binding.quizYes.setBackgroundColor(Color.WHITE);
        binding.quizYes.setTextColor(Color.BLACK);
        binding.quizNo.setBackgroundColor(Color.WHITE);
        binding.quizNo.setTextColor(Color.BLACK);


    }
}








