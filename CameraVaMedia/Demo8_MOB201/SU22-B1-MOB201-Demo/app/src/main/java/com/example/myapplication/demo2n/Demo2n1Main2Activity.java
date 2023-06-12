package com.example.myapplication.demo2n;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.myapplication.R;

public class Demo2n1Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2n1_main2);
        phanQuyen();
        Intent intent = new Intent(this,MyBr1.class);
        sendBroadcast(intent);
    }
    public boolean phanQuyen()
    {
        if(Build.VERSION.SDK_INT>=23)
        {
            if(checkSelfPermission(Manifest.permission.READ_PHONE_STATE)==
                    PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.READ_PHONE_NUMBERS)==
                    PackageManager.PERMISSION_GRANTED)
            {
                return true;//nếu quyền đã khai báo trong android manifest -> return true
            }
            else
            {
                //nếu quyền chưa chưa khai báo trong android manifest -> xin quyền
                ActivityCompat.requestPermissions(Demo2n1Main2Activity.this,
                        new String[]{Manifest.permission.READ_PHONE_NUMBERS,
                        Manifest.permission.READ_PHONE_STATE},1);
                return false;
            }
        }
        else
        {
            return true;
        }
    }
}
