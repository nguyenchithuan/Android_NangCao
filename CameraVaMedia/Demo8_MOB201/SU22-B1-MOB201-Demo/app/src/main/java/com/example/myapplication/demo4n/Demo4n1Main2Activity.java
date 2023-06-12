package com.example.myapplication.demo4n;

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

public class Demo4n1Main2Activity extends AppCompatActivity
implements LocationListener
{
    LocationManager locationManager;
    TextView tvLong,tvLat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4n1_main2);
        tvLong = findViewById(R.id.demo4n1Tv1);
        tvLat = findViewById(R.id.demo4n1Tv2);
        //Phan quyen
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        //goi service
        locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);
        //goi ham update
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000,1,(LocationListener)this);
    }

    //lang nghe su thay doi vi tri
    @Override
    public void onLocationChanged(@NonNull Location location) {
        double longt = location.getLongitude();
        double lat = location.getLatitude();
        tvLong.setText("Toa do Long: "+String.valueOf(longt));
        tvLat.setText("Toa do Lat: "+String.valueOf(lat));
    }
}
