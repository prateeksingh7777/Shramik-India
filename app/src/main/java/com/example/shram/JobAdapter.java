package com.example.shram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private List<Job> joblist;
    private Context context;

    public JobAdapter(Home home, List<Job> joblist, Context context){
        this.joblist = joblist;
        this.context = context;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_recycler_view, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.image.setImageResource(joblist.get(position).getImage());
        holder.name.setText(joblist.get(position).getJname());



        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Search.class);
                intent.putExtra("skill", joblist.get(position).getJname());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Search.class);
                intent.putExtra("skill", joblist.get(position).getJname());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return joblist.size();
    }

    public class JobViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView name;

        public JobViewHolder(@NonNull View itemView) {

            super(itemView);

            image = itemView.findViewById(R.id.j_r_img);
            name = itemView.findViewById(R.id.j_r_name);

        }
    }

}
