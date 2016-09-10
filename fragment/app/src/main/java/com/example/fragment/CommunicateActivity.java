package com.example.fragment;

import com.example.fragment.CommunicateFragment.CommunicateListener;

import android.support.v7.app.ActionBarActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CommunicateActivity extends ActionBarActivity implements CommunicateListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_communicate);
		
		FragmentManager fragmentManager = getFragmentManager();
		CommunicateFragment fragment = (CommunicateFragment) fragmentManager.findFragmentById(R.id.fragment);
		fragment.setData("从activity传给静态fragment的数据");
		fragment.setCommunicateListener(this);
		
		CommunicateFragment1 fragment1 = new CommunicateFragment1();
		Bundle bundle = new Bundle();
		bundle.putString("name", "从activity传给静态fragment的数据");
		fragment1.setArguments(bundle);
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.add(R.id.activeFragment, fragment1, "fragment");
		transaction.commit();
	}

	@Override
	public void onBack(String data) {
		// TODO Auto-generated method stub
		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText(data);
	}
}
