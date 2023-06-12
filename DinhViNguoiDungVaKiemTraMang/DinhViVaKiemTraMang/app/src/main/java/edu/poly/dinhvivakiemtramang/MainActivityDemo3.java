package edu.poly.dinhvivakiemtramang;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivityDemo3 extends AppCompatActivity {
    private TextView tvDemo3;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo3);

        tvDemo3 = findViewById(R.id.tvDemo3);

        Demo3CheckNetwork demo4CheckNetwork = new Demo3CheckNetwork(this);
        demo4CheckNetwork.dangKyMang();

        if(Demo3NetworkAvailable.isNetWorkConnected) {
            tvDemo3.setText("Đã kết nối internet");
        } else {
            tvDemo3.setText("Chưa kết nối internet");
        }
    }
}