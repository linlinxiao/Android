package com.baoxiao.activity.productshow.shouhux;

import com.baoxiao.R;
import com.baoxiao.WebShowA;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ShouHuXA extends Activity implements OnClickListener{
	private TextView baoXianLiNian,chanPinTeSe,
	                 sheJiJiHuaShu;
	private ImageView huShenFuJianJieBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.ps_shouhux);	
		initView();
		
	}

	private void initView() {
		baoXianLiNian = (TextView) findViewById(R.id.baoXianLiNian);
//		touBaoGuiZe = (TextView) findViewById(R.id.touBaoGuiZe);
		chanPinTeSe = (TextView) findViewById(R.id.chanPinTeSe);
		sheJiJiHuaShu = (TextView) findViewById(R.id.sheJiJiHuaShu);
		huShenFuJianJieBack = (ImageView) findViewById(R.id.huShenFuJianJieBack);
		baoXianLiNian.setOnClickListener(this);
//		touBaoGuiZe.setOnClickListener(this);
		chanPinTeSe.setOnClickListener(this);
		sheJiJiHuaShu.setOnClickListener(this);
		huShenFuJianJieBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int title_image_id = R.drawable.pop_peace_icon6;
		switch (v.getId()) {
		case R.id.baoXianLiNian:
//			Intent intent = new Intent(this, ImgAnimationA.class);
//			Bundle b = new Bundle();
//			b.putInt("maxPageCount",11);
//			b.putString("url", "product/shouhux/jianjie/baoxlinian");
//			b.putString("format", "gif");
//			b.putString("screenFlag","jianjie");
//			intent.putExtras(b);
//			startActivity(intent);
    		String url = "http://www.rabbitpre.com/m/y7AYZvU35#";
	    	String shareName = "保险理念";
    		startActivity(new Intent(ShouHuXA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("imageId", title_image_id)
    				.putExtra("shareName", shareName));
			break;
//		case R.id.touBaoGuiZe:
//			Intent intent2 = new Intent(this, WebViewA.class);
//			Bundle b2 = new Bundle();
//			b2.putInt("maxPageCount",5);
//			b2.putString("url", "product/shouhux/jianjie/guize");
//			b2.putString("format", "gif");
//			b2.putString("screenFlag","jianjie");
//			intent2.putExtras(b2);
//			startActivity(intent2);
//			break;
		case R.id.chanPinTeSe:
//			Intent intent3 = new Intent(this, ImgAnimationA.class);
//			Bundle b3 = new Bundle();
//			b3.putInt("maxPageCount",3);
//			b3.putString("url", "product/shouhux/jianjie/tese");
//			b3.putString("format", "gif");
//			b3.putString("screenFlag","jianjie");
//			intent3.putExtras(b3);
//			startActivity(intent3);
    		url = "http://www.rabbitpre.com/m/ZNV3euH#";
	    	shareName = "产品特色";
    		startActivity(new Intent(ShouHuXA.this,WebShowA.class)
    				.putExtra("url", url)
    				.putExtra("title_image_id", title_image_id)
    				.putExtra("shareName", shareName));
			break;
		case R.id.sheJiJiHuaShu:
			Intent intent4 = new Intent(this, ShouHuXPlanA2.class);
			startActivity(intent4);
			break;
		case R.id.huShenFuJianJieBack:
			finish();
			break;
		
		default:
			break;
		}		
	}

}
