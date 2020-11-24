package kg.geektech.quiz_app.ui.history;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.App;
import kg.geektech.quiz_app.models.Result;

public class HistoryViewModel extends ViewModel {
    MutableLiveData<List<Result>> resultMutableLiveData = new MutableLiveData<>(new ArrayList<>());


    void getResult() {
        resultMutableLiveData.postValue(App.repository.getAll());
        Log.d("App.repo", "history view model get result: "+ App.repository.getAll());

    }

        void clearHistory(){
            App.repository.deleteAll();
        }
    }
