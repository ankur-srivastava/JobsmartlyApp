package com.edocent.jobsmartlyapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.edocent.jobsmartlyapp.utility.AppConstants;

public class StartWebAppActivity extends Activity {

    WebView startwebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_start_web_app);

        String url = getIntent().getExtras().get(AppConstants.START_ACTIVITY_KEY).toString();

        final ProgressDialog pd = ProgressDialog.show(this, "", "Please wait", true);

        startwebview = (WebView) findViewById(R.id.startwebview);
        startwebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                pd.show();
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
            }
        });
        startwebview.loadUrl(url);
        //myWebView.setLongClickable(false);
        startwebview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        WebSettings webSettings = startwebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && startwebview.canGoBack()) {
            startwebview.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
