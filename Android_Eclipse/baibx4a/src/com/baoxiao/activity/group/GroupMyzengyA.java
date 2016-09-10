package com.baoxiao.activity.group;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baoxiao.R;
import com.baoxiao.model.Groupuser;
import com.baoxiao.provider.GroupDAO;
import com.baoxiao.util.Define;

public class GroupMyzengyA extends Activity implements OnItemClickListener,OnClickListener
{

	GroupDAO groupDAO;
	ListView listView;
	TextView yaoyue;
	List<Groupuser> users;
	ImageView ivContacts, ivGroupBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group_myzengy);
		listView = (ListView) this.findViewById(R.id.lvClient);
		ivContacts = (ImageView) this.findViewById(R.id.ivContacts);
		yaoyue = (TextView) this.findViewById(R.id.yaoyue);
		ivGroupBack = (ImageView) this.findViewById(R.id.GroupBack);
		groupDAO = new GroupDAO(GroupMyzengyA.this);
		users = groupDAO.getUserList(Define.groupZengY);
		GroupAdapter GroupAdapter = new GroupAdapter(users, this);
		listView.setAdapter(GroupAdapter);
		listView.setOnItemClickListener(this);
		
		SharedPreferences preferences = getSharedPreferences("loading", 0);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("groupType", Define.groupZengY);
		editor.commit();
		
		ivContacts.setOnClickListener(this);
		yaoyue.setOnClickListener(this);
		ivGroupBack.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group_myzengy, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3)
	{
		// TODO 自动生成的方法存根		
		Groupuser groupuser = users.get(position);
		Intent intent = new Intent();
		intent.putExtra("userName", groupuser.getName());
		intent.setClass(GroupMyzengyA.this, GroupXqZengyA.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onClick(View view)
	{
		// TODO 自动生成的方法存根
		switch (view.getId())
		{
		case R.id.ivContacts:
			Intent intent = new Intent(GroupMyzengyA.this, GroupToContactsA.class);
			intent.putExtra("groupType", Define.groupZengY);
			startActivity(intent);
			finish();
			break;

		case R.id.yaoyue:
			Intent intent2 = new Intent(GroupMyzengyA.this, InvitationA.class);
			startActivity(intent2);
			finish();
			break;
			
		case R.id.GroupBack:
			finish();
			break;
		}
	}

}
