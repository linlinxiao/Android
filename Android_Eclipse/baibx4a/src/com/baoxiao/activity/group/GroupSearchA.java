package com.baoxiao.activity.group;

import java.util.List;

import com.baoxiao.R;
import com.baoxiao.R.id;
import com.baoxiao.R.layout;
import com.baoxiao.R.menu;
import com.baoxiao.model.Groupuser;
import com.baoxiao.service.group.GroupService;
import com.baoxiao.util.Define;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 群搜索
 * @author Xiang
 */
public class GroupSearchA extends Activity implements OnClickListener,OnItemClickListener
{

	private EditText etSearch;
	private ImageView ivDeleteText;
	private ListView listSearch;
	private List<Groupuser> groupusers;
	private GroupAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.group_search);
		
		etSearch = (EditText) findViewById(R.id.etSearch);
		ivDeleteText = (ImageView) findViewById(R.id.ivDeleteText);
		listSearch = (ListView) findViewById(R.id.listSearch);
		//给listView填充数据
		paddingData();
		ivDeleteText.setOnClickListener(this);
		listSearch.setOnItemClickListener(this);
		setSearchTextChanged();
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
					groupusers =  GroupService.getSearchGroup(GroupSearchA.this,ssName);
					adapter = new GroupAdapter(groupusers, GroupSearchA.this);
					listSearch.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}
			}
		});
	}

	/**
	 * 将当前用户所有群信息填充进搜索界面的listView
	 * @return
	 */
	public List<Groupuser> paddingData(){
		groupusers = GroupService.getGroupList(GroupSearchA.this);
		adapter = new GroupAdapter(groupusers, GroupSearchA.this);
		listSearch.setAdapter(adapter);
		return groupusers;
	}

	/**
	 * 点击清空搜索框
	 */
	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
		case R.id.ivDeleteText:
			etSearch.setText("");
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO 自动生成的方法存根
		if(keyCode == KeyEvent.KEYCODE_BACK){
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	/**
	 * 选中群友后跳转到选中的群
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
		Groupuser groupuser = groupusers.get(arg2);
		Intent intent = new Intent();
		if(groupuser.getGrouptype().equals(Define.groupKeHu))
			intent.setClass(this, GroupMyClientA.class);
		if(groupuser.getGrouptype().equals(Define.groupZengY))
			intent.setClass(this, GroupMyzengyA.class);
		if(groupuser.getGrouptype().equals(Define.groupTongH))
			intent.setClass(this, GroupMyTonghA.class);
		startActivity(intent);
		finish();
	}

}
