package edu.poly.assignmentnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Key;

import edu.poly.assignmentnangcao.service.KiemTraDangNhapService;

public class LoginActivity extends AppCompatActivity {
    private EditText edUser, edPass;
    private Button btnLogin;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
        btnLogin = findViewById(R.id.btnLogin);

        intentFilter = new IntentFilter();
        intentFilter.addAction(KeyWord.CHECK_LOGIN);
        intentFilter.addAction("...");

        // bình thường thao tác luôn với activity nhưng nay sẽ thao tác dữ liệu thông qua service
        // truyền thông tin xuống dao gọi thông qua service
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUser.getText().toString().trim();
                String pass = edPass.getText().toString().trim();

                Intent intent = new Intent(LoginActivity.this, KiemTraDangNhapService.class);
                Bundle bundle = new Bundle();
                bundle.putString(KeyWord.USER, user);
                bundle.putString(KeyWord.PASS, pass);
                intent.putExtras(bundle);

                startService(intent);
            }
        });
    }


    // activity -> service -> database
    // database -> service -> activity (broadcast receiver)
    // Tạo ra duy nhất một broascast trên 1 activity
    // khi sendRroadcast thì đối tượng này sẽ được chạy
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Lọc các action
            switch (intent.getAction()) { // xử lý khi nhận dữ liệu từ broadcast khác khau
                case KeyWord.CHECK_LOGIN:
                    Boolean check = intent.getBooleanExtra(KeyWord.CHECK, false);
                    if(check) {
                        Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(context, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        // Đăng ký các intent với broadcastRecever
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
