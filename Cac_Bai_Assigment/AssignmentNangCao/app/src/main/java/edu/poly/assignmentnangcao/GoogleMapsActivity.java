package edu.poly.assignmentnangcao;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import edu.poly.assignmentnangcao.databinding.ActivityGoogleMapsBinding;

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
private ActivityGoogleMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityGoogleMapsBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(20.9394567,105.6932187);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Núi trầm"));

        sydney = new LatLng(27.0381278,105.7445984);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Cao đăng fpoly"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                CameraUpdate cameraUpdate =  CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15);
                mMap.animateCamera(cameraUpdate, 2000, null);
                return true;
            }
        });
    }
}