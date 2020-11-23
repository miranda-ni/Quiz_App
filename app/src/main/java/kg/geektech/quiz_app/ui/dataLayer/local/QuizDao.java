package kg.geektech.quiz_app.ui.dataLayer.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.List;

import kg.geektech.quiz_app.models.Result;
import kg.geektech.quiz_app.ui.dataLayer.remote.DateConverter;
import retrofit2.http.DELETE;

@Dao
public interface QuizDao {

    @Query("SELECT * FROM result")
    List<Result> getAll();

    @Insert
    long insert(Result result);

    @Query("SELECT *FROM RESULT WHERE id =:id")
    Result getById(int id);


    @Query("SELECT * FROM result WHERE createAt = :dateOfGame")
    Result getByDate(@TypeConverters({DateConverter.class}) Date dateOfGame);

    @Query("DELETE FROM  result")
    void deleteAll();
}


//    @DELETE
//    void delete (Result result);


//    @Query("SELECT * FROM result ")
//    LiveData <List<Result>>getAll;


