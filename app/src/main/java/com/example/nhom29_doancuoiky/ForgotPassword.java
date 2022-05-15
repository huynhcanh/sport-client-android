package com.example.nhom29_doancuoiky;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom29_doancuoiky.constant.ApiConstant;
import com.example.nhom29_doancuoiky.converter.UserConverter;
import com.example.nhom29_doancuoiky.response.UserApiResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ForgotPassword extends AppCompatActivity {
    EditText txtEmail;
    Button btnSend;
    UserApiResponse userApiResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        txtEmail = findViewById(R.id.txtEmail);
        btnSend = findViewById(R.id.btnSend);

        setEvent();
    }

    private UserApiResponse getUser(String email) {
        RequestQueue requestQueue = Volley.newRequestQueue(ForgotPassword.this);
        String url = ApiConstant.URL_API + "user?email=" + email;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                //api call success
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        userApiResponse = new UserConverter().toApiResponse(response);
                    }
                },
                //api call fail
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);
        return userApiResponse;
    }

    private void setEvent() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                if (!email.equals("")) {
                    RequestQueue requestQueue = Volley.newRequestQueue(ForgotPassword.this);
                    String url = ApiConstant.URL_API + "user?email=" + email;
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                            //api call success
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    userApiResponse = new UserConverter().toApiResponse(response);

                                    final String username = "johnnyhoang482@gmail.com";
                                    final String password = "ahtxuatpvsbecehk";
                                    Random random = new Random();
                                    int randomNumber = random.nextInt(999999);
                                    String messageToSend = String.valueOf(randomNumber);
                                    Properties props = new Properties();
                                    props.put("mail.smtp.auth", "true");
                                    props.put("mail.smtp.starttls.enable", "true");
                                    props.put("mail.smtp.host", "smtp.gmail.com");
                                    props.put("mail.smtp.port", "587");
                                    Session session = Session.getInstance(props,
                                            new javax.mail.Authenticator() {
                                                @Override
                                                protected PasswordAuthentication getPasswordAuthentication() {
                                                    return new PasswordAuthentication(username, password);
                                                }
                                            });
                                    try {
                                        Message message = new MimeMessage(session);
                                        message.setFrom(new InternetAddress(username));
                                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                                        message.setSubject("Thay đổi mật khẩu!");
                                        message.setText(messageToSend);
                                        Transport.send(message);
                                    } catch (MessagingException e) {
                                        throw new RuntimeException();
                                    }

                                    RequestQueue requestQueue = Volley.newRequestQueue(ForgotPassword.this);
                                    String url = ApiConstant.URL_API + "user";
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id", userApiResponse.getId().toString());
                                    params.put("password", messageToSend);

                                    JSONObject jsonObject = new JSONObject(params);
                                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject,
                                            //api call success
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    Toast.makeText(view.getContext(), "Mail đã được gửi đi", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(view.getContext(), Login.class);
                                                    startActivity(intent);
                                                }
                                            },
                                            //api call fail
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                }
                                            });
                                    requestQueue.add(jsonObjectRequest);

                                }
                            },
                            //api call fail
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(view.getContext(), "Email này chưa được đăng kí  !", Toast.LENGTH_SHORT).show();
                                }
                            });
                    requestQueue.add(jsonObjectRequest);

                } else {
                    Toast.makeText(view.getContext(), "Email trống !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
