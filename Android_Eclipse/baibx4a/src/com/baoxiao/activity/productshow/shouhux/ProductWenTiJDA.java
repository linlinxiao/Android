package com.baoxiao.activity.productshow.shouhux;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ProductWenTiJDA extends Activity{

	private WebView webView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����ȫ��
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.ps_shouhux_wentijieda);
		
		initView();
	}



	private void initView() {
		webView = (WebView) findViewById(R.id.wv);
		
		WebSettings webSettings = webView.getSettings();
		// ���ü��ؽ�����ҳ������Ӧ��Ļ
				webSettings.setUseWideViewPort(true);
				webSettings.setLoadWithOverviewMode(true);
				webSettings.setJavaScriptEnabled(true);
				
				webView.loadUrl(Define.Server+"web/video/wentijieda.jsp");
				webView.setWebViewClient(new WebViewClient(){
					public boolean shouldOverrideUrlLoading(WebView view, String url)
		            { //  ��д�˷������������ҳ��������ӻ����ڵ�ǰ��webview����ת��������������Ǳ�
		                    view.loadUrl(url);
		                    return true;
		            }
				});
	}

	/**
	 * �ر�WebView֮������������Ƶ��ֹͣ�Ľ���취
	 */
	@Override
	protected void onPause() {
		webView.reload();
		super.onPause();
	}
	
	/**
	 * ������ҳ�����¼�������ǰһ����ҳ
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK&&webView.canGoBack()){
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	
}
