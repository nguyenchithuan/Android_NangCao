package com.example.myapplication.demo1n;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyServices1 extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        Toast.makeText(this,"Ham OnDestroy duoc goi",
                Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Ham onStartCommand duoc goi",
                Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onCreate() {
        Toast.makeText(this,"Ham onCreate duoc goi",
                Toast.LENGTH_SHORT).show();
        super.onCreate();
    }
}
