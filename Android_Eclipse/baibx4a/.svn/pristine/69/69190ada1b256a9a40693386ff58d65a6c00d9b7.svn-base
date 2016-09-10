package com.baoxiao.activity.productshow.baiwanrwx;

import com.baoxiao.FastWebView;
import com.baoxiao.R;
import com.baoxiao.WebShowA2;
import com.baoxiao.util.CustomProgressDialog;
import com.baoxiao.util.Define;
import com.baoxiao.util.ShareService;
import com.umeng.socialize.media.UMImage;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BaiWanRWXShowA extends Activity {
	protected static final int ONPAGEFINISHED = 0;
	private FastWebView webView;
	private String url;
	private ProgressDialog cpd;
	
	@Override
	protected void onResume() {
		webView.setIs_active(true);
		playMusic(webView);
		super.onResume();
	}
	@Override
	protected void onPause(){
		pauseMusic(webView);
	    super.onPause();
	}
	protected void playMusic(WebView webView) {
		webView.loadUrl("javascript:(function() { var audios = document.getElementsByTagName('audio'); for(var i=0;i<audios.length;i++){audios[i].play();}})()");
		webView.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}})()");		
	}
	protected void pauseMusic(WebView webView) {
		webView.loadUrl("javascript:(function() { var audios = document.getElementsByTagName('audio'); for(var i=0;i<audios.length;i++){audios[i].pause();}})()");
		webView.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].pause();}})()");		
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fast_web_show);
		findViewById(R.id.concern).setVisibility(View.GONE);
		cpd = new CustomProgressDialog(this, "正在拼命加载中...",R.anim.frame_anim);
		webView = (FastWebView) findViewById(R.id.newsConsult);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		cpd.show();
		String data = getIntent().getStringExtra("postDate");
		url = Define.Server + "millionsShow.action?" + data;
		webView.loadUrl(url);
		
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url){
	            if (url.startsWith("tel:")){
	        	    String phone = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
	        	    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:"+phone));
                    startActivity(intent);
    	            return true;
	            }
				if (url.contains("rabbitpre") || url.contains("youku.com")
						|| url.contains("qq.com") || url.contains("sohu.com")
						|| url.contains("mycard.action")) {
					webView.setIs_active(false);
				}
	    		startActivity(new Intent(BaiWanRWXShowA.this,WebShowA2.class)
	    				.putExtra("url", url));
				return true;
            }
			@Override
			public void onPageFinished(WebView view, String url){
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (!isFinishing()) {
							handler2.sendEmptyMessage(ONPAGEFINISHED);
						}
					}
				}).start();
				cpd.dismiss();
				super.onPageFinished(view, url);
			}
		});
	}
	private Handler handler2 = new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case ONPAGEFINISHED:
				webView.loadUrl("javascript:(function() { var audios = document.getElementsByTagName('audio'); for(var i=0;i<audios.length;i++){audios[i].play();}})()");
				webView.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}})()");
				break;

			default:
				break;
			}
			return false;
		}
	});
	
	public void share(View v) {
		UMImage image = new UMImage(BaiWanRWXShowA.this,R.drawable.product_children_icon);
	    String name = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("name", null);
		ShareService.startShareWithImage(image,url,"您好！我是" + name + ",这是我为您设计的百万任我行计划书，请您查看！",
				BaiWanRWXShowA.this,"百万任我行计划书");
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			webView.setIs_active(false);
			finish();
		}
		return false;
	};
	
	public void news_back(View v){
		webView.setIs_active(false);
	   	finish();
	}
}
