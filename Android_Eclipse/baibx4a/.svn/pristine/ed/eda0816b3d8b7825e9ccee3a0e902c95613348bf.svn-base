package com.baoxiao;

import com.baoxiao.R;
import com.baoxiao.util.ShareService;
import com.umeng.socialize.media.UMImage;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class WebShowA extends Activity{
	private WebView webView;
	private String url,shareName;
	int title_image_id;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_articleinfo);
		findViewById(R.id.collection).setVisibility(View.GONE);
		
		webView = (WebView)findViewById(R.id.newsConsult);
		WebSettings webSettings = webView.getSettings();
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);
		
		url = getIntent().getStringExtra("url");
		shareName = getIntent().getStringExtra("shareName");
		((TextView)findViewById(R.id.tv_title)).setText(shareName);
		
		title_image_id = getIntent().getIntExtra("title_image_id",-1);
		webView.loadUrl(url);
		webView.setWebChromeClient(new WebChromeClient());
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				WebShowA.this.url = url;
				view.loadUrl(url);
				return true;
            }
			@Override
			public void onPageFinished(WebView view, String url){
				super.onPageFinished(view, url);
			}
		});
		findViewById(R.id.share).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					share();
			}
			private void share() {
				UMImage image;
				if (title_image_id == -1) {
					image = new UMImage(WebShowA.this, R.drawable.invitation_mould4);
				}else{
					image = new UMImage(WebShowA.this, title_image_id);
				}
				ShareService.startShareWithImage(image,url,
						shareName,
						WebShowA.this,
						""
						);
			}
		});
	}
	public void news_back(View v){
		webView.loadUrl("about:blank");
		finish();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			webView.loadUrl("about:blank");
			finish();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
