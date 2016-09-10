package com.example.spinner;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements OnItemSelectedListener {
	
	private Spinner spinner;
	private TextView textVeiw;
	private ArrayAdapter<String> arr_adapter;//简单布局下拉选择框
	private SimpleAdapter adapter;//自定义布局下拉选择框
	private String[] cities = {"北京", "上海", "深圳", "广州"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cities);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setAdapter(arr_adapter);
        spinner.setOnItemSelectedListener(this);
        
        textVeiw = (TextView) findViewById(R.id.textView1);
    }

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		String city = arr_adapter.getItem(position);
		textVeiw.setText("您选择的城市是："+city);
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
