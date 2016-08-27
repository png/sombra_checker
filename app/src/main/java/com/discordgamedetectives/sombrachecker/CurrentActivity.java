package com.discordgamedetectives.sombrachecker;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.SslErrorHandler;
import android.webkit.WebViewClient;

public class CurrentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WebView mWebView = (WebView) this.findViewById(R.id.webViewCurrent);
        mWebView.setWebViewClient(
                new SSLTolerentWebViewClient()
        );
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setDefaultTextEncodingName("UTF-8");

        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.loadDataWithBaseURL (null,null,"text/html","UTF-8" ,null);
        mWebView.loadUrl("http://jasona99.github.io/sombra_checker/");

    }
    private class SSLTolerentWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }

    }



}
