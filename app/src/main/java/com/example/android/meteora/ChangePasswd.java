package com.example.android.meteora;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePasswd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passwd);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button changePassword = (Button) findViewById(R.id.create_account);

        // Set a click listener on that View
        changePassword.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                TextView old_pass1 = (TextView) findViewById(R.id.old_pass1);
                TextView old_pass2 = (TextView) findViewById(R.id.old_pass2);
                TextView new_pass1 = (TextView) findViewById(R.id.new_pass1);
                TextView new_pass2 = (TextView) findViewById(R.id.new_pass2);
                if (old_pass1.getText().toString().compareTo(old_pass2.getText().toString()) != 0)
                    Toast.makeText(ChangePasswd.this, getResources().getText(R.string.old_not_the_same).toString(), Toast.LENGTH_SHORT).show();
                else if (new_pass1.getText().toString().compareTo(new_pass2.getText().toString()) != 0)
                    Toast.makeText(ChangePasswd.this, getResources().getText(R.string.new_not_the_same).toString(), Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(ChangePasswd.this, getResources().getText(R.string.pass_changed).toString(), Toast.LENGTH_SHORT).show();
                }
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
