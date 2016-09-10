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
        //实例化WebView对象  
        webview = new WebView(this);  
        //设置WebView属性，能够执行Javascript脚本  
        webview.getSettings().setJavaScriptEnabled(true);  
        //加载需要显示的网页  
        webview.loadUrl("http://v.youku.com/v_show/id_XMTM2MzYxOTg1Mg==.html");  
        //设置Web视图  
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
    //设置回退  
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {  
            webview.goBack(); //goBack()表示返回WebView的上一页面 
            finish();
        }
        return false;  
    }
    

}