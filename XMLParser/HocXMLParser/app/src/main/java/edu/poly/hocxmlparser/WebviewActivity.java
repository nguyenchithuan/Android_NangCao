package edu.poly.hocxmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {
    private WebView webView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = findViewById(R.id.webView);

        intent = getIntent();
        String link = intent.getStringExtra(MainActivity.DUONG_LINK);
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}