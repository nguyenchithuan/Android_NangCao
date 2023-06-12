package com.example.myapplication.demo2n;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class Demo2n3Main2Activity extends AppCompatActivity {
    Button button;
    EditText txt;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2n3_main2);
        context = this;
        button = findViewById(R.id.demo2n3Btn1);
        txt = findViewById(R.id.demo2n3Txt1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MyBr3.class);
                String dulieu = txt.getText().toString();
                intent.putExtra("br",dulieu);
                sendBroadcast(intent);
            }
        });
    }
}
