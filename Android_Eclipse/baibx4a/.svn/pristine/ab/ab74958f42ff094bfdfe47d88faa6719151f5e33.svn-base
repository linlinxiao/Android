package com.baoxiao;

import java.io.File;
import com.baoxiao.activity.homepage.GroupA;
import com.baoxiao.activity.homepage.HomePageA;
import com.baoxiao.activity.homepage.MineA;
import com.baoxiao.activity.homepage.WeiHuA;
import com.baoxiao.model.UpdateInfo;
import com.baoxiao.service.weihu.DownloadTask;
import com.baoxiao.service.weihu.UpdateInforService;
import com.baoxiao.util.GetSharedPreferencesValue;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 主页面
 *
 */
public class MainActivityA extends TabActivity implements OnClickListener{

	private static final String TAG = "MainActivity";
	private static TabHost tHost;
	String type;
	private LinearLayout homePageLinear,groupLinear,mineLinear,
	        weiHuLinear;
	private ImageView homePageImageView,groupImageView,mineImageView,
	        weiHuImageView;
	private TextView homePageTextView,groupTextView,mineTextView,
	        weiHuTextView;
	int mCurTabId = R.id.homePageLinear;
	private String updateTishi,autoUpdate,v,version;
	private UpdateInfo info;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initTabHost();
		initView();
		initData();
		
		type = GetSharedPreferencesValue.getType(MainActivityA.this);
		updateTishi = GetSharedPreferencesValue.getUpdateTishi(this);
		autoUpdate = GetSharedPreferencesValue.getAutoUpdate(this);
		//如果更新提示为true的话，显示更新提示
//		if(updateTishi!=null){
//			if(updateTishi.equals("true")){
//				new Thread(OnlineRunnable).start();
//			}else{
//	//			Toast.makeText(getApplicationContext(), "不显示更新提示，并且直接更新！", Toast.LENGTH_SHORT).show();
//				//如果有更新的则直接更新，否则不更新
//				if(autoUpdate!=null){
//					if(autoUpdate.equals("true")){
//						//首先要知道要不要更新，要更新的话则直接更新，不要的话不需要处理
//		//					Toast.makeText(getApplicationContext(), "自动更新", Toast.LENGTH_SHORT).show();
//							new Thread(OnlineRunnable).start();
//					}else{
//					}
//				}
//			}
//		}
	}

	private void initData() {
		version = getVersion();		
	}
	
	public String getVersion(){
		PackageManager pm = getPackageManager();
		PackageInfo pi;
		try {
			pi = pm.getPackageInfo(getPackageName(), 0);
			String version = pi.versionName;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "未知";
		}
	}

	private void showUpdateDialog(String message) {
//		Toast.makeText(getApplicationContext(), "显示更新提示！", Toast.LENGTH_SHORT).show();
//		AlertDialog dialog = new AlertDialog.Builder(MainActivityA.this)
//					.setTitle("");
		AlertDialog.Builder dialog = new AlertDialog.Builder(this)
					.setTitle("更新提醒")
					.setMessage(message)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(getApplicationContext(), "更新开始", Toast.LENGTH_SHORT).show();
							if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								File dir = new File(Environment.getExternalStorageDirectory(),"/baibx/update");
								if(!dir.exists()){
									dir.mkdirs();
								}
								String apkPath = Environment.getExternalStorageDirectory()+"/baibx/update/new.apk";
							    UpdateTask task = new UpdateTask(info.getUrl(), apkPath);
							    progressDialog.show();
							    new Thread(task).start();
							}
						}
					}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
					dialog.create().show();
	}
	
	/**
	 * 下载的线程
	 */
	class UpdateTask implements Runnable{
		private String path,filePath;
		
		public UpdateTask(String path, String filePath) {
			this.path = path;
			this.filePath = filePath;
		}

		@Override
		public void run() {
			File file;
			try {
				file = DownloadTask.getFile(path, filePath, progressDialog);
				progressDialog.dismiss();
//				tv_verssion.setText("最新版本："+v);
			    install(file);
			} catch (Exception e) {
				e.printStackTrace();
			    progressDialog.dismiss();
			    Toast.makeText(getApplicationContext(), "更新失败", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	/**
	 * 安装apk
	 * @param file 要安装的apk的目录
	 */
	private void install(File file) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		finish();
		startActivity(intent);
	}
	
	
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				showUpdateDialog("是否现在更新？");
				break;
			case 2:
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					File dir = new File(Environment.getExternalStorageDirectory(),"/baibx/update");
					if(!dir.exists()){
						dir.mkdirs();
					}
					String apkPath = Environment.getExternalStorageDirectory()+"/baibx/update/new.apk";
				    UpdateTask task = new UpdateTask(info.getUrl(), apkPath);
				    progressDialog.show();
				    new Thread(task).start();
				}
				break;

			default:
				break;
			}
		}
	};
	
	Runnable OnlineRunnable = new Runnable() {
//		Message msg = handler.obtainMessage();
		Message msg = new Message();
		@Override
		public void run() {
			Looper.prepare();
			if(isNeedUpdate(version)){
				if(autoUpdate!=null){
					if(autoUpdate.equals("false")){
						msg.what = 1;
					}else{
						msg.what = 2;
					}
				}else{
					msg.what = 1;
				}
			}
			handler.sendMessage(msg);
		}
	};	
	
	
	private boolean isNeedUpdate(String verssion) {
		UpdateInforService uip = new UpdateInforService(this);
		try {
			info = uip.getUpdateInfo(R.string.serverUrl);
			v = info.getVersion();
			if(v.equals(verssion)){
				Log.i(TAG, "当前版本："+verssion);
				Log.i(TAG, "最新版本："+v);
				return false;
			}else{
				Log.i(TAG, "需要更新");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(MainActivityA.this, "获取更新信息异常，请稍后再试", Toast.LENGTH_SHORT).show();
		}
		return false;
	}

	private void initTabHost() {
		tHost = getTabHost();

		tHost.addTab(tHost.newTabSpec("homepage").setIndicator("主页")
				.setContent(new Intent(MainActivityA.this, HomePageA.class)));
		tHost.addTab(tHost
				.newTabSpec("group")
				.setIndicator("群组",
						getResources().getDrawable(R.drawable.homepage))
				.setContent(new Intent(MainActivityA.this, GroupA.class)));
		tHost.addTab(tHost
				.newTabSpec("mine")
				.setIndicator("我的",
						getResources().getDrawable(R.drawable.homepage))
				.setContent(new Intent(MainActivityA.this, MineA.class)));
		tHost.addTab(tHost
				.newTabSpec("weihu")
				.setIndicator("维护",
						getResources().getDrawable(R.drawable.homepage))
				.setContent(new Intent(MainActivityA.this, WeiHuA.class)));

		tHost.setCurrentTab(0);// 默认显示第一个页面，homePage
	}
	
	private void initView() {
        homePageLinear = (LinearLayout) findViewById(R.id.homePageLinear);		
        groupLinear = (LinearLayout) findViewById(R.id.groupLinear);		
        mineLinear = (LinearLayout) findViewById(R.id.mineLinear);		
        weiHuLinear = (LinearLayout) findViewById(R.id.weiHuLinear);		
	    
        homePageImageView = (ImageView) findViewById(R.id.homePageImageView);
	    groupImageView = (ImageView) findViewById(R.id.groupImageView);
	    mineImageView = (ImageView) findViewById(R.id.mineImageView);
	    weiHuImageView = (ImageView) findViewById(R.id.weiHuImageView);
	    
	    homePageTextView = (TextView) findViewById(R.id.homePageTextView);
	    groupTextView = (TextView) findViewById(R.id.groupTextView);
	    mineTextView = (TextView) findViewById(R.id.mineTextView);
	    weiHuTextView = (TextView) findViewById(R.id.weiHuTextView);
	    
	    progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMessage("正在下载");
	
	    homePageLinear.setOnClickListener(this);
	    groupLinear.setOnClickListener(this);
	    mineLinear.setOnClickListener(this);
	    weiHuLinear.setOnClickListener(this);
	}

	//为tab按钮添加点击事件
	public static void setCurrentTabByTag(String tab) {
		tHost.setCurrentTabByTag(tab);
	}

	@Override
	public void onClick(View v) {
		if (mCurTabId == v.getId()) {
			return;
		}
		homePageImageView.setImageResource(R.drawable.main_homepage_normal);
		groupImageView.setImageResource(R.drawable.main_group_normal);
		mineImageView.setImageResource(R.drawable.main_mine_normal);
		weiHuImageView.setImageResource(R.drawable.main_weihu_normal);
		
		homePageTextView.setTextColor(Color.parseColor("#8E8E8E"));
		groupTextView.setTextColor(Color.parseColor("#8E8E8E"));
		mineTextView.setTextColor(Color.parseColor("#8E8E8E"));
		weiHuTextView.setTextColor(Color.parseColor("#8E8E8E"));
		int checkedId = v.getId();
		final boolean o;
		if (mCurTabId < checkedId)
			o = true;
		else
			o = false;
		if (o)
				tHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this,R.anim.left_out));
		else
		        tHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this,R.anim.right_out));
		switch (checkedId) {
		case R.id.homePageLinear:
			tHost.setCurrentTabByTag("homepage");
			homePageImageView.setImageResource(R.drawable.main_homepage_pressed);
			homePageTextView.setTextColor(Color.parseColor("#2299EE"));
			break;
		case R.id.groupLinear:
			if(type.equals("user")){
				tHost.setCurrentTabByTag("group");
				groupImageView.setImageResource(R.drawable.main_group_pressed);
				groupTextView.setTextColor(Color.parseColor("#2299EE"));
			}else{
				Toast.makeText(getApplicationContext(), "您是客户没有权限", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.mineLinear:
			tHost.setCurrentTabByTag("mine");
			mineImageView.setImageResource(R.drawable.main_mine_pressed);
			mineTextView.setTextColor(Color.parseColor("#2299EE"));
			break;
		case R.id.weiHuLinear:
			tHost.setCurrentTabByTag("weihu");
			weiHuImageView.setImageResource(R.drawable.main_weihu_pressed);
			weiHuTextView.setTextColor(Color.parseColor("#2299EE"));
			break;
		default:
			break;
		}

		if (o)
			tHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this,R.anim.left_in));
		else
			tHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this,R.anim.right_in));
		mCurTabId = checkedId;
	}

}
