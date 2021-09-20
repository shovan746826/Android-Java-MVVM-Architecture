package com.example.mvvmloginproject.users.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmloginproject.R;
import com.example.mvvmloginproject.users.model.UserListModel;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.View_Holder> {

    private final ArrayList<UserListModel> arrayListUser;


    public UserRecyclerViewAdapter(ArrayList<UserListModel> arrayListUser) {
        super();
        this.arrayListUser = arrayListUser;
    }


    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_latout, parent, false);
        return new View_Holder(v);
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull final View_Holder holder, @SuppressLint("RecyclerView") final int position) {

        UserListModel listModel = arrayListUser.get(position);

        holder.textViewName.setText(listModel.first_name + " " + listModel.last_name);
        holder.textViewEmail.setText(listModel.email);
        holder.textViewNumber.setText(listModel.number);

    }

    @Override
    public int getItemCount() {
        return arrayListUser.size();
    }


    public static class View_Holder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewNumber;
        TextView textViewEmail;

        public View_Holder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);

        }
    }
}
