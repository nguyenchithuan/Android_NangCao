package com.example.myapplication.demo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcast3 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Nhan du lieu tu nguoi dung nhap
        String str = intent.getExtras().getString("km");
        //Goi ham xu ly khuyen mai
        String khuyenmai = xuLyChuoi(str);
        //Phat lai thong bao khuyen mai
        Toast.makeText(context,khuyenmai,Toast.LENGTH_LONG).show();
    }
    private String xuLyChuoi(String str)
    {
        String km="";
        if(str.substring(0,3).equals("MEM"))
        {
            if(str.equals("MEM537128"))
            {
                km = "Khuyem mai 10%";
            }
            else if(str.equals("MEM537129"))
            {
                km="Khuyen mai 20%";
            }
            else
            {
                km="Khuyen mai tu 10%->20%";
            }
        }
        else if(str.substring(0,3).equals("VIP"))
        {
            if(str.equals("VIP537128"))
            {
                km = "Khuyem mai 30%";
            }
            else if(str.equals("VIP537129"))
            {
                km="Khuyen mai 50%";
            }
            else
            {
                km="Khuyen mai tu 30%->50%";
            }
        }
        else
        {
            km = "Khong khuyen mai";
        }
        return km;
    }
}
