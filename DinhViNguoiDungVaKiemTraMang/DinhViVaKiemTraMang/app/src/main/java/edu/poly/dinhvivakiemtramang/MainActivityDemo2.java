package edu.poly.dinhvivakiemtramang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivityDemo2 extends AppCompatActivity {
    private TextView tvInternet;
    private ConnectivityManager connectivityManager; // quản lý kết  nối
    private NetworkInfo myWifi, my4G; // Chứa thông tin của mạng là wifi hay 4g

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo2);
        tvInternet = findViewById(R.id.tvInternet);

        // 1. Phần quyền
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        // 2. gọi service
        connectivityManager = (ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);
        // wifi
        myWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        // Phải có permisstion.ACCESS_NETWORK_STATE thì mới lấy ra được các type
        // 4g
        my4G = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(myWifi.isConnected()) {
            tvInternet.setText("Bạn đang dùng wifi");

        }

        if(my4G.isConnected()) {
            tvInternet.setText("Bạn đang dùng 4g");
        }

    }
}