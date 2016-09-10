package com.baoxiao.activity.productshow.hushenfu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.baoxiao.R;
import com.baoxiao.activity.productshow.ImgAnimationA;

public class HuShenFuTiaoKuanA extends Activity implements OnClickListener{
	private Button shouHuX,shaoEr,huoMianZhongJi,changQiYiWai;
	private ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ps_hushenfu_tiaokuan);
		
		shouHuX = (Button) findViewById(R.id.shouHuX);
		shaoEr = (Button) findViewById(R.id.shaoEr);
		huoMianZhongJi = (Button) findViewById(R.id.huoMianZhongJi);
		changQiYiWai = (Button) findViewById(R.id.changQiYiWai);
		back = (ImageView) findViewById(R.id.back);
		
		shouHuX.setOnClickListener(this);
		shaoEr.setOnClickListener(this);
		huoMianZhongJi.setOnClickListener(this);
		changQiYiWai.setOnClickListener(this);
		back.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shouHuX:
			Intent intent = new Intent(this, ImgAnimationA.class);
			Bundle b = new Bundle();
			b.putInt("maxPageCount", 8);
			b.putString("url", "product/hushenfu/yanshi/tiaokuan/hsf");
			b.putString("format", "jpg");
			b.putString("screenFlag","jianjie");
			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.shaoEr:
			Intent intent2 = new Intent(this, ImgAnimationA.class);
			Bundle b2 = new Bundle();
			b2.putInt("maxPageCount", 16);
			b2.putString("url", "product/hushenfu/yanshi/tiaokuan/hsfzj");
			b2.putString("format", "jpg");
			b2.putString("screenFlag","jianjie");
			intent2.putExtras(b2);
			startActivity(intent2);
			break;
		case R.id.huoMianZhongJi:
			Intent intent3 = new Intent(this, ImgAnimationA.class);
			Bundle b3 = new Bundle();
			b3.putInt("maxPageCount", 15);
			b3.putString("url", "product/hushenfu/yanshi/tiaokuan/zjhm");
			b3.putString("format", "jpg");
			b3.putString("screenFlag","jianjie");
			intent3.putExtras(b3);
			startActivity(intent3);
			break;
		case R.id.changQiYiWai:
			Intent intent4 = new Intent(this, ImgAnimationA.class);
			Bundle b4 = new Bundle();
			b4.putInt("maxPageCount", 20);
			b4.putString("url", "product/hushenfu/yanshi/tiaokuan/cqyw");
			b4.putString("format", "jpg");
			b4.putString("screenFlag","jianjie");
			intent4.putExtras(b4);
			startActivity(intent4);
			break;
		case R.id.back:
			finish();
			break;

		default:
			break;
		}
	}

	
}
