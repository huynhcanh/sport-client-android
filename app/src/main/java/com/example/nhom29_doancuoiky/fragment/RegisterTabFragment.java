package com.example.nhom29_doancuoiky.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.nhom29_doancuoiky.EnterOTP;
import com.example.nhom29_doancuoiky.Home;
import com.example.nhom29_doancuoiky.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterTabFragment extends Fragment {
    EditText txtEmail, txtName, txtPhone, txtPassword, txtConfirmPass;
    Button btnRegister;
    float v = 0;

    ViewGroup root;

    private final static String TAG = RegisterTabFragment.class.getName();
    FirebaseAuth mAuth;

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_register, container, false);

        mAuth = FirebaseAuth.getInstance();

        setControl();
        setAppearance();
        setEvent();

        return root;
    }

    private void setControl() {
        txtEmail = root.findViewById(R.id.txtEmail);
        txtName = root.findViewById(R.id.txtName);
        txtPhone = root.findViewById(R.id.txtPhone);
        txtPassword = root.findViewById(R.id.txtPassword);
        txtConfirmPass = root.findViewById(R.id.txtConfirmPass);
        btnRegister = root.findViewById(R.id.btnRegister);
    }

    private void setEvent() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataCheck()) {
                    String phoneNumber = "+84" + txtPhone.getText().toString();
                    startVerifyPhoneNumber(phoneNumber);
                }
            }
        });
    }

    private void setAppearance() {
        txtEmail.setTranslationX(800);
        txtName.setTranslationX(800);
        txtPhone.setTranslationX(800);
        txtPassword.setTranslationX(800);
        txtConfirmPass.setTranslationX(800);
        btnRegister.setTranslationX(800);

        txtEmail.setAlpha(v);
        txtName.setAlpha(v);
        txtPhone.setAlpha(v);
        txtPassword.setAlpha(v);
        txtConfirmPass.setAlpha(v);
        btnRegister.setAlpha(v);

        txtEmail.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        txtName.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        txtPhone.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        txtPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        txtConfirmPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        btnRegister.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
    }

    private boolean dataCheck() {
        if(txtEmail.getText().toString().trim().equals("")
                || txtName.getText().toString().trim().equals("")
                || txtPhone.getText().toString().trim().equals("")
                || txtPassword.getText().toString().trim().equals("")
                || txtConfirmPass.getText().toString().trim().equals("")){
            Toast.makeText(getContext(), "Please enter all info", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!txtPassword.getText().toString().equals(txtConfirmPass.getText().toString())){
            Toast.makeText(getContext(), "Password confirm not match", Toast.LENGTH_SHORT).show();
            return false;
        } else{
            return true;
        }
    }

    private void startVerifyPhoneNumber(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(getActivity())                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(getContext(), "Verification Fail!", Toast.LENGTH_SHORT).show();
                            }

                            @Override // dt ko tu verify dc ma phai nhap
                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationID, forceResendingToken);
//                                goToEnterOTP(phoneNumber,verificationID);
                                Intent intent = new Intent(getActivity(), EnterOTP.class);
                                intent.putExtra("phone_number", phoneNumber);
                                intent.putExtra("verification_id", verificationID);
                                startActivity(intent);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            Intent intent = new Intent(getActivity(),Home.class);
                            startActivity(intent);
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(getActivity(), "The verification code was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
