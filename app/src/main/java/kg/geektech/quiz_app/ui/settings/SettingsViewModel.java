package kg.geektech.quiz_app.ui.settings;

import androidx.lifecycle.ViewModel;

import kg.geektech.App;

public class SettingsViewModel extends ViewModel {

    void clearHistory(){
        App.repository.deleteAll();
    }
}