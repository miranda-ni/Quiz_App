package kg.geektech.quiz_app.utils;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

import kg.geektech.quiz_app.models.Quiz;
import kg.geektech.quiz_app.models.Result;

public interface IHistoryStorage {

    List<Result> getAll();


    Result getQuizResult(int id);

    Result getDate(Date date);

    long saveQuizResult(Result result);

    void delete(int id);
    void deleteAll();

}
