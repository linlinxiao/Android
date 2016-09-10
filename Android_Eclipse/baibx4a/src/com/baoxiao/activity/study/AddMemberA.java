package com.baoxiao.activity.study;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

/**
 * '增员技巧'布局界面
 *
 */
public class AddMemberA extends Activity implements OnClickListener {

	private ImageView studyBack;
	private WebView studyVideo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_webview);

		initWebView();
		
		sendIntentData();

		studyBack = (ImageView) findViewById(R.id.studyBack);

		studyBack.setOnClickListener(this);
	}

	private void sendIntentData() {
		Intent intent = new Intent(this,StudyWebViewA.class);
		intent.putExtra("title", "增员技巧");
		intent.putExtra("urlPath", "Increase");
		startActivity(intent);
		finish();
	}

	private void initWebView() {
		studyVideo = (WebView) findViewById(R.id.studyVideo);

		WebSettings webSettings = studyVideo.getSettings();
		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);

		studyVideo.loadUrl(getUrl());
		studyVideo.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
				view.loadUrl(getUrl());
				return true;
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.studyBack:
			Intent intent8 = new Intent(this, StudyA.class);
			startActivity(intent8);
			finish();
			break;

		default:
			break;
		}
	}

	// 获取url的方法
	public String getUrl() {
		String url = "";
		url = Define.Server + "listgift.action?PageNo=1";
		return url;
	}

}
