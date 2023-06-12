package edu.poly.assignmentnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.poly.assignmentnangcao.dao.DangKyKhoaHocDao;
import edu.poly.assignmentnangcao.model.MonHoc;

public class MainActivity extends AppCompatActivity {
    private Button btnMonHoc;
    private Button btnBanDo;
    private Button btnTinTuc;
    private Button btnMangXaHoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMonHoc = findViewById(R.id.btnMonHoc);
        btnBanDo = findViewById(R.id.btnBanDo);
        btnTinTuc = findViewById(R.id.btnTinTuc);
        btnMangXaHoi = findViewById(R.id.btnMangXaHoi);

        btnMonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MonHocActivity.class);
                startActivity(intent);
            }
        });

        btnBanDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GoogleMapsActivity.class);
                startActivity(intent);
            }
        });

        btnTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TinTucActivity.class);
                startActivity(intent);
            }
        });

        btnMangXaHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}