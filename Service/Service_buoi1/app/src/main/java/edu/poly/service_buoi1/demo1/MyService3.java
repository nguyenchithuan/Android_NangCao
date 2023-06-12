package edu.poly.service_buoi1.demo1;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService3 extends IntentService {
    int count = 0;

    public MyService3() {
        super("MyService3");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Bundle bundle = intent.getExtras();

        if(bundle != null) {
            String strData = bundle.getString("chuoiKiTu");
            demKyTuChuoi(strData);
        }

    }

    private void demKyTuChuoi(String strData) {
        char[] chars = strData.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == 'A') {
                count++;
            }
        }
        Toast.makeText(this, "Số ký tự A là: " + count, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Số lượng ký tự A là: " + count, Toast.LENGTH_SHORT).show();
    }
}
