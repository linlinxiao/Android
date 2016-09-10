package com.baoxiao.activity.homepage;

import com.baoxiao.BaseActivity;
import com.baoxiao.R;
import com.baoxiao.activity.group.GroupMyClientA;
import com.baoxiao.activity.group.GroupMyTonghA;
import com.baoxiao.activity.group.GroupMyzengyA;
import com.baoxiao.activity.group.GroupSearchA;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * '群组'界面布局
 *
 */
public class GroupA extends BaseActivity implements OnClickListener{

	private LinearLayout myClientLinear,myZengyuanLinear,myMyPeer;
	private TextView sousuo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group);
		
		myClientLinear = (LinearLayout) findViewById(R.id.llMyClient);
		myZengyuanLinear = (LinearLayout) findViewById(R.id.llMyZengyuan);
		myMyPeer = (LinearLayout) findViewById(R.id.llMyPeer);
		sousuo = (TextView) findViewById(R.id.sousuo);
		
		myClientLinear.setOnClickListener(this);
		myZengyuanLinear.setOnClickListener(this);
		myMyPeer.setOnClickListener(this);
		sousuo.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view)
	{
		// TODO 自动生成的方法存根
		switch (view.getId())
		{
		case R.id.llMyClient:
			Intent intent = new Intent(GroupA.this,GroupMyClientA.class);
			startActivity(intent);
			break;

		case R.id.llMyZengyuan:
			Intent intent2 = new Intent(GroupA.this,GroupMyzengyA.class);
			startActivity(intent2);
			break;
		
		case R.id.llMyPeer:
			Intent intent3 = new Intent(GroupA.this,GroupMyTonghA.class);
			startActivity(intent3);
			break;
		
		case R.id.sousuo:
			Intent intent4 = new Intent(GroupA.this,GroupSearchA.class);
			startActivity(intent4);
		}
	}
}
