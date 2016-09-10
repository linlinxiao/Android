package com.baoxiao.activity.productshow.childrenPeace;

import com.baoxiao.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class ChildrenPingAnFuPlanA extends Activity{
	
	 private EditText et_name,baoE,//保额
	 et_advance_diseases,//疾病保额
	 et_stricken,//重疾保额
	 et_long_accident,//长期意外
	 et_medical_accident,//意外医疗
	 et_Life_insurance,
	 et_applicant_name,//投保人
	 et_add_hospital_day_totle,
	 et_jianxiang;
	 
	 private Spinner sp_age,sp_period,
	 sp_strichen,//重疾保额
//	 sp_medical_accident,//意外医疗
	 sp_Life_insurance,//定期寿险，有无社保
	 sp_applicant_age;//投保人

	 private String sex = "A",parent = "A",userid;
	 private String[] age0_17;
	 private String[] premium_period = new String[]{
			 "10年","15年","20年"
		};
	 private String[] period5_20 = new String[]{
			 "5年","10年","15年","20年"
		};
//	 private String[] accident_medical = new String[]{
//			 "A(无社保用户)","B(有社保用户)"
//		};
	 private String[] applicant_age20_60;
	 private CheckBox cb_children_accident,cb_long_accident,
	 cb_medical_accident,cb_Life_insurance,cb_accident_exemption,
	 cb_social_security,cb_standard,
	 cb_jianxiang_so,cb_jianxiang_se;
	 	 
	private View ll[] = new View[2];
	private PopupWindow popupWindow;
	private View contentView;
	private TextView mycard_pop_title;
	private LinearLayout ll_mycard_pop_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ps_children_pinganfu_plan);
		userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
		age0_17 = new String[18];
		for (int i = 0; i < age0_17.length; i++) {
			age0_17[i] = i + "岁";
		}
		applicant_age20_60 = new String[41];
		for (int i = 20; i < applicant_age20_60.length + 20; i++) {
			applicant_age20_60[i-20] = i + "岁";
		}

		cb_children_accident = (CheckBox)findViewById(R.id.cb_children_accident);
		cb_long_accident = (CheckBox)findViewById(R.id.cb_long_accident);
		cb_medical_accident = (CheckBox)findViewById(R.id.cb_medical_accident);
		cb_Life_insurance = (CheckBox)findViewById(R.id.cb_Life_insurance);
		cb_accident_exemption = (CheckBox)findViewById(R.id.cb_accident_exemption);
		cb_social_security = (CheckBox)findViewById(R.id.cb_social_security);
		cb_standard = (CheckBox)findViewById(R.id.cb_standard);
		cb_jianxiang_so = (CheckBox)findViewById(R.id.cb_jianxiang_so);
		cb_jianxiang_se = (CheckBox)findViewById(R.id.cb_jianxiang_se);
		
		cb_children_accident.setOnCheckedChangeListener(checkedChangeListener);
		cb_long_accident.setOnCheckedChangeListener(checkedChangeListener);
		cb_medical_accident.setOnCheckedChangeListener(checkedChangeListener);
		cb_Life_insurance.setOnCheckedChangeListener(checkedChangeListener);
		cb_accident_exemption.setOnCheckedChangeListener(checkedChangeListener);
		cb_standard.setOnCheckedChangeListener(checkedChangeListener);
		
		et_name = ((EditText)findViewById(R.id.et_name));
		baoE = ((EditText)findViewById(R.id.baoE));//保额
		et_advance_diseases = ((EditText)findViewById(R.id.et_advance_diseases));//疾病保额
		et_stricken = ((EditText)findViewById(R.id.et_stricken));//重疾保额
		et_long_accident = ((EditText)findViewById(R.id.et_long_accident));//长期意外
		et_medical_accident = ((EditText)findViewById(R.id.et_medical_accident));//意外医疗
		et_Life_insurance = ((EditText)findViewById(R.id.et_Life_insurance));//意外医疗
		et_applicant_name = ((EditText)findViewById(R.id.et_applicant_name));//投保人
		et_add_hospital_day_totle = ((EditText)findViewById(R.id.et_add_hospital_day_totle));
		et_jianxiang = ((EditText)findViewById(R.id.et_jianxiang));
		
		((RadioGroup)findViewById(R.id.sex)).check(R.id.maleRadioButton);
		((RadioButton)findViewById(R.id.maleRadioButton)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.femaleRadioButton)).setOnCheckedChangeListener(checkedChangeListener);

		((RadioGroup)findViewById(R.id.parent)).check(R.id.fatherRadioButton);
		((RadioButton)findViewById(R.id.fatherRadioButton)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.motherRadioButton)).setOnCheckedChangeListener(checkedChangeListener);
//		sex_radio = (RadioButton) findViewById(sex.getCheckedRadioButtonId());
		//获取默认被被选中值  
		sp_age = (Spinner) findViewById(R.id.sp_age);
		sp_period = (Spinner) findViewById(R.id.sp_period);
		sp_strichen = (Spinner) findViewById(R.id.sp_strichen);//重疾保额
//		sp_medical_accident = (Spinner) findViewById(R.id.sp_medical_accident);//意外医疗
		sp_Life_insurance = (Spinner) findViewById(R.id.sp_Life_insurance);//定期寿险
		sp_applicant_age = (Spinner) findViewById(R.id.sp_applicant_age);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				 R.layout.children_spinner, age0_17);
		adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sp_age.setAdapter(adapter);
		sp_age.setOnItemSelectedListener(itemSelectedListener);
		
		adapter = new ArrayAdapter<String>(this,R.layout.children_spinner, premium_period);
		adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sp_period.setAdapter(adapter);
		sp_period.setSelection(2);
				
		adapter = new ArrayAdapter<String>(this,R.layout.children_spinner, period5_20);
		adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sp_strichen.setAdapter(adapter);//重疾
		
//		adapter = new ArrayAdapter<String>(this,R.layout.children_spinner, accident_medical);
//		adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
//		sp_medical_accident.setAdapter(adapter);
		
		adapter = new ArrayAdapter<String>(this,R.layout.children_spinner, period5_20);
		adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sp_Life_insurance.setAdapter(adapter);
		
		adapter = new ArrayAdapter<String>(this,R.layout.children_spinner, applicant_age20_60);
		adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sp_applicant_age.setAdapter(adapter);
		sp_applicant_age.setSelection(10, true);
		
		contentView = LayoutInflater.from(this).inflate(R.layout.mycard_pop_window4, null);
		mycard_pop_title = (TextView)contentView.findViewById(R.id.mycard_pop_title);
		ll_mycard_pop_content = (LinearLayout)contentView.findViewById(R.id.ll_mycard_pop_content);
		ll[0] = findViewById(R.id.ll_add_hospital_day_totle);
		ll[1] = findViewById(R.id.ll_jianxiang);
		
	}
	
	private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			switch (buttonView.getId()) {
			case R.id.maleRadioButton:
				if (isChecked) {
					sex = "A";
				}else{sex = "B";}
				break;
			case R.id.femaleRadioButton:
				if (isChecked) {
					sex = "A";
				}else{sex = "B";}
				break;
			case R.id.fatherRadioButton:
				if (isChecked) {
					parent = "A";
				}else{parent = "B";}
				break;
			case R.id.motherRadioButton:
				if (isChecked) {
					parent = "A";
				}else{parent = "B";}
				break;
			case R.id.cb_children_accident:
				if (isChecked) {//重疾保额
					 et_stricken.setEnabled(true);
				}else{
					 et_stricken.setEnabled(false);
				}
				break;
			case R.id.cb_long_accident:
				if (isChecked) {
					et_long_accident.setEnabled(true);
				}else{
					et_long_accident.setEnabled(false);
				}
				break;
			case R.id.cb_medical_accident:
				if (isChecked) {
					et_medical_accident.setEnabled(true);
				}else{
					et_medical_accident.setEnabled(false);
				}
				break;
			case R.id.cb_Life_insurance:
				if (isChecked) {
					et_Life_insurance.setEnabled(true);
				}else{
					et_Life_insurance.setEnabled(false);
				}
				break;
			case R.id.cb_accident_exemption:
				break;
			case R.id.cb_standard:
				if (isChecked) {//标准组合
//					et_name.setText("");
					baoE.setText("45");//保额
					et_advance_diseases.setText("40");//疾病保额
					et_stricken.setText("5");//重疾陪护金，最高20份
					et_long_accident.setText("20");//长期意外
					et_medical_accident.setText("2");//意外医疗
					et_Life_insurance.setText("5");
//					et_applicant_name.setText("");
					
//					sp_age.setSelection(position);
//					sp_period.setSelection(position);
//					sp_strichen.setSelection(position);//重疾保额
//					sp_Life_insurance.setSelection(position);//定期寿险，有无社保
//					sp_applicant_age.setSelection(position);
					
					cb_children_accident.setChecked(true);
					cb_long_accident.setChecked(true);
					cb_medical_accident.setChecked(true);
					cb_Life_insurance.setChecked(true);
					cb_accident_exemption.setChecked(false);
					cb_social_security.setChecked(false);
					
					ll[0].setVisibility(View.GONE);
					ll[1].setVisibility(View.GONE);
				}
				break;
				
			default:
				break;
			}
		}
	};
	public void children_submit(View v){
//		0到5岁默认交费年期20年，
//		6到10岁默认交费年期15年，
//		1到15岁默认交费年期10年，
//		16到17岁默认交费年期5年，
//		只是少儿重疾陪护金和少儿定期寿险，
//		两个险种在交费年期中选择默认组合。
//		保额默认数额：
//		1、少儿平安福45万；
//		2、少儿平安福重疾40万；
//		3、重疾陪护金5份；
//		4、少儿长期意外保险20万；
//		5、少儿定期重疾5万；
//		6、附加意外伤害医疗（A）2万；
//		7、豁免少儿特定重疾；
//		8、豁免C，加强版；
		String postDate = "";
		postDate += "userid=" + userid + "&";
		//基本信息
		String name = et_name.getText().toString().trim();
		postDate += "insurant_name=" + name + "&";
//		String t_sex = sex;
//		if (parent != null) {
//			try {
//				t_sex = URLEncoder.encode(sex, "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}
		postDate += "sex=" + sex + "&";
		String age = sp_age.getSelectedItem().toString();
		age = age.replace("岁", "");
		postDate += "age=" + age + "&";
		String period = sp_period.getSelectedItem().toString();
		period = period.replace("年", "");
		postDate += "period=" + period + "&";

		//终生寿险
		String baoe = baoE.getText().toString().trim();
		if (baoe == null || "".equals(baoe)) {baoe = "45";}
		postDate += "baoe=" + baoe + "&";
		
		//提前重大疾病
		String advance_diseases = et_advance_diseases.getText().toString().trim();
		if (advance_diseases == null || "".equals(advance_diseases)) {advance_diseases = "40";}
		postDate += "advance_diseases=" + advance_diseases + "&";

		//重大疾病陪护金
		String stricken = et_stricken.getText().toString().trim();
		String strichen_period = sp_strichen.getSelectedItem().toString();
		if (stricken == null || "".equals(stricken)) {stricken = "5";}//5份
		if (!cb_children_accident.isChecked()) {
			stricken = "0";
		}
		postDate += "stricken=" + stricken + "&";
		strichen_period = strichen_period.replace("年", "");
		postDate += "strichen_period=" + strichen_period + "&";
		
		//长期意外
		String long_accident = et_long_accident.getText().toString().trim();
		if (!cb_long_accident.isChecked()) {
			long_accident = "0";
		}
		if (long_accident == null || "".equals(long_accident)) {long_accident = "20";}
		postDate += "long_accident=" + long_accident + "&";
		
		//意外医疗
		String medical_accident = et_medical_accident.getText().toString().trim();
		if (!cb_medical_accident.isChecked()) {
			medical_accident = "0";
		}
		if (medical_accident == null || "".equals(medical_accident)) {medical_accident = "2";}
		postDate += "medical_accident=" + medical_accident + "&";
		String social_security = "A";
		if (cb_social_security.isChecked()) {
			social_security = "B";
		}
		postDate += "social_security=" + social_security + "&";
		
//		add_hospital_day_totle：附加住院日额
//		jianxiang：健享人生
//		jianxiang_is：健享人生是否有社保A：无社保，B有社保
//		jianxiang_selected：是否可选，A可选，B不可选
		String add_hospital_day_totle = et_add_hospital_day_totle.getText().toString().trim();
		postDate += "add_hospital_day_totle=" + add_hospital_day_totle + "&";
		String jianxiang = et_jianxiang.getText().toString().trim();
		postDate += "jianxiang=" + jianxiang + "&";
		String jianxiang_is = "A";
		if (cb_jianxiang_so.isChecked()) {
			jianxiang_is = "B";
		}
		postDate += "jianxiang_is=" + jianxiang_is + "&";
		String jianxiang_selected = "B";
		if (cb_jianxiang_se.isChecked()) {
			jianxiang_is = "A";
		}
		postDate += "jianxiang_selected=" + jianxiang_selected + "&";

		//定期寿险
		String life_insurance = et_Life_insurance.getText().toString().trim();
		if (!cb_Life_insurance.isChecked()) {
			life_insurance = "0";
		}
		if (life_insurance == null || "".equals(life_insurance)) {life_insurance = "5";}
		postDate += "life_insurance=" + life_insurance + "&";
		String life_insurance_period = sp_Life_insurance.getSelectedItem().toString();
		life_insurance_period = life_insurance_period.replace("年", "");
		postDate += "life_insurance_period=" + life_insurance_period + "&";
		//投保人信息,重疾豁免
		String applicant_name = et_applicant_name.getText().toString().trim();
		String applicant_age = sp_applicant_age.getSelectedItem().toString();
		if (!cb_accident_exemption.isChecked()) {
			applicant_age = "-1";
		}
		applicant_age = applicant_age.replace("岁", "");
		postDate += "applicant_name=" + applicant_name + "&";
		postDate += "applicant_age=" + applicant_age + "&";
		postDate += "parent=" + parent;
		
//		String url = Define.Server + "/childrenPeaceShow.action?";
		startActivity(new Intent(this,ChildrenPingAnFuShowA.class)
			.putExtra("postDate", postDate));
	}
	private OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//			0到5岁默认交费年期20年，
//			6到10岁默认交费年期15年，
//			1到15岁默认交费年期10年，
//			16到17岁默认交费年期5年，

			String strichen_age[] = null,s_age;
			s_age = sp_age.getSelectedItem().toString();
			int age = Integer.parseInt(s_age.substring(0, s_age.length() - 1));
			int po;
			if (age <= 5) {
				strichen_age = new String[]{"5年","10年","15年","20年"};
				po = 3;
			}else if(age <= 10){
				strichen_age = new String[]{"5年","10年","15年"};
				po = 2;
			}else if(age <= 15){
				strichen_age = new String[]{"5年","10年"};
				po = 1;
			}else{
				strichen_age = new String[]{"5年"};
				po = 0;
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChildrenPingAnFuPlanA.this,R.layout.children_spinner, strichen_age);
			adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
			sp_strichen.setAdapter(adapter);
			sp_strichen.setSelection(po, true);
			sp_Life_insurance.setAdapter(adapter);
			sp_Life_insurance.setSelection(po, true);
		}
		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	};
	public void peace_back(View v) {
		finish();
	}
	
	public void delect_hospital_day_totle(View v){
		ll[0].setVisibility(View.GONE);
	}
	public void delect_ll_jianxiang(View v){
		ll[1].setVisibility(View.GONE);
	}
	public void add_other(View v){
        String array[] = new String[]{
//           	 "附加意外医疗保险",
        	 "附加住院日额保险",
        	 "健享人生住院医疗保险",
        	};
        String title = "请选择附加险";
        showPop(v,title,array);
	}
	private void showPop(final View v, String title, String[] p) {
        mycard_pop_title.setText(title);
        ll_mycard_pop_content.removeAllViews();
        final CheckBox cb_pop_item[] = new CheckBox[p.length];
        for (int i = 0; i < p.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.mycard_pop_window_item4, null);
        	cb_pop_item[i] = (CheckBox)view.findViewById(R.id.cb_pop_item4);
        	cb_pop_item[i].setText(p[i]);
        	cb_pop_item[i].setChecked(ll[i].getVisibility() == View.GONE ? false:true);
        	ll_mycard_pop_content.addView(view);
		}
//        cb_pop_item[1].setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				cb_pop_item[2].setChecked(!isChecked);
//			}
//		});
//        cb_pop_item[2].setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				cb_pop_item[1].setChecked(!isChecked);
//			}
//		});
        popupWindow = new PopupWindow(contentView,LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		
        contentView.findViewById(R.id.bt_pop_cancle).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
        contentView.findViewById(R.id.bt_pop_ok).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				show_add_other(cb_pop_item);
				popupWindow.dismiss();
			}

			private void show_add_other(CheckBox[] cb_pop_item) {
				for (int i = 0; i < cb_pop_item.length; i++) {
					if (cb_pop_item[i].isChecked()) {
						ll[i].setVisibility(View.VISIBLE);
					}else{
						ll[i].setVisibility(View.GONE);
					}
				}
			}
		});
        showPopupWindow(v,contentView);		
	}
    private void showPopupWindow(View view,View contentView) {
        Button button = new Button(this);
        button.setText("11111");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        ColorDrawable colorDrawable = new ColorDrawable(Color.argb(0, 0, 0, 0));
        popupWindow.setBackgroundDrawable(colorDrawable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
}
