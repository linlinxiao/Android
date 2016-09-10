package com.baoxiao.test.activity.testmian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.baoxiao.R;
import com.baoxiao.activity.productshow.shouhux.ShouHuXPlanA;
import com.baoxiao.activity.userproduct.EditUserInfoA;

public class TestOne extends Activity implements OnClickListener{
	private Button shouHuXA,productShowA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testone);
		
		shouHuXA = (Button) findViewById(R.id.shouHuXA);
		productShowA = (Button) findViewById(R.id.productShowA);
		
		shouHuXA.setOnClickListener(this);
		productShowA.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.productShowA:
			//数据库的数据提取界面
			Intent intent = new Intent(TestOne.this,EditUserInfoA.class);
			startActivity(intent);
			finish();
			break;

		case R.id.shouHuXA:
			//进入产品演示界面
			Intent intent2 = new Intent(TestOne.this,ShouHuXPlanA.class);
			startActivity(intent2);
			finish();
			break;
		}
	}
	
	

}