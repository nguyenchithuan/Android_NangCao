package com.example.myapplication.demo2n;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyBr1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //1. goi service xy ly cuoc goi
        TelephonyManager telephonyManager=
                (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        //2. Tạo object
        MyPhoneState1 myPhoneState1 = new MyPhoneState1(context);
        //3. Gọi phương thức lắng nghe đã thiết kế
        telephonyManager.listen(myPhoneState1,PhoneStateListener.LISTEN_CALL_STATE);
    }
    //Xây dựng lớp lắng nghe cuộc gọi đến
    public class MyPhoneState1 extends PhoneStateListener{
        Context context;
        public MyPhoneState1(Context context)
        {
            this.context = context;
        }
        //hàm lắng nghe cuộc gọi đến
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            if(state==1)//co cuoc goi den
            {
                Toast.makeText(context,"Co cuoc goi den",
                        Toast.LENGTH_LONG).show();
            }
            super.onCallStateChanged(state, phoneNumber);
        }
    }
}
