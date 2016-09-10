package com.baoxiao.activity.mine;

import com.baoxiao.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


public class ShareA extends Activity implements OnClickListener{

	private ImageView mineBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	    setContentView(R.layout.mine_share);
	    
	    initView();
	}


	private void initView() {
	
		mineBack = (ImageView) findViewById(R.id.mineBack);
		mineBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mineBack:
			finish();
			break;
		default:
			break;
		}
	}
}
