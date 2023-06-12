package com.example.myapplication.demo1n;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class Demo1nMain2Activity extends AppCompatActivity {
    Button btnStart, btnStop;
    EditText txt1,txt2;
    Intent intent1,intent2,intent3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1n_main2);
        btnStart = findViewById(R.id.demo1nBtnStart);
        btnStop = findViewById(R.id.demo1nBtnStop);
        txt1 = findViewById(R.id.demo1nTxt1);
        txt2 = findViewById(R.id.demo1nTxt2);
        intent1 = new Intent(this,MyServices1.class);
        intent2 = new Intent(this,MyServices2.class);
        intent3 = new Intent(this,MyServices3.class);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lay ve chuoi chua ky tu can dem
                String inputChar = txt1.getText().toString();
                //chuyen chuoi thanh mang ky tu
                char[] c = inputChar.toCharArray();
                //truyen ky tu cho intent
                intent3.putExtra("char",c[0]);
                //lay chuoi can kiem tra
                String chuoiCanKiemTra = txt2.getText().toString();
                //dua chuoi vao intent
                intent3.putExtra("chuoikiemtra",chuoiCanKiemTra);
                //goi service xu ly
                startService(intent3);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent2);
            }
        });
    }
}
