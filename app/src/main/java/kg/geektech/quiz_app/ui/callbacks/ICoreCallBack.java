package kg.geektech.quiz_app.ui.callbacks;

import java.util.List;

import kg.geektech.quiz_app.models.Quiz;

public interface ICoreCallBack<A> {

    void onSuccess(A result);

    void onFailure(Exception ex);


}