package com.example.tugaspraktikum7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Dashboard extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button buttonLogout,buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        TextView textViewWelcome = findViewById(R.id.tvWelcome);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonSettings = findViewById(R.id.buttonSettings);

        String nim = sharedPreferences.getString("NIM", "");
        textViewWelcome.setText("Selamat Datang " + nim);

        int currentNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            textViewWelcome.setTextColor(ContextCompat.getColor(Dashboard.this, R.color.white));
        } else {
            textViewWelcome.setTextColor(ContextCompat.getColor(Dashboard.this, R.color.black));
        }

        buttonLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            startActivity(new Intent(Dashboard.this, MainActivity.class));
            finish();
        });

        buttonSettings.setOnClickListener(v -> startActivity(new Intent(Dashboard.this, Settings.class)));
    }
}