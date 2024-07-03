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
import com.example.healthguard.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;



public class SignupActivity extends AppCompatActivity {
    public static final String TAG="TAG";
    FirebaseDatabase database;
    FirebaseStorage storage;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        EditText edUsername,edEmail,edPassword,edConfirm;
        Button btn;
        TextView loginRedirectText;

        loginRedirectText=findViewById(R.id.LoginRedirectText);
        edUsername=findViewById(R.id.signup_username);
        edEmail=findViewById(R.id.sigup_email);
        edPassword=findViewById(R.id.signup_password);
        edConfirm=findViewById(R.id.signup_confirm_password);
        btn=findViewById(R.id.signup_btn);

        fauth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
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
                            String id = task.getResult().getUser().getUid();
                            DatabaseReference reference = database.getReference().child("user").child(id);
                            StorageReference storageReference = storage.getReference().child("Upload").child(id);
                            Toast.makeText(SignupActivity.this, "User Create", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(SignupActivity.this,LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);

                            FirebaseUser fuser=fauth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Users users=new Users(id,username,email,password);
                                    reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Intent i=new Intent(SignupActivity.this,LoginActivity.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(i);
                                        }
                                    });
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
    }

}