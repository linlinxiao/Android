package com.baoxiao.activity.group;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baoxiao.R;
import com.baoxiao.model.Groupuser;
import com.baoxiao.provider.GroupDAO;
import com.baoxiao.service.group.GroupService;
import com.baoxiao.service.group.SyncService;
import com.baoxiao.util.Define;
import com.baoxiao.util.GetSharedPreferencesValue;

public class GroupXqZengyA extends Activity implements OnClickListener,
		OnItemSelectedListener
{

	private TextView tvUserName, tvUserNumber, isBaibaox;
	private Spinner sClientSex = null;
	private EditText clientAge, clientZhiye, clientZhuzhi, clientBeizhu;
	private Spinner sClientYix = null;
	private TextView lastTimeDate;
	private TextView nextTimeDate;
	private Button btnOk, btnNo, delGroup;
	private LinearLayout lastTime;
	private LinearLayout nextTime;

	private ArrayAdapter<String> sexAdapter = null;
	private ArrayAdapter<String> clientYixAdapter = null;

	private GroupDAO groupDAO;
	private String userName;
	private Groupuser user;
	private int year;
	private int month;
	private int day;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group_zengy);

		groupDAO = new GroupDAO(this);
		userName = getIntent().getStringExtra("userName");
		user = groupDAO.HaveGroup(userName);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		initControl();
	}

	// ��ʼ���ؼ�
	public void initControl()
	{
		// �󶨿ؼ�
		tvUserName = (TextView) this.findViewById(R.id.clientName);
		tvUserNumber = (TextView) this.findViewById(R.id.clientNum);
		sClientSex = (Spinner) this.findViewById(R.id.sClientSex);
		clientAge = (EditText) this.findViewById(R.id.clientAge);
		clientZhiye = (EditText) this.findViewById(R.id.clientZhiye);
		clientZhuzhi = (EditText) this.findViewById(R.id.clientZhuzhi);
		sClientYix = (Spinner) this.findViewById(R.id.sClientYiX);
		lastTimeDate = (TextView) this.findViewById(R.id.lastTimeDate);
		nextTimeDate = (TextView) this.findViewById(R.id.nextTimeDate);
		isBaibaox = (TextView) this.findViewById(R.id.isBaibaox);
		clientBeizhu = (EditText) this.findViewById(R.id.clientBeizhu);
		btnOk = (Button) this.findViewById(R.id.xqBtnOk);
		btnNo = (Button) this.findViewById(R.id.xqBtnNo);
		delGroup = (Button) this.findViewById(R.id.delGroup);

		lastTime = (LinearLayout) this.findViewById(R.id.lastTime);
		nextTime = (LinearLayout) this.findViewById(R.id.nextTime);

		// ���ؼ���ֵ
		tvUserName.setText(user.getName());
		tvUserNumber.setText(user.getPhoneid());
		clientAge.setText(user.getAge());
		clientZhiye.setText(user.getOccupation());
		clientZhuzhi.setText(user.getAddress());
		clientBeizhu.setText(user.getRemarks());
		lastTimeDate.setText(user.getLasttime());
		nextTimeDate.setText(user.getNexttime());

		initSpinner();
		// Spinner��ֵ
		if (user.getSex() != null)
		{
			if (user.getSex().equals(Define.sexs[0]))
				sClientSex.setSelection(0, true);
			if (user.getSex().equals(Define.sexs[1]))
				sClientSex.setSelection(1, true);
		}

		if (user.getType() != null)
		{
			if (user.getType().equals(Define.hen))
				sClientYix.setSelection(0, true);
			if (user.getType().equals(Define.qiang))
				sClientYix.setSelection(1, true);
			if (user.getType().equals(Define.yi))
				sClientYix.setSelection(2, true);
			if (user.getType().equals(Define.wu))
				sClientYix.setSelection(3, true);
		} else
			sClientYix.setSelection(2, true);

		// �Ƿ��ǰٱ����û�
		if (user.getUsertag() == "1")
			isBaibaox.setText("��");
		else
			isBaibaox.setText("��");

		sClientSex.setOnItemSelectedListener(this);
		sClientYix.setOnItemSelectedListener(this);

		btnOk.setOnClickListener(this);
		btnNo.setOnClickListener(this);
		delGroup.setOnClickListener(this);
		lastTime.setOnClickListener(this);
		nextTime.setOnClickListener(this);
	}

	// ��ʼ��Spinner
	private void initSpinner()
	{
		sexAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.sexs);
		clientYixAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.keHuYiXiang);
		sexAdapter
				.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		clientYixAdapter
				.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sClientSex.setAdapter(sexAdapter);
		sClientYix.setAdapter(clientYixAdapter);
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
		case R.id.xqBtnOk:
			user.setSex(sClientSex.getSelectedItem().toString());
			user.setAge(clientAge.getText().toString());
			user.setOccupation(clientZhiye.getText().toString());
			user.setAddress(clientZhuzhi.getText().toString());
			user.setRemarks(clientBeizhu.getText().toString());
			user.setLasttime(lastTimeDate.getText().toString());
			user.setNexttime(nextTimeDate.getText().toString());

			if (sClientYix.getSelectedItem().toString()
					.equals(Define.keHuYiXiang[0]))
				user.setType("hen");
			if (sClientYix.getSelectedItem().toString()
					.equals(Define.keHuYiXiang[1]))
				user.setType("qiang");
			if (sClientYix.getSelectedItem().toString()
					.equals(Define.keHuYiXiang[2]))
				user.setType("yi");
			if (sClientYix.getSelectedItem().toString()
					.equals(Define.keHuYiXiang[3]))
				user.setType("wu");
			if (isBaibaox.getText().toString() == "��")
				user.setUsertag("1");
			else
				user.setUsertag("0");
			int p = GroupService.updateGroup(user, this);
			if (p > 0)
			{
				Toast.makeText(this, "�޸ĳɹ���", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(GroupXqZengyA.this,
						GroupMyzengyA.class);
				startActivity(intent);
				overridePendingTransition(R.anim.right_in, R.anim.right_out);
				finish();
			}
			break;

		case R.id.xqBtnNo:
			Intent intent2 = new Intent(GroupXqZengyA.this, GroupMyzengyA.class);
			startActivity(intent2);
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
			break;

		case R.id.lastTime:
			upTime = lastTimeDate;
			new DatePickerDialog(GroupXqZengyA.this, Datelistener, year, month,
					day).show();
			break;
		case R.id.nextTime:
			upTime = nextTimeDate;
			new DatePickerDialog(GroupXqZengyA.this, Datelistener, year, month,
					day).show();
			break;
		case R.id.delGroup:
			String userId = GetSharedPreferencesValue.getUserId(this);
			int d = GroupService.deleteGroup(user.getId().toString(),
					user.getPhoneid(), this,userId);
			if (d > 0)
			{
				Toast.makeText(GroupXqZengyA.this, "ɾ���ɹ�", Toast.LENGTH_SHORT)
						.show();
				SyncService.syncDelGroup(user.getPhoneid(),user.getGrouptype(),userId);
				Intent intent = new Intent(GroupXqZengyA.this,
						GroupMyzengyA.class);
				startActivity(intent);
				overridePendingTransition(R.anim.right_in, R.anim.right_out);
				finish();
			}
			break;
		}
	}

	private TextView upTime;
	private DatePickerDialog.OnDateSetListener Datelistener = new DatePickerDialog.OnDateSetListener()
	{
		/**
		 * params��view�����¼���������� params��myyear����ǰѡ����� params��monthOfYear����ǰѡ�����
		 * params��dayOfMonth����ǰѡ�����
		 */
		@Override
		public void onDateSet(DatePicker view, int myyear, int monthOfYear,
				int dayOfMonth)
		{

			// �޸�year��month��day�ı���ֵ���Ա��Ժ󵥻���ťʱ��DatePickerDialog����ʾ��һ���޸ĺ��ֵ
			year = myyear;
			month = monthOfYear;
			day = dayOfMonth;
			// ��������
			updateDate();

		}

		// ��DatePickerDialog�ر�ʱ������������ʾ
		private void updateDate()
		{
			// ��TextView����ʾ����
			upTime.setText(year + "-" + (month + 1) + "-" + day);
		}
	};

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3)
	{
		// TODO �Զ����ɵķ������

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
		// TODO �Զ����ɵķ������

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO �Զ����ɵķ������
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent intent = new Intent(GroupXqZengyA.this, GroupMyzengyA.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
