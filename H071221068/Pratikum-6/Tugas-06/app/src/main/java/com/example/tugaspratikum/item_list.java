package com.example.tugaspratikum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class item_list extends AppCompatActivity {

    private ApiService apiService;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ProgressBar progressBar;
    private Button buttonload;

    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        apiService = RetrofitClient.getClient().create(ApiService.class);
        recyclerView = findViewById(R.id.recyclerView1);
        progressBar = findViewById(R.id.progressBar);
        buttonload = findViewById(R.id.btn_load);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.VISIBLE);
        buttonload.setVisibility(View.GONE);

        loadUsers(currentPage);

        buttonload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                loadUsers(currentPage);
            }
        });
    }

    private void loadUsers(int page) {
        Call<UserResponse> call = apiService.getUsers(page);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                progressBar.setVisibility(View.GONE);
                buttonload.setVisibility(View.VISIBLE);

                if (response.isSuccessful()) {
                    List<User> newUsers = response.body().getData();
                    if (userAdapter == null) {
                        userAdapter = new UserAdapter(newUsers);
                        recyclerView.setAdapter(userAdapter);
                    } else {
                        userAdapter.addUsers(newUsers);
                    }
                    currentPage++;
                } else {
                    Log.e("ItemListActivity", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("ItemListActivity", "Error: " + t.getMessage());
            }
        });
    }
}
