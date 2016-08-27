package com.discordgamedetectives.sombrachecker;


import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.discordgamedetectives.sombrachecker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentFragment extends Fragment {


    public CurrentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout fl = (FrameLayout)inflater.inflate(R.layout.fragment_current, container, false);
        WebView mWebView = (WebView) fl.findViewById(R.id.webViewCurrent);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setDefaultTextEncodingName("UTF-8");
        mWebView.getSettings().setUseWideViewPort(true); mWebView.getSettings().setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        String url = "amomentincrime.com";

        webSettings.setBuiltInZoomControls(true);

        mWebView.setWebViewClient(new Callback());  //HERE IS THE MAIN CHANGE
        mWebView.loadUrl(url);
        System.out.println("Wut");
        return inflater.inflate(R.layout.fragment_current, container, false);
    }

    private class Callback extends WebViewClient{  //HERE IS THE MAIN CHANGE.

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }

    }

}
