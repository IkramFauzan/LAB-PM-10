package com.example.praktikum_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {
    SwitchCompat switch1;
    boolean nightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        switch1 = findViewById(R.id.switch1);

        SharedPreferences sharedPreferences = getSharedPreferences("MODE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        nightMode = sharedPreferences.getBoolean("darkMode", false);
        if (nightMode){
            switch1.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }


        switch1.setOnClickListener(v -> {
            if (nightMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean("darkMode", false);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putBoolean("darkMode", true);
            }
            editor.apply();
        });
    }
}