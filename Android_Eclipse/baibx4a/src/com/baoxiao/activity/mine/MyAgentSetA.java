package com.baoxiao.activity.mine;

import com.baoxiao.R;
import com.baoxiao.model.Userb;
import com.baoxiao.service.mine.MineService;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * '编辑我的保险代理人'布局界面
 *
 */
public class MyAgentSetA extends Activity implements OnClickListener{

	private EditText agentNumber;
	private TextView searchAgent,agentSet;
	private TextView agentName,agentphone,userbId;
	private ImageView agentBack;
	private String userId;
	private SharedPreferences sp;
	private String number = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	    setContentView(R.layout.mine_agentset);
	    userId = getSharedPreferences("loading", 0).getString("userid", null);
	    initView();
	}
	private void initView() {
		agentBack = (ImageView) findViewById(R.id.agentBack);
		agentNumber = (EditText) findViewById(R.id.agentNumber);
		searchAgent = (TextView) findViewById(R.id.searchAgent);
		agentSet = (TextView) findViewById(R.id.agentSet);
		agentName = (TextView) findViewById(R.id.agentName);
		agentphone = (TextView) findViewById(R.id.agentphone);
		userbId = (TextView) findViewById(R.id.userbId);
		agentBack.setOnClickListener(this);
		searchAgent.setOnClickListener(this);
		agentSet.setOnClickListener(this);
		    
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.agentBack:
			Intent intent = new Intent(this,MyAgentA.class);
			startActivity(intent);
			finish();
			break;
		case R.id.searchAgent:
//			number = agentNumber.getText().toString();
			new Thread(runnable).start();
			break;
		case R.id.agentSet:
//			number = agentNumber.getText().toString();
//			if(number!=null){
//				Toast.makeText(getApplicationContext(), "手机号码不能为空", Toast.LENGTH_SHORT).show();
//			}else{
			new Thread(runnable2).start();
//			}
			break;
		default:
			break;
		}
	}
	
	MessageHelper msgHelper = null;
	Userb userb = null;
	String result = "";
	private Runnable runnable2 = new Runnable()
	{
		
		@Override
		public void run()
		{
			result = MineService.setAgent(userId, agentNumber.getText().toString());
			Message msg = new Message();
			msg.what = 3;
			handler.sendMessage(msg);
		}
	};
	
	private Runnable runnable = new Runnable()
	{
		public void run()
		{
			msgHelper = MineService.AgentResult(agentNumber.getText().toString());
			Message msg = new Message();
			if (msgHelper.getResult().equals(Define.S))
			{
				userb = MineService.MyInsuranceAgent(agentNumber.getText().toString());
				msg.what = 1;
			}else{
				msg.what = 2;
			}
			
			handler.sendMessage(msg);
		}
	};
	
	private Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			switch (msg.what)
			{
			case 1:
				agentName.setText(userb.getName());
				agentphone.setText(userb.getPhone());
				userbId.setText(userb.getUserid());
				break;

			case 2:
				Toast.makeText(MyAgentSetA.this, msgHelper.getMessage(), Toast.LENGTH_SHORT).show();
				agentNumber.setText("");
				break;
				
			case 3:
				if(result.equals(Define.S)){
					sp = getSharedPreferences("loading", MODE_APPEND);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("insurerperson", userb.getUserid());
					editor.commit();
					Toast.makeText(MyAgentSetA.this, "设置成功！", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(MyAgentSetA.this, "设置失败！", Toast.LENGTH_SHORT).show();
				}
			}
			super.handleMessage(msg);
		};
	};
}
