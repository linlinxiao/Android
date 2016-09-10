package com.baoxiao.activity.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baoxiao.R;
import com.baoxiao.model.Collection;
import com.baoxiao.model.Invitation;
import com.baoxiao.service.group.InvitaService;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;
import com.baoxiao.util.ShareService;

public class InvitaXqA extends Activity implements OnClickListener
{

	private EditText invitaEdit;
	private LinearLayout lit;
	private Button okBtn;
	private TextView delHuaShu;
	private Invitation invitation;
	private ImageView InvitaBack;
	private ListView collectList;
	private MessageHelper message;
	private List<Collection> list = new ArrayList<Collection>();
	private String[] collects;
	private boolean[] checked;
	private String[] selected;
	private Collection collection;
	private SimpleAdapter simpleAdapter;
	private ArrayList<HashMap<String, Object>> hmList;
	private String shoucid;
	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.group_invita_xq);

		invitaEdit = (EditText) this.findViewById(R.id.invitaEdit);
		lit = (LinearLayout) this.findViewById(R.id.addCollect);
		okBtn = (Button) this.findViewById(R.id.btnInvitaOk);
		delHuaShu = (TextView) this.findViewById(R.id.delHuaShu);
		InvitaBack = (ImageView) this.findViewById(R.id.InvitaBack);
		collectList = (ListView) this.findViewById(R.id.listCollect);
		collection = new Collection();
		invitation = new InvitaService().getInvitaId(getIntent()
				.getStringExtra("id"), this);
		// 获取登录用户id
		userId = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("userid", "");
		invitaEdit.setText(invitation.getContent());

		invitaEdit.setOnClickListener(this);
		lit.setOnClickListener(this);
		okBtn.setOnClickListener(this);
		delHuaShu.setOnClickListener(this);
		InvitaBack.setOnClickListener(this);
		hmList = new ArrayList<HashMap<String, Object>>();
		simpleAdapter = new SimpleAdapter(this, hmList, R.layout.group_scitem,
				new String[]
				{ "id", "title" }, new int[]
				{ R.id.cid, R.id.title });

	}

	@Override
	public void onBackPressed()
	{
		// TODO 自动生成的方法存根
		Intent intent = new Intent(this, InvitationA.class);
		startActivity(intent);
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
		finish();
		super.onBackPressed();
	}

	@Override
	public void onClick(View view)
	{
		// TODO 自动生成的方法存根
		switch (view.getId())
		{
		case R.id.delHuaShu:
			int sid = new InvitaService().deleteInvita(invitation.getId()
					.toString(), InvitaXqA.this);
			if (sid > 0)
			{
				Toast.makeText(this, sid + " 删除成功！", Toast.LENGTH_SHORT).show();
				Intent intent3 = new Intent(this, InvitationA.class);
				overridePendingTransition(R.anim.right_in, R.anim.right_out);
				startActivity(intent3);
				finish();
			} else
				Toast.makeText(this, sid + " 删除失败!", Toast.LENGTH_SHORT).show();
			break;

		case R.id.InvitaBack:
			Intent intent2 = new Intent(this, InvitationA.class);
			startActivity(intent2);
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
			break;

		case R.id.btnInvitaOk:
			String userid = getSharedPreferences("loading",
					Activity.MODE_PRIVATE).getString("userid", "");
			String shouc = "";
			for (int i = 0; i < hmList.size(); i++)
			{
				if ("".equals(shoucid))
					shoucid = hmList.get(i).get("id").toString();
				else
					shoucid = "," + hmList.get(i).get("id").toString();
				shouc = shouc + shoucid;
			}
			String url = "";
			if ("".equals(shoucid))
			{
				url = Define.Server + "huashu.action?userid=" + userid + "&id="
						+ invitation.getId();
			} else
			{
				url = Define.Server + "huashu.action?userid=" + userid + "&id="
						+ invitation.getId() + "&scid=" + shouc;
			}
			ShareService.startShare(url,"邀约话术！", this);
			break;

		case R.id.addCollect:
			new Thread(runnable).start();
			break;
		}
	}

	Runnable runnable = new Runnable()
	{

		@Override
		public void run()
		{
			message = InvitaService.getJsonList(userId);
			Message msg = new Message();
			handler.sendMessage(msg);
		}
	};

	Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			if (message.getResult().equals(Define.S))
			{
				list = message.getList();
				collects = new String[list.size()];
				checked = new boolean[list.size()];
				selected = new String[list.size()];
				for (int i = 0; i < list.size(); i++)
				{
					collects[i] = list.get(i).getTitle();
					checked[i] = false;
				}
				;
				AlertDialog.Builder builder = new AlertDialog.Builder(
						InvitaXqA.this);
				builder.setTitle("我的收藏");
				builder.setMultiChoiceItems(collects, null,
						new DialogInterface.OnMultiChoiceClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog,
									int which, boolean isChecked)
							{
								checked[which] = isChecked;
							}
						});

				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener()
						{

							@Override
							public void onClick(DialogInterface arg0, int arg1)
							{
								for (int i = 0; i < selected.length; i++)
								{
									HashMap<String, Object> map = new HashMap<String, Object>();
									if (checked[i] == true)
									{
										collection = list.get(i);
										map.put("id", collection.getId());
										map.put("title", collection.getTitle());
										hmList.add(map);
									}
								}
								collectList.setAdapter(simpleAdapter);
								SimpleAdapter sa = (SimpleAdapter) collectList
										.getAdapter();
								sa.notifyDataSetChanged();
							}
						});
				builder.setNegativeButton("取消", null).create();
				builder.show();
			}
		};
	};

}
