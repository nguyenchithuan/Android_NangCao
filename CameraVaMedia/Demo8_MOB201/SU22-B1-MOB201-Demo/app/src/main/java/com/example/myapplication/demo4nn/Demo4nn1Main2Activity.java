package com.example.myapplication.demo4nn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class Demo4nn1Main2Activity extends AppCompatActivity
implements LocationListener
{
    TextView tvLong,tvLat;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4nn1_main2);
        tvLong = findViewById(R.id.demo4nn1Tv1);
        tvLat = findViewById(R.id.demo4nn1Tv2);
        //1. Phan quyen
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        //2. goi service
        locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);
        //3. goi ham update vi tri
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000,1,(LocationListener)this);
    }
    //xử lý khi thay đổi vị trí
    @Override
    public void onLocationChanged(@NonNull Location location) {
        double longt = location.getLongitude();
        double lat = location.getLatitude();
        tvLong.setText("Toa do Long: "+String.valueOf(longt));
        tvLat.setText("Toa do Lat: "+String.valueOf(lat));
    }
}
