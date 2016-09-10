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

import com.baoxiao.R;
import com.baoxiao.model.Groupuser;
import com.baoxiao.provider.GroupDAO;
import com.baoxiao.util.Define;

public class GroupMyTonghA extends Activity implements OnItemClickListener
{
	GroupDAO groupDAO;
	ListView listView;
	List<Groupuser> users;
	ImageView ivContacts;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group_mytongh);
		listView = (ListView) this.findViewById(R.id.lvClient);
		ivContacts = (ImageView) this.findViewById(R.id.ivContacts);
		groupDAO = new GroupDAO(GroupMyTonghA.this);
		users = groupDAO.getUserList(Define.groupTongH);
		GroupAdapter GroupAdapter = new GroupAdapter(users, this);
		listView.setAdapter(GroupAdapter);
		listView.setOnItemClickListener(this);
		
		SharedPreferences preferences = getSharedPreferences("loading", 0);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("groupType", Define.groupTongH);
		editor.commit();
		
		ivContacts.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				// TODO 自动生成的方法存根
				Intent intent = new Intent(GroupMyTonghA.this, GroupToContactsA.class);
				intent.putExtra("groupType", Define.groupTongH);
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group_my_tongh, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3)
	{
		// TODO 自动生成的方法存根
		Groupuser groupuser = users.get(position);
		Intent intent = new Intent();
		intent.putExtra("userName", groupuser.getName());
		intent.setClass(GroupMyTonghA.this, GroupXqTonghA.class);
		startActivity(intent);
	}

}
