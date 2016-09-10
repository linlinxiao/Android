package com.baoxiao.activity.group;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
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

public class GroupMyClientA extends Activity implements OnItemClickListener,
		OnClickListener
{

	private GroupDAO groupDAO;
	private ListView listView;
	private List<Groupuser> users;
	private ImageView ivContacts, ivGroupBack;
	private TextView yaoyue;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group_myclient);

		listView = (ListView) this.findViewById(R.id.lvClient);
		ivContacts = (ImageView) this.findViewById(R.id.ivContacts);
		ivGroupBack = (ImageView) this.findViewById(R.id.GroupBack);
		yaoyue = (TextView) this.findViewById(R.id.yaoyue);

		groupDAO = new GroupDAO(GroupMyClientA.this);
		users = groupDAO.getUserList(Define.groupKeHu);
		GroupAdapter GroupAdapter = new GroupAdapter(users, this);
		listView.setAdapter(GroupAdapter);
		listView.setOnItemClickListener(this);
		yaoyue.setOnClickListener(this);
		ivContacts.setOnClickListener(this);
		ivGroupBack.setOnClickListener(this);

		SharedPreferences preferences = getSharedPreferences("loading", 0);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("groupType", Define.groupKeHu);
		editor.commit();
	}

	@Override
	public void onClick(View view)
	{
		// TODO 自动生成的方法存根
		switch (view.getId())
		{
		case R.id.ivContacts:
			Intent intent = new Intent(GroupMyClientA.this,
					GroupToContactsA.class);
			intent.putExtra("groupType", Define.groupKeHu);
			startActivity(intent);
			finish();
			break;

		case R.id.GroupBack:
			finish();
			break;

		case R.id.yaoyue:
			Intent intent2 = new Intent(GroupMyClientA.this, InvitationA.class);
			startActivity(intent2);
			finish();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO 自动生成的方法存根
		finish();
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id)
	{
		// TODO 自动生成的方法存根
		Groupuser groupuser = users.get(position);
		Intent intent = new Intent();
		intent.putExtra("userName", groupuser.getName());
		intent.setClass(GroupMyClientA.this, GroupXqClientA.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_client, menu);
		return true;
	}

}
