package com.baoxiao.activity.group;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baoxiao.R;
import com.baoxiao.model.Groupuser;
import com.baoxiao.provider.GroupDAO;
import com.baoxiao.util.Define;

public class GroupXqTonghA extends Activity implements OnClickListener,OnItemSelectedListener
{

	private  TextView tvUserName,tvUserNumber,isBaibaox;
	private  Spinner sSex = null;
	private  EditText age,zhiwei,suoshu,beizhu;
	private  Spinner sBaoxgs = null;
	private  Button btnOk,btnNo,delGroup;
	
	private  ArrayAdapter<String> sexAdapter=null;
	private  ArrayAdapter<String> baoXianGsAdapter=null;
	
	private GroupDAO groupDAO;
	private String userName;
	private Groupuser user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group_tongx);

		groupDAO = new GroupDAO(this);
		userName = getIntent().getStringExtra("userName");
		user = groupDAO.HaveGroup(userName);
		initControl();
	}

	//初始化控件
		public void initControl(){
			//绑定控件
			tvUserName = (TextView) this.findViewById(R.id.clientName);
			tvUserNumber = (TextView) this.findViewById(R.id.clientNum);
			sSex = (Spinner) this.findViewById(R.id.sSex);
			age = (EditText) this.findViewById(R.id.etAge);
			suoshu = (EditText) this.findViewById(R.id.etSuoShu);
			zhiwei = (EditText) this.findViewById(R.id.etZhiWei);
			sBaoxgs = (Spinner) this.findViewById(R.id.sBaoXgs);
			isBaibaox = (TextView) this.findViewById(R.id.isBaibaox);
			beizhu = (EditText) this.findViewById(R.id.etBeizhu);
			btnOk = (Button) this.findViewById(R.id.xqBtnOk);
			btnNo = (Button) this.findViewById(R.id.xqBtnNo);
			delGroup = (Button) this.findViewById(R.id.delGroup);
			
			//给控件赋值
			tvUserName.setText(user.getName());
			tvUserNumber.setText(user.getPhoneid());
			age.setText(user.getAge());
			suoshu.setText(user.getCompany());
			zhiwei.setText(user.getOccupation());
			beizhu.setText(user.getRemarks());
			
			initSpinner();
			//Spinner赋值
			if(user.getSex()!=null){
				if(user.getSex().equals(Define.sexs[0])) 
					sSex.setSelection(0, true);
				if(user.getSex().equals(Define.sexs[1]))
					sSex.setSelection(1, true);
			}
			
			if(user.getInsurer()!=null){
				if(user.getInsurer().equals(Define.Insurers[0]))
					sBaoxgs.setSelection(0, true);
			}
				
			//是否是百宝箱用户
			if(user.getUsertag()=="1")
				isBaibaox.setText("是");
			else
				isBaibaox.setText("否");

			sSex.setOnItemSelectedListener(this);
			sBaoxgs.setOnItemSelectedListener(this);
			
			btnOk.setOnClickListener(this);
			btnNo.setOnClickListener(this);
			delGroup.setOnClickListener(this);
		}
		
		//初始化Spinner
		private void initSpinner()
		{
			sexAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item,Define.sexs);
			baoXianGsAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item,Define.Insurers);
			sexAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
			baoXianGsAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
			sSex.setAdapter(sexAdapter);
			sBaoxgs.setAdapter(baoXianGsAdapter);
		}
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3)
	{
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void onClick(View view)
	{
		// TODO 自动生成的方法存根
		switch (view.getId())
		{
		case R.id.xqBtnOk:
			user.setSex(sSex.getSelectedItem().toString());
			user.setAge(age.getText().toString());
			user.setInsurer(sBaoxgs.getSelectedItem().toString());
			user.setCompany(zhiwei.getText().toString());
			user.setRemarks(beizhu.getText().toString());

			if(isBaibaox.getText().toString()=="是")
				user.setUsertag("1");
			else
				user.setUsertag("0");
			int p = groupDAO.UpdateGroup(user);
			if(p>0){
				Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT).show();
				finish();
			}
			break;

		case R.id.xqBtnNo:
			finish();
			break;
			
		case R.id.delGroup:
			int d = groupDAO.DelPrice(user.getId().toString());
			if(d>0){
				Toast.makeText(GroupXqTonghA.this, "删除成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(GroupXqTonghA.this,GroupMyClientA.class);
				startActivity(intent);
				finish();
			}
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group_tongx, menu);
		return true;
	}
	
}
