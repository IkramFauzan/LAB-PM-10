package com.example.praktikum4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    public static String PARCEL_DATA = "parcel_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ImageView civ_profile = findViewById(R.id.civ_profile);
        TextView tv_name = findViewById(R.id.tv_name);
        TextView tv_username = findViewById(R.id.tv_username);

        Account account = getIntent().getParcelableExtra("parcel_data");

        civ_profile.setImageResource(account.getProfile());
        tv_name.setText(account.getName());
        tv_username.setText(account.getUsername());

    }
}