package com.baoxiao.test.activity.testmian;

import com.baoxiao.R;
import com.baoxiao.test.activity.webview.URLWebView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TestURLActivity extends Activity
{
	EditText editText;
	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_url);
		
		editText = (EditText) findViewById(R.id.et_URL);
		button = (Button) findViewById(R.id.btn_url);
		
		
		button.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View view)
			{
				// TODO 自动生成的方法存根
				Intent intent = new Intent();
				intent.setClass(TestURLActivity.this, URLWebView.class);
				String url = editText.getText().toString();
				intent.putExtra("url", url);
				startActivity(intent);
			}
		});
		
	}
	
}
