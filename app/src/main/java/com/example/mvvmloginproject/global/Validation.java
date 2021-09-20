package com.example.mvvmloginproject.global;

import android.widget.EditText;

public class Validation {

    public static boolean numberValidation(EditText editText) {
        String number = editText.getText().toString().trim();
        if (number.isEmpty()) {
            editText.setError("Enter Number");
            return false;
        } else if (number.length() < 10) {
            editText.setError("Enter Valid Number");
            return false;
        }
        return true;
    }

    public static boolean passwordValidation(EditText editText) {
        String password = editText.getText().toString().trim();
        if (password.isEmpty()) {
            editText.setError("Enter Password");
            return false;
        } else if (password.length() < 6) {
            editText.setError("Enter Valid Password");
            return false;
        }
        return true;
    }
}
