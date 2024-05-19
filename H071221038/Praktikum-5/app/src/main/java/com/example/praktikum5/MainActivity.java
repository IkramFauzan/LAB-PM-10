package com.example.praktikum5;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.praktikum5.Fragment.HomeFragment;
import com.example.praktikum5.Fragment.PostFragment;
import com.example.praktikum5.Fragment.ProfileFragment;
import com.example.praktikum5.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private HomeFragment HomeFragment;
    private SearchFragment SearchFragment;
    private PostFragment PostFragment;
    private ProfileFragment ProfileFragment;
    private FrameLayout frameLayout;
    private TextView titlebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.frameLayout);
        titlebar = findViewById(R.id.titlebar);
        titlebar.setText("Home");

        HomeFragment = new HomeFragment();
        PostFragment = new PostFragment();
        SearchFragment = new SearchFragment();
        ProfileFragment = new ProfileFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, HomeFragment)
                .commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.nav_home){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, HomeFragment)
                            .commit();
                    titlebar.setText("Home");
                } else if (itemId == R.id.nav_search) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, SearchFragment)
                            .commit();
                    titlebar.setText("Search User");
                } else if (itemId == R.id.nav_post) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, PostFragment)
                            .commit();
                    titlebar.setText("Add Post");
                } else if (itemId == R.id.nav_profile) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, ProfileFragment)
                            .commit();
                    titlebar.setText("Profile");
                }
                return true;
            }
        });

//        FragmentManager fragmentManager  = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout, new HomeFragment());
//        fragmentTransaction.commit();


    }
}