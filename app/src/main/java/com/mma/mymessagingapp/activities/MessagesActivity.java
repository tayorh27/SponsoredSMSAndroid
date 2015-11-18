package com.mma.mymessagingapp.activities;

import android.app.ProgressDialog;
import android.provider.Telephony;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.mma.mymessagingapp.Adapters.FullMessageAdapter;
import com.mma.mymessagingapp.Callbacks.FullMessagesCallback;
import com.mma.mymessagingapp.Information.FullMessageContent;
import com.mma.mymessagingapp.R;
import com.mma.mymessagingapp.Task.LoadFullMessageThread;

import java.util.ArrayList;
import java.util.List;

import me.everything.providers.android.telephony.*;

public class MessagesActivity extends ActionBarActivity implements FullMessagesCallback {

    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText editText;
    int set_color;
    Long thread_id;
    FullMessageAdapter adapter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        Bundle bundle = getIntent().getExtras();
        set_color = bundle.getInt("color");
        thread_id = bundle.getLong("thread");

        toolbar = (Toolbar)findViewById(R.id.myToolBar);
        toolbar.setBackgroundColor(set_color);
        setSupportActionBar(toolbar);

        adapter = new FullMessageAdapter(MessagesActivity.this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        editText = (EditText)findViewById(R.id.editText);
        recyclerView.setLayoutManager(new LinearLayoutManager(MessagesActivity.this));
        recyclerView.setAdapter(adapter);

        //Toast.makeText(MessagesActivity.this, "thread_id = "+thread_id,Toast.LENGTH_LONG).show();
        progressDialog = new ProgressDialog(MessagesActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //TelephonyProvider telephonyProvider = new TelephonyProvider(MessagesActivity.this);
        //List<Conversation> conversations = telephonyProvider.getConversations().getList();
        //List<me.everything.providers.android.telephony.Thread> threads = telephonyProvider.getThreads().getList();

        //Toast.makeText(MessagesActivity.this, "size = "+conversations.size()+"",Toast.LENGTH_LONG).show();
        //editText.setText(conversations.get(0).snippet);

        //Conver
        new LoadFullMessageThread(MessagesActivity.this,this,thread_id).execute();

        //SmsManager manager = SmsManager.getDefault();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_messages, menu);
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
    public void onFullMessagesLoaded(ArrayList<FullMessageContent> list) {
        adapter.setlist(list);
        Toast.makeText(MessagesActivity.this,"size = "+list.size(),Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
    }
}
