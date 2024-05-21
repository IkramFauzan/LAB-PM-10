package com.example.praktikum6;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.UserViewHolder> {
    private static LinearLayout mainLinear;
    public static List<User> userList;

    public Adapter(List<User> userList){
        this.userList = userList;
    }

    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user);
    }

    public int getItemCount() {
        return userList.size();
    }

    public List<User> getUsers() {
        return userList;
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
            mainLinear = itemView.findViewById(R.id.mainLinear);
            mainLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isConnected()) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            User clickedUser = userList.get(position);
                            Context context = itemView.getContext();
                            Intent intent = new Intent(context, DetailActivity.class);
                            intent.putExtra("user", clickedUser.getId());
                            context.startActivity(intent);
                        }
                    } else {
                        Context context = itemView.getContext();
                        Intent intent = new Intent(context, LostConnectionActivity.class);
                        context.startActivity(intent);
                    }
                }

                private boolean isConnected() {
                    ConnectivityManager connectivityManager = (ConnectivityManager) itemView.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                    return networkInfo != null && networkInfo.isConnected();
                }
            });
        }
        public void bind(User user) {
            Picasso.get().load(user.getAvatar()).into(avatarImageView);
            nameTextView.setText(user.getFirst_name() + " " + user.getLast_name());
            emailTextView.setText(user.getEmail());
        }
    }
}
