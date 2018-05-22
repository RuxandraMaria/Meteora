package com.example.android.meteora;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_status);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Change Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        EditText newStatus = (EditText) findViewById(R.id.newstatus);
        TextView oldStatus = (TextView) findViewById(R.id.oldstatus);
        for(Conversation c : Menu.conversations)
            if(c.getUser2().ID == Login.MY_ID) {
               newStatus.setText(c.getUser2().Status);
            }

        newStatus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {

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
