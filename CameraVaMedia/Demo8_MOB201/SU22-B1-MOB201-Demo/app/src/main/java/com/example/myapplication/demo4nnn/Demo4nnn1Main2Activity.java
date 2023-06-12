package com.example.myapplication.demo4nnn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class Demo4nnn1Main2Activity extends AppCompatActivity
        implements LocationListener {
    TextView tvLong, tvLat;
    LocationManager locationManager;//lang nghe

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4nnn1_main2);
        tvLong = findViewById(R.id.demo4nnn2Tv1);
        tvLat = findViewById(R.id.demo4nnn1Tv2);
        phanQuyen();
        //goi service lang nghe
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        //goi ham cap nhat vi tri
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000, 1, (LocationListener) this);
    }
    //lay vi tri
    @Override
    public void onLocationChanged(@NonNull Location location) {
        double longt = location.getLongitude();
        double lat = location.getLatitude();
        tvLong.setText("Toa do Long: "+String.valueOf(longt));
        tvLat.setText("Toa do Lat: "+String.valueOf(lat));
    }
    public boolean phanQuyen()
    {
        if(Build.VERSION.SDK_INT>=23) {
            //1. Nếu các quyền đã được gán thì return true
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
            //2. nếu các quyền chưa được gán thì cần xin người dùng cấp quyền, return false
            else
            {
                ActivityCompat.requestPermissions(Demo4nnn1Main2Activity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},1);
                return false;
            }
        }
        else
        {
            return true;
        }
    }

}
