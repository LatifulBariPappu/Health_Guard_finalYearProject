package com.example.healthguard.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.healthguard.database.Database;
import com.example.healthguard.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edUsername,edPassword;
        Button btn,doctor_btn;
        TextView signUpRedirectText;
        edUsername=findViewById(R.id.login_email);
        edPassword=findViewById(R.id.login_password);
        btn=findViewById(R.id.login_btn);
        signUpRedirectText=findViewById(R.id.signupRedirectText);
        doctor_btn=findViewById(R.id.doctorRedirectLogin);
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edUsername.getText().toString();
                String password=edPassword.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill all details",Toast.LENGTH_SHORT).show();
                }else{
                    if(db.login(username,password)==1){
                        Toast.makeText(getApplicationContext(),"Let us check...",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        //to save our data with key and value
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Invalid username and password",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        signUpRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });

        doctor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, DoctorLoginActivity.class));
            }
        });
    }
}