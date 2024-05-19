package com.example.tugasprak7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

        TextInputEditText nimInput, passInput;
        Button loginBTN, registerBTN;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                nimInput = findViewById(R.id.nimInput);
                passInput = findViewById(R.id.passInput);
                loginBTN = findViewById(R.id.loginBTN);
                registerBTN = findViewById(R.id.registerBTN);

                // menyimpan preferensi aplikasi mode gelap atau terang
                SharedPreferences sharedPreferences1 = getSharedPreferences("MODE", MODE_PRIVATE);
                boolean nightMode = sharedPreferences1.getBoolean("darkMode", false);
                if (nightMode) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

                //Mengecek apakah pengguna telah login sebelumnya
                SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
                String NIM = sharedPreferences.getString("nim", "");
                String PASSWORD = sharedPreferences.getString("password", "");

                if (sharedPreferences.getBoolean("isLoggedIn", false)) {
                        startActivity(new Intent(this, HomeActivity.class));
                        finish();
                }

                //
                loginBTN.setOnClickListener(v -> {
                        String nim = nimInput.getText().toString();
                        String password = passInput.getText().toString();

                        if (nim.isEmpty()) {
                                nimInput.setError("Please fill this field");
                        } else if (password.isEmpty()) {
                                passInput.setError("Please fill this field");
                        } else if (nim.equals(NIM) && password.equals(PASSWORD)) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("isLoggedIn", true);
                                editor.apply();

                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                        } else {
                                Toast.makeText(MainActivity.this, "NIM atau Password salah.", Toast.LENGTH_SHORT).show();
                        }

                });

                registerBTN.setOnClickListener(v -> {
                        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                        startActivity(intent);
                });

        }
}