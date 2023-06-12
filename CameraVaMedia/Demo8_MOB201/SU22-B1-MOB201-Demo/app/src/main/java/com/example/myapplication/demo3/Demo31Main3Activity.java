package com.example.myapplication.demo3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Demo31Main3Activity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo31_main3);
        listView = findViewById(R.id.demo31Listview);
        requestPermission();
        getContacts();
    }

    public void getContacts()
    {
        //b1. lay uri
        List<String> arr = new ArrayList<>();//luu du lieu doc duoc
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        //b2. Tao con tro
        CursorLoader loader =
                new CursorLoader(this,uri,null,null,null,null);
        Cursor cursor = loader.loadInBackground();//dua con tro ve dang background
        //b3- doc du lieu
        //b3.1 - di chuyen ve ban ghi dau tien
        cursor.moveToFirst();
        //b3.2. dua vao vong lap
        int i=0;
        while (cursor.isAfterLast()==false)//neu khong phai ban ghi sau ban ghi cuoi cung
        {
            i++;
            String s = "";
            //b3.2.1 - Lay ve id
            String id = ContactsContract.Contacts._ID;//lay ve id
            int indexID = cursor.getColumnIndex(id);
            s+=cursor.getString(indexID)+" - ";

            //B3.2.2 - lay ve name
            String name = ContactsContract.Contacts.DISPLAY_NAME;//lay ve name
            int indexName = cursor.getColumnIndex(name);
            s+=cursor.getString(indexName)+" - ";
            /////////
            Uri uriContact = Uri.parse("content://contacts/people/"+i);
            String sdt = getSoDienThoai(uriContact,cursor.getString(indexID));
            s+=" - "+sdt;
            ////////
            arr.add(s);
            //b3.3 - di chuyen sang ban ghi tiep theo
            cursor.moveToNext();
        }
        cursor.close();//dong con tro
        //dua vao listview
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Demo31Main3Activity.this,
                android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(adapter);

    }
    public String getSoDienThoai(Uri uriContact,String contactID)
    {
        // "content://contacts/people/1";
        String contactNum = null;
        //lay contact id
        Cursor cursorID = getContentResolver().query(uriContact,
                new String[]{ContactsContract.Contacts._ID},null,null,null);
        if(cursorID.moveToFirst())
        {
            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }
        cursorID.close();
        //su dung contact id de lay so dien thoai
        Cursor cursorNumber = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=? AND "+
                        ContactsContract.CommonDataKinds.Phone.TYPE+"="+
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
                new String[]{contactID},null);
        if(cursorNumber.moveToFirst())
        {
            contactNum = cursorNumber.getString(cursorNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
        cursorNumber.close();
        return contactNum;
    }

    public void readBookmarks()
    {
        List<String> l = new ArrayList<>();
        //1.uri
        Uri uri = Uri.parse("content://browser/bookmarks");
        //2. truong du lieu
        String[] dataType = new String[]{"id","title"};
        //3.con tro
        Cursor cursor = managedQuery(uri,dataType,null,null,null);
        //4. doc du lieu
        //4.1. di chuyen ve ban ghi dau tien
        cursor.moveToFirst();
        while (!cursor.isAfterLast())//neu khong phai la sau ban ghi cuoi cung
        {
            String str="";
            str+=cursor.getString(1)+" - "+ cursor.getString(2);//lay ve cac truong du lieu
            l.add(str);//dua vao list
            cursor.moveToNext();//4.2 chuyen ban ghi tiep theo
        }
        cursor.close();
        //dua vao listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Demo31Main3Activity.this,
                android.R.layout.simple_list_item_1,l);
        listView.setAdapter(adapter);
    }
    public boolean requestPermission()
    {
        //kiem tra version
        if(Build.VERSION.SDK_INT>=23)
        {
            //kiem tra quyen duoc gan chua, neu gan roi, return true
            if(checkSelfPermission(Manifest.permission.WRITE_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                    == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_PHONE_NUMBERS)
                    == PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
            else //neu chua duoc gan, request truc tiep tu nguoi dung
            {
                ActivityCompat.requestPermissions(Demo31Main3Activity.this,
                        new String[]{
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.WRITE_CONTACTS,
                                Manifest.permission.READ_PHONE_NUMBERS,
                                Manifest.permission.READ_PHONE_STATE
                        },1);
                return  false;
            }
        }
        else
        {
            return true;
        }
    }
}
