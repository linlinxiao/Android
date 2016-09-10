package com.baoxiao.activity.productshow.shijitianshi;

import com.baoxiao.R;
import com.baoxiao.model.HongLi;
import com.baoxiao.provider.DBDAO;
import com.baoxiao.service.productshow.ShouHuXService;
import com.baoxiao.util.Define;
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

public class ShiJiTSPlanA extends Activity implements OnClickListener,OnItemSelectedListener,OnCheckedChangeListener{
	 //定义从上往下
	 private EditText userName,baoE,jiBingBaoE;
	 private String uName = "",zhuBaoE = "",zjBaoE = "";
	 private String sex = "";
	 private Spinner ageSpinner = null; 
	 private RadioGroup radioGroup;
	 private RadioButton radioButton;
	 private Spinner periodSpinner = null; 
	 private TextView fenHongShuiPingLow,fenHongShuiPingMiddle,fenHongShuiPingHigh;
	 private String level=Define.Level_Middle;
	 private TextView commitInsurancePlan;
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
		//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ps_shijitianshi_plan);
		
		//需填写或选择的控件初始化
		userName = (EditText) findViewById(R.id.name);
		radioGroup=(RadioGroup) findViewById(R.id.sex);
		//设置默认的单选按钮
		radioGroup.check(R.id.maleRadioButton);
		radioButton=(RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
		//获取默认被被选中值  
		sex = radioButton.getText().toString();
				
		ageSpinner=(Spinner) findViewById(R.id.age);
		periodSpinner=(Spinner) findViewById(R.id.period);
		baoE=(EditText) findViewById(R.id.baoE);
		jiBingBaoE=(EditText) findViewById(R.id.jiBingBaoE);
		fenHongShuiPingLow = (TextView) findViewById(R.id.fenHongShuiPingLow);
		fenHongShuiPingMiddle = (TextView) findViewById(R.id.fenHongShuiPingMiddle);
		fenHongShuiPingHigh = (TextView) findViewById(R.id.fenHongShuiPingHigh);
		commitInsurancePlan = (TextView) findViewById(R.id.commitInsurancePlan);
		shouHuXingBack = (ImageView) findViewById(R.id.shouHuXingBack);
		shouHuXingMenu = (ImageView) findViewById(R.id.shouHuXingMenu);
		
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
                android.R.layout.simple_spinner_item, Define.periodShiJiTianShi);
		baoEAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.baoEShiJiTianShi);
		jiBingBaoEAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.zhongJiBaoEShiJiTianShi);
		
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
		
		ageSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				ageShow.setText(ageSpinner.getSelectedItem().toString()+"岁");
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
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		radioGroup.setOnCheckedChangeListener(this);
		
		//文本框的焦点事件
		baoE.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(baoE.hasFocus() == true){
//					Toast.makeText(getApplicationContext(), "最低3万，为1万的整数倍", 3000).show();
				}else{
					String be = baoE.getText().toString();
					if(!be.equals("")){
						if(Integer.valueOf(be)<3){
							Toast.makeText(getApplicationContext(), "世纪天使终身保险保额的值应不小于3", 3000).show();
							baoE.setText("");
						}else{
							zhuXianBaoEShow.setText(be+Define.WanYuan);
						}
					}
				}
			}
		});
		jiBingBaoE.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(jiBingBaoE.hasFocus() == true){
//					Toast.makeText(getApplicationContext(), "最低0万，为1万的整数倍", 3000).show();
				}else{
					if("".equals(baoE.getText().toString())&&"".equals(jiBingBaoE.getText().toString())){
					}else if("".equals(baoE.getText().toString())&&jiBingBaoE.getText().toString() != null){
						Toast.makeText(getApplicationContext(), "请先输入终身保险保额的值",
								3000).show();
						jiBingBaoE.setText("");
					}else if(baoE.getText().toString() != null&&jiBingBaoE.getText().toString() != null){
						String zj = jiBingBaoE.getText().toString();
//						if(!zj.equals("")){
////							if(Integer.valueOf(zj) >= 3){
////	//							Toast.makeText(getApplicationContext(), yiLiao+"万元", Toast.LENGTH_SHORT).show();
////							}else{
////								Toast.makeText(getApplicationContext(), "重大疾病险保额的值应不小于3", 3000).show();
////								jiBingBaoE.setText("");
////							}
//						}else{
//						}
						}
				}
			}
		});
		
		//文本框的文本改变事件
		jiBingBaoE.addTextChangedListener(new TextWatcher() {
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
				zhongJiBaoEShow.setText(jiBingBaoE.getText().toString()+Define.WanYuan);
				initData();
				zxBaoFeiShow.setText(zhuBaoFeiD+Define.Yuan);
				huoMianBaoEShow.setText(zhuBaoFeiD+Define.Yuan);
				zjBaoFeiShow.setText(zhongJiBaoFeiD+Define.Yuan);
				hmBaoFeiShow.setText(huoMianBaoFeiD+Define.Yuan);
				baoFeiTotal.setText(totalBaoFeiD+Define.Yuan);
				}
			}
		});
		
//		initData();
	}

	private void initData() {
		shxService = new ShouHuXService();
		hongli = new HongLi();
		hongli.setSex(sex);
		hongli.setAge(Integer.valueOf(ageSpinner.getSelectedItem().toString()));
		hongli.setYear(Integer.valueOf(periodSpinner.getSelectedItem().toString()));
		hongli.setLevel(level);
		hongli.setBaoE(Integer.valueOf(baoE.getText().toString()));
		hongli.setFenShu(Integer.valueOf(jiBingBaoE.getText().toString()));
		if (db == null) {
			db = DBDAO.getSqliteDB(this);
		}
		zhuBaoFeiD=shxService.findBaoFei(hongli, this, Define.DB_Type_BaoFei_ShiJiTianShi,db);		
		zhongJiBaoFeiD=shxService.findZhongJiBaoFei(hongli, this, Define.DB_Type_FuJiaBaoFei_ZhongJi,db);
		huoMianBaoFeiD=shxService.findHuoMianBaoFei(hongli, this, Define.DB_Type_FuJiaBaoFei_HuoMian,zhuBaoFeiD,db);
		totalBaoFeiD=zhuBaoFeiD+zhongJiBaoFeiD+huoMianBaoFeiD;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.commitInsurancePlan:
				uName = userName.getText().toString();
				zhuBaoE = baoE.getText().toString();
				zjBaoE = jiBingBaoE.getText().toString(); 
				
				if("".equals(zhuBaoE)&&"".equals(zjBaoE)){
					Toast.makeText(getApplicationContext(), "请先输入各保额的值",
							Toast.LENGTH_LONG).show();
				}else if("".equals(zhuBaoE)&&zjBaoE != null){
					Toast.makeText(getApplicationContext(), "请先输入主险保额的值",
							Toast.LENGTH_LONG).show();
					jiBingBaoE.setText("");
				}else if(!"".equals(zhuBaoE)&&"".equals(zjBaoE)){
						Toast.makeText(getApplicationContext(), "请先输入重大疾病险保额的值",
								Toast.LENGTH_LONG).show();
				}else if(zhuBaoE != null&&zjBaoE != null){
					if(Integer.valueOf(zhuBaoE) >= 3 && Integer.valueOf(zjBaoE) >= 3){
						Intent intent = new Intent(ShiJiTSPlanA.this,ShiJiTSShowA.class);
//						intent.putExtra("name", uName);
//						intent.putExtra("sex", sex);
//						intent.putExtra("age", ageSpinner.getSelectedItem().toString());
//						intent.putExtra("period", periodSpinner.getSelectedItem().toString());
//						intent.putExtra("baoE", zhuBaoE);
//						intent.putExtra("zhongJiBaoE", zjBaoE);
//						intent.putExtra("level", level);
						intent.putExtra("sex", sex);
						intent.putExtra("age", ageSpinner.getSelectedItem().toString());
						intent.putExtra("period", periodSpinner.getSelectedItem().toString());
						intent.putExtra("baoE", zhuBaoE);
						intent.putExtra("zhongJiBaoE", zjBaoE);
						intent.putExtra("level", level);
						
						intent.putExtra("zhuBaoFeiD", zhuBaoFeiD);
						intent.putExtra("zhongJiBaoFeiD", zhongJiBaoFeiD);
						intent.putExtra("huoMianBaoFeiD", huoMianBaoFeiD);
//						intent.putExtra("zhongJiBaoE", jiBingBaoE.getText().toString().replace(Define.Wan, ""));
						startActivity(intent);
						finish();
					}else{
						Toast.makeText(getApplicationContext(), "您输入的数值不正确", 3000).show();
						jiBingBaoE.setText("");
					}
				}
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
			Toast.makeText(getApplicationContext(), "点击了星号", Toast.LENGTH_SHORT).show();
			break;
		
		}
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
//		 provinceSpinner.setSelection(3,true);  //设置默认选中项，此处为默认选中第4个值
//		view.setText("你的血型是："+m[arg2]);
		// TODO Auto-generated method stub
//		Toast.makeText(getApplicationContext(), view + "点击了星号", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		radioButton=(RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
		sex = radioButton.getText().toString();
		sexShow.setText(sex+"性");
	}
}
