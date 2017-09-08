package sample.ramya.com.exampleapp.network;


import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.PartMap;
import retrofit.mime.TypedFile;
import sample.ramya.com.exampleapp.pojo.ApiResponseVo;
import sample.ramya.com.exampleapp.pojo.CommonRequestKey;
import sample.ramya.com.exampleapp.pojo.CommonRequestPojo;
import sample.ramya.com.exampleapp.pojo.CommonResponsePojo;
import sample.ramya.com.exampleapp.pojo.ProductResultVo;
import sample.ramya.com.exampleapp.pojo.RegistrationKeyVo;
import sample.ramya.com.exampleapp.pojo.RegistrationResponseVo;


public interface RetrofitAPI {
    /*@Headers("Cache-Control: no-cache")
    @POST("/api?api_token=tatx123")
    void callApiService(@Body CommonRequestKey login, Callback<ApiResponseVo> response);

    @Multipart
    @POST("/api?api_token=tatx123")
    void uploadImages(
            @Part("multipart") String multipart,
            @Part("requestparameters") CommonRequestKey requestparameters,
            @PartMap Map<String, TypedFile> files,
            Callback<ApiResponseVo> response);

    @GET("/products")
    void getProductList(Callback<ProductResultVo> response);

    @POST("/usersignup")
    void registrationApi(@Body RegistrationKeyVo registrationKeyVo, Callback<RegistrationResponseVo> registrationResponseVoCallback);
*/
    @Headers("Authorization:Basic cmVzdDpnb2thbEBhZG1pbg==")
    @POST("/api")
    void sendAllPostRequests(@Body CommonRequestPojo commonRequestPojo, Callback<CommonResponsePojo> commonResponsePojo);


}
