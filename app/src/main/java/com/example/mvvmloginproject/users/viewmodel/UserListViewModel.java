package com.example.mvvmloginproject.users.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmloginproject.global.SharedPreference;
import com.example.mvvmloginproject.users.model.UserListModel;
import com.example.mvvmloginproject.users.repository.UserListRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserListViewModel extends AndroidViewModel {

    UserListRepository userListRepository;
    LiveData<ArrayList<UserListModel>> mutableLiveData;
    String token;

    public UserListViewModel(@NonNull @NotNull Application application) {
        super(application);
        userListRepository = new UserListRepository();
        mutableLiveData = new MutableLiveData<>();
        SharedPreferences prefs = application.getSharedPreferences(SharedPreference.loginSP, Context.MODE_PRIVATE);
        token = prefs.getString(SharedPreference.loginSP_token, null);
        getData();
    }

    public void getData(){
        mutableLiveData = userListRepository.getAllData(token);
    }

    public LiveData<ArrayList<UserListModel>> setData() {
        return mutableLiveData;
    }

}
