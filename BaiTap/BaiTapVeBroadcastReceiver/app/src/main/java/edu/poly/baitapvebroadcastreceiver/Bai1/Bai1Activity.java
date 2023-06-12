package edu.poly.baitapvebroadcastreceiver.Bai1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import edu.poly.baitapvebroadcastreceiver.R;

public class Bai1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
//        phanQuyen();
        chay();
    }

    private boolean phanQuyen() {
        if(Build.VERSION.SDK_INT >= 23) {
            // kiểm tra các quyền xem đã được gán chưa
            if(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) ==
                    PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_PHONE_NUMBERS) ==
                    PackageManager.PERMISSION_GRANTED) {

                return true;

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.READ_PHONE_NUMBERS}, 1);
                return false;
            }
        } else {
            return false;
        }
    }

    private void chay() {
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.CALL_PHONE}, 1);
    }
}