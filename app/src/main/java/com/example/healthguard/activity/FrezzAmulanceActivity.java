package com.example.healthguard.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthguard.R;

public class FrezzAmulanceActivity extends AppCompatActivity {
    Button callBtn,backBtn;
    static int PERMISSION_CODE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frezz_amulance);

        callBtn=findViewById(R.id.buttonCallFrezzAmb);
        backBtn=findViewById(R.id.buttonBackFrezzAmb);

        if(ContextCompat.checkSelfPermission(FrezzAmulanceActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(FrezzAmulanceActivity.this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);
        }
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo="01706674586";
                Intent i=new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+phoneNo));
                startActivity(i);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FrezzAmulanceActivity.this, AmbulanceActivity.class));
            }
        });
    }
}