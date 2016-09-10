package com.baoxiao.activity.productshow.shouhux;

import com.baoxiao.R;
import com.baoxiao.model.HongLi;
import com.baoxiao.provider.DBDAO;
import com.baoxiao.service.productshow.ShouHuXService;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
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

public class ShouHuXPlanA extends Activity implements OnClickListener,OnItemSelectedListener,OnCheckedChangeListener
{
	 private String sex = "";
	 private Spinner ageSpinner = null; 
	 private RadioGroup radioGroup;
	 private RadioButton radioButton;
	 private Spinner periodSpinner = null; 
	 private EditText baoEEdit; 
	 private EditText jiBingBaoEEdit; 
	 private String level=Define.Level_Middle;
	 private TextView commitInsurancePlan,fenHongShuiPingLow,fenHongShuiPingMiddle,fenHongShuiPingHigh;
	 private ImageView shouHuXingBack,shouHuXingMenu;
	 
	 
	 //设计计划书预览的控件
	 //TODO 设计计划书预览的控件
	 private TextView ageShow,sexShow,
	 			zxPeriodShow,hmPerioidShow,zjPeriodShow,
	 			zhuXianBaoEShow,zhongJiBaoEShow,huoMianBaoEShow,
	 			zxBaoFeiShow,hmBaoFeiShow,zjBaoFeiShow,baoFeiTotal;
	 //设计计划书预览的计算值
	 private ShouHuXService shxService;
	 private HongLi hongli;
	 private Double zhuBaoFeiD,zhongJiBaoFeiD,huoMianBaoFeiD,totalBaoFeiD;
	 
	 private  ArrayAdapter<String> ageAdapter=null;
	 private  ArrayAdapter<String> periodAdapter=null;
	 private  ArrayAdapter<String> baoEAdapter=null;
	 private  ArrayAdapter<String> jiBingBaoEAdapter=null;
	 
	 private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ps_shousux_plan);
		
		shxService = new ShouHuXService();
		hongli = new HongLi();
		
		//初始化控件
		commitInsurancePlan = (TextView) findViewById(R.id.commitInsurancePlan);
		fenHongShuiPingLow = (TextView) findViewById(R.id.fenHongShuiPingLow);
		fenHongShuiPingMiddle = (TextView) findViewById(R.id.fenHongShuiPingMiddle);
		fenHongShuiPingHigh = (TextView) findViewById(R.id.fenHongShuiPingHigh);
		ageSpinner=(Spinner) findViewById(R.id.age);
		periodSpinner=(Spinner) findViewById(R.id.period);
		baoEEdit=(EditText) findViewById(R.id.baoE);
		jiBingBaoEEdit=(EditText) findViewById(R.id.jiBingBaoE);
		radioGroup=(RadioGroup) findViewById(R.id.sex);
		
		shouHuXingBack = (ImageView) findViewById(R.id.shouHuXingBack);
		shouHuXingMenu = (ImageView) findViewById(R.id.shouHuXingMenu);
		
		//设置默认的单选按钮
		radioGroup.check(R.id.maleRadioButton);
		
		radioButton=(RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
		//获取默认被被选中值  
		sex = radioButton.getText().toString();
		
		//计划书预览控件初始化
		ageShow = (TextView) findViewById(R.id.ageShow);
		sexShow = (TextView) findViewById(R.id.sexShow);
		
		zhuXianBaoEShow = (TextView) findViewById(R.id.zhuXianBaoEShow);
		zhongJiBaoEShow = (TextView) findViewById(R.id.zhongJiBaoEShow);
		huoMianBaoEShow = (TextView) findViewById(R.id.huoMianBaoEShow);
		
		zxPeriodShow = (TextView) findViewById(R.id.zxPeriodShow);
		zjPeriodShow = (TextView) findViewById(R.id.zjPeriodShow);
		hmPerioidShow = (TextView) findViewById(R.id.hmPerioidShow);
		
		zxBaoFeiShow = (TextView) findViewById(R.id.zxBaoFeiShow);
		zjBaoFeiShow = (TextView) findViewById(R.id.zjBaoFeiShow);
		hmBaoFeiShow = (TextView) findViewById(R.id.hmBaoFeiShow);
		baoFeiTotal = (TextView) findViewById(R.id.baoFeiTotal);
		
		ageAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Define.ageShouHuX);
		periodAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Define.periodShouHuX);
		baoEAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.baoEShouHuX);
		jiBingBaoEAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.zhongJiBaoEShouHuX);
		
		//设置下拉列表弹出样式
		ageAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		periodAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		baoEAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		jiBingBaoEAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		
		ageSpinner.setAdapter(ageAdapter);
		periodSpinner.setAdapter(periodAdapter);
		
		
		commitInsurancePlan.setOnClickListener(this);
		fenHongShuiPingLow.setOnClickListener(this);
		fenHongShuiPingMiddle.setOnClickListener(this);
		fenHongShuiPingHigh.setOnClickListener(this);
		shouHuXingBack.setOnClickListener(this);
		shouHuXingMenu.setOnClickListener(this);
		
		huoMianBaoEShow.setText("/");
		
		ageSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				ageShow.setText(ageSpinner.getSelectedItem().toString()+"岁");
//				initData();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		periodSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String zxPeriodValue = periodSpinner.getSelectedItem().toString();
				int hmPeriodValue = Integer.valueOf(zxPeriodValue)-1;
				zxPeriodShow.setText(zxPeriodValue+Define.Nian);
				zjPeriodShow.setText(zxPeriodValue+Define.Nian);
				hmPerioidShow.setText(hmPeriodValue+Define.Nian);
//				initData();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		baoEEdit.addTextChangedListener(new TextWatcher() { //给edittext设置文本改变监听器
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			public void afterTextChanged(Editable s) {
				if(!"".equals(baoEEdit.getText().toString())) {
					zhuXianBaoEShow.setText(baoEEdit.getText().toString()+Define.WanYuan);
					
				}
			}
			
		});
			
			
//		OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				String be = baoESpinner.getSelectedItem().toString();
//				zhuXianBaoEShow.setText(be+Define.WanYuan);
//				initData();
//			}
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//			}
//		});
//			
//		jiBingBaoESpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				String zj = jiBingBaoESpinner.getSelectedItem().toString();
//				zhongJiBaoEShow.setText(zj+Define.WanYuan);
////				initData();
//			}
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//			}
//		});

		jiBingBaoEEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				String jbbe = jiBingBaoEEdit.getText().toString();
				if(jiBingBaoEEdit.hasFocus() == false){
					if(jbbe.equals("")){
					}else{
						if(Integer.valueOf(baoEEdit.getText().toString()) <= 5*(Integer.valueOf(jbbe))){
							
//							if(!"".equals(jiBingBaoEEdit.getText().toString())&&jiBingBaoEEdit.getText().toString() != null){
//								zhongJiBaoEShow.setText(jiBingBaoEEdit.getText().toString()+Define.WanYuan);
//							}
//							new Thread(networkTask).start();
//							initData();
							
						}else{
							Toast.makeText(ShouHuXPlanA.this,"附加少儿重大疾病保险最高保额为主险守护星的5倍" ,Toast.LENGTH_LONG).show();
							jiBingBaoEEdit.setText("");
						}
					}
				}else{
					
					
					
//					Toast.makeText(ShouHuXPlanA.this,"附加少儿重大疾病保险最高保额为主险守护星的5倍" ,Toast.LENGTH_LONG).show();
				}
			}
		});	
//		文本框的文本改变事件
		jiBingBaoEEdit.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				if(!"".equals(jiBingBaoEEdit.getText().toString())&&jiBingBaoEEdit.getText().toString() != null){
					zhongJiBaoEShow.setText(jiBingBaoEEdit.getText().toString()+Define.WanYuan);
					new Thread(updateBaoFeiRunnable).start();
				}
//				initData();
			}
			
		});		
		
		
		
		
		
		radioGroup.setOnCheckedChangeListener(this);
		
	}
	
	
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{

			switch (msg.what)
			{
			case 1:
				
				zxBaoFeiShow.setText(zhuBaoFeiD+Define.Yuan);
				hmBaoFeiShow.setText(huoMianBaoFeiD+Define.Yuan);
				zjBaoFeiShow.setText(zhongJiBaoFeiD+Define.Yuan);
				baoFeiTotal.setText(totalBaoFeiD+Define.Yuan);
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	/**
	 * 准备数据
	 */
	Runnable updateBaoFeiRunnable = new Runnable()
	{

		@Override
		public void run()
		
		{   
			initData();
			Message msg = new Message();
			msg.what = 1;
			handler.sendMessage(msg);
		}
	};
	
	
	
	
	private void initData() {
		hongli.setSex(sex);
		hongli.setAge(Integer.valueOf(ageSpinner.getSelectedItem().toString()));
		hongli.setYear(Integer.valueOf(periodSpinner.getSelectedItem().toString()));
		String be = baoEEdit.getText().toString();
		if ("".equals(be)) {be = "0";}
		hongli.setBaoE(Integer.valueOf(be)); //转换成int类型
		String jbe = jiBingBaoEEdit.getText().toString();
		if ("".equals(jbe)) {jbe = "0";}
		hongli.setFenShu(Integer.valueOf(jbe));
		hongli.setLevel(level);
		if (db == null) {
			db = DBDAO.getSqliteDB(this);
		}
//		zhuBaoFeiD=shxService.findBaoFei(hongli, this, Define.DB_Type_BaoFei_ShouHuX,db);
		zhuBaoFeiD=shxService.findBaoFei(hongli, this, Define.DB_Type_BaoFei_ShouHuX,db);
		zhongJiBaoFeiD=shxService.findZhongJiBaoFei(hongli, this, Define.DB_Type_FuJiaBaoFei_ZhongJi,db);
		huoMianBaoFeiD=shxService.findHuoMianBaoFei(hongli, this, Define.DB_Type_FuJiaBaoFei_HuoMian,zhuBaoFeiD,db);
		totalBaoFeiD=zhuBaoFeiD+zhongJiBaoFeiD+huoMianBaoFeiD;
	
//		zxBaoFeiShow.setText(zhuBaoFeiD+Define.Yuan);
//		hmBaoFeiShow.setText(huoMianBaoFeiD+Define.Yuan);
//		zjBaoFeiShow.setText(zhongJiBaoFeiD+Define.Yuan);
//		baoFeiTotal.setText(totalBaoFeiD+Define.Yuan);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.commitInsurancePlan:
					Intent intent = new Intent(ShouHuXPlanA.this,ShouHuXShowA.class);
					intent.putExtra("sex", sex);
					intent.putExtra("age", ageSpinner.getSelectedItem().toString());
					intent.putExtra("period", periodSpinner.getSelectedItem().toString());
					intent.putExtra("baoE", baoEEdit.getText().toString()); //传递数据
					intent.putExtra("zhongJiBaoE", jiBingBaoEEdit.getText().toString());
					intent.putExtra("level", level);
					intent.putExtra("zhuBaoFeiD", zhuBaoFeiD);
					intent.putExtra("zhongJiBaoFeiD", zhongJiBaoFeiD);
					intent.putExtra("huoMianBaoFeiD", huoMianBaoFeiD);
					
					startActivity(intent);
					finish();
			break;
		case R.id.fenHongShuiPingLow:
			fenHongShuiPingHigh.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
			fenHongShuiPingMiddle.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
			fenHongShuiPingLow.setBackgroundColor(android.graphics.Color.parseColor("#F3C095"));
			level=Define.Level_Low;
//			Toast.makeText(getApplicationContext(), "点击了low", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.fenHongShuiPingMiddle:
			fenHongShuiPingHigh.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
			fenHongShuiPingMiddle.setBackgroundColor(android.graphics.Color.parseColor("#F3C095"));
			fenHongShuiPingLow.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
//			Toast.makeText(getApplicationContext(), "点击了middle", Toast.LENGTH_SHORT).show();
			level=Define.Level_Middle;
		
			break;
		case R.id.fenHongShuiPingHigh:
			fenHongShuiPingHigh.setBackgroundColor(android.graphics.Color.parseColor("#F3C095"));
			fenHongShuiPingMiddle.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
			fenHongShuiPingLow.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
//			Toast.makeText(getApplicationContext(), "点击了high", Toast.LENGTH_SHORT).show();
			level=Define.Level_High;
			break;
		case R.id.shouHuXingBack:
			finish();
			break;
		case R.id.shouHuXingMenu:
//			Toast.makeText(getApplicationContext(), "点击了星号", Toast.LENGTH_SHORT).show();
			break;
		}
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
//		 provinceSpinner.setSelection(3,true);  //设置默认选中项，此处为默认选中第4个值
//		view.setText("你的血型是："+m[arg2]);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		radioButton=(RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
		sex = radioButton.getText().toString();
		sexShow.setText(sex+"性");
//		initData();
	}
}
