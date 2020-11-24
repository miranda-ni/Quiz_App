package kg.geektech.quiz_app.ui.quiz;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kg.geektech.App;
import kg.geektech.quiz_app.models.Quiz;
import kg.geektech.quiz_app.models.Result;
import kg.geektech.quiz_app.ui.callbacks.IRequestQuestions;

public class QuizViewModel extends ViewModel {
    private ArrayList<Quiz> received; // save the questions which receive
    private int  listSize; // save the questions which receive
    private String categoryFr;
    String diff;
    Date date;
    int increase = 0;
    int correctAnswersAmount;

    public MutableLiveData<Integer> currentQuestionPos = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Quiz>> itemsToPost = new MutableLiveData<>(); // in this list will be posted received questions
public MutableLiveData<Long>resultMutableLiveData=new MutableLiveData<>();


    void getQuestions(Integer amount, Integer category, String difficulty) {
        App.repository.getQuestions(amount, category, difficulty, new IRequestQuestions.QuestionCallback() {
            @Override
            public void onSuccess(List<Quiz> questions) {
                if (questions.size()>0&&questions!=null){
                received = (ArrayList<Quiz>) questions;
                listSize=questions.size();
                for (int i = 1; i <received.size(); i++) {
                    itemsToPost.postValue(received);
                    diff = questions.get(i).getDifficulty();
                    categoryFr = questions.get(i).getCategory();
                    Log.e("ololo", "onSuccess: " + diff);
                    Log.e("ololo", "onSuccess: " + categoryFr);
                    Log.e("ololo", "onSuccess: " + received);
                    Log.e("ololo", "onSuccess: " + categoryFr);
                } }else{
                }
            }
            @Override
            public void onFailure(Exception ex) {


            }
        });

    }



    int correctAns() {
        correctAnswersAmount = 0;
        for (int i = 0; i < received.size(); i++) {
            if (received.get(i).getSelectedAnswerPos() != null) {
                String correctAnswer = received.get(i).getCorrectAnswers();
                String selectedAnswer = received.get(i).getAnswers()
                        .get(received.get(i).getSelectedAnswerPos());
                if (correctAnswer.equals(selectedAnswer)) {
                    correctAnswersAmount++;
                }
            }
        }return  correctAnswersAmount;
    }





    void onAnswerClick(int pos, int selected) {
        Log.e("ololo", "onAnswerClick:  view model");

        if (received != null && pos >= 0) {
            if (received.get(pos).getSelectedAnswerPos() == null) {
                received.get(pos).setSelectedAnswerPos(selected);
                itemsToPost.setValue((ArrayList<Quiz>) received);
            }
            if (pos + 1 == received.size()) {
                finishQuiz();
            } else {
                currentQuestionPos.setValue(++increase);
            }
        }



    }

    void finishQuiz() {
        Log.d("APP.repo", "finishQuiz: "+ correctAns()+ date);
        @SuppressLint("SimpleDateFormat")
        Date date = new Date();
        DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(date);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
//        date = new Date();
        Log.e("ololo",
                "getDate: " + date);

        Result resultModel = new Result(categoryFr, diff, correctAns(), received, date );
      resultMutableLiveData.postValue(App.repository.saveQuizResult(resultModel));


    }


    void onSkip() {
        Log.e("TAG", " view model onSkip: ");
        currentQuestionPos.setValue(0);
        Integer currentPosition = currentQuestionPos.getValue();
        if (currentPosition != null) {
            onAnswerClick(currentQuestionPos.getValue(), -1);
        } else {
            finishQuiz();
        }
    }



}
















