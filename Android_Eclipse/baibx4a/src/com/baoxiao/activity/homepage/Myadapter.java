package com.baoxiao.activity.homepage;

import java.util.List;

import com.baoxiao.R;
import com.baoxiao.activity.homepage.AsyncImageLoader.ImageCallback;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class Myadapter extends BaseAdapter {
	 private AsyncImageLoader asyncImageLoader;
	List<String> data;
	Context context;
	ImageView iv;
	 private ListView listView;

	public Myadapter(Context context, List<String> list,ListView listView) {
		this.context = context;
		this.data = list;
		asyncImageLoader = new AsyncImageLoader();
		this.listView=listView;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position,View convertView,ViewGroup parent) {
		iv = new ImageView(context);
		String url=data.get(position).toString();
		iv.setTag(url);
		
        Drawable cachedImage = asyncImageLoader.loadDrawable(url, new ImageCallback() {
            public void imageLoaded(Drawable imageDrawable, String imageUrl) {
                ImageView imageViewByTag = (ImageView) listView.findViewWithTag(imageUrl);
                if (imageViewByTag != null) {
                    imageViewByTag.setImageDrawable(imageDrawable);
                }
            }
        });
		if (cachedImage == null) {
			iv.setImageResource(R.drawable.ic_launcher);
		}else{
			iv.setImageDrawable(cachedImage);
		}
		return iv;
	}

}