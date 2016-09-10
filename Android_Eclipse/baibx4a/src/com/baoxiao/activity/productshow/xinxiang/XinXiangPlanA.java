package com.baoxiao.activity.productshow.xinxiang;

import com.baoxiao.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class XinXiangPlanA extends Activity{
	
	private EditText et_name,
	et_main,//世纪天使两全保险
	et_stricken,//附加少儿重大疾病保险
	et_accident_injury_safe,//意外伤害保险
	et_injury_medical,//意外伤害医疗
	et_enjoy_health,//健享人生住院医疗保险
	et_applicant_name;//投保人姓名
	 
	private Spinner sp_age,sp_period,sp_safe_period,sp_privy_age;//年龄，缴费年期，投保人性别

	private String sex = "A",userid,privy_sex = "E";
	private CheckBox cb_stricken,
	cb_accident_injury_safe,//意外伤害保险
	cb_injury_medical,//附加意外医疗保险
	cb_accident_social_security,//附加意外医疗保险有无社保B有A无
	cb_enjoy_health_hospital,//健享人生住院医疗保险
	cb_enjoy_health_add,//健享人生住院医疗保险，可选部分
	cb_enjoy_health_social,//健享人生住院医疗保险，有无社保
	cb_add_exempt_b;//豁免B加强版
	 
	private int grade = 1;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ps_xinxiang_plan);
		
        userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
		((CheckBox)findViewById(R.id.cb_standard)).setOnCheckedChangeListener(checkedChangeListener);
		
		cb_stricken = (CheckBox)findViewById(R.id.cb_stricken);
		
		cb_accident_injury_safe = (CheckBox)findViewById(R.id.cb_accident_injury_safe);
		cb_injury_medical = (CheckBox)findViewById(R.id.cb_injury_medical);
		cb_accident_social_security = (CheckBox)findViewById(R.id.cb_accident_social_security);
		
		cb_enjoy_health_hospital = (CheckBox)findViewById(R.id.cb_enjoy_health_hospital);
		cb_enjoy_health_add = (CheckBox)findViewById(R.id.cb_enjoy_health_add);
		cb_enjoy_health_social = (CheckBox)findViewById(R.id.cb_enjoy_health_social);
		cb_add_exempt_b = (CheckBox)findViewById(R.id.cb_add_exempt_b);
		
		et_name = ((EditText)findViewById(R.id.et_name));
		et_main = ((EditText)findViewById(R.id.et_main));
		et_stricken = ((EditText)findViewById(R.id.et_stricken));
		
		et_accident_injury_safe = ((EditText)findViewById(R.id.et_accident_injury_safe));
		et_injury_medical = ((EditText)findViewById(R.id.et_injury_medical));
		et_enjoy_health = ((EditText)findViewById(R.id.et_enjoy_health));
		et_applicant_name = ((EditText)findViewById(R.id.et_applicant_name));
		
		((RadioButton)findViewById(R.id.maleRadioButton)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.rb_husband)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.rb_wife)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.rb_father)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.rb_mother)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.rb_me)).setOnCheckedChangeListener(checkedChangeListener);
		
		((RadioButton)findViewById(R.id.rb_grade1)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.rb_grade2)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.rb_grade3)).setOnCheckedChangeListener(checkedChangeListener);

		//获取默认被被选中值  
		sp_age = (Spinner) findViewById(R.id.sp_age);
		sp_period = (Spinner) findViewById(R.id.sp_period);
		sp_safe_period = (Spinner) findViewById(R.id.sp_safe_period);
		sp_privy_age = (Spinner) findViewById(R.id.sp_privy_age);
				
		ArrayAdapter<CharSequence> adapter0 = 
				ArrayAdapter.createFromResource(this,R.array.age0_55,R.layout.children_spinner); 
		adapter0.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sp_age.setAdapter(adapter0);
		sp_age.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String array[] = null;
				int se_po = 0;
				if (position > 44) {
					array = new String[]{"20"};
					
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(XinXiangPlanA.this,R.layout.children_spinner, new String[]{"20年"});
					adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
					sp_safe_period.setAdapter(adapter);
				}else {
					array = new String[]{"10","20"};
					se_po = 1;
					
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(XinXiangPlanA.this,R.layout.children_spinner, new String[]{"20年","至60岁"});
					adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
					sp_safe_period.setAdapter(adapter);
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(XinXiangPlanA.this,R.layout.children_spinner, array);
				adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
				sp_period.setAdapter(adapter);
				sp_period.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						String p = ((TextView)view).getText().toString();
						if ("10".equals(p)) {
							String array[] = new String[]{"至60岁"};
							ArrayAdapter<String> adapter = new ArrayAdapter<String>(XinXiangPlanA.this,R.layout.children_spinner, array);
							adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
							sp_safe_period.setAdapter(adapter);
						}
					}
					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
				sp_period.setSelection(se_po);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		int array_id = R.array.age18_60;
		adapter0 = ArrayAdapter.createFromResource(XinXiangPlanA.this,array_id,R.layout.children_spinner); 
		adapter0.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sp_privy_age.setAdapter(adapter0);

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
			case R.id.cb_standard:
				if (isChecked) {//标准组合
//					鑫祥10万、鑫祥重疾8万、附加意外伤害保险10万、附加意外医疗B2万
					et_main.setText("10");
					et_stricken.setText("8");
					et_accident_injury_safe.setText("10");
					et_injury_medical.setText("2");
					cb_accident_social_security.setChecked(true);
					et_enjoy_health.setText("0");
				}
				break;
			case R.id.rb_husband:
				if (isChecked) {
					privy_sex = "A";
				}
				break;
			case R.id.rb_wife:
				if (isChecked) {
					privy_sex = "B";
				}
				break;
			case R.id.rb_father:
				if (isChecked) {
					privy_sex = "C";
				}
				break;
			case R.id.rb_mother:
				if (isChecked) {
					privy_sex = "D";
				}
				break;
			case R.id.rb_me:
				if (isChecked) {
					privy_sex = "E";
				}
				break;
			case R.id.rb_grade1:
				if (isChecked) {
					grade = 0;
					buttonView.setTextColor(Color.parseColor("#ffffff"));
				}else {
					buttonView.setTextColor(Color.parseColor("#0299EE"));
				}
				break;
			case R.id.rb_grade2:
				if (isChecked) {
					grade = 1;
					buttonView.setTextColor(Color.parseColor("#ffffff"));
				}else {
					buttonView.setTextColor(Color.parseColor("#0299EE"));
				}
				break;
			case R.id.rb_grade3:
				if (isChecked) {
					grade = 2;
					buttonView.setTextColor(Color.parseColor("#ffffff"));
				}else {
					buttonView.setTextColor(Color.parseColor("#0299EE"));
				}
				break;
	
			default:
				break;
			}
		}
	};
	public void peace_back(View v) {
		finish();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	
    public void sjtsh_submit(View v){
    	String data = getData();
    	startActivity(new Intent(this,XinXiangShowA.class).putExtra("data", data));
    }
	private String getData(){
		String postDate = "";
		postDate += "userid=" + userid + "&";
		
		String name = et_name.getText().toString();
		postDate += "insurant_name=" + name + "&";
		
		postDate += "sex=" + sex + "&";

		String age = sp_age.getSelectedItem().toString();
		age = age.replace("岁", "");
		postDate += "age=" + age + "&";

		String period = sp_period.getSelectedItem().toString();
		period = period.replace("年", "");
		postDate += "period=" + period + "&";
		
		String safe_period = sp_safe_period.getSelectedItem().toString();
		if ("20年".equals(safe_period)) {
			safe_period = "A";
		}else{
			safe_period = "B";
		}
		postDate += "safe_period=" + safe_period + "&";
		
		String main = et_main.getText().toString();//世纪天使两全保险
		if ("".equals(main)) {main = "10";}
		postDate += "main=" + main + "&";
		
		String stricken = et_stricken.getText().toString();//附加少儿重大疾病保险
		if (!cb_stricken.isChecked()) {stricken = "0";}
		if ("".equals(stricken)) {stricken = "0";}
		postDate += "stricken=" + stricken + "&";
				
		String accident_injury_safe = et_accident_injury_safe.getText().toString();//附加意外伤害保险
		if (!cb_accident_injury_safe.isChecked()) {accident_injury_safe = "0";}
		if ("".equals(accident_injury_safe)) {accident_injury_safe = "0";}
		postDate += "accident_injury_safe=" + accident_injury_safe + "&";
		
		String injury_medical = et_injury_medical.getText().toString();//意外医疗保险
		if (!cb_injury_medical.isChecked()) {injury_medical = "0";}
		if ("".equals(injury_medical)) {injury_medical = "0";}
		postDate += "injury_medical=" + injury_medical + "&";
		
		String accident_social_security = 
				(cb_accident_social_security.isChecked()?"B":"A");//意外伤害医疗,社保,无社保A有社保B
		postDate += "accident_social_security=" + accident_social_security + "&";
		
		String enjoy_health = et_enjoy_health.getText().toString();//健享人生住院医疗保险
		if (!cb_enjoy_health_hospital.isChecked()) {enjoy_health = "0";}
		if ("".equals(enjoy_health)) {enjoy_health = "0";}
		postDate += "enjoy_health=" + enjoy_health + "&";
		
		String enjoy_health_social = 
				(cb_enjoy_health_social.isChecked() == true ? "B":"A");//健享人生住院医疗保险，社保
		postDate += "enjoy_health_social=" + enjoy_health_social + "&";

		String enjoy_health_add = 
				(cb_enjoy_health_add.isChecked() == true ? "A":"B");//健享人生住院医疗保险，可选部分
		postDate += "enjoy_health_add=" + enjoy_health_add + "&";
		
		String applicant_name = et_applicant_name.getText().toString();
		postDate += "applicant_name=" + applicant_name + "&";//投保人姓名

		String privy_age = sp_privy_age.getSelectedItem().toString();
		if (!cb_add_exempt_b.isChecked()) {privy_age = "-1";}
		postDate += "privy_age=" + privy_age + "&";
		postDate += "privy_sex=" + privy_sex + "&";
		postDate += "grade=" + grade;
		
		return postDate;
	}
}
