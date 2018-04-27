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
                //de scos urmatoarele 4 randuri, sunt de test
                ImageView image = new ImageView(Login.this);
                image.setImageResource(R.drawable.photo);
                User test = new User("ruxandra", "parolaruxi", 2, image);
                DataBase.getInstance().getUsers().add(test);

                TextView username = (TextView) findViewById(R.id.username_view);
                TextView password = (TextView) findViewById(R.id.password_view);
                if (DataBase.getInstance().getUser(username.getText().toString()) != null) {
                    if (DataBase.getInstance().getUser(username.getText().toString()).getPassword().compareTo(password.getText().toString()) == 0) {
                        DataBase.getInstance().getUser(username.getText().toString()).setActive(1);
                        Intent loginIntent = new Intent(Login.this, Menu.class);
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
