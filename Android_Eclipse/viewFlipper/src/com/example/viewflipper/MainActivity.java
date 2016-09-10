package com.example.viewflipper;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends ActionBarActivity {

	private int[] images = { R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
			R.drawable.pic4 };
	private ViewFlipper flipper;
	float startX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		flipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
		for (int i = 0; i < images.length; i++) {
			flipper.addView(getImageView(images[i]));
		}
//		flipper.setInAnimation(this, R.anim.right_in);
//		flipper.setOutAnimation(this, R.anim.right_out);
//		flipper.setFlipInterval(3000);// 三秒自动切换
//		flipper.setAutoStart(true);
//		flipper.setOnTouchListener(this);
//		flipper.setFocusableInTouchMode(true);
	}

	private ImageView getImageView(int resId) {
		ImageView imageView = new ImageView(this);
		imageView.setBackgroundResource(resId);
		return imageView;
	}

	 @Override
	 public boolean onTouchEvent(MotionEvent event) {
	 // TODO Auto-generated method stub
		 if (event.getAction() == MotionEvent.ACTION_DOWN) {
				startX = event.getX();
			} else if (event.getAction() == MotionEvent.ACTION_MOVE
					&& event.getX() - startX > 100) {
				// 向右滑，滑动到上一张
				flipper.setInAnimation(this, R.anim.left_in);
				flipper.setOutAnimation(this, R.anim.left_out);
				flipper.showPrevious();
			} else if (event.getAction() == MotionEvent.ACTION_MOVE
					&&  startX - event.getX() > 100) {
				// 向右滑，滑动到上一张
				flipper.setInAnimation(this, R.anim.right_in);
				flipper.setOutAnimation(this, R.anim.right_out);
				flipper.showNext();
			}
	 return super.onTouchEvent(event);
	 }

//	@Override
//	public boolean onTouch(View v, MotionEvent event) {
//		// TODO Auto-generated method stub
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			startX = event.getX();
//		} else if (event.getAction() == MotionEvent.ACTION_MOVE
//				&& event.getX() - startX > 100) {
//			// 向右滑，滑动到上一张
//			flipper.setInAnimation(this, R.anim.left_in);
//			flipper.setOutAnimation(this, R.anim.left_out);
//			flipper.showPrevious();
//		} else if (event.getAction() == MotionEvent.ACTION_MOVE
//				&&  startX - event.getX() > 100) {
//			// 向右滑，滑动到上一张
//			flipper.setInAnimation(this, R.anim.right_in);
//			flipper.setOutAnimation(this, R.anim.right_out);
//			flipper.showNext();
//		}
//		return false;
//	}
}
