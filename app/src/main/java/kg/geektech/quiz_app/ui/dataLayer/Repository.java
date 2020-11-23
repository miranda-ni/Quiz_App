package kg.geektech.quiz_app.ui.dataLayer;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import kg.geektech.App;
import kg.geektech.quiz_app.models.Quiz;
import kg.geektech.quiz_app.models.Result;
import kg.geektech.quiz_app.utils.IHistoryStorage;
import kg.geektech.quiz_app.ui.callbacks.IRequestQuestions;

public class Repository implements IHistoryStorage, IRequestQuestions {
    IHistoryStorage iHistoryStorage;
    IRequestQuestions iRequestQuestions;

    public Repository(IHistoryStorage iHistoryStorage, IRequestQuestions iRequestQuestions) {
        this.iHistoryStorage = iHistoryStorage;
        this.iRequestQuestions = iRequestQuestions;
    }

    @Override
    public List<Result> getAll() {
        Log.d("Repo", " Repo getAll: "+ App.quizDatabase.zzquizDao().getAll());
        return App.quizDatabase.zzquizDao().getAll();
    }


    @Override
    public Result getQuizResult(int id) {
        return App.quizDatabase.zzquizDao().getById(id);
    }

    @Override
    public Result getDate(Date date) {
        Log.d("Repo", "getDate: "+ App.quizDatabase.zzquizDao().getByDate(date));

        return App.quizDatabase.zzquizDao().getByDate(date);
    }

    @Override
    public long saveQuizResult(Result result) {
        Log.e("Repo", "saveQuizResult: " + result.toString());
        return  App.quizDatabase.zzquizDao().insert(result);

    }


    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {
        App.quizDatabase.zzquizDao().deleteAll();

    }

    @Override
    public void getQuestions(Integer amount, Integer category,
                             String difficulty,
                             QuestionCallback callback) {
        iRequestQuestions.getQuestions(amount, category, difficulty, new IRequestQuestions.QuestionCallback() {


            @Override
            public void onSuccess(List<Quiz> quiz) {
                for (int i = 0; i < quiz.size(); i++) {
                    quiz.set(i, shuffleQuestion(quiz.get(i)));

                }
                callback.onSuccess(quiz);
            }


            @Override
            public void onFailure(Exception ex) {
                ex.getMessage();

            }
        });

    }

    public Quiz shuffleQuestion(Quiz quiz) {
        List<String> answers = new ArrayList<>();
        answers.add(quiz.getCorrectAnswers());
        answers.addAll(quiz.getIncorrectAnswers());
        Collections.shuffle(answers);
        quiz.setAnswers(answers);
        return quiz;
    }


}
