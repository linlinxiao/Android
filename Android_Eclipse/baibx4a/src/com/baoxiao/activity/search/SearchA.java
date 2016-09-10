package com.baoxiao.activity.search;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * '首页和收藏页面的搜索'布局界面
 *
 */
public class SearchA extends Activity implements OnClickListener{
	private ImageView searchBack,search;
	private EditText searchText;
	private WebView searchWebView;
	private String userid,souSuo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.search);
		
		initView();
		initWebView();
		souSuo = getIntent().getStringExtra("sousuo");
	}
	
	private void initWebView() {
		searchWebView = (WebView) findViewById(R.id.searchWebView);

		WebSettings webSettings = searchWebView.getSettings();
		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);

		
	}
	
	private void initView() {
		searchBack = (ImageView) findViewById(R.id.searchBack);
		search = (ImageView) findViewById(R.id.search);
		searchText = (EditText) findViewById(R.id.searchText);
		
		searchBack.setOnClickListener(this);
		search.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.searchBack:
			finish();
			break;
		case R.id.search:
			userid = searchText.getText().toString();
			searchWebView.loadUrl(getUrl());
			searchWebView.setWebViewClient(new WebViewClient() {
				public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
					view.loadUrl(url);
					return true;
				}
			});
			break;

		default:
			break;
		}
	}
	
	// 获取url的方法
	public String getUrl() {
		String url = "";
		if(souSuo.equals("collection")){
			url = Define.Server + "mohulist.action?title="+userid;
		}else{
			url = Define.Server + "studymohufind.action?title="+userid;
		}
		return url;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && searchWebView.canGoBack()){
			searchWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
}
