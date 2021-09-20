package com.example.mvvmloginproject.global;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class GlobalFunctions {

    Activity activity;
    ProgressDialog progressDialog;

    public GlobalFunctions(Activity activity) {
        this.activity = activity;
        progressDialog = new ProgressDialog(activity);
    }


    public void showProgressDialog() {
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading...");
        progressDialog.create();
        progressDialog.show();
    }

    public void showProgressDialogWithBody(String title, String message) {
        progressDialog.setCancelable(false);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.create();
        progressDialog.show();
    }

    public void cancelProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog.cancel();
        }
    }

    public void getFragmentBackStack(Fragment fragment, Activity activity, int frameLayoutId) {
        ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(frameLayoutId, fragment)
                .commit();
    }

    public void getFragment(Fragment fragment, Activity activity, int frameLayoutId) {
        ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(frameLayoutId, fragment)
                .commit();
    }

    public void getFragmentClean(Fragment fragment, Activity activity, int frameLayoutId) {
        FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(frameLayoutId, fragment);
        fragmentTransaction.commit();
    }
}
