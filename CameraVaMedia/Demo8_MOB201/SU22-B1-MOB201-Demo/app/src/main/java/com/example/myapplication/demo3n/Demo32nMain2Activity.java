package com.example.myapplication.demo3n;

import androidx.annotation.RequiresApi;
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

public class Demo32nMain2Activity extends AppCompatActivity {
    //content://com.android.contacts/contacts
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo32n_main2);
        listView = findViewById(R.id.demo32nListview);
        phanQuyen();
        getContacts();
    }

    public void getContacts()
    {

            //1. Lay duong dan
            Uri uri = Uri.parse("content://com.android.contacts/contacts");
            //2. Tao danh sach chua contacts
            List<String> list = new ArrayList<>();
            //3. Tao con tro dong
            Cursor cursor = getContentResolver().query(uri,null,
                    null,null,null);
            if(cursor.getCount()>0)//neu ton tai ban ghi
            {
                //4. doc du lieu
                cursor.moveToFirst();//di chuyen ve ban ghi dau tien
                while (!cursor.isAfterLast())//neu khong phai ban ghi cuoi cung thi tiep tuc doc
                {
                    //4.1. Lay ve id
                    String thutu = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    //4.2 lay ve name
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    //4.3. doc so dien thoai: can tao them con tro cot vi co nhieu so dien thoai
                    Cursor cursorNumber = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,//duong dan
                            new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},//so dien thoai
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID//dieu kien
                                    +" =? AND "+
                            ContactsContract.CommonDataKinds.Phone.TYPE+" = "+
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
                            ,new String[]{thutu},null
                    );
                    String contactNumber = null;
                    if(cursorNumber.moveToFirst())
                    {
                        contactNumber = cursorNumber.getString(cursorNumber.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                        ));
                    }
                    cursorNumber.close();//dong con tro cot
                    String id_ten = thutu+" - "+name+" - "+contactNumber;
                    //dua vao list
                    list.add(id_ten);
                    cursor.moveToNext();//di chuyen den ban ghi tiep theo de doc
                }
                cursor.close();//dong con tro hang
                //Dua vao adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Demo32nMain2Activity.this,
                        android.R.layout.simple_list_item_1,list);
                listView.setAdapter(adapter);
            }


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
                    PackageManager.PERMISSION_GRANTED
            )

            {
                return true;//nếu quyền đã khai báo trong android manifest -> return true
            }
            else
            {
                //nếu quyền chưa chưa khai báo trong android manifest -> xin quyền
                ActivityCompat.requestPermissions(Demo32nMain2Activity.this,
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
