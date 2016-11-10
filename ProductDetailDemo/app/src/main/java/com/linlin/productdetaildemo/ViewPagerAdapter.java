package com.linlin.productdetaildemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.ArrayList;


/**
 * Created by linlin on 9/14/16.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewList = new ArrayList<>();
    private String[] titleList = {"商品详情", "商品评价"};
    private Context context;
    private WebView webView1;
    private WebView webView2;
    private int productId = 0;
    private String loadUrl = "http://2.3.1.ysctest.kuaidiantong.cn/appshop/ProductDetails?productId=";

    public ViewPagerAdapter(Context context, int productId) {
        this.productId = productId;
        this.context = context;
        webView1 = (WebView) LayoutInflater.from(context).inflate(R.layout.webview, null);
        webView1.setOnTouchListener((MainActivity) context);
//        webView1.setWebViewClient(new WebViewClient(){
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//            }
//        });
        viewList.add(webView1);

        webView2 = (WebView) LayoutInflater.from(context).inflate(R.layout.webview, null);
        webView1.setOnTouchListener((MainActivity) context);
        viewList.add(webView2);


        loadData();
    }

    private void loadData() {
        String fullUrl = productId == 0 ? loadUrl + "51" : loadUrl + productId;
        webView1.loadUrl(fullUrl);
        webView2.loadUrl(fullUrl);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {

        return arg0 == arg1;
    }

    @Override
    public int getCount() {

        return viewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView(viewList.get(position));

    }

    @Override
    public int getItemPosition(Object object) {

        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titleList[position];//直接用适配器来完成标题的显示，所以从上面可以看到，我们没有使用PagerTitleStrip。当然你可以使用。
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }


}
