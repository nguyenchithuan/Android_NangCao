package com.example.myapplication.demo6;

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

public class Demo62Main2Activity extends AppCompatActivity {
    LoginButton loginButton;//button login
    CallbackManager callbackManager;//service cuar facebook
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //lien ket voi ID
        FacebookSdk.setClientToken("1548871032015163");
        //khoi tao sdk
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_demo62_main2);
        loginButton = findViewById(R.id.btn62Login);
        //khoi tao dich vu
        callbackManager = CallbackManager.Factory.create();
        //dang ky dich vu login
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
                Toast.makeText(getApplicationContext(),
                        "Login that bai: "+e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    //tra ve ket qua cho nguoi dung

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
