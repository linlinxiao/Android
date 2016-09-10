package com.example.webview;

import java.net.URI;
import java.net.URISyntaxException;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends ActionBarActivity {

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 启用外部浏览器浏览网页
		// Uri uri =
		// Uri.parse("http://blog.csdn.net/getchance/article/details/46757955");
		// Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		// startActivity(intent);

		initViews();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initViews() {
		webView = (WebView) findViewById(R.id.webView1);
		webView.loadUrl("http://www.baidu.com");
		webView.setWebViewClient(new CustomWebViewClient());
		WebSettings settings = webView.getSettings();
		// 设置允许运行本地js脚本
		settings.setJavaScriptEnabled(true);
		//设置缓存策略
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.setWebChromeClient(new CustomWebChromeClient());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		//点击返回键的时候不退出程序，而是返回上一个页面
		if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();
		}
		else {
			System.exit(0);
		}
		return super.onKeyDown(keyCode, event);
	}

	class CustomWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			// 控制是否在在本页面的打开网页，返回true表示在本页面打开网页，返回false表示启用外部浏览器打开网页（默认是false）
			view.loadUrl(url);
			return true;
			// return super.shouldOverrideUrlLoading(view, url);
		}
	}

	class CustomWebChromeClient extends WebChromeClient {
		private ProgressDialog progressDialog;

		//显示加载进度
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			// TODO Auto-generated method stub
			if (newProgress != 100) {
				showProgressDialog(newProgress);
			} else {
				dismissDialog();
			}
		}

		private void dismissDialog() {
			// TODO Auto-generated method stub
			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();
				progressDialog = null;
			}

		}

		private void showProgressDialog(int newProgress) {
			// TODO Auto-generated method stub
			if (progressDialog == null) {
				progressDialog = new ProgressDialog(MainActivity.this);
				progressDialog.setTitle("正在加载...");
				progressDialog
						.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progressDialog.show();
			}

			progressDialog.setProgress(newProgress);
		}
	}
}
