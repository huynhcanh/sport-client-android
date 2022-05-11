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

import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;


public class ForgotPassword extends AppCompatActivity {
    EditText txtEmail;
    Button btnSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        txtEmail = findViewById(R.id.txtEmail);
        btnSend = findViewById(R.id.btnSend);

        setEvent();
    }

    private void setEvent() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtEmail.equals("")) {
                    final String username = "johnnyhoang482@gmail.com";
                    final String password = "ahtxuatpvsbecehk";
                    Random random = new Random();
                    int randomNumber = random.nextInt(999999);
                    String messageToSend = String.valueOf(randomNumber);
                    Properties props = new Properties();
                    props.put("mail.smtp.auth","true");
                    props.put("mail.smtp.starttls.enable","true");
                    props.put("mail.smtp.host","smtp.gmail.com");
                    props.put("mail.smtp.port","587");
                    Session session = Session.getInstance(props,
                            new javax.mail.Authenticator(){
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username, password);
                                }
                            });
                    try{

                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(username));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtEmail.getText().toString()));
                        message.setSubject("Thay đổi mật khẩu!");
                        message.setText(messageToSend);
                        Transport.send(message);
                        Toast.makeText(getApplicationContext(),"Password mới đã được gửi vào email",Toast.LENGTH_LONG).show();
                    }catch (MessagingException e){
                        throw new RuntimeException();
                    }

                    Intent intent = new Intent(ForgotPassword.this, Login.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ForgotPassword.this, "Vui lòng nhập tài khoản!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
