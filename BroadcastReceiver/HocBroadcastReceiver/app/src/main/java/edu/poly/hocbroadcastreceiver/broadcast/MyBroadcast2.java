package edu.poly.hocbroadcastreceiver.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcast2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // nhận dữ liệu
        String sms = intent.getExtras().getString("br");

        // Phát lại thông điệp
        Toast.makeText(context, sms, Toast.LENGTH_SHORT).show();
    }

}
