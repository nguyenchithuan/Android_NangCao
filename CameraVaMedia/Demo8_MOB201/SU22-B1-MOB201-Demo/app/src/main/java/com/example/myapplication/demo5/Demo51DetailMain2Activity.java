package com.example.myapplication.demo5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myapplication.R;

public class Demo51DetailMain2Activity extends AppCompatActivity {
    WebView webView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo51_detail_main2);
        webView = findViewById(R.id.demo51Webview);
        intent = getIntent();
        String link = intent.getStringExtra("linkSent");
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}
