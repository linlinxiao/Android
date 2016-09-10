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
import com.baoxiao.view.ViewUtil;

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
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class InvitationVisitLetteA extends Activity{
	protected static final int SUBMIT = 0;
	protected static final int GETVISITLETTEINFO = 1;
	private static final int TO_SELECT_PHOTO = 2;
	private String userid;
	private SharedPreferences visitLette;
	private ProgressDialog dialog;
	
	private PopupWindow popupWindow;
	private View contentView,contentView2;
	private TextView mycard_pop_title,mycard_pop_title2;
	private LinearLayout ll_mycard_pop_content,ll_mycard_pop_content2;
	private String fileKey;
	private ImageView currentImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invitation_visit_lette);
		((TextView)findViewById(R.id.tv_title)).setText("拜访函");
		userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
		visitLette = getSharedPreferences("visitLette", Activity.MODE_PRIVATE);
		currentImage = (ImageView)findViewById(R.id.iv_guestImage1);
		
		contentView = LayoutInflater.from(this).inflate(R.layout.mycard_pop_window2, null);
		mycard_pop_title = (TextView)contentView.findViewById(R.id.mycard_pop_title);
		ll_mycard_pop_content = (LinearLayout)contentView.findViewById(R.id.ll_mycard_pop_content);
		
		contentView2 = LayoutInflater.from(this).inflate(R.layout.mycard_pop_window3, null);
		mycard_pop_title2 = (TextView)contentView2.findViewById(R.id.mycard_pop_title);
		ll_mycard_pop_content2 = (LinearLayout)contentView2.findViewById(R.id.ll_mycard_pop_content);
		
		if (visitLette.getString("subject", null) == null) {
			getVisitLetteInfo();
			return;
		}
		String subject = visitLette.getString("subject", "拜访函");
		((TextView)findViewById(R.id.et_subject)).setText(subject);
		String time = visitLette.getString("time", "具体时间请您予以安排，期待您的回复。");
		((TextView)findViewById(R.id.et_time)).setText(time);
		String guestName1 = visitLette.getString("guestName1", "XXX");
		((TextView)findViewById(R.id.et_guestName1)).setText(guestName1);
		String guestOther1 = visitLette.getString("guestOther1", "你的保险顾问");
		((TextView)findViewById(R.id.et_guestOther1)).setText(guestOther1);
		String content = visitLette.getString("content","你好！\n很高兴能认识您，并和您讨论您家人的保险计划，我真诚希望尽我所能为您和您的家人提供专业的保险理财服务。我在近期内会前往贵公司附近做客户服务，也很希望能拜访您。");
		((TextView)findViewById(R.id.et_content)).setText(content);
		
		String guestImage1_path = visitLette.getString("iv_guestImage1", null);
		if (guestImage1_path != null) {
			Bitmap bmp = BitmapFactory.decodeFile(guestImage1_path,new Options());
			if (bmp == null) {return;}
			Bitmap t_bmp = BitmapUtil.scaleImage(bmp);
//			((ImageView)findViewById(R.id.iv_guestImage1)).setBackground(new BitmapDrawable(getResources(), t_bmp));
			ViewUtil.setBackground((ImageView)findViewById(R.id.iv_guestImage1), this, new BitmapDrawable(getResources(),t_bmp));
		}
	}
	
	private void getVisitLetteInfo() {
		if (dialog == null) {
			dialog = new ProgressDialog(this);
		}dialog.show();
		new Thread(new Runnable() {
			@Override
			public void run() {
				JSONObject object = UserService.getVisitLetteData(userid);
				handler.sendMessage(handler.obtainMessage(GETVISITLETTEINFO, object));
			}
		}).start();
	}

	public void invitation_wine_back(View v){
		finish();
	}
	public void preView(View v) {
		startActivity(new Intent(this,InvitationWinePreViewA.class)
				.putExtra("type", 5));
	}
	
	public void subject(View v){
		String key = "subject";
        String array[] = new String[]{
        	    "拜访函",
        	    "预约函",
        	    "邀约函",
        	    "约见函"
        	};
        String title = "个人荣誉";
        showPop(v,title,array,key);
	}
	private void showPop(final View v, String title, String[] p, final String key) {
        mycard_pop_title.setText(title);
        ll_mycard_pop_content.removeAllViews();
        for (int i = 0; i < p.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.mycard_pop_window_item, null);
        	TextView mycard_pop_item = (TextView)view.findViewById(R.id.mycard_pop_item);
        	mycard_pop_item.setClickable(true);
            mycard_pop_item.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View v2) {
    				String content = ((TextView)v2).getText().toString();
    				((TextView)v).setText(content);
    				submit(key,content);
    				popupWindow.dismiss();
    			}
    		});
        	mycard_pop_item.setText(p[i]);
        	ll_mycard_pop_content.addView(view);
		}
        popupWindow = new PopupWindow(contentView,LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
        showPopupWindow(v,contentView);		
	}
	private void showPop2(final View v, String title, String[] p, final String key) {
        mycard_pop_title2.setText(title);
        ll_mycard_pop_content2.removeAllViews();
        for (int i = 0; i < p.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.mycard_pop_window_item3, null);
        	TextView mycard_pop_item = (TextView)view.findViewById(R.id.mycard_pop_item);
        	mycard_pop_item.setClickable(true);
            mycard_pop_item.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View v2) {
    				String content = ((TextView)v2).getText().toString();
    				((TextView)v).setText(content);
    				submit(key,content);
    				popupWindow.dismiss();
    			}
    		});
        	mycard_pop_item.setText(p[i]);
        	ll_mycard_pop_content2.addView(view);
		}
        popupWindow = new PopupWindow(contentView2,LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		
        contentView2.findViewById(R.id.bt_pop_cancle).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		contentView2.findViewById(R.id.bt_pop_ok).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View vi) {
				String content = ((EditText)contentView2.findViewById(R.id.et_pop_editer)).getText().toString();
				if ("".equals(content)) {
					popupWindow.dismiss();
					return;
				}
				submit(key, content);
				((TextView)v).setText(content);
				popupWindow.dismiss();
			}
		});

        showPopupWindow(v,contentView2);		
	}
    private void showPopupWindow(View view,View contentView) {
        Button button = new Button(this);
        button.setText("11111");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        ColorDrawable colorDrawable = new ColorDrawable(Color.argb(0, 0, 0, 0));
        popupWindow.setBackgroundDrawable(colorDrawable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

	public void time(View v){
		String key = "time";
        String array[] = new String[]{
        	 "具体时间请您予以安排，期待您的回复",
        	 "期待明天上午十点能和你见面",
        	 "期待明天下午三点能和你见面",
        	 "期待明天晚上八点能和你见面",
        	 "很期待明天上午十点能见到你",
        	 "很期待明天下午三点能见到你",
        	 "很期待明天晚上八点能见到你",
        	};
        String title = "拜访时间";
        showPop2(v,title,array,key);
	}
	public void guestName1(View v){
		showMyDialog("guestName1",(TextView)v);
	}
	public void guestOther1(View v){
		String key = "guestOther1";
        String array[] = new String[]{
        	    "你的保险顾问",
        	    "你的好朋友",
        	    "你的邻居",
        	    "你的老同事",
        	    "你的闺蜜",
        	    "你的铁哥们",
        	    "不编辑身份",
        	    "可编辑"
        	};
        String title = "拜访人身份";
        showPop2(v,title,array,key);
	}
	public void content(View v){
		showMyDialog("content",(TextView)v);
	}
	
	public void guestImage1(View v){
		fileKey = "guestImage1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
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
				JSONObject object = UserService.saveVisitLetteData(userid,name,content);
				handler.sendMessage(handler.obtainMessage(SUBMIT, object));
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
				saveNative(entity);
				break;
			case GETVISITLETTEINFO:
				object = (JSONObject)msg.obj;
				if (object == null) {break;}
				try {
					JSONObject messageHelper = new JSONObject(object.getString("messageHelper"));
					entity = new JSONObject(messageHelper.getString("entity"));
					
					String subject = entity.getString("subject");
					((TextView)findViewById(R.id.et_subject)).setText(subject);
					String time = entity.getString("time");
					((TextView)findViewById(R.id.et_time)).setText(time);
					String guestName1 = entity.getString("guestName1");
					((TextView)findViewById(R.id.et_guestName1)).setText(guestName1);
					String guestOther1 = entity.getString("guestOther1");
					((TextView)findViewById(R.id.et_guestOther1)).setText(guestOther1);
					String content = entity.getString("content");
					((TextView)findViewById(R.id.et_content)).setText(content);
					
					saveNative(entity);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
			return false;
		}

		private void saveNative(JSONObject entity) {
			Iterator<?> iterator = entity.keys();
			while(iterator.hasNext()){
				String key = (String) iterator.next();
			    String value = null;
				try {
					value = entity.getString(key);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				visitLette.edit().putString(key, value).commit();
			}			
		}
	});
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (RESULT_OK != resultCode) {return;}
		if (data == null) {return;}
//		//相册
		if(requestCode == TO_SELECT_PHOTO){
			String picPath = data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH);
			if (picPath == null) {return;}
			showBitmap(currentImage,picPath);
			toUploadFile(picPath,fileKey);
			visitLette.edit().putString("iv_guestImage1", picPath).commit();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	private void showBitmap(ImageView imageView,String picPath){
		Bitmap bmp = BitmapFactory.decodeFile(picPath,new Options());
		Bitmap t_bmp = BitmapUtil.scaleImage(bmp);
//		((ImageView)findViewById(R.id.iv_guestImage1))
//		.setBackground(new BitmapDrawable(getResources(),t_bmp));
		ViewUtil.setBackground((ImageView)findViewById(R.id.iv_guestImage1), this, new BitmapDrawable(getResources(),t_bmp));

//		//得到imageView大小
//		int dw = imageView.getWidth();
//		int dh = imageView.getHeight();
//		BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
//		bmpFactoryOptions.inJustDecodeBounds = true;//计算出图像的大小，而不实际进行图像的解码：
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
		});//设置监听器监听上传状态
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userid);
		String filename = "123.jpg";
		if (picPath != null) {
			filename = picPath.substring(picPath.lastIndexOf("/") + 1,picPath.length());
		}
		params.put("filename", filename);
		String requestURL = Define.Server + "visitLetteSave.action";
		uploadUtil.uploadFile(picPath,fileKey,requestURL,params);
	}
	public void visit_lette_back(View v){
		finish();
	}
}
