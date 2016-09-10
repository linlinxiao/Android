package com.baoxiao.activity.homepage;

import com.baoxiao.BaseActivity;
import com.baoxiao.R;
import com.baoxiao.activity.mine.GoldGongLueA;
import com.baoxiao.activity.mine.MyAccountA;
import com.baoxiao.activity.mine.MyAgentA;
import com.baoxiao.activity.mine.MyCollectionA;
import com.baoxiao.util.GetSharedPreferencesValue;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * '我的'界面布局
 *
 */
public class MineA extends BaseActivity implements OnClickListener{
	private LinearLayout account,collection,agent,gold,agentLine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mine);
		
		initView();
	}

	private void initView() {
		account = (LinearLayout) findViewById(R.id.account);
		collection = (LinearLayout) findViewById(R.id.collection);
		agent = (LinearLayout) findViewById(R.id.agent);
		gold = (LinearLayout) findViewById(R.id.gold);
		agentLine = (LinearLayout) findViewById(R.id.agentLine);
	 
		account.setOnClickListener(this);
		collection.setOnClickListener(this);
		agent.setOnClickListener(this);
		gold.setOnClickListener(this);
		
		if(GetSharedPreferencesValue.getType(MineA.this).equals("user")){
			agent.setVisibility(View.GONE);
			agentLine.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.account:
			Intent intent = new Intent(this,MyAccountA.class);
			startActivity(intent);
			break;
		case R.id.collection:
			Intent intent2 = new Intent(this,MyCollectionA.class);
			startActivity(intent2);
			break;
		case R.id.agent:
			Intent intent3 = new Intent(this,MyAgentA.class);
			startActivity(intent3);
			break;
		case R.id.gold:
			Intent intent4 = new Intent(this,GoldGongLueA.class);
			startActivity(intent4);
			break;

		default:
			break;
		}
	}
}
