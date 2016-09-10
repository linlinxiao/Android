package com.baoxiao.activity.invitation;

import java.io.File;

import com.baoxiao.R;
import com.baoxiao.service.study.CollectionService;
import com.baoxiao.util.CustomProgressDialog;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;
import com.baoxiao.util.ShareService;
import com.umeng.socialize.media.UMImage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.widget.TextView;

/**
 * ���뺯Ԥ��
 *
 */
public class PreviewA extends Activity implements OnClickListener
{
	protected static final int ONPAGEFINISHED = 0;
	private ImageView collection,share;
	private WebView webView;
	private String url,urlPath,load;
	private MessageHelper m;
	private CollectionService cs;
	private ProgressDialog cpd;
	
	@Override
	protected void onPause(){
		webView.onPause();
	    super.onPause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.news_articleinfo);
		cpd = new CustomProgressDialog(this, "����ƴ��������...",R.anim.frame_anim);
		collection = (ImageView) findViewById(R.id.collection);
		collection.setVisibility(View.GONE);
		share = (ImageView) findViewById(R.id.share);
		webView = (WebView) findViewById(R.id.newsConsult);

		String userid = getIntent().getStringExtra("userid");
		int hidden = getIntent().getIntExtra("hidden",1);
		url = Define.Server + "invitation2Show.action?userid=" + userid + "&hidden=" + hidden;
		
		((TextView)findViewById(R.id.tv_title)).setText("���뺯Ԥ��");
		urlPath = getIntent().getStringExtra("urlPath");
		
		cs = new CollectionService();

		webView.setLongClickable(true);
		collection.setOnClickListener(this);
		share.setOnClickListener(this);

		WebSettings webSettings = webView.getSettings();

		// ���ü��ؽ�����ҳ������Ӧ��Ļ
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);
		cpd.show();
		clearWebViewCache();
		webView.loadUrl(url);
		
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //��д�˷������������ҳ��������ӻ����ڵ�ǰ��webview����ת��������������Ǳ�
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
				cpd.dismiss();
				super.onPageFinished(view, url);
			}
		});
	}

	private Handler handler = new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case ONPAGEFINISHED:
				webView.loadUrl("javascript:(function() { var audios = document.getElementsByTagName('audio'); for(var i=0;i<audios.length;i++){audios[i].play();}})()");
				webView.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}})()");
				break;

			default:
				break;
			}
			return false;
		}
	});
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.share:
			SharedPreferences isActive = getSharedPreferences("isActive", Activity.MODE_PRIVATE);
			String result = isActive.getString("result", null);
			if (result == null) {return;}
			String message = isActive.getString("message", null);
			if ("2".equals(result)) {
				new AlertDialog.Builder(this).setTitle("��ʾ").setMessage(message)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						share();
					}
				})
				.show();
				return;
			}
			if ("1".equals(result)) {
				share();
				return;
			}
			new AlertDialog.Builder(this).setTitle("��ʾ").setMessage(message)
			.setPositiveButton("ȷ��", null).show();
			break;
		default:
			break;
		}
	}
	

	private void share() {
		UMImage image = new UMImage(PreviewA.this, R.drawable.bbx_icon);
		String name = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("name", "");
		ShareService.startShareWithImage(image,url,"���ã�����" + name +"����ϵ��������μ��ҹ�˾���ŲŴ�ҵ˵���ᣡ", PreviewA.this,"ƽ���Ųżƻ����뺯");		
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			webView.loadUrl("about:blank");
			finish();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	};
    /** 
    * ���WebView���� 
    */  
   public void clearWebViewCache(){
         
       //����Webview�������ݿ�  
       try {  
           deleteDatabase("webview.db");
           deleteDatabase("webviewCache.db");
       } catch (Exception e) {
           e.printStackTrace();
       }
       //WebView �����ļ�  
       File appCacheDir = new File(getFilesDir().getAbsolutePath()+"/webcache");  
//       Log.e(TAG, "appCacheDir path="+appCacheDir.getAbsolutePath());  
       File webviewCacheDir = new File(getCacheDir().getAbsolutePath()+"/webviewCache");  
//       Log.e(TAG, "webviewCacheDir path="+webviewCacheDir.getAbsolutePath());  
       //ɾ��webview ����Ŀ¼  
       if(webviewCacheDir.exists()){  
           deleteFile(webviewCacheDir);  
       }  
       //ɾ��webview ���� ����Ŀ¼  
       if(appCacheDir.exists()){  
           deleteFile(appCacheDir);  
       }  
   }  
     
   /**
    * �ݹ�ɾ�� �ļ�/�ļ���
    *
    * @param file
    */
   public void deleteFile(File file) {  
//       Log.i(TAG, "delete file path=" + file.getAbsolutePath());  
       if (file.exists()) {  
           if (file.isFile()) {  
               file.delete();  
           } else if (file.isDirectory()) {  
               File files[] = file.listFiles();  
               for (int i = 0; i < files.length; i++) {  
                   deleteFile(files[i]);  
               }
           }
           file.delete();
       } else {
//           Log.e(TAG, "delete file no exists " + file.getAbsolutePath());  
       }
   }
   public void news_back(View v){
	   webView.loadUrl("about:blank");
	   finish();
   }
}