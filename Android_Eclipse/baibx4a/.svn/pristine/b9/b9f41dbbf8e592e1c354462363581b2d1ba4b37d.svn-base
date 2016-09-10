package com.baoxiao.activity.productshow.hushenfu;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import com.baoxiao.R;
import com.baoxiao.model.HongLi;
import com.baoxiao.provider.DBDAO;
import com.baoxiao.service.productshow.HuShenFuService;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;

public class HuShenFuPlanA extends Activity implements OnClickListener,OnItemSelectedListener,OnCheckedChangeListener{
	 //定义从上往下
	 private String sex = "";
	 private Spinner ageSpinner = null; 
	 private RadioGroup radioGroup;
	 private RadioButton radioButton;
	 private Spinner periodSpinner = null; 
	 private EditText baoEEdit;
	 private EditText zhongJiBaoEEdit; 
//	 private Spinner yiWaiXianperiodSpinner = null; 
	 private EditText yiWaiBaoEEdit;
	 private TextView fenHongShuiPingLow,fenHongShuiPingMiddle,fenHongShuiPingHigh;
	 private String level=Define.Level_Middle;
	 private TextView commitInsurancePlan;
	 
	 private Double be = 0.0,zj = 0.0;
	 private SQLiteDatabase db;
 	 
	//设计计划书预览的控件
	 //TODO 设计计划书预览的控件
	 private TextView ageShow,sexShow,
	 			zxPeriodShow,hmPerioidShow,zjPeriodShow,ywPeriodShow,
	 			zhuXianBaoEShow,zhongJiBaoEShow,huoMianBaoEShow,yiWaiBaoEShow,
	 			zxBaoFeiShow,hmBaoFeiShow,zjBaoFeiShow,ywBaoFeiShow,baoFeiTotal;
	 //设计计划书预览的计算值
	 private HuShenFuService hsfService;
	 private HongLi hongli;
	 private Double zhuBaoFeiD = 0.0,
			 zhongJiBaoFeiD = 0.0,
			 huoMianBaoED = 0.0,
			 huoMianBaoFeiD = 0.0,
			 totalBaoFeiD = 0.0,
			 yiWaiBaoFeiD = 0.0;
	 
	 private  ArrayAdapter<String> ageAdapter=null;
	 private  ArrayAdapter<String> periodAdapter=null;
	 private  ArrayAdapter<String> baoEAdapter=null;
	 private  ArrayAdapter<String> jiBingBaoEAdapter=null;
	 private  ArrayAdapter<String> yiWaiBaoEAdapter=null;
	 
	 private int ageSpinnerValue;
	 
	 private ImageView huShenFuBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		//设置竖屏
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.ps_hushenfu_plan);
		
		hsfService = new HuShenFuService();
		hongli = new HongLi();
		
		//初始化控件
		radioGroup=(RadioGroup) findViewById(R.id.sex);
		//设置默认的单选按钮
		radioGroup.check(R.id.maleRadioButton);
		
		radioButton=(RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
		//获取默认被被选中值  
		sex = radioButton.getText().toString();
		hongli.setSex(sex);
				
		ageSpinner=(Spinner) findViewById(R.id.age);
		periodSpinner=(Spinner) findViewById(R.id.period);
		baoEEdit=(EditText) findViewById(R.id.baoE);
		zhongJiBaoEEdit=(EditText) findViewById(R.id.jiBingBaoE);
		yiWaiBaoEEdit=(EditText) findViewById(R.id.yiWaiBaoE);
		commitInsurancePlan = (TextView) findViewById(R.id.commitInsurancePlan);
		fenHongShuiPingLow = (TextView) findViewById(R.id.fenHongShuiPingLow);
		fenHongShuiPingMiddle = (TextView) findViewById(R.id.fenHongShuiPingMiddle);
		fenHongShuiPingHigh = (TextView) findViewById(R.id.fenHongShuiPingHigh);
		huShenFuBack = (ImageView) findViewById(R.id.huShenFuBack);
		
		//计划书预览控件初始化
		ageShow = (TextView) findViewById(R.id.ageShow);
		sexShow = (TextView) findViewById(R.id.sexShow);
		
		zhuXianBaoEShow = (TextView) findViewById(R.id.zhuXianBaoEShow);
		zhongJiBaoEShow = (TextView) findViewById(R.id.zhongJiBaoEShow);
		huoMianBaoEShow = (TextView) findViewById(R.id.huoMianBaoEShow);
		yiWaiBaoEShow = (TextView) findViewById(R.id.yiWaiBaoEShow);
		
		zxPeriodShow = (TextView) findViewById(R.id.zxPeriodShow);
		zjPeriodShow = (TextView) findViewById(R.id.zjPeriodShow);
		hmPerioidShow = (TextView) findViewById(R.id.hmPerioidShow);
		ywPeriodShow = (TextView) findViewById(R.id.ywPerioidShow);
		
		zxBaoFeiShow = (TextView) findViewById(R.id.zxBaoFeiShow);
		zjBaoFeiShow = (TextView) findViewById(R.id.zjBaoFeiShow);
		ywBaoFeiShow = (TextView) findViewById(R.id.ywBaoFeiShow);
		hmBaoFeiShow = (TextView) findViewById(R.id.hmBaoFeiShow);
		baoFeiTotal = (TextView) findViewById(R.id.baoFeiTotal);
		
		ageAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Define.ageHuShenFu);
		periodAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Define.periodHuShenFu18and45);
		baoEAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.baoEHuShenFu);
		jiBingBaoEAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.zhongJiBaoEShouHuX);
		yiWaiBaoEAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.yiWaiBaoEHuShenFu);
		
		//设置下拉列表弹出样式
		ageAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		periodAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		baoEAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		jiBingBaoEAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		yiWaiBaoEAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		
		ageSpinner.setAdapter(ageAdapter);
		String age = ageSpinner.getSelectedItem().toString();
		if (age == null || "".equals(age)) {age = "18";}
		hongli.setAge(Integer.parseInt(age));

		periodSpinner.setAdapter(periodAdapter);
		String year = periodSpinner.getSelectedItem().toString();
		if (year == null || "".equals(year)) {year = "10";}
		hongli.setYear(Integer.parseInt(year));
		
		String baoe = baoEEdit.getText().toString();
		if (year == baoe || "".equals(baoe)) {baoe = "0";}
		be = Double.valueOf(baoe);
		hongli.setBaoE(Integer.parseInt(baoe));
		
		String zhongJiBaoE = zhongJiBaoEEdit.getText().toString();
		if (zhongJiBaoE != null && !"".equals(zhongJiBaoE)) {
			zj = Double.valueOf(zhongJiBaoE);
		}
		
		commitInsurancePlan.setOnClickListener(this);
		fenHongShuiPingLow.setOnClickListener(this);
		fenHongShuiPingMiddle.setOnClickListener(this);
		fenHongShuiPingHigh.setOnClickListener(this);
		huShenFuBack.setOnClickListener(this);
		
		ageSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ageSpinnerValue = Integer.valueOf(ageSpinner.getSelectedItem().toString());
				hongli.setAge(ageSpinnerValue);
				updateSpinner(ageSpinnerValue);
				ageShow.setText(ageSpinner.getSelectedItem().toString()+"岁");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		periodSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String zxPeriodValue = periodSpinner.getSelectedItem().toString();
				hongli.setYear(Integer.valueOf(zxPeriodValue));
				int hmPeriodValue = Integer.valueOf(zxPeriodValue)-1;
				zxPeriodShow.setText(zxPeriodValue+Define.Nian);
				zjPeriodShow.setText(zxPeriodValue+Define.Nian);
				hmPerioidShow.setText(hmPeriodValue+Define.Nian);
				ywPeriodShow.setText(zxPeriodValue+Define.Nian);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		
//		baoESpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				be = Double.valueOf(baoESpinner.getSelectedItem().toString());
//				hongli.setBaoE(Integer.valueOf(baoESpinner.getSelectedItem().toString()));
//				zhuXianBaoEShow.setText(be+Define.WanYuan);
//				zhuBaoFeiD=hsfService.findBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_BaoFei_HuShenFu);
//				zxBaoFeiShow.setText(zhuBaoFeiD+Define.Yuan);
//			}
		
		baoEEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				String be = baoEEdit.getText().toString();
				if(baoEEdit.hasFocus() == false){
					if(be.equals("")){
					}else{
						if(Integer.valueOf(be) >=12 ){
						}else{
							Toast.makeText(HuShenFuPlanA.this,"主险护身福保额最低为12万",Toast.LENGTH_LONG).show();
							baoEEdit.setText("");
						}
					}
				}else{
//					Toast.makeText(HuShenFuPlanA.this,"主险护身福保额最低为12万",Toast.LENGTH_LONG).show();
				}
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
				if(!"".equals(baoEEdit.getText().toString()) ) {
					be =Double.valueOf(baoEEdit.getText().toString());
					hongli.setBaoE(Integer.valueOf(baoEEdit.getText().toString()));
					zhuXianBaoEShow.setText(be+Define.WanYuan);
					if (db == null) {
						db = DBDAO.getSqliteDB(HuShenFuPlanA.this);
					}
					zhuBaoFeiD=hsfService.findBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_BaoFei_HuShenFu,db);
					zxBaoFeiShow.setText(zhuBaoFeiD+Define.Yuan);
					
					huoMianBaoED=hsfService.findHuoMianBaoE(hongli,HuShenFuPlanA.this,Define.DB_Type_BaoFei_HuShenFu,yiWaiBaoFeiD,be,zj,db);
					huoMianBaoFeiD=hsfService.findHuoMianBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_FuJiaBaoFei_HuShenFuHuoMian,huoMianBaoED,db);
					hmBaoFeiShow.setText(huoMianBaoFeiD+Define.Yuan);
					total();
				}
			}		
		});			
			

//		zhongJiBaoESpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				zj = Double.valueOf(zhongJiBaoESpinner.getSelectedItem().toString());
//				zhongJiBaoEShow.setText(zj+Define.WanYuan);
//				//重疾保费的数值
//				hongli.setFenShu(Integer.valueOf(zhongJiBaoESpinner.getSelectedItem().toString()));
//				zhongJiBaoFeiD=hsfService.findZhongJiBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_FuJiaBaoFei_HuShenFuZhongJi);
//				zjBaoFeiShow.setText(zhongJiBaoFeiD+Define.Yuan);
////			}
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//			}
//		});
		
		
		zhongJiBaoEEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				String zjbe = zhongJiBaoEEdit.getText().toString();
				if(hasFocus){return;}
				if(zjbe.equals("")){return;}
				if(Integer.valueOf(zjbe) < 12 || Integer.valueOf(zjbe) > Integer.valueOf(baoEEdit.getText().toString()) ){ 
					Toast.makeText(HuShenFuPlanA.this,"护身福重疾保额最低为12万，且不能高于护身福主险保额。",Toast.LENGTH_LONG).show();
					zhongJiBaoEEdit.setText("");
					return;
				}
				if(Integer.valueOf(baoEEdit.getText().toString()) < 37.5) {
					if(Integer.valueOf(zjbe) < 0.8*Integer.valueOf(baoEEdit.getText().toString())){
						Toast.makeText(HuShenFuPlanA.this,"当主险保额在37.5万以内，重疾保额设置必须大于或等于主险保额的0.8倍",Toast.LENGTH_LONG).show();
						zhongJiBaoEEdit.setText("");
						return;
					}
				}
				if(Integer.valueOf(zjbe) >= 30 && Integer.valueOf(baoEEdit.getText().toString())> 37.5){
					Toast.makeText(HuShenFuPlanA.this,"当重疾保额大于等于30万时,可以附加在大于37.5万的任何主险保额上",Toast.LENGTH_LONG).show();
				}
				
			}
		});	
		
		//文本框的文本改变事件
		zhongJiBaoEEdit.addTextChangedListener(new TextWatcher() {
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
				if(!"".equals(zhongJiBaoEEdit.getText().toString())&&zhongJiBaoEEdit.getText().toString() != null){
					zj = Double.valueOf(zhongJiBaoEEdit.getText().toString());
					zhongJiBaoEShow.setText(zj+Define.WanYuan);
					//重疾保费的数值
					if (db == null) {
						db = DBDAO.getSqliteDB(HuShenFuPlanA.this);
					}
					hongli.setFenShu(Integer.valueOf(zhongJiBaoEEdit.getText().toString()));
					zhongJiBaoFeiD=hsfService.findZhongJiBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_FuJiaBaoFei_HuShenFuZhongJi,db);
					zjBaoFeiShow.setText(zhongJiBaoFeiD+Define.Yuan);
					
					huoMianBaoED=hsfService.findHuoMianBaoE(hongli,HuShenFuPlanA.this,Define.DB_Type_BaoFei_HuShenFu,yiWaiBaoFeiD,be,zj,db);
					huoMianBaoFeiD=hsfService.findHuoMianBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_FuJiaBaoFei_HuShenFuHuoMian,huoMianBaoED,db);
					hmBaoFeiShow.setText(huoMianBaoFeiD+Define.Yuan);
					total();
				}
			}
			
		});
		
		
		
//		yiWaiBaoErSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				String ywBaoE = yiWaiBaoErSpinner.getSelectedItem().toString();
//				yiWaiBaoEShow.setText(ywBaoE+Define.WanYuan);
//				//意外险保费的数值
//				hongli.setFenShu(Integer.valueOf(yiWaiBaoErSpinner.getSelectedItem().toString()));
//				yiWaiBaoFeiD=hsfService.findYiWaiBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_FuJiaBaoFei_HuShenFuYiWai);
//				ywBaoFeiShow.setText(yiWaiBaoFeiD+Define.Yuan);
//			}
//				public void onNothingSelected(AdapterView<?> arg0) {
//				}
//			});	

		yiWaiBaoEEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				String ywbe = yiWaiBaoEEdit.getText().toString();
				if(yiWaiBaoEEdit.hasFocus() == false){
					if(ywbe.equals("")){
					}else{
						if(Integer.valueOf(ywbe) >= 15 && Integer.valueOf(ywbe)<= 5*(Integer.valueOf(baoEEdit.getText().toString()))){
						}else{
							Toast.makeText(HuShenFuPlanA.this,"附加长期意外保险最低15万,且不超过护身福主险保额的5倍.",Toast.LENGTH_LONG).show();
							yiWaiBaoEEdit.setText("");
						}
					}
				}else{
//					Toast.makeText(HuShenFuPlanA.this,"附加长期意外保险最低15万,且不超过护身福主险保额的5倍.",Toast.LENGTH_LONG).show();
				}
			}
		});			
		
		
		yiWaiBaoEEdit.addTextChangedListener(new TextWatcher() { //给edittext设置文本改变监听器
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			public void afterTextChanged(Editable s) {
				String ywbe = yiWaiBaoEEdit.getText().toString();
				if(!"".equals(ywbe)) {
					hongli.setFenShu(Integer.valueOf(ywbe));
					yiWaiBaoEShow.setText(ywbe+Define.WanYuan);
					if (db == null) {
						db = DBDAO.getSqliteDB(HuShenFuPlanA.this);
					}
					yiWaiBaoFeiD=hsfService.findYiWaiBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_FuJiaBaoFei_HuShenFuYiWai,db);
					ywBaoFeiShow.setText(yiWaiBaoFeiD +Define.Yuan);
					
					huoMianBaoED=hsfService.findHuoMianBaoE(hongli,HuShenFuPlanA.this,Define.DB_Type_BaoFei_HuShenFu,yiWaiBaoFeiD,be,zj,db);
					huoMianBaoFeiD=hsfService.findHuoMianBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_FuJiaBaoFei_HuShenFuHuoMian,huoMianBaoED,db);
					huoMianBaoEShow.setText(huoMianBaoED+Define.Yuan);
					hmBaoFeiShow.setText(huoMianBaoFeiD+Define.Yuan);
					total();
				}
			}		
		});			
		
		//主险保额和保费
		zhuXianBaoEShow.setText(be+Define.WanYuan);
		if (db == null) {db = DBDAO.getSqliteDB(this);}
		zhuBaoFeiD=hsfService.findBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_BaoFei_HuShenFu,db);
		zxBaoFeiShow.setText(zhuBaoFeiD+Define.Yuan);
		
		//重疾
		zhongJiBaoEShow.setText(zj+Define.WanYuan);
		
		String s_zj = zhongJiBaoEEdit.getText().toString();
		if (s_zj == null || "".equals(s_zj)) {s_zj = "0";}
		hongli.setFenShu(Integer.valueOf(s_zj));
		if (db == null) {
			db = DBDAO.getSqliteDB(this);
		}
		zhongJiBaoFeiD=hsfService.findZhongJiBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_FuJiaBaoFei_HuShenFuZhongJi,db);
		zjBaoFeiShow.setText(zhongJiBaoFeiD+Define.Yuan);

		//豁免保额和保费的数值
		huoMianBaoED=hsfService.findHuoMianBaoE(hongli,HuShenFuPlanA.this,Define.DB_Type_BaoFei_HuShenFu,yiWaiBaoFeiD,be,zj,db);
		//TODO 暂时注释掉
		huoMianBaoFeiD=hsfService.findHuoMianBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_FuJiaBaoFei_HuShenFuHuoMian,huoMianBaoED,db);
		huoMianBaoEShow.setText(huoMianBaoED+Define.Yuan);
		hmBaoFeiShow.setText(huoMianBaoFeiD+Define.Yuan);
		
		//附加意外
		String ywbe = yiWaiBaoEEdit.getText().toString();
		if (ywbe == null || "".equals(ywbe)) {ywbe = "0";}
		hongli.setFenShu(Integer.valueOf(ywbe));
		yiWaiBaoEShow.setText(ywbe+Define.WanYuan);
		yiWaiBaoFeiD=hsfService.findYiWaiBaoFei(hongli, HuShenFuPlanA.this, Define.DB_Type_FuJiaBaoFei_HuShenFuYiWai,db);
		ywBaoFeiShow.setText(yiWaiBaoFeiD +Define.Yuan);

		total();
		radioGroup.setOnCheckedChangeListener(this);
		
		hongli.setLevel(level);
	}
	
	private void total() {
		//总数值
		totalBaoFeiD=Arithmetic4Double.round(
				Arithmetic4Double.add(
				Arithmetic4Double.add(
				Arithmetic4Double.add(
						zhuBaoFeiD,zhongJiBaoFeiD),huoMianBaoFeiD),yiWaiBaoFeiD)
						, Define.Round2);
		baoFeiTotal.setText(totalBaoFeiD+Define.Yuan);		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.commitInsurancePlan:
					Intent intent = new Intent(HuShenFuPlanA.this,HuShenFuShowA.class);
					intent.putExtra("sex", sex);
					intent.putExtra("age", ageSpinner.getSelectedItem().toString());
					intent.putExtra("period", periodSpinner.getSelectedItem().toString());
					String baoE = baoEEdit.getText().toString();
					if (baoE == null || "".equals(baoE)) {baoE = "0";}
					intent.putExtra("baoE", baoE);
					String zji = zhongJiBaoEEdit.getText().toString();
					if (zji == null || "".equals(zji)) {zji = "0";}
					intent.putExtra("zhongJiBaoE", zji);
					String yiWaiBaoE = yiWaiBaoEEdit.getText().toString();
					if (yiWaiBaoE == null || "".equals(yiWaiBaoE)) {yiWaiBaoE = "0";}
					intent.putExtra("yiWaiBaoE", yiWaiBaoE);
					intent.putExtra("level", level);
					intent.putExtra("zhuBaoFeiD", zhuBaoFeiD);
					intent.putExtra("zhongJiBaoFeiD", zhongJiBaoFeiD);
					intent.putExtra("YiWaiBaoFeiD", yiWaiBaoFeiD);
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
		case R.id.huShenFuBack:
			finish();
			break;

		
		}
		
	}
    
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
	}

	

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		radioButton=(RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
		sex = radioButton.getText().toString();
		hongli.setSex(sex);
		sexShow.setText(sex+"性");
	}
	
	private void updateSpinner(int Value) {
		ArrayAdapter<String> adapter=null;
		if(18<=Value&&Value<=45){
			adapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item, Define.periodHuShenFu18and45);
			adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
			periodSpinner.setAdapter(adapter);
//			if(18<=Value&&Value<=40){
//				adapter = new ArrayAdapter<String>(this,
//						android.R.layout.simple_spinner_item, Define.yiWaiXianHuShenFu18and40);
//				yiWaiXianperiodSpinner.setAdapter(adapter);
//			}else{
//				adapter = new ArrayAdapter<String>(this,
//						android.R.layout.simple_spinner_item, Define.yiWaiXianHuShenFu41and50);
//				yiWaiXianperiodSpinner.setAdapter(adapter);
//			}
		}else{
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, Define.periodHuShenFu46and55);
			periodSpinner.setAdapter(adapter);
//			if(45<Value&&Value<=50){
//				adapter = new ArrayAdapter<String>(this,
//						android.R.layout.simple_spinner_item, Define.yiWaiXianHuShenFu41and50);
//				yiWaiXianperiodSpinner.setAdapter(adapter);
//			}else{
//				adapter = new ArrayAdapter<String>(this,
//						android.R.layout.simple_spinner_item, Define.yiWaiXianHuShenFu51and55);
//				yiWaiXianperiodSpinner.setAdapter(adapter);
//			}
		}
		adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
//		Toast.makeText(getApplicationContext(), "转换为数字："+ageSpinnerValue, Toast.LENGTH_LONG).show();
	}
}
