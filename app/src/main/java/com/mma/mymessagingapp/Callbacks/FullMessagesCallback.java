package com.mma.mymessagingapp.Callbacks;

import com.mma.mymessagingapp.Information.FullMessageContent;

import java.util.ArrayList;

/**
 * Created by tayo on 11/16/2015.
 */
public interface FullMessagesCallback {

    public void onFullMessagesLoaded(ArrayList<FullMessageContent> list);
}
