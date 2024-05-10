package com.example.healthguard.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.healthguard.R;

public class HospitalNearMeActivity extends AppCompatActivity {
    WebView mWebView;
    ImageView btnBack;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_near_me);

        mWebView=findViewById(R.id.webViewNearHospitals);
        btnBack=findViewById(R.id.backButtonHospitals);
        mWebView.setWebViewClient(new WebViewClient()); // to handle URL redirects in the app
        mWebView.getSettings().setJavaScriptEnabled(true); // to enable JavaScript on web pages
        mWebView.getSettings().setGeolocationEnabled(true); // to enable GPS location on web pages
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                if (ActivityCompat.checkSelfPermission(HospitalNearMeActivity.this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(HospitalNearMeActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                } else {
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        String latitude = String.valueOf(location.getLatitude());
                        String longitude = String.valueOf(location.getLongitude());
                        String url = "https://www.example.com?lat=" + latitude + "&long=" + longitude;
                        mWebView.loadUrl(url);
                        callback.invoke(origin, true, true);
                    } else {
                        callback.invoke(origin, false, false);
                    }
                }
            }

            @Override
            public void onPermissionRequest(PermissionRequest request) {
                if (request.getOrigin().toString().startsWith("https://")) {
                    request.grant(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION});
                } else {
                    super.onPermissionRequest(request);
                }
            }
        });
        // Check if location permission is granted
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request location permission if not granted
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Load the web page if location permission is granted
            mWebView.loadUrl("https://www.google.com/maps/search/hospitals+near+me/");
        }


        mWebView.loadUrl("https://www.google.com/maps/search/hospitals+near+me/");


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HospitalNearMeActivity.this, MainActivity.class));
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mWebView.loadUrl("https://www.google.com/maps/search/hospitals+near+me/"); // to load the web page after location permission is granted
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}