package com.example.nhom29_doancuoiky.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.nhom29_doancuoiky.ForgotPassword;
import com.example.nhom29_doancuoiky.Home;
import com.example.nhom29_doancuoiky.R;

public class LoginTabFragment extends Fragment {
    EditText txtEmail,txtPass;
    Button btnLogin;
    TextView tvQuenMK;
    float v = 0;
    ViewGroup root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);

        setControl();
        setAppearance();
        setEvent();

        return root;
    }

    private void setControl() {
        txtEmail = root.findViewById(R.id.txtEmail);
        txtPass = root.findViewById(R.id.txtPass);
        btnLogin = root.findViewById(R.id.btnLogin);
        tvQuenMK = root.findViewById(R.id.tvQuenMK);
    }

    private void setAppearance() {
        txtEmail.setTranslationX(800);
        txtPass.setTranslationX(800);
        btnLogin.setTranslationX(800);
        tvQuenMK.setTranslationX(800);

        txtEmail.setAlpha(v);
        txtPass.setAlpha(v);
        btnLogin.setAlpha(v);
        tvQuenMK.setAlpha(v);

        txtEmail.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        txtPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        btnLogin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        tvQuenMK.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
    }

    private void setEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataCheck()){
                    Intent intent = new Intent(getContext(), Home.class);
                    startActivity(intent);
                }
            }
        });

        tvQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    private boolean dataCheck() {
        if(txtEmail.getText().toString().trim().equals("")
                || txtPass.getText().toString().trim().equals("")){
            Toast.makeText(getContext(), "Please enter your account info", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
}
