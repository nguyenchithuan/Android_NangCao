package com.example.myapplication.demo4;

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

public class Demo41Main2Activity extends AppCompatActivity
implements LocationListener {
    TextView tvLong, tvLat;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo41_main2);
        tvLong = findViewById(R.id.demo41TvLong);
        tvLat = findViewById(R.id.demo41TvLat);
        //1. goi service
        locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);
        //2. phan quyen
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)!=
        PackageManager.PERMISSION_GRANTED)
        {
            return;//neu khong co quyen thi khong thuc hien
        }
        //3. Cap nhat vi tri neu co quyen
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000,1,
                (LocationListener)this);
    }
    //ham lang nghe su thay doi vi tri
    @Override
    public void onLocationChanged(@NonNull Location location) {
        double longt = location.getLongitude();
        double lat = location.getLatitude();
        tvLong.setText("Toa do long: "+String.valueOf(longt));
        tvLat.setText("Toa do lat: "+String.valueOf(lat));
    }
}
