package com.baoxiao.activity.productshow.xinsheng;

import com.baoxiao.R;
import com.baoxiao.WebShowA;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class XinShengA extends Activity implements OnClickListener{
	private TextView baoXianLiNianShaoEr,baoXianLiNianChengRen,
	touBaoGuiZe,chanPinTeSe,sheJiJiHuaShu;
	private ImageView xinLiJianJieBack;
	private WebView xinShengVideo;
	
	@Override
	protected void onResume() {
		xinShengVideo.onResume();
		super.onResume();
	}
	@Override
	protected void onPause() {
		xinShengVideo.onPause();
		super.onPause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.ps_xinsheng);
		
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
	
	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView() {
		xinShengVideo = (WebView) findViewById(R.id.xinShengVideo);

		WebSettings webSettings = xinShengVideo.getSettings();
		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);

		xinShengVideo.loadUrl(getUrl());
		xinShengVideo.setWebChromeClient(new WebChromeClient());
		xinShengVideo.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
				view.loadUrl(url);
				return true;
			}
		});
	}
	
	// 获取url的方法
	public String getUrl() {
		String url = "";
//		url = "http://v.qq.com/boke/page/d/0/u/d0167k15gqu.html";
//		url = "http://m.youku.com/video/id_XMTUzMjI3NTE1Ng==.html?firsttime=0&x=";
		url = "http://v.youku.com/v_show/id_XMTUzMjI3NTE1Ng==.html";
		return url;
	}

	@Override
	public void onClick(View v) {
		int title_image_id = R.drawable.product_xinsheng;
		switch (v.getId()) {
		case R.id.baoXianLiNianShaoEr:
//			Intent intent = new Intent(this, ImgAnimationA.class);
//			Bundle b = new Bundle();
//			b.putInt("maxPageCount", 11);
//			b.putString("url", "product/xinsheng/jianjie/linianX");
//			b.putString("format", "gif");
//			b.putString("screenFlag","jianjie");
//			intent.putExtras(b);
//			startActivity(intent);
    		String url = "http://www.rabbitpre.com/m/2AnqE3Z#";
	    	String shareName = "少儿保险理念";
    		startActivity(new Intent(XinShengA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("title_image_id", title_image_id)
    				.putExtra("shareName", shareName));
			break;
		case R.id.baoXianLiNianChengRen:
//			Intent intent2 = new Intent(this, ImgAnimationA.class);
//			Bundle b2 = new Bundle();
//			b2.putInt("maxPageCount",11);
//			b2.putString("url", "product/hushenfu/jianjie/linian");
//			b2.putString("format", "gif");
//			b2.putString("screenFlag","jianjie");
//			intent2.putExtras(b2);
//			startActivity(intent2);

    		url = "http://www.rabbitpre.com/m/AZRBbyD#";
	    	shareName = "成人保险理念";
	    		startActivity(new Intent(XinShengA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName));
			break;
		case R.id.touBaoGuiZe:
//			Intent intent3 = new Intent(this, ImgAnimationA.class);
//			Bundle b3 = new Bundle();
//			b3.putInt("maxPageCount", 5);
//			b3.putString("url", "product/xinsheng/jianjie/guize");
//			b3.putString("format", "gif");
//			b3.putString("screenFlag","jianjie");
//			intent3.putExtras(b3);
//			startActivity(intent3);

    		url = "http://www.rabbitpre.com/m/UBjm22N#";
	    	shareName = "投保规则";
	    		startActivity(new Intent(XinShengA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("title_image_id", title_image_id)
	    				.putExtra("shareName", shareName));
			break;
		case R.id.chanPinTeSe:
//			Intent intent4 = new Intent(this, ImgAnimationA.class);
//			Bundle b4 = new Bundle();
//			b4.putInt("maxPageCount", 4);
//			b4.putString("url", "product/xinsheng/jianjie/tese");
//			b4.putString("format", "gif");
//			b4.putString("screenFlag","jianjie");
//			intent4.putExtras(b4);
//			startActivity(intent4);

    		url = "http://www.rabbitpre.com/m/ZReMAuMuY#";
	    	shareName = "产品特色";
	    		startActivity(new Intent(XinShengA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("title_image_id", title_image_id)
	    				.putExtra("shareName", shareName));
			break;
		case R.id.sheJiJiHuaShu:
			Intent intent5 = new Intent(XinShengA.this,XinShengPlanA2.class);
			startActivity(intent5);
			break;
		case R.id.xinLiJianJieBack:
			finish();
			break;
		}
	}

}
