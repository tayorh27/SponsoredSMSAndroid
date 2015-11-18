package com.mma.mymessagingapp.Background;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.provider.Telephony.Threads;
import android.provider.ContactsContract;

import com.mma.mymessagingapp.Extras.GetNameByNumber;
import com.mma.mymessagingapp.Information.MessageContents;
import com.mma.mymessagingapp.design.SetBackgroundColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import me.everything.providers.android.telephony.Conversation;
import me.everything.providers.android.telephony.TelephonyProvider;

/**
 * Created by tayo on 11/15/2015.
 */
public class Parser {

    private Context context;
    private GetNameByNumber getNameByNumber;

    private ArrayList<MessageContents> customData = new ArrayList<>();


    public Parser(Context context) {
        this.context = context;
        getNameByNumber = new GetNameByNumber(context);
    }

    public ArrayList<MessageContents> returnList() {
        customData.clear();
        Uri uri = Uri.parse("content://mms-sms/conversations?simple=true");
        final String[] allThreadProjections = {
                Threads._ID, Threads.MESSAGE_COUNT, Threads.RECIPIENT_IDS, Threads.SNIPPET
        };
        Cursor cursor = context.getContentResolver().query(uri,allThreadProjections , null, null, "date desc");
        if(cursor.moveToFirst() && cursor != null) {
            while (cursor.moveToNext()) {

                Long id =  cursor.getLong(cursor.getColumnIndexOrThrow(Threads._ID));
                Long rec = cursor.getLong(cursor.getColumnIndexOrThrow(Threads.RECIPIENT_IDS));
                String message = cursor.getString(cursor.getColumnIndexOrThrow(Threads.SNIPPET));
                String name = getNumberForId(rec);
                String date = returnDate(message);
                String name_from_number = getNameByNumber.returnName(name);
                if (name_from_number.contentEquals("")) {
                    name_from_number = name;
                }
                int color = SetBackgroundColor.Color(name_from_number);
                String contactId = getNameByNumber.returnID(name);
                String image = getContactImageUri(contactId);

                MessageContents current = new MessageContents(id,name,message,date,color,name_from_number,image);

                customData.add(current);
            }
            cursor.close();
        }


        return customData;
    }

    public String returnDate(String snippet){
        String date = null;
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms"),null,null,null,null);
        if(cursor.moveToFirst() && cursor != null){
            while (cursor.moveToNext()){
                String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                if(body.contentEquals(snippet)){
                    date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                }else {
                    date = "1345244678987";
                }
            }
            cursor.close();
        }
        return date;
    }

    public String getNumberForId(Long rec_id){

        Uri uri = Uri.parse("content://mms-sms/canonical-address/"+rec_id);
        Cursor cursor = context.getContentResolver().query(uri, null,null,null,null);
        String number = null;
        if(cursor.moveToNext()){
            number = cursor.getString(0);
        }
        cursor.close();
        return number;
    }

    public String getContactImageUri(String contactId){
        Uri image = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, 123484);//Long.parseLong(contactId));
        String imagePath = null;
        if(image != null){
            imagePath = image.toString();
        }
        return imagePath;
    }
}
