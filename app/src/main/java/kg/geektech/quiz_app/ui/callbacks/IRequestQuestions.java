package kg.geektech.quiz_app.ui.callbacks;

import java.util.List;

import kg.geektech.quiz_app.models.Quiz;

public interface IRequestQuestions {

    void getQuestions(Integer amount, Integer category, String difficulty,
                      QuestionCallback callback);




    interface  QuestionCallback extends ICoreCallBack<List<Quiz>> {
        @Override
        void onSuccess(List<Quiz> quiz);

        @Override
        void onFailure(Exception ex);
    }
   }