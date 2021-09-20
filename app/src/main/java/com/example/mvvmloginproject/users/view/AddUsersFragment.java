package com.example.mvvmloginproject.users.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmloginproject.databinding.FragmentAddUsersBinding;
import com.example.mvvmloginproject.users.model.UserAddModel;
import com.example.mvvmloginproject.users.viewmodel.AddUserViewModel;
import com.example.mvvmloginproject.users.viewmodel.UserListViewModel;

import org.jetbrains.annotations.NotNull;


public class AddUsersFragment extends Fragment {

    FragmentAddUsersBinding addUsersBinding;

    Observer<UserAddModel> userAddModelObserver;
    AddUserViewModel addUserViewModel;
    UserListViewModel userListViewModel;

    public AddUsersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        addUsersBinding = FragmentAddUsersBinding.inflate(getLayoutInflater());
        addUserViewModel = new ViewModelProvider(requireActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(AddUserViewModel.class);
        userListViewModel = new ViewModelProvider(requireActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(UserListViewModel.class);

        return addUsersBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userAddModelObserver = userAddModel -> {
            if (userAddModel.success){
                userListViewModel.getData();
                requireActivity().onBackPressed();
            }
        };

        addUser();
    }

    private void addUser(){
        addUsersBinding.buttonAdd.setOnClickListener(v->{
            String first_name = addUsersBinding.editTextFirstName.getText().toString().trim();
            String last_name = addUsersBinding.editTextLastName.getText().toString().trim();
            String number = addUsersBinding.editTextNumber.getText().toString().trim();
            String email = addUsersBinding.editTextEmail.getText().toString().trim();
            int user_type_id = 66;

            addUserViewModel.addUser(first_name,last_name,number,email,user_type_id).observe(requireActivity(),userAddModelObserver);
        });
    }
}