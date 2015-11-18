package com.mma.mymessagingapp.Extras;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.mma.mymessagingapp.Information.PhoneContacts;

import java.util.ArrayList;

/**
 * Created by tayo on 11/14/2015.
 */
public class LoadAddressBook {

    Context context;

    public LoadAddressBook(Context context){
        this.context = context;
    }

    public ArrayList<PhoneContacts> book(){

        ArrayList<PhoneContacts> customData = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
            String name =cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number =cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            PhoneContacts contacts = new PhoneContacts(id,name,number);
            customData.add(contacts);
        }
        cursor.close();
        return customData;
    }
}
