package com.baoxiao;

import com.baoxiao.model.Userb;
import com.baoxiao.service.userproduct.UserService;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * ��ӭҳ��
 *
 */
public class WelcomeA extends Activity implements Runnable
{

	public Boolean flag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.welcome);

		// �����߳�
		new Thread(this).start();

		SharedPreferences sp = getSharedPreferences("loading", 0);
		SharedPreferences.Editor edit = sp.edit();
		edit.putBoolean("loading_flag", true);
		edit.commit();

	}

	@Override
	public void run()
	{
		try
		{
			// ����100����֮����ת����¼����
			Thread.sleep(2000);
			SharedPreferences sp = getSharedPreferences("loading", 0);
			if (!sp.getBoolean("logstate_flag", false))
			{
				Intent intent = new Intent(WelcomeA.this, LoginA.class);
				startActivity(intent);
				finish();
			} else
			{
				intent = new Intent(this, MainActivityA.class);
				new Thread(networkTask).start();
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	Userb user;
	MessageHelper message;
	Intent intent = null;
	// ��¼����

	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);

			if (message.getResult().equals(Define.S))
			{
				UserService.userType = message.getMessage();
				// ������ҳ��
				startActivity(intent);
				finish();
			} else
			{
				Toast.makeText(getApplicationContext(), "��¼ʧ�ܣ���������Ƿ����ӣ�",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(WelcomeA.this, LoginA.class);
				startActivity(intent);
				finish();
			}
			// Bundle data = msg.getData();
			// String val = data.getString("value");
			// Log.i("mylog", "������Ϊ-->" + val);
			// TODO
			// UI����ĸ��µ���ز���
		}
	};

	/**
	 * ���������ص����߳�
	 */
	Runnable networkTask = new Runnable()
	{

		@Override
		public void run()
		{
			SharedPreferences sp = getSharedPreferences("loading", 0);
			String userid = sp.getString("userid", "");
			String password = sp.getString("password", "");
			String imei = UserService.getIMEI(WelcomeA.this);
			String mac = UserService.getLocalMac(WelcomeA.this);
			Userb user = new Userb();
			user.setUserid(userid);
			user.setPassword(password);
			message = UserService.login(user, imei, mac);

			// ��¼�ɹ���¼�û���������
			SharedPreferences.Editor editor = sp.edit();
			editor.putString("userid", userid);
			editor.putString("password", password);
			editor.commit();

			// ��������� http request.����������ز���
			Message msg = new Message();
			Bundle data = new Bundle();
			// data.putString("value", "������");
			// data.put
			// msg.setData(data);
			handler.sendMessage(msg);
		}
	};
}
