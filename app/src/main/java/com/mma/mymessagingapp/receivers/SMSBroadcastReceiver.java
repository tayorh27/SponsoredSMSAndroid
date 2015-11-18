package com.mma.mymessagingapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.mma.mymessagingapp.model.DataProvider;
import com.mma.mymessagingapp.model.Message;

import java.util.Date;

/**
 * Created by tayo on 11/15/2015.
 */
public class SMSBroadcastReceiver extends BroadcastReceiver{

    public SMSBroadcastReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for(Object currentObj : pdusObj) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) currentObj);
                    Message message = new Message(
                            currentMessage.getDisplayMessageBody(),
                            currentMessage.getDisplayOriginatingAddress(),
                            "ME",
                            new Date()
                    );
                    DataProvider.getInstance().addMessage(message);
                }
            }
        } catch (Exception e) {
            Log.e("SMS", "Exception: " + e);
        }

    }
}
