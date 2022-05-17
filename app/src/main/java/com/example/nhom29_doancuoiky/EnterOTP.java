package com.example.nhom29_doancuoiky;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class EnterOTP extends AppCompatActivity {
    TextView txtOTP;
    Button btnSend;

    FirebaseAuth mAuth;
    String mPhoneNumber;
    String mVerificationID;

    private final static String TAG = EnterOTP.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);

        txtOTP = findViewById(R.id.txtOTP);
        btnSend = findViewById(R.id.btnSend);

        getDataIntent();
        mAuth = FirebaseAuth.getInstance();

        setEvent();
    }

    private void setEvent() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = txtOTP.getText().toString().trim();
                startSendOTPCode(otp);
            }
        });
    }

    private void getDataIntent() {
        mPhoneNumber = getIntent().getStringExtra("phone_number");
        mVerificationID = getIntent().getStringExtra("verification_id");
    }

    private void startSendOTPCode(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationID, otp);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            Intent intent = new Intent(EnterOTP.this, Home.class);
                            startActivity(intent);
                            Toast.makeText(EnterOTP.this, "OTP verification success", Toast.LENGTH_SHORT).show();

//                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(EnterOTP.this,
                                        "The verification code was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
