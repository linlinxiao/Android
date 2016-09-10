package com.baoxiao.activity.invitation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.baoxiao.R;
import com.baoxiao.activity.invitation.UploadUtil.OnUploadProcessListener;
import com.baoxiao.service.userproduct.UserService;
import com.baoxiao.util.BitmapUtil;
import com.baoxiao.util.Define;
import com.baoxiao.view.RoundImageView;
import com.baoxiao.view.ViewUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class InvitationFinanCialA extends Activity{
	protected static final int SUBMIT = 0;
	private static final int TO_SELECT_PHOTO = 0;
	private String userid;
	private ImageView currentImage;
	private String fileKey;
	private SharedPreferences invitationFinancial;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invitation_financial);
		userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
		invitationFinancial = getSharedPreferences("invitationFinancial", Activity.MODE_PRIVATE);
		String path = invitationFinancial.getString("iv_guestImage1", null);
		if (path != null) {
			Bitmap bmp = BitmapFactory.decodeFile(path,new Options());
			Bitmap t_bmp = BitmapUtil.scaleImage(bmp);
			((RoundImageView)findViewById(R.id.iv_guestImage1)).setImageBitmap(t_bmp);
		}
		String hidden = invitationFinancial.getString("hidden", "1");
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

		String meetingTime = invitationFinancial.getString("time", "2016年5月13号 16:59");
		((TextView)findViewById(R.id.et_meetingTime)).setText(meetingTime);
		String meetingAddressName = invitationFinancial.getString("addressName", "平安培训中心");
		((TextView)findViewById(R.id.et_meetingAddressName)).setText(meetingAddressName);
		String meetingAddress = invitationFinancial.getString("address", "广东省广州市越秀区中山六路101号");
		((TextView)findViewById(R.id.et_meetingAddress)).setText(meetingAddress);
		
		String inviteeName = invitationFinancial.getString("invitaName", "亲");
		((TextView)findViewById(R.id.et_invitaName)).setText(inviteeName);
		String content = invitationFinancial.getString("content", 
				"如果您羡慕地产经纪，能销售房产获得高额的佣金回报；"
				+ "如果您羡慕汽车销售员，能销售名车获得提成和奖金；"
				+ "如果您羡慕在银行工作，能为亲友获得最低、最便捷的贷款渠道；"
				+ "如果您羡慕证券经纪人，坐在看看电脑就能拿到客户交易股票时的提点；"
				+ "如果您羡慕别人能轻松预约全国三甲医院方便亲友或客户，很有面子；"
				+ "如果您羡慕别人能同时代理车险、寿险、信托、基金等N多业务，获得丰厚的收入；"
				+ "如果您看到国家颁布国十条,发现未来保险业拥有空前的发展潜力……这一切的一切机遇，都在一个地方集结："
				+ "中国平安！只需您来到平安成为综合金融职业经理人！");
		((TextView)findViewById(R.id.et_content)).setText(content);
		String guestName1 = invitationFinancial.getString("guestName1", "王长田");
		((TextView)findViewById(R.id.et_guestName1)).setText(guestName1);
		String guestOther1 = invitationFinancial.getString("guestOther1", 
				"此处输入对人物的介绍，简明扼要，内容不易过多。否则会使人反感。"
				+ "最多三行字！！！！！！！！！！！！！！");
		((TextView)findViewById(R.id.et_guestOther1)).setText(guestOther1);

	}

	public void invitation_wine_back(View v){
		finish();
	}
	public void preView(View v) {
		startActivity(new Intent(this,InvitationWinePreViewA.class)
				.putExtra("type", 3));
	}
	
	public void invitaName(View v){
		showMyDialog("invitaName",(TextView)v);
	}
	public void sex(View v){
		showMyDialog("sex",(TextView)v);
	}
	public void time(View v){
		showMyDialog("time",(TextView)v);
	}
	public void addressName(View v){
		showMyDialog("addressName",(TextView)v);
	}
	public void address(View v){
		showMyDialog("address",(TextView)v);
	}
	public void content(View v){
		showMyDialog("content",(TextView)v);
	}
	public void guestImage1(View v){
		currentImage = (ImageView)v;
		fileKey = "guestImage1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void guestName1(View v){
		showMyDialog("guestName1",(TextView)v);
	}
	public void guestOther1(View v){
		showMyDialog("guestOther1",(TextView)v);
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
				JSONObject object = UserService.saveInvitationFinancialData(userid,name,content);
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
				    getSharedPreferences("invitationFinancial", Activity.MODE_PRIVATE)
				    .edit().putString(key, value).commit();
				}
				break;

			default:
				break;
			}
			return false;
		}
	});
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (RESULT_OK != resultCode) {return;}
		if (data == null) {return;}
		//相册
		if(requestCode == TO_SELECT_PHOTO){
			String picPath = data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH);
			if (picPath == null) {return;}
			showBitmap(currentImage,picPath);
			toUploadFile(picPath,fileKey);
			invitationFinancial.edit().putString("iv_guestImage1", picPath).commit();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	private void showBitmap(ImageView imageView,String picPath){
		//得到imageView大小
		int dw = imageView.getWidth();
		int dh = imageView.getHeight();
		BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
		bmpFactoryOptions.inJustDecodeBounds = true;//计算出图像的大小，而不实际进行图像的解码：
		BitmapFactory.decodeFile(picPath);
		int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight / (float) dh);
		int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth/ (float) dw);
		if ((heightRatio > 1) && (widthRatio > 1)) {
			if (heightRatio > widthRatio) {
				bmpFactoryOptions.inSampleSize = heightRatio;
			} else {
				bmpFactoryOptions.inSampleSize = widthRatio;
			}
		}
		bmpFactoryOptions.inJustDecodeBounds = false;
		Bitmap bmp = BitmapFactory.decodeFile(picPath, bmpFactoryOptions);
		imageView.setAdjustViewBounds(true);
//		imageView.setBackground(new BitmapDrawable(getResources(),bmp));
		ViewUtil.setBackground(imageView, this, new BitmapDrawable(getResources(),bmp));
	}

	private void toUploadFile(String picPath,String fileKey){
		UploadUtil uploadUtil = UploadUtil.getInstance();;
		uploadUtil.setOnUploadProcessListener(new OnUploadProcessListener() {
			@Override
			public void onUploadProcess(int uploadSize) {
			}
			@Override
			public void onUploadDone(int responseCode, String message) {				
			}
			@Override
			public void initUpload(int fileSize) {
			}
		});//设置监听器监听上传状态
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userid);
		String filename = "123.jpg";
		if (picPath != null) {
			filename = picPath.substring(picPath.lastIndexOf("/") + 1,picPath.length());
		}
		params.put("filename", filename);
		String requestURL = Define.Server + "invitationFinancialSave.action";
		uploadUtil.uploadFile(picPath,fileKey,requestURL,params);
	}
}
