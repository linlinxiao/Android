package com.linlin.browserdemo;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private HiXWalkWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (HiXWalkWebView) findViewById(R.id.webView);
        webView.load("http://chuantu.biz/", null);
    }
}
