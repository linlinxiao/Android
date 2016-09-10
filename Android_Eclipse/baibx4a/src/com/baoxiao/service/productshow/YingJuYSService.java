package com.baoxiao.service.productshow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import com.baoxiao.R;
import com.baoxiao.model.Entity;
import com.baoxiao.model.HongLi;
import com.baoxiao.provider.DBDAO;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

public class YingJuYSService {

	
	// 赢聚一生的万能账户的标题变化
		public static String getWanNengZhangHuLevel(String level)
		{
			String zhongShenFenHongLevel = null;
			if (level.equals(Define.Level_Low_display))
			{
				zhongShenFenHongLevel = "万能账户（低等计算）";
			} else if (level.equals(Define.Level_Middle_display))
			{
				zhongShenFenHongLevel = "万能账户（中等计算）";
			} else
			{
				zhongShenFenHongLevel = "万能账户（高等计算）";
			}
			return zhongShenFenHongLevel;

		}
		
	//万能账户值数据
	public Map<String, Double> findHongLi(HongLi hongli) {
		HashMap<String, Double>  map = new HashMap<String, Double>();;
		int age = hongli.getAge();
		Double beginValue = 0.0;
		Double less62 = Arithmetic4Double.multi(
				Arithmetic4Double.multi(hongli.getFenShu(),10000),0.12);
		Double more62 = Arithmetic4Double.multi(
				Arithmetic4Double.multi(hongli.getFenShu(),10000),0.24);
		for (int i = 0; i < 111; i++) {
			int mapAge = i + age + 4;
			if(i==0){
					if(hongli.getLevel().equals(Define.Level_Low)){
						beginValue = Arithmetic4Double.multi(less62,1.0175);
						map.put(String.valueOf(mapAge),beginValue);
					}else if(hongli.getLevel().equals(Define.Level_Middle)){
						beginValue = Arithmetic4Double.multi(less62,1.045);
						map.put(String.valueOf(mapAge),beginValue);
					}else{
						beginValue = Arithmetic4Double.multi(less62,1.06);
						map.put(String.valueOf(mapAge),beginValue);
					}
				}else{
					
				if (mapAge < 62) {
					Double value;
					if(hongli.getLevel().equals(Define.Level_Low)){
						//虚数等于62岁以前的值（低等*1.0175）
						value = Arithmetic4Double.round(
								Arithmetic4Double.multi(
								Arithmetic4Double.add(less62, beginValue),1.0175),0);
					}else if(hongli.getLevel().equals(Define.Level_Middle)){
						//虚数等于62岁以前的值（中等*1.045）
						value = Arithmetic4Double.round(
								Arithmetic4Double.multi(
								Arithmetic4Double.add(less62, beginValue),1.045),0);
					}else{
						//虚数等于62岁以前的值（高等*1.06）
						value = Arithmetic4Double.round(
								Arithmetic4Double.multi(
										Arithmetic4Double.add(less62, beginValue),1.06),0);
					}
					
					beginValue = value;
					map.put(String.valueOf(mapAge),value);
				}else if((i + age + 4) >= 62 && (i + age + 4) <= 105){
					Double value;
					if(hongli.getLevel().equals(Define.Level_Low)){
						//虚数等于62岁以后的值（低等*1.0175）
						value = Arithmetic4Double.round(
								Arithmetic4Double.multi(
								Arithmetic4Double.add(more62, beginValue),1.0175),0);
					}else if(hongli.getLevel().equals(Define.Level_Middle)){
						//虚数等于62岁以后的值（中等*1.045）
						value = Arithmetic4Double.round(
								Arithmetic4Double.multi(
								Arithmetic4Double.add(more62, beginValue),1.045),0);
					}else{
						//虚数等于62岁以后的值（高等*1.06）
						value = Arithmetic4Double.round(
								Arithmetic4Double.multi(
								Arithmetic4Double.add(more62, beginValue),1.06),0);
					}
						beginValue = value;
						map.put(String.valueOf(mapAge),value);
					}else{
						break;
					}
				}
//			Log.d("hongli",
//					"age" + map.getAge() + "," + "hongLi=" + hl.getHongli());
		}
		return map;
	}
	//万能账户值显示数据
	public String getHongLiByAge(Map<String, Double> listMap, int age) {
			String value = null;
			String ageValue = String.valueOf(age);
			for(int i = 0; i < listMap.keySet().size();i++){
				if (listMap.get(ageValue) != null) {
					value = Arithmetic4Double.doubleToString(listMap.get(ageValue));
					break;
				}
			}
			return value;
		}
	//万能账户值列表显示数据
	public String[] findHongLi(Map<String, Double> listMap, int start, int end) {
		ArrayList<String> list = new ArrayList<String>();
		int count = end - start + 1;
//		Set key = listMap.keySet();
		if (listMap.keySet()!=null){
				for(int i = 0; i < count; i++){
			    int age = start + i;
				list.add(age + "岁累计红利：" + listMap.get(String.valueOf(age)) + "元");
				}
			}else{
			}
		return (String[]) list.toArray(new String[0]);
	}
	
	
	//保单现金价值数据
	public ArrayList<HongLi> findBaoDanXianJin(HongLi hongli, Context c, String type) {

		ArrayList<HongLi> list = DBDAO.findBaoDanXianJinJiaZhi(hongli, c, type,Define.tableName_yingJuYiShengHongLi);
		for (HongLi hl : list) {
			double value = 0;
				hl.setAge(hongli.getAge() + hl.getYear());
				value = Arithmetic4Double.div(Double.valueOf(hl.getHongli()), 100);
				value = Arithmetic4Double.multi(value,hongli.getFenShu());
				hl.setHongli(Arithmetic4Double.doubleToString(value));
			Log.d("hongli",
					"age" + hl.getAge() + "," + "hongLi=" + hl.getHongli());
		}

		return list;
	}
	//保单现金价值显示数据
	public String getBaoDanXianJinByAge(ArrayList<HongLi> listMap, int age) {
		String value = "";
		for(HongLi hongli : listMap){
			if(hongli.getAge() == age){
				value = hongli.getHongli();
			}
		}
		return value;
	}
	//保单现金价值列表显示数据
	public String[] findHongLi(ArrayList<HongLi> listMap, int start, int end) {
		ArrayList<String> list = new ArrayList<String>();

		for (HongLi entity : listMap)

			if (entity.getAge() >= start && entity.getAge() <= end) {
				list.add(entity.getAge() + "岁保单现金价值：" + entity.getHongli() + "元");
			}
		return (String[]) list.toArray(new String[0]);
	}
	

	public double findBaoFei(HongLi hongli, Context c, String type,SQLiteDatabase db) {
		double baoFei = 0;
		baoFei = Double.valueOf(DBDAO.findBaoFei(hongli, c, type,db));
		baoFei = Arithmetic4Double.multi(baoFei, hongli.getBaoE());

		return baoFei;
	}

	public double findZhongJiBaoFei(HongLi hongli, Context c, String type,SQLiteDatabase db) {
		double baoFei = 0;
		baoFei = DBDAO.findFuJiaXianBaoFei(hongli, c, type,db);
		baoFei = Arithmetic4Double.multi(baoFei, hongli.getFenShu());

		return baoFei;
	}
	public double findYiWaiBaoFei(HongLi hongli, Context c, String type,SQLiteDatabase db) {
		double baoFei = 0;
		baoFei = DBDAO.findFuJiaXianBaoFei(hongli, c, type,db);
		baoFei = Arithmetic4Double.multi(baoFei, hongli.getFenShu());
		
		return baoFei;
	}

	public double findHuoMianBaoFei(HongLi hongli, Context c, String type,
			double baoFei,SQLiteDatabase db) {
		double huoMianBaoFei = 0;
		huoMianBaoFei = DBDAO.findFuJiaXianBaoFei(hongli, c, type,db);

		huoMianBaoFei = Arithmetic4Double.multi(baoFei, huoMianBaoFei);

		huoMianBaoFei = Arithmetic4Double.div(huoMianBaoFei, 1000, 2);

		return huoMianBaoFei;
	}
	
	public double findhuoMianBaoE(HongLi hongli,Context c, String type
			,String baoE,String zhongJiBaoE,SQLiteDatabase db) {
		double baoFei = 0;
		baoFei = Double.valueOf(DBDAO.findBaoFei(hongli, c, type,db));
		baoFei = Arithmetic4Double.div(baoFei, 35, 2);
		baoFei = Arithmetic4Double.round(
				Arithmetic4Double.div(
				Arithmetic4Double.sub(Double.valueOf(baoE), Double.valueOf(zhongJiBaoE))
				, baoFei), Define.Round2) ;
				
		return baoFei;
	}

	public static int lessCount61 = 0;

	public Map<Integer, String> findFanHuanJin(double baoE, int age) {
		lessCount61 = 61 - (age + 3);
		Map<Integer, String> map = new HashMap<Integer, String>();
		baoE = baoE * 10000;
		String rst = "";
		int c = 72;
		int j = 0;

		for (int i = 0; i < 111; i++) {
			if (i + age + 3 < 61) {
				// lessCount22++;
				map.put(j,
						(i + age + 3)
								+ "岁"
								+ Arithmetic4Double
										.doubleToString(Arithmetic4Double
												.round(Arithmetic4Double.multi(
														baoE, 0.12), 0)));
				rst = rst
						+ i
						+ "岁"
						+ Arithmetic4Double.doubleToString(Arithmetic4Double
								.round(Arithmetic4Double.multi(baoE, 0.12), 0))
						+ " ";
			} else {
				if (i >= 71) {
					map.put(j, "领到老");
					rst = rst + i + "岁" + "领到老" + " ";
					j = j + 1;
					break;
				} else {
					if(i + age + 3==104){
						map.put(j, "领到老");
						rst = rst + i + "岁" + "领到老" + " ";
						j = j + 1;
						break;
					}else{
					map.put(j,
							(i + age + 3)
									+ "岁"
									+ Arithmetic4Double
											.doubleToString(Arithmetic4Double.round(
													Arithmetic4Double.multi(
															Arithmetic4Double.multi(baoE, 0.12),2),
															0)));
					rst = rst
							+ i
							+ "岁"
							+ Arithmetic4Double
									.doubleToString(Arithmetic4Double.round(
											Arithmetic4Double.multi(
											Arithmetic4Double.multi(baoE, 0.12),2),
											0)) + " ";
					}
				}
			}

			j = j + 1;
		}

		return map;

	}

	int j = 0;
	Handler handler;
	public Timer timer1;
	
	/**
	 * 功能：方块的第一部分动画显示
	 * @param fanHuanJinList 方块的TextView的控件初始化的集合
	 * @param map 从数据库获取的要显示在方块上的数据集合
	 * @param ani 方块要显示的动画
	 * @param tv1  左边悬浮按钮
	 * @param tv2  右边悬浮按钮
	 */
	public void showFanHuanJin61(final TextView[] fanHuanJinList,
			Map<Integer, String> map, final Animation ani,
			final TextView tv1, final TextView tv2) {
		final Timer timer = new Timer();
		timer1 = timer;
		j = 0;
		tv1.setEnabled(false);
		tv2.setEnabled(false);
		for (int i = 0; i < map.keySet().size(); i++) {
			if (i < lessCount61) {
				map.get(i);

				fanHuanJinList[i].setText(map.get(i));
			}
		}
			
			handler = new Handler()
			{
				public void handleMessage(Message msg)
				{
					if (msg.what < lessCount61)
					{
							fanHuanJinList[j].setVisibility(View.VISIBLE);
							fanHuanJinList[j].startAnimation(ani);
							j++;
					} else
					{
							timer.cancel();
							tv1.setEnabled(true);
							tv2.setEnabled(true);
					}
				};
			};
			
			timer.schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					handler.sendEmptyMessage(j);
					
				}
			}, 0, 600);
	}

	/**
	 * 功能：方块的第二部分动画显示
	 * @param fanHuanJinList 方块的TextView的控件初始化的集合
	 * @param map 从数据库获取的要显示在方块上的数据集合
	 * @param ani 方块要显示的动画
	 * @param tv1  左边悬浮按钮
	 * @param tv2  右边悬浮按钮
	 */
	public void showFanHuanJinMore61(final TextView[] fanHuanJinList,
			final Map<Integer, String> map, final Animation ani,
			final TextView tv1, final TextView tv2) {
		final Timer timer = new Timer();
		timer1 = timer;
		j = lessCount61;
		tv1.setEnabled(false);
		tv2.setEnabled(false);
		for (int i = 0; i < map.keySet().size(); i++) {
			if (i >= lessCount61) {
				map.get(i);
				fanHuanJinList[i].setText(map.get(i));
				fanHuanJinList[i].setBackgroundResource(R.drawable.fangkuai2);
			}
		}
		
		handler = new Handler()
		{
			public void handleMessage(Message msg)
			{
					if (msg.what >= lessCount61&&j<map.keySet().size())
					{
						fanHuanJinList[j].setVisibility(View.VISIBLE);
						fanHuanJinList[j].startAnimation(ani);
						j++;
					} 
					else
					{
						timer.cancel();
						tv1.setEnabled(true);
						tv2.setEnabled(true);
					}
			};
		};
		
		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				handler.sendEmptyMessage(j);
				
			}
		}, 0, 600);
		
	}
	
	public void hideFanHuanJinMore61(final TextView[] fanHuanJinList,
			final Map<Integer, String> map, final int visible)
	{
		for (int k = lessCount61; k < fanHuanJinList.length; k++)
		{
			fanHuanJinList[k].setVisibility(View.INVISIBLE);
		}
	}

	public ArrayList<Entity> findLeiJiFanHuanJin(double baoE, int age) {
		ArrayList<Entity> list = new ArrayList<Entity>();
		baoE = baoE * 10000;
		String rst = "";
		int c = 72;
		double value = 0;
		double total = 0;
		for (int i = age + 3; i <= 105; i++) {
			Entity entity = new Entity();
			if (i <= 22) {
				value = Arithmetic4Double.multi(baoE, 0.12);
				total = value
						+ Arithmetic4Double.multi((value + total), Define.LiLv)
						+ total;
				total = Arithmetic4Double.round(total, 0);
				entity.setAge(i);
				entity.setValue(Arithmetic4Double.doubleToString(total));
				list.add(entity);
				rst = rst + i + "岁" + Arithmetic4Double.doubleToString(total)
						+ " ";
			} else {

				value = Arithmetic4Double.multi(baoE, 0.06);
				total = value
						+ Arithmetic4Double.multi((value + total), Define.LiLv)
						+ total;
				total = Arithmetic4Double.round(total, 0);
				entity.setAge(i);
				entity.setValue(Arithmetic4Double.doubleToString(total));
				list.add(entity);
				rst = rst + i + "岁" + Arithmetic4Double.doubleToString(total)
						+ " ";
			}
		}

		return list;

	}

	public String[] findLeiJiFanHuanJin(ArrayList<Entity> listMap, int start,
			int end) {
		ArrayList<String> list = new ArrayList<String>();

		for (Entity entity : listMap)

			if (entity.getAge() >= start && entity.getAge() <= end) {
				list.add(entity.getAge() + "岁累计生存金：" + entity.getValue() + "元");
			}

		// String[] array=new String[list.size()];
		// for(int j=0;j<list.size();j++){
		// array[j]=list.get(j);
		// }

		return (String[]) list.toArray(new String[0]);
		// return array;
	}

	public String getLeiJinFanHuanByAge(ArrayList<Entity> listMap, int age) {
		String value = "";
		for (Entity entity : listMap)

			if (entity.getAge() == age) {
				value = entity.getValue();
				break;
			}

		return value;
	}

	public static String getLevel(String level) {
		String grade = "";
		if (level.equals("low")) {
			grade = "低";
		} else if (level.equals("middle")) {
			grade = "中";
		} else {
			grade = "高";
		}
		return grade;

	}

	public void findFanHuanJin(double baoE, int age, Context context) {
		baoE = baoE * 10000;
		String rst = "";
		int c = 72;
		for (int i = age + 3; i < 111; i++) {
			if (i <= 22) {
				rst = rst
						+ i
						+ "岁"
						+ Arithmetic4Double.doubleToString(Arithmetic4Double
								.round(Arithmetic4Double.multi(baoE, 0.12), 0))
						+ " ";
			} else {
				if ((i - 3) >= 72) {
					rst = rst + i + "岁" + "活到老领到老" + " ";
					break;
				} else {
					rst = rst
							+ i
							+ "岁"
							+ Arithmetic4Double
									.doubleToString(Arithmetic4Double.round(
											Arithmetic4Double.multi(baoE, 0.06),
											0)) + " ";
				}
			}
		}

		// return rst;

	}

	public static String getZhongShenFenHongLevel(String level) {
		String zhongShenFenHongLevel = null;
		if (level.equals(Define.Level_Low_display)) {
			zhongShenFenHongLevel = "万能账户(低等计算)";
		} else if (level.equals(Define.Level_Middle_display)) {
			zhongShenFenHongLevel = "万能账户(中等计算)";
		} else {
			zhongShenFenHongLevel = "万能账户(高等计算)";
		}
		return zhongShenFenHongLevel;

	}

	// 世纪天使返还金
	public ArrayList<Entity> findFanHuanJin4ShiJiTS(double baoE, int age) {

		ArrayList<Entity> list = new ArrayList<Entity>();
		baoE = baoE * 10000;
		int ageDisplay = 0;

		int count = 0;
		count = (int) Math.round((103 - age) / 3);
		if (count >= 33) {
			count = 33;
		} else {

		}

		for (int i = 1; i <= count; i++) {
			Entity entity = new Entity();
			// if(ageDisplay>=103){
			// break;
			// }
			// if(i>33 ){
			//
			// if(i==34){
			// ageDisplay=ageDisplay+3;
			// entity.setAge(ageDisplay);
			// entity.setValue(".....");
			// list.add(entity );
			// }else if(i==35){
			// ageDisplay=ageDisplay+3;
			// entity.setAge(ageDisplay);
			// entity.setValue("领到老");
			// list.add(entity );
			// }else{
			// break;
			// }

			if (i == 1) {
				ageDisplay = ageDisplay + age + 3;
				entity.setAge(ageDisplay);
				entity.setValue(String.valueOf(Arithmetic4Double.round(
						Arithmetic4Double.multi(baoE, 0.12), Define.Round2)));
				list.add(entity);

			} else {
				ageDisplay = ageDisplay + 3;
				entity.setAge(ageDisplay);
				entity.setValue(String.valueOf(Arithmetic4Double.round(
						Arithmetic4Double.multi(baoE, 0.12), Define.Round2)));
				list.add(entity);

			}

			// }

		}

		ageDisplay = ageDisplay + 3;
		Entity entity1 = new Entity();
		entity1.setAge(ageDisplay);
		entity1.setValue(".....");
		list.add(entity1);

		ageDisplay = ageDisplay + 3;
		Entity entity2 = new Entity();
		entity2.setAge(ageDisplay);
		entity2.setValue("领到老");
		list.add(entity2);

		return list;

	}


	
	//世纪天使返还金显示
		public void showShiJiTSFanHuanJin(TextView[] fanHuanJinList,
				ArrayList<Entity> list, Animation ani, int visible) {
			for (int i = 0; i < list.size(); i++) {
//	              for(Entity entity : list){
				
				if(i>list.size()-3){
					fanHuanJinList[i].setText(list.get(i).getValue());
					
				}else{
					fanHuanJinList[i].setText(list.get(i).getAge()+"岁"+Arithmetic4Double.doubleToString(Double.valueOf(list.get(i).getValue()))+"元");
				}
					fanHuanJinList[i].setVisibility(visible);
					fanHuanJinList[i].setBackgroundColor(Color
							.parseColor("#FF6900"));
//	              }
					if (visible == View.VISIBLE) {
						fanHuanJinList[i].startAnimation(ani);
				}
			}
		}

	


	
	
// 赢聚一生返还金
public ArrayList<Entity> findFanHuanJin4YingJuYiSheng(double baoE, int age) {
	
	ArrayList<Entity> list = new ArrayList<Entity>();
	baoE = baoE * 10000;
	int ageDisplay = 0;
	double rst=0.0;
	rst=Arithmetic4Double.round(
			Arithmetic4Double.multi(baoE, 0.12), Define.Round2);
	int count = 0;
	count = (int) Math.round(105 - age);
	if (count >= 105) {
		count = 105;
	} else {
		
	}
	
	for (int i = 1; i <= count; i++) {
		Entity entity = new Entity();
		
		if (i == 1) {
			ageDisplay = ageDisplay + age + 2;
			entity.setAge(ageDisplay);
			entity.setValue(String.valueOf(rst));
			list.add(entity);
			
		} else {
			ageDisplay = ageDisplay + 1;
//			处理格子不填满80岁问题
			
			entity.setAge(ageDisplay);
			if(ageDisplay>=62){
				entity.setValue(String.valueOf(Arithmetic4Double.multi(rst, 2)));
			}else{
				entity.setValue(String.valueOf(rst));
			}
			list.add(entity);
			
		}
		
		// }
		
	}
//	 if(ageDisplay==79){
//	Entity entity1 = new Entity();
//	entity1.setAge(80);
//	entity1.setValue(String.valueOf(Arithmetic4Double.multi(baoE, 2)));
//	list.add(entity1);
//	}
	
	 ageDisplay = ageDisplay + 1;
	 Entity entity2 = new Entity();
	 entity2.setAge(ageDisplay);
	 entity2.setValue("领到老");
	 list.add(entity2);
	
	
	return list;
	
}

//赢聚一生显示返还金
public void showYingJuYiShengFanHuanJin(TextView[] fanHuanJinList,
		ArrayList<Entity> list, Animation ani, int visible) {
//	for (int i = 0; i < list.size(); i++) {
    for (int i = 0; i < 72; i++) {
              for(Entity entity : list){
		if(i>list.size()-1){
			fanHuanJinList[i].setText(list.get(i).getValue());
		}else{
			fanHuanJinList[i].setText(list.get(i).getAge()+"岁"+Arithmetic4Double.doubleToString(Double.valueOf(list.get(i).getValue()))+"元");
		}
		fanHuanJinList[i].setVisibility(visible);
		fanHuanJinList[i].setBackgroundColor(Color
				.parseColor("#FF6900"));
              }
		if (visible == View.VISIBLE) {
			fanHuanJinList[i].startAnimation(ani);
		}
	}
}
}
