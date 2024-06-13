package com.example.praktikum_8;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<NoteModel> noteModels;

    public Adapter(List<NoteModel> models) {
        this.noteModels = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        NoteModel model = noteModels.get(position);

        holder.tv_title.setText(model.getTitle());
        holder.tv_desc.setText(model.getDescription());
        holder.tv_date.setText(model.getDate());
        holder.tv_time.setText(model.getTime());
        holder.cv_item.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MainActivity3.class);
            intent.putExtra("ID", model.getId());
            intent.putExtra("title", model.getTitle());
            intent.putExtra("description", model.getDescription());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return noteModels.size();
    }

    public void setFilteredList(List<NoteModel> filteredList) {
        noteModels = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_desc, tv_date,tv_time;
        CardView cv_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_time = itemView.findViewById(R.id.tv_time);
            cv_item = itemView.findViewById(R.id.cv_item);
        }
    }
}
