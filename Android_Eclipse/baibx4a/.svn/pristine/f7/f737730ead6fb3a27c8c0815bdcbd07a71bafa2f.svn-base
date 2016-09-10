package com.baoxiao.activity.study;

import android.app.Activity;
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
import com.baoxiao.R;
import com.baoxiao.service.study.CollectionService;
import com.baoxiao.util.Define;
import com.baoxiao.util.GetSharedPreferencesValue;
import com.baoxiao.util.MessageHelper;
import com.baoxiao.util.ShareService;

/**
 * '视频详情'布局界面
 *
 */
public class StudyVideoInfoA extends Activity implements OnClickListener{

	private final static int SPACEIMAGE = 0;
	private final static int ONPAGEFINISHED = 1;
	private ImageView studyBack,spaceImage,shareImage;
	private WebView studyVideo;
	private String url,load;
	private String urlPath;
	private MessageHelper m;
	private CollectionService cs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	 	requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.study_videoinfo);
	    url = getIntent().getStringExtra("url");
//	    url = "http://v.youku.com/v_show/id_XMTUzNjg3NzU1Mg==.html";
	    urlPath = getIntent().getStringExtra("urlPath");
	    initWebView();
	    studyBack = (ImageView) findViewById(R.id.studyBack);
	    spaceImage = (ImageView) findViewById(R.id.spaceImage);
	    shareImage = (ImageView) findViewById(R.id.shareImage);
	    
	    studyBack.setOnClickListener(this);
	    spaceImage.setOnClickListener(this);
	    shareImage.setOnClickListener(this);
	    
	    cs = new CollectionService();
	}
	
	private void initWebView() {
		studyVideo = (WebView) findViewById(R.id.studyVideo);
		
		WebSettings webSettings = studyVideo.getSettings();
		// 设置加载进来的页面自适应屏幕
				webSettings.setUseWideViewPort(true);
				webSettings.setLoadWithOverviewMode(true);
				webSettings.setJavaScriptEnabled(true);
				
				studyVideo.loadUrl(url);
				studyVideo.setWebViewClient(new WebViewClient(){
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
									handler.sendEmptyMessage(ONPAGEFINISHED);
								}
							}
						}).start();
						super.onPageFinished(view, url);
					}
				});
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.studyBack:
			studyVideo.goBack();
			finish();
			break;
		case R.id.spaceImage:
			//把id和type和userid传给后台
			String id = cs.idSplit(url);
			System.out.println(id);
			String type = urlPath;
			String userid = GetSharedPreferencesValue.getUserId(this);
			load = Define.Server+"addshoucan.action?id="+id+"&type="+type+"&userid="+userid;
			new Thread(runnable).start();
			break;
		case R.id.shareImage:
			ShareService.startShare(url,"平安百宝箱学习乐园", StudyVideoInfoA.this);
			break;

		default:
			break;
		}
	}
	
	Runnable runnable = new Runnable()
	{
		@Override
		public void run()
		{
			m = cs.getDataFromServer(load);
			handler.sendEmptyMessage(SPACEIMAGE);
		}
	};
	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case SPACEIMAGE:
				cs.getJsonList(load,StudyVideoInfoA.this,m);
				break;
			case ONPAGEFINISHED:
				studyVideo.loadUrl("javascript:(function() { var audios = document.getElementsByTagName('audio'); for(var i=0;i<audios.length;i++){audios[i].play();}})()");
				studyVideo.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}})()");
				break;
			default:
				break;
			}
			return false;
		}
	});
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			studyVideo.loadUrl("about:blank");
			finish();
		}
		return false;
	};
}
