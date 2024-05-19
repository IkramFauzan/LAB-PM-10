package com.example.praktikum5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProfileActivity extends AppCompatActivity {

    public static String PARCEL_DATA = "parcel_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        ImageView civ_profile = findViewById(R.id.civ_profile);
        TextView tv_name = findViewById(R.id.tv_name);
        TextView tv_username = findViewById(R.id.tv_username);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        civ_profile.setVisibility(View.GONE);
        tv_name.setVisibility(View.GONE);
        tv_username.setVisibility(View.GONE);

        progressBar.setVisibility(View.VISIBLE);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                proses berjalan namun ui nya tampil setelah sleep
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);

                        civ_profile.setVisibility(View.VISIBLE);
                        tv_name.setVisibility(View.VISIBLE);
                        tv_username.setVisibility(View.VISIBLE);

                        Intent intent = getIntent();
                        Account account = getIntent().getParcelableExtra("parcel_data");

                        civ_profile.setImageResource(account.getProfile());
                        tv_name.setText(account.getName());
                        tv_username.setText(account.getUsername());
                    }
                });
            }
        });

    }
}