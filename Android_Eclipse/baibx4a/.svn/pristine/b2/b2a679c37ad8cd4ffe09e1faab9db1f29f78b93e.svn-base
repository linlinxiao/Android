package com.baoxiao.activity.productshow.zhinengxing;

import com.baoxiao.R;
import com.baoxiao.model.HongLi;
import com.baoxiao.service.productshow.ZhiNengXingService;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ZhiNengXingPlanA extends Activity implements 
OnClickListener,OnCheckedChangeListener{

	//定义从上往下
		 private EditText baoE,baoF,zhongJiBaoE,yiWaiYiLiaoBaoE;
		 private CheckBox fuJiaYiLiao;
		 private String zhuBaoE,zhuBaoF,zjBaoE,ywyl,huoMian="";
		 private String sex = "";
		 private Spinner ageSpinner = null; 
		 private RadioGroup radioGroup;
		 private RadioButton radioButton;
		 private Spinner periodSpinner = null; 
		 private TextView fenHongShuiPingLow,fenHongShuiPingMiddle,fenHongShuiPingHigh;
		 private String level=Define.Level_Middle;
		 private LinearLayout huoMianLinear;
		 private ImageView huoMianImage;
		 private TextView commitInsurancePlan;
		 private ImageView pingAnFuBack,shouHuXingMenu;
		 private Boolean isfuJia = false;
		
		//设计计划书预览的控件
		 //TODO 设计计划书预览的控件
		 private TextView ageShow,sexShow,
		 			zxPeriodShow,zjPeriodShow,ylPerioidShow,
		 			zhuXianBaoEShow,zhongJiBaoEShow,huoMianBaoEShow,yiLiaoBaoEShow,
		 			zxBaoFeiShow,baoFeiTotal;
		 //设计计划书预览的计算值
		 private ZhiNengXingService znxService;
		 private HongLi hongli;
		 private Double zhuBaoFeiD;
		 
		 private  ArrayAdapter<String> ageAdapter=null;
		 private  ArrayAdapter<String> periodAdapter=null;
		 
		 private int ageSpinnerValue;
		 
		 
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			//设置无标题
			requestWindowFeature(Window.FEATURE_NO_TITLE);
//			//设置竖屏
//			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			setContentView(R.layout.ps_zhinengxing_plan);
			
			znxService = new ZhiNengXingService();
			hongli = new HongLi();
			
			//初始化控件
			radioGroup=(RadioGroup) findViewById(R.id.sex);
			//设置默认的单选按钮
			radioGroup.check(R.id.maleRadioButton);
			
			radioButton=(RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
			//获取默认被被选中值  
			sex = radioButton.getText().toString();
					
			ageSpinner=(Spinner) findViewById(R.id.age);
			periodSpinner=(Spinner) findViewById(R.id.period);
			baoE=(EditText) findViewById(R.id.baoE);
			baoF=(EditText) findViewById(R.id.baoF);
			zhongJiBaoE=(EditText) findViewById(R.id.jiBingBaoE);
			yiWaiYiLiaoBaoE=(EditText) findViewById(R.id.yiWaiYiLiaoBaoE);
			fuJiaYiLiao=(CheckBox) findViewById(R.id.fuJiaYiLiao);
//			buFuJiaYiLiao=(CheckBox) findViewById(R.id.buFuJiaYiLiao);
			fenHongShuiPingLow = (TextView) findViewById(R.id.fenHongShuiPingLow);
			fenHongShuiPingMiddle = (TextView) findViewById(R.id.fenHongShuiPingMiddle);
			fenHongShuiPingHigh = (TextView) findViewById(R.id.fenHongShuiPingHigh);
			huoMianLinear = (LinearLayout) findViewById(R.id.huoMianLinear);
			huoMianImage = (ImageView) findViewById(R.id.huoMianImage);
			commitInsurancePlan = (TextView) findViewById(R.id.commitInsurancePlan);
			shouHuXingMenu = (ImageView) findViewById(R.id.shouHuXingMenu);
			pingAnFuBack = (ImageView) findViewById(R.id.pingAnFuBack);
			
			huoMianLinear.setVisibility(View.GONE);
			huoMianImage.setVisibility(View.GONE);
			
			//计划书预览控件初始化
			ageShow = (TextView) findViewById(R.id.ageShow);
			sexShow = (TextView) findViewById(R.id.sexShow);
			
			zhuXianBaoEShow = (TextView) findViewById(R.id.zhuXianBaoEShow);
			zhongJiBaoEShow = (TextView) findViewById(R.id.zhongJiBaoEShow);
			huoMianBaoEShow = (TextView) findViewById(R.id.huoMianBaoEShow);
			yiLiaoBaoEShow = (TextView) findViewById(R.id.yiLiaoBaoEShow);
			
			zxPeriodShow = (TextView) findViewById(R.id.zxPeriodShow);
			zjPeriodShow = (TextView) findViewById(R.id.zjPeriodShow);
			ylPerioidShow = (TextView) findViewById(R.id.ylPerioidShow);
			
			zxBaoFeiShow = (TextView) findViewById(R.id.zxBaoFeiShow);
			baoFeiTotal = (TextView) findViewById(R.id.baoFeiTotal);
			
			ageAdapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item, Define.ageZhiNengXin);
			periodAdapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item, Define.periodZhiNengXingAll);
			
			//设置下拉列表弹出样式
			ageAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
			periodAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
			
			ageSpinner.setAdapter(ageAdapter);
			periodSpinner.setAdapter(periodAdapter);
			
			fenHongShuiPingLow.setOnClickListener(this);
			fenHongShuiPingMiddle.setOnClickListener(this);
			fenHongShuiPingHigh.setOnClickListener(this);
			commitInsurancePlan.setOnClickListener(this);
			shouHuXingMenu.setOnClickListener(this);
			pingAnFuBack.setOnClickListener(this);
			fuJiaYiLiao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					isfuJia = isChecked;
					if (isChecked) {
						huoMianLinear.setVisibility(View.VISIBLE);
						huoMianImage.setVisibility(View.VISIBLE);
					}else{
						huoMianLinear.setVisibility(View.GONE);
						huoMianImage.setVisibility(View.GONE);
					}
				}
			});
//			buFuJiaYiLiao.setOnClickListener(this);
			
			radioGroup.setOnCheckedChangeListener(this);
			
			ageSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					ageSpinnerValue = Integer.valueOf(ageSpinner.getSelectedItem().toString());
//					updateSpinner(ageSpinnerValue);
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
					int hmPeriodValue = Integer.valueOf(zxPeriodValue)-1;
					zxPeriodShow.setText(zxPeriodValue+Define.Nian);
					zjPeriodShow.setText(zxPeriodValue+Define.Nian);
					ylPerioidShow.setText(zxPeriodValue+Define.Nian);
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
				}
			});
			
			//终身寿险保额事件
			baoE.setOnFocusChangeListener(new OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					String be = baoE.getText().toString();
					String bf = baoF.getText().toString();
					if(baoE.hasFocus() == false){
						if(be.equals("")){
						}else{
							if(!"".equals(bf)){
								if(Integer.valueOf(be) >= 10 && Integer.valueOf(be) <= 150){
									if(Integer.valueOf(be) > Arithmetic4Double.div(Arithmetic4Double.multi(Double.valueOf(bf), 80), 10000)){
										Toast.makeText(getApplicationContext(), "保额值最高不能超过保费值的80倍", Toast.LENGTH_LONG).show();
										baoF.setText("");
									}
								}else{
									Toast.makeText(getApplicationContext(), "最低保额设置为10万,最高不超过150万", Toast.LENGTH_LONG).show();
									baoE.setText("");
								}
							}else{
								Toast.makeText(getApplicationContext(), "请先输入智能星终身寿险保费的值", Toast.LENGTH_LONG).show();
							}
						}
					}else{
//						Toast.makeText(getApplicationContext(), "最低保额设置为10万,最高不超过150万", Toast.LENGTH_LONG).show();
					}
				}
			});	
			//文本框的文本改变事件
			baoE.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
				}
				@Override
				public void onTextChanged(CharSequence s, int start, int before,
						int count) {
				}
				//文本改变之后的方法
				@Override
				public void afterTextChanged(Editable s) {
					if(!"".equals(baoE.getText().toString())&&baoE.getText().toString() != null){
						zhuXianBaoEShow.setText(baoE.getText().toString()+Define.WanYuan);
					}
				}
				
			});
			baoF.setOnFocusChangeListener(new OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					String bf = baoF.getText().toString();
					if(baoF.hasFocus() == false){
						if(bf.equals("")){
						}else{
							if(Integer.valueOf(bf) >= 4000 && Integer.valueOf(bf) <= 30000 
									&& Integer.valueOf(bf)%500 == 0
									){
							}else{
								Toast.makeText(getApplicationContext(), "智能星终身保险保费的值应不小于4000,不大于30000并且为500的整数倍", Toast.LENGTH_LONG).show();
								baoF.setText("");
							}
						}
					}else{
//						Toast.makeText(getApplicationContext(), "该值应不小于4000,不大于30000并且为500的整数倍", Toast.LENGTH_LONG).show();
					}
				}
			});	
			//文本框的文本改变事件
			baoF.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
				}
				@Override
				public void onTextChanged(CharSequence s, int start, int before,
						int count) {
				}
				//文本改变之后的方法
				@Override
				public void afterTextChanged(Editable s) {
					if(!"".equals(baoF.getText().toString())){
						zxBaoFeiShow.setText(baoF.getText().toString()+Define.Yuan);
						huoMianBaoEShow.setText(baoF.getText().toString()+Define.Yuan);
						baoFeiTotal.setText(baoF.getText().toString()+Define.Yuan);
					}
				}
				
			});
			
			//重大疾病保险保额事件
			zhongJiBaoE.setOnFocusChangeListener(new OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					String zj = zhongJiBaoE.getText().toString();
					String be = baoE.getText().toString();
					if(zhongJiBaoE.hasFocus() == false){
						if(zj.equals("")){
						}else{
							if(be.equals("")){
								Toast.makeText(getApplicationContext(), "请先输入智能星终身寿险保额的值", Toast.LENGTH_LONG).show();
								zhongJiBaoE.setText("");
							}else{
								if(Integer.valueOf(zj) >= 5 && Integer.valueOf(zj) <= Integer.valueOf(be)){
								}else{
									Toast.makeText(getApplicationContext(), "最低保额为5万元，最高不能大于智能星终身寿险的保额", Toast.LENGTH_LONG).show();
									zhongJiBaoE.setText("");
								}
							}
						}
					}else{
//						Toast.makeText(getApplicationContext(), "低保额为5万元，最高不能大于智能星终身寿险的保额", Toast.LENGTH_LONG).show();
					}
				}
			});	
			//文本框的文本改变事件
			zhongJiBaoE.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
				}
				@Override
				public void onTextChanged(CharSequence s, int start, int before,
						int count) {
				}
				//文本改变之后的方法
				@Override
				public void afterTextChanged(Editable s) {
					if(!"".equals(zhongJiBaoE.getText().toString())){
						zhongJiBaoEShow.setText(zhongJiBaoE.getText().toString()+Define.WanYuan);
					}
				}
				
			});
			
			//附加意外医疗保险保额事件
			yiWaiYiLiaoBaoE.setOnFocusChangeListener(new OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					String yl = yiWaiYiLiaoBaoE.getText().toString();
					if(yiWaiYiLiaoBaoE.hasFocus() == false){
						if(yl.equals("")){
						}else{
							if(Integer.valueOf(yl) >0 ){
							}else{
								Toast.makeText(getApplicationContext(), "最低保额为0万元", Toast.LENGTH_LONG).show();
								yiWaiYiLiaoBaoE.setText("");
							}
						}
					}else{
						Toast.makeText(getApplicationContext(), "最低保额为0万元", Toast.LENGTH_LONG).show();
					}
				}
			});	
			//文本框的文本改变事件
			yiWaiYiLiaoBaoE.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
				}
				@Override
				public void onTextChanged(CharSequence s, int start, int before,
						int count) {
				}
				//文本改变之后的方法
				@Override
				public void afterTextChanged(Editable s) {
					ywyl = yiWaiYiLiaoBaoE.getText().toString();
					if(!"".equals(yiWaiYiLiaoBaoE.getText().toString())){
							if(Integer.valueOf(ywyl)>0 ){
								yiLiaoBaoEShow.setText(ywyl+Define.WanYuan);
							}else{
								Toast.makeText(getApplicationContext(), "最低保额为0万元，请重新输入",Toast.LENGTH_LONG).show();
							}
					}
				}
				
			});
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.commitInsurancePlan:
				zhuBaoE = baoE.getText().toString();
				zhuBaoF = baoF.getText().toString();
				zjBaoE = zhongJiBaoE.getText().toString(); 
				
				
				if(!"".equals(zhuBaoE)&&!"".equals(zhuBaoF)&&!"".equals(zjBaoE)){
						Intent intent = new Intent(this,ZhiNengXingShowA.class);
						intent.putExtra("sex", sex);
						intent.putExtra("age", ageSpinner.getSelectedItem().toString());
						intent.putExtra("period", periodSpinner.getSelectedItem().toString());
						intent.putExtra("baoE", zhuBaoE);
						intent.putExtra("zhongJiBaoE", zjBaoE);
						intent.putExtra("yiWaiYiLiBaoE", ywyl);
						intent.putExtra("level", level);
						intent.putExtra("zhuBaoF", zhuBaoF);
						intent.putExtra("huoMian", huoMian);
						intent.putExtra("isfuJia", isfuJia);
						startActivity(intent);
						finish();
				}
				break;
			case R.id.fenHongShuiPingLow:
				fenHongShuiPingHigh.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
				fenHongShuiPingMiddle.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
				fenHongShuiPingLow.setBackgroundColor(android.graphics.Color.parseColor("#F3C095"));
				level=Define.Level_Low;
//				Toast.makeText(getApplicationContext(), "点击了low", Toast.LENGTH_SHORT).show();
				break;
			case R.id.fenHongShuiPingMiddle:
				fenHongShuiPingHigh.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
				fenHongShuiPingMiddle.setBackgroundColor(android.graphics.Color.parseColor("#F3C095"));
				fenHongShuiPingLow.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
//				Toast.makeText(getApplicationContext(), "点击了middle", Toast.LENGTH_SHORT).show();
				level=Define.Level_Middle;
				break;
			case R.id.fenHongShuiPingHigh:
				fenHongShuiPingHigh.setBackgroundColor(android.graphics.Color.parseColor("#F3C095"));
				fenHongShuiPingMiddle.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
				fenHongShuiPingLow.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
//				Toast.makeText(getApplicationContext(), "点击了high", Toast.LENGTH_SHORT).show();
				level=Define.Level_High;
				break;
//			case R.id.fuJiaYiLiao:
//				huoMian = "true";
//				buFuJiaYiLiao.setChecked(false);
//				//显示豁免保险
//				huoMianLinear.setVisibility(View.VISIBLE);
//				huoMianImage.setVisibility(View.VISIBLE);
//				break;
//			case R.id.buFuJiaYiLiao:
//				huoMian = "";
//				fuJiaYiLiao.setChecked(false);
//				//隐藏豁免保险
//				huoMianLinear.setVisibility(View.GONE);
//				huoMianImage.setVisibility(View.GONE);
//				break;
			case R.id.pingAnFuBack:
				finish();
				break;
			case R.id.shouHuXingMenu:
				Toast.makeText(getApplicationContext(), "点击了星号", Toast.LENGTH_SHORT).show();
				break;

			
			}
			
		}
	    
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
		}

		

		public void onNothingSelected(AdapterView<?> parent) {
			
		}

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			radioButton=(RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
			sex = radioButton.getText().toString();
			sexShow.setText(sex+"性");
		}
}
