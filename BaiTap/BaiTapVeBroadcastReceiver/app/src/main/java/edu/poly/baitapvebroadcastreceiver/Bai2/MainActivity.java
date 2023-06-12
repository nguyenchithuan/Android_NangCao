package edu.poly.baitapvebroadcastreceiver.Bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import edu.poly.baitapvebroadcastreceiver.Bai2.MyBroadcastReceiver2;
import edu.poly.baitapvebroadcastreceiver.R;

public class MainActivity extends AppCompatActivity {
    public static final String MY_ACTION = "fpoly.android.CUSTOM_INTENT";
    public static final String MY_DATA = "fpoly.android.DATA";

    private EditText edData;
    private Button btnSendBroadcast;

    MyBroadcastReceiver2 myBroadcastReceiver2 = new MyBroadcastReceiver2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edData = findViewById(R.id.ed_data_b2);
        btnSendBroadcast = findViewById(R.id.btn_send_broadcast_b2);

        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSendBroadcast();
            }
        });
    }

    private void clickSendBroadcast() {
        String data = edData.getText().toString().trim();
        Intent intent = new Intent(MY_ACTION);
        intent.putExtra(MY_DATA, data);

        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(MY_ACTION);
        registerReceiver(myBroadcastReceiver2, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver2);
    }
}
