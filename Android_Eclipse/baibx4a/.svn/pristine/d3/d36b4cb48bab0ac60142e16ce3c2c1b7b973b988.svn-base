package com.baoxiao.test.activity.productshow;

import java.util.ArrayList;
import java.util.List;

import com.baoxiao.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class TestShouHuXShowA extends Activity implements OnClickListener {

	private List<String> list = new ArrayList<String>();
	private TextView myTextView;
	private Spinner mySpinner;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 设置横屏，全屏，无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.copyofps_shousuxshow);
	
	    list.add("北京");
	    list.add("上海");
	    list.add("深圳");
	    list.add("福州");
	    list.add("厦门");
	    myTextView = (TextView) findViewById(R.id.TextView_city);
	    mySpinner = (Spinner) findViewById(R.id.Spinner_city);
	    adapter = new ArrayAdapter<String>(this,
	    		android.R.layout.simple_spinner_item,
	    		list);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    mySpinner.setAdapter(adapter);
	    mySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
//		Intent intent = new Intent(this,ShouHuXSHowA.class);
//		startAcityty(intent);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
