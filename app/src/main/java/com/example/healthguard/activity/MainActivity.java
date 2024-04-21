package com.example.healthguard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.healthguard.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
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


    }
}