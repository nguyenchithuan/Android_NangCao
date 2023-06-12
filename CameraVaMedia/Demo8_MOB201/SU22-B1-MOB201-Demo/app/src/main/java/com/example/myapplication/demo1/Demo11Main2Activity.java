package com.example.myapplication.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class Demo11Main2Activity extends AppCompatActivity {
    EditText txt1,txt2;
    Button btnStart, btnStop;
    Intent intent1,intent2,intent3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo11_main2);
        txt1 = findViewById(R.id.demo11Txt1);
        txt2 = findViewById(R.id.demo11Txt2);
        btnStart = findViewById(R.id.demo11BtnStart);
        btnStop = findViewById(R.id.demo11BtnStop);

        intent1 = new Intent(this,MyService1.class);
        intent2 = new Intent(this,MyService2.class);
        intent3 = new Intent(this,MyService3.class);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //startService(intent1);
//                //lay du lieu nguoi dung nhap
//                String txtMaSV = txt1.getText().toString();
//                String txtTenSV = txt2.getText().toString();
//                //dua du lieu vao intent
//                intent2.putExtra("masv",txtMaSV);
//                intent2.putExtra("tensv",txtTenSV);
//                //goi service
//                startService(intent2);

                //Lay chuoi ky tu nhap vao
                String inputChar = txt1.getText().toString();
                //chuyen chuoi thanh mang ky tu
                char[] c = inputChar.toCharArray();
                //dua vao intent
                intent3.putExtra("char",c[0]);
                //lay chuoi can kiem tra
                String chuoiCanKiemTra = txt2.getText().toString();
                //dua vao intent
                intent3.putExtra("chk",chuoiCanKiemTra);
                //goi service
                startService(intent3);


            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent1);
            }
        });
    }
}
