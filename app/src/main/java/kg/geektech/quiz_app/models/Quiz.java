package kg.geektech.quiz_app.models;

import androidx.annotation.IntegerRes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private String category;
    @SerializedName("type")
    private String type;
    private String difficulty;
    private String question;

    @SerializedName("multiple")
    private String multiple;


    @SerializedName("correct_answer")
    private String correctAnswers;

    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswers;

    public List<String> answers;
    public void setAnswers(List<String> answers) {

        this.answers = answers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public Quiz(List<String> answers) {
        this.answers = answers;
    }

    private Integer selectedAnswerPos;

    private boolean isAnswered;







//region static

    public Quiz(String category, String type, String difficulty, String question, String multiple, String correctAnswers, List<String> incorrectAnswers, List<String> answers, Integer selectedAnswerPos, boolean isAnswered) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.multiple = multiple;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.selectedAnswerPos = selectedAnswerPos;
        this.isAnswered = isAnswered;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }


    public void setSelectedAnswerPos(Integer selectedAnswerPos) {
        this.selectedAnswerPos = selectedAnswerPos;
    }


    public Integer getSelectedAnswerPos() {
        return selectedAnswerPos;
    }

    public void setSelectedAnswerPos(int selectedAnswerPos) {
        this.selectedAnswerPos = selectedAnswerPos;
    }


    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    @Override
    public String toString() {
        return "QuizAction {" + "type =" + type + ", category" + category;
    }

//endregion
}
