package edu.poly.assignmentnangcao.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import edu.poly.assignmentnangcao.KeyWord;
import edu.poly.assignmentnangcao.dao.NguoiDungDao;

public class KiemTraDangNhapService extends Service {
    private NguoiDungDao dao;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // thao tác với dao thông qua service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            String user = bundle.getString(KeyWord.USER);
            String pass = bundle.getString(KeyWord.PASS);

            dao = new NguoiDungDao(this);
            Boolean check = dao.checkLogin(user, pass);

            // truyền lại dữ liệu thông qua broadcast recever
            Intent intentBR = new Intent(KeyWord.CHECK_LOGIN);
            intentBR.putExtra(KeyWord.CHECK, check);
            // gửi dữ liệu thông qua broadcast
            sendBroadcast(intentBR);
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
