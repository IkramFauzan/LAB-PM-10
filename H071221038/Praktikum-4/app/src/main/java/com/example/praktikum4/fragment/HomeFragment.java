package com.example.praktikum4.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.praktikum4.Account;
import com.example.praktikum4.AccountAdapter;
import com.example.praktikum4.DataSource;
import com.example.praktikum4.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    RecyclerView fr_rv_post;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fr_rv_post = view.findViewById(R.id.fr_rv_post);
        AccountAdapter accountAdapter = new AccountAdapter(DataSource.accounts,this);
        fr_rv_post.setAdapter(accountAdapter);
        if(getArguments() != null) {
            ArrayList<Account> receivedData = getArguments().getParcelableArrayList("accounts");
            if(receivedData != null) {
                DataSource.accounts.addAll(0, receivedData);
                accountAdapter.notifyDataSetChanged();
            }
        }
    }
}