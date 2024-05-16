package com.example.healthguard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthguard.R;

public class DoctorLoginActivity extends AppCompatActivity {

    EditText username,password;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        username = findViewById(R.id.doctor_username);
        password = findViewById(R.id.doctor_password);
        loginButton = findViewById(R.id.doctor_login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("abdulHakim") && password.getText().toString().equals("doctor@123")) {
                    Toast.makeText(DoctorLoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DoctorLoginActivity.this,DoctorCallActivity.class));
                } else {
                    Toast.makeText(DoctorLoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });















    }
}