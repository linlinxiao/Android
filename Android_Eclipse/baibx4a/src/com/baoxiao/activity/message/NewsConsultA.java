package com.baoxiao.activity.message;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.baoxiao.FastWebView;
import com.baoxiao.R;
import com.baoxiao.util.CustomProgressDialog;
import com.baoxiao.util.Define;
import com.baoxiao.util.ShareService;
import com.umeng.socialize.media.UMImage;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * '新闻资讯'界面布局
 *
 */
public class NewsConsultA extends Activity{
	protected static final int ONPAGEFINISHED = 0;
	private FastWebView webView;
	private String url;
	private ProgressDialog cpd;


	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fast_web_show2);
		((TextView)findViewById(R.id.tv_title)).setText("新闻资讯");
		cpd = new CustomProgressDialog(this, "正在拼命加载中...",R.anim.frame_anim);
		webView = (FastWebView) findViewById(R.id.newsConsult);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		cpd.show();
		url = Define.Server+"allnews.action";
		webView.loadUrl(url);
				
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				NewsConsultA.this.url = url;
				view.loadUrl(url);
//				Intent intent = new Intent(NewsConsultA.this,ArticleInfoA.class); 
//				intent.putExtra("url", url);
//				intent.putExtra("urlPath", "news");
//				startActivity(intent);
				return true;
            }
			@Override
			public void onPageFinished(WebView view, String url)
			{
				if (!url.contains("allnews.action")) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if (!isFinishing()) {
								handler.sendEmptyMessage(ONPAGEFINISHED);
							}
						}
					}).start();
				}
				cpd.dismiss();
				super.onPageFinished(view, url);
			}
		});
	}
	private Handler handler = new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case ONPAGEFINISHED:
				playMusic(webView);
				break;

			default:
				break;
			}
			return false;
		}
	});

	protected void playMusic(WebView webView) {
		webView.loadUrl("javascript:(function() { var audios = document.getElementsByTagName('audio'); for(var i=0;i<audios.length;i++){audios[i].play();}})()");
		webView.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}})()");		
	}

	public void share(View v) {
		String title_image_url = split("title_image",url);
		String title_name = split("title_name",url);
		if (title_name != null) {
			try {
				title_name = URLDecoder.decode(title_name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		UMImage image = new UMImage(this, title_image_url);
		ShareService.startShareWithImage(image,url,title_name, this,"");
	}
	
	public String split(String who,String url){
		String result = null;
		String[] strarray = url.split(who + "=");
		if (strarray.length > 1) {
			result = strarray[1].split("&")[0];
		}
		return result;
	}

	public void news_back(View v){
		if (url.contains("allnews.action")) {
			finish();
		}else{
			url = Define.Server+"allnews.action";
			webView.goBack();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return false;
		}else{
			return super.onKeyDown(keyCode, event);
		}
	}
}
