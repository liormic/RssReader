package com.ely.rssreader.displayInBrowser;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ely.rssreader.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayInBrowserActivity extends AppCompatActivity implements DisplayInBrowserView {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_in_browser);
        ButterKnife.bind(this);
        DisplayInBrowserPresenterImpl displayInBrowserPresenter = new DisplayInBrowserPresenterImpl();
        displayInBrowserPresenter.setView(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String url = getIntent().getStringExtra(getString(R.string.ARTICLE_URL_EXTRA));
        initWebView(url);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initWebView(String url) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


