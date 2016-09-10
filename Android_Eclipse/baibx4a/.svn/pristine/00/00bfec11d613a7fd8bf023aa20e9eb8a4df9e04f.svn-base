package com.baoxiao.activity.productshow.pinganfu;


import com.baoxiao.FastWebView;
import com.baoxiao.R;
import com.baoxiao.WebShowA;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.PopupWindow;

public class PingAnFuA extends Activity {
	private FastWebView pingAnFuVideo;
	private PopupWindow window;
	private int screenWidth,screenHeight;
	@Override
	protected void onResume() {
		pingAnFuVideo.setIs_active(false);
		pingAnFuVideo.onResume();
		super.onResume();
	}
	@Override
	protected void onPause() {
		pingAnFuVideo.onPause();
		super.onPause();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.ps_pinganfu);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		
		initWebView();
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView() {
		pingAnFuVideo = (FastWebView) findViewById(R.id.pingAnFuVideo);
		WebSettings webSettings = pingAnFuVideo.getSettings();
		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);
		
		pingAnFuVideo.loadUrl(getUrl());
		pingAnFuVideo.setWebChromeClient(new WebChromeClient());
		pingAnFuVideo.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
				view.loadUrl(url);
				return true;
			}
		});
	}


	
//	1、保险理念
//    平安福保险理念   http://www.rabbitpre.com/m/677ryap#
//    视频：为什么要买保险   http://v.youku.com/v_show/id_XMTYxMTAyNzM2MA==.html 
	public void baoXianLiNian(View v){
	    LayoutInflater inflater = (LayoutInflater) getSystemService(PingAnFuA.LAYOUT_INFLATER_SERVICE);
	    View view = inflater.inflate(R.layout.popwindow_up1, null);
	    view.findViewById(R.id.btn_popitem1).setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    		if (window != null) {window.dismiss();}
	    		String url = "http://www.rabbitpre.com/m/677ryap#";
	    		int title_image_id = R.drawable.product_pinganfu;
	    		startActivity(new Intent(PingAnFuA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("title_image_id", title_image_id)
	    				.putExtra("shareName", "平安福保险理念"));
	    	}
	    });
	    view.findViewById(R.id.btn_popitem2).setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    		if (window != null) {window.dismiss();}
	    		String url = "http://v.youku.com/v_show/id_XMTYxMTAyNzM2MA==.html";
	    		int title_image_id = R.drawable.product_pinganfu;
	    		startActivity(new Intent(PingAnFuA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("title_image_id", title_image_id)
	    				.putExtra("shareName", "视频：为什么要买保险"));
	    	}
	    });
	    
		showPopwindow(v,view,new Point(-(screenWidth/4), (screenHeight/12)));
//		Intent intent = new Intent(this, ImgAnimationA.class);
//		Bundle b = new Bundle();
//		b.putInt("maxPageCount",11);
//		b.putString("url", "product/pinganfu/jianjie/linian");
//		b.putString("format", "gif");
//		b.putString("screenFlag","jianjie");
//		intent.putExtras(b);
//		startActivity(intent);
	}
//2、如何正确买保险
//	    如何正确买保险H5   http://www.rabbitpre.com/m/eEvyjNeab#
//	    视频：如何正确买保险   http://v.youku.com/v_show/id_XMTYxMTAyODI1Mg==.html
//	    投保规则   http://www.rabbitpre.com/m/fyrqijbqM#
	public void touBaoGuiZe(View v){
	    LayoutInflater inflater = (LayoutInflater) getSystemService(PingAnFuA.LAYOUT_INFLATER_SERVICE);
	    View view = inflater.inflate(R.layout.popwindow_up2, null);
	    view.findViewById(R.id.btn_popitem1).setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    		if (window != null) {window.dismiss();}
	    		String url = "http://www.rabbitpre.com/m/eEvyjNeab#";
	    		startActivity(new Intent(PingAnFuA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", "如何正确买保险H5"));
	    	}
	    });
	    view.findViewById(R.id.btn_popitem2).setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    		if (window != null) {window.dismiss();}
	    		String url = "http://v.youku.com/v_show/id_XMTYxMTAyODI1Mg==.html";
	    		startActivity(new Intent(PingAnFuA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", "视频：如何正确买保险"));
	    	}
	    });
	    view.findViewById(R.id.btn_popitem3).setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    		if (window != null) {window.dismiss();}
	    		String url = "http://www.rabbitpre.com/m/fyrqijbqM#";
	    		startActivity(new Intent(PingAnFuA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", "投保规则"));
	    	}
	    });
	    //540--960
		showPopwindow(v,view,new Point(-(screenWidth/8), (screenHeight/12)));
//		Intent intent2 = new Intent(this, ImgAnimationA.class);
//		Bundle b2 = new Bundle();
//		b2.putInt("maxPageCount",5);
//		b2.putString("url", "product/pinganfu/jianjie/guize");
//		b2.putString("format", "gif");
//		b2.putString("screenFlag","jianjie");
//		intent2.putExtras(b2);
//		startActivity(intent2);
	}
//3、平安福保险特色   http://www.rabbitpre.com/m/aURuaiNu3#
	public void chanPinTeSe(View v){
		if (window != null) {window.dismiss();}
		String url = "http://www.rabbitpre.com/m/aURuaiNu3#";
		startActivity(new Intent(PingAnFuA.this,WebShowA.class)
				.putExtra("url", url)
				.putExtra("shareName", "平安福保险特色"));
//		Intent intent3 = new Intent(this, ImgAnimationA.class);
//		Bundle b3 = new Bundle();
//		b3.putInt("maxPageCount",7);
//		b3.putString("url", "product/pinganfu/jianjie/tese");
//		b3.putString("format", "gif");
//		b3.putString("screenFlag","jianjie");
//		intent3.putExtras(b3);
//		startActivity(intent3);
	}
	public void sheJiJiHuaShu(View v){
		Intent intent4 = new Intent(this, PingAnFuPlanA2.class);
		startActivity(intent4);
//		finish();
	}
	
	// 获取url的方法
	public String getUrl() {
		String url = "";
		url = "http://v.qq.com/page/d/v/x/d0156zxdavx.html";
		return url;
	}


	private void showPopwindow(View v,View view,Point p) {
	    window = new PopupWindow(view,
	        WindowManager.LayoutParams.WRAP_CONTENT,
	        WindowManager.LayoutParams.WRAP_CONTENT);
	    window.setFocusable(true);
	    window.setAnimationStyle(R.style.mypopwindow_anim_style);
	    window.setOutsideTouchable(true);
        view.setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
                if (window.isShowing()){
                    window.dismiss();
                }
				return false;
			}
        });
        if (window.isShowing()){
        	window.dismiss();
        }
        else{
    	    window.showAtLocation(v,Gravity.BOTTOM, p.x, p.y);
        }
	}
	public void pingAnFuJianJieBack(View v){
		finish();
	}

}
