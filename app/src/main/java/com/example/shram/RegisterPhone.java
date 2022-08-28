package com.example.shram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterPhone extends AppCompatActivity {

    EditText Pname;
    EditText Pstartnum;
    EditText Pnum;
    EditText Pemail;
    Button Psignup;
    ProgressBar otpprogress;

    FirebaseAuth mAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallBacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);

        Pname = findViewById(R.id.regpname);
        Pstartnum = findViewById(R.id.regphstart);// +91
        Pnum = findViewById(R.id.regphone);
        Pemail = findViewById(R.id.regpemail);
        Psignup = findViewById(R.id.regbtnphone);
        otpprogress = findViewById(R.id.progressBarSignup);

        mAuth = FirebaseAuth.getInstance();

        Psignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Pnum.getText().toString().trim().isEmpty())){
                    Toast.makeText(RegisterPhone.this, "Invalid Phone number", Toast.LENGTH_SHORT).show();
                }else if(Pnum.getText().toString().trim().length() != 10){
                    Toast.makeText(RegisterPhone.this, "Type Valid Phone number", Toast.LENGTH_SHORT).show();
                }else{
                    otpsend();
                }
            }
        });

    }

    private void otpsend() {
        otpprogress.setVisibility(View.VISIBLE);
        Psignup.setVisibility(View.INVISIBLE);

        mcallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                otpprogress.setVisibility(View.GONE);
                Psignup.setVisibility(View.VISIBLE);
                Toast.makeText(RegisterPhone.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token){
                otpprogress.setVisibility(View.GONE);
                Psignup.setVisibility(View.VISIBLE);
                Toast.makeText(RegisterPhone.this, "OTP Send succesfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RegisterPhone.this,OTPmobile.class);
                intent.putExtra("mobile",Pnum.getText().toString().trim());
                intent.putExtra("verificationID",verificationId);
                startActivity(intent);
            }
        };



        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+ Pnum.getText().toString().trim())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mcallBacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

}