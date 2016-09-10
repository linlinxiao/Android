package com.baoxiao.activity.productshow.xinxiang;

import com.baoxiao.R;
import com.baoxiao.WebShowA;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class XinXiangA extends Activity implements OnClickListener{
	private TextView baoXianLiNianShaoEr,baoXianLiNianChengRen,
	touBaoGuiZe,chanPinTeSe,sheJiJiHuaShu;
	private ImageView xinLiJianJieBack;
	private WebView xinXiangVideo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//��������
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.ps_xinxiang);
		
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
	
	private void initWebView() {
		xinXiangVideo = (WebView) findViewById(R.id.xinXiangVideo);

		WebSettings webSettings = xinXiangVideo.getSettings();
		// ���ü��ؽ�����ҳ������Ӧ��Ļ
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);

		xinXiangVideo.loadUrl(getUrl());
		xinXiangVideo.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) { // ��д�˷������������ҳ��������ӻ����ڵ�ǰ��webview����ת��������������Ǳ�
				view.loadUrl(url);
				return true;
			}
		});
	}
	
	// ��ȡurl�ķ���
	public String getUrl() {
		String url = "";
		url = "http://v.youku.com/v_show/id_XMTQ3NDMyMzI4.html?from=s1.8-1-1.2";
		return url;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.baoXianLiNianShaoEr:
//			Intent intent = new Intent(this, ImgAnimationA.class);
//			Bundle b = new Bundle();
//			b.putString("url", "product/shijitianshi/jianjie/baoxianlinian");
//			b.putInt("maxPageCount", 8);
//			b.putString("format", "gif");
//			b.putString("screenFlag","jianjie");
//			intent.putExtras(b);
//			startActivity(intent);
    		String url = "http://www.rabbitpre.com/m/ZfqMVfv#";
	    	String shareName = "�ٶ���������";
    		startActivity(new Intent(XinXiangA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("shareName", shareName));
			break;
		case R.id.baoXianLiNianChengRen:
//			Intent intent2 = new Intent(this, ImgAnimationA.class);
//			Bundle b2 = new Bundle();
//			b2.putInt("maxPageCount", 8);
//			b2.putString("url", "product/xinli/jianjie/linianC");
//			b2.putString("format", "gif");
//			b2.putString("screenFlag","jianjie");
//			intent2.putExtras(b2);
//			startActivity(intent2);
    		url = "http://www.rabbitpre.com/m/ZBnmqmP#";
	    	shareName = "���˱�������";
    		startActivity(new Intent(XinXiangA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("shareName", shareName));
			break;
		case R.id.touBaoGuiZe:
//			Intent intent3 = new Intent(this, ImgAnimationA.class);
//			Bundle b3 = new Bundle();
//			b3.putInt("maxPageCount", 4);
//			b3.putString("url", "product/xinxiang/jianjie/toubaoguize");
//			b3.putString("format", "gif");
//			b3.putString("screenFlag","jianjie");
//			intent3.putExtras(b3);
//			startActivity(intent3);

    		url = "http://www.rabbitpre.com/m/MzQBBvI#";
	    	shareName = "Ͷ������";
    		startActivity(new Intent(XinXiangA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("shareName", shareName));
			break;
		case R.id.chanPinTeSe:
//			Intent intent4 = new Intent(this, ImgAnimationA.class);
//			Bundle b4 = new Bundle();
//			b4.putInt("maxPageCount", 3);
//			b4.putString("url", "product/xinxiang/jianjie/chanpintese");
//			b4.putString("format", "gif");
//			b4.putString("screenFlag","jianjie");
//			intent4.putExtras(b4);
//			startActivity(intent4);

    		url = "http://www.rabbitpre.com/m/IRmbEvEZK#";
	    	shareName = "��Ʒ��ɫ";
    		startActivity(new Intent(XinXiangA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("shareName", shareName));
			break;
		case R.id.sheJiJiHuaShu:
			Intent intent5 = new Intent(XinXiangA.this,XinXiangPlanA.class);
			startActivity(intent5);
			finish();
			break;
		case R.id.xinLiJianJieBack:
			finish();
			break;
		}
	}
	
	@Override
	protected void onPause() {
		xinXiangVideo.reload();
		super.onPause();
	}

}
