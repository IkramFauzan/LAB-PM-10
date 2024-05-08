package com.example.tugaspraktikum5.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaspraktikum5.DataSource;
import com.example.tugaspraktikum5.Instagram;
import com.example.tugaspraktikum5.R;
import com.example.tugaspraktikum5.SeacrhAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {

    private ArrayList<Instagram> instagramList;
    private RecyclerView recyclerView;
    private SeacrhAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        instagramList = new ArrayList<>();

        adapter = new SeacrhAdapter(instagramList);
        recyclerView.setAdapter(adapter);

        ImageView iv_home = view.findViewById(R.id.IV_Home);
        ImageView iv_profile = view.findViewById(R.id.IV_Profile);
        ImageView iv_posting = view.findViewById(R.id.IV_Post);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);

        androidx.appcompat.widget.SearchView searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // Dipanggil ketika teks dalam SearchView berubah. Di dalamnya, dilakukan
            // pencarian berdasarkan teks yang dimasukkan pengguna, dan hasil pencarian
            // ditampilkan dalam RecyclerView
            @Override
            public boolean onQueryTextChange(String newText) {
                // ProgressBar ditetapkan menjadi visible untuk menunjukkan bahwa proses
                // pencarian sedang berlangsung
                progressBar.setVisibility(View.VISIBLE);

                // Untuk menjalankan proses pencarian secara asinkron
                ExecutorService executor = Executors.newSingleThreadExecutor();
                // mem-post hasil pencarian kembali ke main thread setelah proses pencarian selesai
                // menggunakan getMainLooper karena  ingin proses yang di dalam Handler dijalankan di main/ui thread
                Handler handler = new Handler(Looper.getMainLooper());
                executor.execute(() -> {
                    ArrayList<Instagram> filteredList = new ArrayList<>();
                    if (!newText.isEmpty()) {
                        for (Instagram item : DataSource.instagrams) {
                            if (item.getUsername().toLowerCase().contains(newText.toLowerCase()) ||
                                    item.getName().toLowerCase().contains(newText.toLowerCase())) {
                                filteredList.add(item);
                            }
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Setelah proses pencarian selesai, hasilnya dipost kembali ke main thread
                    handler.post(() -> {
                        progressBar.setVisibility(View.GONE);
                        instagramList.clear();
                        if (!newText.isEmpty()) {
                            instagramList.addAll(filteredList);
                        }
                        adapter.notifyDataSetChanged();
                    });
                });
                return true;
            }


        });

        iv_profile.setOnClickListener(v -> {
            ProfileFragment profileFragment = new ProfileFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, profileFragment)
                    .addToBackStack(null)
                    .commit();
        });

        iv_posting.setOnClickListener(v -> {
            PostinganFragment postinganFragment = new PostinganFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, postinganFragment)
                    .addToBackStack(null)
                    .commit();
        });

        iv_home.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, homeFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

}
