package com.example.galleryandimageswitcher;

import android.R.integer;
import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends BaseAdapter{
	private int[] images;
	private Context context;
	
	public ImageAdapter(int[] imageList, Context ctx){
		images = imageList;
		context = ctx;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
//		return images.length;
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(context);
		imageView.setBackgroundResource(images[position%images.length]);
		imageView.setLayoutParams(new Gallery.LayoutParams(200, 150));
		imageView.setScaleType(ScaleType.FIT_XY);
		return imageView;
	}

}
