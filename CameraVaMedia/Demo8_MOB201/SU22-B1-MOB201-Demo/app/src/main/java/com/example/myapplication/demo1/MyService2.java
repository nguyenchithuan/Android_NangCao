package com.example.myapplication.demo1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService2 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //lay du lieu tu intent
        String ma = intent.getStringExtra("masv");
        String ten = intent.getStringExtra("tensv");
        //service dua ra thong bao da nhan du lieu
        Toast.makeText(this,"MaSV: "+ma+"; TenSV: "+ten,
                Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
