package com.example.intent;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends ActionBarActivity implements OnClickListener{
	
	private TextView textView;
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		initViews();
	}
	
	private void initViews() {
		Intent intent = getIntent();
		String text = intent.getStringExtra("first");
		textView = (TextView) findViewById(R.id.textView2);
		textView.setText(text);
		
		btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View btn) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, FirstActivity.class);
		intent.putExtra("second", "第二个页面回来的");
		setResult(2, intent);
		finish();
	}
}
