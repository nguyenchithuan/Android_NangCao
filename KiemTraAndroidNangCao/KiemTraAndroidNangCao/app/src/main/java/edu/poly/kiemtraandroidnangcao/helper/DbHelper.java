package edu.poly.kiemtraandroidnangcao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "KiemTra";
    private static final int DB_VERSION = 3;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE LoaiSP(" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE SanPham(" +
                "maSp INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenSp TEXT NOT NULL, " +
                "maLoai INTEGER REFERENCES LoaiSP(maLoai), " +
                "slNhap INTEGER NOT NULL, " +
                "ngayNhap DATE NOT NULL, " +
                "dgNhap INTEGER NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE HoaDon(" +
                "maHd INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ngayXuat DATE NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE ChiTietHD(" +
                "maCTHD INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maSp INTEGER REFERENCES SanPham(maSp), " +
                "maHd INTGEGER REFERENCES HoaDon(maHd), " +
                "slXuat INTEGER NOT NULL, " +
                "dgXuat INTEGER NOT NULL)";
        db.execSQL(sql);


        sql = "INSERT INTO LoaiSP VALUES" +
                "(1, 'Loai1')," +
                "(2, 'Loai2')";
        db.execSQL(sql);

        sql = "INSERT INTO SanPham VALUES" +
                "(1, 'Sp1', 1, 100, '2022-10-20', 200000)," +
                "(2, 'Sp2', 1, 120, '2022-10-09', 210000)," +
                "(3, 'Sp2', 2, 90, '2021-09-07', 450000)";

        db.execSQL(sql);

        sql = "INSERT INTO HoaDon VALUES" +
                "(1, '2022-11-01')," +
                "(2, '2022-10-25')," +
                "(3, '2021-09-21')";
        db.execSQL(sql);

        sql = "INSERT INTO ChiTietHD VALUES" +
                "(1, 1, 1, 70, 2000000)," +
                "(2, 2, 2, 120, 2000000)," +
                "(3, 3, 3, 80, 3400000)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS LoaiSP");
        db.execSQL("DROP TABLE IF EXISTS SanPham");
        db.execSQL("DROP TABLE IF EXISTS HoaDon");
        db.execSQL("DROP TABLE IF EXISTS ChiTietHD");
        onCreate(db);
    }
}
