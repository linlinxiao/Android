package com.baoxiao.service.productshow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.baoxiao.R;
import com.baoxiao.model.Entity;
import com.baoxiao.model.HongLi;
import com.baoxiao.provider.DBDAO;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;

public class ShouHuXService
{

	/**
	 * 守护星获取数据库中所有红利的数值
	 * @param hongli
	 * @param c
	 * @param type
	 * @return
	 */
	public ArrayList<HongLi> findHongLi(HongLi hongli, Context c, String type)
	{

		ArrayList<HongLi> list = DBDAO.findHongli(hongli, c, type,
				Define.tableName_hongLi);

		for (HongLi hl : list)
		{
			double value = 0;

			// hl.setAge(hl.getYear());
			hl.setAge(hongli.getAge() + hl.getYear());
			value = Arithmetic4Double.div(Double.valueOf(hl.getHongli()), 35);
			value = value * hongli.getFenShu();
			hl.setHongli(Arithmetic4Double.doubleToString(value));
			Log.d("hongli",
					"age" + hl.getAge() + "," + "hongLi=" + hl.getHongli());

		}

		return list;
	}
	
	
	//世纪天使红利的查询
	public ArrayList<HongLi> findHongLi4ShiJiTianShi(HongLi hongli, Context c, String type)
	{

		ArrayList<HongLi> list = DBDAO.findHongli(hongli, c, type,
				Define.tableName_hongLi);

		for (HongLi hl : list)
		{
			double value = 0;

			// hl.setAge(hl.getYear());
			hl.setAge(hongli.getAge() + hl.getYear());
			value = Arithmetic4Double.div(Double.valueOf(hl.getHongli()), 15);
			value = value * hongli.getFenShu();
			hl.setHongli(Arithmetic4Double.doubleToString(value));
			Log.d("hongli",
					"age" + hl.getAge() + "," + "hongLi=" + hl.getHongli());

		}

		return list;
	}

	/**
	 * 获取年龄段内的红利数值
	 * @param listMap
	 * @param start
	 * @param end
	 * @return
	 */
	public String[] findHongLi(ArrayList<HongLi> listMap, int start, int end)
	{
		ArrayList<String> list = new ArrayList<String>();

		for (HongLi entity : listMap)

			if (entity.getAge() >= start && entity.getAge() <= end)
			{
				list.add(entity.getAge() + "岁累计红利：" + entity.getHongli() + "元");
			}

		return (String[]) list.toArray(new String[0]);
	}

	/**
	 * 获取年龄相对应的红利数值
	 * @param listMap
	 * @param age
	 * @return
	 */
	public String getHongLiByAge(ArrayList<HongLi> listMap, int age)
	{
		String value = "";
		for (HongLi entity : listMap)

			if (entity.getAge() == age)
			{
				value = entity.getHongli();
				break;
			}

		return value;
	}

	/**
	 * 计算主险保费（守护星，世纪天使，鑫利同用此方法）
	 * @param hongli
	 * @param c
	 * @param type
	 * @return
	 */
	public double findBaoFei(HongLi hongli, Context c, String type,	SQLiteDatabase db)
	{
		double baoFei = 0;
		String bf = DBDAO.findBaoFei(hongli, c, type, db);
		if ("".equals(bf)) {bf = "0";}
		baoFei = Double.valueOf(bf);
		// 世纪天使1万保额
		if (Define.DB_Type_BaoFei_ShiJiTianShi.equals(type)
				|| Define.DB_Type_BaoFei_XinLi.equals(type))
		{

		} else
		{
			baoFei = Arithmetic4Double.div(baoFei, 35, 2);
		}
		baoFei = Arithmetic4Double.multi(baoFei, hongli.getBaoE());

		return baoFei;
	}

	/**
	 * 计算重疾保费
	 * @param hongli
	 * @param c
	 * @param type
	 * @return
	 */
	public double findZhongJiBaoFei(HongLi hongli, Context c, String type,SQLiteDatabase db)
	{
		double baoFei = 0;
		baoFei = DBDAO.findFuJiaXianBaoFei(hongli, c, type, db);
		baoFei = Arithmetic4Double.multi(baoFei, hongli.getFenShu());

		return baoFei;
	}

	/**
	 * 计算豁免保费
	 * @param hongli
	 * @param c
	 * @param type
	 * @param baoFei
	 * @return
	 */
	public double findHuoMianBaoFei(HongLi hongli, Context c, String type,
			double baoFei,SQLiteDatabase db)
	{
		double huoMianBaoFei = 0;
		huoMianBaoFei = DBDAO.findFuJiaXianBaoFei(hongli, c, type,db);

		huoMianBaoFei = Arithmetic4Double.multi(baoFei, huoMianBaoFei);

		huoMianBaoFei = Arithmetic4Double.div(huoMianBaoFei, 1000, 2);

		return huoMianBaoFei;
	}

	public static int lessCount22 = 0;

	/**
	 * 获取方块内要显示的值
	 * @param baoE
	 * @param age
	 * @return
	 */
	public Map<Integer, String> findFanHuanJin(double baoE, int age)
	{
		lessCount22 = (22 - (age + 3)) + 1;
		Map<Integer, String> map = new HashMap<Integer, String>();
		baoE = baoE * 10000;
		String rst = "";
		int j = 0;

		for (int i = 0; i < 111; i++)
		{
			if (i + age + 3 <= 22)
			{
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
			} else
			{
				if (i >= 71)
				{
					map.put(j, "领到老");
					rst = rst + i + "岁" + "领到老" + " ";
					j = j + 1;
					break;
				} else
				{
					map.put(j,
							(i + age + 3)
									+ "岁"
									+ Arithmetic4Double
											.doubleToString(Arithmetic4Double
													.round(Arithmetic4Double
															.multi(baoE, 0.06),
															0)));
					rst = rst
							+ i
							+ "岁"
							+ Arithmetic4Double
									.doubleToString(Arithmetic4Double.round(
											Arithmetic4Double.multi(baoE, 0.06),
											0)) + " ";
				}
			}

			j = j + 1;
		}

		return map;

	}

	/**
	 * 显示方块内小于22岁要显示的值
	 * @param fanHuanJinList
	 * @param map
	 * @param ani
	 * @param visible
	 * @param tv1
	 * @param tv2
	 */
	public void showFanHuanJin22(final TextView[] fanHuanJinList,
			Map<Integer, String> map, final Animation ani, final int visible,
			final TextView tv1, final TextView tv2)
	{
		final Timer timer = new Timer();
		timer1 = timer;
		j = 0;
		tv1.setEnabled(false);
		tv2.setEnabled(false);
		for (int i = 0; i < map.keySet().size(); i++)
		{
			if (i < lessCount22)
			{
				map.get(i);

				fanHuanJinList[i].setText(map.get(i));
			}
		}
		
		handler = new Handler()
		{
			public void handleMessage(Message msg)
			{
				if (msg.what < lessCount22)
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
	 * 显示方块内大于22岁要显示的值
	 * @param fanHuanJinList
	 * @param map
	 * @param ani
	 * @param visible
	 * @param tv1
	 * @param tv2
	 */
	public void showFanHuanJinMore22(final TextView[] fanHuanJinList,
			final Map<Integer, String> map, final Animation ani, final int visible,
			final TextView tv1, final TextView tv2)
	{
		
		final Timer timer = new Timer();
		timer1 = timer;
		j = lessCount22;
		tv1.setEnabled(false);
		tv2.setEnabled(false);
		for (int i = lessCount22; i < map.keySet().size(); i++)
		{
			if (i < map.keySet().size())
			{
				map.get(i);
				fanHuanJinList[i].setText(map.get(i));
				fanHuanJinList[i].setBackgroundResource(R.drawable.fangkuai2);
			}
		}
		
		handler = new Handler()
		{
			public void handleMessage(Message msg)
			{
					if (msg.what >= lessCount22&&j<map.keySet().size())
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
	
	/**
	 * 隐藏方块内大于22岁要显示的值
	 * @param fanHuanJinList
	 * @param map
	 * @param visible
	 */
	public void hideFanHuanJinMore22(final TextView[] fanHuanJinList,
			final Map<Integer, String> map, final int visible)
	{
		for (int k = lessCount22; k < fanHuanJinList.length; k++)
		{
			fanHuanJinList[k].setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * 计算生存累积生存金的数值
	 * @param baoE
	 * @param age
	 * @return
	 */
	public ArrayList<Entity> findLeiJiFanHuanJin(double baoE, int age)
	{
		ArrayList<Entity> list = new ArrayList<Entity>();
		baoE = baoE * 10000;
		String rst = "";
		double value = 0;
		double total = 0;
		for (int i = age + 3; i <= 105; i++)
		{
			Entity entity = new Entity();
			if (i <= 22)
			{
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
			} else
			{
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
			int end)
	{
		ArrayList<String> list = new ArrayList<String>();

		for (Entity entity : listMap)

			if (entity.getAge() >= start && entity.getAge() <= end)
			{
				list.add(entity.getAge() + "岁累计生存金：" + entity.getValue() + "元");
			}

		return (String[]) list.toArray(new String[0]);
	}

	public String getLeiJinFanHuanByAge(ArrayList<Entity> listMap, int age)
	{
		String value = "";
		for (Entity entity : listMap)

			if (entity.getAge() == age)
			{
				value = entity.getValue();
				break;
			}

		return value;
	}

	public static String getLevel(String level)
	{
		String grade = "";
		if (level.equals("low"))
		{
			grade = "低";
		} else if (level.equals("middle"))
		{
			grade = "中";
		} else
		{
			grade = "高";
		}
		return grade;

	}

	public void findFanHuanJin(double baoE, int age, Context context)
	{
		baoE = baoE * 10000;
		String rst = "";
		int c = 72;
		for (int i = age + 3; i < 111; i++)
		{
			if (i <= 22)
			{
				rst = rst
						+ i
						+ "岁"
						+ Arithmetic4Double.doubleToString(Arithmetic4Double
								.round(Arithmetic4Double.multi(baoE, 0.12), 0))
						+ " ";
			} else
			{
				if ((i - 3) >= 72)
				{
					rst = rst + i + "岁" + "活到老领到老" + " ";
					break;
				} else
				{
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
	}

	public static String getZhongShenFenHongLevel(String level)
	{
		String zhongShenFenHongLevel = null;
		if (level.equals(Define.Level_Low_display))
		{
			zhongShenFenHongLevel = "保单累积分红账户(低等计算)";
		} else if (level.equals(Define.Level_Middle_display))
		{
			zhongShenFenHongLevel = "保单累积分红账户(中等计算)";
		} else
		{
			zhongShenFenHongLevel = "保单累积分红账户(高等计算)";
		}
		return zhongShenFenHongLevel;

	}

	// 世纪天使返还金
	public ArrayList<Entity> findFanHuanJin4ShiJiTS(double baoE, int age)
	{

		ArrayList<Entity> list = new ArrayList<Entity>();
		baoE = baoE * 10000;
		int ageDisplay = 0;

		int count = 0;
		count = (int) Math.round((103 - age) / 3);
		if (count >= 33)
		{
			count = 33;
		} else
		{

		}

		for (int i = 1; i <= count; i++)
		{
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

			if (i == 1)
			{
				ageDisplay = ageDisplay + age + 3;
				entity.setAge(ageDisplay);
				entity.setValue(String.valueOf(Arithmetic4Double.round(
						Arithmetic4Double.multi(baoE, 0.12), Define.Round2)));
				list.add(entity);

			} else
			{
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

	int j = 0;
	Handler handler;
	public Timer timer1;

	/**
	 * 世纪天使返还金显示
	 * @param fanHuanJinList
	 * @param list
	 * @param ani
	 * @param visible
	 * @param tv1
	 * @param tv2
	 */
	public void showShiJiTSFanHuanJin(final TextView[] fanHuanJinList,
			ArrayList<Entity> list, final Animation ani, int visible,
			final TextView tv1, final TextView tv2)
	{
		final Timer timer = new Timer();
		timer1 = timer;
		j = 0;
		tv1.setEnabled(false);
		tv2.setEnabled(false);
		for (int i = 0; i < list.size(); i++)
		{
			if (i > list.size() - 3)
			{
				fanHuanJinList[i].setText(list.get(i).getValue());
			} else
			{
				fanHuanJinList[i].setText(list.get(i).getAge()
						+ "岁"
						+ Arithmetic4Double.doubleToString(Double.valueOf(list
								.get(i).getValue())));
			}
		}

		handler = new Handler()
		{
			public void handleMessage(Message msg)
			{
				if (msg.what < fanHuanJinList.length)
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

	// 世纪天使累计返还金

	public static ArrayList<Entity> findLeiJiFanHuanJin4ShiJiTS(double baoE,
			int age)
	{
		ArrayList<Entity> list = new ArrayList<Entity>();
		baoE = baoE * 10000;
		double begMoney = 0.0;
		double returnMoney = 0.0;
		double endMoney = 0.0;
		int displayAge = 0;
		for (int i = 0; i <= 103 - age; i++)
		{
			displayAge = age + i - 1;
			if (i <= 3)
			{

			} else
			{
				if ((i - 1) % 3 == 0)
				{
					returnMoney = Arithmetic4Double.multi(baoE, 0.12);
				} else
				{
					returnMoney = 0.0;
				}

				begMoney = Arithmetic4Double.round(
						Arithmetic4Double.add(returnMoney, endMoney),
						Define.Round2);
				endMoney = Arithmetic4Double.multi(begMoney, 1.03);

				Entity entity = new Entity();
				entity.setAge(displayAge);
				entity.setValue((Arithmetic4Double.doubleToString(begMoney)));
				list.add(entity);
			}

		}

		// int count=0;

		return list;
	}

	// 鑫利返还金
	public ArrayList<Entity> findFanHuanJin4XinLi(double baoE, int age)
	{

		ArrayList<Entity> list = new ArrayList<Entity>();
		baoE = baoE * 10000;
		int ageDisplay = 0;
		double rst = 0.0;
		rst = Arithmetic4Double.round(Arithmetic4Double.multi(baoE, 0.07),
				Define.Round2);
		int count = 0;
		count = (int) Math.round((80 - age) / 2);
		if (count >= 40)
		{
			count = 40;
		} else
		{

		}

		for (int i = 1; i <= count; i++)
		{
			Entity entity = new Entity();

			if (i == 1)
			{
				ageDisplay = ageDisplay + age + 2;
				entity.setAge(ageDisplay);
				entity.setValue(String.valueOf(rst));
				list.add(entity);

			} else
			{
				ageDisplay = ageDisplay + 2;
				// 处理格子不填满80岁问题

				entity.setAge(ageDisplay);
				entity.setValue(String.valueOf(rst));
				// if(ageDisplay==80){
				// //
				// entity.setValue(String.valueOf(Arithmetic4Double.multi(baoE,
				// 2)+rst));
				// }else{
				// entity.setValue(String.valueOf(rst));
				// }
				list.add(entity);

			}

			// }

		}
		if (ageDisplay == 79)
		{
			Entity entity1 = new Entity();
			entity1.setAge(80);
			entity1.setValue(String.valueOf(Arithmetic4Double.multi(baoE, 2)));
			list.add(entity1);
		}

		return list;

	}

	// 鑫利显示返还金
	public void showXinLiFanHuanJin(final TextView[] fanHuanJinList,
			ArrayList<Entity> list, final Animation ani,
			final TextView tv1, final TextView tv2)
	{
		final Timer timer = new Timer();
		timer1 = timer;
		j = 0;
		tv1.setEnabled(false);
		tv2.setEnabled(false);
		for (int i = 0; i < list.size(); i++)
		{
			if (i == list.size() - 1)
			{
				fanHuanJinList[i].setText(list.get(i).getAge()
						+ "岁"
						+ Arithmetic4Double.doubleToString(Double.valueOf(list
								.get(i).getValue())));
			} else
			{
				fanHuanJinList[i].setText(list.get(i).getAge()
						+ "岁"
						+ Arithmetic4Double.doubleToString(Double.valueOf(list
								.get(i).getValue())));
			}
		}
		
		handler = new Handler()
		{
			public void handleMessage(Message msg)
			{
				if (msg.what < fanHuanJinList.length)
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

	// 鑫利累计返还金

	public static ArrayList<Entity> findLeiJiFanHuanJin4XinLi(double baoE,
			int age)
	{
		ArrayList<Entity> list = new ArrayList<Entity>();
		baoE = baoE * 10000;
		double begMoney = 0.0;
		double returnMoney = 0.0;
		double endMoney = 0.0;
		int displayAge = 0;
		for (int i = 0; i <= (80 - age) + 1; i++)
		{
			displayAge = age + i - 1;
			// displayAge=age+i;
			if (i <= 2)
			{
				// if(i<2){

			} else
			{

				if ((i - 1) % 2 == 0)
				{

					returnMoney = Arithmetic4Double.multi(baoE, 0.07);
					// if(displayAge==78){
					// System.out.println("test");
					// }
					if (displayAge == 80)
					{
						returnMoney = Arithmetic4Double.add(returnMoney,
								Arithmetic4Double.multi(baoE, 2));
					}

				} else
				{
					if (displayAge == 80)
					{
						returnMoney = Arithmetic4Double.multi(baoE, 2);
					} else
					{
						returnMoney = 0.0;
					}
				}

				begMoney = Arithmetic4Double.round(
						Arithmetic4Double.add(returnMoney, endMoney),
						Define.Round2);
				endMoney = Arithmetic4Double.multi(begMoney, Define.totalLiLv);

				Entity entity = new Entity();
				entity.setAge(displayAge);
				entity.setValue((Arithmetic4Double.doubleToString(begMoney)));
				list.add(entity);
			}
			
			
			

		}

		// 80岁返还2*保额
		// Entity entity=new Entity();
		// entity.setAge(80);
		// entity.setValue((Arithmetic4Double.doubleToString(Arithmetic4Double.add(endMoney,
		// Arithmetic4Double.multi(baoE, 2)))));
		// list.add(entity);
		//
		

//		比参保人年龄小不显示
		for (Entity entity : list){

			if (entity.getAge() < age)
			{
				entity.setValue("");
				break;
			}
		}

		return list;
	}
}
