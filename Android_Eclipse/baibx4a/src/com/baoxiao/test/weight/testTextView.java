package com.baoxiao.test.weight;

import com.baoxiao.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class testTextView extends Activity implements OnClickListener{
    private LinearLayout hongLiBaoXianJinELayout1_2,hongLiBaoXianJinELayout2_2,
    hongLiBaoXianJinELayout3_2,hongLiBaoXianJinELayout4_2,hongLiBaoXianJinELayout5_2;
	private TextView hongLiBaoXianJinE_2,hongLiBaoXianJinE1_2,hongLiBaoXianJinE2_2,
	hongLiBaoXianJinE3_2,hongLiBaoXianJinE4_2,hongLiBaoXianJinE5_2;
    
	private LinearLayout baoDanLeiJiContent1,
	      baoDanLeiJiContent2,baoDanLeiJiContent3,baoDanLeiJiContent4,
	      baoDanLeiJiContent5;
	private TextView baoDanLeiJiTitle,baoDanLeiJiContent1_shengcunjin,baoDanLeiJiContent2_shengcunjin,
	      baoDanLeiJiContent3_shengcunjin,baoDanLeiJiContent4_shengcunjin,
	      baoDanLeiJiContent5_shengcunjin;
	
	//�ڶ�����
	
	
//   
	private  double fanHuanZhuXian60D_3;
	private double  zhongShenZongjiD_1_4;
	
	private double  shenGuMore60_2_4;
	private double  yiWaiShenGu22_3_4;
	private double  jiBingShenGu22_4_4;
	private double  shenGuLess22_5_4;
	
	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ps_shousux_lj_fan_huan);
		baoDanLeiJiContent1 = (LinearLayout) findViewById(R.id.baoDanLeiJiContent1);
		baoDanLeiJiContent2 = (LinearLayout) findViewById(R.id.baoDanLeiJiContent2);
		baoDanLeiJiContent3 = (LinearLayout) findViewById(R.id.baoDanLeiJiContent3);
		baoDanLeiJiContent4 = (LinearLayout) findViewById(R.id.baoDanLeiJiContent4);
		baoDanLeiJiContent5 = (LinearLayout) findViewById(R.id.baoDanLeiJiContent5);
		
		hongLiBaoXianJinELayout1_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout1_2);
		hongLiBaoXianJinELayout2_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout2_2);
		hongLiBaoXianJinELayout3_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout3_2);
		hongLiBaoXianJinELayout4_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout4_2);
		hongLiBaoXianJinELayout5_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout5_2);
		
		
		baoDanLeiJiTitle = (TextView) findViewById(R.id.baoDanLeiJiTitle);
		baoDanLeiJiContent1_shengcunjin = (TextView) findViewById(R.id.baoDanLeiJiContent1_shengcunjin);
		baoDanLeiJiContent2_shengcunjin = (TextView) findViewById(R.id.baoDanLeiJiContent2_shengcunjin);
		baoDanLeiJiContent3_shengcunjin = (TextView) findViewById(R.id.baoDanLeiJiContent3_shengcunjin);
		baoDanLeiJiContent4_shengcunjin = (TextView) findViewById(R.id.baoDanLeiJiContent4_shengcunjin);
		baoDanLeiJiContent5_shengcunjin = (TextView) findViewById(R.id.baoDanLeiJiContent5_shengcunjin);
		
		hongLiBaoXianJinE_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE_2);
		hongLiBaoXianJinE1_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE1_2);
		hongLiBaoXianJinE2_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE2_2);
		hongLiBaoXianJinE3_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE3_2);
		hongLiBaoXianJinE4_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE4_2);
		hongLiBaoXianJinE5_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE5_2);
	
		baoDanLeiJiContent1.setOnClickListener(this);
		baoDanLeiJiContent2.setOnClickListener(this);
		baoDanLeiJiContent3.setOnClickListener(this);
		baoDanLeiJiContent4.setOnClickListener(this);
		baoDanLeiJiContent5.setOnClickListener(this);
		
		hongLiBaoXianJinELayout1_2.setOnClickListener(this);
		hongLiBaoXianJinELayout2_2.setOnClickListener(this);
		hongLiBaoXianJinELayout3_2.setOnClickListener(this);
		hongLiBaoXianJinELayout4_2.setOnClickListener(this);
		hongLiBaoXianJinELayout5_2.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.baoDanLeiJiContent1:
			
			break;
		case R.id.baoDanLeiJiContent2:
			
			break;
		case R.id.baoDanLeiJiContent3:
			
			break;
		case R.id.baoDanLeiJiContent4:
			
			break;
		case R.id.baoDanLeiJiContent5:
			
			break;
			
		case R.id.hongLiBaoXianJinELayout1_2:
			
			break;
		case R.id.hongLiBaoXianJinELayout2_2:
			
			break;
		case R.id.hongLiBaoXianJinELayout3_2:
			
			break;
		case R.id.hongLiBaoXianJinELayout4_2:
			
			break;
		case R.id.hongLiBaoXianJinELayout5_2:
			
			break;
		}
	}
	
	
	
	

}
