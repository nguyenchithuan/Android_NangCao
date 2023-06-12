package com.example.myapplication.demo5n;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myapplication.R;

public class Demo5n1DetailMain2Activity extends AppCompatActivity {
    WebView webView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo5n1_detail_main2);
        webView = findViewById(R.id.demo5n1Webview);
        intent = getIntent();
        String link = intent.getStringExtra("link");
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());

    }
}
