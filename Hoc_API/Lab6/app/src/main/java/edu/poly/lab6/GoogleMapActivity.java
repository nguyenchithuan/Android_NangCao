package edu.poly.lab6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.frm_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        LatLng vitri = new LatLng(21.0290163f, 105.7461294f);
        map.addMarker(new MarkerOptions().title("Cao đăng fpt").position(vitri).snippet("Vị trí ở hà nội"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(vitri, 15));
    }
}