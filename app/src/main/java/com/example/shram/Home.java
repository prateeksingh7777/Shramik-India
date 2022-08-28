package com.example.shram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    RecyclerView workerview, jobview;
    DatabaseReference database;
    WorkerAdapter workerAdapter;
    ArrayList<Worker> wlist;
    JobAdapter jobAdapter;
    ArrayList<Job> jlist;
    LinearLayout search;
    EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        workerview = findViewById(R.id.worker_recycler);
        workerview.setHasFixedSize(true);
        workerview.setLayoutManager(new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL, false));

        jobview = findViewById(R.id.job_recycler);
        jobview.setHasFixedSize(true);
        jobview.setLayoutManager(new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL, false));


        database = FirebaseDatabase.getInstance().getReference("Collected Data");
        wlist = new ArrayList<>();
        jlist = new ArrayList<>();
        workerAdapter = new WorkerAdapter( wlist, getApplicationContext());
        jobAdapter = new JobAdapter(Home.this, jlist, getApplicationContext());
        workerview.setAdapter(workerAdapter);
        jobview.setAdapter(jobAdapter);

        jlist.add(new Job(R.drawable.shramik, "plumber"));
        jlist.add(new Job(R.drawable.shramik, "electrician"));
        jlist.add(new Job(R.drawable.shramik, "carpenter"));
        jlist.add(new Job(R.drawable.shramik, "unskilled"));
        jlist.add(new Job(R.drawable.shramik, "Thekedar"));

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                wlist.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Worker w = dataSnapshot.getValue(Worker.class);
                    if(w.isAvailable()){
                        wlist.add(w);
                    }
                }
                workerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        search = findViewById(R.id.search_btn);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Search.class);
                intent.putExtra("skill", "all");
                startActivity(intent);
            }
        });

    }
}