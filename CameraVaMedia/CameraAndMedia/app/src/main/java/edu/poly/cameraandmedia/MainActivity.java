package edu.poly.cameraandmedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Bài 8: Camera và Media

    // 1. Access camera
    // - Action_mage_capture


    private Button btnCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = findViewById(R.id.btn_camerar);

        phanQuyen();

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cams = Camera.getNumberOfCameras();// lấy về số lượng camera
                if(cams > 0) { // nếu có camera
                    Toast.makeText(MainActivity.this, "Thiết bị có: " + cams + " camera", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Thiết bị không có camera", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void phanQuyen() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
    }


}