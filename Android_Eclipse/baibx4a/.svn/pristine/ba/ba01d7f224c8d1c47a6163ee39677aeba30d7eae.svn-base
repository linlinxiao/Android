package com.baoxiao.activity.mine;

import com.baoxiao.R;
import com.baoxiao.util.Define;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
 * '互动入口'布局界面（包括推荐好文和反馈建议）
 *
 */
public class HuDongA extends Activity implements OnClickListener{

	private WebView mineHuDong;
	private ImageView mineBack;
	private String userid;
	private SharedPreferences sp;
	private Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	 	//设置无标题
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	 	setContentView(R.layout.mine_hudong);
	    
	    initView();
	    initWebView();
	}


	private void initView() {
		mineBack = (ImageView) findViewById(R.id.mineBack);
		mineBack.setOnClickListener(this);
	}
	
	private void initWebView() {
		mineHuDong = (WebView) findViewById(R.id.mineHuDong);
		userid = getSharedPreferences("loading", 0).getString(
				"userid", null);

		WebSettings webSettings = mineHuDong.getSettings();
		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setSavePassword(false);
	    webSettings.setSaveFormData(false);
	    webSettings.setJavaScriptEnabled(true);
	    webSettings.setAllowFileAccess(true);
	    webSettings.setSupportZoom(false);


	    mineHuDong.setWebViewClient(new DemoWebViewClient());
	    mineHuDong.setWebChromeClient(new MyWebChromeClient());
	    mineHuDong.addJavascriptInterface(new JavaScriptInterface(),"demo");
		mineHuDong.loadUrl(getUrl());
	}	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mineBack:
			finish();
			break;
		default:
			break;
		}
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
				new AlertDialog.Builder(HuDongA.this).
				      setMessage(message).
				      setPositiveButton("OK", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					}).setCancelable(true).create().show();
				result.confirm();
				return true;
			}
	 }
	
	final class JavaScriptInterface{
		JavaScriptInterface(){
		}
		 @JavascriptInterface
		public void clickOnAndroid(){
			handler.post(new Runnable() {
				@Override
				public void run() {
					mineHuDong.loadUrl("javascript:wave()");
				}
			}) ;
		}
		
	}
	
	// 获取url的方法
		public String getUrl() {
			String url = "";
			url = Define.Server + "feedbaockpage.action?userid="+userid;
			return url;
		}
}
