package com.mma.mymessagingapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by tayo on 11/15/2015.
 */
public class HeadlessSmsSendService extends Service{

    public HeadlessSmsSendService(){

    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
