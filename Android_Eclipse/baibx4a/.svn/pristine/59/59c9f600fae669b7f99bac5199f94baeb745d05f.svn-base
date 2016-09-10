package com.baoxiao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class WebShowNoshare extends Activity{
	private WebView webView;
	private String url,shareName;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_articleinfo);
		findViewById(R.id.collection).setVisibility(View.GONE);
		findViewById(R.id.share).setVisibility(View.GONE);
		
		webView = (WebView)findViewById(R.id.newsConsult);
		WebSettings webSettings = webView.getSettings();
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);
		
		url = getIntent().getStringExtra("url");
		shareName = getIntent().getStringExtra("shareName");
		((TextView)findViewById(R.id.tv_title)).setText(shareName);
		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				WebShowNoshare.this.url = url;
				view.loadUrl(url);
				return true;
            }
			@Override
			public void onPageFinished(WebView view, String url){
				super.onPageFinished(view, url);
			}
		});
	}
	public void news_back(View v){
		webView.loadUrl("about:blank");
		finish();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			webView.loadUrl("about:blank");
			finish();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
