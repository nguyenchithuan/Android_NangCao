package com.example.myapplication.demo3;

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

public class Demo311Main2Activity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo311_main2);
        listView  =findViewById(R.id.demo311Lv);
        phanQuyen();
        getContacts();
    }
    public void getContacts()
    {
        List<String> list = new ArrayList<>();//chua contact doc duoc
        //--thuc hien doc
        //b1. Duong dan
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        //B2. Tao con tro dong
        Cursor cursor = getContentResolver().query(uri,
                null,null,null,null);
        //b3. Kiem tra co du lieu khong
        if(cursor.getCount()>0)
        {
            //B4. Doc du lieu
            //B4.1. di chuyen ve ban ghi dau tien
            cursor.moveToFirst();
            //b4.1. cho vao vong lap va doc du lieu
            while (!cursor.isAfterLast())//neu khong phai ban ghi cuoi cung-> doc
            {
                //B4.1.1. Lay truong id
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                //b4.1.2. Lay truong name
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //b4.1.3. Đọc số điện thoại => cần tạo thêm 1 con trỏ
                Cursor cursorNumber = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,//link
                        new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},//truong can lay du lieu
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = ? AND "+
                                ContactsContract.CommonDataKinds.Phone.TYPE+" = "+
                                ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
                        new String[]{id},//id se truyen vao dau ?
                        null
                );
                //B1.1.3.1
                String contactNumber = null;
                if(cursorNumber.moveToFirst())//di chuyen ve cot dau tien
                {
                    contactNumber=
                            cursorNumber.getString(cursorNumber.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER
                            ));
                }
                //Dong con tro cot
                cursorNumber.close();
                //Noi chuoi va them vao list
                String id_name_phone = id+" - "+name+" - "+contactNumber;
                list.add(id_name_phone);
                cursor.moveToNext();
            }
            //dong con tro hang
            cursor.close();
        }
        //--ket thuc
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Demo311Main2Activity.this,
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
                    && checkSelfPermission(Manifest.permission.WRITE_CONTACTS)==
                    PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_CONTACTS)==
                    PackageManager.PERMISSION_GRANTED)
            {
                return true;//nếu quyền đã khai báo trong android manifest -> return true
            }
            else
            {
                //nếu quyền chưa chưa khai báo trong android manifest -> xin quyền
                ActivityCompat.requestPermissions(Demo311Main2Activity.this,
                        new String[]{Manifest.permission.READ_PHONE_NUMBERS,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.WRITE_CONTACTS,
                                Manifest.permission.READ_CONTACTS},1);
                return false;
            }
        }
        else
        {
            return true;
        }
    }
}
