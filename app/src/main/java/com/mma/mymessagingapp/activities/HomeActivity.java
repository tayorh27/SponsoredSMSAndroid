package com.mma.mymessagingapp.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mma.mymessagingapp.Adapters.MessagesAdapter;
import com.mma.mymessagingapp.Callbacks.MessagesCallback;
import com.mma.mymessagingapp.Information.MessageContents;
import com.mma.mymessagingapp.R;
import com.mma.mymessagingapp.Task.LoadMessages;
import com.mma.mymessagingapp.model.DataProvider;

import java.util.ArrayList;

public class HomeActivity extends ActionBarActivity implements MessagesCallback {

    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton fab;

    MessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        toolbar = (Toolbar)findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        adapter = new MessagesAdapter(HomeActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        recyclerView.setAdapter(adapter);

        //adapter.setAdapter(DataProvider.getInstance().getConversations());

        new LoadMessages(this, HomeActivity.this).execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMessagesLoaded(ArrayList<MessageContents> messageContents) {
        adapter.setAdapter(messageContents);
    }
}
