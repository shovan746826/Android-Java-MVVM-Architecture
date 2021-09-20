package com.example.mvvmloginproject.apiServices;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIServices {
    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static synchronized Retrofit builder(String token) {
        AuthenticationInterceptor interceptor = new AuthenticationInterceptor(token);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);
        httpClient.addInterceptor(logging);
        return new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(API_URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public synchronized static Api getApi(String token) {
        return builder(token).create(Api.class);
    }
}