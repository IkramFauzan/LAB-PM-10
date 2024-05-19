package com.example.tugaspratiku4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaspratiku4.DataSource;
import com.example.tugaspratiku4.Postingan;
import com.example.tugaspratiku4.PostinganAdapter;
import com.example.tugaspratiku4.R;

public class HomeFragment extends Fragment {

    public static final String EXTRA_POSTINGAN = "extra_postingan";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecyclerView rvPostingan = view.findViewById(R.id.postingan);
        rvPostingan.setHasFixedSize(true);
        PostinganAdapter postinganAdapter = new PostinganAdapter(requireContext(), DataSource.postingans, getParentFragmentManager());
        rvPostingan.setAdapter(postinganAdapter);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Postingan postingan = bundle.getParcelable(EXTRA_POSTINGAN);
            if (postingan != null) {
                DataSource.postingans.add(0, postingan);
                postinganAdapter.notifyDataSetChanged();
                rvPostingan.smoothScrollToPosition(0);
            }
        }

        TextView tv_name = view.findViewById(R.id.tv_name);
        ImageView iv_profil = view.findViewById(R.id.profil);
        TextView tv_bio = view.findViewById(R.id.tv_bio);




        postinganAdapter.setOnItemClickListener(new PostinganAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                postinganAdapter.removeItem(position);
            }
        });
    }
}
