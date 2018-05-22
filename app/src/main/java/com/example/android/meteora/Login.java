package com.example.android.meteora;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

public class Login extends AppCompatActivity {

    public static Context context;

    public static TCPClient client;

    public static Handler mainHandler;

    public static int MY_ID;
    public static String LOGIN_NAME;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button exit = (Button) findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        MY_ID = 0;

        Login.mainHandler = new Handler();

        context = this;





        Button login = (Button) findViewById(R.id.login);

        // Set a click listener on that View
        login.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {

                TextView username = (TextView) findViewById(R.id.username_view);
                TextView password = (TextView) findViewById(R.id.password_view);

                LOGIN_NAME = username.getText().toString();

                if (Login.client == null)
                {
                    Login.client = new TCPClient("192.168.100.3", 12345);
                    Login.client.start();
                }

                Login.client.postAction(ConnectionFlag.S_LOGIN,
                        new UserLogin(username.getText().toString(),
                                password.getText().toString()));

//
//
//                DataBase.getInstance().populateDataBase();
//                TextView username = (TextView) findViewById(R.id.username_view);
//                TextView password = (TextView) findViewById(R.id.password_view);
//                if (DataBase.getInstance().getUser(username.getText().toString()) != null) {
//                    if (DataBase.getInstance().getUser(username.getText().toString()).getPassword().compareTo(password.getText().toString()) == 0) {
//                        DataBase.getInstance().setWhoIsLoggedNow(username.getText().toString());
//                        Intent loginIntent = new Intent(Login.this, Menu.class);
//
//                        //fortest
//                        DataBase.getInstance().getWhoIsLoggedNow().addConversation("01_alexandru");
//                        DataBase.getInstance().getWhoIsLoggedNow().addConversation("02_cristina");
//
//                        startActivity(loginIntent);
//                    } else {
//                        Toast.makeText(Login.this, getResources().getText(R.string.wrong_password).toString(), Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(Login.this, getResources().getText(R.string.wrong_username).toString(), Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}
