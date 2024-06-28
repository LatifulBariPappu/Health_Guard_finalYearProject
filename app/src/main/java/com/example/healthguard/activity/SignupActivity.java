package com.example.healthguard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthguard.database.Database;
import com.example.healthguard.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    public static final String TAG="TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);


        EditText edUsername,edEmail,edPassword,edConfirm;
        Button btn;
        TextView loginRedirectText;
        FirebaseAuth fauth;
        loginRedirectText=findViewById(R.id.LoginRedirectText);
        edUsername=findViewById(R.id.signup_username);
        edEmail=findViewById(R.id.sigup_email);
        edPassword=findViewById(R.id.signup_password);
        edConfirm=findViewById(R.id.signup_confirm_password);
        btn=findViewById(R.id.signup_btn);

        fauth=FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edUsername.getText().toString();
                String email=edEmail.getText().toString();
                String password=edPassword.getText().toString();
                String confirm=edConfirm.getText().toString();
                if(TextUtils.isEmpty((username))){
                    edUsername.setError("Name is empty");
                    return;
                }
                if(TextUtils.isEmpty((email))){
                    edEmail.setError("Email is empty");
                    return;
                }
                if(TextUtils.isEmpty((password))){
                    edPassword.setError("Password is Required");
                    return;
                }
                if(password.length()<6){
                    edPassword.setError("Password must be <= 6 character");
                    return;
                }
                if(confirm.isEmpty() || !password.equals(confirm)){
                    edConfirm.setError("Invalid Password");
                    return;
                }
                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "User Create", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(SignupActivity.this,LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);


                            FirebaseUser fuser=fauth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(SignupActivity.this, "Verification Email has been send", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure : Email not sent "+e.getMessage());
                                }
                            });

                        }else{
                            Toast.makeText(SignupActivity.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String username=edUsername.getText().toString();
//                String email=edEmail.getText().toString();
//                String password=edPassword.getText().toString();
//                String confirm=edConfirm.getText().toString();
//                Database db=new Database(getApplicationContext(),"healthcare",null,1);
//                if(username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0 ){
//                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
//                }else{
//                    if(password.compareTo(confirm)==0){
//                        if(isValid(password)){
//                            db.register(username,email,password);
//                            Toast.makeText(getApplicationContext(), "Record inserted", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
//
//                        }else {
//                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters,having letter,digit and special symbol", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }else{
//                        Toast.makeText(getApplicationContext(), "Password and confirm password didn't match", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
    }
//    public static boolean isValid(String passwordhere){
//        int f1=0,f2=0,f3=0;
//        if(passwordhere.length()<8){
//            return false;
//        }else{
//            for(int p=0;p<passwordhere.length();p++){
//                if(Character.isLetter(passwordhere.charAt(p))){
//                    f1=1;
//                }
//            }
//            for(int r=0;r<passwordhere.length();r++){
//                if(Character.isDigit(passwordhere.charAt(r))){
//                    f2=1;
//                }
//            }
//            for(int s=0;s<passwordhere.length();s++){
//                char c=passwordhere.charAt(s);
//                if(c<=33&&c<=46 || c==64){
//                    f3=1;
//                }
//            }
//            if(f1==1 && f2==1 && f3==1){
//                return true;
//            }else {
//                return false;
//            }
//        }
//    }
}