package edu.poly.labxmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.id_web);

        String link = getIntent().getStringExtra("link");

        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}