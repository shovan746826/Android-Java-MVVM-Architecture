package com.example.mvvmloginproject.splashScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmloginproject.R;
import com.example.mvvmloginproject.activityView.HomeActivity;
import com.example.mvvmloginproject.databinding.FragmentSplashScreenBinding;
import com.example.mvvmloginproject.global.SharedPreference;

import org.jetbrains.annotations.NotNull;


public class SplashScreenFragment extends Fragment {

     FragmentSplashScreenBinding splashScreenBinding;

    public SplashScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        splashScreenBinding = FragmentSplashScreenBinding.inflate(getLayoutInflater());

        View view= inflater.inflate(R.layout.fragment_splash_screen,container,false);

        SharedPreferences prefs = requireActivity().getSharedPreferences(SharedPreference.loginSP, Context.MODE_PRIVATE);
        String token = prefs.getString(SharedPreference.loginSP_token, null);
        new Handler().postDelayed(() -> {
            if (token!=null){
                startActivity(new Intent(requireActivity(), HomeActivity.class));
                requireActivity().finish();
            }else {
                Navigation.findNavController(view).navigate(R.id.splash_to_login);
            }
        },3000);

        return view;
    }
}