package edu.poly.service_buoi1.demo1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService1 extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("tag", "Hàm oncreate được gọi");
        Toast.makeText(this, "Hàm oncreate được gọi", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("tag", "Hàm onStartCommand được gọi");
        Toast.makeText(this, "Hàm onStartCommand được gọi", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("tag", "Hàm onDestroy được gọi");
        Toast.makeText(this, "Hàm onDestroy được gọi", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
