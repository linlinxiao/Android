package com.baoxiao.test.activity.testmian;

import com.baoxiao.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

public class TestPtfActivity extends Activity
{

	private WebView wv;
	private String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.ps_shouhux_guanniandr);
		
		wv = (WebView) this.findViewById(R.id.webview1);
//		url = "http://192.168.1.203:8080/baibx/pd.pdf";
//		wv = (WebView) this.findViewById(R.id.webview1);
//        wv.getSettings().setJavaScriptEnabled(true);
//		wv.setWebChromeClient(new WebChromeClient());
//		WebSettings webSettings = wv.getSettings();
//		webSettings.setUseWideViewPort(true);
//		webSettings.setLoadWithOverviewMode(true);
//		webSettings.setBuiltInZoomControls(true);
//		wv.loadUrl("http://docs.google.com/gview?embedded=true&url="+url);
		loadPDF2();

	}

	private void loadPDF2(){  
		wv.getSettings().setJavaScriptEnabled(true);  
		wv.getSettings().setSupportZoom(true);  
		wv.getSettings().setDomStorageEnabled(true);  
		wv.getSettings().setAllowFileAccess(true);  
//		wv.getSettings().setPluginsEnabled(true);  
		wv.getSettings().setUseWideViewPort(true);  
		wv.getSettings().setBuiltInZoomControls(true);  
		wv.requestFocus();  
		wv.getSettings().setLoadWithOverviewMode(true);  
		wv.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);  
        String pdfUrl = "http://192.168.1.203:8080/baibx/pd.pdf";  
        String data = "http://docs.google.com/gview?embedded=true&url="+"pdfUrl";  
        wv.loadData(data, "text/html", "UTF-8");  
          
    }  
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_ptf, menu);
		return true;
	}

}
