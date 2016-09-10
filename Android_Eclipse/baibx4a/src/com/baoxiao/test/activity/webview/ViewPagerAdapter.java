package com.baoxiao.test.activity.webview;

import java.util.ArrayList;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerAdapter extends PagerAdapter {
    //界面列表
	private ArrayList<View> views;
	public ViewPagerAdapter(ArrayList<View> views){
		this.views = views;
	}
	/**
	 * 获得当前界面数
	 */
	@Override
	public int getCount() {
		if(views != null){
			return views.size();
		}
		else return 0;
	}

	/**
	 * 判断是否由对象生成界面
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return (arg0 == arg1);
	}
	
	/**
	 * 销毁position位置的界面
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(views.get(position));
	}
	
	/**
	 * 初始化positionjiemian位置的界面
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(views.get(position), 0);
		return views.get(position);
	}
	
	


}
