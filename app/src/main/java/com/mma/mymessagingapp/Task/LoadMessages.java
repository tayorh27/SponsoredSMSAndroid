package com.mma.mymessagingapp.Task;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.mma.mymessagingapp.Background.Parser;
import com.mma.mymessagingapp.Callbacks.MessagesCallback;
import com.mma.mymessagingapp.Extras.GetNameByNumber;
import com.mma.mymessagingapp.Information.MessageContents;
import com.mma.mymessagingapp.design.SetBackgroundColor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by tayo on 11/14/2015.
 */
public class LoadMessages extends AsyncTask<Void,Void,ArrayList<MessageContents>>{

    private MessagesCallback component;
    private Context context;


    public LoadMessages(MessagesCallback component,Context context){
        this.component = component;
        this.context = context;
    }

    private ArrayList<MessageContents> removeDuplicate(ArrayList<MessageContents> list){
        ArrayList<MessageContents> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>();

        for(MessageContents item : list){
            if(set.equals(item.name)){
                result.remove(item);
                set.add(item.name);
            }
        }
        return result;
    }




    @Override
    protected ArrayList<MessageContents> doInBackground(Void... params) {

        Parser parser = new Parser(context);
        ArrayList<MessageContents> data = parser.returnList();

        return data;
    }

    @Override
    protected void onPostExecute(ArrayList<MessageContents> messageContentses) {
        super.onPostExecute(messageContentses);
        if(component != null){
            component.onMessagesLoaded(messageContentses);
        }
    }
}
