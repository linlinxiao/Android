package com.baoxiao.activity.weihu;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

/**
 * '关于平安百宝箱'布局界面
 *
 */
public class AboutA extends Activity implements OnClickListener{

	private ImageView weiHuBack;
	private WebView about;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	    setContentView(R.layout.weihu_about);
	    
	    initWebView();
	    
	    weiHuBack = (ImageView) findViewById(R.id.weiHuBack);

	    weiHuBack.setOnClickListener(this);
	}
	private void initWebView() {
		about = (WebView) findViewById(R.id.about);
		
		WebSettings webSettings = about.getSettings();
		// 设置加载进来的页面自适应屏幕
				webSettings.setUseWideViewPort(true);
				webSettings.setLoadWithOverviewMode(true);
				webSettings.setJavaScriptEnabled(true);
				
				about.loadUrl(Define.Server+"web/bbx/bbx.html");
				about.setWebViewClient(new WebViewClient(){
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
		case R.id.weiHuBack:
			finish();
			break;

		default:
			break;
		}
	}
}
