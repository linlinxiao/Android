package com.baoxiao.activity.weihu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baoxiao.R;
import com.baoxiao.service.userproduct.UserService;
import com.baoxiao.util.CustomProgressDialog;
import com.baoxiao.util.Define;
import com.baoxiao.util.GetSharedPreferencesValue;
import com.baoxiao.util.MessageHelper;

/**
 * 修改密码
 */
public class ModifyPwdA extends Activity implements OnClickListener{

	private ImageView weiHuBack;
	private EditText etOldPwd,etNewPwd,etNew2Pwd;
	private Button btnModifyPwd;
	private String strOldPWd,strNewPwd,strNew2PWd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	    setContentView(R.layout.weihu_modify_pwd);
	    
	    initWebView();
	    
	    weiHuBack = (ImageView) findViewById(R.id.weiHuBack);

	    weiHuBack.setOnClickListener(this);
	}
	
	private void initWebView() {
		etOldPwd = (EditText)findViewById(R.id.modify_pwd_opwd);
		etNewPwd = (EditText)findViewById(R.id.modify_pwd_npwd);
		etNew2Pwd = (EditText)findViewById(R.id.modify_pwd_n2pwd);
		btnModifyPwd = (Button)findViewById(R.id.modify_pwd_btn);
		
		btnModifyPwd.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.weiHuBack:
			finish();
			break;
		case R.id.modify_pwd_btn:
			//修改密码相关
			strOldPWd = etOldPwd.getText().toString().trim();
			strNewPwd = etNewPwd.getText().toString().trim();
			strNew2PWd = etNew2Pwd.getText().toString().trim();
			
			if(strOldPWd.equals("")||strOldPWd.isEmpty()||strOldPWd == null){
				Toast.makeText(this, "密码不能为空，请输入原密码", Toast.LENGTH_SHORT).show();
			}else{
				if((strNewPwd.equals("")||strNewPwd.isEmpty()||strNewPwd==null)||(strNew2PWd.equals("")||strNew2PWd.isEmpty()||strNew2PWd==null)){
					Toast.makeText(this, "密码不能为空，请输入新密码", Toast.LENGTH_SHORT).show();
				}else if(!strNewPwd.equals(strNew2PWd)){
					Toast.makeText(this, "两次新密码输入不一致", Toast.LENGTH_SHORT).show();
				}else{//修改密码
					new Thread(networkTask).start();
				}
			}
			break;
		default:
			break;
		}
	}
	
	MessageHelper message;
	/**
	 * 网络操作相关的子线程
	 */
	Runnable networkTask = new Runnable()
	{

		@Override
		public void run()
		{
			message = UserService.modifyPassword(GetSharedPreferencesValue.getUserId(ModifyPwdA.this), strOldPWd, strNewPwd);
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
	
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
				if (message.getResult().equals(Define.S)){
					Toast.makeText(ModifyPwdA.this, "密码修改成功", Toast.LENGTH_SHORT).show();
					UserService.userType = message.getMessage();
					// 修改成功记录用户名和密码
					SharedPreferences sp = getSharedPreferences("loading",
							Activity.MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("password", strNewPwd);
					editor.commit();
					finish();
				} else{
					Toast.makeText(ModifyPwdA.this, "密码修改失败", Toast.LENGTH_SHORT).show();
				}
				super.handleMessage(msg);
		}
	};

	
}
