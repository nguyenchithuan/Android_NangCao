package edu.poly.ontap1.helper;

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
                "ngayNhap TEXT NOT NULL, " +
                "dgNhap INTEGER NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE HoaDon(" +
                "maHd INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "slXuat INTEGER NOT NULL, " +
                "ngayXuat TEXT NOT NULL, " +
                "dgXuat INTEGER NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE ChiTietHD(" +
                "maCTHD INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maSp INTEGER REFERENCES SanPham(maSp), " +
                "maHd INTGEGER REFERENCES HoaDon(maHd))";
        db.execSQL(sql);


        sql = "INSERT INTO LoaiSP VALUES" +
                "(1, 'Loai1')," +
                "(2, 'Loai2')";
        db.execSQL(sql);

        sql = "INSERT INTO SanPham VALUES" +
                "(1, 'Sp1', 1, 100, '20-10-2022', 200000)," +
                "(2, 'Sp2', 2, 90, '21-11-2022', 450000)";
        db.execSQL(sql);

        sql = "INSERT INTO HoaDon VALUES" +
                "(1, 90, '25-12-2022', 300000)," +
                "(2, 80, '30-11-2022', 700000)";
        db.execSQL(sql);

        sql = "INSERT INTO ChiTietHD VALUES" +
                "(1, 1, 1)," +
                "(2, 2, 2)";
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
