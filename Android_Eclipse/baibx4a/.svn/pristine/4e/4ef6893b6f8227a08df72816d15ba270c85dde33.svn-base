package com.baoxiao.activity.productshow.yingjuys;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.baoxiao.R;
import com.baoxiao.activity.productshow.ImgAnimationA;

public class YingJuYiShengA extends Activity implements OnClickListener{
	private TextView baoXianLiNianShaoEr,baoXianLiNianChengRen,
	touBaoGuiZe,chanPinTeSe,sheJiJiHuaShu;
	private ImageView xinLiJianJieBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//…Ë÷√ ˙∆¡
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.ps_yingjuys);
		
		baoXianLiNianShaoEr = (TextView) findViewById(R.id.baoXianLiNianShaoEr);
		baoXianLiNianChengRen = (TextView) findViewById(R.id.baoXianLiNianChengRen);
		touBaoGuiZe = (TextView) findViewById(R.id.touBaoGuiZe);
		chanPinTeSe = (TextView) findViewById(R.id.chanPinTeSe);
		sheJiJiHuaShu = (TextView) findViewById(R.id.sheJiJiHuaShu);
		xinLiJianJieBack = (ImageView) findViewById(R.id.xinLiJianJieBack);
		
		baoXianLiNianShaoEr.setOnClickListener(this);
		baoXianLiNianChengRen.setOnClickListener(this);
		touBaoGuiZe.setOnClickListener(this);
		chanPinTeSe.setOnClickListener(this);
		sheJiJiHuaShu.setOnClickListener(this);
		xinLiJianJieBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.baoXianLiNianShaoEr:
			Intent intent = new Intent(this, ImgAnimationA.class);
			Bundle b = new Bundle();
			b.putInt("maxPageCount", 8);
			b.putString("url", "product/xinli/jianjie/linianX");
			b.putString("format", "gif");
			b.putString("screenFlag","jianjie");
			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.baoXianLiNianChengRen:
			Intent intent2 = new Intent(this, ImgAnimationA.class);
			Bundle b2 = new Bundle();
			b2.putInt("maxPageCount", 8);
			b2.putString("url", "product/xinli/jianjie/linianC");
			b2.putString("format", "gif");
			b2.putString("screenFlag","jianjie");
			intent2.putExtras(b2);
			startActivity(intent2);
			break;
		case R.id.touBaoGuiZe:
			Intent intent3 = new Intent(this, ImgAnimationA.class);
			Bundle b3 = new Bundle();
			b3.putInt("maxPageCount", 6);
			b3.putString("url", "product/yingjuyisheng/jianjie/toubaoguize");
			b3.putString("format", "gif");
			b3.putString("screenFlag","jianjie");
			intent3.putExtras(b3);
			startActivity(intent3);
			break;
		case R.id.chanPinTeSe:
			Intent intent4 = new Intent(this, ImgAnimationA.class);
			Bundle b4 = new Bundle();
			b4.putInt("maxPageCount", 3);
			b4.putString("url", "product/yingjuyisheng/jianjie/chanpintese");
			b4.putString("format", "gif");
			b4.putString("screenFlag","jianjie");
			intent4.putExtras(b4);
			startActivity(intent4);
			break;
		case R.id.sheJiJiHuaShu:
			Intent intent5 = new Intent(YingJuYiShengA.this,YingJuYSPlanA.class);
			startActivity(intent5);
			finish();
			break;
		case R.id.xinLiJianJieBack:
			finish();
			break;
		}
	}

}
