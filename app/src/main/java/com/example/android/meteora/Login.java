package com.example.android.meteora;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

public class Login extends AppCompatActivity {

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



        Button login = (Button) findViewById(R.id.login);

        // Set a click listener on that View
        login.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                DataBase.getInstance().populateDataBase();
                TextView username = (TextView) findViewById(R.id.username_view);
                TextView password = (TextView) findViewById(R.id.password_view);
                if (DataBase.getInstance().getUser(username.getText().toString()) != null) {
                    if (DataBase.getInstance().getUser(username.getText().toString()).getPassword().compareTo(password.getText().toString()) == 0) {
                        DataBase.getInstance().setWhoIsLoggedNow(username.getText().toString());
                        Intent loginIntent = new Intent(Login.this, Menu.class);

                        //fortest
                        DataBase.getInstance().getWhoIsLoggedNow().addConversation("01_alexandru");
                        DataBase.getInstance().getWhoIsLoggedNow().addConversation("02_cristina");

                        startActivity(loginIntent);
                    } else {
                        Toast.makeText(Login.this, getResources().getText(R.string.wrong_password).toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, getResources().getText(R.string.wrong_username).toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
