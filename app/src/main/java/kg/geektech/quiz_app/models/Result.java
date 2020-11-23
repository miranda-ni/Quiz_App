package kg.geektech.quiz_app.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kg.geektech.quiz_app.ui.dataLayer.remote.DateConverter;
import kg.geektech.quiz_app.ui.dataLayer.remote.QuizConverter;

@Entity(tableName = "result")

public class Result {

    @PrimaryKey(autoGenerate = true)

    int id;
    @ColumnInfo(name = "category")
    String category;

    @ColumnInfo(name = "difficulty")
    String difficulty;

    @ColumnInfo(name = "amountOfCorAnswers")
    int amountOfCorAnswers;

    @TypeConverters({QuizConverter.class})
    private ArrayList<Quiz> quiz;

    @TypeConverters({DateConverter.class})
    Date createAt;


    public Result(String category, String difficulty, int amountOfCorAnswers, ArrayList<Quiz> quiz, Date createAt) {
        this.category = category;
        this.difficulty = difficulty;
        this.amountOfCorAnswers = amountOfCorAnswers;
        this.quiz = quiz;
        this.createAt = createAt;
    }

    public void setQuiz(ArrayList<Quiz> quiz) {
        this.quiz = quiz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Quiz> getQuiz() {
        return quiz;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getAmountOfCorAnswers() {
        return amountOfCorAnswers;
    }

    public void setAmountOfCorAnswers(int amountOfCorAnswers) {
        this.amountOfCorAnswers = amountOfCorAnswers;
    }
}
