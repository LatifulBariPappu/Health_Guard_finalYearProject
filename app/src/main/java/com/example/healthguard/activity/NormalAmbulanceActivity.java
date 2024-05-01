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

public class NormalAmbulanceActivity extends AppCompatActivity {

    Button callBtn,backBtn;
    static int PERMISSION_CODE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_ambulance);

        callBtn=findViewById(R.id.buttonCallNormalAmb);
        backBtn=findViewById(R.id.buttonBackNormalAmb);

        if(ContextCompat.checkSelfPermission(NormalAmbulanceActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(NormalAmbulanceActivity.this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);
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
                startActivity(new Intent(NormalAmbulanceActivity.this, AmbulanceActivity.class));
            }
        });
    }
}