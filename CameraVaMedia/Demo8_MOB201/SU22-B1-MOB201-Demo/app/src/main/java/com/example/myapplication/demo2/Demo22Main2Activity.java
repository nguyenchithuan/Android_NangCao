package com.example.myapplication.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class Demo22Main2Activity extends AppCompatActivity {
    Button button;
    EditText txt;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo22_main2);
        context = this;
        button = findViewById(R.id.demo22Btn1);
        txt = findViewById(R.id.demo22Txt1);
        button.setOnClickListener(v->{
            Intent intent = new Intent(context,MyBroadcast3.class);
            String dulieu = txt.getText().toString();
            intent.putExtra("km",dulieu);
            sendBroadcast(intent);
        });



    }
}
