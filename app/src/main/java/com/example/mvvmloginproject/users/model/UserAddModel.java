package com.example.mvvmloginproject.users.model;

import com.example.mvvmloginproject.users.repository.UserAddRepository;

public class UserAddModel {

    public boolean success;
    public String message;

    public UserAddModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
