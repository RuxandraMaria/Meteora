package com.example.android.meteora;

import java.util.ArrayList;

public class DataBase {
    private ArrayList<User> users = new ArrayList<>();
    private static DataBase instance = null;

    private DataBase() { }
    public static DataBase getInstance(){
        if(instance == null)
            instance = new DataBase();
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUserName().compareTo(username) == 0)
                return user;
        }
        return null;
    }

}
