package edu.poly.assignmentnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MonHocActivity extends AppCompatActivity {
    private Button btnDangKyMonHoc, btnMonHocDaDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_hoc);

        btnDangKyMonHoc = findViewById(R.id.btnDangKyMonHoc);
        btnMonHocDaDangKy = findViewById(R.id.btnMonHocDaDangKy);

        btnDangKyMonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonHocActivity.this, DangKyMonHocActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isAll", true); // hiển thị tất cả
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnMonHocDaDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonHocActivity.this, DangKyMonHocActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isAll", false); // hiển thị mỗi khóa đăng ký
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}