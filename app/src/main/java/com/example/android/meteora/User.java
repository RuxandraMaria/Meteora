package com.example.android.meteora;

import android.widget.ImageView;

import java.util.ArrayList;


public class User {
    private static int IDcounter;

    public int ID;
    public String userName;
    public String password;
    public String nickName;
    public String Status;
    public String Missions;

    private int userType;
    private int isActive;
    private ArrayList<Conversation> conversations;
    private int imageResourceID = HAS_NO_IMAGE;
    public static final int HAS_NO_IMAGE = -1;

    public User() {
        this.conversations = new ArrayList<>();
    }


    public User(String userName, String password, int userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType; //1-coordonator 2-astronaut
        this.isActive = 0;
        this.nickName = "";
        this.ID = IDcounter++;
        this.conversations = new ArrayList<Conversation>();
    }

    public User(String userName, String password, int userType, int imageResourceID) {
        this.userName = userName;
        this.password = password;
        this.userType = userType; //1-coordonator 2-astronaut
        this.isActive = 0;
        this.imageResourceID = imageResourceID;
        this.ID = IDcounter++;
        this.nickName = "";
        this.conversations = new ArrayList<Conversation>();
    }

    public String getUserName() {
        return userName;
    }

    public int getImageResourceID() { return imageResourceID;}

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
    public void setNickName(String nickName) { this.nickName = nickName;}
    public String getNickName(){ return nickName;}
    public void setImageResourceID(int imageResourceID) {
        this.imageResourceID = imageResourceID;
    }

    public boolean hasImage() {
        return imageResourceID != HAS_NO_IMAGE;
    }

    public void addConversation(String username) {
        if(DataBase.getInstance().getUser(username) != null)
            conversations.add(new Conversation(this, DataBase.getInstance().getUser(username)));
    }

    public  ArrayList<Conversation> getConversations() {
        return conversations;
    }

}