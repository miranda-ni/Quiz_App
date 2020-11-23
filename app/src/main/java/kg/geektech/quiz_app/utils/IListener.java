package kg.geektech.quiz_app.utils;

public interface IListener {

    void onAnswerClick(int position, int selectedAnswerPos);
    void isAnsweredTrue(Boolean b);

}
