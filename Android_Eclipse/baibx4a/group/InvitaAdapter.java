package com.baoxiao.activity.group;

import java.util.List;

import com.baoxiao.R;
import com.baoxiao.model.Invitation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class InvitaAdapter extends BaseAdapter implements OnClickListener
{

	private Context context;
	private List<Invitation> invitas;
	private  ViewHolder holder;
	
	public InvitaAdapter(Context context, List<Invitation> invitas)
	{
		super();
		this.context = context;
		this.invitas = invitas;
	}

	@Override
	public int getCount()
	{
		// TODO �Զ����ɵķ������
		return invitas.size();
	}

	@Override
	public Object getItem(int arg0)
	{
		// TODO �Զ����ɵķ������
		return invitas.get(arg0);
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
		// TODO �Զ����ɵķ������
		if(view==null){
			holder = new ViewHolder();
			LayoutInflater lif = LayoutInflater.from(context);
			
			view = lif.inflate(R.layout.group_invita_item, null);
			
			holder.tvhuashu = (TextView) view.findViewById(R.id.tvhuashu);
			holder.upInvita = (ImageButton) view.findViewById(R.id.upInvita);
			
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		
		Invitation invita = invitas.get(position);
		holder.tvhuashu.setText(invita.getContent());
		holder.upInvita.setTag(position);
		holder.upInvita.setOnClickListener(this);
		
		return view;
	}
	
	class ViewHolder{
		TextView tvhuashu;
		ImageButton upInvita;
	}

	@Override
	public void onClick(View view)
	{
		// TODO �Զ����ɵķ������
		int index = Integer.parseInt(view.getTag().toString());
		switch (view.getId())
		{
		case R.id.upInvita:
			Intent intent = new Intent(context,InvitaEditA.class);
			
			intent.putExtra("which", "2");
			intent.putExtra("huashu", invitas.get(index).getContent());
			intent.putExtra("invitaId", invitas.get(index).getId());
			context.startActivity(intent);
			break;
		}
	}
	
}
