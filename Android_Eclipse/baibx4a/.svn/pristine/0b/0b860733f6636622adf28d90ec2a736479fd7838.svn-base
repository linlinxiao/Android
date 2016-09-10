package com.baoxiao.activity.shop;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

/**
 * '礼品商城'布局界面
 *
 */
public class ShopA extends Activity implements OnClickListener
{

	private ImageView studyBack,shouHuXingMenu;
	private WebView studyVideo;
	private String userId;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shop);

		// 获取登录用户id
		userId = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("userid", "");
			studyVideo = (WebView) findViewById(R.id.studyVideo);
			shouHuXingMenu = (ImageView) findViewById(R.id.shouHuXingMenu);

			WebSettings webSettings = studyVideo.getSettings();
			// 设置加载进来的页面自适应屏幕
			webSettings.setUseWideViewPort(true);
			webSettings.setLoadWithOverviewMode(true);
		    webSettings.setSavePassword(false);      
		    webSettings.setSaveFormData(false);      
		    webSettings.setJavaScriptEnabled(true);  
		    webSettings.setAllowFileAccess(true); 
		    webSettings.setSupportZoom(false);

			studyVideo.setWebViewClient(new DemoWebViewClient());
			studyVideo.setWebChromeClient(new MyWebChromeClient());
			studyVideo.addJavascriptInterface(new DemoJavaScriptInterface(),"demo");
			studyVideo.loadUrl(getUrl());
		
			
			studyBack = (ImageView) findViewById(R.id.studyBack);

		studyBack.setOnClickListener(this);
		shouHuXingMenu.setOnClickListener(this);

	}

	
	
	class DemoWebViewClient extends WebViewClient { 
    	@Override 
    	public boolean shouldOverrideUrlLoading(WebView view, String url) { 
	    	view.loadUrl(url); 
	    	return true; 
    	} 
    } 
	
	 final class MyWebChromeClient extends WebChromeClient{
		 public boolean onJsAlert(WebView view, String url, String message,
					JsResult result) {
				new AlertDialog.Builder(ShopA.this).
				      setMessage(message).
				      setPositiveButton("OK", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					}).setCancelable(true).create().show();
				result.confirm();
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
		case R.id.shouHuXingMenu:
			Intent intent = new Intent(this, ShopMenuA.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	// 获取url的方法
	public String getUrl()
	{
		String url = "";
		url = Define.Server + "listgift.action?PageNo=1&userid=" + userId;
		return url;
	}
	
	final class DemoJavaScriptInterface{
		DemoJavaScriptInterface(){
		}
		 @JavascriptInterface
		public void clickOnAndroid(){
			handler.post(new Runnable() {
				@Override
				public void run() {
					studyVideo.loadUrl("javascript:wave()");
				}
			}) ;
		}
		
	}
	//设置webview回退
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && studyVideo.canGoBack()){
        	studyVideo.goBack();
        	return true;
        }
		return super.onKeyDown(keyCode, event);
	}

	
	

}
