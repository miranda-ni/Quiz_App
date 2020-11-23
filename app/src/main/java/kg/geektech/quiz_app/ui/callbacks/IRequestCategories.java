package kg.geektech.quiz_app.ui.callbacks;

import kg.geektech.quiz_app.models.Categories;

public interface IRequestCategories {

    void getCategories(IRequestCategories.RequestCatCallback callback);


    interface  RequestCatCallback extends ICoreCallBack <Categories> {
        @Override
        void onSuccess(Categories categories);

        @Override
        void onFailure(Exception ex);

    }
}
