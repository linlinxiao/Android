package com.baoxiao.activity.productshow.shouhux;

import com.baoxiao.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ProductTiaoKuanA extends Activity implements OnClickListener{
	private Button zhuXian,zhongJi,huoMian;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ps_shouhux_producttiaokuan);
	
	    initView();
	}

	private void initView() {
		zhuXian = (Button) findViewById(R.id.zhuXianTK);
		zhongJi = (Button) findViewById(R.id.zhongJiTK);
		huoMian = (Button) findViewById(R.id.huoMianTK);
		
		zhuXian.setOnClickListener(this);
		zhongJi.setOnClickListener(this);
		huoMian.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.zhuXianTK:
			Toast.makeText(getApplicationContext(), "������������pdf", Toast.LENGTH_SHORT).show();
			break;
		case R.id.zhongJiTK:
			Toast.makeText(getApplicationContext(), "�����ؼ�����pdf", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.huoMianTK:
			Toast.makeText(getApplicationContext(), "���ػ�������pdf", Toast.LENGTH_SHORT).show();
			
			break;

		default:
			break;
		}
	}

	
}
