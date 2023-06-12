package edu.poly.labs1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.poly.labs1.service.MyService1;
import edu.poly.labs1.service.MyService2;

public class MainActivity extends AppCompatActivity {
    private Button btnStartService, btnStopService;
    private Intent intent1, intent2;
    private EditText edData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);
        edData = findViewById(R.id.ed_data);

        intent1 = new Intent(this, MyService1.class);
        intent2 = new Intent(this, MyService2.class);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startService1();
                startService2();
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                stopService(intent1);
                stopService(intent2);
            }
        });
    }

    private void startService1() {
        Bundle bundle = new Bundle();
        bundle.putInt("StuId", 1);
        bundle.putString("StuName", "John");
        bundle.putString("Class", "PT9101");
        intent1.putExtras(bundle);

        startService(intent1);
    }

    private void startService2() {
        String data = edData.getText().toString().trim();
        intent2.putExtra("data", data);

        startService(intent2);
    }
}