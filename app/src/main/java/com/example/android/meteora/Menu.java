package com.example.android.meteora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    private Spinner spinner;
    public static ArrayList<Conversation> conversations = new ArrayList<Conversation>();
    private List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        conversations.addAll(DataBase.getInstance().getWhoIsLoggedNow().getConversations());
        ConversationAdapter itemsAdapter = new ConversationAdapter(this, conversations, R.color.color_conversation);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        final Intent intent;
        switch(id) {
            case R.id.settings:
                intent = new Intent(Menu.this, Settings.class);
                startActivity(intent);
                break;
            case R.id.help:
                intent = new Intent(Menu.this, Help.class);
                startActivity(intent);
                break;
            case R.id.new_conv:
                intent = new Intent(Menu.this, ConversationScreen.class);
                startActivity(intent);
                break;
            case R.id.logout:
                finish();
                System.exit(0);
                break;
            default:
                break;
        }
        return true;
    }
}
