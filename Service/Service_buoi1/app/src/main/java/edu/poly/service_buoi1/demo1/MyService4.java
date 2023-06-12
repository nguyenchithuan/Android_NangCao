package edu.poly.service_buoi1.demo1;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService4 extends Service {
    private int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();

        if(bundle != null) {
            String strData = bundle.getString("chuoiKiTu");
            demKyTuChuoi(strData);
        }

        return START_NOT_STICKY;
    }

    private void demKyTuChuoi(String strData) {
        count = 0;
        char[] chars = strData.toCharArray();
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
