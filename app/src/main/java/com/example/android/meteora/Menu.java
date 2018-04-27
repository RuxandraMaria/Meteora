package com.example.android.meteora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ArrayList<Conversation> conversations = new ArrayList<Conversation>();

        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.photo);
        User test = new User("Cristina", "parolacristina", 2, image);
        DataBase.getInstance().getUsers().add(test);
        Conversation conv = new Conversation(DataBase.getInstance().getUsers().get(0), test);
        conversations.add(conv);
        conversations.add(conv);
        ConversationAdapter itemsAdapter = new ConversationAdapter(this, conversations);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }
}
