package com.example.mvvmloginproject.users.model;

public class UserListModel {
    public String idx;
    public String first_name;
    public String last_name;
    public String number;
    public String email;

    public UserListModel(String idx, String first_name,
                                       String last_name, String number, String email) {
        this.idx = idx;
        this.first_name = first_name;
        this.last_name = last_name;
        this.number = number;
        this.email = email;
    }
}
