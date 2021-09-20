package com.example.mvvmloginproject.global;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.mvvmloginproject.login.model.LoginRepositoryModel;

public class SharedPreference {
    Activity activity;

    public static String loginSP = "loginSP";
    public static String loginSP_first_name = "loginSP_first_name";
    public static String loginSP_last_name = "loginSP_last_name";
    public static String loginSP_email = "loginSP_email";
    public static String loginSP_number = "loginSP_number";
    public static String loginSP_token = "loginSP_token";


    public SharedPreference(Activity activity){
        this.activity = activity;
    }

    public void saveLoginSharedPreference(LoginRepositoryModel loginRepositoryModel){
        SharedPreferences.Editor editor = activity.getSharedPreferences(loginSP, Context.MODE_PRIVATE).edit();
        editor.putString(loginSP_first_name, loginRepositoryModel.user_first_name);
        editor.putString(loginSP_last_name, loginRepositoryModel.user_last_name);
        editor.putString(loginSP_email, loginRepositoryModel.user_email);
        editor.putString(loginSP_number, loginRepositoryModel.user_number);
        editor.putString(loginSP_token, loginRepositoryModel.token);
        editor.apply();
    }
}
