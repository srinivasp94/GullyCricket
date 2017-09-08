package sample.ramya.com.exampleapp.application;

import android.app.Application;
import android.app.ProgressDialog;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import sample.ramya.com.exampleapp.constants.Constants;
import sample.ramya.com.exampleapp.constants.ServiceUrls;
import sample.ramya.com.exampleapp.network.RetrofitAPI;

/**
 * Created by user on 29-06-2016.
 */
public class TATX extends Application
{


    private static TATX instance;
    private RetrofitAPI retrofitAPI;
    private int requestId;



    private ProgressDialog progressDialog;

    @Override
    public void onCreate()
    {


        super.onCreate();

        instance = this;

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(Constants.CONNECTION_TIME_OUT, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(Constants.READ_TIME_OUT, TimeUnit.SECONDS);
        okHttpClient.setRetryOnConnectionFailure(true);
        okHttpClient.setWriteTimeout(Constants.WRITE_TIME_OUT, TimeUnit.SECONDS);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(ServiceUrls.CURRENT_ENVIRONMENT.getApiUrl())
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .setLog(new AndroidLog("TATX"))
                .build();


        retrofitAPI = restAdapter.create(RetrofitAPI.class);

    }





    public static TATX getInstance()
    {
        return instance;
    }

    public RetrofitAPI getRetrofitAPI()
    {
        return retrofitAPI;
    }





}
