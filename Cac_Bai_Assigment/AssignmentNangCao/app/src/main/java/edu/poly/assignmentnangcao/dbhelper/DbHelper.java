package edu.poly.assignmentnangcao.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DANGKYMONHOC";
    private static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE NguoiDung(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "password TEXT NOT NULL, " +
                "name TEXT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE MonHoc(" +
                "code TEXT PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "teacher TEXT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE ThongTin(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "code TEXT REFERENCES MonHoc(code), " +
                "date TEXT NOT NULL, " +
                "address TEXT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE DangKy(" +
                "id INTEGER REFERENCES NguoiDung(id), " +
                "code TEXT REFERENCES MonHoc(code))";
        db.execSQL(sql);





        sql = "INSERT INTO NguoiDung VALUES" +
                "(1,'admin','123','Admin'), " +
                "(2,'chithuan','123','Chí Thuận')";
        db.execSQL(sql);

        sql = "INSERT INTO MonHoc VALUES " +
                "('MOB201','Android Nâng Cao','Nguyễn Trí Định'), " +
                "('MOB306','React Native','Trần Anh Hùng'), " +
                "('MOB2041','Dự Án Mẫu','Nguyễn Trí Định')";
        db.execSQL(sql);

        sql = "INSERT INTO ThongTin VALUES" +
                "(1, 'MOB201', 'Ca 2 - 19/09/2022', 'T1011'), " +
                "(2, 'MOB201', 'Ca 2 - 21/09/2022', 'T1011'), " +
                "(3, 'MOB201', 'Ca 2 - 23/09/2022', 'T1011'), " +
                "(4, 'MOB306', 'Ca 5 - 20/09/2022', 'F204'), " +
                "(5, 'MOB306', 'Ca 5 - 22/09/2022', 'F204'), " +
                "(6, 'MOB2041', 'Ca 1 - 20/09/2022', 'Online - https://meet.google.com/rku-beuk-wqu')";
        db.execSQL(sql);

        sql = "INSERT INTO DangKy VALUES " +
                "(1,'MOB201'), " +
                "(1,'MOB306'), " +
                "(2,'MOB306')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS NguoiDung");
        db.execSQL("DROP TABLE IF EXISTS MonHoc");
        db.execSQL("DROP TABLE IF EXISTS ThongTin");
        db.execSQL("DROP TABLE IF EXISTS DangKy");
        onCreate(db);
    }
}
