package com.baoxiao.activity.productshow.xinxiang;

import com.baoxiao.R;
import com.baoxiao.activity.productshow.ImgAnimationA;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class XinXiangTiaoKuanA extends Activity implements OnClickListener{
	private Button shouHuX,shaoEr,huoMianZhongJi;
	private ImageView back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ps_xinxiang_tiaokuan);
		
		shouHuX = (Button) findViewById(R.id.shouHuX);
		shaoEr = (Button) findViewById(R.id.shaoEr);
		huoMianZhongJi = (Button) findViewById(R.id.huoMianZhongJi);
		back = (ImageView) findViewById(R.id.back);
		
		shouHuX.setOnClickListener(this);
		shaoEr.setOnClickListener(this);
		huoMianZhongJi.setOnClickListener(this);
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
		case R.id.huoMianZhongJi:
			Intent intent3 = new Intent(this, ImgAnimationA.class);
			Bundle b3 = new Bundle();
			b3.putInt("maxPageCount", 12);
			b3.putString("url", "product/xinli/yanshi/tiaokuan/huomianzhongji");
			b3.putString("format", "jpg");
			b3.putString("screenFlag","jianjie");
			intent3.putExtras(b3);
			startActivity(intent3);
			break;
		case R.id.back:
//			Intent intent4 = new Intent(this, ShouHuXShowA.class);
//			startActivity(intent4);
			finish();
			break;

		default:
			break;
		}
	}

	
}
