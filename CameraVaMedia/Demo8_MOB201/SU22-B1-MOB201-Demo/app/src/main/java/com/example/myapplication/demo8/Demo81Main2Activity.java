package com.example.myapplication.demo8;

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
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.demo2.Demo21Main2Activity;

public class Demo81Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo81_main2);
        phanQuyen();
    }
    public boolean phanQuyen()
    {
        if(Build.VERSION.SDK_INT>=23) {
            //1. Nếu các quyền đã được gán thì return true
            if (checkSelfPermission(Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
            //2. nếu các quyền chưa được gán thì cần xin người dùng cấp quyền, return false
            else
            {
                ActivityCompat.requestPermissions(Demo81Main2Activity.this,
                        new String[]{Manifest.permission.CAMERA},1);
                return false;
            }
        }
        else
        {
            return true;
        }
    }

    public void access_camera1(View view) {
        int cams = Camera.getNumberOfCameras();//lay ve so luong camera
        if(cams>0)//neu co camera
        {
            Toast.makeText(getApplicationContext(),
                    "Thiet bi co: "+cams+" camera",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            this.startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),
                    "Thiet bi khong co camera",
                    Toast.LENGTH_LONG).show();
        }
    }
}
