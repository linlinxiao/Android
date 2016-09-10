package com.baoxiao.activity.group;

import com.baoxiao.R;
import com.baoxiao.model.Invitation;
import com.baoxiao.provider.InvitationDAO;
import com.baoxiao.service.group.InvitaService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class InvitaEditA extends Activity implements OnClickListener
{
	private Button btnOk,btnNo;
	private ImageView invitaBack;
	private EditText invitaEdit;
	private InvitaService invitaService;
	private String userId;
	private String type;
	private String content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group_invita_edit);
		
		btnOk = (Button) this.findViewById(R.id.btnInvitaOk);
		btnNo = (Button) this.findViewById(R.id.btnInvitaNo);
		invitaBack = (ImageView) this.findViewById(R.id.InvitaBack);
		invitaEdit = (EditText) this.findViewById(R.id.invitaEdit);
		invitaService = new InvitaService();
		content = getIntent().getStringExtra("huashu");
		invitaEdit.setText(content);
		btnOk.setOnClickListener(this);
		btnNo.setOnClickListener(this);
		invitaBack.setOnClickListener(this);
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
			String which = getIntent().getStringExtra("which");
			if(which.equals("1")){
				userId = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
				type = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("groupType", "");
				invita.setUserid(userId);
				invita.setType(type);
				Long sid = invitaService.saveInvita(invita, this);
				if(sid>0){
					Toast.makeText(this, sid+" 添加成功!", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(InvitaEditA.this, InvitationA.class);
					startActivity(intent);
					finish();
				}else {
					Toast.makeText(this, sid+" 添加失败!", Toast.LENGTH_SHORT).show();
				}
			}else if(which.equals("2")){
				invita.setId(getIntent().getIntExtra("invitaId", 0));
				int upid = invitaService.updateInvita(invita, this);
				if(upid>0){
					Toast.makeText(this, upid+" 编辑成功!", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(InvitaEditA.this, InvitationA.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(this, upid+" 编辑失败!", Toast.LENGTH_SHORT).show();
				}
			}
			break;

		case R.id.btnInvitaNo:
			Intent intent = new Intent(this, InvitationA.class);
			startActivity(intent);
			finish();
			break;
		
		case R.id.InvitaBack:
			Intent intent2 = new Intent(this, InvitationA.class);
			startActivity(intent2);
			finish();
			break;
		}
	}
}
