package com.baoxiao.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;

public class ViewUtil {
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static void setBackground(ImageView imageView,Context context,Drawable drawable){
	    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			imageView.setBackground(drawable);
	    } else {
	    	imageView.setBackgroundDrawable(drawable);  
	    }
	}
}