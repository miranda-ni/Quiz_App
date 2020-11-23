package kg.geektech.quiz_app.ui.dataLayer.remote;

import android.util.Log;

import kg.geektech.quiz_app.models.Categories;
import kg.geektech.quiz_app.models.QuizResponse;
import kg.geektech.quiz_app.ui.callbacks.IRequestCategories;
import kg.geektech.quiz_app.ui.callbacks.IRequestQuestions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static android.content.ContentValues.TAG;

public class QuizApiClient  implements IRequestQuestions, IRequestCategories {

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        QuizApi client = retrofit.create(QuizApi.class);

    @Override
    public void getQuestions(Integer amount, Integer category, String difficulty, IRequestQuestions.QuestionCallback callback) {
        Call<QuizResponse>call=client.getQuestions(amount,category , difficulty);
        call.enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    callback.onSuccess(response.body().getResults());
                    Log.e(TAG, "onResponse: "+response.body());
                }else{
                    callback.onFailure(new Exception("response.body()==null)"));
                    Log.e(TAG, "onResponse: ", new Exception() );
                }

            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                callback.onFailure(new Exception(t));
                Log.e(TAG, "onFailure: ", t.fillInStackTrace() );

            }
        });

    }



    @Override
    public void getCategories(RequestCatCallback callback) {
        Call<Categories>call=client.getCategories();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new Exception("response.body()==null)"));
                }

            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                callback.onFailure(new Exception(t));

            }
        });


    }


    private interface QuizApi {
            @GET("api.php")
            Call<QuizResponse> getQuestions(
                    @Query("amount") Integer amount,
                    @Query("category") Integer category,
                    @Query("difficulty") String difficulty
            );

            @GET("api_category.php")
            Call<Categories>getCategories();
        }
}


