package com.example.galleryandimageswitcher;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;


public class MainActivity extends ActionBarActivity implements ViewFactory, OnItemSelectedListener {
	private Gallery gallery;
	private ImageSwitcher imgSwitcher;
	private int[] images = {R.drawable.item1,R.drawable.item2,R.drawable.item3,R.drawable.item4,R.drawable.item5,R.drawable.item6,R.drawable.item7,R.drawable.item8,R.drawable.item9,R.drawable.item10,R.drawable.item11,R.drawable.item12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        gallery = (Gallery) findViewById(R.id.gallery1);
        gallery.setAdapter(new ImageAdapter(images, this));
        gallery.setSelection(5);
        gallery.setOnItemSelectedListener(this);
        
        
        imgSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
        imgSwitcher.setFactory(this);
        imgSwitcher.setInAnimation(new AnimationUtils().loadAnimation(this, android.R.anim.fade_in));
        imgSwitcher.setOutAnimation(new AnimationUtils().loadAnimation(this, android.R.anim.fade_out));
    }

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(this);
		imageView.setLayoutParams(new FrameLayout.LayoutParams(getWindow().getWindowManager().getDefaultDisplay().getWidth(), 300));
		imageView.setScaleType(ScaleType.FIT_CENTER);
		return imageView;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		imgSwitcher.setImageResource(images[position%images.length]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
