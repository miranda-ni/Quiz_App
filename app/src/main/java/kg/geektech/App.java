package kg.geektech;

import android.app.Application;

import androidx.room.Room;

import kg.geektech.quiz_app.utils.IHistoryStorage;
import kg.geektech.quiz_app.ui.callbacks.IRequestCategories;
import kg.geektech.quiz_app.ui.callbacks.IRequestQuestions;
import kg.geektech.quiz_app.ui.dataLayer.Repository;
import kg.geektech.quiz_app.ui.dataLayer.local.QuizDatabase;
import kg.geektech.quiz_app.ui.dataLayer.remote.QuizApiClient;
import kg.geektech.quiz_app.utils.HistoryStorage;

public class App extends Application {
    public static QuizApiClient quizInstance;
    public static IRequestCategories iRequestCategories;
    public static IRequestQuestions iRequestQuestions;
    public static IHistoryStorage iHistoryStorage;
    public static Repository repository;
    public static QuizDatabase quizDatabase;


    @Override
    public void onCreate() {
        super.onCreate();
        quizInstance = new QuizApiClient();
        quizDatabase = Room.databaseBuilder(this, QuizDatabase.class, "quiz.db").fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
        iRequestCategories = new QuizApiClient();
        iRequestQuestions = new QuizApiClient();
        iHistoryStorage = new HistoryStorage();
        repository = new Repository(iHistoryStorage, iRequestQuestions);


    }
}
