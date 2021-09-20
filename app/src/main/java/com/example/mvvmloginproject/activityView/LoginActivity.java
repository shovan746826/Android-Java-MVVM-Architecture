package com.example.mvvmloginproject.activityView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mvvmloginproject.R;

public class LoginActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_login);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}