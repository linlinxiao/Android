package com.baoxiao.activity.shop;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

/**
 * '礼品商城商城增送元宝'布局界面
 *
 */
public class FreeGoldA extends Activity implements OnClickListener
{

	private ImageView studyBack;
	private WebView freegold;
	private String userId;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shop_freegold);

		// 获取登录用户id
		userId = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("userid", "");
		freegold = (WebView) findViewById(R.id.freegold);

			WebSettings webSettings = freegold.getSettings();
			// 设置加载进来的页面自适应屏幕
			webSettings.setUseWideViewPort(true);
			webSettings.setLoadWithOverviewMode(true);
		    webSettings.setSavePassword(false);      
		    webSettings.setSaveFormData(false);      
		    webSettings.setJavaScriptEnabled(true);  
		    webSettings.setAllowFileAccess(true); 
		    webSettings.setSupportZoom(false);

		    freegold.setWebViewClient(new DemoWebViewClient());
		    freegold.loadUrl(getUrl());
		
			
			studyBack = (ImageView) findViewById(R.id.studyBack);

		studyBack.setOnClickListener(this);

	}

	
	
	class DemoWebViewClient extends WebViewClient { 
    	@Override 
    	public boolean shouldOverrideUrlLoading(WebView view, String url) { 
	    	view.loadUrl(url); 
	    	return true; 
    	} 
    } 
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.studyBack:
			finish();
			break;
		default:
			break;
		}
	}

	// 获取url的方法
	public String getUrl()
	{
		String url = "";
		url = Define.Server + "songyuanb.action?userid=" + userId;
		return url;
	}
	
	//设置webview的回退
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && freegold.canGoBack()){
			freegold.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
