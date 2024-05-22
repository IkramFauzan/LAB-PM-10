package com.example.praktikum6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    LinearLayout llOn, llOff;
    RecyclerView recyclerView;
    TextView tvHead, tvBody;
    ImageView ivOff;
    Button btnMore, btnRetry;
    ProgressBar pbOn, pbOff;
    private ApiService apiService;
    private UserAdapter userAdapter;
    private int currentPage = 1;
    private boolean isLoading = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        llOn = findViewById(R.id.internetOn);
        llOff = findViewById(R.id.internetOff);
        tvHead = findViewById(R.id.tvHead);
        tvBody = findViewById(R.id.tvBody);
        ivOff = findViewById(R.id.ivOff);
        btnMore = findViewById(R.id.btnMore);
        btnRetry = findViewById(R.id.btnRetry);
        pbOff = findViewById(R.id.pbOff);
        pbOn = findViewById(R.id.pbOn);
        apiService = RetrofitClient.getClient().create(ApiService.class);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inisialisasi pengaturan awal adapter dan panggilan API
        loadData();

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMore.setVisibility(View.GONE);
                // Cek apakah sedang dalam proses memuat
                if (!isLoading) {
                    isLoading = true; // Set isLoading menjadi true agar tidak ada pemanggilan ganda
                    // Menampilkan ProgressBar
                    pbOn.setVisibility(View.VISIBLE);
                    // Membuat dan menjalankan thread untuk memuat data dari halaman berikutnya
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500); // Menunda pembaruan selama 0.5 detik
                                currentPage++; // Tambahkan 1 ke nomor halaman saat tombol "More" ditekan
                                // Panggil metode loadData lagi untuk memuat data dari halaman yang baru
                                loadData();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sembunyikan komponen UI yang terkait dengan tampilan offline
                ivOff.setVisibility(View.GONE);
                tvHead.setVisibility(View.GONE);
                tvBody.setVisibility(View.GONE);
                btnRetry.setVisibility(View.GONE);

                // Tampilkan ProgressBar untuk menunjukkan bahwa aplikasi sedang mencoba menghubungkan kembali
                pbOff.setVisibility(View.VISIBLE);

                // Membuat dan menjalankan thread untuk mencoba menghubungkan kembali
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loadData();
                    }
                }).start();
            }
        });
    }

    private void loadData() {
        btnMore.setVisibility(View.GONE);
        pbOn.setVisibility(View.VISIBLE);

        // Mengirim panggilan API untuk mendapatkan data pengguna dari halaman yang ditentukan
        Call<UserResponse> call = apiService.getUsers(currentPage);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // Sembunyikan ProgressBar setelah mendapatkan respons
                pbOn.setVisibility(View.GONE);
                isLoading = false; // Set isLoading menjadi false setelah pembaruan selesai
                if (response.isSuccessful()) {
                    llOn.setVisibility(View.VISIBLE);
                    btnMore.setVisibility(View.VISIBLE);
                    // Jika respons berhasil, tambahkan data ke adapter RecyclerView
                    List<User> users = response.body().getData();
                    if (userAdapter == null) {
                        // Jika adapter belum ada, inisialisasi adapter baru
                        userAdapter = new UserAdapter(users);
                        recyclerView.setAdapter(userAdapter);
                    } else {
                        // Jika adapter sudah ada, tambahkan data baru ke dalamnya
                        userAdapter.addUsers(users);
                        // Perbarui RecyclerView
                        userAdapter.notifyDataSetChanged();
                    }
                    // Misalnya, Anda dapat menyembunyikan tampilan offline
                    llOff.setVisibility(View.GONE);
                } else {
                    // Tampilkan pesan kesalahan jika respons tidak berhasil
                    showError("Failed to fetch data. Please try again later.");
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                pbOn.setVisibility(View.GONE);
                btnMore.setVisibility(View.GONE);
                llOn.setVisibility(View.GONE);
                isLoading = false;
                // Tampilkan komponen UI yang menunjukkan bahwa koneksi internet tidak terhubung
                llOff.setVisibility(View.VISIBLE);
                ivOff.setVisibility(View.VISIBLE);
                tvHead.setVisibility(View.VISIBLE);
                tvBody.setVisibility(View.VISIBLE);
                btnRetry.setVisibility(View.VISIBLE);
                pbOff.setVisibility(View.GONE);
            }
        });
    }
    // Method untuk menampilkan pesan kesalahan
    private void showError(String message) {
        // Tampilkan pesan kesalahan dengan menggunakan toast atau dialog
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}