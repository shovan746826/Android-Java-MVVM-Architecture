package com.example.mvvmloginproject.users.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmloginproject.R;
import com.example.mvvmloginproject.databinding.FragmentUserListBinding;
import com.example.mvvmloginproject.users.adapters.UserRecyclerViewAdapter;
import com.example.mvvmloginproject.users.viewmodel.UserListViewModel;

import org.jetbrains.annotations.NotNull;


public class UserListFragment extends Fragment {

    FragmentUserListBinding userListBinding;
    UserListViewModel userListViewModel;
    UserRecyclerViewAdapter recyclerViewAdapter;

    public UserListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        userListBinding = FragmentUserListBinding.inflate(getLayoutInflater());
        userListViewModel = new ViewModelProvider(requireActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(UserListViewModel.class);

        return userListBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userListBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        userListViewModel.setData().observe(requireActivity(), arrayList -> {
            Log.e("observe-->","RUN");
            recyclerViewAdapter = new UserRecyclerViewAdapter(arrayList);
            recyclerViewAdapter.notifyDataSetChanged();
            userListBinding.recyclerView.setAdapter(recyclerViewAdapter);
            if (userListBinding.swipeRefreshLayout.isRefreshing()) {
                userListBinding.swipeRefreshLayout.setRefreshing(false);
            }
        });


        userListBinding.swipeRefreshLayout.setOnRefreshListener(() -> userListViewModel.getData());

        userListBinding.buttonAdd.setOnClickListener(v->
                Navigation.findNavController(v).navigate(R.id.action_userListFragment_to_addUsersFragment));
    }
}