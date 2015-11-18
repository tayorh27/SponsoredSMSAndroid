package com.mma.mymessagingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.mma.mymessagingapp.activities.HomeActivity;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Discover whether we're default
        final String packageName = MainActivity.this.getPackageName();
        if(!Telephony.Sms.getDefaultSmsPackage(MainActivity.this).equals(packageName)) {
            // Not default
//            View vg = getView().findViewById(R.id.composeNotDefault);
//            vg.setVisibility(View.VISIBLE);

            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setCancelable(false);
            alertDialog.setTitle("Change SMS App?");
            alertDialog.setMessage("Use MyMessagingApp instead of Messaging as your SMS app?");
            alertDialog.setButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            });
            alertDialog.setButton2("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                    intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, packageName);
                    startActivity(intent);
                }
            });
            alertDialog.show();

            // Add click listener to the set default button
//            Button button = (Button)getView().findViewById(R.id.composeButtonSetDefault);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//                    intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, packageName);
//                    startActivity(intent);
//                }
//            });
        } else {
            // App is default
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
//            View vg = getView().findViewById(R.id.composeNotDefault);
//            vg.setVisibility(View.GONE);
        }

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    //Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally{
                    //startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            }
        };
        thread.start();
    }

}
