package com.baoxiao.activity.productshow;

import java.util.List;

import com.baoxiao.R;
import com.baoxiao.util.AsyncImageLoader;
import com.baoxiao.util.AsyncImageLoader.ImageCallback;
import com.baoxiao.view.ViewUtil;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 适配器，负责装配 、销毁 数据 和 组件 。
 */
public class MyAdapter extends PagerAdapter
{
	private List<View> mList;
	private AsyncImageLoader asyncImageLoader;
	private String[] imgUrl;
	private ImageView image;
	private Context context;

	public MyAdapter(Context context,List<View> mList,String[] imgUrl)
	{
		super();
		this.context = context;
		this.mList = mList;
		this.imgUrl = imgUrl;
		this.asyncImageLoader = new AsyncImageLoader();
	}

	@Override
	public int getCount()
	{
		return mList.size();
	}

	/**
	 * 滑动过后就销毁 ，销毁当前页的前一个的前一个的页！
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object)
	{
		container.removeView(mList.get(position));
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1)
	{
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(final ViewGroup container,
			final int position)
	{
		Drawable cachedImage = asyncImageLoader.loadDrawable(
				imgUrl[position], new ImageCallback()
				{

					@Override
					public void imageLoaded(Drawable imageDrawable,
							String imageUrl)
					{
						View view = mList.get(position);
						image = (ImageView) view.findViewById(R.id.image);
//						image.setBackground(imageDrawable);
						ViewUtil.setBackground(image, context, imageDrawable);
						container.removeView(mList.get(position));
						container.addView(mList.get(position));
					}
				});
		View view = mList.get(position);
		image = (ImageView) view.findViewById(R.id.image);
//		image.setBackground(cachedImage);
		ViewUtil.setBackground(image, context, cachedImage);
		container.removeView(mList.get(position));
		container.addView(mList.get(position));
		return mList.get(position);
	}
}