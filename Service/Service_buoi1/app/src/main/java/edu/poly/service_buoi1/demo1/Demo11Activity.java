package edu.poly.service_buoi1.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.poly.service_buoi1.R;

public class Demo11Activity extends AppCompatActivity {
    EditText txt1, txt2, txt3;
    Button btnStart, btnStop;
    Intent intent1, intent2, intent3, intent4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo11);

        txt1 = findViewById(R.id.ed_demo11Txt1);
        txt2 = findViewById(R.id.ed_demo11Txt2);
        txt3 = findViewById(R.id.ed_demo11Txt3);
        btnStart = findViewById(R.id.btn_start_demo11);
        btnStop = findViewById(R.id.btn_stop_demo11);
        intent1 = new Intent(this, MyService1.class);
        intent2 = new Intent(this, MyService2.class);
        intent3 = new Intent(this, MyService3.class);
        intent4 = new Intent(this, MyService4.class);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startIntent1();
//                startIntent2();
                startIntent3();
//                startIntent4();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                stopService(intent1);
//                stopService(intent2);
                stopService(intent3);
//                stopService(intent4);
            }
        });
    }

    private void startIntent1() {
        startService(intent4);
    }

    private void startIntent2() {
        // 1.lấy dữ liệu người dùng nhập
        String maSV = txt1.getText().toString().trim();
        String tenSv = txt2.getText().toString().trim();

        // 2.đưa vào intent
        intent2.putExtra("masv", maSV);
        intent2.putExtra("tensv", tenSv);

        // 3. gọi service
        startService(intent2);
    }

    private void startIntent3() {
        // để chuỗi vào đây để khi mỗi lần onclick thì sẽ nhập lại dữ liệu
        String chuoiKiTu = txt3.getText().toString().trim();

        Bundle bundle = new Bundle();
        bundle.putString("chuoiKiTu", chuoiKiTu);
        intent3.putExtras(bundle);

        startService(intent3);
    }

    private void startIntent4() {
        String chuoiKiTu = txt3.getText().toString().trim();

        Bundle bundle = new Bundle();
        bundle.putString("chuoiKiTu", chuoiKiTu);
        intent4.putExtras(bundle);

        startService(intent4);
    }




}