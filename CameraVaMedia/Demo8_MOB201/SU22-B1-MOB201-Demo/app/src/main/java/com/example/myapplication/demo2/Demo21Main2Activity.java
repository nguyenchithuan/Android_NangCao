package com.example.myapplication.demo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.myapplication.R;

public class Demo21Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo21_main2);
        phanQuyen();
        Intent intent = new Intent(this,MyBroadcast1.class);
        sendBroadcast(intent);

    }
    public boolean phanQuyen()
    {
        if(Build.VERSION.SDK_INT>=23) {
            //1. Nếu các quyền đã được gán thì return true
            if (checkSelfPermission(Manifest.permission.READ_PHONE_NUMBERS) ==
                    PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_PHONE_STATE) ==
                    PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
            //2. nếu các quyền chưa được gán thì cần xin người dùng cấp quyền, return false
            else
            {
                ActivityCompat.requestPermissions(Demo21Main2Activity.this,
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
