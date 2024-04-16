package com.example.healthguard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;

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
        slideModels.add(new SlideModel(R.drawable.onlinemed, ScaleTypes.FIT));
    }
}