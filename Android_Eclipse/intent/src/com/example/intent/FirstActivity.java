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

public class FirstActivity extends ActionBarActivity implements OnClickListener{
	
	private Button btn1;
	private Button btn2;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		initViews();
	}
	
	private void initViews() {
		btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(this);
		
		btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(this);
		
		textView = (TextView) findViewById(R.id.textView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View btn) {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(this, SecondActivity.class);
		intent.putExtra("first", "第一个页面来的");
		switch (btn.getId()) {
		case R.id.button1:
			startActivity(intent);
			break;

		default:
			startActivityForResult(intent, 1);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, data);
		
		String text = data.getStringExtra("second");
		textView.setText(text);
	}
	
	
}
