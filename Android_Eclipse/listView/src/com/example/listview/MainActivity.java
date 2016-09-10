package com.example.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
		OnItemClickListener, OnScrollListener {

	private ListView listView;
	private String[] arr = { "list1", "list2", "list3" };
	private ArrayList<HashMap<String, Object>> data;
	private SimpleAdapter simple_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		data = getData();

		initListView();
	}

	private void initListView() {
		simple_adapter = new SimpleAdapter(this, data,
				R.layout.item, new String[] { "image", "text" }, new int[] {
						R.id.imageView1, R.id.textView1 });
		ArrayAdapter<String> arr_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arr);

		listView = (ListView) findViewById(R.id.listView1);
		// listView.setAdapter(arr_adapter);
		listView.setAdapter(simple_adapter);
		listView.setOnItemClickListener(this);
		listView.setOnScrollListener(this);
		//
	}

	private ArrayList<HashMap<String, Object>> getData() {
		ArrayList<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < 20; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.ic_launcher);
			map.put("text", "琳琳列表数据" + i);

			array.add(map);
		}

		return array;
	}

	@Override
	public void onItemClick(AdapterView<?> listView, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub

		Log.i("tag", "" + position);
		String item = ((ListView) listView).getItemAtPosition(position)
				.toString();
		Toast.makeText(this, item, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int state) {
		// TODO Auto-generated method stub
		switch (state) {
		case SCROLL_STATE_FLING:
			Toast.makeText(this, "手指离开屏幕减速滑动的状态", Toast.LENGTH_LONG).show();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.abc_ic_go);
			map.put("text", "下拉加载更多的数据");
			data.add(map);
			simple_adapter.notifyDataSetChanged();//通知ListView刷新视图
			break;
		case SCROLL_STATE_IDLE:
			Toast.makeText(this, "滑动停止的状态", Toast.LENGTH_LONG).show();

			break;
		case SCROLL_STATE_TOUCH_SCROLL:
			Toast.makeText(this, "手指未离开屏幕滑动的过程", Toast.LENGTH_LONG).show();

			break;

		default:
			break;
		}
	}

}
