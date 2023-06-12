package com.example.myapplication.demo2n;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBr3 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String str = intent.getExtras().getString("br");
        String kq = kiemTraKhuyenMai(str);
        Toast.makeText(context,kq,Toast.LENGTH_LONG).show();
    }
    private String kiemTraKhuyenMai(String str)
    {
        if(str.substring(0,3).equals("MEM"))
        {
            if(str.equals("MEM537128"))
            {
                return "Khuyen mai 10%";
            }
            else  if(str.equals("MEM537129"))
            {
                return "khuyen mai 20%";
            }
            else
            {
                return "Khuyen mai 10%->20%";
            }
        }
        else if(str.substring(0,3).equals("VIP"))
        {
            if(str.equals("VIP537128"))
            {
                return "Khuyen mai 30%";
            }
            else  if(str.equals("VIP537129"))
            {
                return "khuyen mai 50%";
            }
            else
            {
                return "Khuyen mai 30%->50%";
            }
        }
        else
        {
            return "Khong khuyen mai";
        }
    }
}
