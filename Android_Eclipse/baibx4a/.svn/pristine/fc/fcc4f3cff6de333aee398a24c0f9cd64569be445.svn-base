package com.baoxiao.activity.group;

import java.util.List;

import com.baoxiao.R;
import com.baoxiao.R.layout;
import com.baoxiao.R.menu;
import com.baoxiao.model.Invitation;
import com.baoxiao.provider.InvitationDAO;
import com.baoxiao.service.group.InvitaService;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class InvitationA extends Activity implements OnClickListener,OnItemClickListener
{
	
	private ImageView addInvita,invitaBack;
	private ListView lvInvita;
	private InvitaService invitaS;
	private String userId;
	private InvitaAdapter invitaAdapter;
	private List<Invitation> invitations;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group_invitation);
		
		addInvita = (ImageView) this.findViewById(R.id.addHuaShu);
		invitaBack = (ImageView) this.findViewById(R.id.InvitaBack);
		lvInvita = (ListView) this.findViewById(R.id.lvInvita);
		invitaS = new InvitaService();
		userId = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
		invitations = invitaS.getInvitaList(userId, InvitationA.this);
		invitaAdapter = new InvitaAdapter(this, invitations);
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
			Intent intent = new Intent(this,GroupMyClientA.class);
			startActivity(intent);
			finish();
			break;

		case R.id.addHuaShu:
			Intent intent2 = new Intent(this,InvitaEditA.class);
			intent2.putExtra("which", "1");
			startActivity(intent2);
			finish();
			break;
		}
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3)
	{
		// TODO 自动生成的方法存根
		Toast.makeText(this, "点击的id是："+invitations.get(arg2).getId(), Toast.LENGTH_SHORT).show();
	}

}
