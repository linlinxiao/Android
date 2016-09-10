package com.baoxiao.test.sqlite.main;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.baoxiao.R;
import com.baoxiao.model.HongLi;
import com.baoxiao.provider.DBDAO;

public class TestHongliSqlite extends Activity {

	/**
	 * 测试sqlite数据库是否成功接受数据
	 * 
	 * @param args
	 */
	private DBDAO sqliteDb = null;
	private HongLi hongLi = null;
	private ArrayList<HongLi> arrayHongLi = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlitemain);
		hongLi = new HongLi();
		sqliteDb = new DBDAO();
//		arrayHongLi = sqliteDb.findHongli(hongLi, this);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = null;
		for (HongLi hongLi : arrayHongLi) {
			// map = new HashMap<String, Object>();
			// map.put("sex", hongLi.getSex());
			// map.put("age", hongLi.getAge());
			// map.put("payperoid", hongLi.getPayperoid());
			// map.put("year", hongLi.getYear());
			// map.put("level", hongLi.getLevel());
			// map.put("hongli", hongLi.getHongli());
			// map.put("type", hongLi.getType());
			// map.put("id", hongLi.getId());
			// list.add(map);

			Toast.makeText(getApplicationContext(), hongLi.getAge(),
					Toast.LENGTH_SHORT).show();
		}
	}
}