package com.example.praktikum5.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.praktikum5.R;

public class ProfileFragment extends Fragment {
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_USERNAME = "extra_username";
    public static final String EXTRA_PROFILE = "extra_profile";
    private ImageView civ_profile;
    private TextView tv_name;
    private TextView tv_username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        civ_profile = view.findViewById(R.id.civ_profile);
        tv_name = view.findViewById(R.id.tv_name);
        tv_username = view.findViewById(R.id.tv_username);

        if (getArguments() != null) {
            civ_profile.setImageResource(getArguments().getInt(EXTRA_PROFILE));
            tv_name.setText(getArguments().getString(EXTRA_NAME));
            tv_username.setText(getArguments().getString(EXTRA_USERNAME));

        }
        return view;

    }
}