package com.example.android.meteora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        spinner = (Spinner) findViewById(R.id.spinner);
        list.add("New conv");
        list.add("New Conversation");
        list.add("Settings");
        list.add("Help");
        list.add("Logout");
        final EmptyFirstItemAdapter dataAdapter = new EmptyFirstItemAdapter(this, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private boolean mInitialized = false;

            public void onClick(View v) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int position, long id) {
                final Intent intent;
                if (!mInitialized && position == 0 && id == 1) {
                    // User selected the 1st item after the 'empty' item was initially removed,
                    // update the data set to compensate for the removed item.
                    mInitialized = true;
                    dataAdapter.notifyDataSetChanged();
                } else if (mInitialized){
                    switch (position) {
                        case 0:
                            intent = new Intent(Menu.this, NewConversation.class);
                            startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(Menu.this, Settings.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(Menu.this, Help.class);
                            startActivity(intent);
                            break;
                        case 3:
                            finish();
                            System.exit(0);
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });

    }

}
