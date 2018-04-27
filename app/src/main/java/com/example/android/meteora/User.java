package com.example.android.meteora;

import android.widget.ImageView;

public class User {
    private static int IDcounter;
    private int ID;
    private String userName;
    private String password;
    private int userType;
    private int isActive;
    private ImageView avatar;

    public User(String userName, String password, int userType, ImageView avatar) {
        this.userName = userName;
        this.password = password;
        this.userType = userType; //1-coordonator 2-astronaut
        this.isActive = 0;
        this.avatar = avatar;
        this.ID = IDcounter++;
    }

    public String getUserName() {
        return userName;
    }

    public ImageView getAvatar() { return avatar;}

    public String getPassword() {
        return password;
    }

    public int getUserType() {
        return userType;
    }
    public int isActive() {
        return isActive;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setActive(int isActive) {
        this.isActive = isActive;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

}