package edu.poly.ontap1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.poly.ontap1.helper.DbHelper;
import edu.poly.ontap1.models.ChiTiet;
import edu.poly.ontap1.models.HoaDon;
import edu.poly.ontap1.models.LoaiSp;
import edu.poly.ontap1.models.SanPham;

public class DuLieuDao {
    private SQLiteDatabase db;

    public DuLieuDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<SanPham> getAllSp() {
        ArrayList<SanPham> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM SanPham", null);

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                SanPham sanPham =  new SanPham();
                sanPham.setMaSp_ph26023(cursor.getInt(0));
                sanPham.setTenSp_ph26023(cursor.getString(1));
                sanPham.setMaLoai_ph26023(cursor.getInt(2));
                sanPham.setSlNhap_ph26023(cursor.getInt(3));
                sanPham.setNgayNhap_ph26023(cursor.getString(4));
                sanPham.setDgNhap_ph26023(cursor.getInt(5));

                list.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return list;
    }

    public int getCount() {
        int count = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM SanPham", null);
        if(cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.getCount();

        return count;
    }

    public long insert(LoaiSp loaiSp) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSp.getTenLoai_ph26023());
        return db.insert("LoaiSP", null, values);
    }

    public long insert(SanPham sanPham) {
        ContentValues values = new ContentValues();
        values.put("tenSp", sanPham.getTenSp_ph26023());
        values.put("maLoai", sanPham.getMaLoai_ph26023());
        values.put("slNhap", sanPham.getSlNhap_ph26023());
        values.put("ngayNhap", sanPham.getNgayNhap_ph26023());
        values.put("dgNhap", sanPham.getDgNhap_ph26023());
        return db.insert("SanPham", null, values);
    }

    public long insert(HoaDon hoaDon) {
        ContentValues values = new ContentValues();
        values.put("slXuat", hoaDon.getSlXuat_ph26023());
        values.put("ngayXuat", hoaDon.getNgayXuat_ph26023());
        values.put("dgXuat", hoaDon.getDgXuat_ph26023());
        return db.insert("HoaDon", null, values);
    }
    public long insert(ChiTiet chiTiet) {
        ContentValues values = new ContentValues();
        values.put("maSp", chiTiet.getMaSp_ph26023());
        values.put("maHd", chiTiet.getMaHd_ph26023());
        return db.insert("ChiTietHD", null, values);
    }

    public int getMaLoai() {
        int id = 1;

        Cursor cursor = db.rawQuery("SELECT maLoai FROM LoaiSP", null);

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                id = cursor.getInt(0);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return id;
    }

    public int getMaSanPham() {
        int id = 1;

        Cursor cursor = db.rawQuery("SELECT maSp FROM SanPham", null);

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                id = cursor.getInt(0);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return id;
    }

    public int getMaHoaDon() {
        int id = 1;

        Cursor cursor = db.rawQuery("SELECT maHd FROM HoaDon", null);

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                id = cursor.getInt(0);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return id;
    }
}
