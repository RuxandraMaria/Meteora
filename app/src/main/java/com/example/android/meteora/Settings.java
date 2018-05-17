package com.example.android.meteora;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Change password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView changePassword = (TextView) findViewById(R.id.change_password);

        // Set a click listener on that View
        changePassword.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent passwordIntent = new Intent(Settings.this, ChangePasswd.class);
                startActivity(passwordIntent);
            }
        });

        TextView changePhoto = (TextView) findViewById(R.id.change_photo);

        // Set a click listener on that View
        changePhoto.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(Settings.this, ChangePhoto.class);
                startActivity(photoIntent);
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
