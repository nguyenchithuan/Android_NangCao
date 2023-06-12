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

public class DkAndHuyMonHocService extends Service {
    private DangKyKhoaHocDao dao;
    private ArrayList<MonHoc> list;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        dao = new DangKyKhoaHocDao(this);

        if(bundle != null) {
            int id = bundle.getInt("id");
            String code = bundle.getString("code");
            int isRegister = bundle.getInt("isRegister");
            boolean isAll = bundle.getBoolean("isAll");

            if(isRegister == id) { // nếu như có dữ liệu thì hủy đăng ký
                if(dao.deleteMH(id, code) < 0) {
                    Toast.makeText(this, "Hủy đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            } else { // không có dữ liệu thì thêm đăng ký
                if(dao.insertMH(id, code) < 0) {
                    Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            list = dao.getAllMonHoc(id, isAll);
            Intent intentBR = new Intent("DKMonHoc");
            Bundle bundleBR = new Bundle();
            bundleBR.putSerializable("list", list);
            intentBR.putExtras(bundleBR);
            sendBroadcast(intentBR);
        }


        return super.onStartCommand(intent, flags, startId);
    }

}
