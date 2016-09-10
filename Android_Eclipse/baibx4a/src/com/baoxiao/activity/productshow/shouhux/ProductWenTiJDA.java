package com.baoxiao.activity.productshow.shouhux;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ProductWenTiJDA extends Activity{

	private WebView webView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.ps_shouhux_wentijieda);
		
		initView();
	}



	private void initView() {
		webView = (WebView) findViewById(R.id.wv);
		
		WebSettings webSettings = webView.getSettings();
		// 设置加载进来的页面自适应屏幕
				webSettings.setUseWideViewPort(true);
				webSettings.setLoadWithOverviewMode(true);
				webSettings.setJavaScriptEnabled(true);
				
				webView.loadUrl(Define.Server+"web/video/wentijieda.jsp");
				webView.setWebViewClient(new WebViewClient(){
					public boolean shouldOverrideUrlLoading(WebView view, String url)
		            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
		                    view.loadUrl(url);
		                    return true;
		            }
				});
	}

	/**
	 * 关闭WebView之后，声音或者视频不停止的解决办法
	 */
	@Override
	protected void onPause() {
		webView.reload();
		super.onPause();
	}
	
	/**
	 * 设置网页回退事件，返回前一个网页
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK&&webView.canGoBack()){
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	
}
