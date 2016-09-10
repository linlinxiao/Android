package com.baoxiao.activity.group;

import java.util.List;

import com.baoxiao.R;
import com.baoxiao.model.Groupuser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GroupAdapter extends BaseAdapter implements OnClickListener
{
	
	private List<Groupuser> users;
	private Context context;
	private Groupuser user;
	
	public GroupAdapter(List<Groupuser> users, Context context)
	{
		super();
		this.users = users;
		this.context = context;
	}

	@Override
	public int getCount()
	{
		// TODO 自动生成的方法存根
		return users.size();
	}

	@Override
	public Object getItem(int arg0)
	{
		// TODO 自动生成的方法存根
		return users.get(arg0);
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
		// TODO 自动生成的方法存根
		final ViewHolder holder;
		if(view==null){
			holder = new ViewHolder();
			LayoutInflater lif = LayoutInflater.from(context);
			view = lif.inflate(R.layout.group_myclient_item, null);
			holder.ivClientType = (ImageView) view.findViewById(R.id.ivClientType);
			holder.tvName = (TextView) view.findViewById(R.id.tvClientName);
			holder.ivIsTag = (ImageView) view.findViewById(R.id.ivIsTag);
			holder.imgNote = (ImageButton) view.findViewById(R.id.ivNote);
			holder.imgPhone = (ImageButton) view.findViewById(R.id.ivPhone);
			view.setTag(holder);
		}else{//重用的时候
			holder = (ViewHolder) view.getTag();
		}
		
		//填充数据
		user = users.get(position);
		
		if(user.getType()!=null){
			if(user.getType().equals("ke"))
				holder.ivClientType.setImageResource(R.drawable.group_myclient_ke);
			if(user.getType().equals("qian"))
				holder.ivClientType.setImageResource(R.drawable.group_myclient_qian);
			if(user.getType().equals("fei"))
				holder.ivClientType.setImageResource(R.drawable.group_myclient_fei);
		}else
			holder.ivClientType.setImageResource(R.drawable.group_myclient_fei);
		
		holder.tvName.setText(user.getName());
		if(user.getUsertag()!=null&&user.getUsertag().equals("1"))
			holder.ivIsTag.setImageResource(R.drawable.group_myclient_is);
		
		holder.imgNote.setTag(position);
		holder.imgPhone.setTag(position);
		holder.imgNote.setOnClickListener(this);
		holder.imgPhone.setOnClickListener(this);
		
		return view;
	}
	
	@Override
	public void onClick(View view)
	{
		// TODO 自动生成的方法存根
		int index = Integer.parseInt(view.getTag().toString());
		switch (view.getId())
		{
		case R.id.ivNote:
			Toast.makeText(context, "点击的是："+index, Toast.LENGTH_SHORT).show();
			break;

		case R.id.ivPhone:
			
			Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+users.get(index).getPhoneid()));
			context.startActivity(intent);
			break;
		}
	}

	class ViewHolder{
		ImageView ivClientType;
		TextView tvName;
		ImageView ivIsTag;
		ImageButton imgNote;
		ImageButton imgPhone;
	}

}
