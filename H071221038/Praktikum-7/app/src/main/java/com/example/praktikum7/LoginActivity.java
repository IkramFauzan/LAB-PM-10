package com.example.praktikum7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private final static String PREFERENCES = "my_pref";
    private final static String PREF_USERNAME = "username";
    private final static String PREF_PASSWORD = "password";
    private final static String PREF_LOGIN = "login";

    EditText login_et_username;
    EditText login_et_password;
    Button login_btn_login;
    Button login_btn_register;

    SharedPreferences preferences;

    String username, getUsername;
    String password, getPassword;

    Boolean cekLogin, getCekLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        login_et_username = findViewById(R.id.login_et_username);
        login_et_password = findViewById(R.id.login_et_password);
        login_btn_login = findViewById(R.id.login_btn_login);
        login_btn_register = findViewById(R.id.login_btn_register);

        // Mendaftarkan result launcher untuk mendapatkan hasil dari intent
        ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result ->{
                    if (result.getResultCode() == RESULT_OK){

                        // Mengambil data username dan password dari SharedPreferences setelah pendaftaran
                        preferences = LoginActivity.this.getSharedPreferences(PREFERENCES, MODE_PRIVATE);
                        getUsername = preferences.getString(PREF_USERNAME, "");
                        getPassword = preferences.getString(PREF_PASSWORD, "");

                    }
                }
        );

        login_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = login_et_username.getText().toString().trim();
                password = login_et_password.getText().toString().trim();

                if (username.isEmpty()){
                    login_et_username.setError("Isi Username");
                } else if (password.isEmpty()) {
                    login_et_password.setError("Isi Password");
                } else if (username.equals(getUsername) && password.equals(getPassword)) {
                    SharedPreferences preferencesLogin = LoginActivity.this.getSharedPreferences("logincheck", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencesLogin.edit();
                    editor.putBoolean(PREF_LOGIN, true);
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                }
//                else {
//                    // Memeriksa apakah username dan password yang dimasukkan sesuai dengan yang disimpan
//                    if (username.equals(getUsername) && password.equals(getPassword)){
//                        // Jika sesuai, menyimpan status login ke SharedPreferences dan membuka MainActivity
//                        SharedPreferences preferencesLogin = LoginActivity.this.getSharedPreferences("logincheck", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = preferencesLogin.edit();
//                        editor.putBoolean(PREF_LOGIN, true);
//                        editor.apply();
//
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Toast.makeText(LoginActivity.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
//                    }

            }
        });

        login_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intentResult.launch(intent);
                login_et_username.setText("");
                login_et_password.setText("");
            }
        });

        // Mengambil data username dan password dan selama aplikasi berjalan
        preferences = LoginActivity.this.getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        getUsername = preferences.getString(PREF_USERNAME, "");
        getPassword = preferences.getString(PREF_PASSWORD, "");

        SharedPreferences preferencesTheme = getSharedPreferences("my_preftheme", MODE_PRIVATE);
        Boolean dark_mode = preferencesTheme.getBoolean("darkmode", false);

        if (dark_mode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

    }
}