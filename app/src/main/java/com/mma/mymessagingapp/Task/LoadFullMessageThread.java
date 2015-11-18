package com.mma.mymessagingapp.Task;

import android.content.Context;
import android.os.AsyncTask;

import com.mma.mymessagingapp.Background.FullParser;
import com.mma.mymessagingapp.Callbacks.FullMessagesCallback;
import com.mma.mymessagingapp.Information.FullMessageContent;

import java.util.ArrayList;

/**
 * Created by tayo on 11/16/2015.
 */
public class LoadFullMessageThread extends AsyncTask<Void,Void,ArrayList<FullMessageContent>>{

    private Context context;
    private Long t_id;
    private FullMessagesCallback component;

    public LoadFullMessageThread(Context context,FullMessagesCallback component, Long threadId){
        this.context = context;
        this.component = component;
        this.t_id = threadId;
    }

    @Override
    protected ArrayList<FullMessageContent> doInBackground(Void... params) {
        FullParser parser = new FullParser(context,t_id);
        ArrayList<FullMessageContent> customList = parser.returnFullList();
        return customList;
    }

    @Override
    protected void onPostExecute(ArrayList<FullMessageContent> fullMessageContents) {
        if(component != null){
            component.onFullMessagesLoaded(fullMessageContents);
        }
    }
}
