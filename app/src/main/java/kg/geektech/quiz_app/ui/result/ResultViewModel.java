package kg.geektech.quiz_app.ui.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import kg.geektech.App;
import kg.geektech.quiz_app.models.Result;

public class ResultViewModel extends ViewModel {


    MutableLiveData<List<Result>> result = new MutableLiveData<>();

    void getRes() {
        result.postValue(App.repository.getAll());

    }


}
