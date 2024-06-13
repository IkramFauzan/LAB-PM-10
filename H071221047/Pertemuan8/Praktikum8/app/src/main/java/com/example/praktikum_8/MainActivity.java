package com.example.praktikum_8;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btn_add;
    RecyclerView rc_main;
    SearchView search_view;
    List<NoteModel> noteModels;
    Adapter adapter;
    TextView tv_noData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Notes");

        btn_add = findViewById(R.id.btn_add);
        rc_main = findViewById(R.id.rc_main);
        search_view = findViewById(R.id.search_view);
        tv_noData = findViewById(R.id.tv_noData);


        btn_add.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        DatabaseHelper db = new DatabaseHelper(this);
        noteModels = db.getNote();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rc_main.setLayoutManager(linearLayoutManager);

        adapter = new Adapter(noteModels);
        rc_main.setAdapter(adapter);
        checkIfDataIsEmpty();

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

    }
    private void checkIfDataIsEmpty() {
        if (noteModels.isEmpty()) {
            tv_noData.setVisibility(View.VISIBLE);
        } else {
            tv_noData.setVisibility(View.GONE);
        }
    }

    private void filterList(String newText) {
        DatabaseHelper db = new DatabaseHelper(this);
        List<NoteModel> filteredList = db.searchNotes(newText);
        if (filteredList.isEmpty()){
            rc_main.setVisibility(View.GONE);
            tv_noData.setVisibility(View.VISIBLE);

            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }else {
            tv_noData.setVisibility(View.GONE);
            rc_main.setVisibility(View.VISIBLE);
            adapter.setFilteredList(filteredList);
        }
    }
}