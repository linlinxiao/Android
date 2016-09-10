package com.baoxiao.activity.group;

import java.util.List;

import com.baoxiao.R;
import com.baoxiao.model.Groupuser;
import com.baoxiao.util.Define;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

/**
 * 群数据的适配器，数据分客户，同行，增员
 */
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
		final ViewHolder holder;
		// 初始化View
		if (view == null)
		{
			holder = new ViewHolder();
			LayoutInflater lif = LayoutInflater.from(context);
			view = lif.inflate(R.layout.group_myclient_item, null);
			holder.ivClientType = (ImageView) view
					.findViewById(R.id.ivClientType);
			holder.tvName = (TextView) view.findViewById(R.id.tvClientName);
			holder.ivIsTag = (ImageView) view.findViewById(R.id.ivIsTag);
			holder.imgNote = (ImageButton) view.findViewById(R.id.ivNote);
			holder.imgPhone = (ImageButton) view.findViewById(R.id.ivPhone);
			view.setTag(holder);
		} else
		{// 重用的时候
			holder = (ViewHolder) view.getTag();
		}

		// 填充数据
		user = users.get(position);
		if (user.getType() != null)
		{
			if (user.getType().equals("ke"))
				holder.ivClientType
						.setImageResource(R.drawable.group_myclient_ke);
			if (user.getType().equals("qian"))
				holder.ivClientType
						.setImageResource(R.drawable.group_myclient_qian);
			if (user.getType().equals("fei"))
				holder.ivClientType
						.setImageResource(R.drawable.group_myclient_fei);
		} else
			holder.ivClientType.setImageResource(R.drawable.group_myclient_fei);

		holder.tvName.setText(user.getName());
		if (user.getUsertag() != null && user.getUsertag().equals("1"))
			holder.ivIsTag.setImageResource(R.drawable.group_myclient_is);

		holder.imgNote.setTag(position);
		holder.imgPhone.setTag(position);
		holder.imgNote.setOnClickListener(this);
		holder.imgPhone.setOnClickListener(this);

		if (!Define.groupKeHu.equals(user.getGrouptype()))
		{
			holder.ivClientType.setVisibility(View.INVISIBLE);
		}

		return view;
	}

	/**
	 * 适配器点击事件，点击发送短信，拨打电话
	 */
	@Override
	public void onClick(View view)
	{
		// 获取点击的listView下标，知道点击的是第几个
		final int index = Integer.parseInt(view.getTag().toString());
		final String phone = users.get(index).getPhoneid();
		switch (view.getId())
		{
		// 通过短信发送话术
		case R.id.ivNote:
			// 因InvitationA存在两种状态:1.点击话术发送短信 2.点击话术进入编辑界面
			// 从此处点击进入InvitationA界面是发送短信，所以在sp存了个 isMsm
			// 值用于在InvitationA界面判断是否是发送短信
			SharedPreferences preferences = context.getSharedPreferences(
					"loading", 0);
			SharedPreferences.Editor editor = preferences.edit();
			editor.putString("isMsm", Define.S);
			editor.commit();
			Intent intent2 = new Intent(context, InvitationA.class);
			// phone 存的是电话号码
			intent2.putExtra("phone", phone);
			context.startActivity(intent2);
			((Activity) context).finish();
			break;
		// 拨打电话
		case R.id.ivPhone:
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("是否拨打？");
			builder.setTitle("提示");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface arg0, int arg1)
						{
							Intent intent = new Intent(Intent.ACTION_CALL, Uri
									.parse("tel:" + phone));
							context.startActivity(intent);
						}
					});
			builder.setNegativeButton("取消", null);
			builder.create().show();
			break;
		}
	}

	class ViewHolder
	{
		ImageView ivClientType;
		TextView tvName;
		ImageView ivIsTag;
		ImageButton imgNote;
		ImageButton imgPhone;
	}

}
