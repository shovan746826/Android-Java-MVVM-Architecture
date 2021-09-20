package com.example.mvvmloginproject.login.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmloginproject.apiServices.APIServices;
import com.example.mvvmloginproject.login.model.LoginRepositoryModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

//    Application application;
    MutableLiveData<LoginRepositoryModel> mutableLiveData;

    public LoginRepository() {
        mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<LoginRepositoryModel> getLogin(String number, String password) {
        Log.e("LoginRepository-->", "Run");
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("mobile", number);
        jsonObject.put("password", password);
        jsonObject.put("imei", "");
        jsonObject.put("phone_brand", "");
        jsonObject.put("phone_os", "");
        jsonObject.put("os_version", "");
        jsonObject.put("device_id", "");
        jsonObject.put("fcm", "");

        APIServices.getApi("").login(jsonObject).enqueue(new Callback<ResponseBody>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        assert response.body() != null;
                        String res = response.body().string();
                        JSONObject jsonObject = new JSONObject(res);
                        boolean is_success = jsonObject.getBoolean("issuccess");
                        JSONObject jsonObjectPayload = jsonObject.getJSONObject("payload");
                        JSONObject jsonObjectUserData = jsonObjectPayload.getJSONObject("user_info");
                        String token = jsonObjectPayload.getString("token");
                        String user_first_name = jsonObjectUserData.getString("firstname");
                        String user_last_name = jsonObjectUserData.getString("lastname");
                        String user_email = jsonObjectUserData.getString("email");
                        String user_number = jsonObjectUserData.getString("mobile");
                        String user_reg_id = jsonObjectUserData.getString("idx");
                        boolean isparentcustomer = jsonObjectUserData.getBoolean("isparentcustomer");

                        mutableLiveData.postValue(new LoginRepositoryModel(is_success, user_first_name,
                                user_last_name, user_email, user_number, user_reg_id, isparentcustomer, token));

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        assert response.errorBody() != null;
                        String res = response.errorBody().string();
                        System.err.println("res-->" + res);
                        JSONObject jsonObject = new JSONObject(res);
                        boolean is_success = jsonObject.getBoolean("issuccess");
                        mutableLiveData.postValue(new LoginRepositoryModel(is_success));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
