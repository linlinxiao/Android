package com.baoxiao.activity.productshow.childrenPeace;


import com.baoxiao.FastWebView;
import com.baoxiao.R;
import com.baoxiao.WebShowA;
import com.baoxiao.WebShowA2;
import com.baoxiao.util.CustomProgressDialog;
import com.baoxiao.util.Define;
import com.baoxiao.util.ShareService;
import com.umeng.socialize.media.UMImage;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ChildrenPingAnFuShowA extends Activity {
	protected static final int ONPAGEFINISHED = 0;
	private FastWebView webView;
	private String url;
	private ProgressDialog cpd;
	private PopupWindow window;
	
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

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fast_web_show);
		findViewById(R.id.concern).setVisibility(View.VISIBLE);
		findViewById(R.id.concern).setVisibility(View.GONE);
		cpd = new CustomProgressDialog(this, "����ƴ��������...",R.anim.frame_anim);
		webView = (FastWebView) findViewById(R.id.newsConsult);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		cpd.show();
		String data = getIntent().getStringExtra("postDate");
		url = Define.Server + "childrenPeaceShow.action?" + data;
		webView.loadUrl(url);
		
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url){
	            if (url.startsWith("tel:")){
	        	    String phone = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
	        	    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:"+phone));
                    startActivity(intent);
    	            return true;
	            }
				if (url.contains("rabbitpre") || url.contains("youku.com")
						|| url.contains("qq.com") || url.contains("sohu.com")
						|| url.contains("mycard.action")) {
					webView.setIs_active(false);
				}
	    		startActivity(new Intent(ChildrenPingAnFuShowA.this,WebShowA2.class)
	    				.putExtra("url", url));
				return true;
            }
			@Override
			public void onPageFinished(WebView view, String url){
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (!isFinishing()) {
							handler2.sendEmptyMessage(ONPAGEFINISHED);
						}
					}
				}).start();
				cpd.dismiss();
				super.onPageFinished(view, url);
			}
		});
	}
	private Handler handler2 = new Handler(new Handler.Callback() {
		
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
	
	public void share(View v) {
		UMImage image = new UMImage(ChildrenPingAnFuShowA.this,R.drawable.product_children_icon);
//		String age = getAge("age");
		//���ã�����xxx,������Ϊ����Ƶļƻ��飬�����鿴��
//	    String name = getSharedPreferences("mycard", Activity.MODE_PRIVATE).getString("p1t1", null);
	    String name = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("name", null);
		ShareService.startShareWithImage(image,url,"���ã�����" + name + ",������Ϊ����Ƶ��ٶ�ƽ�����ƻ��飬�����鿴��",
				ChildrenPingAnFuShowA.this,"�ٶ�ƽ�����ƻ���");
	}
	

//	private String getAge(String name) {
//		String age = "0";
//		if (postDate != null) {
//			String s[] = postDate.split("&" + name + "+");
//			if (s.length > 0) {
//				age = s[1].split("&")[0];
//			}
//		}
//		return age;
//	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			webView.setIs_active(false);
			finish();
		}
		return false;
	};
	
	public void news_back(View v){
		webView.setIs_active(false);
	   	finish();
	}
   
//   public void concern(View v){
//	   	concern_is = !concern_is;
//		if (concern_is) {
//			concern.setImageDrawable(getResources().getDrawable(R.drawable.concern_selected));
//		}else{
//			concern.setImageDrawable(getResources().getDrawable(R.drawable.concern_normal));
//		}
//   }
	public void concern(View v){
	    LayoutInflater inflater = (LayoutInflater) getSystemService(ChildrenPingAnFuA.LAYOUT_INFLATER_SERVICE);
	    View view = inflater.inflate(R.layout.popwindow_peace1, null);
	    
//	    1��Խ������ô�Խ�ࣨ��ɣ�
//	    ��Ӧ���α���ʾ��
//	    http://www.rabbitpre.com/m/IReMuMQew#
//
//	    2��������ƣ���ɣ�
//	    ��Ӧ��WS11_���յ�������ơ�
//	    http://www.rabbitpre.com/m/UJBYvMQIa
//
//	    3�������������ɣ�
//	    ��Ӧ��WS08_���������
//	    http://www.rabbitpre.com/m/ymqeuuw
//
//
//	    4�����յ���ԥ�ڣ���ɣ�
//	    ��Ӧ��WS05_��������ԥ��(�ɰ���)��
//	    http://www.rabbitpre.com/m/NeMuqEvEf#
//
//	    ������
//	    5�����˶�Ҫ��������Ǳ��գ�������У�
//	    6�������������𣿣�����У�
//	    ��Ӧ��ƽ�����������
//	    http://www.rabbitpre.com/m/In7YRmFZk
//
//	    7���ؼ����ϵļ���̫�����ˡ�����ɣ�
//	    ��Ӧ��WS03_�ؼ����ϵļ����ܿ��¡�
//	    http://www.rabbitpre.com/m/jEFvIja
//
//	    8����Ǯ����Ҫ�����𣿣���ɣ�
//	    ��Ӧ��WS09_��Ǯ���費��Ҫ���գ���
//	    http://www.rabbitpre.com/m/i7mbEZZ3e
//
//	    9��Ϊʲô���յĽ�������ô��������ɣ�
//	    ��Ӧ��WS10_�����Ľɷ���Ϊʲô���Ǻܳ���
//	    http://www.rabbitpre.com/m/zzrEBmjjd
//
//	    10�����յ����ⰸ������ɣ�����ѧϰ԰�ص����鴦��
//	    ��Ӧ��x12-�������Գ�Ѫ����2��������չ�˾ȴ��52��
//	    http://www.rabbitpre.com/m/MIBIBYBAn
	    
	    view.findViewById(R.id.ll_peace_pop_item1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/IReMuMQew#";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item1)).getText().toString();
	    		int imageId = R.drawable.pop_peace_icon0;
	    		startActivity(new Intent(ChildrenPingAnFuShowA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", imageId));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/UJBYvMQIa";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item2)).getText().toString();
	    		int imageId = R.drawable.pop_peace_icon1;
	    		startActivity(new Intent(ChildrenPingAnFuShowA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", imageId));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item3).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/ymqeuuw";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item3)).getText().toString();
	    		int imageId = R.drawable.pop_peace_icon2;
	    		startActivity(new Intent(ChildrenPingAnFuShowA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", imageId));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item4).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/NeMuqEvEf#";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item4)).getText().toString();
	    		int imageId = R.drawable.pop_peace_icon3;
	    		startActivity(new Intent(ChildrenPingAnFuShowA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", imageId));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item5).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/Auq6QQYRg#";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item5)).getText().toString();
	    		int imageId = R.drawable.pop_peace_icon4;
	    		startActivity(new Intent(ChildrenPingAnFuShowA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", imageId));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item6).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/In7YRmFZk";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item6)).getText().toString();
	    		int imageId = R.drawable.pop_peace_icon5;
	    		startActivity(new Intent(ChildrenPingAnFuShowA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", imageId));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item7).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/jEFvIja";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item7)).getText().toString();
	    		int imageId = R.drawable.pop_peace_icon6;
	    		startActivity(new Intent(ChildrenPingAnFuShowA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", imageId));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item8).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/i7mbEZZ3e";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item8)).getText().toString();
	    		int imageId = R.drawable.pop_peace_icon7;
	    		startActivity(new Intent(ChildrenPingAnFuShowA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", imageId));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item9).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/zzrEBmjjd";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item9)).getText().toString();
	    		int imageId = R.drawable.pop_peace_icon8;
	    		startActivity(new Intent(ChildrenPingAnFuShowA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", imageId));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item10).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/MIBIBYBAn";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item10)).getText().toString();
	    		int imageId = R.drawable.pop_peace_icon9;
	    		startActivity(new Intent(ChildrenPingAnFuShowA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", imageId));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item11).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/Ruy7mVBmd#";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item11)).getText().toString();
	    		int imageId = R.drawable.pop_peace_icon10;
	    		startActivity(new Intent(ChildrenPingAnFuShowA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", imageId));
			}
		});
	    
	    
	    
	    view.findViewById(R.id.ib_cancle).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
			}
		});
	    window = new PopupWindow(view,
		        WindowManager.LayoutParams.MATCH_PARENT,
		        WindowManager.LayoutParams.MATCH_PARENT);
	    window.setFocusable(true);
	    window.setAnimationStyle(R.style.mypopwindow_anim_style);
	    window.setOutsideTouchable(true);
        view.setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
                if (window.isShowing()){
                    window.dismiss();
                }
				return false;
			}
        });
        if (window.isShowing()){
        	window.dismiss();
        }
        else{
            window.showAtLocation(view, Gravity.CENTER, 0, 0);
        }

	}

}
