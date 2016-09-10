package com.baoxiao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebShowA2 extends Activity{
	protected static final int ONPAGEFINISHED = 0;
	private WebView webView;
	private String url;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_show2);
		webView = (WebView)findViewById(R.id.newsConsult);
		WebSettings webSettings = webView.getSettings();
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);
		
		url = getIntent().getStringExtra("url");
		webView.loadUrl(url);
		webView.setWebChromeClient(new WebChromeClient());
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				view.loadUrl(url);
				return true;
            }
			@Override
			public void onPageFinished(WebView view, String url){
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (!isFinishing()) {
							handler.sendEmptyMessage(ONPAGEFINISHED);
						}
					}
				}).start();
				super.onPageFinished(view, url);
			}
		});
	}
	@Override
	protected void onResume() {
        try {
			WebView.class.getMethod("onResume").invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onResume();
	}
	@Override
	protected void onPause() {
        try {
			WebView.class.getMethod("onPause").invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onPause();
	}
	private Handler handler = new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case ONPAGEFINISHED:
				playWebView(webView);
				break;

			default:
				break;
			}
			return false;
		}
	});

	public void news_back(View v){
		pauseWebView(webView);
		finish();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			pauseWebView(webView);
			finish();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	public void playWebView(WebView webView){
		webView.loadUrl("javascript:(function() { var audios = document.getElementsByTagName('audio'); for(var i=0;i<audios.length;i++){audios[i].play();}})()");
		webView.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}})()");
	}
	public void pauseWebView(WebView webView){
		webView.loadUrl("javascript:(function() { var audios = document.getElementsByTagName('audio'); for(var i=0;i<audios.length;i++){audios[i].pause();}})()");
		webView.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].pause();}})()");
	}
}
