package kg.geektech.quiz_app.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kg.geektech.App;
import kg.geektech.quiz_app.models.Categories;
import kg.geektech.quiz_app.ui.callbacks.IRequestCategories;

public class MainViewModel extends ViewModel {

    public MutableLiveData<Categories> trivia = new MutableLiveData();
    public void getCategories() {

        App.iRequestCategories.getCategories(new IRequestCategories.RequestCatCallback() {
            @Override
            public void onSuccess(Categories categories) {

                trivia.setValue(categories);
            }

            @Override
            public void onFailure(Exception ex) {
                ex.getMessage();
                Log.e("TAG", "onFailure: " + ex.getMessage());
            }
        });
    }
}