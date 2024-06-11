package com.example.prak8;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Data> dataList;
    private Context context;

    public DataAdapter(List<Data> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.setData(data);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toUpdate = new Intent(holder.itemView.getContext(), UpdateActivity.class);
                toUpdate.putExtra("data", data);
                holder.itemView.getContext().startActivity(toUpdate);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, timestamp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            timestamp = itemView.findViewById(R.id.time_stamp);
        }

        public void setData(Data data) {
            if (data.getUpdated_at() != null && !data.getUpdated_at().isEmpty()){
                timestamp.setText("Updated at " + data.getUpdated_at());
            } else {
                timestamp.setText("Created at " + data.getCreated_at());
            }
            title.setText(data.getTitle());
            description.setText(data.getDescription());
        }
    }
//    public void setFilteredList (List<Data> filteredList){
//        this.dataList =filteredList;
//        notifyDataSetChanged();
//    }
}
