package edu.poly.kiemtraandroidnangcao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import edu.poly.kiemtraandroidnangcao.adapter.Bai3Adapter;
import edu.poly.kiemtraandroidnangcao.dao.DuLieuDao;
import edu.poly.kiemtraandroidnangcao.models.Bai3;

public class Bai3Activity extends AppCompatActivity {
    private RecyclerView rcvShow;
    private DuLieuDao dao;
    private ArrayList<Bai3> list;
    private Bai3Adapter adapter;
    private EditText edNgayBatDau, edNgayKetThuc;
    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        edNgayBatDau = findViewById(R.id.edNgayBatDau);
        edNgayKetThuc = findViewById(R.id.edNgayKetThuc);
        btnClick = findViewById(R.id.btn_click);
        rcvShow = findViewById(R.id.rcv_show);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvShow.setLayoutManager(layoutManager);

        dao = new DuLieuDao(this);
        list = dao.getSumSp(edNgayBatDau.getText().toString().trim(), edNgayKetThuc.getText().toString().trim());
        adapter = new Bai3Adapter(this);
        adapter.setData(list);
        rcvShow.setAdapter(adapter);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = dao.getSumSp(edNgayBatDau.getText().toString().trim(), edNgayKetThuc.getText().toString().trim());
                adapter.setData(list);
            }
        });
    }
}