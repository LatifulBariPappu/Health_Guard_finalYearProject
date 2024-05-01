package com.example.healthguard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.healthguard.R;

public class AmbulanceActivity extends AppCompatActivity {
    Button btnback;
    CardView amb1,amb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        amb1=findViewById(R.id.cardViewAmb1);
        amb2=findViewById(R.id.cardViewAmb2);
        btnback=findViewById(R.id.backbuttonAmb);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AmbulanceActivity.this, MainActivity.class));
            }
        });
        amb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AmbulanceActivity.this,NormalAmbulanceActivity.class));
            }
        });
        amb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AmbulanceActivity.this,FrezzAmulanceActivity.class));
            }
        });
    }
}