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
import com.umeng.socialize.utils.Log;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ���뺯�༭
 *
 */
public class Invitation2A extends Activity {
	
    protected static final int GETINVITATION = 0;
	protected static final int SUBMIT = 1;
	private JSONObject object;
	private String userid,subject,address,time,
	invitaName,guestName1,guestOther1,sex;
	private String fileKey;
	private JSONObject entity;
	private ProgressDialog dialog;
	protected static final int TO_UPLOAD_FILE = 1;
	protected static final int UPLOAD_FILE_DONE = 2;
	public static final int TO_SELECT_PHOTO = 3;
	private ImageView currentImage;
	private int hidden = 1;
	private SharedPreferences invation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitation2);
        userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
//      getInvitation();
		invation = getSharedPreferences("invation", Activity.MODE_PRIVATE);
		String path = invation.getString("iv_guestImage1", null);
		if (path != null) {
			Bitmap bmp = BitmapFactory.decodeFile(path,new Options());
			Bitmap t_bmp = BitmapUtil.scaleImage(bmp);
			((RoundImageView)findViewById(R.id.iv_guestImage1)).setImageBitmap(t_bmp);
		}

		RadioButton rb_no_appear = (RadioButton)findViewById(R.id.rb_no_appear);
        hidden = invation.getInt("hidden", 1);
        if (hidden == 0) {
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
					hidden = 1;
					invation.edit().putInt("hidden", hidden).commit();
				}else{
					((TextView)buttonView).setTextColor(Color.parseColor("#888888"));
					hidden = 0;
					invation.edit().putInt("hidden", hidden).commit();
				}
			}
		});

		((RadioButton)findViewById(R.id.rb_male)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					((TextView)buttonView).setTextColor(Color.parseColor("#2299ee"));
					submit("sex","��");
				}else{
					submit("sex","Ů");
					((TextView)buttonView).setTextColor(Color.parseColor("#888888"));
				}
			}
		});
		((RadioButton)findViewById(R.id.rb_female)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					((TextView)buttonView).setTextColor(Color.parseColor("#2299ee"));
				}else{
					((TextView)buttonView).setTextColor(Color.parseColor("#888888"));
				}
			}
		});
		
		String inviteeName = invation.getString("invitaName", "������");
		((TextView)findViewById(R.id.et_invitaName)).setText(inviteeName);
		String time = invation.getString("time", "2016��5��13�� 16:59");
		((TextView)findViewById(R.id.et_time)).setText(time);
		String addressName = invation.getString("addressName", "ƽ����ѵ����");
		((TextView)findViewById(R.id.et_addressName)).setText(addressName);
		String address = invation.getString("address", "");
		((TextView)findViewById(R.id.et_address)).setText(address);
		String guestName1 = invation.getString("guestName1", "��ΰ");
		((TextView)findViewById(R.id.et_guestName1)).setText(guestName1);
		String guestOther1 = invation.getString("guestOther1", 
				"������ѧ��������ʿ\r\n�й���˶ʿ");
		((TextView)findViewById(R.id.et_guestOther1)).setText(guestOther1);
    }
	protected void submit(final String name, final String content) {
		if (entity == null) {
			entity = new JSONObject();
		}
		try {
			entity.put(name,content);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				JSONObject object = UserService.saveInvitationData(userid,name,content);
				handler.sendMessage(handler.obtainMessage(SUBMIT, object));
//				{"messageHelper":{"message":"","result":"S","list":null,"entity":null}}
			}
		}).start();
	}
	private void getInvitation() {
		if (dialog == null) {
			dialog = new ProgressDialog(this);
		}dialog.show();
		new Thread(new Runnable() {
			@Override
			public void run() {
				object = UserService.getInvitationData(userid);
				handler.sendMessage(handler.obtainMessage(GETINVITATION, object));
			}
		}).start();
	}
	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			if (dialog != null) {
				dialog.dismiss();
			}
			switch (msg.what) {
			case GETINVITATION:
				JSONObject object = (JSONObject)msg.obj;
				if (object == null) {break;}
				Log.i("object", object.toString());
//				{"messageHelper":{"message":"","result":"S","list":null,"entity":
//				{"subject":"ƽ���ŲŴ�ҵ˵����",
//				"userid":"123",
//				"time":"2016 \/ 5\/ 8 \/  ����6��30",
//				"introduction1":"������һȺ���Բ�ͬ��ҵ�ľ�Ӣ\r\n�߱�רҵ������\r\n�ܹ����õĽ���\r\n���Ų�iͬ�Ĺ�����������ҵ����",
//				"guestOther2":"������ѧ��������ʿ\r\n�й���˶ʿ",
//				"guestOther1":"������ѧ��������ʿ\r\n�й���˶ʿ",
//				"myName":"ҵ��Ա\r\n����",
//				"introduction2":"�й�ƽ������Ϊ���������ʵ��˲ţ�¡���Ƴ����Ųżƻ������ṩ�߶�ĵ�н��ר�ŵ�ѵ������˾����ƽ����ѵ���ľ��С��Ųš�ר����ҵ˵���ᣬ��ʱ��ӭ��ݰ���ֳ������Ĵ�ҵ���룬��������δ��",
//				"introduction3":"�й�ƽ�����й���һ�ҹɷ��Ʊ�����ҵ�������ѷ�չ��Ϊ�ڱ��ա����С�Ͷ��������Ӫҵ��Ϊһ�塢���Ľ����뻥��������ҵ���з�չ�ĸ��˽������������֮һ����ֹ2015�꼯�����ʲ���4.77����Ԫ�����������Ƹ�����־��ȫ��������ҵ500ǿ�����е�96λ���ǹ�����ҵ��������һ��",
//				"backMusic":null,
//				"process5":"13:00--14:00 �α�ǩ��",
//				"process6":"13:00--14:00 �α�ǩ��",
//				"myPhone":"ҵ��Ա��ϵ�绰\r\n13800000000",
//				"process3":"13:00--14:00 �α�ǩ��",
//				"process4":"13:00--14:00 �α�ǩ��",
//				"title1":"ƽ���Ų�",
//				"process1":"13:00--14:00 �α�ǩ��",
//				"process2":"13:00--14:00 �α�ǩ��",
//				"title2":"�Ų�һ����",
//				"guestName2":"����",
//				"guestName1":"����",
//				"companyName":"�й�ƽ������",
//				"process7":null,
//				"image0":"\/images\/invitation\/youcai.png",
//				"invitaName":"�𾴵�����\/Ůʿ",
//				"image2":"\/images\/invitation\/image2.png",
//				"image1":"\/images\/invitation\/image1.png",
//				"address":"ƽ����ѧ����",
//				"company":"�й�ƽ�����ٱ��չɷ����޹�˾",
//				"guestImage1":"\/images\/invitation\/guestImage1.png",
//				"guestImage2":"\/images\/invitation\/guestImage2.png",
//				"image4":null,"image3":"\/images\/invitation\/staticmap.png"}}}
	
				try {
					JSONObject messageHelper = new JSONObject(object.getString("messageHelper"));
					entity = new JSONObject(messageHelper.getString("entity"));
					subject = entity.getString("subject");
					((TextView)findViewById(R.id.et_subject)).setText(subject);
					address = entity.getString("address");
					((TextView)findViewById(R.id.et_address)).setText(address);
					time = entity.getString("time");
					((TextView)findViewById(R.id.et_time)).setText(time);
					invitaName = entity.getString("invitaName");
					((TextView)findViewById(R.id.et_invitaName)).setText(invitaName);
					guestName1 = entity.getString("guestName1");
					((TextView)findViewById(R.id.et_guestName1)).setText(guestName1);
					guestOther1 = entity.getString("guestOther1");
					((TextView)findViewById(R.id.et_guestOther1)).setText(guestOther1);
//					image4 = entity.getString("image4");
//					((TextView)findViewById(R.id.et_subject)).setText(subject);
//					myName = entity.getString("myName");
//					((TextView)findViewById(R.id.et_myname)).setText(myName);
//					myPhone = entity.getString("myPhone");					
//					((TextView)findViewById(R.id.et_myphone)).setText(myPhone);
					sex = entity.getString("sex");
					if ("��".equals(sex)) {
						((RadioButton)findViewById(R.id.rb_male)).setChecked(true);
					}else{
						((RadioButton)findViewById(R.id.rb_female)).setChecked(true);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
			case SUBMIT:
//				{"messageHelper":{"message":"","result":"S","list":null,"entity":null}}
				object = (JSONObject)msg.obj;
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
				if (entity == null) {break;}
				Iterator<?> iterator = entity.keys();
				while(iterator.hasNext()){
					String key = (String) iterator.next();
				    String value = null;
					try {
						value = entity.getString(key);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					invation.edit().putString(key, value).commit();
				}
				break;
			default:
				break;
			}
			return false;
		}
	});
//	private String getData(JSONObject entity) throws Exception{
//		String result = "";
//		result += "userid=" + entity.getString("userid") + "&";
//		result += "company=" + URLEncoder.encode(entity.getString("company"),"UTF-8") + "&";
//		result += "subject=" + URLEncoder.encode(entity.getString("subject"),"UTF-8") + "&";
//		result += "backMusic=" + URLEncoder.encode(entity.getString("backMusic"),"UTF-8") + "&";
//		result += "address=" + URLEncoder.encode(entity.getString("address"),"UTF-8") + "&";
//		
//		result += "time=" + URLEncoder.encode(entity.getString("time"),"UTF-8") + "&";
//		result += "image0=" + URLEncoder.encode(entity.getString("image0"),"UTF-8") + "&";
//		result += "title1=" + URLEncoder.encode(entity.getString("title1"),"UTF-8") + "&";
//		result += "introduction1=" + URLEncoder.encode(entity.getString("introduction1"),"UTF-8") + "&";
//		result += "image1=" + URLEncoder.encode(entity.getString("image1"),"UTF-8") + "&";
//		
//		result += "title2=" + URLEncoder.encode(entity.getString("title2"),"UTF-8") + "&";
//		result += "invitaName=" + URLEncoder.encode(entity.getString("invitaName"),"UTF-8") + "&";
//		result += "introduction2=" + URLEncoder.encode(entity.getString("introduction2"),"UTF-8") + "&";
//		result += "image2=" + URLEncoder.encode(entity.getString("image2"),"UTF-8") + "&";
//		result += "companyName=" + URLEncoder.encode(entity.getString("companyName"),"UTF-8") + "&";
//		
//		result += "introduction3=" + URLEncoder.encode(entity.getString("introduction3"),"UTF-8") + "&";
//		result += "image3=" + URLEncoder.encode(entity.getString("image3"),"UTF-8") + "&";
//		result += "guestImage1=" + URLEncoder.encode(entity.getString("guestImage1"),"UTF-8") + "&";
//		result += "guestName1=" + URLEncoder.encode(entity.getString("guestName1"),"UTF-8") + "&";
//		result += "guestOther1=" + URLEncoder.encode(entity.getString("guestOther1"),"UTF-8") + "&";
//		
//		result += "guestImage2=" + URLEncoder.encode(entity.getString("guestImage2"),"UTF-8") + "&";
//		result += "guestName2=" + URLEncoder.encode(entity.getString("guestName2"),"UTF-8") + "&";
//		result += "guestOther2=" + URLEncoder.encode(entity.getString("guestOther2"),"UTF-8") + "&";
//		result += "process1=" + URLEncoder.encode(entity.getString("process1"),"UTF-8") + "&";
//		result += "process2=" + URLEncoder.encode(entity.getString("process2"),"UTF-8") + "&";
//		
//		result += "process3=" + URLEncoder.encode(entity.getString("process3"),"UTF-8") + "&";
//		result += "process4=" + URLEncoder.encode(entity.getString("process4"),"UTF-8") + "&";
//		result += "process5=" + URLEncoder.encode(entity.getString("process5"),"UTF-8") + "&";
//		result += "process6=" + URLEncoder.encode(entity.getString("process6"),"UTF-8") + "&";
//		result += "process7=" + URLEncoder.encode(entity.getString("process7"),"UTF-8") + "&";
//		
//		result += "image4=" + URLEncoder.encode(entity.getString("image4"),"UTF-8") + "&";
//		result += "myName=" + URLEncoder.encode(entity.getString("myName"),"UTF-8") + "&";
//		result += "myPhone=" + URLEncoder.encode(entity.getString("myPhone"),"UTF-8");
//
//        return result;
//	}
	
//	private String getData2(JSONObject entity) throws Exception{
//		String result = "";
//		result += "userid=" + userid + "&";
//		result += "company=" + company + "&";
//		result += "subject=" + subject + "&";
//		result += "backMusic=" + "" + "&";
//		result += "address=" + address + "&";
//		
//		result += "time=" + time + "&";
//		result += "image0=" + image0 + "&";
//		result += "title1=" + title1 + "&";
//		result += "introduction1=" + introduction1 + "&";
//		result += "image1=" + image1 + "&";
//		
//		result += "title2=" + title2 + "&";
//		result += "invitaName=" + invitaName + "&";
//		result += "introduction2=" + introduction2 + "&";
//		result += "image2=" + image2 + "&";
//		result += "companyName=" + companyName + "&";
//		
//		result += "introduction3=" + introduction3 + "&";
//		result += "image3=" + image3 + "&";
//		result += "guestImage1=" + guestImage1 + "&";
//		result += "guestName1=" + guestName1 + "&";
//		result += "guestOther1=" + guestOther1 + "&";
//		
//		result += "guestImage2=" + guestImage2 + "&";
//		result += "guestName2=" + guestName2 + "&";
//		result += "guestOther2=" + guestOther2 + "&";
//		result += "process1=" + process1 + "&";
//		result += "process2=" + process2 + "&";
//		
//		result += "process3=" + process3 + "&";
//		result += "process4=" + process4 + "&";
//		result += "process5=" + process5 + "&";
//		result += "process6=" + process6 + "&";
//		result += "process7=" + process7 + "&";
//		
//		result += "image4=" + image4 + "&";
//		result += "myName=" + myName + "&";
//		result += "myPhone=" + myPhone;
//
//        return result;
//	}

	public void subject(View v){
		showMyDialog("subject",(TextView)v);
	}
	public void time(View v){
		showMyDialog("time",(TextView)v);
	}
	public void address(View v){
		showMyDialog("address",(TextView)v);
	}
	public void company(View v){
		showMyDialog("company",(TextView)v);
	}
	public void title1(View v){
		showMyDialog("title1",(TextView)v);
	}
	public void introduction1(View v){
		showMyDialog("introduction1",(TextView)v);
	}
	public void title2(View v){
		showMyDialog("title2",(TextView)v);
	}
	public void invitaName(View v){
		showMyDialog("invitaName",(TextView)v);
	}
	public void sex(View v){
		showMyDialog("sex",(TextView)v);
	}
	public void addressName(View v){
		showMyDialog("addressName",(TextView)v);
	}
	public void introduction2(View v){
		showMyDialog("introduction2",(TextView)v);
	}
	public void companyName(View v){
		showMyDialog("companyName",(TextView)v);
	}
	public void guestName1(View v){
		showMyDialog("guestName1",(TextView)v);
	}
	public void guestOther1(View v){
		showMyDialog("guestOther1",(TextView)v);
	}
	public void myname(View v){
//		showMyDialog("myname",myName,(TextView)v);
	}
	public void myphone(View v){
//		showMyDialog("myphone",myPhone,(TextView)v);
	}
	public void preView(View v){
//		String data = null;
//		try {
//			data = getData2(entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		Log.i("tag", data.toString());
		startActivity(new Intent(Invitation2A.this,PreviewA.class)
				.putExtra("userid", userid)
				.putExtra("hidden", hidden)
				);
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
				if (entity == null) {
					entity = new JSONObject();
				}
				try {
					entity.put(key,content);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		})
		.show();
	}
//	public void image0(View v){
//		position = 0;
//		fileKey = "image0";
//		Intent intent = new Intent(this,SelectPicActivity.class);
//		startActivityForResult(intent, TO_SELECT_PHOTO);
////		new AlertDialog.Builder(Invitation2A2.this)
////		.setTitle("��ѡ��")
////		.setPositiveButton("���", new DialogInterface.OnClickListener() {
////			@Override
////			public void onClick(DialogInterface dialog, int which) {
////				Intent intent = new Intent();
////				intent.setAction(Intent.ACTION_PICK);
////				intent.setType("image/*");
////				startActivityForResult(intent, 0);//�������
////			}
////		})
////		.setNegativeButton("����", new DialogInterface.OnClickListener() {
////			@Override
////			public void onClick(DialogInterface dialog, int which) {
////				File file = new File(imageFilePath + "image0" + userid +".jpg");
////				Uri uri = Uri.fromFile(file);
////				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
////				startActivityForResult(intent, 0);
////			}
////		})
////		.show();
//	}
	public void guestImage1(View v){
		currentImage = (ImageView)v;
		fileKey = "guestImage1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
//	public void image3(View v){
//		position = 2;
//		fileKey = "image3";
//		Intent intent = new Intent(this,SelectPicActivity.class);
//		startActivityForResult(intent, TO_SELECT_PHOTO);
//	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (RESULT_OK != resultCode) {return;}
		if (data == null) {return;}
//		if(requestCode < 100){//����
//			Bitmap bmp = showBitmap(Invitation2A2.this, imageName[requestCode], requestCode);
//			try {
//				saveBitmap(imageZoom(bmp), imageName[requestCode]);
////				path_to[requestCode] = imageFilePath + name[requestCode] + ".jpg";
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			bmp = null;
//			return;
//		}
//		//���
		if(requestCode == TO_SELECT_PHOTO){
			String picPath = data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH);
			if (picPath == null) {return;}
			showBitmap(currentImage,picPath);
			toUploadFile(picPath,fileKey);
			invation.edit().putString("iv_guestImage1", picPath).commit();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	private void showBitmap(ImageView imageView,String picPath){
		Bitmap bmp = BitmapFactory.decodeFile(picPath,new Options());
		Bitmap t_bmp = BitmapUtil.scaleImage(bmp);
		((RoundImageView)findViewById(R.id.iv_guestImage1)).setImageBitmap(t_bmp);

//		//�õ�imageView��С
//		int dw = imageView.getWidth();
//		int dh = imageView.getHeight();
//		BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
//		bmpFactoryOptions.inJustDecodeBounds = true;//�����ͼ��Ĵ�С������ʵ�ʽ���ͼ��Ľ��룺
//		BitmapFactory.decodeFile(picPath);
//		int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight / (float) dh);
//		int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth/ (float) dw);
//		if ((heightRatio > 1) && (widthRatio > 1)) {
//			if (heightRatio > widthRatio) {
//				bmpFactoryOptions.inSampleSize = heightRatio;
//			} else {
//				bmpFactoryOptions.inSampleSize = widthRatio;
//			}
//		}
//		bmpFactoryOptions.inJustDecodeBounds = false;
//		Bitmap bmp = BitmapFactory.decodeFile(picPath, bmpFactoryOptions);
//		imageView.setAdjustViewBounds(true);
//		imageView.setBackground(new BitmapDrawable(getResources(),bmp));
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
		String requestURL = Define.Server + "invitationSave.action";
		uploadUtil.uploadFile(picPath,fileKey,requestURL,params);
	}
	public void invitation_back(View v){
		finish();
	}
}