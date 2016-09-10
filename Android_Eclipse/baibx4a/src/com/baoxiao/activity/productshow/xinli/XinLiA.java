package com.baoxiao.activity.productshow.xinli;

import com.baoxiao.R;
import com.baoxiao.WebShowA;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class XinLiA extends Activity implements OnClickListener{
	private TextView baoXianLiNianShaoEr,baoXianLiNianChengRen,
	touBaoGuiZe,chanPinTeSe,sheJiJiHuaShu;
	ImageView xinLiJianJieBack;
	private WebView xinLiVideo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.ps_xinli);
		
		baoXianLiNianShaoEr = (TextView) findViewById(R.id.baoXianLiNianShaoEr);
		baoXianLiNianChengRen = (TextView) findViewById(R.id.baoXianLiNianChengRen);
		touBaoGuiZe = (TextView) findViewById(R.id.touBaoGuiZe);
		chanPinTeSe = (TextView) findViewById(R.id.chanPinTeSe);
		sheJiJiHuaShu = (TextView) findViewById(R.id.sheJiJiHuaShu);
		xinLiJianJieBack = (ImageView) findViewById(R.id.xinLiJianJieBack);
		
		baoXianLiNianShaoEr.setOnClickListener(this);
		baoXianLiNianChengRen.setOnClickListener(this);
		touBaoGuiZe.setOnClickListener(this);
		chanPinTeSe.setOnClickListener(this);
		sheJiJiHuaShu.setOnClickListener(this);
		xinLiJianJieBack.setOnClickListener(this);
	
		initWebView();
	}
	
	private void initWebView() {
		xinLiVideo = (WebView) findViewById(R.id.xinLiVideo);

		WebSettings webSettings = xinLiVideo.getSettings();
		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);

		xinLiVideo.loadUrl(getUrl());
		xinLiVideo.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
				view.loadUrl(url);
				return true;
			}
		});
	}
	
	// 获取url的方法
	public String getUrl() {
		String url = "";
		url = "http://v.qq.com/boke/page/d/0/7/d0165h4uuc7.html";
		return url;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.baoXianLiNianShaoEr:
//			Intent intent = new Intent(this, ImgAnimationA.class);
//			Bundle b = new Bundle();
//			b.putInt("maxPageCount", 8);
//			b.putString("url", "product/xinli/jianjie/linianX");
//			b.putString("format", "gif");
//			b.putString("screenFlag","jianjie");
//			intent.putExtras(b);
//			startActivity(intent);
    		String url = "http://www.rabbitpre.com/m/EjQNV31#";
	    	String shareName = "少儿保险理念";
    		startActivity(new Intent(XinLiA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("shareName", shareName));
			break;
		case R.id.baoXianLiNianChengRen:
//			Intent intent2 = new Intent(this, ImgAnimationA.class);
//			Bundle b2 = new Bundle();
//			b2.putInt("maxPageCount", 8);
//			b2.putString("url", "product/xinli/jianjie/linianC");
//			b2.putString("format", "gif");
//			b2.putString("screenFlag","jianjie");
//			intent2.putExtras(b2);
//			startActivity(intent2);
			
    		url = "http://www.rabbitpre.com/m/V3jF3zNYo#";
	    	shareName = "成人保险理念";
    		startActivity(new Intent(XinLiA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("shareName", shareName));
			break;
		case R.id.touBaoGuiZe:
//			Intent intent3 = new Intent(this, ImgAnimationA.class);
//			Bundle b3 = new Bundle();
//			b3.putInt("maxPageCount", 3);
//			b3.putString("url", "product/xinli/jianjie/guize");
//			b3.putString("format", "gif");
//			b3.putString("screenFlag","jianjie");
//			intent3.putExtras(b3);
//			startActivity(intent3);

    		url = "http://www.rabbitpre.com/m/qMIvy7Y3W#";
	    	shareName = "投保规则";
    		startActivity(new Intent(XinLiA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("shareName", shareName));
			break;
		case R.id.chanPinTeSe:
//			Intent intent4 = new Intent(this, ImgAnimationA.class);
//			Bundle b4 = new Bundle();
//			b4.putInt("maxPageCount", 3);
//			b4.putString("url", "product/xinli/jianjie/tese");
//			b4.putString("format", "gif");
//			b4.putString("screenFlag","jianjie");
//			intent4.putExtras(b4);
//			startActivity(intent4);
    		url = "http://www.rabbitpre.com/m/aEnumy5#";
	    	shareName = "产品特色";
    		startActivity(new Intent(XinLiA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("shareName", shareName));
			break;
		case R.id.sheJiJiHuaShu:
			Intent intent5 = new Intent(XinLiA.this,XinLiPlanA.class);
			startActivity(intent5);
			finish();
			break;
		case R.id.xinLiJianJieBack:
			finish();
			break;
		}
	}

	@Override
	protected void onPause() {
		xinLiVideo.reload();
		super.onPause();
	}
}
