package com.example.fragment;

import android.R.bool;
import android.support.v7.app.ActionBarActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends ActionBarActivity implements
		OnCheckedChangeListener {

	private RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		radioGroup.setOnCheckedChangeListener(this);

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int id) {
		// TODO Auto-generated method stub
		switch (id) {
		// 静态加载fragment
		case R.id.radio0:
			Intent intent = new Intent(this, FragmentActivity.class);
			startActivity(intent);
			break;
		// 动态加载fragment
		case R.id.radio1:
			SecondFragment fragment = new SecondFragment();
			FragmentManager manager = getFragmentManager();
			// 事务处理，类似于数据库的处理，每次添加、移除等操作过后，都需要commit才能够生效
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.replace(R.id.frame, fragment);
			transaction.addToBackStack(null);
			transaction.commit();
			break;
			// 切换fragment
		case R.id.radio2:
			FirstFragment fragment1 = new FirstFragment();
			FragmentManager manager1 = getFragmentManager();
			// 事务处理，类似于数据库的处理，每次添加、移除等操作过后，都需要commit才能够生效
			FragmentTransaction transaction1 = manager1.beginTransaction();
			transaction1.replace(R.id.frame, fragment1);
			transaction1.addToBackStack(null);
			transaction1.commit();
			break;
		case R.id.radio3:
			Intent intent1 = new Intent(this, CommunicateActivity.class);
			startActivity(intent1);
			break;
		default:
			break;
		}

	}
}
