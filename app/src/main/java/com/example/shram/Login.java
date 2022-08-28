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

public class Login extends AppCompatActivity {

    EditText logemail, logpass, logphs, logphe;
    Button logbtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logemail = findViewById(R.id.logemail);
        logpass = findViewById(R.id.logpass);
        logbtn = findViewById(R.id.logbtn);

        auth = FirebaseAuth.getInstance();

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = logemail.getText().toString();
                String pass = logpass.getText().toString();

                if(email.isEmpty() && pass.isEmpty()){
                    Toast.makeText(Login.this, "Email and password Fields are Compulsory", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Login.this, "Checking Info", Toast.LENGTH_SHORT).show();
                    auth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        startActivity(new Intent(Login.this, Home.class));

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(Login.this, "Authentication failed: wrong email and password",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });
    }
}