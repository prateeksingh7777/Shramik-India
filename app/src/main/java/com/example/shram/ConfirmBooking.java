package com.example.shram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ConfirmBooking extends AppCompatActivity {

    String phone, add, pin, name, rating, skill;
    TextView wname, wrating, wskill, wpin;
    ImageView wimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);

        name = getIntent().getStringExtra("name");
        skill = getIntent().getStringExtra("skill");
        rating = getIntent().getStringExtra("rating");
        phone = getIntent().getStringExtra("phone");
        add = getIntent().getStringExtra("add");
        pin = getIntent().getStringExtra("wpin");
        String cpin = getIntent().getStringExtra("cpin");
        String img = getIntent().getStringExtra("image");

        wname = findViewById(R.id.wbname);
        wrating = findViewById(R.id.wbrating);
        wskill = findViewById(R.id.wbskill);
        wpin = findViewById(R.id.wbpin);
        wimg = findViewById(R.id.wbimg);

        wname.setText(name);
        wskill.setText(skill);
        wrating.setText(rating +" "+ getString(R.string.rating));
        wpin.setText(pin);
        Picasso.with(ConfirmBooking.this).load(Uri.parse(img)).into(wimg);

        LinearLayout callw = findViewById(R.id.wcall);
        LinearLayout messagew = findViewById(R.id.wmessage);
        LinearLayout confirmbooking = findViewById(R.id.confirmbooking);
        LinearLayout wreport = findViewById(R.id.wreport);

        confirmbooking.setVisibility(View.GONE);


        wreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfirmBooking.this, ReportPage.class));
            }
        });

        callw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmbooking.setVisibility(View.VISIBLE);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phone));
                startActivity(intent);

            }
        });

        messagew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmbooking.setVisibility(View.VISIBLE);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phone, null)));

            }
        });

    }
}