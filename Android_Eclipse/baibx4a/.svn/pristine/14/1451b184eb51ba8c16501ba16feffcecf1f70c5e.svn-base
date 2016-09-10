package com.baoxiao.test.activity.testmian;


import com.baoxiao.DaoYeA;
import com.baoxiao.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestThree extends Activity implements OnClickListener{

	private Button testVideo,testPtf,testLog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_three);
		
		testVideo = (Button) findViewById(R.id.btn_testVideo);
		testPtf = (Button) findViewById(R.id.btn_testPtf);
		testLog = (Button) findViewById(R.id.btn_testLog);
		
		testVideo.setOnClickListener(this);
		testPtf.setOnClickListener(this);
		testLog.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view)
	{
		// TODO 自动生成的方法存根
		switch (view.getId())
		{
		case R.id.btn_testVideo:
			Intent intent1 = new Intent(TestThree.this,TestYouKuVideo.class);
			startActivity(intent1);
			finish();
			break;
		
		case R.id.btn_testPtf:
			Intent intent2 = new Intent(TestThree.this,TestPtfActivity.class);
			startActivity(intent2);
			finish();
			break;
			
		case R.id.btn_testLog:
			Intent intent3 = new Intent(TestThree.this,DaoYeA.class);
			startActivity(intent3);
			finish();
			break;
		}
	}
	
}

