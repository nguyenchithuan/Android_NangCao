package edu.poly.labs1.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService2 extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String data = intent.getStringExtra("data");
        if(data.length() == 0) {
            Toast.makeText(this, "Mời nhập dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            timKiemKiTu(data);
        }

        return START_NOT_STICKY;
    }

    private void timKiemKiTu(String data) {
        int count = 0;
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == 'A') {
                count++;
            }
        }
        Toast.makeText(this, "Số ký tự A là: " + count, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
