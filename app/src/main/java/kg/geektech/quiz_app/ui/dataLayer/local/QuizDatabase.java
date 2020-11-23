package kg.geektech.quiz_app.ui.dataLayer.local;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import kg.geektech.quiz_app.models.Quiz;
import kg.geektech.quiz_app.models.Result;

@Database(entities = {Result.class}, version = 1)

public  abstract class QuizDatabase  extends RoomDatabase {

    public abstract QuizDao zzquizDao();


}
