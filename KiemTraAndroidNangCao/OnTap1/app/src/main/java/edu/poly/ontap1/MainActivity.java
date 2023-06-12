package edu.poly.ontap1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.poly.ontap1.dao.DuLieuDao;
import edu.poly.ontap1.models.ChiTiet;
import edu.poly.ontap1.models.HoaDon;
import edu.poly.ontap1.models.LoaiSp;
import edu.poly.ontap1.models.SanPham;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4;
    private EditText edTenLoai, edTenSp, edSlNhap, edSlXuat, edGiaNhap, edGiaXuat, edNgayNhap, edNgayXuat;
    private String strTenSp, strTenLoai, strSlNhap, strSlXuat, strDgNhap, strDgXuat, strNgayNhap, strNgayXuat;
    private DuLieuDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXaView();
        dao = new DuLieuDao(this);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickInsertData();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ShowActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onclickInsertData() {
        strTenSp = edTenSp.getText().toString().trim();
        strTenLoai = edTenLoai.getText().toString().trim();
        strSlNhap = edSlNhap.getText().toString().trim();
        strSlXuat = edSlXuat.getText().toString().trim();
        strDgNhap = edGiaNhap.getText().toString().trim();
        strDgXuat = edGiaXuat.getText().toString().trim();
        strNgayNhap = edNgayNhap.getText().toString().trim();
        strNgayXuat = edNgayXuat.getText().toString().trim();

        if(kiemTraDuLieu()) {
            dao.insert(new LoaiSp(strTenLoai));
            dao.insert(new SanPham(strTenSp, dao.getMaLoai(), Integer.parseInt(strSlNhap), strNgayNhap, Integer.parseInt(strDgNhap)));
            dao.insert(new HoaDon(Integer.parseInt(strSlXuat), strNgayXuat, Integer.parseInt(strDgXuat)));
            dao.insert(new ChiTiet(dao.getMaSanPham(), dao.getMaHoaDon()));
            Toast.makeText(this, "Thêm thành công !", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean kiemTraDuLieu() {
        if(strTenSp.isEmpty() || strTenLoai.isEmpty() || strSlNhap.isEmpty() || strSlXuat.isEmpty() ||
                strDgNhap.isEmpty() || strDgXuat.isEmpty() || strNgayNhap.isEmpty() || strNgayXuat.isEmpty()) {
            Toast.makeText(this, "Nhập dữ liệu đầy đủ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void anhXaView() {
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);

        edTenLoai = findViewById(R.id.ed_tenLoai);
        edTenSp  = findViewById(R.id.ed_tenSp);
        edSlNhap = findViewById(R.id.ed_slNhap);
        edSlXuat = findViewById(R.id.ed_slXuat);
        edGiaNhap = findViewById(R.id.ed_giaNhap);
        edGiaXuat = findViewById(R.id.ed_giaXuat);
        edNgayNhap = findViewById(R.id.ed_ngayNhap);
        edNgayXuat = findViewById(R.id.ed_ngayXuat);
    }
}