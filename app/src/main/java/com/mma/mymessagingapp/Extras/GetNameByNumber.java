package com.mma.mymessagingapp.Extras;

import android.content.Context;
import android.telephony.PhoneNumberUtils;

import com.mma.mymessagingapp.Information.PhoneContacts;

import java.util.ArrayList;

/**
 * Created by tayo on 11/14/2015.
 */
public class GetNameByNumber {

    Context context;
    ArrayList<PhoneContacts> customList;
    public GetNameByNumber(Context context){
        this.context = context;
        customList = new LoadAddressBook(context).book();
    }

    public String returnName(String userNumber){
        String userName = "";
        for(int i = 0; i < customList.size(); i++){
            PhoneContacts current = customList.get(i);
            if(PhoneNumberUtils.compare(context,current.number,userNumber)){
                userName = current.name;
            }else {
                //userName = userNumber;
            }
        }

        return userName;
    }

    public String returnID(String userNumber){
        String userName = "";
        for(int i = 0; i < customList.size(); i++){
            PhoneContacts current = customList.get(i);
            if(PhoneNumberUtils.compare(context,current.number,userNumber)){
                userName = current.id;
            }else {
                //userName = userNumber;
            }
        }

        return userName;
    }
}
