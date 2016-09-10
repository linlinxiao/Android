package com.baoxiao.activity.productshow.baiwanrwx;

import com.baoxiao.R;
import com.baoxiao.activity.productshow.ImgAnimationA;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * '简介'布局界面
 *
 */
public class BaiWanRWXA extends Activity implements OnClickListener 
{

	private TextView fengXianVideo,
					touBaoGuiZe,chanPinTeSe,sheJiJiHuaShu;
	private ImageView xinLiJianJieBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ps_baiwanrewoxing);
	
		//初始化界面控件
		initView();
	}

	private void initView() {
		fengXianVideo = (TextView) findViewById(R.id.fengXianVideo);
		touBaoGuiZe = (TextView) findViewById(R.id.touBaoGuiZe);
		chanPinTeSe = (TextView) findViewById(R.id.chanPinTeSe);
		sheJiJiHuaShu = (TextView) findViewById(R.id.sheJiJiHuaShu);
		xinLiJianJieBack = (ImageView) findViewById(R.id.xinLiJianJieBack);
		
		fengXianVideo.setOnClickListener(this);
		touBaoGuiZe.setOnClickListener(this);
		chanPinTeSe.setOnClickListener(this);
		sheJiJiHuaShu.setOnClickListener(this);
		xinLiJianJieBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fengXianVideo:
			Intent intent2 = new Intent(this, BwrwxChanpinshipin.class);
//			Bundle b2 = new Bundle();
//			b2.putInt("maxPageCount", 8);
//			b2.putString("url", "product/xinli/jianjie/linianC");
//			b2.putString("format", "gif");
//			b2.putString("screenFlag","jianjie");
//			intent2.putExtras(b2);
			startActivity(intent2);
			break;
		case R.id.touBaoGuiZe:
			Intent intent3 = new Intent(this, ImgAnimationA.class);
			Bundle b3 = new Bundle();
			b3.putInt("maxPageCount", 5);
			b3.putString("url", "product/baiwanrenwoxing/jianjie/toubaoguize");
			b3.putString("format", "gif");
			b3.putString("screenFlag","jianjie");
			intent3.putExtras(b3);
			startActivity(intent3);
			break;
		case R.id.chanPinTeSe:
			Intent intent4 = new Intent(this, ImgAnimationA.class);
			Bundle b4 = new Bundle();
			b4.putInt("maxPageCount", 5);
			b4.putString("url", "product/baiwanrenwoxing/jianjie/chanpintese");
			b4.putString("format", "gif");
			b4.putString("screenFlag","jianjie");
			intent4.putExtras(b4);
			startActivity(intent4);
			break;
		case R.id.sheJiJiHuaShu:
			Intent intent5 = new Intent(BaiWanRWXA.this,BaiWanRWXPlanA.class);
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
