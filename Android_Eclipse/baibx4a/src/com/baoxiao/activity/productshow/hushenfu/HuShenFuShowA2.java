package com.baoxiao.activity.productshow.hushenfu;


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

public class HuShenFuShowA2 extends Activity {
	protected static final int ONPAGEFINISHED = 0;
	private FastWebView webView;
	private String home_url,url;
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
//		findViewById(R.id.concern).setVisibility(View.VISIBLE);
		cpd = new CustomProgressDialog(this, "正在拼命加载中...",R.anim.frame_anim);
		webView = (FastWebView) findViewById(R.id.newsConsult);
//		WebSettings webSettings = webView.getSettings();
//		// 设置加载进来的页面自适应屏幕
////		webSettings.setUseWideViewPort(true);
////		webSettings.setLoadWithOverviewMode(true);
//		webSettings.setJavaScriptEnabled(true);
		cpd.show();

		//需要访问的网址
		String data = getIntent().getStringExtra("data");
		home_url = Define.Server + "talismanShow.action?" + data;
		url = home_url;
		
		webView.loadUrl(home_url);
		WebSettings s = webView.getSettings();
		s.setLoadWithOverviewMode(true);
		s.setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
	           if (url.startsWith("tel:")){
	        	   String phone = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
	        	   Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:"+phone));
                   startActivity(intent);
    	           return true;
	           }

	           if (url.contains("rabbitpre") || url.contains("youku.com") || url.contains("qq.com")
	        		   || url.contains("sohu.com") || url.contains("mycard.action")) {
	        	   webView.setIs_active(false);
	           }
	           String shareName = null;
	           int imageId = -1;
	           startActivity(new Intent(HuShenFuShowA2.this,WebShowA2.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", imageId));
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
		if (url.contains(Define.Server + "peaceShow.action?")) {
			UMImage image = new UMImage(HuShenFuShowA2.this,R.drawable.peace_share_icon);
//			String age = getAge("age");
			//您好！我是xxx,这是我为您设计的计划书，请您查看！
//		    String name = getSharedPreferences("mycard", Activity.MODE_PRIVATE).getString("p1t1", null);
		    String name = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("name", null);
		    String content = "您好！我是" + name + ",这是我为您设计的平安福计划书，请您查看！";
			ShareService.startShareWithImage(image,url,content,HuShenFuShowA2.this,"平安福计划书");
		}
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
		webView.setIs_active(false);
	    LayoutInflater inflater = (LayoutInflater) getSystemService(HuShenFuShowA2.LAYOUT_INFLATER_SERVICE);
	    View view = inflater.inflate(R.layout.popwindow_peace1, null);
//	    1、越早办理，好处越多（完成）
//	    对应《参保提示》
//	    http://www.rabbitpre.com/m/IReMuMQew#
//
//	    2、理财优势（完成）
//	    对应《WS11_保险的理财优势》
//	    http://www.rabbitpre.com/m/UJBYvMQIa
//
//	    3、责任免除（完成）
//	    对应《WS08_责任免除》
//	    http://www.rabbitpre.com/m/ymqeuuw
//
//
//	    4、保险的犹豫期（完成）
//	    对应《WS05_保单的犹豫期(可爱版)》
//	    http://www.rabbitpre.com/m/NeMuqEvEf#
//
//	    问题解答
//	    5、死了都要爱，这就是保险！（设计中）
//	    6、保险理赔难吗？（设计中）
//	    对应《平安的理赔服务》
//	    http://www.rabbitpre.com/m/In7YRmFZk
//
//	    7、重疾保障的疾病太可怕了。（完成）
//	    对应《WS03_重疾保障的疾病很可怕》
//	    http://www.rabbitpre.com/m/jEFvIja
//
//	    8、有钱人需要买保险吗？（完成）
//	    对应《WS09_有钱人需不需要买保险？》
//	    http://www.rabbitpre.com/m/i7mbEZZ3e
//
//	    9、为什么保险的交费期那么长？（完成）
//	    对应《WS10_保单的缴费期为什么总是很长》
//	    http://www.rabbitpre.com/m/zzrEBmjjd
//
//	    10、保险的理赔案例（完成）【在学习园地的异议处理】
//	    对应《x12-江先生脑出血申请2万理赔金保险公司却赔52万》
//	    http://www.rabbitpre.com/m/MIBIBYBAn
	    
	    view.findViewById(R.id.ll_peace_pop_item1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/IReMuMQew#";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item1)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon0;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/UJBYvMQIa";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item2)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon1;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item3).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/ymqeuuw";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item3)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon2;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item4).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/NeMuqEvEf#";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item4)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon3;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item5).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/Auq6QQYRg#";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item5)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon4;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item6).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/In7YRmFZk";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item6)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon5;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item7).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/jEFvIja";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item7)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon6;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item8).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/i7mbEZZ3e";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item8)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon7;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item9).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/zzrEBmjjd";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item9)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon8;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item10).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/MIBIBYBAn";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item10)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon9;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item11).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/Ruy7mVBmd#";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item11)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon10;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item12).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/VRuqqyzbt#";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item12)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon11;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item13).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/6Rnamyc#";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item13)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon12;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("imageId", title_image_id));
			}
		});
	    view.findViewById(R.id.ll_peace_pop_item14).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	        	window.dismiss();
	    		String url = "http://www.rabbitpre.com/m/jNQYZ3FZv#";
	    		String shareName = ((TextView)((LinearLayout)v)
	    				.findViewById(R.id.tv_peace_pop_item14)).getText().toString();
	    		int title_image_id = R.drawable.pop_peace_icon13;
	    		startActivity(new Intent(HuShenFuShowA2.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("shareName", shareName)
						.putExtra("title_image_id", title_image_id));
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
