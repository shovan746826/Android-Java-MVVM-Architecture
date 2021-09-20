package com.example.mvvmloginproject.home.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mvvmloginproject.R;
import com.example.mvvmloginproject.databinding.FragmentHomeBinding;
import com.example.mvvmloginproject.home.model.HomeDataModel;
import com.example.mvvmloginproject.home.viewmodel.HomeViewModel;

import org.jetbrains.annotations.NotNull;


public class HomeFragment extends Fragment {


    FragmentHomeBinding homeBinding;
    HomeViewModel homeViewModel;

    Observer<HomeDataModel>homeDataModelObserver;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        return homeBinding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeDataModelObserver = homeDataModel -> {
            homeBinding.textViewName.setText(homeDataModel.first_name + homeDataModel.last_name);
            homeBinding.textViewEmail.setText(homeDataModel.email);
            homeBinding.textViewNumber.setText(homeDataModel.number);
        };
        homeViewModel.getHomeData(requireActivity()).observe(requireActivity(),homeDataModelObserver);

        setUserButton();
    }

    private void setUserButton(){
        homeBinding.buttonUser.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_userListFragment);
        });
    }
}