package com.example.tugaspraktikum7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    EditText editTextNIM, editTextPassword;
    SharedPreferences sharedPreferences;
    Button buttonLogin,buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        setContentView(R.layout.activity_main);

        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            startActivity(new Intent(MainActivity.this, Dashboard.class));
            finish();
        }

        editTextNIM = findViewById(R.id.etNIM);
        editTextPassword = findViewById(R.id.etPw);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegist);

        buttonLogin.setOnClickListener(v -> {
            String nim = editTextNIM.getText().toString();
            String password = editTextPassword.getText().toString();

            if (nim.isEmpty()) {
                editTextNIM.setError("NIM tidak boleh kosong");
                return;
            }

            if (password.isEmpty()) {
                editTextPassword.setError("Password tidak boleh kosong");
                return;
            }

            String savedNIM = sharedPreferences.getString("NIM", "");
            String savedPassword = sharedPreferences.getString("Password", "");

            if (nim.equals(savedNIM) && password.equals(savedPassword)) {
                Toast.makeText(MainActivity.this, "Selamat datang " + nim, Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                startActivity(new Intent(MainActivity.this, Dashboard.class));
                finish();
            } else {
                Toast.makeText(MainActivity.this, "NIM atau password salah", Toast.LENGTH_SHORT).show();
            }
        });

        buttonRegister.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Register.class));
            finish();
        });


        int theme = sharedPreferences.getInt("theme", 0);
        if (theme == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
