package com.example.android.meteora;

import java.util.ArrayList;

public class DataBase {
    private ArrayList<User> users = new ArrayList<>();
    private static DataBase instance = null;
    private User isLoggedNow;

    private DataBase() { }
    public static DataBase getInstance(){
        if(instance == null) {
            instance = new DataBase();
        }
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

    public void setWhoIsLoggedNow(String username) {
        if(getUser(username) != null) {
            isLoggedNow = getUser(username);
            getUser(username).setActive(1);
        }
    }

    public User getWhoIsLoggedNow() {
        return isLoggedNow;
    }

    public void populateDataBase(){
        users.add(new User("01_alexandru", "mission11", 2,  R.drawable.photo));
        users.add(new User("02_cristina", "mission12", 2,  R.drawable.logonasa));
        users.add(new User("03_ruxandra", "mission13",2));
        users.add(new User("C_admin", "coord11", 1));

    }

}
