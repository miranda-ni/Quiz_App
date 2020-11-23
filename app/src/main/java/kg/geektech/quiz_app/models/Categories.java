package kg.geektech.quiz_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {

    @SerializedName("trivia_categories")
    @Expose
    private List<TriviaCategories> triviaCategories;

    public List<TriviaCategories> getTriviaCategories() {
        return triviaCategories;
    }

    public void setTriviaCategories(List<TriviaCategories> triviaCategories) {
        this.triviaCategories = triviaCategories;
    }

    public Categories(List<TriviaCategories> triviaCategories) {
        this.triviaCategories = triviaCategories;
    }
}
