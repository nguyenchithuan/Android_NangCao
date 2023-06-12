package com.example.myapplication.demo3n;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.demo2n.Demo2n1Main2Activity;

public class Demo31nMain2Activity extends AppCompatActivity {
    TextView tvKQ;
    Button btn;
    private static final int CHO_PHEP_DOC = 1;
    private Uri uriContact;
    private String contactID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo31n_main2);
        phanQuyen();
        tvKQ = findViewById(R.id.demo31nResult);
        btn = findViewById(R.id.demo31nReadContact);
        btn.setOnClickListener(v->{
            startActivityForResult(new Intent(Intent.ACTION_PICK,
                    ContactsContract.Contacts.CONTENT_URI),CHO_PHEP_DOC);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CHO_PHEP_DOC && resultCode ==RESULT_OK)
        {
            uriContact = data.getData();
            String contactName = getContactName();
            String contactNumber = getContactNumber();
            tvKQ.setText(contactName +" - "+contactNumber);
        }
    }

    public String getContactName()
    {
        String contactName = null;
        //doc 1 ban ghi (bang cach dung con tro)
        Cursor cursor = getContentResolver().query(uriContact,
                null,null,null,null);
        if(cursor.moveToFirst())//di chuyen ve ban ghi dau tien
        {
            contactName = cursor.getString(cursor.getColumnIndex(
                    ContactsContract.Contacts.DISPLAY_NAME));
        }
        cursor.close();
        return contactName;
    }
    public String getContactNumber()
    {
        String contactNumber = null;
        //su dung con tro
        Cursor cursorID = getContentResolver().query(uriContact,
                new String[]{ContactsContract.Contacts._ID},null,null,null);
        //doc 1 ban ghi
        if(cursorID.moveToFirst())
        {
            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }
        cursorID.close();
        //lay cac thong tin ve ban ghi (bang cach dung con tro)
        Cursor cursorPhone = getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND "+
                        ContactsContract.CommonDataKinds.Phone.TYPE+" = "+
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
                        new String[]{contactID},null);
        //di chuyen ve cot dau tien
        if(cursorPhone.moveToFirst())
        {
            //lay so dien thoai
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
            ));
        }
        cursorPhone.close();
        return contactNumber;
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
                ActivityCompat.requestPermissions(Demo31nMain2Activity.this,
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
