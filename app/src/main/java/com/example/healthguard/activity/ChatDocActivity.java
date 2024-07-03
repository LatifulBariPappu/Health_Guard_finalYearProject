package com.example.healthguard.activity;

import static com.example.healthguard.activity.LoginActivity.SHARED_PREFS;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthguard.R;

public class ChatDocActivity extends AppCompatActivity {
    TextView chatUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_doc);

        Users users=new Users();
        String username=users.getUserName();

        chatUsername=findViewById(R.id.chatUserName);
        chatUsername.setText(username);

    }
}