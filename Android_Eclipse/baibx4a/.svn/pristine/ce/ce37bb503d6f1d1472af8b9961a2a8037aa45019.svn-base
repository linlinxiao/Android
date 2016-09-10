package com.baoxiao.activity.productshow.hushenfu;

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
import com.baoxiao.WebShowA;

public class HuShenFuA extends Activity implements OnClickListener{
	private TextView baoXianLiNian,touBaoGuiZe,chanPinTeSe,
	                 sheJiJiHuaShu;
	private ImageView huShenFuJianJieBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.ps_hushenfu);
		
		initView();
		
	}

	private void initView() {
		baoXianLiNian = (TextView) findViewById(R.id.baoXianLiNian);
		touBaoGuiZe = (TextView) findViewById(R.id.touBaoGuiZe);
		chanPinTeSe = (TextView) findViewById(R.id.chanPinTeSe);
		sheJiJiHuaShu = (TextView) findViewById(R.id.sheJiJiHuaShu);
		huShenFuJianJieBack = (ImageView) findViewById(R.id.huShenFuJianJieBack);
		baoXianLiNian.setOnClickListener(this);
		touBaoGuiZe.setOnClickListener(this);
		chanPinTeSe.setOnClickListener(this);
		sheJiJiHuaShu.setOnClickListener(this);
		huShenFuJianJieBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int title_image_id = R.drawable.product_hushenfu;
		switch (v.getId()) {
		case R.id.baoXianLiNian:
//			Intent intent = new Intent(this, ImgAnimationA.class);
//			Bundle b = new Bundle();
//			b.putInt("maxPageCount",11);
//			b.putString("url", "product/hushenfu/jianjie/linian");
//			b.putString("format", "gif");
//			b.putString("screenFlag","jianjie");
//			intent.putExtras(b);
//			startActivity(intent);
			
    		String url = "http://www.rabbitpre.com/m/NmNmVnzrk#";
//    		String url = "http://www.rabbitpre.com/m/V3EfumI#";
	    		String shareName = "保险理念";
	    		startActivity(new Intent(HuShenFuA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("title_image_id", title_image_id)
	    				.putExtra("shareName", shareName));
			break;
		case R.id.touBaoGuiZe:
//			Intent intent2 = new Intent(this, ImgAnimationA.class);
//			Bundle b2 = new Bundle();
//			b2.putInt("maxPageCount",5);
//			b2.putString("url", "product/hushenfu/jianjie/guize");
//			b2.putString("format", "gif");
//			b2.putString("screenFlag","jianjie");
//			intent2.putExtras(b2);
//			startActivity(intent2);
			
    		url = "http://www.rabbitpre.com/m/UJ3jr6mNS#";
	    	shareName = "投保规则";
	    		startActivity(new Intent(HuShenFuA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("title_image_id", title_image_id)
	    				.putExtra("shareName", shareName));
			break;
		case R.id.chanPinTeSe:
//			Intent intent3 = new Intent(this, ImgAnimationA.class);
//			Bundle b3 = new Bundle();
//			b3.putInt("maxPageCount",5);
//			b3.putString("url", "product/hushenfu/jianjie/tese");
//			b3.putString("format", "gif");
//			b3.putString("screenFlag","jianjie");
//			intent3.putExtras(b3);
//			startActivity(intent3);

    		url = "http://www.rabbitpre.com/m/u26JZvB#";
	    	shareName = "产品特色";
	    		startActivity(new Intent(HuShenFuA.this,WebShowA.class)
	    				.putExtra("url", url)
	    				.putExtra("title_image_id", title_image_id)
	    				.putExtra("shareName", shareName));
			break;
		case R.id.sheJiJiHuaShu:
			Intent intent4 = new Intent(this, HuShenFuPlanA2.class);
			startActivity(intent4);
			finish();
			break;
		case R.id.huShenFuJianJieBack:
			finish();
			break;
		
		default:
			break;
		}
	}

}
