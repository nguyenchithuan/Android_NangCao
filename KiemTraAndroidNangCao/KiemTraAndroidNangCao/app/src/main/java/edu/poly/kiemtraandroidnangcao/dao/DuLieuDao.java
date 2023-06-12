package edu.poly.kiemtraandroidnangcao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.poly.kiemtraandroidnangcao.helper.DbHelper;
import edu.poly.kiemtraandroidnangcao.models.Bai3;
import edu.poly.kiemtraandroidnangcao.models.Bai4;
import edu.poly.kiemtraandroidnangcao.models.ChiTiet;
import edu.poly.kiemtraandroidnangcao.models.HoaDon;
import edu.poly.kiemtraandroidnangcao.models.LoaiSp;
import edu.poly.kiemtraandroidnangcao.models.SanPham;

public class DuLieuDao {
    private SQLiteDatabase db;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public DuLieuDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
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
        values.put("ngayNhap", sdf.format(sanPham.getNgayNhap_ph26023()));
        values.put("dgNhap", sanPham.getDgNhap_ph26023());
        return db.insert("SanPham", null, values);
    }

    public long insert(HoaDon hoaDon) {
        ContentValues values = new ContentValues();
        values.put("ngayXuat", sdf.format(hoaDon.getNgayXuat_ph26023()));
        return db.insert("HoaDon", null, values);
    }

    public long insert(ChiTiet chiTiet) {
        ContentValues values = new ContentValues();
        values.put("maSp", chiTiet.getMaSp_ph26023());
        values.put("maHd", chiTiet.getMaHd_ph26023());
        values.put("slXuat", chiTiet.getSlXuat_ph26023());
        values.put("dgXuat", chiTiet.getDgXuat_ph26023());
        return db.insert("ChiTietHD", null, values);
    }

    public ArrayList<Bai3> getSumSp(String ngayDau, String ngayTiep) {
        ArrayList<Bai3> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT LOWER(tenSp), SUM(slNhap) FROM SanPham " +
                "WHERE ngayNhap BETWEEN ? AND ? GROUP BY LOWER(tenSp)", new String[]{ngayDau, ngayTiep});
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Bai3 bai3 = new Bai3();
                bai3.setName(cursor.getString(0));
                bai3.setAmount(cursor.getInt(1));

                list.add(bai3);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public ArrayList<Bai4> getListBai4(String ngayDau, String ngayTiep) {
        ArrayList<Bai4> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT sp.maLoai, sp.maSp, ct.slXuat FROM ChiTietHD AS ct " +
                "LEFT JOIN HoaDon AS hd ON ct.maHd = hd.maHd " +
                "LEFT JOIN SanPham AS sp ON ct.maSp = sp.maSp " +
                "WHERE hd.ngayXuat BETWEEN ? AND ?", new String[]{ngayDau, ngayTiep});

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                Bai4 bai4 = new Bai4();

                bai4.setMaLoai(cursor.getInt(0));
                bai4.setMaSanPham(cursor.getInt(1));
                bai4.setSoLuong(cursor.getInt(2));

                list.add(bai4);

                cursor.moveToNext();
            }
            cursor.close();
        }

        return list;
    }


    public String getTenLoai(String data) {
        String chuoi = "";
        Cursor cursor = db.rawQuery("SELECT tenLoai FROM LoaiSP WHERE maLoai = ?", new String[]{data});

        if(cursor.moveToFirst()) {
            chuoi = cursor.getString(0);
        }

        return chuoi;
    }

    public String getTenSanPham(String data) {
        String chuoi = "";
        Cursor cursor = db.rawQuery("SELECT tenSp FROM SanPham WHERE maSp = ?", new String[]{data});

        if(cursor.moveToFirst()) {
            chuoi = cursor.getString(0);
        }

        return chuoi;
    }

    public int getMaLoai() {
        int id = 1;

        Cursor cursor = db.rawQuery("SELECT maLoai FROM LoaiSP", null);

        if (cursor.moveToFirst()) {
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

        if (cursor.moveToFirst()) {
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

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                id = cursor.getInt(0);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return id;
    }
}
