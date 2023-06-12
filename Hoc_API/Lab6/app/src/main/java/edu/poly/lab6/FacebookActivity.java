package edu.poly.lab6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class FacebookActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // c2:
        FacebookSdk.setApplicationId("1065047890875482");
        FacebookSdk.setClientToken("f7c51c13f825b91762bc34e17af4fb48");
        // đăng ký sdk
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_facebook);
        loginButton = findViewById(R.id.btn_logtin_fb);


        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(FacebookActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(@NonNull FacebookException e) {
                Toast.makeText(FacebookActivity.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}