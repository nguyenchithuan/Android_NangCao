package edu.poly.hocbroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.poly.hocbroadcastreceiver.broadcast.MyBroadcast2;
import edu.poly.hocbroadcastreceiver.broadcast.MyBroadcast3;

public class Demo23Activity extends AppCompatActivity {
    private Button button;
    private EditText edDuLieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo23);

        button = findViewById(R.id.btn_gui);
        edDuLieu = findViewById(R.id.ed_nhanDuLieu);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy dữ liệu người dùng
                String dulieu = edDuLieu.getText().toString().trim();


                // truyền dữ liệu cho intent
                Intent intent = new Intent(getApplicationContext(), MyBroadcast3.class);
                intent.putExtra("br", dulieu);

                // yêu cầu broadcast
                sendBroadcast(intent);
            }
        });
    }
}