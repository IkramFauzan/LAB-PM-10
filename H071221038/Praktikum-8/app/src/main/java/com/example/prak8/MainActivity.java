package com.example.prak8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    SearchView main_sv;
    ImageButton main_btn_add;
    RecyclerView main_rv;
    TextView main_tv_nodata;
    List<Data> dataList;
    List<Data> filteredList;
    DataAdapter dataAdapter;


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

        toolbar = findViewById(R.id.toolbar);
        main_rv = findViewById(R.id.main_rv);
        main_sv = findViewById(R.id.main_sv);
        main_tv_nodata = findViewById(R.id.main_tv_nodata);
        main_btn_add = findViewById(R.id.main_btn_add);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notes");

        dataList = new ArrayList<>();
        filteredList = new ArrayList<>();
        loadData();

        filteredList.addAll(dataList);
        dataAdapter = new DataAdapter(filteredList, this);
        main_rv.setAdapter(dataAdapter);

//        main_tv_nodata.setVisibility(View.VISIBLE);

        if (dataList.isEmpty()){
            main_tv_nodata.setVisibility(View.VISIBLE);
        }

        main_sv.clearFocus();
        main_sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filteredList.clear();
                if (!newText.isEmpty()){
                    for (Data data : dataList){
                        if (data.getTitle().toLowerCase().contains(newText.toLowerCase())){
                            filteredList.add(data);
                        }
                    }
                } else {
                    filteredList.addAll(dataList);

                }
                dataAdapter.notifyDataSetChanged();
                if (filteredList.isEmpty()){
                    main_tv_nodata.setVisibility(View.VISIBLE);
                } else {
                    main_tv_nodata.setVisibility(View.GONE);
                }
                return true;
            }
        });

        main_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAdd = new Intent(MainActivity.this, AddActivity.class);
                startActivity(toAdd);
            }
        });
    }

    public void loadData(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getAllRecord();
        main_tv_nodata.setVisibility(View.GONE);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String created_at = cursor.getString(cursor.getColumnIndexOrThrow("created_at"));
                String updated_at = cursor.getString(cursor.getColumnIndexOrThrow("updated_at"));

                dataList.add(new Data(id, title, description, created_at, updated_at));

            }  while (cursor.moveToNext());
        }
    }
}