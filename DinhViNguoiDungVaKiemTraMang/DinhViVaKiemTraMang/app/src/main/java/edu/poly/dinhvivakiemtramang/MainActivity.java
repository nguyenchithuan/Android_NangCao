package edu.poly.dinhvivakiemtramang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    // Bài 4: Định vị người dùng, quản lý kết nối mạng


    // I. Định vị người dùng

    // 1. Nguyên lý làm việc
    // - bắn theo đường dây chuyền


    // 2. Định ví người dùng: (dùng máy thật)
    // Để xác định vị trí, người ta dùng Kinh Độ và Vĩ Độ(Longtitude, Latitude)(Long, Lat)


    // 3. Phương pháp xác định:
    // - Dựa vào chức năng GPS để định vị người dùng
    // - Dựa vào kết nối internet(xác định qua vị trí của người dung IP) -> hay dùng trong smartphone
    // - GPS(Hệ thống tin địa lý): Không cần có internet (Phương pháp của ngày xưa lúc ý internet chưa phổ biến)


    // 4. Cấu trúc lập trình
    // - Nó là 1 service: Location Service
    // - Để thực hiện, ta cần có 2 quyền:
    // - access_fine_lacation : Cho phép tìm vị trí
    // - access_coarse_location: Cho phép truy cập
    // - Hàm thực hiện: requestLocationUpdates(GPS provider)
    // - Lấy vị trí bằng: getLongtitude, getLatitude


    // II. Quản lý kết nối mạng:
    // - Xác định kết nối internet là wifi hay 4G
    // - Bản chất: là 1 service:connectivity_service
    // - Cần xác định: wifi hay 4G dựa vào Type_wifi hoặc Type_mobile
    // - Các quyền cần sử dụng
    // + internet : truy cập internet
    // + access_network_state : trạng thái truy cập internet


    // - Các bước định vị người dung:
    // B1: Khai báo quyền
    // B2: Cài đặt giao diện và gọi service
    // B3: Cài đặt hàm lắng nghe sự thay đổi vị trí


    // - Các bước quản lý kết nối:
    // B1: Khai báo quyền
    // B2: detect connection


    private TextView tvLong, tvLat;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLong = findViewById(R.id.tvLong);
        tvLat = findViewById(R.id.tvLat);

        // 1. gọi service
        // lấy ra service của hệ thống
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        // 2. Phần quyền


        // 3. Cập nhật vị trí nếu có quyền
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        // Hàm thực hiện
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);

        // tham số thứ 1: dữ liệu dùng cái hệ thống định vị GPS_PROVIDER (nếu không dùng mạng)
        //              : nếu dùng mạng thì dùng NETWORK_PROVIDER
        // tham số thứ 2: thời gian
        // tham số thứ 3: khoảng cách 1 m
        // tham số thứ 4: lắng nghe sự thay đổi
        }


    // lắng nghe sự thay đổi vị trí
    @Override
    public void onLocationChanged(@NonNull Location location) {
        double longt = location.getLongitude();
        double lat = location.getLatitude();

        tvLong.setText("Tọa độ long: " + String.valueOf(longt));
        tvLat.setText("Tọa độ long: " + String.valueOf(lat));
    }
}