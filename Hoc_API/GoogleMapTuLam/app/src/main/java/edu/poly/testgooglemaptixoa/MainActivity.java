package edu.poly.testgooglemaptixoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    // Bài 6: Làm việc với google map
    // B1: Tạo 1 dự án  mới(Không dùng dự án cũ)
    // B2: Vào link: https://console.cloud.google.com/apis/enableflow?apiid=maps_android_backend&keyType=CLIENT_SIDE_ANDROID
    // B3: Tạo trong string.xml : <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">AIzaSyCTiL7X7Ktmnd1bv2nk4X7Jh-UmPMg2mVI</string>
    // B4: vào adroid manifest:
    //       <meta-data
    //            android:name="com.google.android.geo.API_KEY"
    //            android:value="@string/google_maps_key" />
    // B5: Lập trình

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.myMap);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        LatLng sydney = new LatLng(21.0290163, 105.7461294);
        map.addMarker(new MarkerOptions().title("Cao đẳng FPT Poly").position(sydney).snippet("Giới thiệu"));// Thêm mark vào vị trí vừa thêm
//        map.setMinZoomPreference(15.0f); // room nhỏ nhất 15 lần
//        map.setMaxZoomPreference(30.0f); // room lớn nhất 30 lần
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney)); // góc độ màn hình có thể xem
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15)); // camera mà hình
    }
}