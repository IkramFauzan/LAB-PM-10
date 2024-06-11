package com.example.tugaspratikumsql;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityUpdate extends AppCompatActivity {
    private EditText et_UpdateJudul;
    private EditText et_UpdateDeskripsi;
    private Button btn_update;
    private DBConfig dbConfig;
    private int recordId;

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

        dbConfig = new DBConfig(this);

        et_UpdateJudul = findViewById(R.id.et_upjudul);
        et_UpdateDeskripsi = findViewById(R.id.et_updesc);
        btn_update = findViewById(R.id.btn_update);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("record_id")) {
            recordId = intent.getIntExtra("record_id", -1);
            loadRecordData(recordId);
        }


        findViewById(R.id.btn_backk).setOnClickListener(v -> {
            showCancelConfirmationDialog();
        });

        findViewById(R.id.btn_delete).setOnClickListener(v -> {
            dbConfig.deleteRecords(recordId);
            showDeleteConfirmationDialog();
        });


        btn_update.setOnClickListener(v -> {
            String judul = et_UpdateJudul.getText().toString();
            String deskripsi = et_UpdateDeskripsi.getText().toString();

            if (!judul.isEmpty() && !deskripsi.isEmpty()) {
                dbConfig.updateRecord(recordId, judul, deskripsi);
                finish(); // Close the activity
            } else {
                if (judul.isEmpty()) {
                    et_UpdateJudul.setError("Judul tidak boleh kosong");
                }
                if (deskripsi.isEmpty()) {
                    et_UpdateDeskripsi.setError("Deskripsi tidak boleh kosong");
                }
            }
        });
    }

    private void loadRecordData(int id) {
        Cursor cursor = dbConfig.getReadableDatabase().rawQuery("SELECT * FROM " + dbConfig.getTableName() + " WHERE " + dbConfig.getColumnId() + " = ?", new String[]{String.valueOf(id)});
        if (cursor != null && cursor.moveToFirst()) {
            et_UpdateJudul.setText(cursor.getString(cursor.getColumnIndexOrThrow(dbConfig.getColumnJudul())));
            et_UpdateDeskripsi.setText(cursor.getString(cursor.getColumnIndexOrThrow(dbConfig.getColumnDeskripsi())));
            cursor.close();
        }
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Note");
        builder.setMessage("Apakah anda yakin ingin menghapus item ini?");
        builder.setPositiveButton("Ya", (dialog, which) -> {
            dbConfig.deleteRecords(recordId);
            finish();
        });
        builder.setNegativeButton("Tidak", (dialog, which) ->
                dialog.dismiss());
        builder.create().show();
    }

    private void showCancelConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Batal");
        builder.setMessage("Apakah anda ingin membatalkan perubahan pada form?");

        builder.setPositiveButton("Ya", (dialog, which) -> {
            Intent intent = new Intent(ActivityUpdate.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("Tidak", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }
    @Override
    public void onBackPressed() {
        showCancelConfirmationDialog();
    }
}