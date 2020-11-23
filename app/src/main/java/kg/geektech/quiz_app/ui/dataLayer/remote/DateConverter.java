package kg.geektech.quiz_app.ui.dataLayer.remote;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import kg.geektech.quiz_app.models.Quiz;

public class DateConverter {

    @TypeConverter
    public static Long toJsonDate(@Nullable Date date) {

        if (date == null)
            return null;

        return date.getTime();

    }

    @TypeConverter
    public Date fromJsonDate(@Nullable Long timestamp) {
        if (timestamp == null)
            return null;
        return new Date(timestamp);
    }

}
