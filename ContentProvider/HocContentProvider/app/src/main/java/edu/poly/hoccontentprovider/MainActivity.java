package edu.poly.hoccontentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // ----------------
    // Content provider là để quản lý truy cập dữ liệu,
    // nó cung cấp các phương thức khác nhau để các ứng dụng có thể truy cập dữ liệu
    // từ một ứng dụng khác bằng cách sử dụng ContentResolver


    // contentResolver hoặc getContentResolver là Phương thưc để truy cập dữ liệu từ một ứng dụng khác


    // ----------------
    // Content Provider hoạt động rất giống với một cơ sở dữ liệu,
    // bạn có thể truy vấn, chỉnh sửa nội dung,
    // cũng như là thêm xóa các nội dung sử dụng các phương thức: insert(), update(), delete(), query().



    // 1. -------------
    // Intent: Vận chuyển dữ liệu
    // Activity: giao diện người dùng
    // Service: Chạy ngầm trong hệ thống(Lắng nghe)
    // BroadcastReceiver: Nhận và phát lại thông điệp do service, activity cung cấp, hoặc hệ thống android phát ra
    // Content Provider: trình quản lý nội dung (Giống như 1 csdl dùng lưu thông tin cấu hình)



    // 2. Content Provider quản lý gì ?
    // - Contact: Bạn có thể lưu số điện thoại vào smartphone là nhờ ContentProvider
    // => Content Provider quản lý Contact
    // - Bạn có thể xem cuộc gọi nhỡ => nhờ Content Provider lưu nên ta có thể xem
    // - Bạn có thể lưu password wifi => nhờ Content Provider lưu



    // 3. Khái niệm
    // Content Provider là 1 csdl SQLite dùng để lưu các thành phần hệ thống cần quản lý trong android.
    // ví dụ: Contacts, Call Logs, wifi, Configuration



    // 4. Truy xuất Content Provider
    // Các ứng dụng bên ngoài có thể truy xuất Content provider không ?
    // Trước android 6.0(api 23) => có thể truy xuất trực tiếp
    // Sau android 6.0(>= 23) => một số chức nawgns bị hạn chế do tính bảo mật
    // Ví dụ: Bookmark



    // Cách dùng những quyền khi hạn chế:
    // Lập trình => upload lên google play(nhân viên google sẽ review trc)
    // => xin quyền người dùng => nếu người dùng đồng ý thì sẽ truy xuất được


    // 5. Cấu trúc
    // B1 - Địa chỉ truy xuất
    // content://com.android.contacts/contacts     :  Đọc trong danh bạ
    // B2 - Dùng con trỏ để truy xuất dữ liệu từng hàng
    // Cursor cursor = getContentResolver.query(uri, .....);
    // B3 - Lấy 1 số thành phần được phép truy xuất
    // ID:       ContactsContract.Contacts._ID
    // Name:     ContactsContract.Contacts.DISPLAY_NAME


    // 6. Truy xuất bookmark: hiện tại không dùng được nữa


    // 7. Truy xuất media store:
    // MediaStore.MediaColumn.DisplayName


    private ListView lvList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvList = findViewById(R.id.lvList);

        getContacts();

    }

    @SuppressLint("Range")
    public void getContacts() {

        // Kiểm tra quyền: nếu chưa có thì xin quyền từ người dùng
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    999);
        } else {

            // nếu đã có quyền -> đọc dữ liệu
            Uri uri = Uri.parse("content://com.android.contacts/contacts"); // đường dẫn đến danh bạ
            ArrayList list = new ArrayList(); // list lưu danh bạ

            // dùng con trỏ để lấy dữ liệu
            // con trỏ theo hàng
            Cursor cursor = getContentResolver().query(uri,
                    null, null, null, null);


            // kiểm tra xem có dữ liệu không
            if (cursor.getCount() > 0) {
                cursor.moveToFirst(); // di chuyển về bản ghi đầu tiên

                // đọc dữ liệu
                while (!cursor.isAfterLast()) { // nếu không phải bản ghi cuối cùng thì tiếp tục đọc
                    // lấy số thứ tự trong danh bạ
                    @SuppressLint("Range") String thuTu = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    // lấy tên của bản ghi
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    // lấy số điện thoại
                    // Con trỏ theo cột của hàng
                    // lấy thông tin trong con trỏ hàng
                    Cursor cursorNumber = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =? AND " +
                                    ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
                            new String[]{thuTu}, null);

                    String contactNumber = null;
                    if (cursorNumber.moveToFirst()) {
                        contactNumber = cursorNumber.getString(cursorNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }

                    cursorNumber.close();
                    // kết quả cuối cùng
                    String thuTu_ten = thuTu + " - " + name + " - " + contactNumber;
                    // dựa vào list
                    list.add(thuTu_ten);
                    // di chuyển con trỏ đến bản ghi tiếp theo
                    cursor.moveToNext();
                }
                // đóng con trỏ
                cursor.close();

                // dựa vào adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                lvList.setAdapter(adapter);

            }
        }

    }

}