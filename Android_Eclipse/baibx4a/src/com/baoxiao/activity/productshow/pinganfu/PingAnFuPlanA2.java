package com.baoxiao.activity.productshow.pinganfu;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class PingAnFuPlanA2 extends Activity{
	private CheckBox cb_standard;
	
	private EditText et_name,et_main_totle,et_add_advance_totle,
	et_add_long_accident_totle,et_add_accident_medical_totle,et_add_hospital_day_totle,
	et_hospital_medical_totle,et_jianxiang,et_life_insurance,et_applicant_name;
	
	private RadioButton maleRadioButton;
	private CheckBox cb_add_accident_medical,cb_add_accident_medical_select,
	cb_hospital_medical,cb_hospital_medical_selected,
	cb_jianxiang,cb_jianxiang_selected,cb_add_exempt_b;
	
	private Spinner sp_age,sp_period,sp_privy_age;
	
	private View ll[] = new View[5];
//	ll_add_long_accident,ll_add_accident_medical,ll_add_hospital_day_totle,
//	ll_hospital_medical_totle,ll_jianxiang,ll_life_insurance;

	public static  String[] period18to40 = new String[]{"10","15","20","30"};
	public static  String[] period41to55 = new String[]{"10","15","20"};
	
	private PopupWindow popupWindow;
	private View contentView;
	private TextView mycard_pop_title;
	private LinearLayout ll_mycard_pop_content;
	private String userid;
	private String sex = "A",//AΪ�У�BΪŮ
			add_accident_medical_is = "A",//AΪ���籣��BΪ���籣
			hospital_medical_is = "A",//AΪ���籣��BΪ���籣
			jianxiang_is = "A",//AΪ���籣��BΪ���籣
			jianxiang_selected = "A",
			hospital_medical_selected = "A",//AΪ��ѡ��BΪ�ǿ�ѡ
			privy_sex = "E";//�뱻���˹�ϵAΪ���׻��ɷ�BΪĸ�׻�����
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ps_pinganfu_plan2);
		userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");

//		pinganfuplan = getSharedPreferences("pinganfuplan", Activity.MODE_PRIVATE);
		contentView = LayoutInflater.from(this).inflate(R.layout.mycard_pop_window4, null);
		mycard_pop_title = (TextView)contentView.findViewById(R.id.mycard_pop_title);
		ll_mycard_pop_content = (LinearLayout)contentView.findViewById(R.id.ll_mycard_pop_content);
		
		cb_standard = (CheckBox) findViewById(R.id.cb_standard);
//		boolean standard = pinganfuplan.getBoolean("cb_standard", false);
//		cb_standard.setChecked(standard);
		cb_standard.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					et_main_totle.setText(20+"");
					et_add_advance_totle.setText(15+"");
					int age = Integer.parseInt(sp_age.getSelectedItem().toString());
					et_add_long_accident_totle.setText(20+"");
					if (age > 50) {
						et_add_long_accident_totle.setText("0");
					}
					et_add_accident_medical_totle.setText(2+"");
					
					ll[0].setVisibility(View.GONE);
					ll[1].setVisibility(View.GONE);
					ll[2].setVisibility(View.GONE);
					ll[3].setVisibility(View.GONE);
				}
			}
		});
		
		et_name = (EditText)findViewById(R.id.et_name);
		maleRadioButton = (RadioButton)findViewById(R.id.maleRadioButton);
		maleRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					sex = "A";
				}else{sex = "B";}
			}
		});
		sp_age = (Spinner) findViewById(R.id.sp_age);
		sp_period = (Spinner) findViewById(R.id.sp_period);
		
		sp_privy_age = (Spinner) findViewById(R.id.sp_privy_age);
		String privy_age[] = new String[40];
		for (int i = 0; i < privy_age.length; i++) {
			privy_age[i] = (20 + i) + "";
		}
		ArrayAdapter<String> ageAdapter = new ArrayAdapter<String>(this,R.layout.simple_spinner_item, privy_age);
		ageAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sp_privy_age.setAdapter(ageAdapter);

		ageAdapter = new ArrayAdapter<String>(this,R.layout.simple_spinner_item, Define.agePingAnFu);
		ageAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sp_age.setAdapter(ageAdapter);
		sp_age.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String age = sp_age.getSelectedItem().toString();
				setPeriodAdapter(age);
			}
			private void setPeriodAdapter(String age) {
				final int a = Integer.parseInt(age);
				ArrayAdapter<String> periodAdapter;
				if (a <= 45) {
					periodAdapter = new ArrayAdapter<String>(PingAnFuPlanA2.this,R.layout.simple_spinner_item, Define.periodXinShengAll);
					periodAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
					sp_period.setAdapter(periodAdapter);
					sp_period.setSelection(2);
					sp_period.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							if (position == 3) {
								if (a > 40) {
									et_add_long_accident_totle.setText("0");
									et_add_long_accident_totle.setEnabled(false);
									et_life_insurance.setText("0");
									et_life_insurance.setEnabled(false);
								}else{
									et_add_long_accident_totle.setEnabled(true);
									et_life_insurance.setEnabled(true);
								}
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}
					});
				}else{
					periodAdapter = new ArrayAdapter<String>(PingAnFuPlanA2.this,R.layout.simple_spinner_item, Define.periodXinSheng45and55);
					periodAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
					sp_period.setAdapter(periodAdapter);
					sp_period.setSelection(2);
					sp_period.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							if (a > 50) {
								et_add_long_accident_totle.setText("0");
								et_add_long_accident_totle.setEnabled(false);
							}else{
								et_add_long_accident_totle.setEnabled(true);
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}
					});
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		et_main_totle = (EditText)findViewById(R.id.et_main_totle);
		et_add_advance_totle = (EditText)findViewById(R.id.et_add_advance_totle);
		et_add_long_accident_totle = (EditText)findViewById(R.id.et_add_long_accident_totle);
		
		et_add_accident_medical_totle = (EditText)findViewById(R.id.et_add_accident_medical_totle);
		
		cb_add_accident_medical_select = (CheckBox)findViewById(R.id.cb_add_accident_medical_select);

		cb_add_accident_medical = (CheckBox)findViewById(R.id.cb_add_accident_medical);
		cb_add_accident_medical.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					add_accident_medical_is = "B";
				}else{add_accident_medical_is = "A";}
			}
		});
		et_add_hospital_day_totle = (EditText)findViewById(R.id.et_add_hospital_day_totle);
		
		et_hospital_medical_totle = (EditText)findViewById(R.id.et_hospital_medical_totle);
		cb_hospital_medical = (CheckBox)findViewById(R.id.cb_hospital_medical_so);
		cb_hospital_medical.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					hospital_medical_is = "A";
				}else {hospital_medical_is = "B";}
			}
		});
		cb_hospital_medical_selected = (CheckBox)findViewById(R.id.cb_hospital_medical_se);
		cb_hospital_medical_selected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					hospital_medical_selected = "A";
				}else {hospital_medical_selected = "B";}
			}
		});
		
		et_jianxiang = (EditText)findViewById(R.id.et_jianxiang);
		cb_jianxiang = (CheckBox)findViewById(R.id.cb_jianxiang_so);
		cb_jianxiang.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					jianxiang_is = "A";
				}else {jianxiang_is = "B";}
			}
		});
		cb_jianxiang_selected = (CheckBox)findViewById(R.id.cb_jianxiang_se);
		cb_jianxiang_selected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					jianxiang_selected = "A";
				}else {jianxiang_selected = "B";}
			}
		});
		cb_add_exempt_b = (CheckBox)findViewById(R.id.cb_add_exempt_b);
		et_life_insurance = (EditText)findViewById(R.id.et_life_insurance);
		
//		ll_add_accident_medical,ll_add_hospital_day_totle,
//		ll_hospital_medical_totle,ll_jianxiang,ll_life_insurance;
//		ll_add_accident_medical = findViewById(R.id.ll_add_accident_medical);
		ll[0] = findViewById(R.id.ll_add_hospital_day_totle);
		ll[1] = findViewById(R.id.ll_hospital_medical_totle);
		ll[2] = findViewById(R.id.ll_jianxiang);
		ll[3] = findViewById(R.id.ll_life_insurance);
		
//		rb_husband,rb_wife,rb_father,rb_mother
		et_applicant_name = (EditText)findViewById(R.id.et_applicant_name);
		((RadioButton)findViewById(R.id.rb_husband)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					privy_sex = "A";
				}
			}
		});
		((RadioButton)findViewById(R.id.rb_wife)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					privy_sex = "B";
				}
			}
		});
		((RadioButton)findViewById(R.id.rb_father)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					privy_sex = "C";
				}
			}
		});
		((RadioButton)findViewById(R.id.rb_mother)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					privy_sex = "D";
				}
			}
		});
		((RadioButton)findViewById(R.id.rb_me)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					privy_sex = "E";
					et_applicant_name.setEnabled(false);
					sp_privy_age.setEnabled(false);
				}else{
					et_applicant_name.setEnabled(true);
					sp_privy_age.setEnabled(true);
				}
			}
		});
	}
	
//	public void delect_add_accident_medical(View v){
//		ll_add_accident_medical.setVisibility(View.GONE);
//	}
	public void delect_hospital_day_totle(View v){
		ll[0].setVisibility(View.GONE);
	}
	public void delect_hospital_medical_totle(View v){
		ll[1].setVisibility(View.GONE);
	}
	public void delect_ll_jianxiang(View v){
		ll[2].setVisibility(View.GONE);
	}
	public void delect_life_insurance(View v){
		ll[3].setVisibility(View.GONE);
	}
	public void add_other(View v){
        String array[] = new String[]{
//           	 "��������ҽ�Ʊ���",
        	 "����סԺ�ն��",
        	 "סԺҽ�Ʊ���",
        	 "��������סԺҽ�Ʊ���",
        	 "���Ӷ�������",
        	};
        String title = "��ѡ�񸽼���";
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
        cb_pop_item[1].setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				cb_pop_item[2].setChecked(!isChecked);
			}
		});
        cb_pop_item[2].setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				cb_pop_item[1].setChecked(!isChecked);
			}
		});
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
    
    public void peace_submit(View v){
    	String data = getData();
    	startActivity(new Intent(PingAnFuPlanA2.this,PingAnFuShowA2.class)
    			.putExtra("data", data));
    }
    private String getData() {
    	String result = "";
    	result = "userid=" + userid + "&";
    	String insurant_name = et_name.getText().toString();
    	result = result + "insurant_name=" + insurant_name + "&";
    	result = result + "sex=" + sex + "&";
    	
    	String age = sp_age.getSelectedItem().toString();
    	result = result + "age=" + age + "&";
    	
    	String period = sp_period.getSelectedItem().toString();
    	result = result + "period=" + period + "&";
    	
    	String main_totle = et_main_totle.getText().toString();
    	if ("".equals(main_totle)) {main_totle = "20";}
    	if (Float.parseFloat(main_totle) < 20) {main_totle = "20";}
    	result =  result + "main_totle=" + main_totle + "&";
    	
    	String add_advance_totle = et_add_advance_totle.getText().toString();
    	if ("".equals(add_advance_totle)) {add_advance_totle = "15";}
    	if (Float.parseFloat(add_advance_totle) < 15) {add_advance_totle = "15";}
    	else if (Float.parseFloat(add_advance_totle) > Float.parseFloat(main_totle)) {
    		add_advance_totle = main_totle;
    	}
    	result = result + "add_advance_totle=" + add_advance_totle + "&";
    	
    	String add_long_accident_totle = et_add_long_accident_totle.getText().toString();
    	if ("".equals(add_long_accident_totle)) {add_long_accident_totle = "20";}
    	if (Float.parseFloat(add_long_accident_totle) < 20) {add_long_accident_totle = "20";}
    	result = result + "add_long_accident_totle=" + add_long_accident_totle + "&";
    	
    	String add_accident_medical_totle;
    	if (!cb_add_accident_medical_select.isChecked()) {add_accident_medical_totle = "0";}
    	else{
    		add_accident_medical_totle = et_add_accident_medical_totle.getText().toString();
        	if ("".equals(add_accident_medical_totle)) {add_accident_medical_totle = "2";}
    	}
    	if (Float.parseFloat(add_accident_medical_totle) < 1) {add_accident_medical_totle = "1";}
    	result = result + "add_accident_medical_totle=" + add_accident_medical_totle + "&";
    	result = result + "add_accident_medical_is=" + add_accident_medical_is + "&";
    	
    	
    	String add_hospital_day_totle = et_add_hospital_day_totle.getText().toString();
    	if (ll[0].getVisibility() == View.GONE) {add_hospital_day_totle = "0";}
    	if ("".equals(add_hospital_day_totle)) {add_hospital_day_totle = "0";}
    	if (Float.parseFloat(add_hospital_day_totle) > 200) {
    		add_hospital_day_totle = "200";
    	}
    	result = result + "add_hospital_day_totle=" + add_hospital_day_totle + "&";
    	
    	String hospital_medical_totle = et_hospital_medical_totle.getText().toString();
    	if (ll[1].getVisibility() == View.GONE) {hospital_medical_totle = "0";}
    	if ("".equals(hospital_medical_totle)) {hospital_medical_totle = "0";}
    	if (Float.parseFloat(hospital_medical_totle) > 15) {
    		add_hospital_day_totle = "15";
    	}
    	result = result + "hospital_medical_totle=" + hospital_medical_totle + "&";
    	result = result + "hospital_medical_is=" + hospital_medical_is + "&";
    	result = result + "hospital_medical_selected=" + hospital_medical_selected + "&";
    	
    	String jianxiang = et_jianxiang.getText().toString();
    	if (ll[2].getVisibility() == View.GONE) {jianxiang = "0";}
    	if ("".equals(jianxiang)) {jianxiang = "0";}
    	if (Float.parseFloat(jianxiang) > 15) {
    		jianxiang = "15";
    	}
    	result = result + "jianxiang=" + jianxiang + "&";
    	result = result + "jianxiang_is=" + jianxiang_is + "&";
    	result = result + "jianxiang_selected=" + jianxiang_selected + "&";
    	
    	String life_insurance = et_life_insurance.getText().toString();
    	if (ll[3].getVisibility() == View.GONE) {life_insurance = "0";}
    	if ("".equals(life_insurance)) {life_insurance = "0";}
    	if (!"0".equals(life_insurance) && Float.parseFloat(life_insurance) < 1) {
    		life_insurance = "1";
    	}else if(Float.parseFloat(life_insurance) > Float.parseFloat(main_totle)*50){
    		life_insurance = Float.parseFloat(main_totle)*50 + "";
    	}
    	result = result + "life_insurance=" + life_insurance + "&";
    	String privy_age = sp_privy_age.getSelectedItem().toString();
    	if (!cb_add_exempt_b.isChecked()) {
			privy_age = "-1";
		}
    	String applicant_name = et_applicant_name.getText().toString();
    	result = result + "applicant_name=" + applicant_name + "&";
    	result = result + "privy_age=" + privy_age + "&";
    	result = result + "privy_sex=" + privy_sex;

		return result;
	}

	public void peace_back(View v){
//		startActivity(new Intent(this, PingAnFuA.class));
    	finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK){
//			startActivity(new Intent(this, PingAnFuA.class));
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}