package com.example.shram;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder> {

    private List<Worker> workerlist;
    Context context;

    public WorkerAdapter( List<Worker> workerlist, Context context){

        this.workerlist = workerlist;
        this.context = context;
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_recycler_home, parent, false);
        return new WorkerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {

        final Worker temp = workerlist.get(position);

        holder.name.setText(workerlist.get(position).getName());
        holder.skill.setText(workerlist.get(position).getSkill());
        Picasso.with(context)
                .load(Uri.parse(workerlist.get(position).getImage()))
                .into(holder.image);

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WorkerProfile.class);
                intent.putExtra("image", temp.getImage());
                intent.putExtra("name", temp.getName());
                intent.putExtra("skill", temp.getSkill());
                intent.putExtra("phone", temp.getPhone());
                intent.putExtra("exp", temp.getExp());
                intent.putExtra("rating", temp.getRating());
                intent.putExtra("review", temp.getReview());
                intent.putExtra("pin", temp.getPincode());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WorkerProfile.class);
                intent.putExtra("image", temp.getImage());
                intent.putExtra("name", temp.getName());
                intent.putExtra("skill", temp.getSkill());
                intent.putExtra("phone", temp.getPhone());
                intent.putExtra("exp", temp.getExp());
                intent.putExtra("rating", temp.getRating());
                intent.putExtra("review", temp.getReview());
                intent.putExtra("pin", temp.getPincode());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WorkerProfile.class);
                intent.putExtra("image", temp.getImage());
                intent.putExtra("name", temp.getName());
                intent.putExtra("skill", temp.getSkill());
                intent.putExtra("phone", temp.getPhone());
                intent.putExtra("exp", temp.getExp());
                intent.putExtra("rating", temp.getRating());
                intent.putExtra("pin", temp.getPincode());
                intent.putExtra("review", temp.getReview());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return workerlist.size();
    }

    public class WorkerViewHolder extends RecyclerView.ViewHolder{

        private TextView name, skill;
        private ImageView image;

        public WorkerViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.w_r_h_name);
            skill = itemView.findViewById(R.id.w_r_h_skill);
            image = itemView.findViewById(R.id.w_r_h_img);

        }
    }
}
