package com.example.praktikum_8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {
    TextInputEditText up_title, up_desc;
    Button btn_update;
    String date, time;
    Calendar calendar;
    int id;
    String title, description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().setTitle("Update");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        up_title = findViewById(R.id.up_title);
        up_desc = findViewById(R.id.up_desc);
        btn_update = findViewById(R.id.btn_update);

        calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        id = getIntent().getIntExtra("ID", -1);
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");

        up_title.setText(title);
        up_desc.setText(description);


        btn_update.setOnClickListener(v -> {
            String title1 = up_title.getText().toString();
            if (title1.isEmpty()) {
                up_title.setError("Field tidak boleh kosong");
                return;
            }
            date = dateFormat.format(calendar.getTime());
            time = timeFormat.format(calendar.getTime());

            NoteModel noteModel = new NoteModel(up_title.getText().toString(), up_desc.getText().toString(), date,time);
            DatabaseHelper db = new DatabaseHelper(MainActivity3.this);
            db.updateNote(noteModel, id);

            Intent intent = new Intent(MainActivity3.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            Toast.makeText(MainActivity3.this, "Data Updated Succesfully", Toast.LENGTH_SHORT).show();
            finish();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Note")
                    .setMessage("Apakah Anda yakin ingin menghapus catatan ini?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        DatabaseHelper db = new DatabaseHelper(this);
                        id = getIntent().getIntExtra("ID", -1);
                        db.deleteNote(id);
                        db.close();
                        Toast.makeText(MainActivity3.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
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