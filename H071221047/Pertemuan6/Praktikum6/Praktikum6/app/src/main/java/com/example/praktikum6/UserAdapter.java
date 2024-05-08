package com.example.praktikum6;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends
        RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    public static List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent,
                        false);
        return new UserViewHolder(view);
    }

    public void onBindViewHolder(@NonNull UserViewHolder holder, int
            position) {
        User user = userList.get(position);
        holder.bind(user);
    }

    public int getItemCount() {
        return userList.size();
    }

    public void addUsers(List<User> users) {
        // Tambahkan semua pengguna baru ke dalam userList
        userList.addAll(users);
        // Memberitahu RecyclerView bahwa data telah berubah
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatarImageView;
        private TextView nameTextView;
        private TextView emailTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Dapatkan posisi item yang diklik
                    int position = getAdapterPosition();
                    // Pastikan posisi valid
                    if (position != RecyclerView.NO_POSITION) {
                        // Dapatkan pengguna dari userList berdasarkan posisi
                        User clickedUser = userList.get(position);
                        // Buat Intent untuk memulai DetailActivity
                        Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                        // Tambahkan data pengguna ke Intent sebagai ekstra
                        intent.putExtra("USER_ID", clickedUser.getId());
                        // Mulai DetailActivity dengan Intent yang telah dibuat
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }

        //metode bind ini menghubungkan data dari objek
        // User ke elemen-elemen UI yang sesuai dalam aplikasi.
        public void bind(User user) {
            //memuat gambar avatar pengguna yang disimpan dalam objek user.
            Picasso.get().load(user.getAvatar()).into(avatarImageView);
            nameTextView.setText(user.getFirst_name() + " " +
                    user.getLast_name());
            emailTextView.setText(user.getEmail());
        }
    }
}
