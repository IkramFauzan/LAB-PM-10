package com.example.prak8;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {
    EditText aa_et_title;
    EditText aa_et_description;
    Toolbar toolbar;
    ActionBar actionBar;
    Button aa_btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Note");

        aa_et_title = findViewById(R.id.aa_et_title);
        aa_et_description = findViewById(R.id.aa_et_description);
        aa_btn_add = findViewById(R.id.aa_btn_add);


        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        aa_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = aa_et_title.getText().toString();
                String description = aa_et_description.getText().toString();


                if (aa_et_title.getText().toString().isEmpty()){
                    aa_et_title.setError("Title tidak boleh kosong");
                } else {
                    DatabaseHelper databaseHelper = new DatabaseHelper(AddActivity.this);
                    databaseHelper.insertRecord(title, description);
                    Intent toHome = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(toHome);
                    finish();
                }


            }
        });

//        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
////                Data getData = getIntent().getParcelableExtra("data");
//                builder.setTitle("Batal")
//                        .setMessage("Apakah anda ingin membatalkan perubahan pada form?")
//                        .setCancelable(true)
//                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent toMain = new Intent(AddActivity.this, MainActivity.class);
//                                startActivity(toMain);
//                                finish();
//                            }
//                        })
//                        .setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        }).show();
//            }
//        };
//        getOnBackPressedDispatcher().addCallback(AddActivity.this, callback);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Batal")
                .setMessage("Apakah anda ingin membatalkan penambahan pada form?")
                .setCancelable(true)
                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent toMain = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(toMain);
                        finish();
                    }
                })
                .setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();

        return super.onOptionsItemSelected(item);
    }

}