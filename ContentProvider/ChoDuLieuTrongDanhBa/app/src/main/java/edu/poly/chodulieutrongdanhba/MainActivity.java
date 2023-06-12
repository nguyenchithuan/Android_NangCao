package edu.poly.chodulieutrongdanhba;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnContacts;
    private TextView tvDanhBa;
    private static final int REQUEST_CODE = 1;
    private Uri uriContact;
    private String contactID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phanQuyen();

        btnContacts = findViewById(R.id.btn_contacts);
        tvDanhBa = findViewById(R.id.tv_danh_ba);

        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chọn 1 cái contacts bất kỳ
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                // Chuyển dữ liệu sang màn hình danh bạ và chọn dữ liệu
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            uriContact = data.getData(); // trả về đường dẫn danh bạ uri

            String contactName = getContactName();
            String contactNumber = getContactNumber();

            tvDanhBa.setText(contactName + " - " + contactNumber);
        }
    }

    @SuppressLint("Range")
    private String getContactName() {
        String contactName = null;

        // lấy thông tin toàn bộ dữ liệu trong hàng
        Cursor cursor = getContentResolver().query(uriContact,
                null, null, null, null);

        if(cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        }
        cursor.close();

        return contactName;
    }

    @SuppressLint("Range")
    private String getContactNumber() {
        String contactNumber = null;

        // sử dụng con trỏ
        // Con trỏ theo hàng
        // lấy dữ liệu mỗi ID thôi,  không phải lấy tất
        Cursor cursorID = getContentResolver().query(uriContact,
                new String[] {ContactsContract.Contacts._ID},
                null, null, null);


        // đọc 1 bản ghi
        if(cursorID.moveToFirst()) {
            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }
        cursorID.close();


        // lấy thông tin các bản ghi
        // Con trỏ theo cột
        // Commondatakinds lấy dữ liệu trong cột cột
        // Contacts lấy dữ liệu trong hàng
        Cursor cursorNumber = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, // đường dẫn
                new String[] {ContactsContract.CommonDataKinds.Phone.NUMBER}, // lấy số đt
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =? AND " +  // điều kiện
                        ContactsContract.CommonDataKinds.Phone.TYPE  + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, // kiểu phải là mobile
                new String[] {contactID}, null // thay vào điều kiện ?
        );


        if(cursorNumber.moveToFirst()) {
            contactNumber = cursorNumber.getString(cursorNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
        cursorNumber.close();

        return  contactNumber;
    }

    public void phanQuyen() {
        if(checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
            && checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS},
                    999);
        }
    }
}