package com.example.tugaspraktikum5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.tugaspraktikum5.fragment.HomeFragment;

// Menampilkan HomeFragment di MainActivity jika belum ada instance dari
// HomeFragment yang sedang berjalan

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Digunakan untuk mengelola fragmen dalam activity
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Membuat Objek HomeFragment
        HomeFragment homeFragment = new HomeFragment();

        // Mencari fragmen dengan tag
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        // Menambahkan Fragment ke Container
        if (!(fragment instanceof HomeFragment)){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, homeFragment)
                    .commit();
        }
    }
}