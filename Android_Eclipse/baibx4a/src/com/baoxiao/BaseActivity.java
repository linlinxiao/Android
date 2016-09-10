package com.baoxiao;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.widget.Toast;

public class BaseActivity extends Activity{
	private static boolean mBackKeyPressed = false;
	@Override
	public void onBackPressed() {
		if(!mBackKeyPressed){
			Toast.makeText(this, "再按一次退出平安百宝箱", Toast.LENGTH_SHORT).show();
			mBackKeyPressed = true;
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					mBackKeyPressed = false;
				}
			},2000);
		}else{
			this.finish();
			System.exit(0);
		}
	}
}
