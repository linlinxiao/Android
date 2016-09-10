package com.baoxiao.test.activity.webview;

import com.baoxiao.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ViewFlipper;

@SuppressLint("SetJavaScriptEnabled")
public class URLWebView extends Activity
{

	private GestureDetector detector;
	private ViewFlipper flipper;
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
		setContentView(R.layout.ps_shouhux_guanniandr);

		flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper01);
		webView = (WebView) findViewById(R.id.webview1);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		url = bundle.getString("url");

		webView.setLongClickable(true);

		WebSettings webSettings = webView.getSettings();

		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);
		
		webView.loadUrl(url);
		
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                    view.loadUrl(url);
                    return true;
            }
		});
	}

}