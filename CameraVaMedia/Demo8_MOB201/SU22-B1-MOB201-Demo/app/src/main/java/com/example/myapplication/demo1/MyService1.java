package com.example.myapplication.demo1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService1 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("onCreate","Ham onCreate duoc goi");
        Toast.makeText(this,"Ham onCreate duoc goi",
                Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d("onDestroy","Ham onDestroy duoc goi");
        Toast.makeText(this,"Ham onDestroy duoc goi",
                Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("onStartCommand","Ham onStartCommand duoc goi");
        Toast.makeText(this,"Ham onStartCommand duoc goi",
                Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }
}
