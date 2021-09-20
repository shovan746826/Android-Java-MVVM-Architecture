package com.example.mvvmloginproject.login.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmloginproject.databinding.FragmentLoginBinding;
import com.example.mvvmloginproject.global.GlobalFunctions;
import com.example.mvvmloginproject.global.Validation;
import com.example.mvvmloginproject.login.model.LoginRepositoryModel;
import com.example.mvvmloginproject.login.viewmodel.LoginViewModel;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends Fragment {

    LoginViewModel loginViewModel;
    FragmentLoginBinding loginBinding;
    GlobalFunctions globalFunctions;
    Observer<LoginRepositoryModel> loginRepositoryModelObserver;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(getLayoutInflater());
        globalFunctions = new GlobalFunctions(requireActivity());
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        return loginBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        loginViewModel = new ViewModelProvider(requireActivity(),
//                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity()
//                        .getApplication())).get(LoginViewModel.class);

        loginRepositoryModelObserver = loginRepositoryModel -> {
            Log.e("observer-->","Run");
            if (loginRepositoryModel.is_success) {
                Toast.makeText(requireActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                loginViewModel.intentActivity(requireActivity(),loginRepositoryModel);
            } else {
                Toast.makeText(requireActivity(), "Login failed", Toast.LENGTH_SHORT).show();
            }
            globalFunctions.cancelProgressDialog();
        };

        setLogin();
    }

    private void setLogin() {
        loginBinding.buttonLogin.setOnClickListener(v -> {
            String number = loginBinding.editTextNumber.getText().toString().trim();
            String password = loginBinding.editTextPassword.getText().toString().trim();
            if (Validation.numberValidation(loginBinding.editTextNumber)
                    && Validation.passwordValidation(loginBinding.editTextPassword)) {
                globalFunctions.showProgressDialog();
                loginViewModel.getLoginData(number, password).observe(requireActivity(), loginRepositoryModelObserver);
            }
        });
    }
}