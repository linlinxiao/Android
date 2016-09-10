package com.baoxiao.activity.homepage;

import com.baoxiao.BaseActivity;
import com.baoxiao.LoginA;
import com.baoxiao.R;
import com.baoxiao.activity.pay.ProductPay;
import com.baoxiao.activity.weihu.AboutA;
import com.baoxiao.activity.weihu.DataBackUpA;
import com.baoxiao.activity.weihu.ModifyPwdA;
import com.baoxiao.activity.weihu.SystemUpdateA;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * '维护'界面布局
 *
 */
public class WeiHuA extends BaseActivity implements OnClickListener{
	private LinearLayout update,backUp,pay,about, alter;
	private TextView safeExit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weihu);
		
		initView();
	}

	private void initView() {
		update = (LinearLayout) findViewById(R.id.update);
		backUp = (LinearLayout) findViewById(R.id.backUp);
		pay = (LinearLayout) findViewById(R.id.pay);
		about = (LinearLayout) findViewById(R.id.about);
		safeExit = (TextView) findViewById(R.id.safeExit);
		
		alter = (LinearLayout) findViewById(R.id.alter);
	 
		update.setOnClickListener(this);
		backUp.setOnClickListener(this);
		pay.setOnClickListener(this);
		about.setOnClickListener(this);
		safeExit.setOnClickListener(this);
		//修改密码
		alter.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.update:
			Intent intent = new Intent(this,SystemUpdateA.class);
			startActivity(intent);
			break;
		case R.id.backUp:
			Intent intent2 = new Intent(this,DataBackUpA.class);
			startActivity(intent2);
			break;
		case R.id.pay:
			Intent intent3 = new Intent(this,ProductPay.class);
			startActivity(intent3);
			break;
		case R.id.about:
			Intent intent4 = new Intent(this,AboutA.class);
			startActivity(intent4);
			break;

//			/**
//			 * 退出时，清除在SharedPreference中保存的账户和密码以及是否是第一次登录的数据
//			 */
		case R.id.safeExit:
			Intent intent5 = new Intent(this,LoginA.class);
			intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			SharedPreferences sp = getSharedPreferences("loading", MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			editor.putString("userid", "");
			editor.putString("insurerPerson", "");
			editor.putString("password", "");
			editor.putBoolean("logstate_flag", false);
			editor.commit();
			startActivity(intent5);
			finish();
			break;
		case R.id.alter:
	        Intent intent6=new Intent(this,ModifyPwdA.class);
			startActivity(intent6);
			break;

		default:
			break;
		}
	}

}
