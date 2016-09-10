package com.example.progressbar;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_main);
		setProgressBarIndeterminateVisibility(true);
		// 设置精确进度显示的进度值，最大值是10000
		setProgress(6000);
		
		initViews();
	}
	
	private void initViews() {
		progressBar = (ProgressBar) findViewById(R.id.progressBar4);
	}

	public void onClick(View btn) {
		switch (btn.getId()) {
		case R.id.add:
			progressBar.incrementProgressBy(10);
			progressBar.incrementSecondaryProgressBy(10);
			break;
		case R.id.sub:
			progressBar.incrementProgressBy(-10);
			progressBar.incrementSecondaryProgressBy(-10);
			break;
		case R.id.reset:
			progressBar.setProgress(60);
			progressBar.setSecondaryProgress(80);
			break;
		case R.id.showDailog:
			ProgressDialog progressDialog = new ProgressDialog(this, android.R.style.Theme_Dialog);
			progressDialog.setIndeterminate(false);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setIcon(R.drawable.ic_launcher);
			progressDialog.setTitle("进度条提示框");
			progressDialog.setMessage("已下载到60％，请耐心等候！");
			progressDialog.setMax(100);
			progressDialog.setProgress(60);
			progressDialog.setSecondaryProgress(80);
			progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity.this, "您点击了确定按钮", Toast.LENGTH_LONG).show();
				}
			});
			progressDialog.setCancelable(true);
			progressDialog.show();

		default:
			break;
		}
	}
}
