package com.baoxiao.activity.productshow.baiwanrwx;  
 
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
 
public class BwrwxChanpinshipin extends Activity {
	
	private final static int ONPAGEFINISHED = 0;
    private WebView webview;  
    @Override 
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        //ʵ����WebView����  
        webview = new WebView(this);  
        //����WebView���ԣ��ܹ�ִ��Javascript�ű�  
        webview.getSettings().setJavaScriptEnabled(true);  
        //������Ҫ��ʾ����ҳ  
        webview.loadUrl("http://v.youku.com/v_show/id_XMTM2MzYxOTg1Mg==.html");  
        //����Web��ͼ  
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(webview);  
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
//			@Override
//			public void onPageFinished(WebView view, String url)
//			{
//				new Thread(new Runnable() {
//					public void run() {
//						try {
//							Thread.sleep(500);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						handler.sendEmptyMessage(ONPAGEFINISHED);
//					}
//				}).start();
//				super.onPageFinished(view, url);
//			}
        });
    }  
//	private Handler handler = new Handler(new Handler.Callback() {
//		
//		@Override
//		public boolean handleMessage(Message msg) {
//			switch (msg.what) {
//			case ONPAGEFINISHED:
//				webview.loadUrl("javascript:(function() { var audios = document.getElementsByTagName('audio'); for(var i=0;i<audios.length;i++){audios[i].play();}})()");
//				webview.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}})()");
//				break;
//
//			default:
//				break;
//			}
//			return false;
//		}
//	});

    @Override 
    //���û���  
    //����Activity���onKeyDown(int keyCoder,KeyEvent event)����  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {  
            webview.goBack(); //goBack()��ʾ����WebView����һҳ�� 
            finish();
        }
        return false;  
    }
    

}