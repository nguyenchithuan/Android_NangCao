package edu.poly.labs4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private TextView tvLongtitute, tvLatitute, tvTrangThi;
    private LocationManager locationManager;
    private ConnectivityManager connectivityManager;
    private NetworkInfo wifi, mang4g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLongtitute = findViewById(R.id.tvLongitute);
        tvLatitute = findViewById(R.id.tvLatitute);
        tvTrangThi = findViewById(R.id.tvTrangThi);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    999);
            return;
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        mang4g = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isConnected()) {
            tvTrangThi.setText("wifi");
        }

        if(mang4g.isConnected()) {
            tvTrangThi.setText("mang 4g");
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double longi = location.getLongitude();
        double lat = location.getLongitude();
        tvLongtitute.setText("Longitude: " + longi);
        tvLatitute.setText("Longitude: " + lat);
    }

}