package com.example.gridview;

import java.util.ArrayList;
import java.util.HashMap;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{

	private GridView gridView;
	private ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
	private SimpleAdapter adapter;
	private String[] names = { "product1", "product2", "product3", "product4",
			"product5", "product6", "product7", "product8" };
	private Object[] icons = { R.drawable.product1, R.drawable.product2,
			R.drawable.product3, R.drawable.product4, R.drawable.product5,
			R.drawable.product6, R.drawable.product7, R.drawable.product8 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getDataList();

		initViews();
	}

	private void initViews() {
		adapter = new SimpleAdapter(this, dataList, R.layout.item,
				new String[] { "image", "name" }, new int[] { R.id.imageView1,
						R.id.textView1 });

		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);

	}

	private ArrayList<HashMap<String, Object>> getDataList() {
		for (int i = 0; i < names.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", icons[i]);
			map.put("name", names[i]);
			dataList.add(map);
		}
		return dataList;
	}

	@Override
	public void onItemClick(AdapterView<?> gridview, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Toast.makeText(this, gridView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
	}
}
