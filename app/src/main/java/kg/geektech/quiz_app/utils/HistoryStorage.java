package kg.geektech.quiz_app.utils;

import java.util.Date;
import java.util.List;

import kg.geektech.quiz_app.models.Result;
import kg.geektech.quiz_app.ui.dataLayer.local.QuizDao;

public class HistoryStorage implements IHistoryStorage {
    QuizDao quizDao;


    @Override
    public Result getQuizResult(int id) {

        return quizDao.getById(id);
    }

    @Override
    public Result getDate(Date date) {
        return quizDao.getByDate(date);
    }

    @Override
    public long saveQuizResult(Result result) {

        return 0;
    }

    @Override
    public List<Result> getAll() {
        return quizDao.getAll();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {
        quizDao.deleteAll();

    }
}
