package com.baoxiao.test.activity.webview;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.baoxiao.R;
import com.baoxiao.util.Define;

public class Flip extends Activity implements OnGestureListener,
		OnTouchListener {

	private GestureDetector detector;
	private ViewFlipper flipper;
	private WebView webView;
	private int i = 1;
	private int maxPageCount;
	private int minPageCount = 1;
	private String urlStr = "",format = "",screenFlag = "",tiaokuan = "";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.ps_shouhux_guanniandr);


		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		maxPageCount = bundle.getInt("maxPageCount");
		urlStr = bundle.getString("url");
		format = bundle.getString("format");
		
		flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper01);
		webView = (WebView) findViewById(R.id.webview1);

		detector = new GestureDetector(this);

		// 设置webview的滑动事件
		webView.setOnTouchListener(this);
		webView.setLongClickable(true);

		WebSettings webSettings = webView.getSettings();

		webView.loadUrl(getUrl(i));
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                    view.loadUrl(url);
                    return true;
            }
		});
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i("Fling", "Activity onTouchEvent!");
		return this.detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	private int verticalMinDistance = 20;
	private int minVelocity = 0;

	/**
	 * 监听滑动
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Log.i("Fling", "Fling Happened!");
		if (e1.getX() - e2.getX() > verticalMinDistance
				&& Math.abs(velocityX) > minVelocity) {
			i++;
			if (i > maxPageCount) {
				//如果继续再往下滑动页面，将返回界面
				i = maxPageCount;
				finish();
//				Toast.makeText(this, "已经是最后一页", Toast.LENGTH_SHORT).show();
			} else {
				//解除webview加载闪屏问题
				webView.loadUrl(getUrl(i));
				webView.setWebViewClient(new WebViewClient(){
					public boolean shouldOverrideUrlLoading(WebView view, String url)
		            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
		                    view.loadUrl(url);
		                    return true;
		            }
				});
//				this.flipper.startAnimation(AnimationUtils.loadAnimation(this,
//						R.anim.in_from_right));
				this.flipper.startAnimation(AnimationUtils.loadAnimation(this,
						R.anim.ps_shouhuxshow_anim_scale_big));
			}
			return true;
		} else if (e2.getX() - e1.getX() > verticalMinDistance
				&& Math.abs(velocityX) > minVelocity) {
//			this.flipper.startAnimation(AnimationUtils.loadAnimation(this,
//					R.anim.in_from_left));
			i--;
			if (i < minPageCount) {
				i = minPageCount;
				Toast.makeText(this, "已经是首页", Toast.LENGTH_SHORT).show();
			} else {
				webView.loadUrl(getUrl(i));
				webView.setWebViewClient(new WebViewClient(){
					public boolean shouldOverrideUrlLoading(WebView view, String url)
		            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
		                    view.loadUrl(url);
		                    return true;
		            }
				});
				this.flipper.startAnimation(AnimationUtils.loadAnimation(this,
						R.anim.ps_shouhuxshow_anim_scale_big));
			}
			return true;
		}
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}

	// 获取url的方法
	public String getUrl(int x) {
		String url = "";
			url = Define.Server+"imagePage.action?url="+urlStr+maxPageCount+"/"+x+"."+format;
		return url;
	}

	/**
	 * 在进入webview之前判断是横屏还是竖屏
	 */
	@Override
	protected void onResume() {
		if(getIntent().getExtras().getString("screenFlag").equals("jianjie")){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}else{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			
		}
		super.onResume();
	}
	
	/**
	 * 处理webview闪屏的方法
	 */
	
	
}
