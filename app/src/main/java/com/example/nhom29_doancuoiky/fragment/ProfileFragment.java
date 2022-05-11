package com.example.nhom29_doancuoiky.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.model.User;

public class ProfileFragment extends Fragment {
    EditText txtEmail,txtPhone,txtName,txtPassword;
    Button btnSave, btnCancel;
    ViewGroup root;
    User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);

        user = new User(1,"johnnyhoang482@gmail.com","0355114279","Johnny Hoang","123");

        setControl();
        setData();
        setEvent();

        return root;
    }

    private void setData() {
        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone());
        txtName.setText(user.getName());
        txtPassword.setText(user.getPassword());
    }
    private void setEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataCheck()){
                    Toast.makeText(getContext(), "Save Clicked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Cancel Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setControl() {
        txtEmail = root.findViewById(R.id.txtEmail);
        txtPhone = root.findViewById(R.id.txtPhone);
        txtName = root.findViewById(R.id.txtName);
        txtPassword = root.findViewById(R.id.txtPassword);
        btnSave = root.findViewById(R.id.btnSave);
        btnCancel = root.findViewById(R.id.btnCancel);
    }
    private boolean dataCheck() {
        if(txtEmail.getText().toString().trim().equals("")
                || txtName.getText().toString().trim().equals("")
                || txtPhone.getText().toString().trim().equals("")
                || txtPassword.getText().toString().trim().equals("")){
            Toast.makeText(getContext(), "Please enter all info", Toast.LENGTH_SHORT).show();
            return false;
        } else{
            return true;
        }
    }

}
