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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom29_doancuoiky.ForgotPassword;
import com.example.nhom29_doancuoiky.Home;
import com.example.nhom29_doancuoiky.R;
import com.example.nhom29_doancuoiky.constant.ApiConstant;
import com.example.nhom29_doancuoiky.converter.UserConverter;
import com.example.nhom29_doancuoiky.response.UserApiResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

        txtEmail.setText("bingbang004@gmail.com");
        txtPass.setText("123");

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

                    Map<String,String> params = new HashMap<>();
                    params.put("email",txtEmail.getText().toString());
                    params.put("password",txtPass.getText().toString());
                    JSONObject jsonObject = new JSONObject(params);

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    String url = ApiConstant.URL_API +"user/login";
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                            //api call success
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    UserApiResponse userApiResponse = new UserConverter().toApiResponse(response);
                                    Toast.makeText(getContext(), "Welcome "+ userApiResponse.getName(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getContext(), Home.class);
                                    intent.putExtra("user",userApiResponse);
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
