package com.example.praktikum6;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private LinearLayout ll_lost_connection;
    private Button btnRetry;
    private ProgressBar progressBar;
    private Button btnLoadMore;
    private List<User> allUsers = new ArrayList<>();

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

        apiService = RetrofitClient.getClient();
        progressBar = findViewById(R.id.progressBar);
        ll_lost_connection = findViewById(R.id.ll_lost_connection);
        btnRetry = findViewById(R.id.btnRetry);
        btnLoadMore = findViewById(R.id.btnloadMore);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //pemanggilan recycler view page 1
        if (isConnected()) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            btnLoadMore.setVisibility(View.GONE);
            ll_lost_connection.setVisibility(View.GONE);
            Call<UserResponse> call = apiService.getUsers(1);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        allUsers.clear();
                        List<User>users = response.body().getData();
                        allUsers.addAll(users);
                        adapter = new Adapter(allUsers);
                        recyclerView.setAdapter(adapter);
                        btnLoadMore.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }else {
                        progressBar.setVisibility(View.GONE);
                        // Handle error
                    }
                }
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                }
            });
            btnLoadMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isConnected()) {
                        progressBar.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        btnLoadMore.setVisibility(View.GONE);
                        // Make API call to get users from page 2
                        Call<UserResponse> call = apiService.getUsers(2);
                        call.enqueue(new Callback<UserResponse>() {
                            @Override
                            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                if (response.isSuccessful()) {
                                    // Get new users from page 2
                                    List<User> users = response.body().getData();
                                    allUsers.addAll(users);
                                    adapter = new Adapter(allUsers);
                                    recyclerView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    btnLoadMore.setVisibility(View.VISIBLE);
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    btnLoadMore.setVisibility(View.VISIBLE);
                                }
                            }
                            @Override
                            public void onFailure(Call<UserResponse> call, Throwable t) {
                            }
                        });
                    } else {
                        // No internet connection
                        recyclerView.setVisibility(View.GONE);
                        btnLoadMore.setVisibility(View.GONE);
                        ll_lost_connection.setVisibility(View.VISIBLE);
                        btnRetry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isConnected()) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    btnLoadMore.setVisibility(View.GONE);
                                    ll_lost_connection.setVisibility(View.GONE);
                                    btnLoadMore.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (isConnected()) {
                                                progressBar.setVisibility(View.VISIBLE);
                                                recyclerView.setVisibility(View.VISIBLE);
                                                btnLoadMore.setVisibility(View.GONE);
                                                // Make API call to get users from page 2
                                                Call<UserResponse> call = apiService.getUsers(2);
                                                call.enqueue(new Callback<UserResponse>() {
                                                    @Override
                                                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                                        if (response.isSuccessful()) {
                                                            // Get new users from page 2
                                                            List<User> users = response.body().getData();
                                                            allUsers.addAll(users);
                                                            adapter = new Adapter(allUsers);
                                                            recyclerView.setAdapter(adapter);
                                                            adapter.notifyDataSetChanged();
                                                            progressBar.setVisibility(View.GONE);
                                                            recyclerView.setVisibility(View.VISIBLE);
                                                            btnLoadMore.setVisibility(View.VISIBLE);
                                                        } else {
                                                            progressBar.setVisibility(View.GONE);
                                                            btnLoadMore.setVisibility(View.VISIBLE);
                                                            // Handle error
                                                        }
                                                    }
                                                    @Override
                                                    public void onFailure(Call<UserResponse> call, Throwable t) {
                                                    }
                                                });
                                            } else {
                                                // No internet connection
                                                recyclerView.setVisibility(View.GONE);
                                                btnLoadMore.setVisibility(View.GONE);
                                                ll_lost_connection.setVisibility(View.VISIBLE);
                                            }
                                        }
                                    });
                                    Call<UserResponse> call = apiService.getUsers(1);
                                    call.enqueue(new Callback<UserResponse>() {
                                        @Override
                                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                            if (response.isSuccessful()) {
                                                allUsers.clear();
                                                List<User>users = response.body().getData();
                                                allUsers.addAll(users);
                                                adapter = new Adapter(allUsers);
                                                recyclerView.setAdapter(adapter);
                                                btnLoadMore.setVisibility(View.VISIBLE);
                                                progressBar.setVisibility(View.GONE);
                                            }else {
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<UserResponse> call, Throwable t) {
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        } else {
            // no internet connection
            ll_lost_connection.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            btnLoadMore.setVisibility(View.GONE);
            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isConnected()) {
                        progressBar.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        btnLoadMore.setVisibility(View.GONE);
                        ll_lost_connection.setVisibility(View.GONE);
                        btnLoadMore.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isConnected()) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    btnLoadMore.setVisibility(View.GONE);
                                    // Make API call to get users from page 2
                                    Call<UserResponse> call = apiService.getUsers(2);
                                    call.enqueue(new Callback<UserResponse>() {
                                        @Override
                                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                            if (response.isSuccessful()) {
                                                // Get new users from page 2
                                                List<User> users = response.body().getData();
                                                allUsers.addAll(users);
                                                adapter = new Adapter(allUsers);
                                                recyclerView.setAdapter(adapter);
                                                adapter.notifyDataSetChanged();
                                                progressBar.setVisibility(View.GONE);
                                                recyclerView.setVisibility(View.VISIBLE);
                                                btnLoadMore.setVisibility(View.VISIBLE);
                                            } else {
                                                progressBar.setVisibility(View.GONE);
                                                btnLoadMore.setVisibility(View.VISIBLE);
                                                // Handle error
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<UserResponse> call, Throwable t) {
                                        }
                                    });
                                } else {
                                    // No internet connection
                                    recyclerView.setVisibility(View.GONE);
                                    btnLoadMore.setVisibility(View.GONE);
                                    ll_lost_connection.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        Call<UserResponse> call = apiService.getUsers(1);
                        call.enqueue(new Callback<UserResponse>() {
                            @Override
                            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                if (response.isSuccessful()) {
                                    allUsers.clear();
                                    List<User>users = response.body().getData();
                                    allUsers.addAll(users);
                                    adapter = new Adapter(allUsers);
                                    recyclerView.setAdapter(adapter);
                                    btnLoadMore.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
                                }else {
                                    progressBar.setVisibility(View.GONE); // Hide progressBar if response is not successful
//                        // Handle error
                                }
                            }
                            @Override
                            public void onFailure(Call<UserResponse> call, Throwable t) {
                            }
                        });
                    }
                }
            });
        }
    }
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null) {
                return activeNetwork.isConnected() || activeNetwork.isConnectedOrConnecting();
            }
        }
        return false;
    }
}
