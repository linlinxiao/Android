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

		String meetingTime = invitationFinancial.getString("time", "2016��5��13�� 16:59");
		((TextView)findViewById(R.id.et_meetingTime)).setText(meetingTime);
		String meetingAddressName = invitationFinancial.getString("addressName", "ƽ����ѵ����");
		((TextView)findViewById(R.id.et_meetingAddressName)).setText(meetingAddressName);
		String meetingAddress = invitationFinancial.getString("address", "�㶫ʡ������Խ������ɽ��·101��");
		((TextView)findViewById(R.id.et_meetingAddress)).setText(meetingAddress);
		
		String inviteeName = invitationFinancial.getString("invitaName", "��");
		((TextView)findViewById(R.id.et_invitaName)).setText(inviteeName);
		String content = invitationFinancial.getString("content", 
				"�������Ľ�ز����ͣ������۷�����ø߶��Ӷ��ر���"
				+ "�������Ľ��������Ա�����������������ɺͽ���"
				+ "�������Ľ�����й�������Ϊ���ѻ����͡����ݵĴ���������"
				+ "�������Ľ֤ȯ�����ˣ����ڿ������Ծ����õ��ͻ����׹�Ʊʱ����㣻"
				+ "�������Ľ����������ԤԼȫ������ҽԺ�������ѻ�ͻ����������ӣ�"
				+ "�������Ľ������ͬʱ�����ա����ա����С������N��ҵ�񣬻�÷������룻"
				+ "������������Ұ䲼��ʮ��,����δ������ҵӵ�п�ǰ�ķ�չǱ��������һ�е�һ�л���������һ���ط����᣺"
				+ "�й�ƽ����ֻ��������ƽ����Ϊ�ۺϽ���ְҵ�����ˣ�");
		((TextView)findViewById(R.id.et_content)).setText(content);
		String guestName1 = invitationFinancial.getString("guestName1", "������");
		((TextView)findViewById(R.id.et_guestName1)).setText(guestName1);
		String guestOther1 = invitationFinancial.getString("guestOther1", 
				"�˴����������Ľ��ܣ�������Ҫ�����ݲ��׹��ࡣ�����ʹ�˷��С�"
				+ "��������֣���������������������������");
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
		.setTitle("������")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setView(inputServer)
	    .setNegativeButton("ȡ��", null)
		.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
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
		//���
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
		//�õ�imageView��С
		int dw = imageView.getWidth();
		int dh = imageView.getHeight();
		BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
		bmpFactoryOptions.inJustDecodeBounds = true;//�����ͼ��Ĵ�С������ʵ�ʽ���ͼ��Ľ��룺
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
		});//���ü����������ϴ�״̬
		
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
