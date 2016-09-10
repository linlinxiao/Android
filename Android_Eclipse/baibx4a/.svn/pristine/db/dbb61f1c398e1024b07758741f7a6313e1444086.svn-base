package com.baoxiao.activity.zoujinpingan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.baoxiao.R;
import com.baoxiao.activity.zhaopinjingying.ZhaopinWeb;
import com.baoxiao.service.userproduct.UserService;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * '走进平安'布局界面
 *
 */
public class ComingPingAnA extends Activity implements OnClickListener{
	protected static final int GETURL = 0;
	private ImageView back;
	private RelativeLayout zhaoPin, tiaoJian, peiXun, liYou,rongYu;
//	历史结算利率:http://www.rabbitpre.com/m/IfMeibyVe#
//	平安荣誉:http://www.rabbitpre.com/m/VBfUVzc#
//	平安发展历程:http://www.rabbitpre.com/m/QvMBJv9#
//	平安资产:http://www.rabbitpre.com/m/BuMAeayNn#
//	平安概述:http://www.rabbitpre.com/m/Uv2q2ih#
	private String url1 = "http://www.rabbitpre.com/m/IfMeibyVe#",
			url2 = "http://www.rabbitpre.com/m/VBfUVzc#",
			url3 = "http://www.rabbitpre.com/m/QvMBJv9#",
			url4 = "http://www.rabbitpre.com/m/BuMAeayNn#",
			url5 ="http://www.rabbitpre.com/m/Uv2q2ih#";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.comingpingan);
	    
	    back = (ImageView) findViewById(R.id.back);
		zhaoPin = (RelativeLayout) findViewById(R.id.zhaoPin);
		tiaoJian = (RelativeLayout) findViewById(R.id.tiaoJian);
		peiXun = (RelativeLayout) findViewById(R.id.peiXun);
		liYou = (RelativeLayout) findViewById(R.id.liYou);
		rongYu = (RelativeLayout) findViewById(R.id.rongYu);

		back.setOnClickListener(this);
		zhaoPin.setOnClickListener(this);
		tiaoJian.setOnClickListener(this);
		peiXun.setOnClickListener(this);
		liYou.setOnClickListener(this);
		rongYu.setOnClickListener(this);
		getUrl();
	}

	private void getUrl() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Message msg = new Message();
				MessageHelper message = UserService.getIntoPeaceUrl();
				msg.obj = message;
				msg.what = GETURL;
				handler.sendMessage(msg);
			}
		}).start();
	}

	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			MessageHelper message = (MessageHelper)msg.obj;
			switch (msg.what) {
			case GETURL:
				if (message.getResult().equals(Define.S)) {
					try {
						JSONArray array = new JSONArray(new JSONObject(
								new JSONObject(message.getResouce())
								.getString("messageHelper"))
								.getString("entity"));
						if (array.length() >= 5) {
							url1 = array.getJSONObject(0).getString("outlink");
							url2 = array.getJSONObject(1).getString("outlink");
							url3 = array.getJSONObject(2).getString("outlink");
							url4 = array.getJSONObject(3).getString("outlink");
							url5 = array.getJSONObject(4).getString("outlink");
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				break;

			default:
				break;
			}
			return false;
		}
	});
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.zhaoPin:
//			Intent intent = new Intent(this, ImgAnimationA.class);
//			Bundle b = new Bundle();
//			b.putInt("maxPageCount", 30);
//			b.putString("url", "product/zoujinpingan/pinganjituanjieshao");
//			b.putString("format", "gif");
//			b.putString("screenFlag","jianjie");
//			intent.putExtras(b);
//			startActivity(intent);
			startActivity(new Intent(ComingPingAnA.this,ZhaopinWeb.class)
					.putExtra("url",url3)
					.putExtra("name", "平安集团介绍"));
			break;
		case R.id.tiaoJian:
//			Intent intent2 = new Intent(this, ImgAnimationA.class);
//			Bundle b2 = new Bundle();
//			b2.putInt("maxPageCount", 12);
//			b2.putString("url", "product/zoujinpingan/pinganrenshou");
//			b2.putString("format", "gif");
//			b2.putString("screenFlag","jianjie");
//			intent2.putExtras(b2);
//			startActivity(intent2);
			startActivity(new Intent(ComingPingAnA.this,ZhaopinWeb.class)
					.putExtra("url",url2)
					.putExtra("name", "平安发展历程"));
			break;
		case R.id.peiXun:
//			Intent intent3 = new Intent(this, ImgAnimationA.class);
//			Bundle b3 = new Bundle();
//			b3.putInt("maxPageCount", 9);
//			b3.putString("url", "product/zoujinpingan/pinganlishijiesuanlilv");
//			b3.putString("format", "gif");
//			b3.putString("screenFlag","jianjie");
//			intent3.putExtras(b3);
//			startActivity(intent3);			
			startActivity(new Intent(ComingPingAnA.this,ZhaopinWeb.class)
					.putExtra("url",url4)
					.putExtra("name", "平安历史\n结算利率"));
			break;
		case R.id.liYou:
//			Intent intent4 = new Intent(this, ImgAnimationA.class);
//			Bundle b4 = new Bundle();
//			b4.putInt("maxPageCount", 19);
//			b4.putString("url", "product/zoujinpingan/pinganzichanguanligongsi");
//			b4.putString("format", "gif");
//			b4.putString("screenFlag","jianjie");
//			intent4.putExtras(b4);
//			startActivity(intent4);
			startActivity(new Intent(ComingPingAnA.this,ZhaopinWeb.class)
					.putExtra("url",url1)
					.putExtra("name", "平安发展历程"));
			break;
		case R.id.rongYu:
//			Intent intent5 = new Intent(this, ImgAnimationA.class);
//			Bundle b5 = new Bundle();
//			b5.putInt("maxPageCount", 13);
//			b5.putString("url", "product/zoujinpingan/pingansuohuoderongyu");
//			b5.putString("format", "gif");
//			b5.putString("screenFlag","jianjie");
//			intent5.putExtras(b5);
//			startActivity(intent5);
			startActivity(new Intent(ComingPingAnA.this,ZhaopinWeb.class)
					.putExtra("url",url5)
					.putExtra("name", "平安荣誉"));
			break;
		case R.id.back:
			finish();
			break;

		default:
			break;
		}		
	}

	
}
