package com.example.android.meteora;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class InfoIntent extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        TextView tv = (TextView) findViewById(R.id.infoName);

        int pos = getIntent().getIntExtra("position", 0);

        User usr = Menu.conversations.get(pos).getUser2();


        String text = "";

        text += "Username: " + usr.userName + "\n";
        text += "Password: " + usr.password + "\n";
        text += "Nickname: " + usr.nickName + "\n";
        text += "Status: " + usr.Status + "\n";
        text += "Missions: " + usr.Missions + "\n";

        tv.setText(text);

    }
}
