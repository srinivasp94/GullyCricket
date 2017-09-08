package sample.ramya.com.exampleapp.networkdata;

import android.telecom.Call;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import sample.ramya.com.exampleapp.pojoexample.Enemy;
import sample.ramya.com.exampleapp.pojoexample.Example;
import sample.ramya.com.exampleapp.pojoexample.Moviepojo;
import sample.ramya.com.exampleapp.pojoexample.PojoResponse;

/**
 * Created by elancer on 7/31/2017.
 */

public interface RetrofitObjectAPI {

    @GET("/game/game_stats.json")
    void getResponse(Callback<PojoResponse> responseCallback);

    @GET("/users/mobilesiri")
    void getlogindata(Callback<Example> exampleCallback);

    @GET("/fakher/Retrofit/movie_list.php/")
    void getMovie(Callback<Moviepojo> mMoviepojo);
}
