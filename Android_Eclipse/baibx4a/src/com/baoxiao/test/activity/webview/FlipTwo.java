package com.baoxiao.test.activity.webview;

import android.app.Activity;
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
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.baoxiao.R;

public class FlipTwo extends Activity implements OnGestureListener,
		OnTouchListener {

	private GestureDetector detector;
	private ViewFlipper flipper;
	private WebView webView;
	private int i = 0;

	// private String url = "http://192.168.1.103:8080/1/index1.html";
	// private String url2 = "http://192.168.1.103:8080/1/index2.html";
	// private String url3 = "http://192.168.1.103:8080/1/index3.html";
	// private String url4 = "http://192.168.1.103:8080";

	private String url;

	// 1.删除了不必要的代码。
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.ps_shouhux_guanniandr);

		flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper01);
		webView = (WebView) findViewById(R.id.webview1);

		detector = new GestureDetector(this);

		url = "http://192.168.1.103:8080/1/index" + i + ".html";

		// 设置webview的滑动事件
		webView.setOnTouchListener(this);
		webView.setLongClickable(true);

		WebSettings webSettings = webView.getSettings();

		// 设置加载进来的页面自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webView.loadUrl(url);
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
		// TODO Auto-generated method stub
		Log.i("Fling", "Fling Happened!");
		if (e1.getX() - e2.getX() > verticalMinDistance
				&& Math.abs(velocityX) > minVelocity) {
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.in_from_right));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.in_from_left));
			webView.loadUrl(url);
			// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();
			i--;
			return true;
		} else if (e2.getX() - e1.getX() > verticalMinDistance
				&& Math.abs(velocityX) > minVelocity) {
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.in_from_left));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.in_from_right));
			webView.loadUrl(url);
			// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
			return true;
		}
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

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
}
