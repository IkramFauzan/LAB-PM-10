package com.example.praktikum4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikum4.fragment.HomeFragment;
import com.example.praktikum4.fragment.OptionDialogFragment;
import com.example.praktikum4.fragment.ProfileFragment;

import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {
    private final ArrayList<Account> accounts;
    private final HomeFragment context;

    public AccountAdapter(ArrayList<Account> accounts, HomeFragment context) {
        this.accounts = accounts;
        this.context = context;
    }

    @NonNull
    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountAdapter.ViewHolder holder, int position) {
        Account account = accounts.get(position);
        holder.setData(account);

        holder.idProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    Intent intent = new Intent(holder.itemView.getContext(),MainActivity2.class);
                    intent.putExtra(MainActivity2.PARCEL_DATA, account);
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionDialogFragment optionDialogFragment  = new OptionDialogFragment();
                FragmentManager fragmentManager = context.getChildFragmentManager();
                optionDialogFragment
                        .show(fragmentManager, OptionDialogFragment.class
                        .getSimpleName());

                optionDialogFragment.setOnDialogOptionSelectedListener(new OptionDialogFragment.OnDialogOptionSelectedListener() {
                    @Override
                    public void onOptionSelected(boolean isDeleteSelected) {
                        if (isDeleteSelected) {
                            int position = holder.getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                accounts.remove(position);
                                notifyItemRemoved(position);
                            }
                        }
                    }
                });
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
        TextView home_tv_content;
        ImageButton btn_delete;
        RelativeLayout idProfile;
        Uri uriPost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            home_iv_profile = itemView.findViewById(R.id.home_iv_profile);
            home_tv_name = itemView.findViewById(R.id.home_tv_name);
            home_tv_username = itemView.findViewById(R.id.home_tv_username);
            home_iv_post = itemView.findViewById(R.id.home_iv_post);
            home_tv_content = itemView.findViewById(R.id.home_tv_content);
            btn_delete = itemView.findViewById(R.id.btn_delete);
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
            home_tv_content.setText(account.getContent());

        }
    }
}
