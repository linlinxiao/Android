package com.baoxiao.activity.productshow.zunyurensheng;

import com.baoxiao.R;
import com.baoxiao.WebShowA;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ZunYuRenShengA extends Activity implements OnClickListener 
{

	private TextView baoXianLiNianShaoEr,baoXianLiNianChengRen,
					touBaoGuiZe,chanPinTeSe,sheJiJiHuaShu;
	private ImageView xinLiJianJieBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ps_zunyurensheng);
	
		//初始化界面控件
		initView();
	}

	private void initView() {
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
//			Intent intent = new Intent(this, ImgAnimationA.class);
//			Bundle b = new Bundle();
//			b.putInt("maxPageCount", 8);
//			b.putString("url", "product/shijitianshi/jianjie/baoxianlinian");
//			b.putString("format", "gif");
//			b.putString("screenFlag","jianjie");
//			intent.putExtras(b);
//			startActivity(intent);
    		String url = "http://www.rabbitpre.com/m/EjQNV31#";
	    	String shareName = "少儿保险理念";
    		startActivity(new Intent(ZunYuRenShengA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("shareName", shareName));
			break;
		case R.id.baoXianLiNianChengRen:
//			Intent intent2 = new Intent(this, ImgAnimationA.class);
//			Bundle b2 = new Bundle();
//			b2.putInt("maxPageCount", 8);
//			b2.putString("url", "product/xinli/jianjie/linianC");
//			b2.putString("format", "gif");
//			b2.putString("screenFlag","jianjie");
//			intent2.putExtras(b2);
//			startActivity(intent2);
    		url = "http://www.rabbitpre.com/m/MQeybyz71#";
	    	shareName = "成人保险理念";
    		startActivity(new Intent(ZunYuRenShengA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("shareName", shareName));
			break;
		case R.id.touBaoGuiZe:
//			Intent intent3 = new Intent(this, ImgAnimationA.class);
//			Bundle b3 = new Bundle();
//			b3.putInt("maxPageCount", 5);
//			b3.putString("url", "product/zunyurensheng/jianjie/toubaoguize");
//			b3.putString("format", "gif");
//			b3.putString("screenFlag","jianjie");
//			intent3.putExtras(b3);
//			startActivity(intent3);
    		url = "http://www.rabbitpre.com/m/MQmzrqqEZ#";
	    	shareName = "投保规则";
    		startActivity(new Intent(ZunYuRenShengA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("shareName", shareName));
			break;
		case R.id.chanPinTeSe:
//			Intent intent4 = new Intent(this, ImgAnimationA.class);
//			Bundle b4 = new Bundle();
//			b4.putInt("maxPageCount", 3);
//			b4.putString("url", "product/zunyurensheng/jianjie/chanpintese");
//			b4.putString("format", "gif");
//			b4.putString("screenFlag","jianjie");
//			intent4.putExtras(b4);
//			startActivity(intent4);
    		url = "http://www.rabbitpre.com/m/QFZFjyx#";
	    	shareName = "产品特色";
    		startActivity(new Intent(ZunYuRenShengA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("shareName", shareName));
			break;
		case R.id.sheJiJiHuaShu:
			Intent intent5 = new Intent(ZunYuRenShengA.this,ZunYuRenShengPlanA.class);
			startActivity(intent5);
			finish();
			break;
		case R.id.xinLiJianJieBack:
			finish();
			break;

		default:
			break;
		}
	}

}
