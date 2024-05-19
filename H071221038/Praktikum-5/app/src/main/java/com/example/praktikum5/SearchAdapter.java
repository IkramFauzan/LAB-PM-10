package com.example.praktikum5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<Account> accounts;

    public SearchAdapter(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {

        Account account = accounts.get(position);

        holder.iv_search.setImageResource(account.getProfile());
        holder.tv_username.setText(account.getUsername());
        holder.tv_name.setText(account.getName());

        holder.idSearch.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(),ProfileActivity.class);
            intent.putExtra(ProfileActivity.PARCEL_DATA, account);
            holder.itemView.getContext().startActivity(intent);
        });


    }

    public void filterList(ArrayList<Account> filteredList) {
        accounts = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_search;
        TextView tv_username, tv_name;
        LinearLayout idSearch;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_search = itemView.findViewById(R.id.iv_search);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_name = itemView.findViewById(R.id.tv_name);
            idSearch = itemView.findViewById(R.id.idSearch);
            context = itemView.getContext();

        }
    }
}
