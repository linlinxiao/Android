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
 * 登录页面
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
		// 添加按钮监听事件
		login.setOnClickListener(this);
		loginForgetPassword.setOnClickListener(this);
		register.setOnClickListener(this);

	}

	// 覆写onClick事件
	@Override
	public void onClick(View v){
		switch (v.getId()){
		case R.id.login:

			if (("".equals(loginUserName.getText().toString().trim()) || null == loginUserName
					.getText().toString().trim())
					|| ("".equals(loginPassword.getText().toString().trim()) || null == loginPassword
							.getText().toString().trim()))
			{// 只要用户名和密码有一个为空
				Toast.makeText(getApplicationContext(), "账号或密码不能为空，请输入账号或密码",
						Toast.LENGTH_SHORT).show();
			} else
			{
				intent = new Intent(this, MainActivityA.class);
				cpd = new CustomProgressDialog(this, "正在登录...",R.anim.frame_anim);
				cpd.show();
				new Thread(networkTask).start();

				// this.startService(new Intent(this,NetService.class));
				// Userb user=new Userb();
				// user.setUserid(loginUserName.getText().toString().trim());
				// user.setPassword(loginPassword.getText().toString().trim());
				// MessageHelper message=UserService.login(user);

				// if(message.getResult().equals(Define.S)){
				// UserService.userType=message.getMessage();
				// //进入主页面
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
				Toast.makeText(getApplicationContext(), "账户号不能为空，请输入账号",
						Toast.LENGTH_SHORT).show();
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("点击确定将重置密码，新密码稍后发送短信到您手机，是否确定？");
				builder.setTitle("提示");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface arg0, int arg1)
					{
						new Thread(runnable).start();
					}
				});
				builder.setNegativeButton("取消", null);
				builder.create().show();
			}
			break;
		case R.id.register:
			// Toast.makeText(getApplicationContext(),"进入注册页面！" ,
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
					cpd = new CustomProgressDialog(LoginA.this, "登录成功！",R.anim.frame_anim);
					cpd.show();
					UserService.userType = message.getMessage();
					// 登录成功记录用户名和密码
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
					// 进入主页面
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
						Toast.makeText(LoginA.this, "登录失败", Toast.LENGTH_SHORT)
						.show();
					}

				}
				break;

			case 2:
				if (msg.obj.equals(Define.S))
				{
					Toast.makeText(LoginA.this, "新密码申请成功！", Toast.LENGTH_SHORT)
							.show();
				} else
				{
					Toast.makeText(LoginA.this, "新密码申请失败！", Toast.LENGTH_SHORT)
							.show();
				}
				break;
			}
			super.handleMessage(msg);
		}
	};

	private ProgressDialog cpd;
	/**
	 * 网络操作相关的子线程
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
			// 在这里进行 http request.网络请求相关操作
			Message msg = new Message();
			// Bundle data = new Bundle();
			// data.putString("value", "请求结果");
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
