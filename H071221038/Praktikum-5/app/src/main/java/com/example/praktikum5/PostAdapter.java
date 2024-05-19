package com.example.praktikum5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikum5.Fragment.HomeFragment;
import com.example.praktikum5.Fragment.ProfileFragment;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private final ArrayList<Account> accounts;
    private final HomeFragment context;

    public PostAdapter(ArrayList<Account> accounts, HomeFragment context) {
        this.accounts = accounts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Account account = accounts.get(position);
        holder.setData(account);
        holder.idProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    Intent intent = new Intent(holder.itemView.getContext(),ProfileActivity.class);
                    intent.putExtra(ProfileActivity.PARCEL_DATA, account);
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView home_iv_profile;
        TextView home_tv_name;
        TextView home_tv_username;
        ImageView home_iv_post;
        TextView home_tv_desc;
        RelativeLayout idProfile;
        Uri uriPost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            home_iv_profile = itemView.findViewById(R.id.iv_profile);
            home_tv_name = itemView.findViewById(R.id.home_tv_name);
            home_tv_username = itemView.findViewById(R.id.home_tv_username);
            home_iv_post = itemView.findViewById(R.id.iv_post);
            home_tv_desc = itemView.findViewById(R.id.tv_desc);
            idProfile = itemView.findViewById(R.id.idProfile);
        }

        public void setData(Account account) {
            home_iv_profile.setImageResource(account.getProfile());
            home_tv_name.setText(account.getName());
            home_tv_username.setText(account.getUsername());
            if (account.getUrigambar() != null){
                home_iv_post.setImageURI(account.getUrigambar());
            } else {
                home_iv_post.setImageResource(account.getPost());
            }
            home_tv_desc.setText(account.getDesc());

        }
    }
}
