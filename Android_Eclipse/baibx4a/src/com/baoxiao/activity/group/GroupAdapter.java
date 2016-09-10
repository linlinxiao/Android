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
 * Ⱥ���ݵ������������ݷֿͻ���ͬ�У���Ա
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
		// TODO �Զ����ɵķ������
		return users.size();
	}

	@Override
	public Object getItem(int arg0)
	{
		// TODO �Զ����ɵķ������
		return users.get(arg0);
	}

	@Override
	public long getItemId(int arg0)
	{
		// TODO �Զ����ɵķ������
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup)
	{
		final ViewHolder holder;
		// ��ʼ��View
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
		{// ���õ�ʱ��
			holder = (ViewHolder) view.getTag();
		}

		// �������
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
	 * ����������¼���������Ͷ��ţ�����绰
	 */
	@Override
	public void onClick(View view)
	{
		// ��ȡ�����listView�±֪꣬��������ǵڼ���
		final int index = Integer.parseInt(view.getTag().toString());
		final String phone = users.get(index).getPhoneid();
		switch (view.getId())
		{
		// ͨ�����ŷ��ͻ���
		case R.id.ivNote:
			// ��InvitationA��������״̬:1.����������Ͷ��� 2.�����������༭����
			// �Ӵ˴��������InvitationA�����Ƿ��Ͷ��ţ�������sp���˸� isMsm
			// ֵ������InvitationA�����ж��Ƿ��Ƿ��Ͷ���
			SharedPreferences preferences = context.getSharedPreferences(
					"loading", 0);
			SharedPreferences.Editor editor = preferences.edit();
			editor.putString("isMsm", Define.S);
			editor.commit();
			Intent intent2 = new Intent(context, InvitationA.class);
			// phone ����ǵ绰����
			intent2.putExtra("phone", phone);
			context.startActivity(intent2);
			((Activity) context).finish();
			break;
		// ����绰
		case R.id.ivPhone:
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("�Ƿ񲦴�");
			builder.setTitle("��ʾ");
			builder.setPositiveButton("ȷ��",
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
			builder.setNegativeButton("ȡ��", null);
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
