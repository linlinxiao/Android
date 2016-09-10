package com.baoxiao.activity.mine;

import com.baoxiao.R;
import com.baoxiao.activity.search.SearchA;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

/**
 * '我的收藏'布局界面
 *
 */
public class MyCollectionA extends Activity implements OnClickListener{

	private WebView mineHuDong;
	private ImageView mineBack,search;
	private String userid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	    setContentView(R.layout.mine_collection);
	    
	    userid = getSharedPreferences("loading", 0).getString(
	    		"userid", null);
	    initView();
	    initWebView();
//	    Toast.makeText(getApplicationContext(), "userid="+userid, Toast.LENGTH_LONG).show();
	}


	private void initView() {
		mineBack = (ImageView) findViewById(R.id.mineBack);
		mineBack.setOnClickListener(this);
		
		search = (ImageView) findViewById(R.id.search);
		search.setOnClickListener(this);
	}
	
	private void initWebView() {
		mineHuDong = (WebView) findViewById(R.id.mineHuDong);

		WebSettings webSettings = mineHuDong.getSettings();
		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);

		mineHuDong.loadUrl(getUrl());
		mineHuDong.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
				view.loadUrl(url);
				return true;
			}
		});
	}	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mineBack:
			finish();
			break;
		case R.id.search:
			Intent intent2 = new Intent(this,SearchA.class);
			intent2.putExtra("sousuo", "collection");
			startActivity(intent2);
			break;
		default:
			break;
		}
	}
	
	// 获取url的方法
	public String getUrl() {
		String url = "";
		url = Define.Server + "shoucanlist.action?PageNo=1&userid="+userid;
		return url;
	}


	// 设置webview回退
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && mineHuDong.canGoBack()){
			mineHuDong.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
		
	
		
}
