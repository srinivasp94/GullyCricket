package sample.ramya.com.exampleapp.network;



import java.util.HashMap;

import sample.ramya.com.exampleapp.pojo.ApiResponseVo;
import sample.ramya.com.exampleapp.pojo.CommonResponsePojo;

public interface RetrofitResponseListener
{


    void onResponseSuccess(CommonResponsePojo commonResponsePojo, HashMap<String, String> requestParams, int requestId);



}
