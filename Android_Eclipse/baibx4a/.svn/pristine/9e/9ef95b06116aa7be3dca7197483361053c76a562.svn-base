package com.baoxiao;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baoxiao.model.Userb;
import com.baoxiao.service.userproduct.UserService;
import com.baoxiao.util.CustomProgressDialog;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;

/**
 * 注册页面
 *
 */
public class RegisterA extends Activity implements OnClickListener,
		OnCheckedChangeListener
{
	private ImageView registerBack;
	private TextView confirmRegister, registerGetYanZhengMa;
	private EditText number, yanzhengma, password, confirmPassword,et_real_name;
	private CheckBox readXieYi;
//	private RadioGroup type;
	private RadioButton salesman, customer;
	Userb user;
	MessageHelper message;
	MessageHelper loginmessage;
	ProgressDialog cpd;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		cpd = new CustomProgressDialog(this, "注册中...",R.anim.frame_anim);
		// 初始化控件
		registerBack = (ImageView) findViewById

		(R.id.registerBack);
		confirmRegister = (TextView) findViewById(R.id.confirmRegister);
		registerGetYanZhengMa = (TextView) findViewById(R.id.registerGetYanZhengMa);
		number = (EditText) findViewById(R.id.number);
		yanzhengma = (EditText) findViewById(R.id.yanzhengma);
		password = (EditText) findViewById(R.id.password);
		confirmPassword = (EditText) findViewById(R.id.confirmPassword);
		et_real_name = (EditText) findViewById(R.id.et_real_name);
		
//		type = (RadioGroup) findViewById(R.id.type);
//		salesman = (RadioButton) findViewById(R.id.salesman);
//		customer = (RadioButton) findViewById(R.id.customer);
		readXieYi = (CheckBox) findViewById(R.id.readXieYi);

		registerBack.setOnClickListener(this);
		confirmRegister.setOnClickListener(this);
		registerGetYanZhengMa.setOnClickListener(this);
		readXieYi.setOnCheckedChangeListener(this);
//		type.setOnCheckedChangeListener(seleteType);

	}

	private String num, yzm, pwd, cfp;
	private String ctr = "";
	private String slm = "";
	private Boolean flag = false;

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		// 返回按钮，返回登录界面
		case R.id.registerBack:
			finish();
			break;
		// 确认注册按钮，跳转到注册成功界面（即激活界面）
		case R.id.confirmRegister:
			num = number.getText().toString();
			yzm = yanzhengma.getText().toString();
			pwd = password.getText().toString();
			cfp = confirmPassword.getText().toString();
			if ("".equals(number.getText().toString())||
//				"".equals(yanzhengma.getText().toString()) ||
				"".equals(password.getText().toString()) ||
				"".equals(confirmPassword.getText().toString()))
			{
				Toast.makeText(getApplicationContext(), "注册页面信息未完成！",
						Toast.LENGTH_SHORT).show

				();
			} else if (!flag)
			{
				Toast.makeText(this, "请勾选《平安百宝箱服务协议》", Toast.LENGTH_SHORT).show();
			} else if(!pwd.equals(cfp)){
				Toast.makeText(this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
			}else
			{
				// 启动线程前必须初始化
				user = new Userb();
				user.setUserid(num);
				user.setPassword(pwd);
				user.setVerifycode(yzm);
				user.setName(et_real_name.getText().toString().trim());

//				if (!slm.equals(""))
//				{
					user.setType(Define.USER_TYPE);
//				} else if (!ctr.equals(""))
//				{
//					user.setType(Define.CUSTOM_TYPE);
//				}
				cpd.show();
				// 启动线程
				new Thread(networkTask).start();
			}
			break;
		// 获取验证码
		case R.id.registerGetYanZhengMa:
			String num = number.getText().toString();
			if (!isMobileNO(num)) {
				Toast.makeText(this, "请正确填写手机号码", Toast.LENGTH_SHORT).show();
				break;
			}
			startCount();
			break;
		}

	}
	private boolean isMobileNO(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches()+"---");
		return m.matches();
	}
	
	/**
	 * 选择业务类型
	 */
	public RadioGroup.OnCheckedChangeListener seleteType = new

	RadioGroup.OnCheckedChangeListener()
	{

		public void onCheckedChanged(RadioGroup arg0, int arg1)
		{
			if (salesman.getId() == arg1)
			{
				slm = salesman.getText().toString();
				ctr = "";
			} else if (customer.getId() == arg1)
			{
				ctr = customer.getText().toString();
				slm = "";
			}
		}

	};

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean

	isChecked)
	{
		if (readXieYi.isChecked())
		{
//			Toast.makeText(getApplicationContext(), "你添加了readXieyi!!!",
//					Toast.LENGTH_SHORT).show();
			flag = true;
		} else if (!readXieYi.isChecked())
		{
			flag = false;
		}
	}

	private int jishi;
	private Timer timer;

	// 验证码倒计时
	public void startCount()
	{
		jishi = 30;
		timer = new Timer();

		registerGetYanZhengMa.setEnabled(false);
		timer.schedule(new TimerTask()
		{

			@Override
			public void run()
			{
				if (jishi == 30)
				{
					UserService.yanZhengMa
					(number.getText().toString());
				}
				handler.sendEmptyMessage(jishi--);
			}
		}, 0, 1000);
	}

	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{

			if (msg.what > 0 && msg.what <= 30)
			{
				registerGetYanZhengMa.setText(msg.what +

				"秒获取");
			} else if (msg.what == 0)
			{
				registerGetYanZhengMa.setText("点击获取");
				registerGetYanZhengMa.setEnabled(true);
				timer.cancel();
			}

			if (msg.what == 100)
			{
				if (cpd != null) {cpd.dismiss();}
				UserService.userType =

				message.getMessage();
				// 登录成功记录用户名和密码
				SharedPreferences sp =

				getSharedPreferences("loading", Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = sp.edit();
				editor.putString("userid",user.getUserid());
				editor.putString("password",user.getPassword());
				editor.putString("type", user.getType());
				editor.putString("name", user.getName());
				editor.putBoolean("logstate_flag", true);
				editor.commit();
				cpd.dismiss();
				Toast.makeText(RegisterA.this, "注册成功!", Toast.LENGTH_SHORT)
						.show();
				Intent intentRegister = new Intent
				(RegisterA.this, JiHuoA.class);
				startActivity(intentRegister);
				finish();
			} else if (msg.what == 101)
			{
				if (cpd != null) {cpd.dismiss();}
				Toast.makeText(RegisterA.this, "注册成功，登录失败!", Toast.LENGTH_SHORT)
						.show();
			} else if (msg.what == 102)
			{
				if (cpd != null) {cpd.dismiss();}
				Toast.makeText(RegisterA.this,
				message.getMessage(), Toast.LENGTH_SHORT).show();
			}
			super.handleMessage(msg);
		}
	};

	/**
	 * 网络操作相关的子线程
	 */
	Runnable networkTask = new Runnable()
	{

		@Override
		public void run()
		{
			Message msg = new Message();
			message = UserService.register(user);
			String imei = UserService.getIMEI(RegisterA.this);
			String mac = UserService.getLocalMac(RegisterA.this);
			if (message.getResult().equals(Define.S))
			{
				loginmessage = UserService.login(user, imei, mac);
				user = UserService.getUserb(loginmessage);
				if (loginmessage.getResult().equals(Define.S))
				{
					msg.what = 100;
				} else
				{
					msg.what = 101;
				}
			} else
			{
				msg.what = 102;
			}
			handler.sendMessage(msg);
		}
	};

	protected void onDestroy()
	{
		if (timer != null)
		{
			timer.cancel();
		}
		super.onDestroy();
	};
}
