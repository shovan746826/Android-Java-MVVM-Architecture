package com.example.mvvmloginproject.login.model;

public class LoginRepositoryModel {
    public boolean is_success;
    public String user_first_name;
    public String user_last_name;
    public String user_email;
    public String user_number;
    public String user_reg_id;
    boolean is_parent_customer;
    public String token;

    public LoginRepositoryModel(boolean is_success){
        this.is_success = is_success;
    }

    public LoginRepositoryModel(boolean is_success, String user_first_name, String user_last_name,
                                String user_email, String user_number, String user_reg_id,
                                boolean is_parent_customer, String token) {
        this.is_success = is_success;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.user_email = user_email;
        this.user_number = user_number;
        this.user_reg_id = user_reg_id;
        this.is_parent_customer = is_parent_customer;
        this.token = token;
    }
}
