package edu.poly.hocbroadcastreceiver.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcast3 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getExtras().getString("br");
        String kq = xuLyChuoi(msg);
        Toast.makeText(context, kq, Toast.LENGTH_SHORT).show();
    }

    private String xuLyChuoi(String str) {
        if(str.substring(0, 3).equals("MEM")) {
            if(str.equals("MEM537128")) {
                return "Khuyến mại 10%";
            } else if(str.equals("MEM537129")) {
                return "Khuyến mại 20%";
            } else {
                return "Khuyến mại từ 10% đến 30%";
            }
        } else if(str.substring(0, 3).equals("VIP")) {
            if(str.equals("VIP537128")) {
                return "Khuyến mại 30%";
            } else if(str.equals("VIP537129")) {
                return "Khuyến mại 50%";
            } else {
                return "Khuyến mại từ 30% đến 50%";
            }
        } else {
            return "Không khuyến mại";
        }
    }

}
