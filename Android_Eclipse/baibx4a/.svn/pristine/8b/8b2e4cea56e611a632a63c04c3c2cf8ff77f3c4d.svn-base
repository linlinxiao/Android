package com.baoxiao.activity.homepage;

import java.util.ArrayList;
import java.util.List;

import com.baoxiao.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class SynListViewImageActivity extends Activity {
	/** Called when the activity is first created. */
	public List<String> URL;
	ListView lv;
	Myadapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_picture);
		URL = new ArrayList<String>();
		addurl();
		lv = (ListView) findViewById(R.id.lv_list_picture);
		adapter = new Myadapter(this, URL,lv);
		lv.setAdapter(adapter);

	}

	public void addurl() {
		URL.add("http://www.bbxapp.com/images/recruit/recruitment_1.png");
		URL.add("http://www.bbxapp.com/images/recruit/recruitment_2.png");
		URL.add("http://www.bbxapp.com/images/recruit/recruitment_3.png");
		URL.add("http://www.bbxapp.com/images/recruit/recruitment_4.png");
		URL.add("http://www.bbxapp.com/images/recruit/recruitment_5.png");
		URL.add("http://www.bbxapp.com/images/recruit/recruitment_6.png");
		URL.add("http://www.bbxapp.com/images/recruit/recruitment_7.png");
	}
}