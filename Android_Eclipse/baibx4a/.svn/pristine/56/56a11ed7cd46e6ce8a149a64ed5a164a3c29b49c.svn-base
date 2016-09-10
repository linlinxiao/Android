package com.baoxiao.activity.productshow.zhiyuerensheng;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.baoxiao.R;
import com.baoxiao.model.HongLi;
import com.baoxiao.service.productshow.ZhiNengXingService;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;

public class ZhiYueRenShengPlanA extends Activity implements
	OnClickListener,OnCheckedChangeListener{

		//定义从上往下
			 private EditText baoE,baoF,zhongJiBaoE,yiWaiShangHaiBaoE,yiWaiYiLiaoBaoE;
			 private CheckBox fuJiaYiLiao,buFuJiaYiLiao;
			 private String zhuBaoE,zhuBaoF,zjBaoE,ywyl,ywsh,huoMian="";
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
			
			//设计计划书预览的控件
			 //TODO 设计计划书预览的控件
			 private TextView ageShow,sexShow,
			 			zxPeriodShow,
			 			zhuXianBaoEShow,zhongJiBaoEShow,huoMianBaoEShow,ywshBaoEShow,yiLiaoBaoEShow,
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
//				//设置竖屏
//				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				setContentView(R.layout.ps_zhiyuerensheng_plan);
				
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
				yiWaiShangHaiBaoE=(EditText) findViewById(R.id.yiWaiShangHaiBaoE);
				yiWaiYiLiaoBaoE=(EditText) findViewById(R.id.yiWaiYiLiaoBaoE);
				fuJiaYiLiao=(CheckBox) findViewById(R.id.fuJiaYiLiao);
				buFuJiaYiLiao=(CheckBox) findViewById(R.id.buFuJiaYiLiao);
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
				ywshBaoEShow = (TextView) findViewById(R.id.ywshBaoEShow);
				yiLiaoBaoEShow = (TextView) findViewById(R.id.yiLiaoBaoEShow);
				
				zxPeriodShow = (TextView) findViewById(R.id.zxPeriodShow);
				
				zxBaoFeiShow = (TextView) findViewById(R.id.zxBaoFeiShow);
				baoFeiTotal = (TextView) findViewById(R.id.baoFeiTotal);
				
				ageAdapter = new ArrayAdapter<String>(this,
		                android.R.layout.simple_spinner_item, Define.ageZhiYueRenSheng);
				periodAdapter = new ArrayAdapter<String>(this,
		                android.R.layout.simple_spinner_item, Define.periodZhiYueRenShengAll);
				
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
				fuJiaYiLiao.setOnClickListener(this);
				buFuJiaYiLiao.setOnClickListener(this);
				
				radioGroup.setOnCheckedChangeListener(this);
				
				ageSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) {
						ageSpinnerValue = Integer.valueOf(ageSpinner.getSelectedItem().toString());
//						updateSpinner(ageSpinnerValue);
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
						zxPeriodShow.setText(zxPeriodValue+Define.Nian);
					}
					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
				
				//终身寿险保费事件
				baoF.setOnFocusChangeListener(new OnFocusChangeListener() {
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						String bf = baoF.getText().toString();
						if(baoF.hasFocus() == false){
							if(bf.equals("")){
							}else{
								if(Integer.valueOf(bf) >= 4000)
										{}else{
									Toast.makeText(getApplicationContext(), "智悦人生终身保险保费最低为4000",Toast.LENGTH_LONG).show();
									baoF.setText("");
								}
							}
						}else{
//							Toast.makeText(getApplicationContext(), "智悦人生终身保险保费最低为4000", Toast.LENGTH_LONG).show();
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
				
				//终身寿险保额事件
				baoE.setOnFocusChangeListener(new OnFocusChangeListener() {
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						String be = baoE.getText().toString();
						String bf = baoF.getText().toString();
						if(baoE.hasFocus() == false){
							if(!"".equals(be)){
								if("".equals(bf)){
									Toast.makeText(getApplicationContext(), "请先输入智悦人生终身寿险保费的值", Toast.LENGTH_LONG).show();
								}else{
									int bfValue = Integer.valueOf(bf);
									int beValue = Integer.valueOf(be);
									if(bfValue<=10000){      //multiply乘的意思  divide约分，除的意思
										if(beValue>=Arithmetic4Double.div(Arithmetic4Double.multi(bfValue, 20),10000)){
										}else{
											Toast.makeText(getApplicationContext(), "保额最低为保费的20倍", Toast.LENGTH_LONG).show();
											baoE.setText("");
										}
									}else{
//										if(beValue>=20&&beValue>=Arithmetic4Double.div(
//												Arithmetic4Double.multi(bfValue, 5),10000)&&
//												beValue<=Arithmetic4Double.div(
//														Arithmetic4Double.multi(bfValue, 75),10000)){
//										}else{
//											Toast.makeText(getApplicationContext(), "该值最低投保保额为20万元", 3000).show();
//											baoE.setText("");
//										}
										Toast.makeText(getApplicationContext(), "保额最低为20万",Toast.LENGTH_LONG).show();
									}
								}
							}
						}else{
//							if(!"".equals(bf)){
//								if(Integer.valueOf(bf)<=10000){
//									Toast.makeText(getApplicationContext(), "保额最低为8万,保额为保费的20倍", Toast.LENGTH_LONG).show();
//								}else {
//									Toast.makeText(getApplicationContext(), "保额最低为20万", Toast.LENGTH_LONG).show();
//								}
//							}else{
//								Toast.makeText(getApplicationContext(), "请先输入智悦人生终身寿险保费的值", Toast.LENGTH_LONG).show();
//							}
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
									Toast.makeText(getApplicationContext(), "请先输入智悦人生终身寿险保额的值", Toast.LENGTH_LONG).show();
									zhongJiBaoE.setText("");
								}else{
									if(Integer.valueOf(zj)  <= Integer.valueOf(be)){
									}else{
										Toast.makeText(getApplicationContext(), "该值不能大于智悦人生终身寿险的保额", Toast.LENGTH_LONG).show();
										zhongJiBaoE.setText("");
									}
								}
							}
						}else{
//							Toast.makeText(getApplicationContext(), "该值最低为0万元，且不能大于智悦人生终身寿险的保额", Toast.LENGTH_LONG).show();
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
				
				//附加意外伤害保险保额事件
				yiWaiShangHaiBaoE.setOnFocusChangeListener(new OnFocusChangeListener() {
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						String sh = yiWaiShangHaiBaoE.getText().toString();
						Double bf = 0.0;
						if(yiWaiShangHaiBaoE.hasFocus() == false){
							if(sh.equals("")){
							}else{
//								if(!baoF.getText().toString().equals("")){
//									bf = Arithmetic4Double.div(
//											Arithmetic4Double.multi(
//											Double.valueOf(baoF.getText().toString()),100),10000);
//									
//								}
//								最低为6万最高为保费的100倍，并不能大于100万
//								if(Integer.valueOf(sh) >= 6 && Integer.valueOf(sh) <= bf && Integer.valueOf(sh) <= 100){
								if(Integer.valueOf(sh) >= 6){
								}else{
									Toast.makeText(getApplicationContext(), "该保额最低为6万", Toast.LENGTH_LONG).show();
									yiWaiShangHaiBaoE.setText("");
								}
							}
						}else{
//							Toast.makeText(getApplicationContext(), "该保额最低为6万", Toast.LENGTH_LONG).show();
						}
					}
				});	
				//文本框的文本改变事件
				yiWaiShangHaiBaoE.addTextChangedListener(new TextWatcher() {
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
						if(!"".equals(yiWaiShangHaiBaoE.getText().toString())){
							ywshBaoEShow.setText(yiWaiShangHaiBaoE.getText().toString()+Define.WanYuan);
						}
					}
					
				});
				
				//附加意外医疗保险保额事件
				//保额不能大于无忧意外伤害保险，并且保额不能大于10万
				yiWaiYiLiaoBaoE.setOnFocusChangeListener(new OnFocusChangeListener() {
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						String yl = yiWaiYiLiaoBaoE.getText().toString();
						String ys = yiWaiShangHaiBaoE.getText().toString();
						if(yiWaiYiLiaoBaoE.hasFocus() == false){
							if(yl.equals("")){
							}else{
//								if(!"".equals(ys)){
//									if(Integer.valueOf(yl) <= Integer.valueOf(ys) && Integer.valueOf(yl) <= 10){
//									}else{
//										Toast.makeText(getApplicationContext(), "该保额最低为1万", Toast.LENGTH_LONG).show();
//										yiWaiYiLiaoBaoE.setText("");
//										
//									}
//								}
								if(Integer.valueOf(yl)>=1){
								}else{
									Toast.makeText(getApplicationContext(), "该保额最低为1万", Toast.LENGTH_LONG).show();
								}
							}
						}else{
//							Toast.makeText(getApplicationContext(), "该保额最低为1万", Toast.LENGTH_LONG).show();
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
						ywsh = yiWaiShangHaiBaoE.getText().toString();
//						if(!"".equals(ywyl)){
//									if(!"".equals(ywsh)){
//										if(Integer.valueOf(ywyl) <= Integer.valueOf(ywsh) && Integer.valueOf(ywyl) <= 10){
//											yiLiaoBaoEShow.setText(ywyl+Define.WanYuan);
//										}else{
//											Toast.makeText(getApplicationContext(), "该保额最低为1万", Toast.LENGTH_LONG).show();
//											yiWaiYiLiaoBaoE.setText("");
//										}
//									}
//						}
						yiLiaoBaoEShow.setText(ywyl+Define.WanYuan);
						
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
							Intent intent = new Intent(this,ZhiYueRenShengShowA.class);
							intent.putExtra("sex", sex);
							intent.putExtra("age", ageSpinner.getSelectedItem().toString());
							intent.putExtra("period", periodSpinner.getSelectedItem().toString());
							intent.putExtra("baoE", zhuBaoE);
							intent.putExtra("zhongJiBaoE", zjBaoE);
							intent.putExtra("yiWaiShangHaiBaoE", ywsh);
							intent.putExtra("yiWaiYiLiBaoE", ywyl);
							intent.putExtra("level", level);
							intent.putExtra("zhuBaoF", zhuBaoF);
							intent.putExtra("huoMian", huoMian);
							startActivity(intent);
							finish();
					}
					break;
				case R.id.fenHongShuiPingLow:
					fenHongShuiPingHigh.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
					fenHongShuiPingMiddle.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
					fenHongShuiPingLow.setBackgroundColor(android.graphics.Color.parseColor("#F3C095"));
					level=Define.Level_Low;
//					Toast.makeText(getApplicationContext(), "点击了low", Toast.LENGTH_SHORT).show();
					break;
				case R.id.fenHongShuiPingMiddle:
					fenHongShuiPingHigh.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
					fenHongShuiPingMiddle.setBackgroundColor(android.graphics.Color.parseColor("#F3C095"));
					fenHongShuiPingLow.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
//					Toast.makeText(getApplicationContext(), "点击了middle", Toast.LENGTH_SHORT).show();
					level=Define.Level_Middle;
					break;
				case R.id.fenHongShuiPingHigh:
					fenHongShuiPingHigh.setBackgroundColor(android.graphics.Color.parseColor("#F3C095"));
					fenHongShuiPingMiddle.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
					fenHongShuiPingLow.setBackgroundColor(android.graphics.Color.parseColor("#DDDDDD"));
//					Toast.makeText(getApplicationContext(), "点击了high", Toast.LENGTH_SHORT).show();
					level=Define.Level_High;
					break;
				case R.id.fuJiaYiLiao:
					huoMian = "true";
					buFuJiaYiLiao.setChecked(false);
					//显示豁免保险
					huoMianLinear.setVisibility(View.VISIBLE);
					huoMianImage.setVisibility(View.VISIBLE);
					break;
				case R.id.buFuJiaYiLiao:
					huoMian = "";
					fuJiaYiLiao.setChecked(false);
					//隐藏豁免保险
					huoMianLinear.setVisibility(View.GONE);
					huoMianImage.setVisibility(View.GONE);
					break;
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
