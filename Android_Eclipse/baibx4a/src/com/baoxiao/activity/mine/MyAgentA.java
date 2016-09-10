package com.baoxiao.activity.mine;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import com.baoxiao.util.GetSharedPreferencesValue;
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
 * '我的保险代理人'布局界面
 *
 */
public class MyAgentA extends Activity implements OnClickListener{

	private ImageView agentSet;
	private WebView studyVideo;
	private ImageView agentBack;
	private String userId,insurerPerson;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	    setContentView(R.layout.mine_agent);
	    
	    userId = GetSharedPreferencesValue.getUserId(this);
	    insurerPerson = GetSharedPreferencesValue.getInsurerperson(this);
	    initWebView();
	    
	    agentBack = (ImageView) findViewById(R.id.agentBack);
	    agentBack.setOnClickListener(this);
	    
	    agentSet = (ImageView) findViewById(R.id.agentSet);

	    agentSet.setOnClickListener(this);
	    
	}
	private void initWebView() {
		studyVideo = (WebView) findViewById(R.id.studyVideo);
		
		WebSettings webSettings = studyVideo.getSettings();
		// 设置加载进来的页面自适应屏幕
				webSettings.setUseWideViewPort(true);
				webSettings.setLoadWithOverviewMode(true);
				webSettings.setJavaScriptEnabled(true);
				
				studyVideo.loadUrl(getUrl());
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
		case R.id.agentBack:
			finish();
			break;
		case R.id.agentSet:
			Intent intent2 = new Intent(this,MyAgentSetA.class);
			startActivity(intent2);
			finish();
			break;

		default:
			break;
		}
	}
	
	// 获取url的方法
		public String getUrl() {
			String url = "";
				url = Define.Server+"mp.action?userid="+insurerPerson;
			return url;
		}
		@Override
		protected void onResume() {
			studyVideo.reload();
			super.onResume();
		}

		
}
