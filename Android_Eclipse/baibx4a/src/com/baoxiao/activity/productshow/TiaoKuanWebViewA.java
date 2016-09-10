package com.baoxiao.activity.productshow;

import com.baoxiao.R;
import com.baoxiao.activity.productshow.hushenfu.HuShenFuTiaoKuanA;
import com.baoxiao.activity.productshow.pinganfu.PingAnFuTiaoKuanA;
import com.baoxiao.activity.productshow.shouhux.ShouHuXTiaoKuanA;
import com.baoxiao.activity.productshow.xinli.XinLiTiaoKuanA;
import com.baoxiao.activity.productshow.xinsheng.XinShengTiaoKuanA;
import com.baoxiao.activity.productshow.xinxiang.XinXiangTiaoKuanA;
import com.baoxiao.activity.productshow.yingjuys.YingJuYiShengTiaoKuanA;
import com.baoxiao.activity.productshow.zhinengxing.ZhiNengXingTiaoKuanA;
import com.baoxiao.util.Define;

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
import android.widget.Toast;
import android.widget.ViewFlipper;

public class TiaoKuanWebViewA extends Activity implements OnGestureListener,
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
		tiaokuan = bundle.getString("tiaokuan");
		
		
		
		flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper01);
		webView = (WebView) findViewById(R.id.webview1);

		detector = new GestureDetector(this);

		// 设置webview的滑动事件
		webView.setOnTouchListener(this);
		webView.setLongClickable(true);

		WebSettings webSettings = webView.getSettings();

		// 设置加载进来的页面自适应屏幕
//		sttings.setLoadWithOverviewMode(true);
		webView.loadUrl(getUrl(i));
//		getUrl(i);
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
				if(tiaokuan.equals("shouhux")){
					Intent intent = new Intent(this, ShouHuXTiaoKuanA.class);
					startActivity(intent);
				}else if(tiaokuan.equals("hushenfu")){
					Intent intent = new Intent(this, HuShenFuTiaoKuanA.class);
					startActivity(intent);
				}
				else if(tiaokuan.equals("pinganfu")){
					Intent intent = new Intent(this, PingAnFuTiaoKuanA.class);
					startActivity(intent);
				}
				else if(tiaokuan.equals("xinli")){
					Intent intent = new Intent(this, XinLiTiaoKuanA.class);
					startActivity(intent);
				}
				else if(tiaokuan.equals("xinxiang")){
					Intent intent = new Intent(this, XinXiangTiaoKuanA.class);
					startActivity(intent);
				}
				else if(tiaokuan.equals("xinsheng")){
					Intent intent = new Intent(this, XinShengTiaoKuanA.class);
					startActivity(intent);
				}
				else if(tiaokuan.equals("yingjuyisheng")){
					Intent intent = new Intent(this, YingJuYiShengTiaoKuanA.class);
					startActivity(intent);
				}
				else if(tiaokuan.equals("zhinengxing")){
					Intent intent = new Intent(this, ZhiNengXingTiaoKuanA.class);
					startActivity(intent);
				}
				else{}
				i = maxPageCount;
				finish();
//				Toast.makeText(this, "已经是最后一页", Toast.LENGTH_SHORT).show();
			} else {
				webView.loadUrl(getUrl(i));
				this.flipper.startAnimation(AnimationUtils.loadAnimation(this,
						R.anim.in_from_right));
//				this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
//						R.anim.in_from_left));
//				Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();
			}
			return true;
		} else if (e2.getX() - e1.getX() > verticalMinDistance
				&& Math.abs(velocityX) > minVelocity) {
			this.flipper.startAnimation(AnimationUtils.loadAnimation(this,
					R.anim.in_from_left));
//			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
//					R.anim.in_from_right));
			i--;
			if (i < minPageCount) {
				i = minPageCount;
				Toast.makeText(this, "已经是首页", Toast.LENGTH_SHORT).show();
			} else {
				webView.loadUrl(getUrl(i));
//				Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
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
//		http://192.168.1.203:8080/baibx/imagePage.action?url=
//			product/shijitianshi/jianjie/baoxianlinian8/1.jpg
//		    product/shijitianshi/jianjie/baoxianlinian
		
		return url;
	}

	@Override
	protected void onResume() {
		if(getIntent().getExtras().getString("screenFlag").equals("jianjie")){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}else{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			
		}
		super.onResume();
	}
	
	
}
