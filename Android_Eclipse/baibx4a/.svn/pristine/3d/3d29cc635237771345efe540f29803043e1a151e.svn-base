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
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.us_edit_userinfo);
		
		webView=(WebView)this.findViewById(R.id.edituser);
		TextView renZheng=(TextView)this.findViewById(R.id.renZheng);
		renZheng.setOnClickListener(this);
//		CharSequence[] items = {"相册", "相机"};  
//	    new AlertDialog.Builder(this)
//		    .setTitle("选择图片来源")
//		    .setItems(items, new OnClickListener() {
//				public void onClick(DialogInterface dialog, int which) {
//					if( which == SELECT_PICTURE ){
//						Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//						intent.addCategory(Intent.CATEGORY_OPENABLE);
//						intent.setType("image/*");
//						startActivityForResult(Intent.createChooser(intent, "选择图片"), SELECT_PICTURE); 
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
	 * 通过Base32将Bitmap转换成Base64字符串
	 * @param bit
	 * @return
	 */
	public String Bitmap2StrByBase64(Bitmap bit){
	   ByteArrayOutputStream bos=new ByteArrayOutputStream();
	   bit.compress(CompressFormat.JPEG, 40, bos);//参数100表示不压缩
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
//				    //进入主页面
//					startActivity(intent);
//					finish();
//					}else{
//						Toast.makeText(getApplicationContext(),message.getMessage() , Toast.LENGTH_SHORT).show();
//					}
//			Bundle data = msg.getData();
//			String val = data.getString("value");
//			Log.i("mylog", "请求结果为-->" + val);
			// TODO
			// UI界面的更新等相关操作
			
			Toast.makeText(getApplicationContext(),
					result, Toast.LENGTH_SHORT).show();
		}
	};

	/**
	 * 网络操作相关的子线程
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
			// 在这里进行 http request.网络请求相关操作
			
			
			Map<String ,String>  params = new HashMap<String,String>();
//			params.put("userid", "a123");
//			params.put("name", "song");
//			
//			Bitmap myBitmap = BitmapFactory.decodeResource(getResources(),
//					R.drawable.shouhuxing_floatingcenter);// 获得图片资源
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
//			data.putString("value", "请求结果");
//			data.put
//			msg.setData(data);
			handler.sendMessage(msg);
		}
	};



}

