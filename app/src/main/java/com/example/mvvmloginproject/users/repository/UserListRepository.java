package com.example.mvvmloginproject.users.repository;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmloginproject.apiServices.APIServices;
import com.example.mvvmloginproject.global.SharedPreference;
import com.example.mvvmloginproject.users.model.UserListModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListRepository {

    MutableLiveData<ArrayList<UserListModel>> mutableLiveData;

    public UserListRepository() {
        mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<UserListModel>> getAllData(String token) {

        ArrayList<UserListModel> arrayList = new ArrayList<>();

        APIServices.getApi("Bearer " +token).mysubuserlist().enqueue(new Callback<ResponseBody>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        assert response.body() != null;
                        String res = response.body().string();
                        JSONObject jsonObject = new JSONObject(res);
                        JSONArray jsonArray = jsonObject.getJSONArray("payload");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonChildNode = jsonArray.getJSONObject(i);
                            String idx = jsonChildNode.getString("idx");
                            String email = jsonChildNode.getString("email");
                            String mobileno = jsonChildNode.getString("mobileno");
                            String firstname = jsonChildNode.getString("firstname");
                            String lastname = jsonChildNode.getString("lastname");

                            arrayList.add(new UserListModel(idx, firstname, lastname, mobileno, email));
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        assert response.errorBody() != null;
                        String res = response.errorBody().string();
                        System.err.println("res-->" + res);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                mutableLiveData.postValue(arrayList);
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
