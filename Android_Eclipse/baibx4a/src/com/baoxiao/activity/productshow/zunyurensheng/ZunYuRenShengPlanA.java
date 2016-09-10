package com.baoxiao.activity.productshow.zunyurensheng;

import com.baoxiao.R;
import com.baoxiao.model.HongLi;
import com.baoxiao.provider.DBDAO;
import com.baoxiao.service.productshow.ZunYuRSService;
import com.baoxiao.util.Define;
import com.baoxiao.util.E2DoubleUtil;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ZunYuRenShengPlanA extends Activity implements OnClickListener,OnCheckedChangeListener
{

		// 定义从上往下
		private String sex = "";
		private Spinner ageSpinner = null;
		private RadioGroup radioGroup;
		private RadioButton radioButton;
		private Spinner periodSpinner = null;
		private EditText baoE;
		private TextView fenHongShuiPingLow, fenHongShuiPingMiddle,
				fenHongShuiPingHigh;
		private String level = Define.Level_Middle;
		private TextView commitInsurancePlan;
		private ImageView ZunYuRenShengBack, ZunYuRenShengMenu;

		//设计计划书预览的控件
		private TextView ageShow,sexShow,
	 				zxPeriodShow,
	 			    zhuXianBaoEShow,
	 			    zxBaoFeiShow,baoFeiTotal;
		//设计计划书预览的计算值
		private ZunYuRSService zyrsService;
		private HongLi hongli;
		private Double zhuBaoFeiD,totalBaoFeiD;
		
		private ArrayAdapter<String> ageAdapter = null;
		private ArrayAdapter<String> periodAdapter = null;

		private String ageSpinnerValue,zxPeriodValue,bE;
		private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ps_zunyurensheng_plan);
	
		zyrsService=new ZunYuRSService();
		hongli =new HongLi();
		
		//初始化控件
		initView();
		//设置个控件的点击事件
		setClickEvent();
	}

	private void initView() {
		ZunYuRenShengBack = (ImageView) findViewById(R.id.shouHuXingBack);
		ZunYuRenShengMenu = (ImageView) findViewById(R.id.shouHuXingMenu);
		// 初始化控件
		baoE = (EditText) findViewById(R.id.baoE);
		radioGroup = (RadioGroup) findViewById(R.id.sex);
		// 设置默认的单选按钮
		radioGroup.check(R.id.maleRadioButton);

		radioButton = (RadioButton) findViewById(radioGroup
				.getCheckedRadioButtonId());
		// 获取默认被被选中值
		sex = radioButton.getText().toString();

		radioGroup.setOnCheckedChangeListener(this);
		ageSpinner = (Spinner) findViewById(R.id.age);
		periodSpinner = (Spinner) findViewById(R.id.period);
		fenHongShuiPingLow = (TextView) findViewById(R.id.fenHongShuiPingLow);
		fenHongShuiPingMiddle = (TextView) findViewById(R.id.fenHongShuiPingMiddle);
		fenHongShuiPingHigh = (TextView) findViewById(R.id.fenHongShuiPingHigh);
		commitInsurancePlan = (TextView) findViewById(R.id.commitInsurancePlan);

		//计划书预览控件初始化
		ageShow = (TextView) findViewById(R.id.ageShow);
		sexShow = (TextView) findViewById(R.id.sexShow);
		
		zhuXianBaoEShow = (TextView) findViewById(R.id.zhuXianBaoEShow);
		
		zxPeriodShow = (TextView) findViewById(R.id.zxPeriodShow);
		
		zxBaoFeiShow = (TextView) findViewById(R.id.zxBaoFeiShow);
		baoFeiTotal = (TextView) findViewById(R.id.baoFeiTotal);
		
		ageAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.ageZunYuRS);
		periodAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.periodZunYuRSAll);

		// 设置下拉列表弹出样式
		ageAdapter
				.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		periodAdapter
				.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

		ageSpinner.setAdapter(ageAdapter);
		periodSpinner.setAdapter(periodAdapter);

		commitInsurancePlan.setOnClickListener(this);
		fenHongShuiPingLow.setOnClickListener(this);
		fenHongShuiPingMiddle.setOnClickListener(this);
		fenHongShuiPingHigh.setOnClickListener(this);
		ZunYuRenShengBack.setOnClickListener(this);
		ZunYuRenShengMenu.setOnClickListener(this);
	}

	private void setClickEvent() {
		ageSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id)
			{
				ageSpinnerValue = ageSpinner.getSelectedItem().toString();
//				updateSpinner(ageSpinnerValue);
				ageShow.setText(ageSpinnerValue+"岁");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		periodSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				zxPeriodValue = periodSpinner.getSelectedItem().toString();
				zxPeriodShow.setText(zxPeriodValue+Define.Nian);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		//文本框的文本改变事件
		baoE.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			//文本改变之后的方法
			@Override
			public void afterTextChanged(Editable s) {
				if(!"".equals(baoE.getText().toString())&&baoE.getText().toString() != null){
					zhuXianBaoEShow.setText(baoE.getText().toString()+Define.WanYuan);
//					new Thread(DataRunnable).start();
					initData();
					//使double数值不使用科学计算法显示
					zxBaoFeiShow.setText(E2DoubleUtil.E2Double(zhuBaoFeiD)+Define.Yuan);
					baoFeiTotal.setText(E2DoubleUtil.E2Double(totalBaoFeiD)+Define.Yuan);
				}
			}
		});
	}
	
//	Runnable DataRunnable = new Runnable() {
//		@Override
//		public void run() {
//			Message m = new Message();
//			m.what = 1;
//			initData();
//			handler.sendMessage(m);
//		}
//	};
//	
//	Handler handler = new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			if(msg.what == 1){
//				//使double数值不使用科学计算法显示
//				zxBaoFeiShow.setText(E2DoubleUtil.E2Double(zhuBaoFeiD)+Define.Yuan);
//				baoFeiTotal.setText(E2DoubleUtil.E2Double(totalBaoFeiD)+Define.Yuan);
//			}
//		}
//		
//	};
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		radioButton = (RadioButton) findViewById(radioGroup
				.getCheckedRadioButtonId());
		sex = radioButton.getText().toString();
		sexShow.setText(sex+"性");
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.commitInsurancePlan:
				bE = baoE.getText().toString();
				if (!bE.equals(""))
				{
					Intent intent = new Intent(ZunYuRenShengPlanA.this,
							ZunYuRSShowA.class);
					intent.putExtra("sex", sex);
					intent.putExtra("age", ageSpinner.getSelectedItem().toString());
					intent.putExtra("period", periodSpinner.getSelectedItem()
							.toString());
					intent.putExtra("baoE", bE);
					intent.putExtra("level", level);
					intent.putExtra("zhuBaoFeiD", zhuBaoFeiD);
					startActivity(intent);
					finish();
				} else
				{
					Toast.makeText(getApplicationContext(), "请输入保额",
							Toast.LENGTH_SHORT).show();
					baoE.setText("");
				}
		
				break;
			case R.id.fenHongShuiPingLow:
				fenHongShuiPingHigh.setBackgroundColor(android.graphics.Color
						.parseColor("#DDDDDD"));
				fenHongShuiPingMiddle.setBackgroundColor(android.graphics.Color
						.parseColor("#DDDDDD"));
				fenHongShuiPingLow.setBackgroundColor(android.graphics.Color
						.parseColor("#F3C095"));
				level = Define.Level_Low;
				break;
			case R.id.fenHongShuiPingMiddle:
				fenHongShuiPingHigh.setBackgroundColor(android.graphics.Color
						.parseColor("#DDDDDD"));
				fenHongShuiPingMiddle.setBackgroundColor(android.graphics.Color
						.parseColor("#F3C095"));
				fenHongShuiPingLow.setBackgroundColor(android.graphics.Color
						.parseColor("#DDDDDD"));
				level = Define.Level_Middle;
				break;
			case R.id.fenHongShuiPingHigh:
				fenHongShuiPingHigh.setBackgroundColor(android.graphics.Color
						.parseColor("#F3C095"));
				fenHongShuiPingMiddle.setBackgroundColor(android.graphics.Color
						.parseColor("#DDDDDD"));
				fenHongShuiPingLow.setBackgroundColor(android.graphics.Color
						.parseColor("#DDDDDD"));
				level = Define.Level_High;
				break;
		
			case R.id.shouHuXingBack:
				finish();
				break;
			case R.id.shouHuXingMenu:
				break;
		}
	
	}
	
//	//下拉联动
//	private void updateSpinner(String Value)
//	{
//		int aSpinnerValue = Integer.valueOf(Value);
//		ArrayAdapter<String> adapter = null;
//		// 交费年期下拉判断
//		if (18 <= aSpinnerValue && aSpinnerValue <= 50)
//		{
//			adapter = new ArrayAdapter<String>(this,
//					android.R.layout.simple_spinner_item,
//					Define.periodZunYuRSAll);
//			periodSpinner.setAdapter(adapter);
//		} else if (51 <= aSpinnerValue && aSpinnerValue <= 55)
//		{
//			adapter = new ArrayAdapter<String>(this,
//					android.R.layout.simple_spinner_item,
//					Define.periodZunYuRS51and55);
//			periodSpinner.setAdapter(adapter);
//		} else
//		{
//			adapter = new ArrayAdapter<String>(this,
//					android.R.layout.simple_spinner_item,
//					Define.periodZunYuRS56and57);
//			periodSpinner.setAdapter(adapter);
//		}
//		adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
//	}
	
	private void initData() {
		hongli.setSex(sex);
		hongli.setAge(Integer.valueOf(ageSpinner.getSelectedItem().toString()));
		hongli.setYear(Integer.valueOf(periodSpinner.getSelectedItem().toString()));
		hongli.setLevel(level);
		hongli.setBaoE(Integer.valueOf(baoE.getText().toString()));
		if (db == null) {
			db = DBDAO.getSqliteDB(this);
		}
		zhuBaoFeiD=zyrsService.findBaoFei(hongli, ZunYuRenShengPlanA.this, Define.DB_Type_BaoFei_ZunYuRenSheng,db);
		totalBaoFeiD=zhuBaoFeiD;
	}
}
