package com.baoxiao.activity.weihu;

import java.util.ArrayList;

import com.baoxiao.R;
import com.baoxiao.model.Groupuser;
import com.baoxiao.model.Invitation;
import com.baoxiao.service.group.GroupService;
import com.baoxiao.service.group.InvitaService;
import com.baoxiao.service.group.SyncService;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 'ϵͳ����'���ֽ���
 *
 */
public class DataBackUpA extends Activity implements OnClickListener
{
	private TextView backUp, huiFu, backUpTime;
	private ImageView mineBack;
	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weihu_backup);
		userId = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("userid", "");
		initView();
	}

	private void initView()
	{
		backUp = (TextView) findViewById(R.id.backUp);
		huiFu = (TextView) findViewById(R.id.huiFu);
		backUpTime = (TextView) findViewById(R.id.backUpTime);
		mineBack = (ImageView) findViewById(R.id.mineBack);

		backUp.setOnClickListener(this);
		huiFu.setOnClickListener(this);
		mineBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.backUp:
			Toast.makeText(getApplicationContext(), "�ѱ��ݣ���",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.huiFu:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("�Ƿ�ָ����ݣ�");
			builder.setTitle("��ʾ");
			builder.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface arg0, int arg1)
						{
							Toast.makeText(DataBackUpA.this, "��ʼ�ָ�Ⱥ������...",
									Toast.LENGTH_SHORT).show();
							new Thread(runnable).start();
						}
					});
			builder.setNegativeButton("ȡ��", null);
			builder.create().show();

			break;
		case R.id.mineBack:
			finish();
			break;

		default:
			break;
		}
	}

	ArrayList<Object> data = new ArrayList<Object>();
	ArrayList<Groupuser> groupusers = new ArrayList<Groupuser>();
	ArrayList<Invitation> invitas = new ArrayList<Invitation>();
	private Runnable runnable = new Runnable()
	{

		@SuppressWarnings("unchecked")
		@Override
		public void run()
		{
			data = SyncService.recoverData(userId);
			groupusers = (ArrayList<Groupuser>) data.get(0);
			invitas = (ArrayList<Invitation>) data.get(1);
			handler.sendEmptyMessage(1);
		}
	};
	private Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case 1:
				for (int i = 0; i < groupusers.size(); i++)
				{
					Groupuser groupuser = new Groupuser();
					groupuser = groupusers.get(i);
					Long is = GroupService.saveGroup(groupuser,
							DataBackUpA.this);
					if (is > 0)
					{
						Log.i("Ⱥ��Ϣ�ָ���", groupuser.getName() + "�ѻָ�");
					} else
					{
						Log.i("Ⱥ��Ϣ�ָ���", groupuser.getName() + "�ָ�ʧ��");
					}
					if (i == (groupusers.size() - 1))
					{
						Toast.makeText(DataBackUpA.this,
								"Ⱥ�����ϻָ��ɹ���", Toast.LENGTH_SHORT)
								.show();
					}
				}
				for (int i = 0; i < invitas.size(); i++)
				{
					Invitation invita = new Invitation();
					invita = invitas.get(i);
					Long is = InvitaService.insertInvita(invita,
							DataBackUpA.this);
					if (is > 0)
					{
						Log.i("�����ָ���", invita.getId() + "�ѻָ�");
					} else
					{
						Log.i("�����ָ���", invita.getId() + "�ָ�ʧ��");
					}
					if (i == (invitas.size() - 1))
					{
						Toast.makeText(DataBackUpA.this, "�������ϻָ��ɹ���",
								Toast.LENGTH_SHORT).show();
					}
				}
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		};
	};
}
