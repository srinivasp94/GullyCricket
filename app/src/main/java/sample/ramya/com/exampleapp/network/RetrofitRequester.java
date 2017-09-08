package sample.ramya.com.exampleapp.network;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.google.gson.Gson;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;
import sample.ramya.com.exampleapp.HomeActivity;
import sample.ramya.com.exampleapp.application.TATX;
import sample.ramya.com.exampleapp.commonutills.Common;
import sample.ramya.com.exampleapp.constants.Constants;
import sample.ramya.com.exampleapp.pojo.ApiResponseVo;
import sample.ramya.com.exampleapp.pojo.CommonRequestKey;
import sample.ramya.com.exampleapp.pojo.CommonRequestPojo;
import sample.ramya.com.exampleapp.pojo.CommonResponsePojo;
import sample.ramya.com.exampleapp.pojo.ProductResultVo;
import sample.ramya.com.exampleapp.pojo.RegistrationKeyVo;
import sample.ramya.com.exampleapp.pojo.RegistrationResponseVo;


public class RetrofitRequester {
    private final RetrofitResponseListener retrofitResponseListener;
    private Activity activity;
    private Context context;
    private ProgressDialog progressDialog;

    public RetrofitRequester(RetrofitResponseListener retrofitResponseListener) {

       this.retrofitResponseListener = retrofitResponseListener;

        if(retrofitResponseListener instanceof Activity)
        {
        this.context = (Context) retrofitResponseListener;
            this.activity = (Activity) retrofitResponseListener;
        }
        else if(retrofitResponseListener instanceof Fragment)
        {
            this.context = ((Fragment) retrofitResponseListener).getActivity();
            this.activity = ((Fragment) retrofitResponseListener).getActivity();

        }
        else if(retrofitResponseListener instanceof android.support.v4.app.Fragment)
        {
            this.context = ((android.support.v4.app.Fragment) retrofitResponseListener).getActivity();
            this.activity = ((android.support.v4.app.Fragment) retrofitResponseListener).getActivity();

        }else if(retrofitResponseListener instanceof WakefulBroadcastReceiver)
        {
            this.context =  (Context) retrofitResponseListener;
            this.activity = (Activity) retrofitResponseListener;

        }

    }



    public void sendStringRequest(String requestName, HashMap<String, String> requestParams) {

        sendStringRequest(requestName, requestParams, -1);

    }



    public void sendStringRequest(String requestName, final HashMap<String, String> requestParams, final int requestId, boolean showProgressDialog)
    {


        if (requestId == Constants.RequestCodes.ONCREATE_REQUEST_CODE) {
            Common.showContentView((Activity) context, false);
        }

        if (!Common.haveInternet(context)) {

            Common.customToast(context, Constants.INTERNET_UNABLEABLE);

            return;

        }



        if (showProgressDialog) {

            progressDialog = Common.showProgressDialog(context);

        }


        CommonRequestPojo commonRequestPojo = new CommonRequestPojo();
       // commonRequestPojo.re(String.valueOf(Common.getUserIdFromSP(context)));
        commonRequestPojo.setRequestname(requestName);
        commonRequestPojo.setRequestparameters(new Gson().toJson(requestParams));
        Common.Log.i("commonRequestPojo.toString() : " + commonRequestPojo.toString());

        Common.Log.i("? - API Request : "+new Gson().toJson(commonRequestPojo));




        TATX.getInstance().getRetrofitAPI().sendAllPostRequests(commonRequestPojo, new Callback<CommonResponsePojo>() {
            @Override
            public void success(CommonResponsePojo commonResponsePojo, Response response) {
                Common.dismissProgressDialog(progressDialog);

                Common.Log.i("Inside Retrofit Success of " + getClass().getSimpleName());

                Common.Log.i("? - API Response : " + new Gson().toJson(commonResponsePojo));

                Common.Log.i("response : " + response);

                retrofitResponseListener.onResponseSuccess(commonResponsePojo, requestParams, requestId);

                if (requestId == Constants.RequestCodes.ONCREATE_REQUEST_CODE) {
                    Common.showContentView((Activity) context, true);
                }



            }

            @Override
            public void failure(RetrofitError error) {

                error.printStackTrace();

                Common.Log.i("error.toString() : " + error.toString());

                Common.dismissProgressDialog(progressDialog);


            }

        });








    }


    public void sendStringRequest(String requestName, HashMap<String, String> requestParams, boolean showProgressDialog) {

        sendStringRequest(requestName, requestParams, -1, showProgressDialog);

    }


    public void sendStringRequest(String requestName, HashMap<String, String> requestParams, final int requestId) {
        sendStringRequest(requestName, requestParams, requestId, true);
    }


/*
    public void sendMultiPartRequest(String requestName, final HashMap<String, String> requestParams, HashMap<String, TypedFile> filesHashMap)
    {

        if (!Common.haveInternet(context))
        {

            Common.customToast(context, Constants.INTERNET_UNABLEABLE);

            return;

        }


        progressDialog = Common.showProgressDialog(context);




        CommonRequestKey commonRequestKey = new CommonRequestKey();
        commonRequestKey.setRequesterid(String.valueOf(Common.getUserIdFromSP(context)));
        commonRequestKey.setRequestname(requestName);
        commonRequestKey.setRequestparameters(new Gson().toJson(requestParams));

        Common.Log.i("? - Mupltipart Request : "+new Gson().toJson(commonRequestKey));




        TATX.getInstance().getRetrofitAPI().uploadImages( "1", commonRequestKey,filesHashMap, new Callback<ApiResponseVo>() {
            @Override
            public void success(ApiResponseVo apiResponseVo, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();

                Common.Log.i("error.toString() : " + error.toString());

                Common.dismissProgressDialog(progressDialog);

            }
        });





    }
*/



/*
    public void getProductListAPI(){

        Common.Log.i("productResultVo");

        TATX.getInstance().getRetrofitAPI().getProductList(new Callback<ProductResultVo>() {
            @Override
            public void success(ProductResultVo productResultVo, Response response) {
                Common.Log.i("productResultVo"+productResultVo);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }*/

/*public void registrationAPI(RegistrationKeyVo registrationKeyVo){
    TATX.getInstance().getRetrofitAPI().registrationApi(registrationKeyVo, new Callback<RegistrationResponseVo>() {
        @Override
        public void success(RegistrationResponseVo registrationResponseVo, Response response) {
            Common.Log.i("productResultVo"+registrationResponseVo);

        }

        @Override
        public void failure(RetrofitError error) {

        }
    });


}*/

}
