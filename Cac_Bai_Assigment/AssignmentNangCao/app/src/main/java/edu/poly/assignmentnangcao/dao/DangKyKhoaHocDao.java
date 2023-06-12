package edu.poly.assignmentnangcao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.poly.assignmentnangcao.dbhelper.DbHelper;
import edu.poly.assignmentnangcao.model.MonHoc;
import edu.poly.assignmentnangcao.model.ThongTin;

public class DangKyKhoaHocDao {
    private SQLiteDatabase db;

    public DangKyKhoaHocDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<MonHoc> getData(String sql , String ...selection) {
        ArrayList<MonHoc> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selection);
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                MonHoc monHoc = new MonHoc();

                monHoc.setCode(cursor.getString(0));
                monHoc.setName(cursor.getString(1));
                monHoc.setTeacher(cursor.getString(2));
                monHoc.setIsRegister(cursor.getInt(3));
                // lấy ra mảng thông tin
                monHoc.setListTT(getThongTinMonHoc(cursor.getString(0)));

                list.add(monHoc);
                cursor.moveToNext();
            }
        }
        return list;
    }

    public ArrayList<MonHoc> getAllMonHoc(int id, boolean isAll) {
        ArrayList<MonHoc> list = new ArrayList<>();
        String sql;

        if(isAll) {
            sql = "SELECT mh.code, mh.name, mh.teacher, dk.id FROM MonHoc AS mh " +
                    "LEFT JOIN DangKy AS dk ON mh.code = dk.code AND dk.id = ?";
        } else {
            sql = "SELECT mh.code, mh.name, mh.teacher, dk.id FROM MonHoc AS mh " +
                    "INNER JOIN DangKy AS dk ON mh.code = dk.code WHERE dk.id = ?";
        }
        list = getData(sql, String.valueOf(id));
        return list;
    }

    public long insertMH(int id, String code) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("code", code);
        return db.insert("DangKy", null, values);
    }

    public int deleteMH(int id, String code) {
        return db.delete("DangKy", "id = ? AND code = ?", new String[] {String.valueOf(id), code});
    }

    // danh sach thông tin của từng môn học
    public ArrayList<ThongTin> getThongTinMonHoc(String code) {
        ArrayList<ThongTin> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT date, address FROM ThongTin WHERE code = ?", new String[] {code});


        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ThongTin thongTin = new ThongTin();
                thongTin.setDate(cursor.getString(0));
                thongTin.setAddress(cursor.getString(1));
                list.add(thongTin);

                cursor.moveToNext();
            }
            cursor.close();
        }

        return list;
    }
}
