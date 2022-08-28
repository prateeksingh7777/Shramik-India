package com.example.shram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    String[] items = {"plumber", "carpenter", "electrician", "unskilled", "Thekedar"};
    String[] ratings = {"4", "3", "2", "1"};
    AutoCompleteTextView acstv, acrtv;
    ArrayAdapter<String> itemsadapter, itemradapter;
    RecyclerView searchview;
    SearchAdapter searchadapter;
    ArrayList<Worker> slist;
    DatabaseReference database;
    SearchView searchView;
    String pin, item = "all";
    double rating= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        item = getIntent().getStringExtra("skill");

        searchView = findViewById(R.id.searchView);
        database = FirebaseDatabase.getInstance().getReference("Collected Data");

        LinearLayout explore = findViewById(R.id.explore);

        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Search.this, Home.class));
            }
        });

        acstv = findViewById(R.id.w_s_ac);
        itemsadapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
        acstv.setAdapter(itemsadapter);

        acstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString();
                getList(item, rating, pin);
            }
        });

        acrtv = findViewById(R.id.w_r_ac);
        itemradapter = new ArrayAdapter<String>(this, R.layout.list_item, ratings);
        acrtv.setAdapter(itemradapter);

        acrtv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rating = Float.parseFloat((String) parent.getItemAtPosition(position));
                getList(item, rating, pin);
            }
        });

        searchview = findViewById(R.id.s_recycler);
        searchview.setHasFixedSize(true);
        searchview.setLayoutManager(new LinearLayoutManager(Search.this, LinearLayoutManager.VERTICAL, false));
        slist = new ArrayList<>();
        searchadapter = new SearchAdapter(slist, getApplicationContext());
        searchview.setAdapter(searchadapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slist.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Worker w = dataSnapshot.getValue(Worker.class);
                    if(w.isAvailable() && w.getSkill().equals(item)){
                        slist.add(w);
                    }
                }
                searchadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                pin = s;
                getList(item, rating, pin);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    public void getList(String skill, double rating, String pin){
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slist.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Worker w = dataSnapshot.getValue(Worker.class);
                    if(skill.equals("all")) {
                        if (w.getPincode().equals(pin) && w.isAvailable()) {
                            slist.add(w);
                        }
                    }
                    else{
                        if (w.getPincode().equals(pin) && w.isAvailable() && w.getSkill().equals(skill) ) {
                            slist.add(w);
                        }
                    } if(w.getPincode().equals(pin) && w.isAvailable() && w.getRating() >= rating){
                        slist.add(w);
                    }
                }
                searchadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}