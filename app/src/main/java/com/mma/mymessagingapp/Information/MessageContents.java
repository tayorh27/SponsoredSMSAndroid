package com.mma.mymessagingapp.Information;

/**
 * Created by tayo on 11/14/2015.
 */

public class MessageContents {

    public String name,message,date,name_from_number,uri;
    public int color;
    public Long id;

    public String snip,thread,messageC;


    public  MessageContents(Long id, String nm, String msg, String dt,int color, String name_from_number, String uri){
        this.id = id;
        this.name = nm;
        this.message = msg;
        this.date = dt;
        this.color = color;
        this.name_from_number = name_from_number;
        this.uri = uri;
    }
    public MessageContents(){

    }

    public MessageContents(String snippet, String messageCount, String threadId){
this.snip = snippet;
        this.messageC = messageCount;
        this.thread = threadId;
    }

}
