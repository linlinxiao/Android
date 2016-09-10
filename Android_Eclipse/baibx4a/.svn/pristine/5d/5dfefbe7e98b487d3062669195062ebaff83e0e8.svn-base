package com.baoxiao.activity.invitation;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.baoxiao.R;
import com.baoxiao.service.userproduct.UserService;
import com.baoxiao.util.BitmapUtil;
import com.baoxiao.util.Define;
import com.baoxiao.view.RoundImageView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class InvitationWinePartyA extends Activity{
	protected static final int SUBMIT = 0;
	private String userid;
	private SharedPreferences invitationWineParty;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invitation_wine_party);
		userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
		invitationWineParty = getSharedPreferences("invitationWineParty", Activity.MODE_PRIVATE);
		String hidden = invitationWineParty.getString("hidden", "1");
		RadioButton rb_no_appear = (RadioButton)findViewById(R.id.rb_no_appear);
        if ("0".equals(hidden)) {
    		rb_no_appear.setChecked(true);
		}
		rb_no_appear.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					((TextView)buttonView).setTextColor(Color.parseColor("#2299ee"));
				}else{
					((TextView)buttonView).setTextColor(Color.parseColor("#888888"));
				}
			}
		});
		((RadioButton)findViewById(R.id.rb_appear)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					((TextView)buttonView).setTextColor(Color.parseColor("#2299ee"));
					submit("hidden", "1");
				}else{
					((TextView)buttonView).setTextColor(Color.parseColor("#888888"));
					submit("hidden", "0");
				}
			}
		});
		String sex = invitationWineParty.getString("sex", "男");
		RadioButton rb_female = (RadioButton)findViewById(R.id.rb_female);
        if ("女".equals(sex)) {
        	rb_female.setChecked(true);
		}
        ((RadioButton)findViewById(R.id.rb_male)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					((TextView)buttonView).setTextColor(Color.parseColor("#2299ee"));
					submit("sex","男");
				}else{
					submit("sex","女");
					((TextView)buttonView).setTextColor(Color.parseColor("#888888"));
				}
			}
		});
		rb_female.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					((TextView)buttonView).setTextColor(Color.parseColor("#2299ee"));
				}else{
					((TextView)buttonView).setTextColor(Color.parseColor("#888888"));
				}
			}
		});
		String sponsor = invitationWineParty.getString("sponsor", "2016");
		((TextView)findViewById(R.id.et_sponsor)).setText(sponsor);
		String winePartyName = invitationWineParty.getString("winePartyName", " 客户答谢会");
		((TextView)findViewById(R.id.et_winePartyName)).setText(winePartyName);
		String inviteeName = invitationWineParty.getString("inviteeName", "张伟");
		((TextView)findViewById(R.id.et_inviteeName)).setText(inviteeName);
		String meetingTime = invitationWineParty.getString("meetingTime", "2016年5月24号 16:30");
		((TextView)findViewById(R.id.et_meetingTime)).setText(meetingTime);
		String meetingAddressName = invitationWineParty.getString("meetingAddressName", "中国平安");
		((TextView)findViewById(R.id.et_meetingAddressName)).setText(meetingAddressName);
		String meetingAddress = invitationWineParty.getString("meetingAddress", "北京市东花市北里20号楼6单元501室");
		((TextView)findViewById(R.id.et_meetingAddress)).setText(meetingAddress);
		String content = invitationWineParty.getString("content", "一路走来，您的殷切关怀是我努力进取的动力；"
				+ "您的大力支持是我取得成功的基石；您的倾心帮助是我敢于不断超越的源泉！我的成长离不开您的一切一切，"
				+ "为了感恩回馈您的支持帮助与理解，本人将举办一场客户答谢会，现诚邀您参加，真诚期待您的准时光临！");
		((TextView)findViewById(R.id.et_content)).setText(content);
		String meetingProcess = invitationWineParty.getString("meetingProcess", "");
		((TextView)findViewById(R.id.et_meetingProcess)).setText(meetingProcess);
//		主办人             2016
//		酒会名称           客户答谢会
//		被邀请人姓名       张伟
//		性别               男
//		参会时间           2016年5月24号 16:30
//		参会地址名称       中国平安
//		参会地址           北京市东花市北里20号楼6单元501室
//
//		客户答谢酒会邀请函内容
//		一路走来，您的殷切关怀是我努力进取的动力；您的大力支持是我取得成功的基石；您的倾心帮助是我敢于不断超越的源泉！我的成长离不开您的一切一切，为了感恩回馈您的支持帮助与理解，本人将举办一场客户答谢会，现诚邀您参加，真诚期待您的准时光临！
//		this.sponsor = sponsor;
//		this.winePartyName = winePartyName;
//		this.inviteeName = inviteeName;
//		this.sex = sex;
//		this.meetingTime = meetingTime;
//		this.meetingAddressName = meetingAddressName;
//		this.meetingAddress = meetingAddress;
//		this.content = content;
//		this.meetingProcess = meetingProcess;

	}
	
	public void invitation_wine_back(View v){
		finish();
	}
	public void preView(View v) {
		startActivity(new Intent(this,InvitationWinePreViewA.class));
	}
//	public void hidden(View v){
//		showMyDialog("hidden",(TextView)v);
//	}
	public void sponsor(View v){
		showMyDialog("sponsor",(TextView)v);
	}
	public void winePartyName(View v){
		showMyDialog("winePartyName",(TextView)v);
	}
	public void inviteeName(View v){
		showMyDialog("inviteeName",(TextView)v);
	}
	public void sex(View v){
		showMyDialog("sex",(TextView)v);
	}
	public void meetingTime(View v){
		showMyDialog("meetingTime",(TextView)v);
	}
	public void meetingAddressName(View v){
		showMyDialog("meetingAddressName",(TextView)v);
	}
	public void meetingAddress(View v){
		showMyDialog("meetingAddress",(TextView)v);
	}
	public void content(View v){
		showMyDialog("content",(TextView)v);
	}
	public void meetingProcess(View v){
		showMyDialog("meetingProcess",(TextView)v);
	}
	public void iv_meetingProcess(View v){
		final EditText inputServer = new EditText(this);
		inputServer.setInputType(EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE);
		inputServer.setMinLines(2);
		inputServer.setSingleLine(false);
		final TextView et_meetingProcess = (TextView)findViewById(R.id.et_meetingProcess);
		final String value = et_meetingProcess.getText().toString();
		inputServer.setText(value);
		new AlertDialog.Builder(this)
		.setTitle("请输入")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setView(inputServer)
	    .setNegativeButton("取消", null)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String content = inputServer.getText().toString();
				if (value.equals(content)) {return;}
				submit("meetingProcess",content);
				et_meetingProcess.setText(content);
			}
		})
		.show();
	}
	
	private void showMyDialog(final String key,final TextView v) {
		final EditText inputServer = new EditText(this);
		inputServer.setInputType(EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE);
		inputServer.setMinLines(2);
		inputServer.setSingleLine(false);
		final String value = v.getText().toString();
		inputServer.setText(value);
		new AlertDialog.Builder(this)
		.setTitle("请输入")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setView(inputServer)
	    .setNegativeButton("取消", null)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String content = inputServer.getText().toString();
				if (value.equals(content)) {return;}
				submit(key,content);
				v.setText(content);
			}
		})
		.show();
	}
	protected void submit(final String name, final String content) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				JSONObject object = UserService.saveInvitationWinePartyData(userid,name,content);
				handler.sendMessage(handler.obtainMessage(SUBMIT, object));
			}
		}).start();
	}
	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case SUBMIT:
				JSONObject object = (JSONObject)msg.obj;
				if (object == null) {break;}
				JSONObject entity = null;
				try {
					JSONObject messageHelper = new JSONObject(object.getString("messageHelper"));
					String result = messageHelper.getString("result");
					if (Define.F.equals(result)) {break;}
					entity = new JSONObject(messageHelper.getString("entity"));
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				Iterator<?> iterator = entity.keys();
				while(iterator.hasNext()){
					String key = (String) iterator.next();
				    String value = null;
					try {
						value = entity.getString(key);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					invitationWineParty.edit().putString(key, value).commit();
				}
				break;

			default:
				break;
			}
			return false;
		}
	});
}
