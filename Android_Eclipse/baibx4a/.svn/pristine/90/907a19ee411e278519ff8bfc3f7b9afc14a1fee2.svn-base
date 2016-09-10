package com.baoxiao.activity.mine; 
  
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.baoxiao.R;
import com.baoxiao.activity.invitation.SelectPicActivity;
import com.baoxiao.activity.invitation.UploadUtil;
import com.baoxiao.activity.invitation.UploadUtil.OnUploadProcessListener;
import com.baoxiao.service.userproduct.UserService;
import com.baoxiao.util.BitmapUtil;
import com.baoxiao.util.Define;
import com.baoxiao.view.DefinedScrollView;
import com.baoxiao.view.RoundImageView;
import com.baoxiao.view.ViewUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MyCardEditA3 extends Activity { 
	private LinearLayout mLinearLayout;
	private LinearLayout.LayoutParams param;
	private DefinedScrollView scrollView;
	private LayoutInflater inflater;
    protected static final int GETMYCARD = 0;
	protected static final int SUBMIT = 1;
	private static final int TO_SELECT_PHOTO = 3;
	private ImageView selectimage;
	private String userid;
	private String fileKey;
	private JSONObject entity;
	private PopupWindow popupWindow;
	private View contentView;
	private TextView mycard_pop_title;
	private LinearLayout ll_mycard_pop_content;
	private char hidden[];
	private SharedPreferences mycard;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycard4);
        userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
        mycard = getSharedPreferences("mycard", Activity.MODE_PRIVATE);
        String hid = mycard.getString("hidden", "");
        if (hid.length() == 21) {
        	hidden = new char[21];
			for (int i = 0; i < 21; i++) {
				hidden[i] = hid.charAt(i);
			}
		}else{
			hidden = new char[]{
					'1','1','1','1','1',
					'1','1','1','1','1',
					'1','1','1','1','1',
					'1','1','1','1','1',
					'1'
				};
		}
		contentView = LayoutInflater.from(this).inflate(R.layout.mycard_pop_window, null);
		mycard_pop_title = (TextView)contentView.findViewById(R.id.mycard_pop_title);
		ll_mycard_pop_content = (LinearLayout)contentView.findViewById(R.id.ll_mycard_pop_content);
		setupView();
		getMycard();
	}
	private void getMycard() {
		new Thread(new Runnable() {
			@Override
			public void run() {
		        String userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
		        JSONObject object = UserService.getMycardData(userid);
				try {
					JSONObject messageHelper = new JSONObject(object.getString("messageHelper"));
					entity = new JSONObject(messageHelper.getString("entity"));
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}).start();
	}
	private void setupView() {
		scrollView = (DefinedScrollView) findViewById(R.id.definedview);
		int mycard_item_id[] = new int[]{
				R.layout.mycard_item1,
				R.layout.mycard_item2,
				R.layout.mycard_item3,
				R.layout.mycard_item4,
				R.layout.mycard_item5,
				R.layout.mycard_item6,
				R.layout.mycard_item7,
				R.layout.mycard_item8,
				R.layout.mycard_item9,
				R.layout.mycard_item10,
				R.layout.mycard_item11,
				R.layout.mycard_item12,
				R.layout.mycard_item13,
				R.layout.mycard_item14,
				R.layout.mycard_item15,
				R.layout.mycard_item16,
				R.layout.mycard_item17,
				R.layout.mycard_item18,
				R.layout.mycard_item19,
				R.layout.mycard_item20,
				R.layout.mycard_item21
		};
		for (int i = 0; i < mycard_item_id.length; i++) {
			param = new LinearLayout.LayoutParams(
			android.view.ViewGroup.LayoutParams.MATCH_PARENT,
			android.view.ViewGroup.LayoutParams.MATCH_PARENT);
			inflater = this.getLayoutInflater();
		  
			View addview = inflater.inflate(mycard_item_id[i], null);
			
			final int cb_left_begin_id = R.id.cb_mycard_left_row1;
			CheckBox right_cb = (CheckBox)addview.findViewById(cb_left_begin_id + i);
			if (hidden[i] == '0') {
				right_cb.setChecked(true);
			}
			right_cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
					if (isChecked) {
						hidden[buttonView.getId() - cb_left_begin_id] = '0';
				        getSharedPreferences("mycard", Activity.MODE_PRIVATE).edit().putString("hidden", String.valueOf(hidden)).commit();
					}else{
						hidden[buttonView.getId() - cb_left_begin_id] = '1';
				        getSharedPreferences("mycard", Activity.MODE_PRIVATE).edit().putString("hidden", String.valueOf(hidden)).commit();
					}
					submit("hidden",String.valueOf(hidden));
				}
			});

			mLinearLayout = new LinearLayout(this);
			mLinearLayout.addView(addview, param);
			scrollView.addView(mLinearLayout);
			
			switch (i) {
			case 0:
				String p1v1 = mycard.getString("p1v1", null);
				if (p1v1 != null) {
					Bitmap bmp = BitmapFactory.decodeFile(p1v1,new Options());
					Bitmap t_bmp = BitmapUtil.scaleImage(bmp);
					((RoundImageView)addview.findViewById(R.id.iv_mycard_p1_v1)).setImageBitmap(t_bmp);
				}
//				String p1t1 = mycard.getString("p1t1", null);
//				if (p1t1 != null) {
//					((TextView)findViewById(R.id.tv_mycard_p1_t1)).setText(p1t1);
//				}
//				String p1t2 = mycard.getString("p1t2", null);
//				if (p1t2 != null) {
//					((TextView)findViewById(R.id.tv_mycard_p1_t2)).setText(p1t2);
//				}
				break;

			default:
				break;
			}
		}
	
		scrollView.setPageListener(new DefinedScrollView.PageListener() {
			@Override
			public void page(int page) {
			}
		});
	}
	protected void submit(final String name, final String content) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				JSONObject object = UserService.saveMycardData(userid,name,content);
				Log.i("tag", "userid=" + userid + "name=" + name + "content=" + content);
				handler.sendMessage(handler.obtainMessage(SUBMIT, object));
//				{"messageHelper":{"message":"","result":"S","list":null,"entity":null}}
			}
		}).start();
	}
	private String getValue(String key) {
		String value = "";
		try {
			if (entity != null) {
				value = entity.getString(key);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}
	public void p1_t1(View v){
		String key = "p1t1";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p1_t2(View v){
		String key = "p1t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p2_t1(View v){
		String key = "p2t1";
//		String value = getValue(key);
//		showMyDialog(key,value,(TextView)v);
        String p2[] = new String[23];
        for (int i = 0; i < p2.length; i++) {
			p2[i] = (1994 + i) + "";
		}
        String title = "入司时间";
        showPop(v,title,p2,key);
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
    				if (entity == null) {
    					entity = new JSONObject();
    				}
    				try {
    					entity.put(key,content);
    				} catch (JSONException e) {
    					e.printStackTrace();
    				}

    				popupWindow.dismiss();
    			}
    		});
        	mycard_pop_item.setText(p[i]);
        	ll_mycard_pop_content.addView(view);
		}
        popupWindow = new PopupWindow(contentView,LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        showPopupWindow(v,contentView);		
	}
	public void p2_t2(View v){
		String key = "p2t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p2_t3(View v){
		String key = "p2t3";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p3_t1(View v){
		String key = "p3t1";
//		String value = getValue(key);
//		showMyDialog(key,value,(TextView)v);
        String p3[] = new String[]{
        		"试用业务员","业务员","主任","高级主任",
        		"资深主任","经理","高级经理","资深经理",
        		"总监","高级总监","资深总监","客户服务经理"
        		};
        String title = "职务";
        showPop(v,title,p3,key);
	}
	public void p4_t1(View v){
		String key = "p4t1";
//		String value = getValue(key);
//		showMyDialog(key,value,(TextView)v);
        String p4[] = new String[]{
//        		"非荣誉业务员",
        		"金奖荣誉业务员",
        		"银奖荣誉业务员",
        		"铜奖荣誉业务员"
        		};
        String title = "个人荣誉";
        showPop(v,title,p4,key);
	}
	public void p5_t1(View v){
		String key = "p5t1";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p5_t2(View v){
		String key = "p5t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p6_t1(View v){
		String key = "p6t1";
//		String value = getValue(key);
//		showMyDialog(key,value,(TextView)v);
        String p6[] = new String[]{
//	        	"非讲师",
				"见习讲师",
				"一星级讲师",
				"二星级讲师",
				"三星级讲师",
				"四星级讲师",
				"五星级讲师"
        		};
        String title = "讲师";
        showPop(v,title,p6,key);
	}
	public void p6_t2(View v){
		String key = "p6t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p6_t3(View v){
		String key = "p6t3";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p7_t1(View v){
		String key = "p7t1";
//		String value = getValue(key);
//		showMyDialog(key,value,(TextView)v);		   
        String p7[] = new String[]{
//	        	"无",
        		"营业部",
        		"营业区",
        		"分公司",
        		"全国"
        		};
        String title = "十大展业能手";
        showPop(v,title,p7,key);
	}
	public void p7_t2(View v){
		String key = "p7t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p8_t1(View v){
		String key = "p8t1";
//		String value = getValue(key);
//		showMyDialog(key,value,(TextView)v);  
        String p8[] = new String[]{
//	        "非会员",
     		"银星会员",
     		"金星会员",
     		"钻星会员",
     		"终身会员"
		};
        String title = "会员";
        showPop(v,title,p8,key);
	}
	public void p8_t2(View v){
		String key = "p8t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p9_t1(View v){
		String key = "p9t1";
//		String value = getValue(key);
//		showMyDialog(key,value,(TextView)v);
        String p9[] = new String[]{
//	    	    "非会员",
         		"银星主管会员",
         		"金星主管会员",
         		"钻星主管会员",
         		"终身主管会员"
		};
        String title = "主管会员";
        showPop(v,title,p9,key);
	}
	public void p9_t2(View v){
		String key = "p9t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p10_t1(View v){
		String key = "p10t1";
//		String value = getValue(key);
//		showMyDialog(key,value,(TextView)v);
		
	    String p10[] = new String[]{
//		    	"非会员",
	 		   "普通会员",
	 		   "超级会员",
			   "顶尖会员",
			   "终身会员"
		};
	    String title = "百万圆桌会议";
	    showPop(v,title,p10,key);
	}
	public void p11_t1(View v){
		String key = "p11t1";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p12_t1(View v){
		String key = "p12t1";
//		String value = getValue(key);
//		showMyDialog(key,value,(TextView)v);		   
	    String p12[] = new String[]{
//			    	"非会员",
	 		   "铜奖杰出业务员",
			   "银奖杰出业务员",
			   "金奖杰出业务员",
			   "白金奖杰出业务员"
		};
	    String title = "IDA国际龙奖营销员";
	    showPop(v,title,p12,key);
	}
	public void p12_t2(View v){
		String key = "p12t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p13_t1(View v){
		String key = "p13t1";
//		String value = getValue(key);
//		showMyDialog(key,value,(TextView)v);
	    String p13[] = new String[]{
//			    "非会员",
	 		   "铜龙奖优秀主管",
			   "银龙奖优秀主管",
			   "金龙奖优秀主管",
			   "白金奖优秀主管"
		};
	    String title = "IDA国际龙奖优秀主管";
	    showPop(v,title,p13,key);

	}
	public void p13_t2(View v){
		String key = "p13t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p14_t1(View v){
		String key = "p14t1";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p15_t1(View v){
		String key = "p15t1";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p16_t1(View v){
		String key = "p16t1";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p17_t1(View v){
		String key = "p17t1";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p17_t2(View v){
		String key = "p17t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p17_t3(View v){
		String key = "p17t3";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p18_t1(View v){
		String key = "p18t1";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p18_t2(View v){
		String key = "p18t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p18_t3(View v){
		String key = "p18t3";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p19_t1(View v){
		String key = "p19t1";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p19_t2(View v){
		String key = "p19t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p19_t3(View v){
		String key = "p19t3";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p21_t1(View v){
		String key = "p21t1";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}
	public void p21_t2(View v){
		String key = "p21t2";
		String value = getValue(key);
		showMyDialog(key,value,(TextView)v);
	}

	public void preView(View v){
//		String data = null;
//		try {
//			data = getData(entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String name = null;
		String head_image = null;
		try {
			name = entity.getString("p1t1");
			head_image = entity.getString("p1v1");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		startActivity(new Intent(MyCardEditA3.this,MyCardA2.class)
				.putExtra("userid", userid)
				.putExtra("hidden", String.valueOf(hidden))
				.putExtra("name", name)
				.putExtra("head_image", head_image)
				);
	}
	
	private void showMyDialog(final String key,final String value,final TextView v) {
		final EditText inputServer = new EditText(this);
		inputServer.setInputType(EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE);
		inputServer.setMinLines(2);
		inputServer.setSingleLine(false);
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
	public void p1_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p1_v1));
		fileKey = "p1v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p2_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p2_v1));
		fileKey = "p2v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p2_v2(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p2_v2));
		fileKey = "p2v2";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p2_v3(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p2_v3));
		fileKey = "p2v3";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p3_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p3_v1));
		fileKey = "p3v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p3_v2(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p3_v2));
		fileKey = "p3v2";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p3_v3(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p3_v3));
		fileKey = "p3v3";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p4_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p4_v1));
		fileKey = "p4v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p4_v2(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p4_v2));
		fileKey = "p4v2";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p4_v3(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p4_v3));
		fileKey = "p4v3";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p5_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p5_v1));
		fileKey = "p5v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p5_v2(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p5_v2));
		fileKey = "p5v2";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p5_v3(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p5_v3));
		fileKey = "p5v3";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p6_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p6_v1));
		fileKey = "p6v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p6_v2(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p6_v2));
		fileKey = "p6v2";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p7_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p7_v1));
		fileKey = "p7v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p7_v2(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p7_v2));
		fileKey = "p7v2";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p7_v3(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p7_v3));
		fileKey = "p7v3";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p8_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p8_v1));
		fileKey = "p8v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p8_v2(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p8_v2));
		fileKey = "p8v2";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p8_v3(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p8_v3));
		fileKey = "p8v3";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p9_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p9_v1));
		fileKey = "p9v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p10_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p10_v1));
		fileKey = "p10v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p11_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p11_v1));
		fileKey = "p11v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p12_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p12_v1));
		fileKey = "p12v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p13_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p13_v1));
		fileKey = "p13v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p14_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p14_v1));
		fileKey = "p14v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p15_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p15_v1));
		fileKey = "p15v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p16_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p16_v1));
		fileKey = "p16v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p17_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p17_v1));
		fileKey = "p17v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p17_v2(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p17_v2));
		fileKey = "p17v2";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p17_v3(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p13_v1));
		fileKey = "p17v3";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p18_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p18_v1));
		fileKey = "p18v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p18_v2(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p18_v2));
		fileKey = "p18v2";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p19_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p19_v1));
		fileKey = "p19v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}
	public void p21_v1(View v){
		selectimage = ((ImageView)findViewById(R.id.iv_mycard_p21_v1));
		fileKey = "p21v1";
		Intent intent = new Intent(this,SelectPicActivity.class);
		startActivityForResult(intent, TO_SELECT_PHOTO);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (RESULT_OK != resultCode) {return;}
		if (data == null) {return;}
		if(requestCode == TO_SELECT_PHOTO){
			final String picPath = data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH);
			if (picPath == null) {return;}
//			showCuter(picPath);
			if (selectimage.getId() == R.id.iv_mycard_p1_v1) {
//				Uri uri = Uri.parse(data.getStringExtra("photoUri"));
				Bitmap bmp = BitmapFactory.decodeFile(picPath,new Options());
				Bitmap t_bmp = BitmapUtil.scaleImage(bmp);
				((RoundImageView)selectimage).setImageBitmap(t_bmp);
//				((RoundImageView)selectimage).setVisibility(View.VISIBLE);
			}else{
				showBitmap(selectimage,picPath);
			}
			toUploadFile(picPath,fileKey);
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
		UploadUtil uploadUtil = UploadUtil.getInstance();
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
		String requestURL = Define.Server + "mycardSave.action";
		uploadUtil.uploadFile(picPath,fileKey,requestURL,params);
	}
    private void showPopupWindow(View view,View contentView) {
        // 设置按钮的点击事件
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
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        ColorDrawable colorDrawable = new ColorDrawable(Color.argb(0, 0, 0, 0));
        popupWindow.setBackgroundDrawable(colorDrawable);
        // 设置好参数之后再show
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
    public void mycard_back(View v){
    	finish();
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
					mycard.edit().putString(key, value).commit();
				}
				break;
								
			default:
				break;
			}
			return false;
		}
	});
}