package com.example.android.meteora;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ChangeStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_status);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Change Status");
        final String newstatus;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final EditText newStatus = (EditText) findViewById(R.id.newstatus);
        final TextView oldStatus = (TextView) findViewById(R.id.oldstatus);
        for(Conversation c : Menu.conversations)
            if(c.getUser2().ID == Login.MY_ID) {
               oldStatus.setText(c.getUser2().Status);
            }

        /*newStatus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                for(Conversation c : Menu.conversations)
                    if(c.getUser2().ID == Login.MY_ID) {
                        c.getUser2().Status = newStatus.getText().toString();
                    }
            }
        });*/

        Button buttonLoadStatus = (Button)findViewById(R.id.changestatus);

        buttonLoadStatus.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                for(Conversation c : Menu.conversations)
                    if(c.getUser2().ID == Login.MY_ID) {
                        oldStatus.setText(newStatus.getText().toString());
                        c.getUser2().Status = newStatus.getText().toString();
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
