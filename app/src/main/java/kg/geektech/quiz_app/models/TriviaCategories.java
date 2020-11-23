package kg.geektech.quiz_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TriviaCategories {

    private int id;
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TriviaCategories(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
