package com.baoxiao.activity.study;

import com.baoxiao.R;
import com.baoxiao.service.study.CollectionService;
import com.baoxiao.util.Define;
import com.baoxiao.util.GetSharedPreferencesValue;
import com.baoxiao.util.MessageHelper;
import com.baoxiao.util.ShareService;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

/**
 * '文档详情'布局界面
 *
 */
public class DocumentInfoA extends Activity implements OnClickListener{

	private ImageView studyBack,spaceImage,shareImage;
	private WebView studyVideo;
	private String url,load;
	private String urlPath;
	private CollectionService cs;
	private MessageHelper m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	 	requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.study_documentinfo);
	    
	    url = getIntent().getStringExtra("url");
	    urlPath = getIntent().getStringExtra("urlPath");
	    initWebView();
	    studyBack = (ImageView) findViewById(R.id.studyBack);
	    spaceImage = (ImageView) findViewById(R.id.spaceImage);
	    shareImage = (ImageView) findViewById(R.id.shareImage);
	    
	    studyBack.setOnClickListener(this);
	    spaceImage.setOnClickListener(this);
	    shareImage.setOnClickListener(this);
	    
	    cs = new CollectionService();
	}
	private void initWebView() {
		studyVideo = (WebView) findViewById(R.id.studyVideo);
		
		WebSettings webSettings = studyVideo.getSettings();
		// 设置加载进来的页面自适应屏幕
				webSettings.setUseWideViewPort(true);
				webSettings.setLoadWithOverviewMode(true);
				webSettings.setJavaScriptEnabled(true);
				
				studyVideo.loadUrl(url);
				studyVideo.setWebViewClient(new WebViewClient(){
					public boolean shouldOverrideUrlLoading(WebView view, String url)
		            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
		                    view.loadUrl(url);
		                    return true;
		            }
				});
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.studyBack:
			finish();
			break;
		case R.id.spaceImage:
			String id = cs.idSplit(url);
			String type = urlPath;
			String userid = GetSharedPreferencesValue.getUserId(this);
			load = Define.Server+"addshoucan.action?id="+id+"&type="+type+"&userid="+userid;
			new Thread(runnable).start();
			break;
		case R.id.shareImage:
			ShareService.startShare(url, "百宝箱学习园地",DocumentInfoA.this);
			break;

		default:
			break;
		}
	}
	
	Runnable runnable = new Runnable()
	{

		@Override
		public void run()
		{
			m = cs.getDataFromServer(load);
			Message msg = new Message();
			handler.sendMessage(msg);
		}
	};
	private Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			cs.getJsonList(load,DocumentInfoA.this,m);
		};
	};
}
