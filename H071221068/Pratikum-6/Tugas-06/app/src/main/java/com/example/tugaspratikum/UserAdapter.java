package com.example.tugaspratikum;

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
public class UserAdapter extends
        RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    public List<User> userList;

    public void addUsers(List<User> newUsers) {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        userList.addAll(newUsers);
        notifyDataSetChanged();
    }
    public UserAdapter(List<User> userList){
        this.userList = userList;
    }
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_item, parent,
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
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatarImageView;
        private TextView nameTextView;
        private TextView emailTextView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
        }
        public void bind(User user) {
            Picasso.get().load(user.getAvatar()).into(avatarImageView);
            nameTextView.setText(user.getFirst_name() + " " +
                    user.getLast_name());
            emailTextView.setText(user.getEmail());
        }
    }

}

