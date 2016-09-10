package com.baoxiao.activity.study;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * '学习乐园内部选项'共同的布局界面
 *
 */
public class StudyWebViewA extends Activity implements OnClickListener {

	private final static int ONPAGEFINISHED = 0;
	private ImageView studyBack;
	private WebView studyVideo;
	private TextView studyTitle;
	private String title,urlPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_webview);

		studyBack = (ImageView) findViewById(R.id.studyBack);
		studyTitle = (TextView) findViewById(R.id.studyTitle);
		
		Intent intent = getIntent();
		title = intent.getStringExtra("title");
		urlPath = intent.getStringExtra("urlPath");
		studyTitle.setText(title);
		initWebView();
		studyBack.setOnClickListener(this);
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
//				view.loadUrl(url);
				Intent intent;
				if(urlPath.equals("video")){
					intent = new Intent(StudyWebViewA.this,StudyVideoInfoA.class);
				}else{
					intent = new Intent(StudyWebViewA.this,DocumentInfoA.class);
				}
				intent.putExtra("url", url);
				intent.putExtra("urlPath", urlPath);
				startActivity(intent);
				return true;
			}
			@Override
			public void onPageFinished(WebView view, String url)
			{
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

	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case ONPAGEFINISHED:
				studyVideo.loadUrl("javascript:(function() { var audios = document.getElementsByTagName('audio'); for(var i=0;i<audios.length;i++){audios[i].play();}})()");
				studyVideo.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}})()");
				break;
			default:
				break;
			}
			return false;
		}
	});
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.studyBack:
			finish();
			break;

		}
	}

	// 获取url的方法
	public String getUrl() {
		String url = "";
		url = Define.Server + "studylist.action?PageNo=1&type="+urlPath;
		return url;
	}
	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

}
