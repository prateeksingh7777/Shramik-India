package com.example.shram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReportPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_page);

        EditText rtext = findViewById(R.id.rtext), rsub = findViewById(R.id.rsub);
        Button rbtn = findViewById(R.id.rbtn);

        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                email.setType("text/plain");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "facehian@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, rsub.getText().toString());
                email.putExtra(Intent.EXTRA_TEXT, rtext.getText().toString());

                startActivity(Intent.createChooser(email, "Send mail to ...."));
            }
        });
    }
}