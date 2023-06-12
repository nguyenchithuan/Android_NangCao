package edu.poly.ontap1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import edu.poly.ontap1.adapter.SanPhamAdapter;
import edu.poly.ontap1.dao.DuLieuDao;
import edu.poly.ontap1.models.SanPham;

public class ShowActivity extends AppCompatActivity {
    private RecyclerView rcvShow;
    private TextView tvSoLuong;
    private DuLieuDao dao;
    private ArrayList<SanPham> list;
    private SanPhamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        rcvShow = findViewById(R.id.rcv_show);
        tvSoLuong = findViewById(R.id.tv_soLuong);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvShow.setLayoutManager(layoutManager);

        dao = new DuLieuDao(this);
        list = dao.getAllSp();
        adapter = new SanPhamAdapter(this);
        adapter.setData(list);
        rcvShow.setAdapter(adapter);

        tvSoLuong.setText("Số lượng sản phẩm: " + dao.getCount());
    }
}