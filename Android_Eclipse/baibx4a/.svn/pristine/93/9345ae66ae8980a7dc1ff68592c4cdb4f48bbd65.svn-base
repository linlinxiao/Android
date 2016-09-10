package com.baoxiao.activity.group;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.baoxiao.R;
import com.baoxiao.model.Contact;
import com.baoxiao.model.Groupuser;
import com.baoxiao.provider.GroupDAO;
import com.baoxiao.service.group.ContactService;
import com.baoxiao.util.Define;

public class GroupToContactsA extends Activity implements OnClickListener,OnItemClickListener
{
	private ListView listView;
	private List<Contact> contacts; 
	private Button btnOK,btnNO,btnQX;
	private GroupDAO dao;
	private GroupToContactsAdapter adapter;
	private ImageView GroupBack;
	// 记录是否是多选状态，true为是，false不是
	private boolean isMultiple = false;
	 // 记录选中的条目数量
	private int checkNum;
 	private String type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group_to_contacts);
		listView = (ListView) findViewById(R.id.lvContact);
		type = (String) getIntent().getSerializableExtra("groupType");
		btnOK = (Button) findViewById(R.id.btnOK);
		btnNO = (Button) findViewById(R.id.btnNo);
		btnQX = (Button) findViewById(R.id.btnQX);
		GroupBack = (ImageView) findViewById(R.id.GroupBack);
		contacts = new ContactService().getPhoneContacts(getContentResolver());
		adapter = new GroupToContactsAdapter(this, contacts);
		listView.setAdapter(adapter);
	
		btnOK.setOnClickListener(this);
		btnNO.setOnClickListener(this);
		btnQX.setOnClickListener(this);
		
		GroupBack.setOnClickListener(this);
	}

	@SuppressLint("ShowToast")
	@Override
	public void onClick(View view)
	{
		// TODO 自动生成的方法存根
		switch (view.getId())
		{
		case R.id.btnOK:
			for(int i=0;i<contacts.size();i++){
				if(contacts.get(i).isChoosed()){
					dao = new GroupDAO(this);
					Groupuser user = new Groupuser();
					user.setName(contacts.get(i).getName());
					user.setPhoneid(contacts.get(i).getPhone());
					user.setGrouptype(type);
					Long uid = dao.SaveGroup(user);
					if(uid>0){
						Toast.makeText(this, "导入成功", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent();
						if(type.equals(Define.groupKeHu))
							intent.setClass(GroupToContactsA.this,GroupMyClientA.class);
						if(type.equals(Define.groupZengY))
							intent.setClass(GroupToContactsA.this,GroupMyzengyA.class);
						if(type.equals(Define.groupTongH))
							intent.setClass(GroupToContactsA.this,GroupMyTonghA.class);
						startActivity(intent);
						finish();
					}else{
						Toast.makeText(this, "导入失败", Toast.LENGTH_SHORT).show();
					}
				}
			};
			break;
		case R.id.btnNo:
			Intent intent1 = new Intent(GroupToContactsA.this,GroupMyClientA.class);
			startActivity(intent1);
			finish();
			break;
			
		case R.id.GroupBack:
			Intent intent = new Intent();
			if(type.equals(Define.groupKeHu))
				intent.setClass(GroupToContactsA.this,GroupMyClientA.class);
			if(type.equals(Define.groupZengY))
				intent.setClass(GroupToContactsA.this,GroupMyzengyA.class);
			if(type.equals(Define.groupTongH))
				intent.setClass(GroupToContactsA.this,GroupMyTonghA.class);
			startActivity(intent);
			finish();
			break;

		case R.id.btnQX:
			if(!isMultiple){
				isMultiple = true;
				for(int i=0;i<contacts.size();i++){
					adapter.getCheckedMap().put(i, true);
					contacts.get(i).setChoosed(true);
				}
				 // 数量设为list的长度
                checkNum = contacts.size();  
                // 刷新listview  
                adapter.notifyDataSetChanged();
			}else{
				isMultiple = false;
				// 遍历list的长度，将已选的按钮设为未选  
                for (int i = 0; i < contacts.size(); i++) {  
                    if (adapter.getCheckedMap().get(i)) {  
                        adapter.getCheckedMap().put(i, false);  
                        contacts.get(i).setChoosed(false);
                        checkNum--;// 数量减1  
                    }  
                }  
                // 刷新listview和TextView的显示  
                adapter.notifyDataSetChanged(); 
			}
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO 自动生成的方法存根
		Intent intent = new Intent();
		if(type.equals(Define.groupKeHu))
			intent.setClass(GroupToContactsA.this,GroupMyClientA.class);
		if(type.equals(Define.groupZengY))
			intent.setClass(GroupToContactsA.this,GroupMyzengyA.class);
		if(type.equals(Define.groupTongH))
			intent.setClass(GroupToContactsA.this,GroupMyTonghA.class);
		startActivity(intent);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		finish();
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3)
	{
		// TODO 自动生成的方法存根
			
	}
	
}
