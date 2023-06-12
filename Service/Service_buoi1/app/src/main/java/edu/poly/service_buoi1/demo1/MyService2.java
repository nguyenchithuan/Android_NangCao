package edu.poly.service_buoi1.demo1;

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
    public void onCreate() {
        Toast.makeText(this, "Tạo service", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String ma = intent.getStringExtra("masv");
        String ten = intent.getStringExtra("tensv");
        Toast.makeText(this, "MaSV: " + ma + ", TenSV:" + ten , Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Hủy service", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
