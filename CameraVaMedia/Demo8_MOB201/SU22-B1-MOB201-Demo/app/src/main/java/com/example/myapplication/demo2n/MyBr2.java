package com.example.myapplication.demo2n;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBr2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Nhan du lieu tu Activity truyen qua intent
        String sms = intent.getExtras().getString("br");
        //Broadcast
        Toast.makeText(context,sms,Toast.LENGTH_LONG).show();

    }
}
