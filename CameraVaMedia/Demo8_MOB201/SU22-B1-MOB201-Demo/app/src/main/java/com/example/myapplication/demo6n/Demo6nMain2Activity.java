package com.example.myapplication.demo6n;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.jetbrains.annotations.NotNull;

public class Demo6nMain2Activity extends AppCompatActivity {
    LoginButton loginButton;//click
    CallbackManager callbackManager;//service lang nghe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.setClientToken("1548871032015163");//key
        FacebookSdk.sdkInitialize(getApplicationContext());//khoi tao
        setContentView(R.layout.activity_demo6n_main2);

        loginButton = findViewById(R.id.demo6nBtnLogin);
        callbackManager = CallbackManager.Factory.create();//khoi tao service lang ngh
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(),
                        "Login thanh cong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(@NotNull FacebookException e) {
                Toast.makeText(getApplicationContext(),"Login that bai: "+e.getMessage(),
                        Toast.LENGTH_LONG).show();

            }
        });
    }
    //tra ket qua cho nguoi dung
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
