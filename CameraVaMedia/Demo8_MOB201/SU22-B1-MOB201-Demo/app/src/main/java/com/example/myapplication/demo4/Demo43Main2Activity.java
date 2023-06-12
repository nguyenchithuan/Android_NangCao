package com.example.myapplication.demo4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class Demo43Main2Activity extends AppCompatActivity {
    public static boolean isNetworkConnected1;
    TextView textView;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo43_main2);
        textView = findViewById(R.id.demo43Tv1);
        Demo43CheckNetwork cb = new Demo43CheckNetwork(getApplicationContext());
        synchronized (cb) {
            while (!cb.dangKyMang()) // while instead of if due to "spurious wakeups"
            {
                try {
                    cb.wait(2000);
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        if(Demo43NetworkAvailable.isNetworkConnected==true)
        {
            textView.setText("Da ket noi thanh cong voi internet");
        }
        else
        {
            if(Demo43NetworkAvailable.isNetworkConnected==true)
            {
                textView.setText("Ket noi internet thanh cong");
            }
            else
            {
                textView.setText("Ket noi internet that bai");
            }

        }
    }
}
