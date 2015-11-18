package com.mma.mymessagingapp.model;

import java.util.ArrayList;

/**
 * Created by tayo on 11/15/2015.
 */
public class Conversation {

    public String sender;
    public ArrayList<Message> messages;

    public Conversation(String sender) {
        this.sender = sender;
        this.messages = new ArrayList<Message>();
    }

    public Conversation(String sender, ArrayList<Message> messages) {
        this.sender = sender;
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }


    public void addMessage(Message message) {
        this.messages.add(message);
    }
}
