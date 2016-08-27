package com.discordgamedetectives.sombrachecker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class WikiFragment extends Fragment {


    public WikiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout fl = (FrameLayout)inflater.inflate(R.layout.fragment_wiki, container, false);
        WebView mWebView = (WebView) fl.findViewById(R.id.webViewWiki);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.loadDataWithBaseURL (null,null,"text/html","UTF-8" ,null);
        mWebView.loadUrl("http://wiki.gamedetectives.net/index.php?title=Sombra_ARG");
        return inflater.inflate(R.layout.fragment_wiki, container, false);
    }

}
