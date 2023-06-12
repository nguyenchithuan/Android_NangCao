package com.example.myapplication.demo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcast2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //1.Nhan du lieu tu intent
        String ma = intent.getExtras().getString("masv");
        //2. Phat lai thong bao
        Toast.makeText(context,"MaSV: "+ma,
                Toast.LENGTH_LONG).show();
    }
}
