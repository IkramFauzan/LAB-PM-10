package com.example.tugaspratiku4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;
import androidx.fragment.app.FragmentManager;

public class PostinganAdapter extends RecyclerView.Adapter<PostinganAdapter.ViewHolder> {
    private ArrayList<Postingan> postingans;
    private Context context;
    private OnItemClickListener listener;

    private FragmentManager fragmentManager;


    public PostinganAdapter(Context context, ArrayList<Postingan> postingans, FragmentManager fragmentManager) {
        this.context = context;
        this.postingans = postingans;
        this.fragmentManager = fragmentManager;
    }



    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Postingan postingan = postingans.get(position);



        holder.profil.setImageResource(postingan.getProfil());
        holder.name.setText(postingan.getName());
        holder.tvdesc.setText(postingan.getDesc());
        holder.post.setImageResource(postingan.getPost());
        holder.iv_post1.setImageURI(postingan.getSelectedImageUri());
        holder.tvbio.setText(postingan.getBio());


        holder.btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

            builder.setTitle("Konfirmasi");
            builder.setMessage("Apakah Anda yakin ingin menghapus postingan ini?");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        postingans.remove(adapterPosition);
                        notifyItemRemoved(adapterPosition);
                        Toast.makeText( context,"Postingan Telah Dihapus", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        });

    }

    @Override
    public int getItemCount() {
        return postingans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Context context;
        CircleImageView profil;
        TextView name, tvbio;
        TextView tvdesc;
        ImageView post,iv_post1;
        ImageView btnDelete;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.tv_name);
            tvdesc = itemView.findViewById(R.id.desc);
            profil = itemView.findViewById(R.id.profil);
            post = itemView.findViewById(R.id.post);
            tvbio = itemView.findViewById(R.id.tv_bio);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            iv_post1 = itemView.findViewById(R.id.post);


            btnDelete.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position);
                    }
                }
            });

            profil.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Postingan clickedPostingan = postingans.get(position);
                    Intent intent = new Intent(context, ProfileActivity.class);
                    intent.putExtra("instagram", clickedPostingan);
                    context.startActivity(intent);
                }
            });

            name.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Postingan clickedPostingan = postingans.get(position);
                    Intent intent = new Intent(context, ProfileActivity.class);
                    intent.putExtra("instagram", clickedPostingan);
                    context.startActivity(intent);
                }
            });

            tvbio.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Postingan clickedPostingan = postingans.get(position);
                    Intent intent = new Intent(context, ProfileActivity.class);
                    intent.putExtra("instagram", clickedPostingan);
                    context.startActivity(intent);
                }
            });

        }
    }

    public void removeItem(int position) {
        postingans.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, postingans.size());
        Toast.makeText(context, "Postingan dihapus", Toast.LENGTH_SHORT).show();
    }

//    private void openProfileFragment() {
//        ProfileFragment profileFragment = new ProfileFragment();
//        fragmentManager
//                .beginTransaction()
//                .replace(R.id.frame_container, profileFragment)
//                .addToBackStack(null)
//                .commit();
//    }

}
