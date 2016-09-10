package com.baoxiao.activity.mine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baoxiao.R;
import com.baoxiao.model.Userb;
import com.baoxiao.service.mine.MineService;

/**
 * '我的账号'布局界面
 *
 */
public class MyAccountA extends Activity implements OnClickListener
{

	private TextView zhangHuMing, jiHuoZhuangTai, jiHuoRiQi, days, xianZhong,
			company, goldNumber;
	private LinearLayout goldGongLue;
	private ImageView mineBack;
	private Userb userb = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_myaccount);
		initView();
		setText();
	}

	private void setText()
	{

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				String userid = getSharedPreferences("loading", 0).getString(
						"userid", null);
				userb = MineService.MyAccountNews(userid);
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
			}
		}).start();

	}

	private void initView()
	{
		zhangHuMing = (TextView) findViewById(R.id.zhangHuMing);
		jiHuoZhuangTai = (TextView) findViewById(R.id.jiHuoZhuangTai);
		jiHuoRiQi = (TextView) findViewById(R.id.jiHuoRiQi);
		days = (TextView) findViewById(R.id.days);
		xianZhong = (TextView) findViewById(R.id.xianZhong);
		company = (TextView) findViewById(R.id.company);
		goldNumber = (TextView) findViewById(R.id.goldNumber);

		goldGongLue = (LinearLayout) findViewById(R.id.goldGongLue);
		goldGongLue.setOnClickListener(this);

		mineBack = (ImageView) findViewById(R.id.mineBack);
		mineBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.goldGongLue:
			Intent intent = new Intent(this, GoldGongLueA.class);
			intent.putExtra("page", "myccount");
			startActivity(intent);
			break;
		case R.id.mineBack:
			finish();
			break;
		default:
			break;
		}
	}

	private Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{

			switch (msg.what)
			{
			case 1:
				zhangHuMing.setText(userb.getUserid());
				jiHuoZhuangTai.setText(userb.getStatus());
				Date renewaltime = userb.getRenewaltime();
				if (renewaltime != null) {
					String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
							Locale.getDefault()).format(renewaltime);
					jiHuoRiQi.setText(dateStr);
				}
				String remainDay = userb.getRemainDay();
				if (remainDay != null) {
					days.setText(remainDay);
				}
				String xian = userb.getXianZhong();
				if (xian != null) {
					xianZhong.setText(xian);
				}
				String gs = userb.getXianzhonggs();
				if (gs != null) {
					company.setText(gs);
				}
				if (userb.getGold() != null) {
					goldNumber.setText(userb.getGold() + "");
				}
				break;
			}
			super.handleMessage(msg);
		};
	};
}