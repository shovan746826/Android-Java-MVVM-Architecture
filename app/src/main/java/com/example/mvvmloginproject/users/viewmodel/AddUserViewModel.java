package com.example.mvvmloginproject.users.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmloginproject.global.SharedPreference;
import com.example.mvvmloginproject.users.model.UserAddModel;
import com.example.mvvmloginproject.users.repository.UserAddRepository;

import org.jetbrains.annotations.NotNull;

public class AddUserViewModel extends AndroidViewModel {

    MutableLiveData<UserAddModel>mutableLiveData;
    UserAddRepository userAddRepository;
    String token;

    public AddUserViewModel(@NonNull @NotNull Application application) {
        super(application);
        SharedPreferences prefs = application.getSharedPreferences(SharedPreference.loginSP, Context.MODE_PRIVATE);
        token = prefs.getString(SharedPreference.loginSP_token, null);
        userAddRepository = new UserAddRepository();
        mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<UserAddModel> addUser(String first_name, String last_name, String number, String email,int sub_user_id){
        mutableLiveData =  userAddRepository.addSubUser(first_name,last_name,number,email,sub_user_id,token);
        return mutableLiveData;
    }


}
