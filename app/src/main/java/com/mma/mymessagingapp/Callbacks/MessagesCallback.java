package com.mma.mymessagingapp.Callbacks;

import com.mma.mymessagingapp.Information.MessageContents;

import java.util.ArrayList;

/**
 * Created by tayo on 11/14/2015.
 */
public interface MessagesCallback {

    public void onMessagesLoaded(ArrayList<MessageContents> messageContents);
}
