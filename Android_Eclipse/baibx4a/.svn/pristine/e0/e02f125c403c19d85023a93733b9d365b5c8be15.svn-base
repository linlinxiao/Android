package com.baoxiao.activity.productshow.yingjuys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.baoxiao.R;
import com.baoxiao.activity.productshow.ImgAnimationA;

public class YingJuYiShengTiaoKuanA extends Activity implements OnClickListener{
	private Button shouHuX,shaoEr;
	private ImageView back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ps_yingjuyisheng_tiaokuan);
		
		shouHuX = (Button) findViewById(R.id.shouHuX);
		shaoEr = (Button) findViewById(R.id.shaoEr);
		back = (ImageView) findViewById(R.id.back);
		
		shouHuX.setOnClickListener(this);
		shaoEr.setOnClickListener(this);
		back.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shouHuX:
			Intent intent = new Intent(this, ImgAnimationA.class);
			Bundle b = new Bundle();
			b.putInt("maxPageCount", 9);
			b.putString("url", "product/xinli/yanshi/tiaokuan/xinli");
			b.putString("format", "jpg");
			b.putString("screenFlag","jianjie");
			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.shaoEr:
			Intent intent2 = new Intent(this, ImgAnimationA.class);
			Bundle b2 = new Bundle();
			b2.putInt("maxPageCount", 12);
			b2.putString("url", "product/xinli/yanshi/tiaokuan/xinlizhongji");
			b2.putString("format", "jpg");
			b2.putString("screenFlag","jianjie");
			intent2.putExtras(b2);
			startActivity(intent2);
			break;
		case R.id.back:
			finish();
			break;

		default:
			break;
		}
	}

	
}
