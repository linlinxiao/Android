package com.example.togglebutton;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class CheckButtonActivity extends ActionBarActivity implements OnCheckedChangeListener {
	
	//CheckBox可以实现多选
	private CheckBox checkBox1;
	private CheckBox checkBox2;
	private CheckBox checkBox3;
	private CheckBox checkBox4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_button);
		
		initViews();
	}

	private void initViews() {
		checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
		checkBox1.setOnCheckedChangeListener(this);
		
		checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
		checkBox2.setOnCheckedChangeListener(this);
		
		checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
		checkBox3.setOnCheckedChangeListener(this);
		
		checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
		checkBox4.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton checkBox, boolean checked) {
		// TODO Auto-generated method stub
		
		if(!checked) return;
		Toast.makeText(this, checkBox.getText().toString(), Toast.LENGTH_SHORT).show();
		
	}
}
