package com.baoxiao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.baoxiao.activity.pay.ProductPay;
import com.baoxiao.service.userproduct.UserService;
import com.baoxiao.util.CustomProgressDialog;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;

/**
 * 注册成功后的激活页面
 *
 */
public class JiHuoA extends Activity implements OnClickListener
{

	private EditText etJiHuo;
	private TextView nowJiHuo, ToPayFor, notJiHuo;
	private ImageView back;
	private String userId, IntentTag;
	private Intent notIntent = null;
	ProgressDialog cpd;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.jihuo);
		cpd = new CustomProgressDialog(this, "正在激活...",R.anim.frame_anim);
		etJiHuo = (EditText) this.findViewById(R.id.etJiHuoMa);
		nowJiHuo = (TextView) this.findViewById(R.id.nowJiHuo);
		back = (ImageView) this.findViewById(R.id.back);
		ToPayFor = (TextView) this.findViewById(R.id.ToPayFor);
//		RzFree = (TextView) this.findViewById(R.id.RzFree);
		notJiHuo = (TextView) this.findViewById(R.id.notJiHuo);
		userId = getSharedPreferences("loading", 0).getString("userid", null);
		IntentTag = getIntent().getStringExtra("IntentTag");
		nowJiHuo.setOnClickListener(this);
		ToPayFor.setOnClickListener(this);
//		RzFree.setOnClickListener(this);
		notJiHuo.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
		case R.id.nowJiHuo:
			cpd.show();
			new Thread(runnable1).start();
			break;

		case R.id.ToPayFor:
			Intent payIntent = new Intent(this, ProductPay.class);
			startActivity(payIntent);
			break;

//		case R.id.RzFree:
//			Intent renzIntent = new Intent(this, MyCardEditA.class);
//			renzIntent.putExtra("rz", "1");
//			startActivity(renzIntent);
//			finish();
//			break;

		case R.id.notJiHuo:
			if ("1".equals(IntentTag))
			{
				notIntent = new Intent(this, LoginA.class);
			} else
			{
				notIntent = new Intent(this, MainActivityA.class);
			}
			startActivity(notIntent);
			finish();
			break;

		case R.id.back:
			if ("1".equals(IntentTag))
			{
				notIntent = new Intent(this, LoginA.class);
			} else
			{
				notIntent = new Intent(this, MainActivityA.class);
			}
			startActivity(notIntent);
			finish();
			break;
		}

	}

	public boolean onKeyDown(int keyCode, android.view.KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			if ("1".equals(IntentTag))
			{
				notIntent = new Intent(this, LoginA.class);
			} else
			{
				notIntent = new Intent(this, MainActivityA.class);
			}
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			startActivity(notIntent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	};

	MessageHelper msgHelper;

	Runnable runnable1 = new Runnable()
	{

		@Override
		public void run()
		{
			// msgHelper = UserService.jiHuoState(userId,
			// etJiHuo.getText().toString());
			String imei = UserService.getIMEI(JiHuoA.this);
			String mac = UserService.getLocalMac(JiHuoA.this);
			msgHelper = UserService.getDealData(userId, etJiHuo.getText()
					.toString(), imei, mac);
			handler.sendEmptyMessage(1);
		}
	};
	ArrayList<Map<String, String>> maps = new ArrayList<Map<String, String>>();
	public Handler handler = new Handler()
	{

		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case 1:
				if (msgHelper.getResult().equals(Define.S))
				{
					cpd.dismiss();
					Toast.makeText(JiHuoA.this, "激活成功！", Toast.LENGTH_SHORT)
							.show();
					maps = (ArrayList<Map<String, String>>) msgHelper.getList();
					Intent intent = new Intent(JiHuoA.this, JiHuoSucceedA.class);
					intent.putExtra("mapList", (Serializable) maps);
					intent.putExtra("jiHuoMa", etJiHuo.getText());
					startActivity(intent);
					SharedPreferences isActive = getSharedPreferences("isActive", Activity.MODE_PRIVATE);
					isActive.edit().putString("result", "1").commit();
					isActive.edit().putString("message", "激活状态").commit();
					finish();
				} else
				{
					cpd.dismiss();
					Toast.makeText(JiHuoA.this, "激活失败！", Toast.LENGTH_SHORT)
							.show();
				}
				break;
			}
		}
	};

}
