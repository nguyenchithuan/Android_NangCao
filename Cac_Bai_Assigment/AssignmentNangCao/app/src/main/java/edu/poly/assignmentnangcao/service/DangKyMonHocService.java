package edu.poly.assignmentnangcao.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.poly.assignmentnangcao.dao.DangKyKhoaHocDao;
import edu.poly.assignmentnangcao.model.MonHoc;

public class DangKyMonHocService extends Service {
    private DangKyKhoaHocDao dao;
    private ArrayList<MonHoc> list;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            int id = bundle.getInt("id");
            boolean isAll = bundle.getBoolean("isAll");
            dao = new DangKyKhoaHocDao(this);
            list = dao.getAllMonHoc(id, isAll);

            Intent intentBR = new Intent("ListMonHoc");
            Bundle bundleBR = new Bundle();
            bundleBR.putSerializable("list", list);
            intentBR.putExtras(bundleBR);
            sendBroadcast(intentBR);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
