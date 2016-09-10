package com.example.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener{
	
	private TextView titleTextView;
	private ProgressBar progressBar;
	private ViewStub stub;
	private TextView settingTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        titleTextView.setText("平安百宝箱");
        
        stub = (ViewStub) findViewById(R.id.stub);
        stub.inflate();
        
        progressBar = (ProgressBar)findViewById(R.id.progressBar1);
        progressBar.setActivated(true);
        
        settingTV = (TextView) findViewById(R.id.textView1);
//        settingTV.setVisibility(View.GONE);
        settingTV.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, SettingActivity.class);
		startActivity(intent);
	}
}
