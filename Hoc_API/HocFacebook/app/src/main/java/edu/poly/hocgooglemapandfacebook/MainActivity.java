package edu.poly.hocgooglemapandfacebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    // Bài 6: API
    // 1. API - ()
    // Giao diện lập trình ứng dụng: 1 chức năng được giao tiếp với client qua server

    // 2. Qui trình làm việc với API
    // - Không có qui trình chung
    // - Phụ thuộc vào bên cung cấp API
    // Vidu: Tích hợp google map với App, tích hợp đăng nhập fackbook và app

    // 3. Làm việc với API facebook
    // B1: Đăng ký acc facebook developer
    // B2: Tạo app trên trang facebook developer
    // B3: Đưa ID app vào meta-data trong android manifest
    // B4: Link version Facebook SDK
    // B5: Cấu hình java
    // B6: Cấu hình Activity
    // B7: Thiết kết giao diện
    // B8: Chức năng

    private LoginButton loginButton;
    private CallbackManager callbackManager; // Lắng nghe xem có chương trình nào gọi không


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Nếu chưa khởi tạo trong android manifest thì ta phải khở tạo trước sdk
        FacebookSdk.setApplicationId("1065047890875482");
        FacebookSdk.setClientToken("f7c51c13f825b91762bc34e17af4fb48");

        FacebookSdk.sdkInitialize(getApplicationContext()); // khởi tạo sdk

        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.demo6BtnLoginFacebook);


        callbackManager = CallbackManager.Factory.create(); // service lắng nghe, đối tượng lắng nghe

        // xử lý xự kiện nếu click vào button login
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this, "Login thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(@NonNull FacebookException e) {
                Toast.makeText(MainActivity.this, "Login lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    // Lấy về kết quả sau khi chuyển qua activity khác
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // trả về người dùng
        callbackManager.onActivityResult(resultCode, requestCode, data); // trả về kết quả cho người dùng
    }
}