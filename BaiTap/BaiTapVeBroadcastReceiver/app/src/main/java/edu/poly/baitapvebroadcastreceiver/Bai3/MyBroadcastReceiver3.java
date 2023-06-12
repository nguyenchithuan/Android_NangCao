package edu.poly.baitapvebroadcastreceiver.Bai3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver3 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra(Bai3Activity.BAI3_DATA);


        String kq = suLyChuoi(data, context);
        Toast.makeText(context, kq, Toast.LENGTH_SHORT).show();

    }

    private String suLyChuoi(String data, Context context) {
        if(data.length() != 9) {
            return "Nhập số ký tự là 9";
        }

        if(data.substring(0, 3).equals("MEM") || data.substring(0,3).equals("VIP")) {
            switch (data) {
                case "MEM537128":
                    return "Khuyến mãi 10%";

                case "MEM537129":
                    return "Khuyến mãi 20%";

                case "VIP537128":
                    return "Khuyến mãi 30%";

                case "VIP537129":
                    return "Khuyến mãi 50%";

                default:
                    return "Không khuyến mãi!";
            }
        } else {
            return "Chuỗi bắt đầu bằng MEM hoặc VIP";
        }
    }

}
