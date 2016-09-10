package com.baoxiao.activity.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baoxiao.R;
import com.baoxiao.model.Contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;

public class GroupToContactsAdapter extends BaseAdapter
{

	private Context context;
	private List<Contact> contacts;
	//保存checkbox是否被选中的状态
	public Map<Integer,Boolean> checkedMap;
	
	public GroupToContactsAdapter(Context context, List<Contact> contacts)
	{
		super();
		this.context = context;
		this.contacts = contacts;
		checkedMap =  new HashMap<Integer, Boolean>();
		for(int i=0;i<contacts.size();i++){  
			checkedMap.put(i, false); 
		}
	}

	@Override
	public int getCount()
	{
		// TODO 自动生成的方法存根
		return contacts.size();
	}

	@Override
	public Object getItem(int arg0)
	{
		// TODO 自动生成的方法存根
		return contacts.get(arg0);
	}

	@Override
	public long getItemId(int arg0)
	{
		// TODO 自动生成的方法存根
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup)
	{
		final ViewHolder holder;
		if(view==null){
			holder = new ViewHolder();
			LayoutInflater lif = LayoutInflater.from(context);
			view = lif.inflate(R.layout.group_contacts_item,null);
			holder.name = (TextView) view.findViewById(R.id.contactName);
			holder.phone = (TextView) view.findViewById(R.id.contactPhone);
			holder.isChoosed = (CheckBox) view.findViewById(R.id.contactIs);
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		
		Contact contact = contacts.get(position);
		holder.name.setText(contact.getName());
		holder.phone.setText(contact.getPhone());
		holder.isChoosed.setChecked(false);
		if(contact.isChoosed())
		{
			holder.isChoosed.setChecked(true);
		}
		holder.isChoosed.setChecked(getCheckedMap().get(position));
		final int p = position;
		holder.isChoosed.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				// TODO 自动生成的方法存根
				contacts.get(p).setChoosed(holder.isChoosed.isChecked());
			}
		});
		//填充控件
		holder.name.setText(contact.getName());
		holder.phone.setText(contact.getPhone());
		return view;
	}
	
	
	
	public Map<Integer, Boolean> getCheckedMap()
	{
		return checkedMap;
	}

	public void setCheckedMap(Map<Integer, Boolean> checkedMap)
	{
		this.checkedMap = checkedMap;
	}
	
	class ViewHolder{
		TextView name;
		TextView phone;
		CheckBox isChoosed;
	}
	
}
