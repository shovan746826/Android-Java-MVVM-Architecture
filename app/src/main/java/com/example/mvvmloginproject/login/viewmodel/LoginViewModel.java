package com.example.mvvmloginproject.login.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmloginproject.activityView.HomeActivity;
import com.example.mvvmloginproject.global.SharedPreference;
import com.example.mvvmloginproject.login.model.LoginRepositoryModel;
import com.example.mvvmloginproject.login.repository.LoginRepository;

public class LoginViewModel extends ViewModel {

    LoginRepository loginRepository;

    public LoginViewModel() {
        loginRepository = new LoginRepository();
    }

    public LiveData<LoginRepositoryModel> getLoginData(String number, String password){
        Log.e("getLoginData-->","Run");
        return loginRepository.getLogin(number,password);
    }

    public void intentActivity(Activity activity, LoginRepositoryModel loginRepositoryModel){
        new SharedPreference(activity).saveLoginSharedPreference(loginRepositoryModel);
        activity.startActivity(new Intent(activity, HomeActivity.class));
        activity.finish();
    }

}
