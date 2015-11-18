package com.mma.mymessagingapp.Information;

/**
 * Created by tayo on 11/16/2015.
 */
public class FullMessageContent {

    public int threadId;
    public String body,date,from,to,sent,received,type;

    public FullMessageContent(int threadId, String body, String date, String from, String to, String sent, String received, String type){
        this.threadId = threadId;
        this.body = body;
        this.date = date;
        this.from = from;
        this.to = to;
        this.sent = sent;
        this.received = received;
        this.type = type;
    }

    public FullMessageContent(int threadId, String body, String date){
        this.threadId = threadId;
        this.body = body;
        this.date = date;
    }
}
