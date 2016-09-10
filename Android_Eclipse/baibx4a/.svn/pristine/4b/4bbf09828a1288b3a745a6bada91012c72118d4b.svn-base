package com.baoxiao.activity.message;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import com.baoxiao.R;
import com.baoxiao.service.study.CollectionService;
import com.baoxiao.util.CustomProgressDialog;
import com.baoxiao.util.Define;
import com.baoxiao.util.GetSharedPreferencesValue;
import com.baoxiao.util.MessageHelper;
import com.baoxiao.util.ShareService;
import com.umeng.socialize.media.UMImage;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * '新闻详情页面'界面布局
 *
 */
public class ArticleInfoA extends Activity implements OnClickListener
{
	protected static final int ONPAGEFINISHED = 0;
	private ImageView collection,share;
	private WebView webView;
	private String url,urlPath,load;
	private MessageHelper m;
	private CollectionService cs;
	private ProgressDialog cpd;
	
	@Override
	protected void onPause(){
		webView.onPause();
	    super.onPause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.news_articleinfo);
		cpd = new CustomProgressDialog(this, "正在拼命加载中...",R.anim.frame_anim);
		collection = (ImageView) findViewById(R.id.collection);
		share = (ImageView) findViewById(R.id.share);
		webView = (WebView) findViewById(R.id.newsConsult);

		url = getIntent().getStringExtra("url");
		//http://www.rabbitpre.com/m/JBfANru#?id=138&title_image=http://www.bbxapp.com:80/images/news/4899443_203527027459_2.jpg&title_name=%E5%A6%82%E6%9E%9C%E4%BD%A0%E5%8F%91%E7%94%9F%E6%84%8F%E5%A4%96%E4%BA%86%E4%BD%A0%E8%83%BD%E7%95%99%E4%B8%8B%E4%BB%80%E4%B9%88%EF%BC%9F
		
		String title = "文章详情";
		if (url.contains("&title_name=")) {
			title = url.split("&title_name=")[1].split("&")[0];
			try {
				title = URLDecoder.decode(title, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		((TextView)findViewById(R.id.tv_title)).setText(title);
		urlPath = getIntent().getStringExtra("urlPath");
		
		cs = new CollectionService();

		webView.setLongClickable(true);
		collection.setOnClickListener(this);
		share.setOnClickListener(this);

		WebSettings webSettings = webView.getSettings();

		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);
		cpd.show();
		webView.loadUrl(url);
		
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                    view.loadUrl(url);
                    return true;
            }
			@Override
			public void onPageFinished(WebView view, String url)
			{
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
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.collection:
			//把id和type和userid传给后台
			String id = cs.split("id",url);
//			System.out.println(id);
			String type = urlPath;
			String userid = GetSharedPreferencesValue.getUserId(this);
			load = Define.Server+"addshoucan.action?id="+id+"&type="+type+"&userid="+userid;
			new Thread(runnable).start();
			break;
		case R.id.share:
			String title_image_url = cs.split("title_image",url);
			String title_name = cs.split("title_name",url);
			if (title_name != null) {
				try {
					title_name = URLDecoder.decode(title_name, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			UMImage image = new UMImage(ArticleInfoA.this, title_image_url);
			ShareService.startShareWithImage(image,url,title_name, ArticleInfoA.this,"");
			break;
		default:
			break;
		}
	}
	

	Runnable runnable = new Runnable(){
		@Override
		public void run(){
			m = cs.getDataFromServer(load);
			Message msg = new Message();
			handler.sendMessage(msg);
		}
	};
	private Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			cs.getJsonList(load,ArticleInfoA.this,m);
		};
	};
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			webView.loadUrl("about:blank");
			finish();
		}
		return false;
	};
	public void news_back(View v){
		webView.loadUrl("about:blank");
		finish();
	}
}
