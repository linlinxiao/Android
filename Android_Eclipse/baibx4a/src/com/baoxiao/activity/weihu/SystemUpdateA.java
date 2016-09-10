package com.baoxiao.activity.weihu;

import java.io.File;
import com.baoxiao.R;
import com.baoxiao.model.UpdateInfo;
import com.baoxiao.service.weihu.DownloadTask;
import com.baoxiao.service.weihu.UpdateInforService;
import com.baoxiao.util.GetSharedPreferencesValue;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

/**
 * '数据备份恢复'布局界面
 *
 */
public class SystemUpdateA extends Activity implements OnClickListener{

//	private static final String TAG = "SystemUpdateA";

	protected static final int VERSIONUPDATE = 0;
	protected static final int IS_NEED_UPDATE = 1;
	protected static final int IS_NO_NEED_UPDATE = 2;
	private ImageView updateTiShi,autoUpdate;
	private TextView tv_verssion;
	private String updateTiShiFlag = "",autoUpdateFlag = "";
	private UpdateInfo info;
	private String verssion,v;
	private ProgressDialog progressDialog;
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weihu_update3);
	
	    initView();
	    initData();
//	    judgeVersion();
	}
//	private void judgeVersion() {
////		dialog = new ProgressDialog(LoginActivity.this);
////		dialog.show();
//		new Thread(new Runnable() {
//			public void run() {
//				String u = "http://oa.inno-vision.cn/APP/base_info_oa.xml";
//				String result = null;
//				try {
//					result = NetworkTool.getContent(u);
//				} catch (Throwable e) {
//					e.printStackTrace();
//				}
//				Message message = handler.obtainMessage();
//				message.obj = result;
//				message.what = VERSIONUPDATE ;
//				handler.sendMessage(message);
//			}
//		}).start();
//	}
	private void initView() {
		updateTiShi = (ImageView) findViewById(R.id.updateTiShi);
		autoUpdate = (ImageView) findViewById(R.id.autoUpdate);
		tv_verssion = (TextView) findViewById(R.id.verssion);
//		progressDialog = new ProgressDialog(this);
//		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//		progressDialog.setMessage("正在下载");
		
		updateTiShi.setOnClickListener(this);
		autoUpdate.setOnClickListener(this);
		
//		new Thread(){
//			@Override
//			public void run() {
//				try {
//					sleep(3000);
//					handler.sendEmptyMessage(0);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//	    	
//	    }.start();
		
	    
	    sp = getSharedPreferences("loading", MODE_APPEND);
	    editor = sp.edit();
		
	    updateTiShiFlag = GetSharedPreferencesValue.getUpdateTishi(this);
	    autoUpdateFlag = GetSharedPreferencesValue.getAutoUpdate(this);
	    if(updateTiShiFlag != null){
			if(updateTiShiFlag.equals("false")){
				updateTiShi.setScaleType(ScaleType.CENTER_INSIDE);
				updateTiShi.setImageDrawable(getResources().getDrawable(R.drawable.update_off));
//				updateTiShiFlag = "true";
			}else{
				updateTiShi.setScaleType(ScaleType.CENTER_INSIDE);
				updateTiShi.setImageDrawable(getResources().getDrawable(R.drawable.update_on));
//				updateTiShiFlag = "false";
			}	
	    }
	    if(autoUpdateFlag != null){
	    	if(autoUpdateFlag.equals("false")){
				autoUpdate.setScaleType(ScaleType.CENTER_INSIDE);
				autoUpdate.setImageDrawable(getResources().getDrawable(R.drawable.update_off));
//				autoUpdateFlag = "true";
			}else{
				autoUpdate.setScaleType(ScaleType.CENTER_INSIDE);
				autoUpdate.setImageDrawable(getResources().getDrawable(R.drawable.update_on));
//				autoUpdateFlag = "false";
			}
	    }
	} 
	
	private void initData() {
		verssion = getVersion();
		tv_verssion.setText("最新版本："+verssion);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.updateTiShi:
			if(updateTiShiFlag == null){
				updateTiShiFlag = "false";
				if(updateTiShiFlag.equals("false")){
					updateTiShi.setScaleType(ScaleType.CENTER_INSIDE);
					updateTiShi.setImageDrawable(getResources().getDrawable(R.drawable.update_on));
					updateTiShiFlag = "true";
	//				Toast.makeText(getApplicationContext(), updateTiShiFlag, Toast.LENGTH_SHORT).show();
				}else{
					updateTiShi.setScaleType(ScaleType.CENTER_INSIDE);
					updateTiShi.setImageDrawable(getResources().getDrawable(R.drawable.update_off));
					updateTiShiFlag = "false";
	//				Toast.makeText(getApplicationContext(), updateTiShiFlag, Toast.LENGTH_SHORT).show();
				}
			}else{
				if(updateTiShiFlag.equals("false")){
					updateTiShi.setScaleType(ScaleType.CENTER_INSIDE);
					updateTiShi.setImageDrawable(getResources().getDrawable(R.drawable.update_on));
					updateTiShiFlag = "true";
	//				Toast.makeText(getApplicationContext(), updateTiShiFlag, Toast.LENGTH_SHORT).show();
				}else{
					updateTiShi.setScaleType(ScaleType.CENTER_INSIDE);
					updateTiShi.setImageDrawable(getResources().getDrawable(R.drawable.update_off));
					updateTiShiFlag = "false";
	//				Toast.makeText(getApplicationContext(), updateTiShiFlag, Toast.LENGTH_SHORT).show();
				}
			}
			editor.putString("updateTishi", updateTiShiFlag);
			editor.commit();
			break;
//		case R.id.shuoMing:
//			Toast.makeText(getApplicationContext(), "说明！！", Toast.LENGTH_SHORT).show();
//			break;
		case R.id.autoUpdate:
			if(autoUpdateFlag == null){
				autoUpdateFlag = "false";
				if(autoUpdateFlag.equals("false")){
					autoUpdate.setScaleType(ScaleType.CENTER_INSIDE);
					autoUpdate.setImageDrawable(getResources().getDrawable(R.drawable.update_on));
					autoUpdateFlag = "true";
	//				Toast.makeText(getApplicationContext(), autoUpdateFlag, Toast.LENGTH_SHORT).show();
				}else{
					autoUpdate.setScaleType(ScaleType.CENTER_INSIDE);
					autoUpdate.setImageDrawable(getResources().getDrawable(R.drawable.update_off));
					autoUpdateFlag = "false";
	//				Toast.makeText(getApplicationContext(), autoUpdateFlag, Toast.LENGTH_SHORT).show();
				}
			}else{
				if(autoUpdateFlag.equals("false")){
					autoUpdate.setScaleType(ScaleType.CENTER_INSIDE);
					autoUpdate.setImageDrawable(getResources().getDrawable(R.drawable.update_on));
					autoUpdateFlag = "true";
	//				Toast.makeText(getApplicationContext(), autoUpdateFlag, Toast.LENGTH_SHORT).show();
				}else{
					autoUpdate.setScaleType(ScaleType.CENTER_INSIDE);
					autoUpdate.setImageDrawable(getResources().getDrawable(R.drawable.update_off));
					autoUpdateFlag = "false";
	//				Toast.makeText(getApplicationContext(), autoUpdateFlag, Toast.LENGTH_SHORT).show();
				}
			}
			editor.putString("autoUpdate", autoUpdateFlag);
			editor.commit();
			break;

		default:
			break;
		}
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
		AlertDialog dialog = new AlertDialog.Builder(this)
		       	.setTitle("更新提醒：").setMessage(message)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Uri uri = Uri.parse("http://a.app.qq.com/o/simple.jsp?pkgname=com.hengshuo.baibx4a");  
					Intent it = new Intent(Intent.ACTION_VIEW, uri);  
					startActivity(it);
//					String value = Environment.getExternalStorageState();
//					if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//						File dir = new File(Environment.getExternalStorageDirectory(),"/baibx/update");
//						if(!dir.exists()){
//							dir.mkdirs();
//						}
//						String apkPath = Environment.getExternalStorageDirectory()+"/baibx/update/new.apk";
//					    UpdateTask task = new UpdateTask(info.getUrl(), apkPath);
//					    progressDialog.show();
//					    new Thread(task).start();
//					}
				}
			})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						handler.removeMessages(1);
					}
				})
				.setCancelable(false)
				.show();
		dialog.setCanceledOnTouchOutside(false);
	}
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case IS_NEED_UPDATE:
				showUpdateDialog("是否现在更新？");
				break;
			case IS_NO_NEED_UPDATE:
				new AlertDialog.Builder(SystemUpdateA.this)
		       	.setTitle("更新提醒：").setMessage("当前已经是最新版本了！").show();
				break;

			default:
				break;
			}
		}
	};
	
	private boolean isNeedUpdate(String verssion) {
		UpdateInforService uip = new UpdateInforService(this);
		try {
			info = uip.getUpdateInfo(R.string.serverUrl);
			v = info.getVersion();
			if(v.equals(verssion)){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
	public void weihu_update(View v) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Message msg = handler.obtainMessage();
				if(isNeedUpdate(verssion)){
					msg.what = IS_NEED_UPDATE;
				}else{
					msg.what = IS_NO_NEED_UPDATE;
				}
				handler.sendMessage(msg);
			}
		}).start();
	}
	public void mineBack(View v) {
		finish();
	}
}
