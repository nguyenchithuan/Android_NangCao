package com.example.myapplication.demo4nn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.demo2n.Demo2n1Main2Activity;

public class Demo4nn2Main2Activity extends AppCompatActivity {
    TextView textView;
    ConnectivityManager connectivityManager;//service
    NetworkInfo myWifi, my4G;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4nn2_main2);
        textView = findViewById(R.id.demo4nn2Tv1);
        phanQuyen();
        //goi service
        connectivityManager =
                (ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);
        //xac dinh thong tin wifi, 4G
        myWifi = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        my4G = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        //kiem tra
        if(myWifi.isConnected())
        {
            textView.setText(myWifi.getTypeName()+" "+myWifi.getState());
        }
        else if(my4G.isConnected())
        {
            textView.setText(my4G.getTypeName()+" "+my4G.getState());
        }
    }
    public boolean phanQuyen()
    {
        if(Build.VERSION.SDK_INT>=23)
        {
            if(checkSelfPermission(Manifest.permission.INTERNET)==
                    PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)==
                    PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE)==
                    PackageManager.PERMISSION_GRANTED)
            {
                return true;//nếu quyền đã khai báo trong android manifest -> return true
            }
            else
            {
                //nếu quyền chưa chưa khai báo trong android manifest -> xin quyền
                ActivityCompat.requestPermissions(Demo4nn2Main2Activity.this,
                        new String[]{Manifest.permission.INTERNET,
                                Manifest.permission.ACCESS_NETWORK_STATE,
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
