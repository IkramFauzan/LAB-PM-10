package com.example.tugaspratikumsql;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabAdd;
    private RecyclerView rvSearch;
    private TextView tvNoData;
    private SearchView searchView;
    private DBConfig dbConfig;
    private List<Notes> notes;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fabAdd = findViewById(R.id.fab_add);
        dbConfig = new DBConfig(this);
        rvSearch = findViewById(R.id.rv_search);
        tvNoData = findViewById(R.id.tv_no_data);
        searchView = findViewById(R.id.search);

        notes = new ArrayList<>();
        notesAdapter = new NotesAdapter(this, notes);
        rvSearch.setAdapter(notesAdapter);
        rvSearch.setLayoutManager(new GridLayoutManager(this, 1));

        loadData("");

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, add.class);
            startActivity(intent);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadData(newText);
                return true;
            }
        });
    }

    private void loadData(String query) {
        notes.clear();
        Cursor cursor;
        if (query.isEmpty()) {
            cursor = dbConfig.getAllRecords();
        } else {
            cursor = dbConfig.searchByTitle(query);
        }

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(dbConfig.getColumnId()));
                String judul = cursor.getString(cursor.getColumnIndexOrThrow(dbConfig.getColumnJudul()));
                String deskripsi = cursor.getString(cursor.getColumnIndexOrThrow(dbConfig.getColumnDeskripsi()));
                String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(dbConfig.getColumnCreatedAt()));
                String updatedAt = cursor.getString(cursor.getColumnIndexOrThrow(dbConfig.getColumnUpdatedAt()));
                notes.add(new Notes(id, judul, deskripsi, createdAt, updatedAt));
            } while (cursor.moveToNext());
            cursor.close();
        }

        if (notes.isEmpty()) {
            tvNoData.setVisibility(View.VISIBLE);
            rvSearch.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.GONE);
            rvSearch.setVisibility(View.VISIBLE);
        }

        notesAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData("");
    }
}