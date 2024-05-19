package com.example.praktikum_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText regNimInput, regPassInput;
    Button regBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regNimInput = findViewById(R.id.regNimInput);
        regPassInput = findViewById(R.id.regPassInput);
        regBTN = findViewById(R.id.regBTN);

        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE );

        regBTN.setOnClickListener(v ->{
            String nim = regNimInput.getText().toString();
            String password = regPassInput.getText().toString();

            if (nim.isEmpty()) {
                regNimInput.setError("Please fill this field");
            } else if (password.isEmpty()) {
                regPassInput.setError("Please fill this field");
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nim", nim);
                editor.putString("password", password);
                editor.apply();

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                Toast.makeText(RegisterActivity.this, "Berhasil membuat akun.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}