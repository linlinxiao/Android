package com.baoxiao.activity.study;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.baoxiao.FastWebView;
import com.baoxiao.R;
import com.baoxiao.util.Define;
import com.baoxiao.util.ShareService;
import com.umeng.socialize.media.UMImage;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * '保鲜视频列表显示'布局界面
 *
 */
public class StudyA2 extends Activity {

	private final static int ONPAGEFINISHED = 0;
	private FastWebView webView;
	private ImageView shareImage;
	private TextView tv_title;
	private String titleName,titleImage,myUrl = "";
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	    setContentView(R.layout.study_video);
	    tv_title = (TextView) findViewById(R.id.tv_title);
	    shareImage = (ImageView) findViewById(R.id.shareImage);
	    tv_title.setText("学习园地");
	    initWebView();
	    
	}
	private void initWebView() {
		webView = (FastWebView) findViewById(R.id.studyVideo); 
//		studyVideo.setVerticalScrollBarEnabled(false);
		WebSettings webSettings = webView.getSettings();
		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);
		//http://localhost:8080/adminhoutai/admin/study/safeVideo.action?pageNo=0
		String url = Define.Server+"adminhoutai/admin/study/studylist.action?PageNo=0&type=DevelopCustomer";
		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
				myUrl = url;
				if (url.contains("studylist.action") || url.contains("safeVideo.action")) {//列表
					shareImage.setVisibility(View.INVISIBLE);
				    tv_title.setText("学习园地");
				}else{//详情
//							spaceImage.setVisibility(View.VISIBLE);
					shareImage.setVisibility(View.VISIBLE);
					titleName = getField(url,"title");
					titleImage = getField(url,"titleImage");
				}
				view.loadUrl(url);
				return true;
            }
			private String getField(String url,String field) {
				//http://v.youku.com/v_show/id_XMTUzMjI3NTA4NA==.html?title=%E4%B8%93%E5%AE%B6%E8%A7%A3%E8%AF%B4-%E7%A9%BA%E6%B0%94%E6%B1%A1%E6%9F%93%E4%B8%8E%E4%BA%BA%E4%BD%93%E6%8A%B5%E6%8A%97&titleImage=/images/study/kqwr.png
				String result = "";
				if (url.contains(field + "=")) {
					result = url.split(field + "=")[1];
					result = result.split("&")[0];
				}
				return result;
			}
			@Override
			public void onPageFinished(WebView view, String url)
			{
				if (!url.contains("studylist.action") && !url.contains("safeVideo.action")) {//列表
				    try {
						tv_title.setText(URLDecoder.decode(titleName, "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}

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

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			webView.destroy();
			finish();
		}
		return false;
	};
	public void share(View v){
		UMImage image = new UMImage(StudyA2.this, titleImage);
		ShareService.startShareWithImage(image,myUrl,titleName, StudyA2.this,"");
	}
	public void study_back(View v){
		if ("".equals(myUrl) || myUrl.contains("studylist.action") || myUrl.contains("safeVideo.action")) {
			//列表
//			webView.destroy();
			finish();
		}else{
			myUrl = Define.Server+"adminhoutai/admin/study/studylist.action?PageNo=0&type=DevelopCustomer";
			webView.goBack();
		}
	}
}