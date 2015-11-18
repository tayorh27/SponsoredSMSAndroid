package com.mma.mymessagingapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by tayo on 11/15/2015.
 */
public class DataProvider extends Observable{

    public static DataProvider ourInstance = new DataProvider();

    public static DataProvider getInstance(){
        return ourInstance;
    }

    private Map<String, Conversation> conversationMap;
    private ArrayList<Conversation> conversationList;

    private DataProvider() {
        // Create the store
        conversationMap = new HashMap<String, Conversation>();
        conversationList = new ArrayList<Conversation>();

        // Create some sample data
//        addMessage(new Message("content1", "sender1", "recipient", new Date()));
//        addMessage(new Message("content2", "sender1", "recipient", new Date()));
//        addMessage(new Message("content3", "sender2", "recipient", new Date()));
//        addMessage(new Message("content4", "sender3", "recipient", new Date()));
//        addMessage(new Message("content5", "sender4", "recipient", new Date()));
//        addMessage(new Message("content6", "sender4", "recipient", new Date()));
//        addMessage(new Message("content7", "sender4", "recipient", new Date()));
//        addMessage(new Message("content8", "sender4", "recipient", new Date()));
//        addMessage(new Message("content9", "sender4", "recipient", new Date()));
    }

    public void addMessage(Message message) {
        if(conversationMap.containsKey(message.Sender)) {
            // Can add the message to an existing conversation
            conversationMap.get(message.Sender).addMessage(message);
        } else {
            // Need to create a new conversation
            Conversation conversation = new Conversation(message.Sender);
            conversation.addMessage(message);
            conversationMap.put(message.Sender, conversation);
            conversationList.add(conversation);
        }
        // Ensure that everything gets updated
        setChanged();
        notifyObservers();
    }

    public ArrayList<Conversation> getConversations() {
        return conversationList;
    }

    public Map<String, Conversation> getConversationMap() {
        return conversationMap;
    }

}
