package com.baoxiao.activity.userproduct;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import com.baoxiao.R;
import com.baoxiao.util.NetworkTool;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class EditUserInfoA extends Activity implements OnClickListener{

	private WebView webView;
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.us_edit_userinfo);
		
		webView=(WebView)this.findViewById(R.id.edituser);
		TextView renZheng=(TextView)this.findViewById(R.id.renZheng);
		renZheng.setOnClickListener(this);
//		CharSequence[] items = {"���", "���"};  
//	    new AlertDialog.Builder(this)
//		    .setTitle("ѡ��ͼƬ��Դ")
//		    .setItems(items, new OnClickListener() {
//				public void onClick(DialogInterface dialog, int which) {
//					if( which == SELECT_PICTURE ){
//						Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//						intent.addCategory(Intent.CATEGORY_OPENABLE);
//						intent.setType("image/*");
//						startActivityForResult(Intent.createChooser(intent, "ѡ��ͼƬ"), SELECT_PICTURE); 
//			        }else{
//			        	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
//			        	startActivityForResult(intent, SELECT_CAMER);  
//			        }
//				}
//			})
//			.create().show(); 
		
//		webView.loadUrl("http://192.168.1.201:8080/baibx/rzpage.action?userid=a123");
//		webView.setWebChromeClient(client)
//		webView.setWebViewClient(client);
		
	}

	/**
	 * ͨ��Base32��Bitmapת����Base64�ַ���
	 * @param bit
	 * @return
	 */
	public String Bitmap2StrByBase64(Bitmap bit){
	   ByteArrayOutputStream bos=new ByteArrayOutputStream();
	   bit.compress(CompressFormat.JPEG, 40, bos);//����100��ʾ��ѹ��
	   byte[] bytes=bos.toByteArray();
	   return Base64.encodeToString(bytes, Base64.DEFAULT);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.renZheng:
			new Thread(networkTask).start();
			break;
			
			
		default:
			break;
		
		}
		
	}



	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
//			  if(message.getResult().equals(Define.S)){
//					UserService.userType=message.getMessage();
//				    //������ҳ��
//					startActivity(intent);
//					finish();
//					}else{
//						Toast.makeText(getApplicationContext(),message.getMessage() , Toast.LENGTH_SHORT).show();
//					}
//			Bundle data = msg.getData();
//			String val = data.getString("value");
//			Log.i("mylog", "������Ϊ-->" + val);
			// TODO
			// UI����ĸ��µ���ز���
			
			Toast.makeText(getApplicationContext(),
					result, Toast.LENGTH_SHORT).show();
		}
	};

	/**
	 * ���������ص����߳�
	 */
	String result;
	Runnable networkTask = new Runnable() {

		@Override
		public void run() {
			// TODO
			
//			Userb user=new Userb();
//			user.setUserid(loginUserName.getText().toString().trim());
//			user.setPassword(loginPassword.getText().toString().trim());
//			 message=UserService.login(user);
			// ��������� http request.����������ز���
			
			
			Map<String ,String>  params = new HashMap<String,String>();
//			params.put("userid", "a123");
//			params.put("name", "song");
//			
//			Bitmap myBitmap = BitmapFactory.decodeResource(getResources(),
//					R.drawable.shouhuxing_floatingcenter);// ���ͼƬ��Դ
//			
//			String icon=Bitmap2StrByBase64(myBitmap);
//			params.put("heart", icon);
//			
//			result=NetworkTool.postRequest("http://192.168.1.202:8080/baibx/renz.action", params);
			
			params.put("userid", "a123");
			params.put("password", "song");
			
			
			
			result=NetworkTool.postRequest("http://192.168.1.202:8080/baibx/userlogin.action", params);
			
			
			Message msg = new Message();
			Bundle data = new Bundle();
//			data.putString("value", "������");
//			data.put
//			msg.setData(data);
			handler.sendMessage(msg);
		}
	};



}

