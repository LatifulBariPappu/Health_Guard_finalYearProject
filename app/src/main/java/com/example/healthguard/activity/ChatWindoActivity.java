package com.example.healthguard.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthguard.R;

public class ChatWindoActivity extends AppCompatActivity {

    TextView reciverNName;
    String  reciverUid,reciverName,SenderUID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_windo);

        reciverName = getIntent().getStringExtra("nameeee");
        reciverUid = getIntent().getStringExtra("uid");


        reciverNName = findViewById(R.id.recivername);
        reciverNName.setText(""+reciverName);
    }
}