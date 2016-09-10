package com.baoxiao.activity.group;

import com.baoxiao.R;
import com.baoxiao.model.Invitation;
import com.baoxiao.service.group.InvitaService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InvitaEditA extends Activity implements OnClickListener
{
	private Button btnOk, btnNo;
	private ImageView invitaBack;
	private EditText invitaEdit;
	private InvitaService invitaService;
	private String userId, type, which;
	private Invitation invitation;
	private TextView delIinvita;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.group_invita_edit);

		btnOk = (Button) this.findViewById(R.id.btnInvitaOk);
		btnNo = (Button) this.findViewById(R.id.btnInvitaNo);
		invitaBack = (ImageView) this.findViewById(R.id.InvitaBack);
		invitaEdit = (EditText) this.findViewById(R.id.invitaEdit);
		delIinvita = (TextView) this.findViewById(R.id.delHuaShu);
		which = getIntent().getStringExtra("which");
		invitaService = new InvitaService();
		getInvita();
		btnOk.setOnClickListener(this);
		btnNo.setOnClickListener(this);
		delIinvita.setOnClickListener(this);
		invitaBack.setOnClickListener(this);
		if (which.equals("1"))
			delIinvita.setVisibility(View.INVISIBLE);
	}

	// 获取
	private void getInvita()
	{

		if (which.equals("2"))
		{
			int invitaId = getIntent().getIntExtra("invitaId", 0);
			invitation = invitaService.getInvitaId(invitaId + "", this);
			invitaEdit.setText(invitation.getContent());
		}
	}

	@Override
	public void onClick(View view)
	{
		// TODO 自动生成的方法存根
		switch (view.getId())
		{
		case R.id.btnInvitaOk:
			Invitation invita = new Invitation();
			invita.setContent(invitaEdit.getText().toString());
			if (which.equals("1"))
			{
				userId = getSharedPreferences("loading", Activity.MODE_PRIVATE)
						.getString("userid", "");
				type = getSharedPreferences("loading", Activity.MODE_PRIVATE)
						.getString("groupType", "");
				invita.setUserid(userId);
				invita.setType(type);
				Long sid = invitaService.saveInvita(invita, this);
				if (sid > 0)
				{
					Toast.makeText(this, sid + " 添加成功!", Toast.LENGTH_SHORT)
							.show();
					Intent intent = new Intent(InvitaEditA.this,
							InvitationA.class);
					startActivity(intent);
					overridePendingTransition(R.anim.right_in, R.anim.right_out);
					finish();
				} else
				{
					Toast.makeText(this, sid + " 添加失败!", Toast.LENGTH_SHORT)
							.show();
				}
			} else if (which.equals("2"))
			{
				invita.setId(invitation.getId());
				invita.setType(invitation.getType());
				invita.setUserid(invitation.getUserid());
				int upid = invitaService.updateInvita(invita, this);
				if (upid > 0)
				{
					Toast.makeText(this, upid + " 编辑成功!", Toast.LENGTH_SHORT)
							.show();
					Intent intent = new Intent(InvitaEditA.this,
							InvitationA.class);
					startActivity(intent);
					overridePendingTransition(R.anim.right_in, R.anim.right_out);
					finish();
				} else
				{
					Toast.makeText(this, upid + " 编辑失败!", Toast.LENGTH_SHORT)
							.show();
				}
			}
			break;

		case R.id.btnInvitaNo:
			Intent intent = new Intent(this, InvitationA.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
			break;

		case R.id.InvitaBack:
			Intent intent2 = new Intent(this, InvitationA.class);
			startActivity(intent2);
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
			break;

		case R.id.delHuaShu:
			int i = invitaService.deleteInvita(invitation.getId().toString(),
					InvitaEditA.this);
			if (i > 0)
			{
				Toast.makeText(this, i + " 删除成功！", Toast.LENGTH_SHORT).show();
				Intent intent3 = new Intent(this, InvitationA.class);
				startActivity(intent3);
				overridePendingTransition(R.anim.right_in, R.anim.right_out);
				finish();
			} else
				Toast.makeText(this, i + " 删除失败!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent intent3 = new Intent(this, InvitationA.class);
			startActivity(intent3);
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
