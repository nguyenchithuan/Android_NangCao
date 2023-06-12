package com.example.myapplication.demo3n;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.demo2n.Demo2n1Main2Activity;

import java.util.ArrayList;
import java.util.List;

public class Demo3n3Main2Activity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3n3_main2);
        listView = findViewById(R.id.demo3n3Listview);
        phanQuyen();
        getContacts();
    }
    public void getContacts()
    {
        List<String> list = new ArrayList<>();
        //b1. Khai bao duong dan
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        //b2: khai bao con tro hang
        Cursor cursor = getContentResolver().query(uri,
                null,null,null,null,null);
        //b3. kiem tra xem co du lueu khong
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                //di chuyen ve ban ghi dau tien
                //3.1. Lay truong du lieu ID
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                //3.2. lay truong name
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //3.3. lay truong so dien thoai
                //3.3.1. tao con tro cot
                Cursor cursorNumber = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,//duong dan
                        new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},//so dien thoai
                        //dieu kien truy van
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                                ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                                ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
                        //gia tri truyen
                        new String[]{id}, null
                );
                //3.3.2. doc so dien thoai
                String contactNumber = null;
                if (cursorNumber.moveToFirst())//di chuyen ve cot dau tien
                {
                    contactNumber = cursorNumber.getString(
                            cursorNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    );
                }
                //dong con tro cot
                cursorNumber.close();
                //ket qua doc duoc
                String id_name_sdt = id + " - " + name + " - " + contactNumber;
                //dua vao list
                list.add(id_name_sdt);
                //di chuyen con tro hang den ban ban ghi tiep theo
                cursor.moveToNext();
            }
        }
        //dong con tro
        cursor.close();
        // Tao adapter de xu ly
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Demo3n3Main2Activity.this,
                android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }
    public boolean phanQuyen()
    {
        if(Build.VERSION.SDK_INT>=23)
        {
            if(checkSelfPermission(Manifest.permission.READ_PHONE_STATE)==
                    PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_PHONE_NUMBERS)==
                    PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_CONTACTS)==
                    PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.WRITE_CONTACTS)==
                    PackageManager.PERMISSION_GRANTED)
            {
                return true;//nếu quyền đã khai báo trong android manifest -> return true
            }
            else
            {
                //nếu quyền chưa chưa khai báo trong android manifest -> xin quyền
                ActivityCompat.requestPermissions(Demo3n3Main2Activity.this,
                        new String[]{Manifest.permission.READ_PHONE_NUMBERS,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.WRITE_CONTACTS},1);
                return false;
            }
        }
        else
        {
            return true;
        }
    }
}
