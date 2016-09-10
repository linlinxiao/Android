package com.baoxiao.activity.productshow.shijitianshi;

import com.baoxiao.R;
import com.baoxiao.WebShowA;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class ShiJiTSA extends Activity implements OnClickListener{
	private TextView baoXianLiNian,touBaoGuiZe,chanPinTeSe,
	                 sheJiJiHuaShu;
	private ImageView shiJiTianShiJianJieBack;
	private WebView shiJiTianShiVideo;
	
	@Override
	protected void onResume() {
		shiJiTianShiVideo.onResume();
		super.onResume();
	}
	@Override
	protected void onPause() {
		shiJiTianShiVideo.onPause();
		super.onPause();
	}	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.ps_shijitianshi);
		
		baoXianLiNian = (TextView) findViewById(R.id.baoXianLiNian);
		touBaoGuiZe = (TextView) findViewById(R.id.touBaoGuiZe);
		chanPinTeSe = (TextView) findViewById(R.id.chanPinTeSe);
		sheJiJiHuaShu = (TextView) findViewById(R.id.sheJiJiHuaShu);
		shiJiTianShiJianJieBack = (ImageView) findViewById(R.id.shiJiTianShiJianJieBack);
		
	
		
		baoXianLiNian.setOnClickListener(this);
		touBaoGuiZe.setOnClickListener(this);
		chanPinTeSe.setOnClickListener(this);
		sheJiJiHuaShu.setOnClickListener(this);
		shiJiTianShiJianJieBack.setOnClickListener(this);
		
		initWebView();
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView() {
		shiJiTianShiVideo = (WebView) findViewById(R.id.shiJiTianShiVideo);

		WebSettings webSettings = shiJiTianShiVideo.getSettings();
		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);

		shiJiTianShiVideo.loadUrl(getUrl());
		shiJiTianShiVideo.setWebChromeClient(new WebChromeClient());
		shiJiTianShiVideo.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
				view.loadUrl(url);
				return true;
			}
		});
	}
	
	// 获取url的方法
	public String getUrl() {
		String url = "";
		url = "http://v.qq.com/page/m/q/e/m016783vxqe.html";
		return url;
	}


	@Override
	public void onClick(View v) {
		int title_image_id = R.drawable.product_sjts;
		switch (v.getId()) {
		case R.id.baoXianLiNian:
//			Intent intent = new Intent(ShiJiTSA.this,ImgAnimationA.class);
//			Bundle b = new Bundle();
//			b.putString("url", "product/shijitianshi/jianjie/baoxianlinian");
//			b.putInt("maxPageCount", 8);
//			b.putString("format", "gif");
//			b.putString("screenFlag","jianjie");
//			intent.putExtras(b);
//			startActivity(intent);

    		String url = "http://www.rabbitpre.com/m/NeaUv6AI4#";
	    	String shareName = "保险理念";
	    		startActivity(new Intent(ShiJiTSA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("title_image_id", title_image_id)
	    				.putExtra("shareName", shareName));
			break;
		case R.id.touBaoGuiZe:
//			Intent intent2 = new Intent(ShiJiTSA.this,ImgAnimationA.class);
//			Bundle b2 = new Bundle();
//			b2.putString("url", "product/shijitianshi/jianjie/toubaoguize");
//			b2.putInt("maxPageCount", 3);
//			b2.putString("format", "gif");
//			b2.putString("screenFlag","jianjie");
//			intent2.putExtras(b2);
//			startActivity(intent2);

    		url = "http://www.rabbitpre.com/m/ZBI37QAAs#";
	    	shareName = "投保规则";
	    		startActivity(new Intent(ShiJiTSA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("title_image_id", title_image_id)
	    				.putExtra("shareName", shareName));
			break;
		case R.id.chanPinTeSe:
//			Intent intent3 = new Intent(ShiJiTSA.this,ImgAnimationA.class);
//			Bundle b3 = new Bundle();
//			b3.putString("url", "product/shijitianshi/jianjie/producttese");
//			b3.putInt("maxPageCount", 4);
//			b3.putString("format", "gif");
//			b3.putString("screenFlag","jianjie");
//			intent3.putExtras(b3);
//			startActivity(intent3);

    		url = "http://www.rabbitpre.com/m/vuYZBbY#";
	    	shareName = "产品特色";
	    		startActivity(new Intent(ShiJiTSA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("title_image_id", title_image_id)
	    				.putExtra("shareName", shareName));
			break;
		case R.id.sheJiJiHuaShu:
			Intent intent4 = new Intent(ShiJiTSA.this,ShiJiTSPlanA2.class);
			startActivity(intent4);
			break;
		case R.id.shiJiTianShiJianJieBack:
			finish();
			break;
		}
	}

}
