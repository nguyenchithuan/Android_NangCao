package edu.poly.assignmentnangcao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

import edu.poly.assignmentnangcao.adapter.DangKyMonHocAdapter;
import edu.poly.assignmentnangcao.model.MonHoc;
import edu.poly.assignmentnangcao.service.DangKyMonHocService;

public class DangKyMonHocActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private SharedPreferences pref;
    private DangKyMonHocAdapter adapter;
    private IntentFilter intentFilter;
    private int id;
    private Boolean isAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_mon_hoc);

        recycler = findViewById(R.id.recyclerDangKyMonHoc);

        intentFilter = new IntentFilter();
        intentFilter.addAction("ListMonHoc");
        intentFilter.addAction("DKMonHoc");

        pref = getSharedPreferences(KeyWord.PREF_FILE, MODE_PRIVATE);
        id = pref.getInt("id", -1);

        Bundle bundleIsAll = getIntent().getExtras();
        isAll = bundleIsAll.getBoolean("isAll");

        Intent intent = new Intent(DangKyMonHocActivity.this, DangKyMonHocService.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putBoolean("isAll", isAll);
        intent.putExtras(bundle);
        startService(intent);
    }

    private void loadData(ArrayList<MonHoc> list) {
        // các dạng layout của recycle
        // GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        adapter = new DangKyMonHocAdapter(DangKyMonHocActivity.this, id, isAll);
        adapter.setData(list);
        recycler.setAdapter(adapter);
    }

    BroadcastReceiver myBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "ListMonHoc":
                case "DKMonHoc":
                    Bundle bundle = intent.getExtras();
                    ArrayList<MonHoc> list = (ArrayList<MonHoc>) bundle.getSerializable("list");
                    loadData(list);
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcast, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcast);
    }
}