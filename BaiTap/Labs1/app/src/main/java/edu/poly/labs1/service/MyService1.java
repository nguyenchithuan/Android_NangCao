package edu.poly.labs1.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService1 extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            thongTinBai1(bundle);
        }
        return START_NOT_STICKY;
    }

    private void thongTinBai1(Bundle bundle) {
        int id = bundle.getInt("StuId");
        String name = bundle.getString("StuName");
        String lop = bundle.getString("Class");

        String content = "Thêm sinh viên thành công\nThông tin sinh viên\nSinh viên: " + id + " - " + name + "\nLớp: " + lop;
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
