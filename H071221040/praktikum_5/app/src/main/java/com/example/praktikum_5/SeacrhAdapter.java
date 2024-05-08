package com.example.praktikum_5;

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

public class SeacrhAdapter extends RecyclerView.Adapter<SeacrhAdapter.ViewHolder> {

    private ArrayList<Instagram> instagrams;

    public SeacrhAdapter(ArrayList<Instagram> instagrams) {
        this.instagrams = instagrams;
    }

    @NonNull
    @Override
    public SeacrhAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search, parent, false);
        return new SeacrhAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeacrhAdapter.ViewHolder holder, int position) {

        Instagram instagram2 = instagrams.get(position);

        holder.IV_Search.setImageResource(instagram2.getFotoProfile());
        holder.tv_username.setText(instagram2.getUsername());
        holder.tv_name.setText(instagram2.getName());

        holder.IV_Search.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("instagram", instagram2);
            holder.context.startActivity(intent);
        });

        holder.tv_username.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("instagram", instagram2);
            holder.context.startActivity(intent);
        });
        holder.tv_name.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("instagram", instagram2);
            holder.context.startActivity(intent);
        });

    }

    public void filterList(ArrayList<Instagram> filteredList) {
        instagrams = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return instagrams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView IV_Search;
        TextView tv_username, tv_name;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            IV_Search = itemView.findViewById(R.id.iv_search);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_name = itemView.findViewById(R.id.tv_name);
            context = itemView.getContext();
        }
    }
}
