package edu.poly.kiemtraandroidnangcao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import edu.poly.kiemtraandroidnangcao.adapter.Bai4Adapter;
import edu.poly.kiemtraandroidnangcao.dao.DuLieuDao;
import edu.poly.kiemtraandroidnangcao.models.Bai4;

public class Bai4Activity extends AppCompatActivity {
    private RecyclerView rcv;
    private DuLieuDao dao;
    private ArrayList<Bai4> list;
    private Bai4Adapter adapter;
    private EditText edNgayBatDau, edNgayKetThuc;
    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);

        edNgayBatDau = findViewById(R.id.edNgayBatDau);
        edNgayKetThuc = findViewById(R.id.edNgayKetThuc);
        btnClick = findViewById(R.id.btn_click);
        rcv = findViewById(R.id.rcv_show);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
        String batDau = edNgayBatDau.getText().toString().trim();
        String ketThuc = edNgayKetThuc.getText().toString().trim();

        dao = new DuLieuDao(this);
        list = dao.getListBai4(batDau, ketThuc);
        adapter = new Bai4Adapter(this);
        adapter.setData(list);
        rcv.setAdapter(adapter);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = dao.getListBai4(edNgayBatDau.getText().toString().trim(), edNgayKetThuc.getText().toString().trim());
                adapter.setData(list);
            }
        });
    }
}