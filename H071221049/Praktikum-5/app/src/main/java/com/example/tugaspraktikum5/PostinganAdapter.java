package com.example.tugaspraktikum5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Menghubungkan data dari ArrayList instagrams dengan tata letak item dalam RecyclerView
public class PostinganAdapter extends RecyclerView.Adapter<PostinganAdapter.ViewHolder> {

    private ArrayList<Instagram> instagrams;

    public PostinganAdapter(ArrayList<Instagram> instagrams) {
        this.instagrams = instagrams;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postingan, parent, false);
        return new ViewHolder(view);
    }

    // Menetapkan data dari objek Instagram ke ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Instagram instagram1 = instagrams.get(position);

        holder.TV_Username.setText(instagram1.getUsername());
        holder.TV_Name.setText(instagram1.getName());
        holder.TV_Desc.setText(instagram1.getDesc());
        holder.IV_FotoProfile.setImageResource(instagram1.getFotoProfile());
        holder.IV_FotoPostingan.setImageResource(instagram1.getFotoPostingan());
        holder.IV_Feed.setImageURI(instagram1.getSelectedImageUri());

        holder.IV_FotoProfile.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("instagram", instagram1);
            holder.context.startActivity(intent);
        });

        holder.TV_Name.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("instagram", instagram1);
            holder.context.startActivity(intent);
        });
        holder.TV_Username.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("instagram", instagram1);
            holder.context.startActivity(intent);
        });

    }

    // Mengembalikan jumlah item dalam daftar instagrams yang digunakan oleh RecyclerView
    @Override
    public int getItemCount() {
        return instagrams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TV_Username, TV_Name, TV_Desc;
        ImageView IV_FotoProfile, IV_FotoPostingan, IV_Feed;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TV_Username = itemView.findViewById(R.id.TV_Username);
            TV_Name = itemView.findViewById(R.id.TV_Name);
            TV_Desc = itemView.findViewById(R.id.TV_Desc);
            IV_FotoProfile = itemView.findViewById(R.id.IV_Profile);
            IV_FotoPostingan = itemView.findViewById(R.id.IV_Postingan);
            IV_Feed = itemView.findViewById(R.id.IV_Postingan);
            context = itemView.getContext();
        }
    }
}
