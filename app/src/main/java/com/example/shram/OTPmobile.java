package com.example.shram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.annotations.NotNull;

public class OTPmobile extends AppCompatActivity {

    EditText block1, block2, block3, block4,block5, block6;
    String verificationID;
    ProgressBar VerifyProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpmobile);

        block1 = findViewById(R.id.otp1);
        block2 = findViewById(R.id.otp2);
        block3 = findViewById(R.id.otp3);
        block4 = findViewById(R.id.otp4);
        block5 = findViewById(R.id.otp5);
        block6 = findViewById(R.id.otp6);
        ProgressBar VerifyProgress = findViewById(R.id.progressBarVerify);

        TextView phonetext = findViewById(R.id.otpphone);//jis number pe otp jaayega
        Button verifybtn = findViewById(R.id.verifyotp);

        verificationID = getIntent().getStringExtra("verificationID");

        phonetext.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (block1.getText().toString().trim().isEmpty() ||
                        block2.getText().toString().trim().isEmpty() ||
                        block3.getText().toString().trim().isEmpty() ||
                        block4.getText().toString().trim().isEmpty() ||
                        block5.getText().toString().trim().isEmpty() ||
                        block6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(OTPmobile.this, "OTP is not Valid", Toast.LENGTH_SHORT).show();
                } else if (verificationID != null) {
                    String code = block1.getText().toString().trim() +
                            block2.getText().toString().trim() +
                            block3.getText().toString().trim() +
                            block4.getText().toString().trim() +
                            block5.getText().toString().trim() +
                            block6.getText().toString().trim();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, code);

                    FirebaseAuth
                            .getInstance()
                            .signInWithCredential(credential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                       @Override
                                                       public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                                           if (task.isSuccessful()) {
                                                               VerifyProgress.setVisibility(View.VISIBLE);
                                                               verifybtn.setVisibility(View.INVISIBLE);
                                                               Toast.makeText(OTPmobile.this, "Welcome...", Toast.LENGTH_SHORT).show();
                                                               Intent intent = new Intent(OTPmobile.this, MainActivity.class);
                                                               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                               startActivity(intent);
                                                           } else {
                                                               VerifyProgress.setVisibility(View.GONE);
                                                               verifybtn.setVisibility(View.VISIBLE);
                                                               Toast.makeText(OTPmobile.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                                                           }
                                                       }
                                                   }
                            );
                } else {
                    Toast.makeText(OTPmobile.this, "Fill the OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
        moveblock();
    }

    private void moveblock() {
        block1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    block2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        block2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    block3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        block3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    block4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        block4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    block5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        block5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    block6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}