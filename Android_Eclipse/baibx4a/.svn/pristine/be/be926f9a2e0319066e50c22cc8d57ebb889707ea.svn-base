package com.baoxiao.activity.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.baoxiao.R;
import com.baoxiao.model.Contact;
import com.baoxiao.model.Groupuser;
import com.baoxiao.provider.GroupDAO;
import com.baoxiao.service.group.ContactService;
import com.baoxiao.service.group.SyncService;
import com.baoxiao.util.Define;
import com.baoxiao.util.NetService;

public class GroupToContactsA extends Activity implements OnClickListener,
		OnItemClickListener
{
	private ListView listView;
	private List<Contact> contacts;
	private Button btnOK, btnNO, btnQX;
	private GroupDAO dao;
	private GroupToContactsAdapter adapter;
	private ImageView GroupBack;
	// 记录是否是多选状态，true为是，false不是
	private boolean isMultiple = false;

	private String type;
	private List<Groupuser> grouplist;
	private ImageView ivDeleteText;
	private EditText etSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.group_to_contacts);
		initView();
		paddingData();
		viewOnClick();
		setSearchTextChanged();
	}

	// 填充数据 已存在的不显示在通讯录
	public void paddingData()
	{
		contacts = new ContactService().getPhoneContacts(getContentResolver());
		grouplist = new GroupDAO(this).getUserList();
		for (int i = 0; i < contacts.size(); i++)
		{
			for (int j = 0; j < grouplist.size(); j++)
			{
				String contactPhone = contacts.get(i).getPhone();
				String groupPhone = grouplist.get(j).getPhoneid();
				if (contactPhone.equals(groupPhone))
				{
					contacts.remove(i);
				}
			}
		}
		adapter = new GroupToContactsAdapter(GroupToContactsA.this, contacts);
		listView.setAdapter(adapter);
	}

	public void viewOnClick()
	{
		btnOK.setOnClickListener(this);
		btnNO.setOnClickListener(this);
		btnQX.setOnClickListener(this);
		GroupBack.setOnClickListener(this);
		ivDeleteText.setOnClickListener(this);
	}

	public void initView()
	{
		listView = (ListView) findViewById(R.id.lvContact);
		type = (String) getIntent().getSerializableExtra("groupType");
		btnOK = (Button) findViewById(R.id.btnOK);
		btnNO = (Button) findViewById(R.id.btnNo);
		btnQX = (Button) findViewById(R.id.btnQX);
		GroupBack = (ImageView) findViewById(R.id.GroupBack);
		ivDeleteText = (ImageView) findViewById(R.id.ivDeleteText);
		etSearch = (EditText) findViewById(R.id.etSearch);
	}

	@SuppressLint("ShowToast")
	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
		case R.id.btnOK:
			String price = "";
			final Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < contacts.size(); i++)
			{
				if (contacts.get(i).isChoosed())
				{
					dao = new GroupDAO(this);
					final Groupuser user = new Groupuser();
					user.setName(contacts.get(i).getName());
					user.setPhoneid(contacts.get(i).getPhone());
					user.setGrouptype(type);
					user.setUserid(getSharedPreferences("loading",
							Activity.MODE_PRIVATE).getString("userid", ""));
					if (dao.phoneGroup(contacts.get(i).getPhone()))
					{
						Toast.makeText(this,
								contacts.get(i).getName() + "在其它群中已存在",
								Toast.LENGTH_SHORT).show();
					} else
					{
						Long uid = dao.SaveGroup(user);

						if (uid > 0)
						{
							Toast.makeText(this, "导入成功", Toast.LENGTH_SHORT)
									.show();
							Groupuser u = new GroupDAO(this).HaveGroup(contacts
									.get(i).getName());
							// 拼接同步后台数据库的语句
							String p;
							if (price.equals(""))
								p = new SyncService().getPara(u);
							else
								p = "|" + new SyncService().getPara(u);
							price = price + p;
							map.put("group", price);
							// 添加成功后返回的页面
							Intent intent = new Intent();
							if (type.equals(Define.groupKeHu))
								intent.setClass(GroupToContactsA.this,
										GroupMyClientA.class);
							if (type.equals(Define.groupZengY))
								intent.setClass(GroupToContactsA.this,
										GroupMyzengyA.class);
							if (type.equals(Define.groupTongH))
								intent.setClass(GroupToContactsA.this,
										GroupMyTonghA.class);
							startActivity(intent);
							finish();
						} else
						{
							Toast.makeText(this, "导入失败", Toast.LENGTH_SHORT)
									.show();
						}
					}

				}
			}
			;
			// 这个线程用于增加后台群数据，跟后台数据同步
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					SyncService.syncSaveGroup(map);
				}
			}).start();
			break;
			
		case R.id.btnNo:
			Intent intent1 = new Intent(GroupToContactsA.this,
					GroupMyClientA.class);
			if (type.equals(Define.groupKeHu))
				intent1.setClass(GroupToContactsA.this, GroupMyClientA.class);
			if (type.equals(Define.groupZengY))
				intent1.setClass(GroupToContactsA.this, GroupMyzengyA.class);
			if (type.equals(Define.groupTongH))
				intent1.setClass(GroupToContactsA.this, GroupMyTonghA.class);
			startActivity(intent1);
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
			break;

		case R.id.GroupBack:
			Intent intent = new Intent();
			if (type.equals(Define.groupKeHu))
				intent.setClass(GroupToContactsA.this, GroupMyClientA.class);
			if (type.equals(Define.groupZengY))
				intent.setClass(GroupToContactsA.this, GroupMyzengyA.class);
			if (type.equals(Define.groupTongH))
				intent.setClass(GroupToContactsA.this, GroupMyTonghA.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
			break;

		case R.id.btnQX:
			// 记录选中的条目数量
			int checkNum = 0;
			if (!isMultiple)
			{
				isMultiple = true;
				for (int i = 0; i < contacts.size(); i++)
				{
					adapter.getCheckedMap().put(i, true);
					contacts.get(i).setChoosed(true);
				}
				// 数量设为list的长度
				checkNum = contacts.size();
				// 刷新listview
				adapter.notifyDataSetChanged();
			} else
			{
				isMultiple = false;
				// 遍历list的长度，将已选的按钮设为未选
				for (int i = 0; i < contacts.size(); i++)
				{
					if (adapter.getCheckedMap().get(i))
					{
						adapter.getCheckedMap().put(i, false);
						contacts.get(i).setChoosed(false);
						checkNum--;// 数量减1
					}
				}
				// 刷新listview和TextView的显示
				adapter.notifyDataSetChanged();
			}
			break;
		case R.id.ivDeleteText:
			etSearch.setText("");
			break;

		}
	}

	private void setSearchTextChanged()
	{
		etSearch.addTextChangedListener(new TextWatcher()
		{

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3)
			{
				// TODO 自动生成的方法存根

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3)
			{
				// TODO 自动生成的方法存根

			}

			@Override
			public void afterTextChanged(Editable arg0)
			{
				// TODO 自动生成的方法存根
				if (arg0.length() == 0)
				{
					ivDeleteText.setVisibility(View.GONE);
					paddingData();
					adapter.notifyDataSetChanged();
				} else
				{
					ivDeleteText.setVisibility(View.VISIBLE);
					String ssName = arg0.toString();
					for (int i = 0; i < contacts.size(); i++)
					{
						String cName;
						if(ssName.length()>=contacts.get(i).getName().length()){
							cName = contacts.get(i).getName();
						}else{
							cName = contacts.get(i).getName()
									.substring(0, ssName.length());
						}

						if (!ssName.equals(cName))
						{
							contacts.remove(i);
							i--;
						}
					}
					adapter.notifyDataSetChanged();
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO 自动生成的方法存根
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent intent = new Intent();
			if (type.equals(Define.groupKeHu))
				intent.setClass(GroupToContactsA.this, GroupMyClientA.class);
			if (type.equals(Define.groupZengY))
				intent.setClass(GroupToContactsA.this, GroupMyzengyA.class);
			if (type.equals(Define.groupTongH))
				intent.setClass(GroupToContactsA.this, GroupMyTonghA.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3)
	{
	}

}
