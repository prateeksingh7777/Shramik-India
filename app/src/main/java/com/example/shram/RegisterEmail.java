package com.example.shram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterEmail extends AppCompatActivity {

    EditText regname;
    EditText regemail;
    EditText regpass;
    Button regbtnemail;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);

        regname = findViewById(R.id.regname);
        regemail = findViewById(R.id.regemail);
        regpass = findViewById(R.id.regpass);
        regbtnemail = findViewById(R.id.regbtnemail);

        regbtnemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = regname.getText().toString();
                String email = regemail.getText().toString();
                String pass = regpass.getText().toString();

                if(name.isEmpty() || email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(RegisterEmail.this, "All Feilds are Required!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    auth = FirebaseAuth.getInstance();
                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference("Users");

                    auth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(RegisterEmail.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        FirebaseUser user = auth.getCurrentUser();
                                        User data = new User(name, email);
                                        reference.child(user.getUid()).setValue(data)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        startActivity(new Intent(RegisterEmail.this, Home.class));
                                                    }
                                                });

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(RegisterEmail.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });

    }
}