package com.baoxiao.activity.zhaopinjingying;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baoxiao.R;
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

/**
 * '招聘精英'布局界面
 *
 */
public class ZhaoPinJingYingA extends Activity implements OnClickListener {
	protected static final int GETURL = 0;
	private ImageView back;
	private ImageView zhaoPin, tiaoJian, peiXun, liYou;
	private String url1 = "http://www.rabbitpre.com/m/iAF32uS#",
			url2 = "http://www.rabbitpre.com/m/Q7FBnuc#",
			url3 = "http://www.rabbitpre.com/m/yQfE7jj#",
			url4 = "http://www.rabbitpre.com/m/uibiVB2Nr#";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhaopinjingying);

		back = (ImageView) findViewById(R.id.back);
		zhaoPin = (ImageView) findViewById(R.id.zhaoPin);
		tiaoJian = (ImageView) findViewById(R.id.tiaoJian);
		peiXun = (ImageView) findViewById(R.id.peiXun);
		liYou = (ImageView) findViewById(R.id.liYou);

		back.setOnClickListener(this);
		zhaoPin.setOnClickListener(this);
		tiaoJian.setOnClickListener(this);
		peiXun.setOnClickListener(this);
		liYou.setOnClickListener(this);
		getUrl();
	}
	private void getUrl() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Message msg = new Message();
				MessageHelper message = UserService.getRecruitUrl();
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
						if (array.length() >= 4) {
							url1 = array.getJSONObject(0).getString("outlink");
							url2 = array.getJSONObject(1).getString("outlink");
							url3 = array.getJSONObject(2).getString("outlink");
							url4 = array.getJSONObject(3).getString("outlink");
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
//			b.putInt("maxPageCount", 35);
//			b.putString("url", "product/jingyingzhaop/zhaopin");
//			b.putString("format", "png");
////			b.putString("screenFlag", "yanshi");
//			b.putString("screenFlag", "jianjie");
//			intent.putExtras(b);
//			startActivity(intent);
			startActivity(new Intent(ZhaoPinJingYingA.this,ZhaopinWeb.class)
					.putExtra("url",url1)
					.putExtra("name", "招聘"));
			break;
		case R.id.tiaoJian:
//			Intent intent2 = new Intent(this, ImgAnimationA.class);
//			Bundle b2 = new Bundle();
//			b2.putInt("maxPageCount", 2);
//			b2.putString("url", "product/jingyingzhaop/zhaopintiaojian");
//			b2.putString("format", "png");
//			b2.putString("screenFlag", "jianjie");
//			intent2.putExtras(b2);
//			startActivity(intent2);
			startActivity(new Intent(ZhaoPinJingYingA.this,ZhaopinWeb.class)
					.putExtra("url",url4)
					.putExtra("name", "招聘条件"));
			break;
		case R.id.peiXun:
//			Intent intent3 = new Intent(this, ImgAnimationA.class);
//			Bundle b3 = new Bundle();
//			b3.putInt("maxPageCount", 17);
//			b3.putString("url", "product/jingyingzhaop/pingandedaxuehepeixun");
//			b3.putString("format", "png");
//			b3.putString("screenFlag", "jianjie");
//			intent3.putExtras(b3);
//			startActivity(intent3);
			startActivity(new Intent(ZhaoPinJingYingA.this,ZhaopinWeb.class)
					.putExtra("url",url3)
					.putExtra("name", "平安大学和培训"));
			break;
		case R.id.liYou:
//			Intent intent4 = new Intent(this, ImgAnimationA.class);
//			Bundle b4 = new Bundle();
//			b4.putInt("maxPageCount", 12);
//			b4.putString("url", "product/jingyingzhaop/zuobaoxiandeshidaliyou");
//			b4.putString("format", "png");
//			b4.putString("screenFlag", "jianjie");
//			intent4.putExtras(b4);
//			startActivity(intent4);
			startActivity(new Intent(ZhaoPinJingYingA.this,ZhaopinWeb.class)
					.putExtra("url",url2)
					.putExtra("name", "做保险十大理由"));
			break;
		case R.id.back:
			finish();
			break;

		default:
			break;
		}
	}

}
