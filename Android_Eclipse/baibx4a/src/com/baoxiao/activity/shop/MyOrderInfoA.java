package com.baoxiao.activity.shop;

import com.baoxiao.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

/**
 * '��Ʒ�̳ǹ���������'���ֽ���
 *
 */
public class MyOrderInfoA extends Activity implements OnClickListener
{

	private ImageView studyBack;
	private WebView myOrder;
	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//�����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shop_myorderinfo);

		// ��ȡ��¼�û�id
		userId = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("userid", "");
		myOrder = (WebView) findViewById(R.id.myOrder);

			WebSettings webSettings = myOrder.getSettings();
			// ���ü��ؽ�����ҳ������Ӧ��Ļ
			webSettings.setUseWideViewPort(true);
			webSettings.setLoadWithOverviewMode(true);
		    webSettings.setSavePassword(false);      
		    webSettings.setSaveFormData(false);      
		    webSettings.setJavaScriptEnabled(true);  
		    webSettings.setAllowFileAccess(true); 
		    webSettings.setSupportZoom(false);

		    myOrder.setWebViewClient(new DemoWebViewClient());
		    myOrder.loadUrl(getUrl());
		
			
			studyBack = (ImageView) findViewById(R.id.studyBack);

		studyBack.setOnClickListener(this);

	}

	
	
	class DemoWebViewClient extends WebViewClient { 
    	@Override 
    	public boolean shouldOverrideUrlLoading(WebView view, String url) { 
	    	view.loadUrl(url); 
	    	return true; 
    	} 
    } 
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.studyBack:
			finish();
			break;
		default:
			break;
		}
	}

	// ��ȡurl�ķ���
	public String getUrl()
	{
		String url = "";
		url = getIntent().getStringExtra("url");
		return url;
	}
	
	@Override
	public void onBackPressed()
	{
		finish();
		super.onBackPressed();
	}

}