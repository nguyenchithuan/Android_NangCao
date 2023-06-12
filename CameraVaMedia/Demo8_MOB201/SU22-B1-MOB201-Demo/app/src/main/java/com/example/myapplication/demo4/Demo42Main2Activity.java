package com.example.myapplication.demo4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class Demo42Main2Activity extends AppCompatActivity {
    TextView textView;
    ConnectivityManager connectivityManager;//quan ly ket noi
    NetworkInfo myWifi, my4G;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo42_main2);
        textView = findViewById(R.id.demo42Tv1);
        //1. phan quyen
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)!=
                PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_NETWORK_STATE)!=
                PackageManager.PERMISSION_GRANTED)
        {
            return;//neu khong co quyen thi khong thuc hien
        }
        //2.goi service
        connectivityManager = (ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);
        //wifi
        myWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        //4g
        my4G = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(myWifi.isConnected()==true)
        {
            textView.setText("Ban dang dung wifi");
        }
        else if(my4G.isConnected()==true)
        {
            textView.setText("Ban dang dung 4G");
        }
    }
}
