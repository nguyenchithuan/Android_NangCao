package edu.poly.hocbroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import edu.poly.hocbroadcastreceiver.broadcast.MyBroadcast1;

public class MainActivity extends AppCompatActivity {

    // Broadcast: quảng bá, phát thông điệp
    // Receiver: nhân thông điệp
    // => Nhận và phát lại thông điệp cho người dùng biết
    // 1. Cuộc gới đến thì đến serivce
    // 2. Service gửi thông địp có cuộc gọi đến broadcastReceiver
    // 3. Receiver nhận thông điệp do service gửi
    // 4. Broadcast phát lại thông điệp thành tiếng chuông cho người dùng biết

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phanQuyen();

        Intent intent = new Intent(this, MyBroadcast1.class);
        sendBroadcast(intent);

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

}