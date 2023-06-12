package edu.poly.hocbroadcastreceiver.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyBroadcast1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Hàm nhập thông điệp
        // 1. gọi service để xử lý cuộc gọi
        TelephonyManager telephonyManager  = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        // 2. Tạo object
        MyPhoneState myPhoneState = new MyPhoneState(context);

        // 3. gọi phương thức lắng nghe
        telephonyManager.listen(myPhoneState, PhoneStateListener.LISTEN_CALL_STATE);
    }

    // tạo lớp lắng nghe cuộc gọi đến
    public class MyPhoneState extends PhoneStateListener {
        private Context context;
        public MyPhoneState(Context context) {
            this.context = context;
        }

        // Hàm lắng nghe
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            super.onCallStateChanged(state, phoneNumber);

            if(state == 1) {
                // Có cuộc gọi đến
                Toast.makeText(context, "Có cuộc gọi đến", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
