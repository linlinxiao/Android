package com.baoxiao.test.activity.testmian;

import com.baoxiao.R;
import com.baoxiao.activity.productshow.BaoXianProductA;
import com.baoxiao.test.activity.webview.Flip;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestTwo extends Activity implements OnClickListener{
	private Button daoYeA,flip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testtwo);
		
		daoYeA = (Button) findViewById(R.id.daoYeA);
		flip = (Button) findViewById(R.id.flip);
		
		daoYeA.setOnClickListener(this);
		flip.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.daoYeA:
			//进入引导页面
			Intent intent = new Intent(TestTwo.this,Flip.class);
			Bundle b = new Bundle();
			b.putInt("maxPageCount", 8);
			b.putString("url", "product/shouhux/yanshi/canbaotishi");
			b.putString("format", "gif");
			b.putString("screenFlag","yanshi");
			intent.putExtras(b);
			startActivity(intent);
			finish();
			break;
		case R.id.flip:
			Intent intent2 = new Intent(TestTwo.this,BaoXianProductA.class);
			startActivity(intent2);
			finish();
			break;
		}
	}

	
}
