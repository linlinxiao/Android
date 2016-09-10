package com.baoxiao.activity.productshow.baiwanrwx;

import com.baoxiao.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class BaiWanRWXPlanA extends Activity{
	
	private EditText et_name,et_main;//保额	 
	private Spinner sp_age,sp_safe_period;

	private String sex = "A",userid;
	 	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ps_baiwanrenwoxing_plan);
		userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
		
		((CheckBox)findViewById(R.id.cb_standard)).setOnCheckedChangeListener(checkedChangeListener);
		
		et_name = ((EditText)findViewById(R.id.et_name));
		et_main = ((EditText)findViewById(R.id.et_main));//保额
		
		((RadioGroup)findViewById(R.id.sex)).check(R.id.maleRadioButton);
		((RadioButton)findViewById(R.id.maleRadioButton)).setOnCheckedChangeListener(checkedChangeListener);

		String age18_50[] = new String[33];
		for (int i = 0; i < age18_50.length; i++) {
			age18_50[i] = (i+18)+"岁";
		}
		sp_age = (Spinner) findViewById(R.id.sp_age);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				 R.layout.children_spinner, age18_50);
		adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sp_age.setAdapter(adapter);
		sp_age.setOnItemSelectedListener(itemSelectedListener);
				
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
					et_main.setText("1");//保额
				}
				break;
				
			default:
				break;
			}
		}
	};
	public void submit(View v){
		String postDate = "";
		postDate += "userid=" + userid + "&";
		//基本信息
		String name = et_name.getText().toString().trim();
		postDate += "insurant_name=" + name + "&";
		postDate += "sex=" + sex + "&";
		String age = sp_age.getSelectedItem().toString();
		age = age.replace("岁", "");
		postDate += "age=" + age + "&";
		String period = sp_safe_period.getSelectedItem().toString();
		period = period.replace("年", "");
		postDate += "period=" + period + "&";

		//终生寿险
		String main = et_main.getText().toString().trim();
		if ("".equals(main) || "0".equals(main)) {main = "1";}
		if (Integer.parseInt(main)>2) {
			main = "2";
		}
		postDate += "main=" + main + "&";
		
		startActivity(new Intent(this,BaiWanRWXShowA.class)
			.putExtra("postDate", postDate));
	}
	private OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

			String safe_period[] = null;
			if (position <= 22) {
				safe_period = new String[]{"20年","30年"};
			}else {
				safe_period = new String[]{"20年"};
			}
			sp_safe_period = (Spinner) findViewById(R.id.sp_safe_period);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(BaiWanRWXPlanA.this,R.layout.children_spinner, safe_period);
			adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
			sp_safe_period.setAdapter(adapter);
			sp_safe_period.setOnItemSelectedListener(itemSelectedListener);
		}
		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	};
	
	public void peace_back(View v) {
		finish();
	}
	
}
