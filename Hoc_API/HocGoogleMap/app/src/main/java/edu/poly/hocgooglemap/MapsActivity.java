package edu.poly.hocgooglemap;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.poly.hocgooglemap.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    // Bài 6: Làm việc với google map
    // B1: Tạo 1 dự án  mới(Không dùng dự án cũ)
    // B2: Vào link: https://console.cloud.google.com/apis/enableflow?apiid=maps_android_backend&keyType=CLIENT_SIDE_ANDROID
    // B3: Tạo trong string.xml : <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">AIzaSyCTiL7X7Ktmnd1bv2nk4X7Jh-UmPMg2mVI</string>
    // B4: vào adroid manifest:
    //       <meta-data
    //            android:name="com.google.android.geo.API_KEY"
    //            android:value="@string/google_maps_key" />
    // B5: Lập trình
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(21.0290163, 105.7461294); // Đánh dấu vị trí
        mMap.addMarker(new MarkerOptions().position(sydney).title("Poly").snippet("Tên gửi ý giới thiệu")); // Thêm mark vào vị trí vừa thêm
        mMap.setMinZoomPreference(15.0f); // room nhỏ nhất 15 lần
        mMap.setMaxZoomPreference(30.0f); // room lớn nhất 30 lần
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney)); // góc độ màn hình có thể xem
    }
}