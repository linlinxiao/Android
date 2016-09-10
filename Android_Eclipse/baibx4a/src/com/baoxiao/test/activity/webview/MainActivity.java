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
 * @���������������������
 */
public class MainActivity extends Activity implements OnTouchListener{
    // ����ViewPager����
    private ViewPager viewPager;
    // ����ViewPager������
    private ViewPagerAdapter vpAdapter;
    // ����һ��ArrayList�����View
    private ArrayList<View> views;
    // ��¼��ǰѡ��λ��
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_viewpager);
        initView();
        initData();
    }

    /**
     * ��ʼ�����
     */
    private void initView() {
        // ʵ����ArrayList����
        views = new ArrayList<View>();
        // ʵ����ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        // ʵ����ViewPager������
        vpAdapter = new ViewPagerAdapter(views);
    }

    /**
     * ��ʼ������
     */
    private void initData() {
        // ����һ�����ֲ����ò���
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        // ��ʼ������ͼƬ�б�
        for (int i = 0; i < 11; i++) {
            WebView webView = new WebView(this);
            webView.setLayoutParams(mParams);
         // ����webview�Ļ����¼�
    		webView.setOnTouchListener(this);
    		webView.setLongClickable(true);

    		WebSettings webSettings = webView.getSettings();

    		// ���ü��ؽ�����ҳ������Ӧ��Ļ
    		webSettings.setUseWideViewPort(true);
    		webSettings.setLoadWithOverviewMode(true);
    		webView.loadUrl(getUrl(i));
            //��ֹͼƬ����������Ļ
//            iv.setScaleType(ScaleType.FIT_XY);
//            //����ͼƬ��Դ
//            iv.setImageResource(pics[i]);
            views.add(webView);
        }

        // ��������
        viewPager.setAdapter(vpAdapter);

    }


	// ��ȡurl�ķ���
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

