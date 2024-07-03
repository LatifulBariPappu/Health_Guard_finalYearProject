package com.example.healthguard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.healthguard.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String SHARED_PREFS="sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageSlider imageSlider=findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels=new ArrayList<SlideModel>();

        slideModels.add(new SlideModel(R.drawable.onlinemed, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.finddoctor, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.medicines, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.appoinment, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.pharmacy, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ambulance, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(), "Welcome "+username, Toast.LENGTH_SHORT).show();

        CardView exit=findViewById(R.id.cardLogout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("name","");
                editor.apply();

                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });

        CardView findDoctor=findViewById(R.id.cardFidDoctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,FindDoctorActivity.class));
            }
        });
        CardView labTest=findViewById(R.id.cardLabTest);
        labTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LabTestActivity.class));
            }
        });
        CardView orderDetails=findViewById(R.id.cardOrderDetails);
        orderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,OrderDetailsActivity.class));
            }
        });
        CardView buyMedicine=findViewById(R.id.cardBuyMedicine);
        buyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,BuyMedicineActivity.class));
            }
        });

        CardView health=findViewById(R.id.cardHealthArticles);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,HealthArticlesActivity.class));
            }
        });

        CardView ambulance=findViewById(R.id.cardAmbulance);
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AmbulanceActivity.class));
            }
        });
        CardView hospital=findViewById(R.id.cardHospital);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HospitalNearMeActivity.class));
            }
        });
        CardView chat=findViewById(R.id.cardChat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, ChatDocActivity.class);
                i.putExtra("username",username);
                startActivity(i);
                finish();
            }
        });
    }
}