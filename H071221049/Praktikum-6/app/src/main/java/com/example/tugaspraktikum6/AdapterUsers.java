package com.example.tugaspraktikum6;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

// kalo diklik profilnya, akan memunculkan data sesuai id nya

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.ViewHolder> {
    ArrayList<User> users;

    public AdapterUsers(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    // membuat instance dari ViewHolder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.tv_name.setText(user.getFirstName());
        holder.tv_email.setText(user.getEmail());
        Picasso.get().load(user.getAvatarUrl()).into(holder.iv_foto);

        holder.itemView.setOnClickListener(v -> {
            int data = users.get(holder.getAdapterPosition()).getId();
            String id = Integer.toString(data);
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra("id", id);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    // mengembalikan jumlah total item dalam RecyclerView
    public int getItemCount() {
        return users.size();
    }

    // menambahkan data baru ke RecyclerView
    public void addData(List<User> newData) {
        int startPosition = users.size();
        users.addAll(newData);
        notifyItemRangeInserted(startPosition, newData.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_foto;
        TextView tv_name, tv_email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_foto = itemView.findViewById(R.id.iv_foto);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_email = itemView.findViewById(R.id.tv_email);
        }
    }
}

