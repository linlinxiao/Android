package com.baoxiao.activity.mine;

import com.baoxiao.R;
import com.baoxiao.activity.shop.ShopA;
import com.baoxiao.util.Define;
import com.baoxiao.util.ShareService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * '赚元宝攻略'布局界面
 *
 */
public class GoldGongLueA extends Activity implements OnClickListener{

	private LinearLayout share,shop,tuiJian,feedBack;
	private ImageView mineBack;
	        
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_goldgonglue);
		
		initView();
	}

	private void initView() {
		share = (LinearLayout) findViewById(R.id.share);
		shop = (LinearLayout) findViewById(R.id.shop);
		tuiJian = (LinearLayout) findViewById(R.id.tuiJian);
		feedBack = (LinearLayout) findViewById(R.id.feedBack);
	
		share.setOnClickListener(this);
		shop.setOnClickListener(this);
		tuiJian.setOnClickListener(this);
		feedBack.setOnClickListener(this);
		
		mineBack = (ImageView) findViewById(R.id.mineBack);
		mineBack.setOnClickListener(this);
	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.share:
			//分享微信平安百宝箱页面链接
			String url = Define.Server + "appindex.action";
			ShareService.startShare(url,"平安百宝箱",this);
			break;
		case R.id.shop:
			Intent intent2 = new Intent(this,ShopA.class);
			startActivity(intent2);
			break;
		case R.id.tuiJian:
			Intent intent4 = new Intent(this,HuDongA.class);
			startActivity(intent4);
			break;
		case R.id.feedBack:
			Intent intent5 = new Intent(this,HuDongA.class);
			startActivity(intent5);
			break;
		case R.id.mineBack:
			finish();
			break;

		default:
			break;
		}
	}
}
