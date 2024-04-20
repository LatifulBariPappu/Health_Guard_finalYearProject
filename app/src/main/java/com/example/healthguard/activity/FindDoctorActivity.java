package com.example.healthguard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthguard.R;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_doctor);

        CardView exit=findViewById(R.id.cardFDbackbutton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindDoctorActivity.this,MainActivity.class));
            }
        });
        CardView familyphysician=findViewById(R.id.cardFDfamilyphisicians);
        familyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                it.putExtra("title","Top Family Physicians");
                startActivity(it);
            }
        });
        CardView dietitian=findViewById(R.id.cardFDdietitians);
        dietitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                it.putExtra("title","Top Dietitians");
                startActivity(it);
            }
        });
        CardView dentist=findViewById(R.id.cardFDdentists);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                it.putExtra("title","Top Dentists");
                startActivity(it);
            }
        });
        CardView sergion=findViewById(R.id.cardFDsergeons);
        sergion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                it.putExtra("title","Top Sergions");
                startActivity(it);
            }
        });
        CardView cardiologist=findViewById(R.id.cardFDcardiologists);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                it.putExtra("title","Top Cardiologists");
                startActivity(it);
            }
        });
    }
}