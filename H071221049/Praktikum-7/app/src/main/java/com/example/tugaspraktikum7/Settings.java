package com.example.tugaspraktikum7;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Settings extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Switch switchTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        switchTheme = findViewById(R.id.switchTheme);

        int theme = sharedPreferences.getInt("theme", 0);
        switchTheme.setChecked(theme == 1);

        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            editor.putInt("theme", isChecked ? 1 : 0);
            editor.apply();
        });
    }
}
