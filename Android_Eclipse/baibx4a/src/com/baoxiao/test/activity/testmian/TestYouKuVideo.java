package com.baoxiao.test.activity.testmian;

import com.baoxiao.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class TestYouKuVideo extends Activity
{

	private WebView webView;
	private String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.test_youku_video_show);
		
		webView = (WebView) findViewById(R.id.webView1);
	
		url = "http://192.168.1.203:8080/baibx/videopage.action?url=http://player.youku.com/embed/XOTQ4NDU0MzQ0";
		
		WebSettings webSettings = webView.getSettings();
		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setBuiltInZoomControls(true);
		webView.loadUrl(url);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_you_ku_video_show, menu);
		return true;
	}

}


