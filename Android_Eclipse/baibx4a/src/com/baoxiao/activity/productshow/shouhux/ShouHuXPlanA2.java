package com.baoxiao.activity.productshow.shouhux;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class ShouHuXPlanA2 extends Activity{
	
	 private EditText et_name,
	 et_main,//世纪天使两全保险
	 et_stricken,//附加少儿重大疾病保险
	 et_injury_medical,//意外伤害医疗
	 et_add_hospital_stay,//附加住院日额保险
	 et_enjoy_health,//健享人生住院医疗保险
	 et_applicant_name;//投保人姓名
	 
	 private Spinner sp_age,sp_period,sp_privy_age;//年龄，缴费年期，投保人性别

	 private String sex = "A",userid,privy_sex = "A";
//	 private String[] age0_17;
	 private String[] premium_period = new String[]{
			 "10","20"
		};
	 private CheckBox /*cb_standard,*/
	 cb_injury_medical,//意外伤害医疗
	 cb_accident_social_security,//意外伤害医疗，有无社保
	 cb_enjoy_health_add,//健享人生住院医疗保险，可选部分
	 cb_enjoy_health_social,//健享人生住院医疗保险，有无社保
	 cb_add_exempt_b;//豁免B加强版
	private PopupWindow popupWindow;
	private View contentView;
	private TextView mycard_pop_title;
	private LinearLayout ll_mycard_pop_content;
	private View ll[] = new View[2];
	private int grade = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ps_shouhux_plan);
		
		contentView = LayoutInflater.from(this).inflate(R.layout.mycard_pop_window4, null);
		mycard_pop_title = (TextView)contentView.findViewById(R.id.mycard_pop_title);
		ll_mycard_pop_content = (LinearLayout)contentView.findViewById(R.id.ll_mycard_pop_content);
        popupWindow = new PopupWindow(contentView,LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		ll[0] = findViewById(R.id.ll_add_hospital_stay);
		ll[1] = findViewById(R.id.ll_enjoy_health_hospital);

        userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
//		age0_17 = new String[18];
//		for (int i = 0; i < age0_17.length; i++) {
//			age0_17[i] = i + "岁";
//		}
		((CheckBox)findViewById(R.id.cb_standard)).setOnCheckedChangeListener(checkedChangeListener);
		
		cb_injury_medical = (CheckBox)findViewById(R.id.cb_injury_medical);
		cb_accident_social_security = (CheckBox)findViewById(R.id.cb_accident_social_security);
		cb_enjoy_health_add = (CheckBox)findViewById(R.id.cb_enjoy_health_add);
		cb_enjoy_health_social = (CheckBox)findViewById(R.id.cb_enjoy_health_social);
		cb_add_exempt_b = (CheckBox)findViewById(R.id.cb_add_exempt_b);
		
		et_name = ((EditText)findViewById(R.id.et_name));
		et_main = ((EditText)findViewById(R.id.et_main));
		et_stricken = ((EditText)findViewById(R.id.et_stricken));
		et_injury_medical = ((EditText)findViewById(R.id.et_injury_medical));
		et_add_hospital_stay = ((EditText)findViewById(R.id.et_add_hospital_stay));
		et_enjoy_health = ((EditText)findViewById(R.id.et_enjoy_health));
		et_applicant_name = ((EditText)findViewById(R.id.et_applicant_name));
		
		((RadioButton)findViewById(R.id.maleRadioButton)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.rb_father)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.rb_grade1)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.rb_grade2)).setOnCheckedChangeListener(checkedChangeListener);
		((RadioButton)findViewById(R.id.rb_grade3)).setOnCheckedChangeListener(checkedChangeListener);
		
		//获取默认被被选中值  
		sp_age = (Spinner) findViewById(R.id.sp_age);
		sp_period = (Spinner) findViewById(R.id.sp_period);
		sp_privy_age = (Spinner) findViewById(R.id.sp_privy_age);
				
		ArrayAdapter<CharSequence> adapter0 = 
				ArrayAdapter.createFromResource(this,R.array.age0_17,R.layout.children_spinner); 
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				 R.layout.children_spinner, age0_17);
		adapter0.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sp_age.setAdapter(adapter0);
//		sp_age.setOnItemSelectedListener(itemSelectedListener);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.children_spinner, premium_period);
		adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sp_period.setAdapter(adapter);
		sp_period.setSelection(1);

		int array_id = R.array.age18_60;
		adapter0 = ArrayAdapter.createFromResource(ShouHuXPlanA2.this,array_id,R.layout.children_spinner); 
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
					et_main.setText("10");
					et_stricken.setText("50");
					et_injury_medical.setText("2");
					et_add_hospital_stay.setText("0");
					et_enjoy_health.setText("0");

//					baoE.setText("45");//保额
//					et_advance_diseases.setText("40");//疾病保额
//					et_stricken.setText("5");//重疾陪护金，最高20份
//					et_long_accident.setText("20");//长期意外
//					et_medical_accident.setText("2");//意外医疗
//					et_Life_insurance.setText("5");
//					
//					cb_children_accident.setChecked(true);
//					cb_long_accident.setChecked(true);
//					cb_medical_accident.setChecked(true);
//					cb_Life_insurance.setChecked(true);
//					cb_accident_exemption.setChecked(false);
//					cb_social_security.setChecked(false);
				}
				break;
			case R.id.rb_father:
				if (isChecked) {
					privy_sex = "A";
				}else {
					privy_sex = "B";
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
	public void add_other(View v){
        String array[] = new String[]{
       	 "附加住院日额保险",
       	 "健享人生住院医疗保险"
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
//        cb_pop_item[0].setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				cb_pop_item[1].setChecked(!isChecked);
//			}
//		});
//        cb_pop_item[1].setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				cb_pop_item[0].setChecked(!isChecked);
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
    public void sjtsh_submit(View v){
    	String data = getData();
    	startActivity(new Intent(this,ShouHuXShowA2.class)
    			.putExtra("data", data));
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
		
		String main = et_main.getText().toString();//世纪天使两全保险
		if ("".equals(main)) {main = "10";}
		postDate += "main=" + main + "&";
		
		String stricken = et_stricken.getText().toString();//附加少儿重大疾病保险
		if ("".equals(stricken)) {stricken = "50";}
		postDate += "stricken=" + stricken + "&";
		
		String injury_medical = et_injury_medical.getText().toString();//意外伤害医疗
		if (!cb_injury_medical.isChecked()) {injury_medical = "0";}
		if ("".equals(injury_medical)) {injury_medical = "0";}
		postDate += "injury_medical=" + injury_medical + "&";
		
		String accident_social_security = 
				(cb_accident_social_security.isChecked() == true ? "B":"A");//意外伤害医疗,社保,无社保A有社保B
		postDate += "accident_social_security=" + accident_social_security + "&";
		
		String add_hospital_stay = et_add_hospital_stay.getText().toString();//附加住院日额保险
		if (ll[0].getVisibility() == View.GONE) {add_hospital_stay = "0";}
		if ("".equals(add_hospital_stay)) {add_hospital_stay = "0";}
		postDate += "add_hospital_stay=" + add_hospital_stay + "&";
		
		String enjoy_health = et_enjoy_health.getText().toString();//健享人生住院医疗保险
		if (ll[1].getVisibility() == View.GONE) {enjoy_health = "0";}
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
