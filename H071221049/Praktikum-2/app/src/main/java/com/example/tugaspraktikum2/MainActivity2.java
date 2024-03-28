package com.example.tugaspraktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private EditText etNote1;
    private EditText etNote2;
    private Button btnSave;

    private String textFromEtNote1;
    private String textFromEtNote2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etNote1 = findViewById(R.id.note1);
        etNote2 = findViewById(R.id.note2);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(view -> {
            textFromEtNote1 = etNote1.getText().toString().trim();
            textFromEtNote2 = etNote2.getText().toString().trim();

            if (textFromEtNote1.isEmpty() || textFromEtNote2.isEmpty()) {
                Toast.makeText(this, "Isi title dan content terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, MainActivity3.class);

                // Meneruskan data ke MainActivity3
                intent.putExtra("textFromEtName", getIntent().getStringExtra("textFromEtName"));
                intent.putExtra("textFromEtUname", getIntent().getStringExtra("textFromEtUname"));
                intent.putExtra("textFromEtNote1", textFromEtNote1);
                intent.putExtra("textFromEtNote2", textFromEtNote2);

                // Meneruskan data gambar dari MainActivity
                intent.putExtra("imageUri", getIntent().getStringExtra("imageUri"));
                startActivity(intent);
            }
        });
    }
}