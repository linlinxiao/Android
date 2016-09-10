package com.baoxiao.test.activity.testmian;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.baoxiao.R;

public class TestFloating extends Activity {
	private Spinner sp_bgcolor;

	 private ArrayAdapter<String> adapter;
	
	private String[] bgcolor={"黑色","黄色","绿色","青色","红色","橙色","紫色","蓝色"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.testweight);
		

		sp_bgcolor.setAdapter(colorAdapter());
		sp_bgcolor.setOnItemSelectedListener(new BgColorItemSelectedListener());
		
	}
	
	
	private SpinnerAdapter colorAdapter(){
		//使用自定义的ArrayAdapter
		adapter = new SPAdapter(this,bgcolor); 
		return adapter;
		
	}
	public class SPAdapter extends ArrayAdapter{
		private Context mContext;
	    private String [] mStringArray;
		public SPAdapter(Context context, String[] stringArray) {
			super(context, android.R.layout.simple_spinner_item, stringArray);
			mContext = context;
			mStringArray=stringArray;
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getDropDownView(int position, View convertView, ViewGroup parent) {
			//修改Spinner展开后的字体颜色
			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent,false);
			}

			//此处text1是Spinner默认的用来显示文字的TextView
			TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
			tv.setText(mStringArray[position]);
			tv.setTextSize(18f);
			tv.setTextColor(Color.BLUE);

			return convertView;

		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 修改Spinner选择后结果的字体颜色
			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
			}

			//此处text1是Spinner默认的用来显示文字的TextView
			TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
			tv.setText(mStringArray[position]);
			tv.setTextSize(18f);
			tv.setTextColor(Color.BLUE);
			return convertView;
		}
	}
	
	
	private class BgColorItemSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,long arg3) {
			// TODO Auto-generated method stub
//			mBgColor=bgcolor[position];
		}
		

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	}

}
