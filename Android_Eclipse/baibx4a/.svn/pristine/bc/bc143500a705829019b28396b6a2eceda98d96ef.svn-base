package com.baoxiao.activity.productshow.childrenPeace;

import com.baoxiao.R;
import com.baoxiao.WebShowA;
import com.baoxiao.activity.productshow.pinganfu.PingAnFuA;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

public class ChildrenPingAnFuA extends Activity {
//	private WebView pingAnFuVideo;
	private PopupWindow window;
	private int screenWidth,screenHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.ps_children_pinganfu);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;

//		initWebView();
	}

//	private void initWebView() {
//		pingAnFuVideo = (WebView) findViewById(R.id.pingAnFuVideo);
//
//		WebSettings webSettings = pingAnFuVideo.getSettings();
//		// 设置加载进来的页面自适应屏幕
//		webSettings.setUseWideViewPort(true);
//		webSettings.setLoadWithOverviewMode(true);
//		webSettings.setJavaScriptEnabled(true);
//
//		pingAnFuVideo.loadUrl(getUrl());
//		pingAnFuVideo.setWebViewClient(new WebViewClient() {
//			public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
//				view.loadUrl(url);
//				return true;
//			}
//		});
//	}

	public void baoXianLiNian(View v){
	    LayoutInflater inflater = (LayoutInflater) getSystemService(PingAnFuA.LAYOUT_INFLATER_SERVICE);
	    View view = inflater.inflate(R.layout.popwindow_up1, null);
	    Button btn_popitem1 = (Button) view.findViewById(R.id.btn_popitem1);
	    btn_popitem1.setText("为什么要买少儿险\n(h5动画)");
	    btn_popitem1.setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    		if (window != null) {window.dismiss();}
	    		String url = "http://www.rabbitpre.com/m/MIZZnV3br";
	    		int title_image_id = R.drawable.product_children_icon;
	    		startActivity(new Intent(ChildrenPingAnFuA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("title_image_id", title_image_id)
	    				.putExtra("shareName", "视频：为什么要买少儿险\r(h5)"));
	    	}
	    });
	    Button btn_popitem2 = (Button) view.findViewById(R.id.btn_popitem2);
	    btn_popitem2.setText("孩子需要重疾险吗\n(视频)");
	    btn_popitem2.setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    		if (window != null) {window.dismiss();}
	    		String url = "http://player.youku.com/embed/XMTYyMzQ0NzI1Ng==";
	    		int title_image_id = R.drawable.product_children_icon;
	    		startActivity(new Intent(ChildrenPingAnFuA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("title_image_id", title_image_id)
	    				.putExtra("shareName", "孩子需要重疾险吗\r视频"));
	    	}
	    });
		showPopwindow(v,view,new Point(-(screenWidth/4), (screenHeight/12)));

//		Intent intent = new Intent(this, ImgAnimationA.class);
//		Bundle b = new Bundle();
//		b.putInt("maxPageCount",12);
//		b.putString("url", "/product/children_peace/linian");
//		b.putString("format", "png");
//		b.putString("screenFlag","jianjie");
//		intent.putExtras(b);
//		startActivity(intent);
	}
	public void touBaoGuiZe(View v){
		String url = "http://www.rabbitpre.com/m/7fyIRrI";
		int title_image_id = R.drawable.product_children_icon;
		startActivity(new Intent(ChildrenPingAnFuA.this,WebShowA.class)
				.putExtra("url", url)
				.putExtra("title_image_id", title_image_id)
				.putExtra("shareName", "投保规则"));
//
//		Intent intent2 = new Intent(this, ImgAnimationA.class);
//		Bundle b2 = new Bundle();
//		b2.putInt("maxPageCount",6);
//		b2.putString("url", "product/children_peace/guize");
//		b2.putString("format", "png");
//		b2.putString("screenFlag","jianjie");
//		intent2.putExtras(b2);
//		startActivity(intent2);
	}
	public void chanPinTeSe(View v){
		String url = "http://www.rabbitpre.com/m/2YJR7ni";
		int title_image_id = R.drawable.product_children_icon;
		startActivity(new Intent(ChildrenPingAnFuA.this,WebShowA.class)
				.putExtra("url", url)
				.putExtra("title_image_id", title_image_id)
				.putExtra("shareName", "产品特色"));
//		Intent intent3 = new Intent(this, ImgAnimationA.class);
//		Bundle b3 = new Bundle();
//		b3.putInt("maxPageCount",6);
//		b3.putString("url", "product/children_peace/chanpintese");
//		b3.putString("format", "png");
//		b3.putString("screenFlag","jianjie");
//		intent3.putExtras(b3);
//		startActivity(intent3);
	}
	public void sheJiJiHuaShu(View v){
		Intent intent4 = new Intent(this, ChildrenPingAnFuPlanA.class);
		startActivity(intent4);
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

//	@Override
//	protected void onPause() {
//		pingAnFuVideo.reload();
//		super.onPause();
//	}	
}
