package com.example.shram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Address extends AppCompatActivity {

    String name, skill, img, rating, wwpin, phone;
    TextView wname, wrating, wskill, wpin;
    ImageView wimg;
    EditText badd, bpin;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        name = getIntent().getStringExtra("name");
        skill = getIntent().getStringExtra("skill");
        rating = getIntent().getStringExtra("rating");
        img = getIntent().getStringExtra("image");
        wwpin = getIntent().getStringExtra("pin");
        phone = getIntent().getStringExtra("phone");

        wname = findViewById(R.id.wbname);
        wrating = findViewById(R.id.wbrating);
        wskill = findViewById(R.id.wbskill);
        wpin = findViewById(R.id.wbpin);
        wimg = findViewById(R.id.wbimg);

        wname.setText(name);
        wskill.setText(skill);
        wrating.setText(rating +" "+ getString(R.string.rating));
        wpin.setText(wwpin);

        Picasso.with(Address.this).load(Uri.parse(img)).into(wimg);

        badd = findViewById(R.id.badd);
        bpin = findViewById(R.id.bpin);
        btn = findViewById(R.id.b_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String add = badd.getText().toString().trim();
                String pin = bpin.getText().toString().trim();

                if(add.isEmpty() || pin.isEmpty()){
                    Toast.makeText(Address.this, "Address and Pincode fields are complusory to fill!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(Address.this, ConfirmBooking.class);
                    intent.putExtra("image", img);
                    intent.putExtra("name", name);
                    intent.putExtra("skill", skill);
                    intent.putExtra("rating", rating);
                    intent.putExtra("wpin", wwpin);
                    intent.putExtra("phone", phone);
                    intent.putExtra("add", add);
                    intent.putExtra("cpin", pin);
                    startActivity(intent);
                }
            }
        });

    }
}