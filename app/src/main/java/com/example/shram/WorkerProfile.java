package com.example.shram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class WorkerProfile extends AppCompatActivity {

    String name, skill, img, exp, rating, review, pin, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_profile);

        TextView wname = findViewById(R.id.wname);
        TextView wwork = findViewById(R.id.wwork);
        ImageView wimg = findViewById(R.id.wimg);
        TextView wexp = findViewById(R.id.wexp);
        TextView wrating = findViewById(R.id.wrating);
        TextView wreviews = findViewById(R.id.w_c_reviews);


        name = getIntent().getStringExtra("name");
        skill = getIntent().getStringExtra("skill");
        exp = getIntent().getStringExtra("exp");
        rating = getIntent().getStringExtra("rating");
        review = getIntent().getStringExtra("review");
        img = getIntent().getStringExtra("image");
        pin = getIntent().getStringExtra("pin");
        phone = getIntent().getStringExtra("phone");

        wname.setText(name);
        wwork.setText(skill);
        wexp.setText(exp + " years");
        wrating.setText(rating +" "+ getString(R.string.rating));
        wreviews.setText(review);

        Picasso.with(this)
                .load(Uri.parse(img))
                .into(wimg);

        LinearLayout search = findViewById(R.id.search_btn);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkerProfile.this, Search.class);
                intent.putExtra("skill", "all");
                startActivity(intent);
            }
        });

        LinearLayout explore = findViewById(R.id.explore);

        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkerProfile.this, Home.class));
            }
        });

        Button wbook = findViewById(R.id.wbook);

        wbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkerProfile.this, Address.class);
                intent.putExtra("image", img);
                intent.putExtra("name", name);
                intent.putExtra("skill", skill);
                intent.putExtra("rating", rating);
                intent.putExtra("pin", pin);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });
    }
}