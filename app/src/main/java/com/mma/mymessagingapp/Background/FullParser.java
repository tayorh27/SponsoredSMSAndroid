package com.mma.mymessagingapp.Background;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;

import com.mma.mymessagingapp.Information.FullMessageContent;

import java.util.ArrayList;

/**
 * Created by tayo on 11/16/2015.
 */
public class FullParser {

    private Context context;
    private Long thread;

    public FullParser(Context context, Long thread) {
        this.context = context;
        this.thread = thread;
    }


    public ArrayList<FullMessageContent> returnFullList() {

        ArrayList<FullMessageContent> customData = new ArrayList<>();
        Uri uri = Uri.parse("content://sms/conversations/" + thread);//"thread_id="+thread
        String[] projection = new String[]{Telephony.Sms.DATE, Telephony.Sms.BODY, Telephony.Sms.TYPE, Telephony.Sms.SEEN};

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, Telephony.Sms.DATE + " DESC ");
        if (cursor.moveToFirst() && cursor != null) {
            while (cursor.moveToNext()) {
                int type = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.TYPE));
                String body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE));

                Log.d("body===============================",body);

                FullMessageContent current = new FullMessageContent(type, body, date);
                customData.add(current);

            }
            cursor.close();
        }


        return customData;
    }
}
