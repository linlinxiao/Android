package com.baoxiao.activity.group;

import java.util.List;

import com.baoxiao.R;
import com.baoxiao.model.Invitation;
import com.baoxiao.util.Define;

import android.app.Activity;
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
	private String isMsm;
	private  ViewHolder holder;
	
	public InvitaAdapter(Context context, List<Invitation> invitas, String isMsm)
	{
		super();
		this.context = context;
		this.invitas = invitas;
		this.isMsm = isMsm;
	}

	@Override
	public int getCount()
	{
		// TODO 自动生成的方法存根
		return invitas.size();
	}

	@Override
	public Object getItem(int arg0)
	{
		// TODO 自动生成的方法存根
		return invitas.get(arg0);
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
		if(isMsm.equals(Define.F))
			holder.upInvita.setVisibility(View.VISIBLE);
		else
			holder.upInvita.setVisibility(View.INVISIBLE);
		return view;
	}
	
	class ViewHolder{
		TextView tvhuashu;
		ImageButton upInvita;
	}

	@Override
	public void onClick(View view)
	{
		int index = Integer.parseInt(view.getTag().toString());
		switch (view.getId())
		{
		case R.id.upInvita:
			Intent intent = new Intent(context,InvitaEditA.class);
			intent.putExtra("which", "2");
			intent.putExtra("invitaId", invitas.get(index).getId());
			context.startActivity(intent);
			break;
		}
	}
	
}
