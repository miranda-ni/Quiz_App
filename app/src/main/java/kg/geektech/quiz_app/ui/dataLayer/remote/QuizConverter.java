package kg.geektech.quiz_app.ui.dataLayer.remote;

import android.util.JsonReader;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import kg.geektech.quiz_app.models.Quiz;
public class QuizConverter {


@TypeConverter
    public static String toJsonQuiz (@Nullable  ArrayList<Quiz>quizQuestion){

        if (quizQuestion==null)
            return null;

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Quiz>>(){}.getType();

        return gson.toJson(quizQuestion,type);

   }


    @TypeConverter
    public static ArrayList<Quiz> fromJsonQuiz(@Nullable  String json){

        if (json==null)
            return null;

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Quiz>>(){}.getType();

        return gson.fromJson(json,type);

    }


}
