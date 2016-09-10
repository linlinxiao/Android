package com.baoxiao;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.baoxiao.model.Userb;
import com.baoxiao.service.userproduct.UserService;
import com.baoxiao.util.CustomProgressDialog;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;

/**
 * ��¼ҳ��
 *
 */
public class LoginA extends Activity implements OnClickListener
{
	private EditText loginUserName, loginPassword;
	private TextView login, loginForgetPassword, register;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);

		loginUserName = (EditText) findViewById(R.id.loginUserName);
		loginPassword = (EditText) findViewById(R.id.loginPassword);
		login = (TextView) findViewById(R.id.login);
		loginForgetPassword = (TextView) findViewById(R.id.loginForgetPassword);
		register = (TextView) findViewById(R.id.register);
		// ��Ӱ�ť�����¼�
		login.setOnClickListener(this);
		loginForgetPassword.setOnClickListener(this);
		register.setOnClickListener(this);

	}

	// ��дonClick�¼�
	@Override
	public void onClick(View v){
		switch (v.getId()){
		case R.id.login:

			if (("".equals(loginUserName.getText().toString().trim()) || null == loginUserName
					.getText().toString().trim())
					|| ("".equals(loginPassword.getText().toString().trim()) || null == loginPassword
							.getText().toString().trim()))
			{// ֻҪ�û�����������һ��Ϊ��
				Toast.makeText(getApplicationContext(), "�˺Ż����벻��Ϊ�գ��������˺Ż�����",
						Toast.LENGTH_SHORT).show();
			} else
			{
				intent = new Intent(this, MainActivityA.class);
				cpd = new CustomProgressDialog(this, "���ڵ�¼...",R.anim.frame_anim);
				cpd.show();
				new Thread(networkTask).start();

				// this.startService(new Intent(this,NetService.class));
				// Userb user=new Userb();
				// user.setUserid(loginUserName.getText().toString().trim());
				// user.setPassword(loginPassword.getText().toString().trim());
				// MessageHelper message=UserService.login(user);

				// if(message.getResult().equals(Define.S)){
				// UserService.userType=message.getMessage();
				// //������ҳ��
				// Intent intent = new Intent(this,MainActivity.class);
				// startActivity(intent);
				// finish();
				// }else{
				// Toast.makeText(getApplicationContext(),message.getMessage() ,
				// Toast.LENGTH_SHORT).show();
				// }

			}

			break;
		case R.id.loginForgetPassword:
			if(("".equals(loginUserName.getText().toString().trim()) || null == loginUserName
					.getText().toString().trim())){
				Toast.makeText(getApplicationContext(), "�˻��Ų���Ϊ�գ��������˺�",
						Toast.LENGTH_SHORT).show();
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("���ȷ�����������룬�������Ժ��Ͷ��ŵ����ֻ����Ƿ�ȷ����");
				builder.setTitle("��ʾ");
				builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface arg0, int arg1)
					{
						new Thread(runnable).start();
					}
				});
				builder.setNegativeButton("ȡ��", null);
				builder.create().show();
			}
			break;
		case R.id.register:
			// Toast.makeText(getApplicationContext(),"����ע��ҳ�棡" ,
			// Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, RegisterA.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	Userb user;
	MessageHelper message;
	Intent intent = null;
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			switch (msg.what)
			{
			case 1:
				if (message.getResult().equals(Define.S))
				{
					cpd = new CustomProgressDialog(LoginA.this, "��¼�ɹ���",R.anim.frame_anim);
					cpd.show();
					UserService.userType = message.getMessage();
					// ��¼�ɹ���¼�û���������
					SharedPreferences sp = getSharedPreferences("loading",
							Activity.MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("userid", user.getUserid());
					editor.putString("password", user.getPassword());
					editor.putString("type", user.getType());
					editor.putString("name", user.getName());
					editor.putString("phone", user.getPhone());
					editor.putBoolean("logstate_flag", true);
					editor.commit();
					cpd.dismiss();
					// ������ҳ��
					startActivity(intent);
					finish();
				} else
				{
					cpd.dismiss();
					String m = message.getMessage().toString();
					if(!m.equals("")){
						Toast.makeText(LoginA.this, m, Toast.LENGTH_SHORT)
						.show();
					}else{
						Toast.makeText(LoginA.this, "��¼ʧ��", Toast.LENGTH_SHORT)
						.show();
					}

				}
				break;

			case 2:
				if (msg.obj.equals(Define.S))
				{
					Toast.makeText(LoginA.this, "����������ɹ���", Toast.LENGTH_SHORT)
							.show();
				} else
				{
					Toast.makeText(LoginA.this, "����������ʧ�ܣ�", Toast.LENGTH_SHORT)
							.show();
				}
				break;
			}
			super.handleMessage(msg);
		}
	};

	private ProgressDialog cpd;
	/**
	 * ���������ص����߳�
	 */
	Runnable networkTask = new Runnable(){
		@Override
		public void run(){
			user = new Userb();
			user.setUserid(loginUserName.getText().toString().trim());
			user.setPassword(loginPassword.getText().toString().trim());
			String imei = UserService.getIMEI(LoginA.this);
			String mac = UserService.getLocalMac(LoginA.this);
			message = UserService.login(user, imei, mac);
			user = UserService.getUserb(message);
			// ��������� http request.����������ز���
			Message msg = new Message();
			// Bundle data = new Bundle();
			// data.putString("value", "������");
			// data.put
			// msg.setData(data);
			msg.what = 1;
			handler.sendMessage(msg);
		}
	};

	Runnable runnable = new Runnable()
	{

		@Override
		public void run()
		{
			MessageHelper messageHelper = UserService.seekPwd(loginUserName
					.getText().toString());
			Message msg = new Message();
			msg.obj = messageHelper.getResult();
			msg.what = 2;
			handler.sendMessage(msg);
		}
	};
}
