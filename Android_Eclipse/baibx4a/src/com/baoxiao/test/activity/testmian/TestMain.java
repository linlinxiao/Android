package com.baoxiao.test.activity.testmian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.baoxiao.R;

public class TestMain extends Activity implements OnClickListener{
	private Button btn1,btn2,btn3,btn4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.testmain);
		
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			Intent intent = new Intent(TestMain.this,TestOne.class);
			startActivity(intent);
			finish();
			break;

		case R.id.btn2:
			Intent intent2 = new Intent(TestMain.this,TestTwo.class);
			startActivity(intent2);
			finish();
			break;
			
		case R.id.btn3:
			Intent intent3 = new Intent(TestMain.this,TestThree.class);
			startActivity(intent3);
			finish();
			break;
			
		case R.id.btn4:
			Intent intent4 = new Intent(TestMain.this,TestURLActivity.class);
			startActivity(intent4);
			finish();
			break;
		}
	}
	
	

}
