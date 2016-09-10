package com.baoxiao;

import java.util.ArrayList;
import java.util.Map;

import com.baoxiao.service.userproduct.UserService;
import com.baoxiao.util.CustomProgressDialog;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;
import com.umeng.socialize.net.n;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 激活成功的页面
 *
 */
public class JiHuoSucceedA extends Activity implements OnClickListener
{

	private LinearLayout insuranceType1, insuranceType2, insuranceType3,
			insuranceType4, insuranceType5, insuranceType6, insuranceType7,
			insuranceType8, insuranceType9, insuranceType10, insuranceType11;
	private ImageView insuranceSelect1, insuranceSelect2, insuranceSelect3,
			insuranceSelect4, insuranceSelect5, insuranceSelect6,
			insuranceSelect7, insuranceSelect8, insuranceSelect9,
			insuranceSelect10, insuranceSelect11;
	private TextView btnOk, tvXzType, tvDeal;
	private Spinner spinnerDeal;
	private boolean check1 = false, check2 = false, check3 = false,
			check4 = false, check5 = false, check6 = false, check7 = false,
			check8 = false, check9 = false, check10 = false, check11 = false;
	private boolean[] check =
	{ check1, check2, check3, check4, check5, check6, check7, check8, check9,
			check10, check11 };
	private String userType;
	private String userid;
	private String productcode = "";
	private MessageHelper msgHelper = new MessageHelper();
	private ArrayList<Map<String, String>> maps = new ArrayList<Map<String, String>>();
	private ProgressDialog cpd;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.jihuo_succeed2);
		cpd = new CustomProgressDialog(this, "正在登录...",R.anim.frame_anim);
		userType = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("type", null);
		userid = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("userid", null);
		maps = (ArrayList<Map<String, String>>) getIntent()
				.getSerializableExtra("mapList");
		initView();
		btnOk.setOnClickListener(this);
	}

	private void initView()
	{
		insuranceType1 = (LinearLayout) this.findViewById(R.id.insuranceType1);
		insuranceType2 = (LinearLayout) this.findViewById(R.id.insuranceType2);
		insuranceType3 = (LinearLayout) this.findViewById(R.id.insuranceType3);
		insuranceType4 = (LinearLayout) this.findViewById(R.id.insuranceType4);
		insuranceType5 = (LinearLayout) this.findViewById(R.id.insuranceType5);
		insuranceType6 = (LinearLayout) this.findViewById(R.id.insuranceType6);
		insuranceType7 = (LinearLayout) this.findViewById(R.id.insuranceType7);
		insuranceType8 = (LinearLayout) this.findViewById(R.id.insuranceType8);
		insuranceType9 = (LinearLayout) this.findViewById(R.id.insuranceType9);
		insuranceType10 = (LinearLayout) this
				.findViewById(R.id.insuranceType10);
		insuranceType11 = (LinearLayout) this
				.findViewById(R.id.insuranceType11);
		insuranceSelect1 = (ImageView) this.findViewById(R.id.insuranceSelect1);
		insuranceSelect2 = (ImageView) this.findViewById(R.id.insuranceSelect2);
		insuranceSelect3 = (ImageView) this.findViewById(R.id.insuranceSelect3);
		insuranceSelect4 = (ImageView) this.findViewById(R.id.insuranceSelect4);
		insuranceSelect5 = (ImageView) this.findViewById(R.id.insuranceSelect5);
		insuranceSelect6 = (ImageView) this.findViewById(R.id.insuranceSelect6);
		insuranceSelect7 = (ImageView) this.findViewById(R.id.insuranceSelect7);
		insuranceSelect8 = (ImageView) this.findViewById(R.id.insuranceSelect8);
		insuranceSelect9 = (ImageView) this.findViewById(R.id.insuranceSelect9);
		insuranceSelect10 = (ImageView) this
				.findViewById(R.id.insuranceSelect10);
		insuranceSelect11 = (ImageView) this
				.findViewById(R.id.insuranceSelect11);
		spinnerDeal = (Spinner) this.findViewById(R.id.spinnerDeal);
		btnOk = (TextView) this.findViewById(R.id.tvOk);
		tvXzType = (TextView) this.findViewById(R.id.tvXzType);
		tvDeal = (TextView) this.findViewById(R.id.tvDeal);

		if (userType.equals("user"))
		{
			setCheckOk();
			tvXzType.setText("你可以任意查看所有险种");
		} else
		{
			viewClick();
		}
		if (maps.size() != 0)
		{
			spinnerfillData();
		} else
		{
			tvDeal.setVisibility(View.INVISIBLE);
			spinnerDeal.setVisibility(View.INVISIBLE);
		}

	}

	private void setCheckOk()
	{
		insuranceSelect1.setImageResource(R.drawable.succeed_ok);
		insuranceSelect2.setImageResource(R.drawable.succeed_ok);
		insuranceSelect3.setImageResource(R.drawable.succeed_ok);
		insuranceSelect4.setImageResource(R.drawable.succeed_ok);
		insuranceSelect5.setImageResource(R.drawable.succeed_ok);
		insuranceSelect6.setImageResource(R.drawable.succeed_ok);
		insuranceSelect7.setImageResource(R.drawable.succeed_ok);
		insuranceSelect8.setImageResource(R.drawable.succeed_ok);
		insuranceSelect9.setImageResource(R.drawable.succeed_ok);
		insuranceSelect10.setImageResource(R.drawable.succeed_ok);
		insuranceSelect11.setImageResource(R.drawable.succeed_ok);
	}

	String orderbId[];
	ArrayAdapter<String> dealAdapter = null;
	String oId = "";
	int num = 2;

	private void spinnerfillData()
	{
		// new Thread(this).start();
		orderbId = new String[maps.size()];
		for (int i = 0; i < maps.size(); i++)
		{
			orderbId[i] = "购买订单编号：" + maps.get(i).get("id");
		}
		dealAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, orderbId);
		dealAdapter
				.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		spinnerDeal.setAdapter(dealAdapter);

		spinnerDeal.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3)
			{
				// TODO 自动生成的方法存根
				if (userType.equals("user"))
				{
					oId = maps.get(arg2).get("id");
				} else
				{
					num = Integer.parseInt(maps.get(arg2).get("productgroup")) * 2;
					oId = maps.get(arg2).get("id");
					tvXzType.setText("你可以任意勾选" + num + "个险种！");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				// TODO 自动生成的方法存根

			}

		});

	}

	private void viewClick()
	{
		insuranceType1.setOnClickListener(this);
		insuranceType2.setOnClickListener(this);
		insuranceType3.setOnClickListener(this);
		insuranceType4.setOnClickListener(this);
		insuranceType5.setOnClickListener(this);
		insuranceType6.setOnClickListener(this);
		insuranceType7.setOnClickListener(this);
		insuranceType8.setOnClickListener(this);
		insuranceType9.setOnClickListener(this);
		insuranceType10.setOnClickListener(this);
		insuranceType11.setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
		case R.id.insuranceType1:
			if (check[0])
			{
				insuranceSelect1.setImageResource(R.drawable.succeed_no);
				check[0] = false;
			} else
			{
				insuranceSelect1.setImageResource(R.drawable.succeed_ok);
				check[0] = true;
			}
			break;

		case R.id.insuranceType2:

			if (check[1])
			{
				insuranceSelect2.setImageResource(R.drawable.succeed_no);
				check[1] = false;
			} else
			{
				insuranceSelect2.setImageResource(R.drawable.succeed_ok);
				check[1] = true;
			}
			break;
		case R.id.insuranceType3:
			if (check[2])
			{
				insuranceSelect3.setImageResource(R.drawable.succeed_no);
				check[2] = false;
			} else
			{
				insuranceSelect3.setImageResource(R.drawable.succeed_ok);
				check[2] = true;
			}
			break;
		case R.id.insuranceType4:
			if (check[3])
			{
				insuranceSelect4.setImageResource(R.drawable.succeed_no);
				check[3] = false;
			} else
			{
				insuranceSelect4.setImageResource(R.drawable.succeed_ok);
				check[3] = true;
			}
			break;
		case R.id.insuranceType5:
			if (check[4])
			{
				insuranceSelect5.setImageResource(R.drawable.succeed_no);
				check[4] = false;
			} else
			{
				insuranceSelect5.setImageResource(R.drawable.succeed_ok);
				check[4] = true;
			}
			break;
		case R.id.insuranceType6:
			if (check[5])
			{
				insuranceSelect6.setImageResource(R.drawable.succeed_no);
				check[5] = false;
			} else
			{
				insuranceSelect6.setImageResource(R.drawable.succeed_ok);
				check[5] = true;
			}
			break;
		case R.id.insuranceType7:
			if (check[6])
			{
				insuranceSelect7.setImageResource(R.drawable.succeed_no);
				check[6] = false;
			} else
			{
				insuranceSelect7.setImageResource(R.drawable.succeed_ok);
				check[6] = true;
			}
			break;
		case R.id.insuranceType8:
			if (check[7])
			{
				insuranceSelect8.setImageResource(R.drawable.succeed_no);
				check[7] = false;
			} else
			{
				insuranceSelect8.setImageResource(R.drawable.succeed_ok);
				check[7] = true;
			}
			break;
		case R.id.insuranceType9:
			if (check[8])
			{
				insuranceSelect9.setImageResource(R.drawable.succeed_no);
				check[8] = false;
			} else
			{
				insuranceSelect9.setImageResource(R.drawable.succeed_ok);
				check[8] = true;
			}
			break;
		case R.id.insuranceType10:
			if (check[9])
			{
				insuranceSelect10.setImageResource(R.drawable.succeed_no);
				check[9] = false;
			} else
			{
				insuranceSelect10.setImageResource(R.drawable.succeed_ok);
				check[9] = true;
			}
			break;
		case R.id.insuranceType11:
			if (check[10])
			{
				insuranceSelect11.setImageResource(R.drawable.succeed_no);
				check[10] = false;
			} else
			{
				insuranceSelect11.setImageResource(R.drawable.succeed_ok);
				check[10] = true;
			}
			break;

		case R.id.tvOk:
			if (!userType.equals("user"))
			{
				int number = 0;
				for (int i = 0; i < check.length; i++)
				{
					String p = "";
					if (check[i])
					{
						number++;
						if (productcode.equals(""))
						{
							if (i >= 9)
								p = "0" + (i + 1);
							else
								p = "00" + (i + 1);
						} else
						{
							if (i >= 9)
								p = ",0" + (i + 1);
							else
								p = ",00" + (i + 1);
						}

					}
					productcode = productcode + p;
				}
				if (number > num)
				{
					Toast.makeText(JiHuoSucceedA.this, "亲，只能选择" + num + "个险种！",
							Toast.LENGTH_SHORT).show();
				} else if (number == 0)
				{
					Toast.makeText(JiHuoSucceedA.this, "亲，你未购买险种，请先去订单页面购买！",
							Toast.LENGTH_SHORT).show();
				}
			}
			cpd.show();
			new Thread(runnable).start();
			break;
		}
	}

	private Runnable runnable = new Runnable()
	{

		@Override
		public void run()
		{
			msgHelper = UserService.selectXianZhong(userid, userType, oId,
					productcode);
			Message msg = new Message();
			handler.sendMessage(msg);
		}
	};

	private Handler handler = new Handler()
	{

		public void handleMessage(android.os.Message msg)
		{
			if (msgHelper.getResult().equals(Define.S))
			{
				cpd.dismiss();
				Intent intent = new Intent(JiHuoSucceedA.this,
						MainActivityA.class);
				startActivity(intent);
				finish();
			} else
			{
				cpd.dismiss();
				Toast.makeText(JiHuoSucceedA.this, "险种选择失败!",
						Toast.LENGTH_SHORT).show();
			}
			super.handleMessage(msg);
		};
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ji_huo_succeed, menu);
		return true;
	}

}
