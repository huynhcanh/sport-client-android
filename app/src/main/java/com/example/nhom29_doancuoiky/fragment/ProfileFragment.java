package com.example.nhom29_doancuoiky.fragment;

import android.content.Intent;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom29_doancuoiky.Home;
import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.constant.ApiConstant;
import com.example.nhom29_doancuoiky.converter.UserConverter;
import com.example.nhom29_doancuoiky.response.UserApiResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {
    EditText txtEmail, txtPhone, txtName, txtPassword;
    Button btnSave, btnCancel;
    ViewGroup root;
    UserApiResponse userApiResponse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);

        setControl();
        setData();
        setEvent();

        return root;
    }

    private void setData() {
        txtEmail.setText(ApiConstant.userLog.getEmail());
        txtPhone.setText(ApiConstant.userLog.getPhone());
        txtName.setText(ApiConstant.userLog.getName());
    }

    private void setEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataCheck()) {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", ApiConstant.userLog.getId().toString());
                    params.put("email", txtEmail.getText().toString());
                    params.put("password", txtPassword.getText().toString());
                    params.put("name", txtName.getText().toString());
                    params.put("phone", txtPhone.getText().toString());

                    JSONObject jsonObject = new JSONObject(params);

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    String url = ApiConstant.URL_API + "user";
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject,
                            //api call success
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    ApiConstant.userLog = new UserConverter().toApiResponse(response);
                                    Intent intent = new Intent(getContext(), Home.class);
                                    startActivity(intent);
                                }
                            },
                            //api call fail
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getContext(), "Ops! Please try again!", Toast.LENGTH_SHORT).show();
                                }
                            });
                    requestQueue.add(jsonObjectRequest);

                    Toast.makeText(getContext(), "Save Done !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Home.class);
                startActivity(intent);
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
        if (txtEmail.getText().toString().trim().equals("")
                || txtName.getText().toString().trim().equals("")
                || txtPhone.getText().toString().trim().equals("")
                || txtPassword.getText().toString().trim().equals("")) {
            Toast.makeText(getContext(), "Please enter all info", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}
