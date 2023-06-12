package com.example.myapplication.demo2n;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class Demo2n2Main2Activity extends AppCompatActivity {
    EditText txt1;
    Button btn1;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2n2_main2);
        txt1 = findViewById(R.id.demo2n2Txt1);
        btn1 = findViewById(R.id.demo2n2Btn1);
        context = this;
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lây dữ liệu từ người dùng nhập
                String dulieu = txt1.getText().toString();
                //Đưa dữ liệu vào intent
                Intent intent = new Intent(context,MyBr2.class);
                intent.putExtra("br",dulieu);
                //gửi dữ liệu cho broadcast
                sendBroadcast(intent);
            }
        });
    }
}
