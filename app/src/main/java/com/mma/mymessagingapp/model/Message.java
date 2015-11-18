package com.mma.mymessagingapp.model;

import java.util.Date;

/**
 * Created by tayo on 11/15/2015.
 */
public class Message {

    public String Content;
    public String Sender;
    public String Recipient;
    public Date Time;

    public Message(String content, String sender, String recipient, Date time) {
        Content = content;
        Sender = sender;
        Recipient = recipient;
        Time = time;
    }
}
