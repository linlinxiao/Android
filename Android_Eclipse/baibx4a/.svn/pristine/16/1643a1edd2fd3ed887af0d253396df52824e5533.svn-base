package com.baoxiao.activity.group;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.baoxiao.R;
import com.baoxiao.model.Invitation;
import com.baoxiao.service.group.InvitaService;
import com.baoxiao.util.Define;

public class InvitationA extends Activity implements OnClickListener,
		OnItemClickListener
{

	private ImageView addInvita, invitaBack;
	private ListView lvInvita;
	private InvitaService invitaService;
	private String userId;
	private InvitaAdapter invitaAdapter;
	private List<Invitation> invitations;
	
	private String groupType;
	private String isMsm;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group_invitation);
		addInvita = (ImageView) this.findViewById(R.id.addHuaShu);
		invitaBack = (ImageView) this.findViewById(R.id.InvitaBack);
		lvInvita = (ListView) this.findViewById(R.id.lvInvita);
		//是编辑还是发送短信
		isMsm = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("isMsm", "");
		//哪一个群
		groupType = getSharedPreferences("loading", Activity.MODE_PRIVATE)
					.getString("groupType", "");
		//获取登录用户id
		userId = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("userid", "");
		//获取该用户所有话术
		invitaService = new InvitaService();
		invitations = invitaService.getInvitaList(userId, groupType, InvitationA.this);
		//适配器
		invitaAdapter = new InvitaAdapter(this, invitations, isMsm);
		lvInvita.setAdapter(invitaAdapter);
		addInvita.setOnClickListener(this);
		invitaBack.setOnClickListener(this);
		lvInvita.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		// TODO 自动生成的方法存根
		switch (view.getId())
		{
		case R.id.InvitaBack:
			Intent intent = new Intent();
			if(groupType.equals(Define.groupKeHu))
				intent.setClass(this, GroupMyClientA.class);
			if(groupType.equals(Define.groupZengY))
				intent.setClass(this, GroupMyzengyA.class);
			if(groupType.equals(Define.groupTongH))
				intent.setClass(this, GroupMyTonghA.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
			break;

		case R.id.addHuaShu:
			Intent intent2 = new Intent(this, InvitaEditA.class);
			intent2.putExtra("which", "1");
			startActivity(intent2);
			finish();
			break;

		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO 自动生成的方法存根
		if(keyCode == KeyEvent.KEYCODE_BACK){
			Intent intent = new Intent();
			if(groupType.equals(Define.groupKeHu))
				intent.setClass(this, GroupMyClientA.class);
			if(groupType.equals(Define.groupZengY))
				intent.setClass(this, GroupMyzengyA.class);
			if(groupType.equals(Define.groupTongH))
				intent.setClass(this, GroupMyTonghA.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, final int arg2, long arg3)
	{
		// TODO 自动生成的方法存根
		if (isMsm.equals(Define.F))
		{
			Intent intent = new Intent(this, InvitaXqA.class);
			intent.putExtra("id", invitations.get(arg2).getId().toString());
			startActivity(intent);
			
			finish();
		}
		if (isMsm.equals(Define.S))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(InvitationA.this);
			builder.setMessage("是否发送该条短信？");
			builder.setTitle("提示");
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
			{
				
				@Override
				public void onClick(DialogInterface arg0, int arg1)
				{
					String phone = getIntent().getStringExtra("phone");
					String content = invitations.get(arg2).getContent();
					SmsManager sms = SmsManager.getDefault();
					if (content.length() > 70)
					{
						// 返回多条短信
						List<String> contents = sms.divideMessage(content);
						for (String m : contents)
						{
							sms.sendTextMessage(phone, null, m, null, null);
						}
					} else
					{
						sms.sendTextMessage(phone, null, content, null, null);
					}
				}
			});
			builder.setNegativeButton("取消", null);
			builder.create().show();
		}
	}
}
