package com.example.praktikum_8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    TextInputEditText et_title, et_desc;
    Button btn_submit;
    String date, time;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("Add");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_title = findViewById(R.id.et_title);
        et_desc = findViewById(R.id.et_desc);
        btn_submit = findViewById(R.id.btn_submit);

        calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        btn_submit.setOnClickListener(v -> {
            String title1 = et_title.getText().toString();
            if (title1.isEmpty()) {
                et_title.setError("Field tidak boleh kosong");
                return;
            }
            date = dateFormat.format(calendar.getTime());
            time = timeFormat.format(calendar.getTime());

            NoteModel noteModel = new NoteModel(et_title.getText().toString(), et_desc.getText().toString(), date,time);
            DatabaseHelper db = new DatabaseHelper(MainActivity2.this);
            db.addNote(noteModel);
            db.close();

            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            Toast.makeText(MainActivity2.this, "Data Inserted Succesfully", Toast.LENGTH_SHORT).show();
            finish();

        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        showExitConfirmationDialog();
        return true;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        showExitConfirmationDialog();
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Exit")
                .setMessage("Are you sure you want to leave this form?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    finish();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                })
                .create()
                .show();
    }

}