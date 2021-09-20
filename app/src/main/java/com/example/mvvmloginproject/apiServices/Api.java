package com.example.mvvmloginproject.apiServices;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @POST("api/auth/login")
    Call<ResponseBody> login(@Body Map jsonObject);

    @GET("api/customer/mysubuserlist")
    Call<ResponseBody> mysubuserlist();
    @POST("api/customer/addsubuser")
    Call<ResponseBody> addsubuser(@Body Map jsonObject);

}
