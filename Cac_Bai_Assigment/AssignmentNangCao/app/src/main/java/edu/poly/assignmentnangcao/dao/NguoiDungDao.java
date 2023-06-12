package edu.poly.assignmentnangcao.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.poly.assignmentnangcao.KeyWord;
import edu.poly.assignmentnangcao.dbhelper.DbHelper;

public class NguoiDungDao {
    private SQLiteDatabase db;
    private SharedPreferences pref;

    public NguoiDungDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        pref = context.getSharedPreferences(KeyWord.PREF_FILE, Context.MODE_PRIVATE);
    }

    public boolean checkLogin(String user, String pass) {
        Cursor cursor = db.rawQuery("SELECT * FROM NguoiDung WHERE username = ? AND password = ?", new String[] {user, pass});
        if(cursor.moveToFirst()) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("id", cursor.getInt(0));
            editor.commit();
            return true;
        }
        return false;
    }
}
