package com.example.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SettingActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		TextView backTextView = (TextView) findViewById(R.id.textView3);
		backTextView.setVisibility(View.VISIBLE);
		backTextView.setOnClickListener(this);
		
        
		TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        titleTextView.setText("设置");
        
        TextView settingTV = (TextView) findViewById(R.id.textView1);
        settingTV.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		finish();
	}
}
