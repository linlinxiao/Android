package com.baoxiao.test.activity.webview;

import java.util.ArrayList;

import com.baoxiao.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

/**
 * 
 * @author YeChao
 * @功能描述：主程序入口类
 */
public class MainActivity extends Activity implements OnTouchListener{
    // 定义ViewPager对象
    private ViewPager viewPager;
    // 定义ViewPager适配器
    private ViewPagerAdapter vpAdapter;
    // 定义一个ArrayList来存放View
    private ArrayList<View> views;
    // 记录当前选中位置
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_viewpager);
        initView();
        initData();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        // 实例化ArrayList对象
        views = new ArrayList<View>();
        // 实例化ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        // 实例化ViewPager适配器
        vpAdapter = new ViewPagerAdapter(views);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 定义一个布局并设置参数
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        // 初始化引导图片列表
        for (int i = 0; i < 11; i++) {
            WebView webView = new WebView(this);
            webView.setLayoutParams(mParams);
         // 设置webview的滑动事件
    		webView.setOnTouchListener(this);
    		webView.setLongClickable(true);

    		WebSettings webSettings = webView.getSettings();

    		// 设置加载进来的页面自适应屏幕
    		webSettings.setUseWideViewPort(true);
    		webSettings.setLoadWithOverviewMode(true);
    		webView.loadUrl(getUrl(i));
            //防止图片不能填满屏幕
//            iv.setScaleType(ScaleType.FIT_XY);
//            //加载图片资源
//            iv.setImageResource(pics[i]);
            views.add(webView);
        }

        // 设置数据
        viewPager.setAdapter(vpAdapter);

    }


	// 获取url的方法
		public String getUrl(int x) {
			String url = "";
			url = "http://192.168.1.101:8080/web/product/shouhux/guanniandr/" + x + ".html";
//			url = "http://192.168.1.201:8080/baibx/allnews.action";

			return url;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			return false;
		}

}

