package edu.poly.baitapvebroadcastreceiver.Bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.poly.baitapvebroadcastreceiver.R;

public class Bai3Activity extends AppCompatActivity {
    public static final String BAI3_DATA =  "fpoly.android.DATA";

    private EditText edData;
    private Button btnSendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        edData = findViewById(R.id.ed_data_b3);
        btnSendBroadcast = findViewById(R.id.btn_send_broadcast_b3);

        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickSendBroadcast();
            }
        });
    }

    private void onclickSendBroadcast() {
        String data = edData.getText().toString().trim();

        Intent intent = new Intent(this, MyBroadcastReceiver3.class);

        intent.putExtra(BAI3_DATA, data);

        sendBroadcast(intent);
    }
}