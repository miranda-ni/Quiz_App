package kg.geektech.quiz_app.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizResponse {

    @SerializedName("response_code")

    private int responseCode;

    private List<Quiz> results;


    //region Static
    public QuizResponse(int responseCode, List<Quiz> results) {
        this.responseCode = responseCode;
        this.results = results;
    }


    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Quiz> getResults() {
        return results;
    }

    public void setResults(List<Quiz> results) {
        this.results = results;
    }
//endregion
}
