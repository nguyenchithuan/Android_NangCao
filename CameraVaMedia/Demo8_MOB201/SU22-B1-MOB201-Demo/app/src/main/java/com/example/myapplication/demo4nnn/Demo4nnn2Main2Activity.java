package com.example.myapplication.demo4nnn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class Demo4nnn2Main2Activity extends AppCompatActivity {
    TextView textView;
    ConnectivityManager connectivityManager;//su dung service
    NetworkInfo myWifi, my4G;//xac dinh thong tin
    WifiManager wifiManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4nnn2_main2);
        textView = findViewById(R.id.demo4nnn2Tv1);
        phanQuyen();
        //goi service
        connectivityManager = (ConnectivityManager)
                this.getSystemService(CONNECTIVITY_SERVICE);
        wifiManager = (WifiManager)
                this.getApplicationContext().getSystemService(WIFI_SERVICE);
        //xac dinh thong tin mang
        myWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        my4G = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        //Lay thong tin dia chi ip
        String ip = android.text.format.Formatter.formatIpAddress(
                wifiManager.getConnectionInfo().getIpAddress());
        if(myWifi.isConnected())
        {
            textView.setText(myWifi.getTypeName()+" "+myWifi.getDetailedState()+": "+ip);
        }
        else if(my4G.isConnected())
        {
            textView.setText(my4G.getClass()+" "+my4G.getDetailedState());
        }
    }
    public boolean phanQuyen()
    {
        if(Build.VERSION.SDK_INT>=23) {
            //1. Nếu các quyền đã được gán thì return true
            if (checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) ==
                    PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) ==
                    PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
            //2. nếu các quyền chưa được gán thì cần xin người dùng cấp quyền, return false
            else
            {
                ActivityCompat.requestPermissions(Demo4nnn2Main2Activity.this,
                        new String[]{Manifest.permission.ACCESS_NETWORK_STATE,
                                Manifest.permission.ACCESS_WIFI_STATE},1);
                return false;
            }
        }
        else
        {
            return true;
        }
    }

}
