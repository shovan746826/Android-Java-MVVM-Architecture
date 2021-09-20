package com.example.mvvmloginproject.activityView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.example.mvvmloginproject.R;

public class HomeActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_home);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}