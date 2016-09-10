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
import com.baoxiao.util.Define;

public class GroupXqClientA extends Activity implements OnClickListener,
		OnItemSelectedListener
{

	private TextView tvUserName, tvUserNumber, isBaibaox, tvBirth;
	private Spinner sClientSex = null;
	private EditText clientAge, clientZhiye,  clientZhuzhi,
			clientBeizhu;
	private Spinner sClientType = null;
	private TextView lastTimeDate;
	private TextView nextTimeDate;
	private Button btnOk, btnNo, delGroup;
	private LinearLayout lastTime;
	private LinearLayout nextTime;

	private ArrayAdapter<String> sexAdapter = null;
	private ArrayAdapter<String> clientTypeAdapter = null;

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
		setContentView(R.layout.group_clientxq);

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
		tvBirth = (TextView) this.findViewById(R.id.birthTimeDate);
		sClientSex = (Spinner) this.findViewById(R.id.sClientSex);
		clientAge = (EditText) this.findViewById(R.id.clientAge);
		clientZhiye = (EditText) this.findViewById(R.id.clientZhiye);
		clientZhuzhi = (EditText) this.findViewById(R.id.clientZhuzhi);
		sClientType = (Spinner) this.findViewById(R.id.sClientType);
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
		tvBirth.setText(user.getBirth());
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
			if (user.getType().equals(Define.ke))
				sClientType.setSelection(0, true);
			if (user.getType().equals(Define.qian))
				sClientType.setSelection(1, true);
			if (user.getType().equals(Define.fei))
				sClientType.setSelection(2, true);
		} else
			sClientType.setSelection(2, true);

		// �Ƿ��ǰٱ����û�
		if (user.getUsertag() == "1")
			isBaibaox.setText("��");
		else
			isBaibaox.setText("��");

		sClientSex.setOnItemSelectedListener(this);
		sClientType.setOnItemSelectedListener(this);

		btnOk.setOnClickListener(this);
		btnNo.setOnClickListener(this);
		delGroup.setOnClickListener(this);
		lastTime.setOnClickListener(this);
		nextTime.setOnClickListener(this);
		tvBirth.setOnClickListener(this);
	}

	// ��ʼ��Spinner
	private void initSpinner()
	{
		sexAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.sexs);
		clientTypeAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Define.keHuLeiXing);
		sexAdapter
				.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		clientTypeAdapter
				.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		sClientSex.setAdapter(sexAdapter);
		sClientType.setAdapter(clientTypeAdapter);
	}

	@Override
	public void onClick(View view)
	{
		// TODO �Զ����ɵķ������
		switch (view.getId())
		{
		case R.id.xqBtnOk:
			user.setSex(sClientSex.getSelectedItem().toString());
			user.setAge(clientAge.getText().toString());
			user.setOccupation(clientZhiye.getText().toString());
			user.setBirth(tvBirth.getText().toString());
			user.setAddress(clientZhuzhi.getText().toString());
			user.setRemarks(clientBeizhu.getText().toString());
			user.setLasttime(lastTimeDate.getText().toString());
			user.setNexttime(nextTimeDate.getText().toString());

			if (sClientType.getSelectedItem().toString()
					.equals(Define.keHuLeiXing[0]))
				user.setType("ke");
			if (sClientType.getSelectedItem().toString()
					.equals(Define.keHuLeiXing[1]))
				user.setType("qian");
			if (sClientType.getSelectedItem().toString()
					.equals(Define.keHuLeiXing[2]))
				user.setType("fei");
			
			int p = groupDAO.UpdateGroup(user);
			if (p > 0)
			{
				Toast.makeText(this, "�޸ĳɹ���", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(GroupXqClientA.this,GroupMyClientA.class);
				startActivity(intent);
				finish();
			}
			break;

		case R.id.xqBtnNo:
			Intent intent = new Intent(GroupXqClientA.this,GroupMyClientA.class);
			startActivity(intent);
			finish();
			break;

		case R.id.birthTimeDate:
			upTime = tvBirth;
			new DatePickerDialog(GroupXqClientA.this, Datelistener, year,
					month, day).show();
			break;
			
		case R.id.lastTime:
			upTime = lastTimeDate;
			new DatePickerDialog(GroupXqClientA.this, Datelistener, year,
					month, day).show();
			break;
		case R.id.nextTime:
			upTime = nextTimeDate;
			new DatePickerDialog(GroupXqClientA.this, Datelistener, year,
					month, day).show();
			break;
		case R.id.delGroup:
			int d = groupDAO.DelPrice(user.getId().toString());
			if (d > 0)
			{
				Toast.makeText(GroupXqClientA.this, "ɾ���ɹ�", Toast.LENGTH_SHORT)
						.show();
				Intent intent2 = new Intent(GroupXqClientA.this,GroupMyClientA.class);
				startActivity(intent2);
				finish();
			}
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO �Զ����ɵķ������
		Intent intent = new Intent(GroupXqClientA.this,GroupMyClientA.class);
		startActivity(intent);
		finish();
		return super.onKeyDown(keyCode, event);
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

}
