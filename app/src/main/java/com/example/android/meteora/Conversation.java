package com.example.android.meteora;

import android.text.style.UpdateLayout;

import java.util.Date;

public class Conversation {
    private User user1, user2;
    private Date ConversationBegin;

    public Conversation(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        ConversationBegin = new Date();
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public Date getConversationBegin() {
        return ConversationBegin;
    }
}
