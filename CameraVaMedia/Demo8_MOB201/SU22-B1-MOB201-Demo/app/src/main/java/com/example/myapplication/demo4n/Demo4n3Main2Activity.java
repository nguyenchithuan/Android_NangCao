package com.example.myapplication.demo4n;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class Demo4n3Main2Activity extends AppCompatActivity {
    TextView textView;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4n3_main2);
        textView = findViewById(R.id.demo4n3Tv1);

        Demo4n3XuLyCallback cb = new Demo4n3XuLyCallback(this);
        synchronized (cb)
        {
            while (!cb.dangKyMang())
            {
                try {
                    cb.wait(2000);
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if(Demo4n3NetworkVariable.isNetworkConnected)
        {
            textView.setText("Da ket noi voi mang internet");
        }
        else
        {
            textView.setText("Khong ket noi voi mang internet");
        }
    }
}
