package com.example.shram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadlocale();
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, Splash.class);

        Button english = findViewById(R.id.english);
        Button hindi = findViewById(R.id.hindi);
        Button gujarati = findViewById(R.id.gujarati);

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLang("en");
                startActivity(intent);
            }
        });

        hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLang("hi");
                startActivity(intent);
            }
        });

        gujarati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLang("gu");
                startActivity(intent);
            }
        });
    }

    private void changeLang(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My Lang", lang);
        editor.apply();

    }

    public void loadlocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String lang = prefs.getString("My Lang", "");
        changeLang(lang);
    }
}