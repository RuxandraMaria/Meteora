package com.example.android.meteora;


import android.content.Intent;
import android.graphics.Path;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.example.android.meteora.ConnectionFlag;
import com.example.android.meteora.Login;
import com.example.android.meteora.Menu;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HandlerJob implements Runnable
{
    JSONObject obj;
    int action;
    ArrayList<User> users;
    SendMessage messge;
    User user;



    public HandlerJob(int action)
    {
        this.action = action;
    }

    public HandlerJob(int action, ArrayList<User> users)
    {
        this.users = users;
        this.action = action;
    }

    public HandlerJob(int action, SendMessage messge)
    {
        this.messge = messge;
        this.action = action;
    }

    public HandlerJob(int action, User user)
    {
        this.user = user;
        this.action = action;
    }


    public void run()
    {
        switch (action)
        {
            case ConnectionFlag.S_LOGIN_OK:
            {
                Intent intent = new Intent(Login.context, Menu.class);
                Login.context.startActivity(intent);

                break;
            }

            case ConnectionFlag.S_LOGIN_NAME:
            {
                System.out.println("Login NO name");
                System.out.println("Login NO name");
                System.out.println("Login NO name");
                System.out.println("Login NO name");
                break;
            }


            case ConnectionFlag.S_LOGIN_PASSWORD:
            {
                System.out.println("Login NO pass");
                System.out.println("Login NO pass");
                System.out.println("Login NO pass");
                System.out.println("Login NO pass");
                break;
            }

            case ConnectionFlag.S_INIT_DATA:
            {
                System.out.println("S_INIT_DATA");

                Menu.conversations.clear();
                for (User user : users)
                {
                    Menu.conversations.add(new Conversation(new User(), user));
                }

                break;
            }

            case ConnectionFlag.S_REGISTER_NOTIFY:
            {
                System.out.println("S_REGISTER_NOTIFY ");

                Menu.conversations.add(new Conversation(new User(), user));
                Menu.itemsAdapter.notifyDataSetChanged();

//                Menu.

                break;
            }



            case ConnectionFlag.S_MESSAGE:
            {
                for (Conversation conv : Menu.conversations)
                {
                    if (conv.getUser2().ID == messge.dest)
                    {
                        conv.messages.add(messge);
                        break;
                    }
                }

                if (ConversationScreen.Activity != null)
                {
                    ConversationScreen.Activity.ReceiveMessage(messge);
                }

                break;
            }

        }
    }
}