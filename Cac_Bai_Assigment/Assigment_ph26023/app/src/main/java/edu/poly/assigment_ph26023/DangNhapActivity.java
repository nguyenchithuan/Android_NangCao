package edu.poly.assigment_ph26023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import edu.poly.assigment_ph26023.custom_toast.CustomToast;
import edu.poly.assigment_ph26023.dao.ThuThuDao;


public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    // Thuộc tính chức năng
    private Button btnDangNhap;
    private EditText edUserName, edPassWord;
    private TextView tvLoiUserName, tvLoiPassWord;
    private CheckBox chkGhiNho;
    private ThuThuDao dao;
    private SharedPreferences prefer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        btnDangNhap = findViewById(R.id.btn_dang_nhap);
        edUserName = findViewById(R.id.edUserName);
        edPassWord = findViewById(R.id.edPassWord);
        chkGhiNho = findViewById(R.id.chkGhiNho);
        tvLoiUserName = findViewById(R.id.tv_loi_username);
        tvLoiPassWord = findViewById(R.id.tv_loi_password);

        tvLoiUserName.setText("");
        tvLoiPassWord.setText("");

        dao = new ThuThuDao(this);
        prefer = getSharedPreferences("USER_FILE", MODE_PRIVATE);


        // Code chức năng
        btnDangNhap.setOnClickListener(this);


        // Đọc dữ liệu ở trong prefer để hiển thị lên màn hình login
        readPrefer();
    }
    //-----------------------------Code chức năng-------------------------------

    @Override
    public void onClick(View v) { // hàm click của btnDangNhap
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {
        String userName = edUserName.getText().toString();
        String passUser = edPassWord.getText().toString();
        Boolean status = chkGhiNho.isChecked();

        tvLoiUserName.setText("");
        tvLoiPassWord.setText("");

        if(userName.isEmpty()) {
            tvLoiUserName.setText("Mời nhập tài khoản");
        } else if(passUser.isEmpty()) {
            tvLoiPassWord.setText("Mời nhập mật khẩu");
        } else if(userName.length() < 5) {
            tvLoiUserName.setText("Tối thiểu 5 ký tự");
        } else if(dao.checkUserName(userName) < 0) {
            tvLoiUserName.setText("Tài khoản không tồn tại");
        } else if(dao.checkPassword(userName, passUser) < 0) {
            tvLoiPassWord.setText("Mật khẩu không đúng");
        } else if(dao.checkLogin(userName, passUser) > 0 || userName.equals("admin") && passUser.equals("admin")) {
            writePrefer(userName, passUser, status);
            CustomToast.showMessage(this, "Đăng nhập thành công!");
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            // chuyển userName sang bên mainActivity để thao tác
            intent.putExtra("user", userName);
            startActivity(intent);
            finish();

        }
    }

    public void writePrefer(String userName, String passWord, Boolean status) {
        SharedPreferences.Editor editor = prefer.edit();

        editor.putString("USERNAME", userName);
        editor.putString("PASSWORD", passWord);
        editor.putBoolean("STATUS", status);

        editor.commit();
    }

    public void readPrefer() {
        String userName = prefer.getString("USERNAME", null);
        String passWord = prefer.getString("PASSWORD", null);
        Boolean check = prefer.getBoolean("STATUS", false);

        chkGhiNho.setChecked(check);

        if(userName == null || passWord == null) {
            return;
        }

        if(chkGhiNho.isChecked()) {
            edUserName.setText(userName);
            edPassWord.setText(passWord);
        } else {
            edUserName.setText(userName);
        }
    }


}