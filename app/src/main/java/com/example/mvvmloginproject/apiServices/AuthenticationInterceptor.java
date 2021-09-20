package com.example.mvvmloginproject.apiServices;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {
    private final String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder()
                .header("Module", "JW9tc0ByZWRsdGQl")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", authToken);
        Request request = builder.build();
        return chain.proceed(request);
    }
}
