package edu.poly.baitapvebroadcastreceiver.Bai2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(MainActivity.MY_ACTION.equals(intent.getAction())) {
            String data = intent.getStringExtra(MainActivity.MY_DATA);
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
        }
    }

}
