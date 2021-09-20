package com.example.mvvmloginproject.users.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmloginproject.apiServices.APIServices;
import com.example.mvvmloginproject.users.model.UserAddModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAddRepository {

    MutableLiveData<UserAddModel> mutableLiveData;

    public UserAddRepository(){

        mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<UserAddModel> addSubUser(String first_name, String last_name,
                                                    String number, String email,int sub_user_id, String token) {

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("mobile", number);
        jsonObject.put("email", email);
        jsonObject.put("firstname", first_name);
        jsonObject.put("lastname", last_name);
        jsonObject.put("subusertype", sub_user_id);

        APIServices.getApi("Bearer "+token).addsubuser(jsonObject).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                String message;
                boolean success;
                if (response.isSuccessful()) {
                    try {
                        assert response.body() != null;
                        String res = response.body().string();
                        System.err.println("res-->" + res);
                        JSONObject jsonObject = new JSONObject(res);
                        success = jsonObject.getBoolean("issuccess");
                        message = jsonObject.getString("message");

                        mutableLiveData.postValue(new UserAddModel(success,message));

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        assert response.errorBody() != null;
                        String res = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(res);
                        System.err.println("res-->" + res);
                        success = jsonObject.getBoolean("issuccess");
                        message = jsonObject.getString("message");

                        mutableLiveData.postValue(new UserAddModel(success,message));

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
