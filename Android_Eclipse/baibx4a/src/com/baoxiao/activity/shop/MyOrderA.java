package com.baoxiao.activity.shop;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

/**
 * '我的订单'布局界面
 *
 */
public class MyOrderA extends Activity implements OnClickListener
{

	private ImageView studyBack;
	private WebView myOrder;
	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shop_myorder);

		// 获取登录用户id
		userId = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("userid", "");
		myOrder = (WebView) findViewById(R.id.myOrder);

			WebSettings webSettings = myOrder.getSettings();
			// 设置加载进来的页面自适应屏幕
			webSettings.setUseWideViewPort(true);
			webSettings.setLoadWithOverviewMode(true);
		    webSettings.setSavePassword(false);      
		    webSettings.setSaveFormData(false);      
		    webSettings.setJavaScriptEnabled(true);  
		    webSettings.setAllowFileAccess(true); 
		    webSettings.setSupportZoom(false);

		    myOrder.setWebViewClient(new DemoWebViewClient());
		    myOrder.loadUrl(getUrl());
		
			
			studyBack = (ImageView) findViewById(R.id.studyBack);

		studyBack.setOnClickListener(this);

	}

	
	
	class DemoWebViewClient extends WebViewClient { 
    	@Override 
    	public boolean shouldOverrideUrlLoading(WebView view, String url) { 
//	    	view.loadUrl(url);
    		Intent intent = new Intent(MyOrderA.this,MyOrderInfoA.class);
    		intent.putExtra("url", url);
    	    startActivity(intent);
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
		url = Define.Server + "dingdanlist.action?userid=" + userId;
		return url;
	}
	
	@Override
	public void onBackPressed()
	{
		finish();
		super.onBackPressed();
	}

}
