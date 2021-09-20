package com.example.mvvmloginproject.home.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmloginproject.global.SharedPreference;
import com.example.mvvmloginproject.home.model.HomeDataModel;

public class HomeViewModel extends ViewModel {

    MutableLiveData<HomeDataModel>liveData;

    public HomeViewModel(){
        liveData = new MutableLiveData<>();
    }

    public LiveData<HomeDataModel> getHomeData(Activity activity){
        SharedPreferences prefs = activity.getSharedPreferences(SharedPreference.loginSP, Context.MODE_PRIVATE);
        liveData.setValue(new HomeDataModel(
                prefs.getString(SharedPreference.loginSP_first_name, null),
                prefs.getString(SharedPreference.loginSP_last_name, null),
                prefs.getString(SharedPreference.loginSP_email, null),
                prefs.getString(SharedPreference.loginSP_number, null)));
        return liveData;
    }
}
