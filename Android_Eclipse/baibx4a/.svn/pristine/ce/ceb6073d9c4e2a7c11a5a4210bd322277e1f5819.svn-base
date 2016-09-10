//package com.baoxiao.activity.pay;
//
//import com.baoxiao.JiHuoA;
//import com.baoxiao.R;
//import com.baoxiao.R.layout;
//import com.baoxiao.R.menu;
//import com.baoxiao.activity.mine.HuDongA;
//import com.baoxiao.activity.productshow.BaoXianProductA;
//import com.baoxiao.util.Define;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.view.KeyEvent;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.Window;
//import android.view.WindowManager;
//import android.webkit.JavascriptInterface;
//import android.webkit.JsResult;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.ImageView;
//import android.widget.Toast;
//
// TODO ֧�����������޸ģ�
///**
// * '֧�����ֽ��'���ֽ���
// *
// */
//public class BaoXianProductPayA extends Activity
//{
//
//	private WebView mWebView;
//	private ImageView imageView;
//	private String url;
//	private String userid;
//	private Handler handler = new Handler();
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState)
//	{
//		super.onCreate(savedInstanceState);
//		
//		// �����ޱ���
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		// ����ȫ��
////		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
////				WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		setContentView(R.layout.pay_baibao_product);
//		
////		mWebView = (WebView) findViewById(R.id.wv_bbxpay);
//		imageView = (ImageView) findViewById(R.id.baoXianPayBack);
//		
//		userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
//		url = Define.Server+"paypage.action?userid="+userid;
//		
////		Toast.makeText(this, "�û����ǣ�"+userid,  Toast.LENGTH_SHORT).show();
//		mWebView.setLongClickable(true);
//		WebSettings webSettings = mWebView.getSettings();
//
//		// ���ü��ؽ�����ҳ������Ӧ��Ļ
//		webSettings.setUseWideViewPort(true);
//		webSettings.setLoadWithOverviewMode(true);
//		webSettings.setSavePassword(false);      
//	    webSettings.setSaveFormData(false);      
//	    webSettings.setJavaScriptEnabled(true);  
//	    webSettings.setAllowFileAccess(true); 
//	    webSettings.setSupportZoom(false);
//		
//	    mWebView.setWebViewClient(new DemoWebViewClient());
//	    mWebView.setWebChromeClient(new MyWebChromeClient());
//	    mWebView.addJavascriptInterface(new JavaScriptInterface(),"demo");
//	    mWebView.loadUrl(url);
//		
//		imageView.setOnClickListener(new OnClickListener()
//		{
//			
//			@Override
//			public void onClick(View arg0)
//			{
//				finish();
//			}
//		});
//		
//		
//	}
//	
//	class DemoWebViewClient extends WebViewClient { 
//    	@Override 
//    	public boolean shouldOverrideUrlLoading(WebView view, String url) { 
//	    	view.loadUrl(url); 
//	    	return true; 
//    	} 
//    } 
//
//	final class MyWebChromeClient extends WebChromeClient{
//		 public boolean onJsAlert(WebView view, String url, String message,
//					JsResult result) {
//				new AlertDialog.Builder(BaoXianProductPayA.this).
//				      setMessage(message).
//				      setPositiveButton("OK", new DialogInterface.OnClickListener() {
//						@Override
//						public void onClick(DialogInterface dialog, int which) {
//							Intent intent = new Intent(BaoXianProductPayA.this,JiHuoA.class);
//							intent.putExtra("IntentTag", "3");
//							startActivity(intent);
//							finish();
//						}
//					}).setCancelable(true).create().show();
//				result.confirm();
//				return true;
//			}
//	 }
//	
//	final class JavaScriptInterface{
//		JavaScriptInterface(){
//		}
//		 @JavascriptInterface
//		public void clickOnAndroid(){
//			handler.post(new Runnable() {
//				@Override
//				public void run() {
//					mWebView.loadUrl("javascript:wave()");
//				}
//			}) ;
//		}
//		
//	}
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event)
//	{
//		// TODO �Զ����ɵķ������
//		if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) { 
//			mWebView.goBack();
//            return true; 
//        } 
//		return super.onKeyDown(keyCode, event);
//	}
//}
