package com.baoxiao.activity.shop;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
 * '礼品商城购物车'布局界面
 *
 */
public class MyShopCartA extends Activity implements OnClickListener
{

	private ImageView studyBack;
	private WebView myShopCart;
	private String userId;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shop_myshopcart);

		// 获取登录用户id
		userId = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("userid", "");
		myShopCart = (WebView) findViewById(R.id.myShopCart);

			WebSettings webSettings = myShopCart.getSettings();
			// 设置加载进来的页面自适应屏幕
			webSettings.setUseWideViewPort(true);
			webSettings.setLoadWithOverviewMode(true);
		    webSettings.setSavePassword(false);      
		    webSettings.setSaveFormData(false);      
		    webSettings.setJavaScriptEnabled(true);  
		    webSettings.setAllowFileAccess(true); 
		    webSettings.setSupportZoom(false);

		    myShopCart.setWebViewClient(new DemoWebViewClient());
		    myShopCart.setWebChromeClient(new MyWebChromeClient());
		    myShopCart.addJavascriptInterface(new DemoJavaScriptInterface(),"demo");
		    myShopCart.loadUrl(getUrl());
		
			
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
	
	final class MyWebChromeClient extends WebChromeClient{
		 public boolean onJsAlert(WebView view, String url, String message,
					JsResult result) {
				new AlertDialog.Builder(MyShopCartA.this).
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
		default:
			break;
		}
	}

	// 获取url的方法
	public String getUrl()
	{
		String url = "";
		url = Define.Server + "gouwuche.action?userid=" + userId;
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
					myShopCart.loadUrl("javascript:wave()");
				}
			}) ;
		}
		
	}
	
	
	//设置webview的回退
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && myShopCart.canGoBack()){
			myShopCart.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
