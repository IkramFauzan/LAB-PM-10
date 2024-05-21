package com.example.tugaspratikumsql;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    private Context context;
    private List<Notes> notesList;

    public NotesAdapter(Context context, List<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        Notes notes = notesList.get(position);
        holder.tvTitle.setText(notes.getJudul());
        holder.tvDesc.setText(notes.getDeskripsi());

        String timestampText;
        if (notes.getCreatedAt().equals(notes.getUpdatedAt())) {
            timestampText = "Created at " + notes.getCreatedAt();
        } else {
            timestampText = "Updated at " + notes.getUpdatedAt();
        }
        holder.tvTimestamp.setText(timestampText);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ActivityUpdate.class);
            intent.putExtra("record_id", notes.getId());
            context.startActivity(intent);
        });
    }




    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc, tvTimestamp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp);
        }
    }
}