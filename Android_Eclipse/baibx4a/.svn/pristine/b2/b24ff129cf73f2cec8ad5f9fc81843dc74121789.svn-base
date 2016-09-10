package com.baoxiao.activity.productshow.zhiyuerensheng;

import com.baoxiao.R;
import com.baoxiao.WebShowA;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class ZhiYueRenShengA extends Activity implements OnClickListener{

	private TextView baoXianLiNian,touBaoGuiZe,chanPinTeSe,
    sheJiJiHuaShu;
	private ImageView huShenFuJianJieBack;
	private WebView children_video;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.ps_zhiyuerensheng);
		initView();
		initWebView();
	}
	private void initWebView() {
		children_video = (WebView) findViewById(R.id.children_video);

		WebSettings webSettings = children_video.getSettings();
		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);
		String url = "http://v.youku.com/v_show/id_XMTUzMTg1ODg2OA==.html";
		children_video.loadUrl(url);
		children_video.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
				view.loadUrl(url);
				return true;
			}
		});
	}

	private void initView() {
		baoXianLiNian = (TextView) findViewById(R.id.baoXianLiNian);
		touBaoGuiZe = (TextView) findViewById(R.id.touBaoGuiZe);
		chanPinTeSe = (TextView) findViewById(R.id.chanPinTeSe);
		sheJiJiHuaShu = (TextView) findViewById(R.id.sheJiJiHuaShu);
		huShenFuJianJieBack = (ImageView) findViewById(R.id.huShenFuJianJieBack);
		baoXianLiNian.setOnClickListener(this);
		touBaoGuiZe.setOnClickListener(this);
		chanPinTeSe.setOnClickListener(this);
		sheJiJiHuaShu.setOnClickListener(this);
		huShenFuJianJieBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.baoXianLiNian:
//			Intent intent = new Intent(this, ImgAnimationA.class);
//			Bundle b = new Bundle();
//			b.putInt("maxPageCount", 11);
//			b.putString("url", "product/zhiyuerensheng/jianjie/linian");
//			b.putString("format", "gif");
//			b.putString("screenFlag", "jianjie");
//			intent.putExtras(b);
//			startActivity(intent);
			
    		String url = "http://www.rabbitpre.com/m/u6mFBIReV#";
	    	String shareName = "保险理念";
	    		startActivity(new Intent(ZhiYueRenShengA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName));
			break;
		case R.id.touBaoGuiZe:
//			Intent intent2 = new Intent(this, ImgAnimationA.class);
//			Bundle b2 = new Bundle();
//			b2.putInt("maxPageCount", 6);
//			b2.putString("url", "product/zhiyuerensheng/jianjie/toubaoguize");
//			b2.putString("format", "gif");
//			b2.putString("screenFlag", "jianjie");
//			intent2.putExtras(b2);
//			startActivity(intent2);

    		url = "http://www.rabbitpre.com/m/iEfyaar#";
	    	shareName = "投保规则";
	    		startActivity(new Intent(ZhiYueRenShengA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName));
			break;
		case R.id.chanPinTeSe:
//			Intent intent3 = new Intent(this, ImgAnimationA.class);
//			Bundle b3 = new Bundle();
//			b3.putInt("maxPageCount", 6);
//			b3.putString("url", "product/zhiyuerensheng/jianjie/chanpintese");
//			b3.putString("format", "gif");
//			b3.putString("screenFlag", "jianjie");
//			intent3.putExtras(b3);
//			startActivity(intent3);

    		url = "http://www.rabbitpre.com/m/ZfaUvMe6W#";
	    	shareName = "产品特色";
	    		startActivity(new Intent(ZhiYueRenShengA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName));
			break;
		case R.id.sheJiJiHuaShu:
			Intent intent4 = new Intent(this, ZhiYueRenShengPlanA.class);
			startActivity(intent4);
			finish();
			break;
		case R.id.huShenFuJianJieBack:
			children_video.goBack();
			finish();
			break;

		default:
			break;
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			children_video.goBack();
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
