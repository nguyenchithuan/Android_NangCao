package com.example.myapplication.demo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyBroadcast1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //1. Goi service xu ly cuoc goi
        TelephonyManager telephonyManager
                =(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        //2. Tao object lang nghe
        MyPhoneState2 myPhoneState2 = new MyPhoneState2(context);
        //3.Bắt đầu lắng nghe
        telephonyManager.listen(myPhoneState2,
                PhoneStateListener.LISTEN_CALL_STATE);
    }
    //Tạo lớp lắng nghe cuộc gọi
    public class MyPhoneState2 extends PhoneStateListener{
        Context context;
        //ham khoi tao
        public MyPhoneState2(Context context)
        {
            this.context = context;
        }
        //ham lang nghe
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            if(state==1)//có cuộc gọi đến
            {
                Toast.makeText(context,"Co cuoc goi den",
                        Toast.LENGTH_LONG).show();
            }
            super.onCallStateChanged(state, phoneNumber);
        }
    }
}
