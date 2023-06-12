package com.example.myapplication.demo4n;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class Demo4n2Main2Activity extends AppCompatActivity {
    ConnectivityManager connectivityManager;//doi tuong quan ly ket noi
    NetworkInfo myWifi, my4G;
    TextView textView;
    WifiManager wifiManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4n2_main2);
        textView = findViewById(R.id.demo4n2Tv1);
        //1.Phan quyen
        //2. goi service
        connectivityManager = (ConnectivityManager)
                getSystemService(CONNECTIVITY_SERVICE);
        //3. lay thong tin ket noi
        myWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        my4G = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        wifiManager = (WifiManager)getApplicationContext()
                .getSystemService(WIFI_SERVICE);
        String ip = android.text.format.Formatter.formatIpAddress(
                wifiManager.getConnectionInfo().getIpAddress());

        //4. Kiem tra
        if(myWifi.isConnected())
        {
            textView.setText("Ban dung wifi: "+ip);
        }
        else if(my4G.isConnected())
        {
            textView.setText("Ban dung 4G: "+ip);
        }
    }
}
