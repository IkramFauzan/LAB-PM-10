package com.example.prak8;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class UpdateActivity extends AppCompatActivity {

    EditText au_et_title;
    EditText au_et_description;
    Button au_btn_update;
    Toolbar toolbar;
    ActionBar actionBar;
    Data getData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Update");

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        au_et_title = findViewById(R.id.au_et_title);
        au_et_description = findViewById(R.id.au_et_description);
        au_btn_update = findViewById(R.id.au_btn_update);

        getData = getIntent().getParcelableExtra("data");

        au_et_title.setText(getData.getTitle());
        au_et_description.setText(getData.getDescription());

//        if (au_et_title.getText().toString().isEmpty()){
//            au_et_title.setError("Title harus di isi");
//        }

        au_btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newId = getData.getId();
                String newTitle = au_et_title.getText().toString();
                String newDescription = au_et_description.getText().toString();

                if (au_et_title.getText().toString().isEmpty()){
                    au_et_title.setError("Title harus di isi");
                } else {
                    DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                    databaseHelper.updateRecord(newId, newTitle, newDescription);
                    Intent toHome = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(toHome);
                    finish();
                }
            }
        });

//        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
//                Data getData = getIntent().getParcelableExtra("data");
//                builder.setTitle("Batal")
//                        .setMessage("Apakah anda ingin membatalkan perubahan pada form?")
//                        .setCancelable(true)
//                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent toMain = new Intent(UpdateActivity.this, MainActivity.class);
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
//        getOnBackPressedDispatcher().addCallback(UpdateActivity.this, callback);
    }

    // menampilkan logo delete
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Data getData = getIntent().getParcelableExtra("data");
        if (item.getItemId() == R.id.delete){
            builder.setTitle("Hapus Note")
                    .setMessage("Apakah anda yakin ingin menghapus item ini?")
                    .setCancelable(true)
                    .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                            databaseHelper.deleteRecord(getData.getId());

                            Intent toMain = new Intent(UpdateActivity.this, MainActivity.class);
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
        } else {
            builder.setTitle("Batal")
                    .setMessage("Apakah anda ingin membatalkan perubahan pada form?")
                    .setCancelable(true)
                    .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent toMain = new Intent(UpdateActivity.this, MainActivity.class);
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

        }
        return super.onOptionsItemSelected(item);
    }

}
