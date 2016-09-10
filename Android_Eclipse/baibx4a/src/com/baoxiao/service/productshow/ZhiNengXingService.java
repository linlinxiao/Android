package com.baoxiao.service.productshow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import com.baoxiao.model.Entity;
import com.baoxiao.model.HongLi;
import com.baoxiao.provider.DBDAO;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

public class ZhiNengXingService {
	// 计算尊御人生两全保险的保费
	public Double findBaoFei(HongLi hongli, Context c, String type,SQLiteDatabase db) {
		double baoFei = 0;
		baoFei = Double.valueOf(DBDAO.findBaoFei(hongli, c, type,db));
		baoFei = Arithmetic4Double.multi(baoFei, hongli.getBaoE());
		return baoFei;
	}

	// 聚财宝万能账户的标题变化
	public static String getWanNengZhangHuLevel(String level) {
		String zhongShenFenHongLevel = null;
		if (level.equals(Define.Level_Low_display)) {
			zhongShenFenHongLevel = "保单现金价值账户（低等计算））";
		} else if (level.equals(Define.Level_Middle_display)) {
			zhongShenFenHongLevel = "保单现金价值账户（中等计算）";
		} else {
			zhongShenFenHongLevel = "保单现金价值账户（高等计算）";
		}
		return zhongShenFenHongLevel;

	}

	// // 聚财宝万能账户值数据
	// public ArrayList<HongLi> findHongLi(HongLi hongli, Context c, String
	// type) {
	// ArrayList<HongLi> list = DBDAO.findHongli(hongli, c, type,
	// Define.tableName_zyrsHongLi);
	//
	// for (HongLi hl : list) {
	// double value = 0;
	// hl.setAge(hongli.getAge() + hl.getYear());
	// value = Arithmetic4Double.div(Double.valueOf(hl.getHongli()), 100);
	// value = value * hongli.getFenShu();
	// hl.setHongli(Arithmetic4Double.doubleToString(value));
	// Log.d("hongli",
	// "age" + hl.getAge() + "," + "hongLi=" + hl.getHongli());
	// }
	// return list;
	// }

	// 聚财宝万能账户值显示数据
	public String getHongLiByAge(ArrayList<HongLi> listMap, int age) {
		String value = "";
		for (HongLi entity : listMap) {

			if (entity.getAge() == age) {
				value = entity.getHongli();
				break;
			}
		}
		return value;
	}

	// 聚财宝万能账户列表显示数据
	public static String[] findHongLi(ArrayList<HongLi> listMap, int start,
			int end) {
		ArrayList<String> list = new ArrayList<String>();

		for (HongLi entity : listMap)

			if (entity.getAge() >= start && entity.getAge() <= end) {
				list.add(entity.getAge() + "岁账户累积：" + entity.getHongli() + "元");
			}
		return (String[]) list.toArray(new String[0]);
	}

	public static int lessCount61 = 0;

	// 尊御人生方块中的数值的第一部分
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
												.multi(baoE, 0.15)));
				rst = rst
						+ i
						+ "岁"
						+ Arithmetic4Double.doubleToString(Arithmetic4Double
								.multi(baoE, 0.15)) + " ";
			} else {
				if (i >= 71) {
					map.put(j, "领到老");
					rst = rst + i + "岁" + "领到老" + " ";
					j = j + 1;
					break;
				} else {
					if (i + age + 3 == 104) {
						map.put(j, "领到老");
						rst = rst + i + "岁" + "领到老" + " ";
						j = j + 1;
						break;
					} else {
						map.put(j,
								(i + age + 3)
										+ "岁"
										+ Arithmetic4Double
												.doubleToString(Arithmetic4Double
														.multi(baoE, 0.18)));
						rst = rst
								+ i
								+ "岁"
								+ Arithmetic4Double
										.doubleToString(Arithmetic4Double
												.multi(baoE, 0.18)) + " ";
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
	 * 功能：尊御人生方块的第一部分动画显示
	 * 
	 * @param fanHuanJinList
	 *            方块的TextView的控件初始化的集合
	 * @param map
	 *            从数据库获取的要显示在方块上的数据集合
	 * @param ani
	 *            方块要显示的动画
	 * @param tv1
	 *            左边悬浮按钮
	 * @param tv2
	 *            右边悬浮按钮
	 */
	public void showFanHuanJin61(final TextView[] fanHuanJinList,
			Map<Integer, String> map, final Animation ani, final TextView tv1,
			final TextView tv2) {
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

		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what < lessCount61) {
					fanHuanJinList[j].setVisibility(View.VISIBLE);
					fanHuanJinList[j].startAnimation(ani);
					j++;
				} else {
					timer.cancel();
					tv1.setEnabled(true);
					tv2.setEnabled(true);
				}
			};
		};

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(j);

			}
		}, 0, 600);
	}

	/**
	 * 功能：尊御人生方块的第二部分动画显示
	 * 
	 * @param fanHuanJinList
	 *            方块的TextView的控件初始化的集合
	 * @param map
	 *            从数据库获取的要显示在方块上的数据集合
	 * @param ani
	 *            方块要显示的动画
	 * @param tv1
	 *            左边悬浮按钮
	 * @param tv2
	 *            右边悬浮按钮
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
			}
		}

		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what >= lessCount61 && j < map.keySet().size()) {
					fanHuanJinList[j].setVisibility(View.VISIBLE);
					fanHuanJinList[j].startAnimation(ani);
					j++;
				} else {
					timer.cancel();
					tv1.setEnabled(true);
					tv2.setEnabled(true);
				}
			};
		};

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(j);

			}
		}, 0, 600);

	}

	// 尊御人生的第二部分方块的隐藏
	public void hideFanHuanJinMore61(final TextView[] fanHuanJinList,
			final Map<Integer, String> map, final int visible) {
		for (int k = lessCount61; k < fanHuanJinList.length; k++) {
			fanHuanJinList[k].setVisibility(View.INVISIBLE);
		}
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

	// 智能星重疾保障成本的计算
	public ArrayList<HongLi> findZhongJiBaoFei(HongLi hongli, Context c,
			ArrayList<HongLi> zjFeiLv) {
		ArrayList<HongLi> hl = new ArrayList<HongLi>();
		double baoFei = 0;
		// 重疾保障成本的计算公式
		for (int i = 1; i <= 104 - hongli.getAge() + 1; i++) {
			HongLi hong = new HongLi();
			// 因为费率是每年根据年龄变化的
			// 费率的查找公式
			for (HongLi entity : zjFeiLv) {
				if (entity.getAge() == hongli.getAge() + i - 1) {
					if (entity.getSex().equals(hongli.getSex())) {
						baoFei = entity.getFeiLv();
						break;
					}
				}
			}
			if (i == 1) {
				// 第一年（费率/1000*重疾保额）*0.75
				baoFei = Arithmetic4Double.multi(
						Arithmetic4Double.multi(
								Arithmetic4Double.div(baoFei, 1000),
								hongli.getZhongJiBaoE()), 0.75);
			} else {
				// 第二年以及以后 费率/1000*重疾保额
				baoFei = Arithmetic4Double.multi(
						Arithmetic4Double.div(baoFei, 1000),
						hongli.getZhongJiBaoE());
			}
			hong.setHongli(String.valueOf(baoFei));
			hl.add(hong);
		}
		return hl;
	}

	// 智能星意外医疗保障成本的计算
	public Double findYiLiaoBaoFei(String baoE, Context c) {
		Double yiwai = 0.0;
		// 意外的值=31+(医疗保额-1000)/1000*8
		yiwai = Arithmetic4Double.add(
				Arithmetic4Double.multi(
						Arithmetic4Double.div(Arithmetic4Double.sub(
								Double.valueOf(baoE), 1000), 1000), 8), 31);
		return yiwai;
	}

	// 智能星豁免保障成本的计算
	public ArrayList<HongLi> findHuoMianBaoFei(HongLi hongli, Context c,
			ArrayList<HongLi> hmFL, int year, ArrayList<HongLi> wxxsFL, int baoF) {
		ArrayList<HongLi> hongLi = new ArrayList<HongLi>();
		HongLi hl = null;
		Double huoMian = 0.0;
		// 豁免费率
		Double hmFeiLv = 0.0;
		// 危险系数
		Double wxxsFeiLv = 0.0;
		int huoMianTime = hongli.getYear() - 1;
		if (Integer.valueOf(hongli.getZhongJiBaoE()) >= Integer.valueOf(hongli
				.getBaoE())) {
			for (int i = 1; i <= huoMianTime; i++) {
				hl = new HongLi();
				hl.setHongli(huoMian.toString());
				hongLi.add(hl);
			}
		} else {
			for (int i = 1; i <= huoMianTime; i++) {
				hl = new HongLi();
				// 豁免费率
				for (HongLi entity : hmFL) {
					if (entity.getAge() == hongli.getAge() + i - 1) {
						if (entity.getSex().equals(hongli.getSex())) {
							hmFeiLv = entity.getFeiLv();
							break;
						}
					}
				}
				// 危险系数的查询
				for (HongLi entity : wxxsFL) {
					if (entity.getAge() == hongli.getYear() - i) {
						wxxsFeiLv = entity.getFeiLv();
						break;
					}
				}

				if (i == 1) {
					// 豁免的值=(豁免费率*危险系数*期交保费)/1000 * 0.75
					huoMian = Arithmetic4Double.multi(
							Arithmetic4Double.div(
									Arithmetic4Double.multi(Arithmetic4Double
											.multi(hmFeiLv, wxxsFeiLv), baoF),
									1000), 0.75);
				} else {
					// 豁免的值=(豁免费率*危险系数*期交保费)/1000
					huoMian = Arithmetic4Double.div(Arithmetic4Double.multi(
							Arithmetic4Double.multi(hmFeiLv, wxxsFeiLv), baoF),
							1000);
				}
				hl.setHongli(huoMian.toString());
				hongLi.add(hl);
			}
		}
		return hongLi;
	}

	// 智能型附加保险的值=重疾保障成本+医疗保障成本+豁免保障成本
	public ArrayList<HongLi> findFuJiaXianBaoFei(HongLi hongli,
			ArrayList<HongLi> zhongJiBaoFeiD, Double yiLiaoBaoZhang,
			ArrayList<HongLi> huoMianBaoZhang, int year) {
		ArrayList<HongLi> hongLiList = new ArrayList<HongLi>();
		HongLi hl = null;
		int i = 0;
		Double fuJiaXian = 0.0;
		for (HongLi h : zhongJiBaoFeiD) {
			hl = new HongLi();
			// 医疗保障成本只到65岁
			if (hongli.getAge() + i <= 65) {
				// 豁免只在交费年期内有
				if (i < year - 1) {
					// 在交费年期内有值
					fuJiaXian = Arithmetic4Double.add(Arithmetic4Double.add(
							Double.valueOf(h.getHongli()), yiLiaoBaoZhang),
							Double.valueOf(huoMianBaoZhang.get(i).getHongli()));
				} else {
					// 超过交费年期，豁免为0
					fuJiaXian = Arithmetic4Double.add(
							Double.valueOf(h.getHongli()), yiLiaoBaoZhang);
				}
			}
			// 65岁后的医疗保障成本为0
			else {
				fuJiaXian = Double.valueOf(h.getHongli());
			}
			i++;
			hl.setHongli(String.valueOf(fuJiaXian));
			hongLiList.add(hl);
		}
		// for(HongLi entity : hongLiList){
		// Log.i("zhineng", "智能星附加="+entity.getHongli());
		// }
		return hongLiList;
	}

	String oneCash;
	private Double cash = 0.0;

	// 第一年的主险保障成本的值，算出第一年的保单现金价值（年末）
	public Double findZhuXianBaoFei(int bF, ArrayList<HongLi> chuShiFeiYong,
			ArrayList<HongLi> fuJiaXian, Double zxFeiLv) {
		Double zxBaoZhang = 0.0;
		int j = 0;
		// 第一年的主险保障成本
		// 第一年的主险保障成本=(期交保费-初始费用-附加险保障成本/12)*主费率/1000
		zxBaoZhang = Arithmetic4Double.div(Arithmetic4Double.multi(
				Arithmetic4Double.sub(Arithmetic4Double.sub(bF,
						Double.valueOf(chuShiFeiYong.get(j).getHongli())),
						Arithmetic4Double.div(
								Double.valueOf(fuJiaXian.get(j).getHongli()),
								12)), zxFeiLv), 1000);
		return zxBaoZhang;
	}

	// 计算累积保费
	public ArrayList<HongLi> findLeiJiBaoFei(int bF, int year) {
		ArrayList<HongLi> hl = new ArrayList<HongLi>();
		HongLi h = null;
		Double ljBaoF = 0.0;
		for (int i = 1; i <= 105; i++) {
			h = new HongLi();
			if (i <= year) {
				ljBaoF = Arithmetic4Double.multi(bF, i);
			} else {
				ljBaoF = Arithmetic4Double.multi(bF, year);
			}
			h.setHongli(ljBaoF.toString());
			hl.add(h);
		}
		return hl;
	}

	// 计算第一年年末的保单现金价值
	public String findFirstBaoDanXianJin(int bF,
			ArrayList<HongLi> chuShiFeiYong, ArrayList<HongLi> fuJiaXian,
			Double yiLiaoBaoZhang, HongLi hongli, Context c, int year,
			ArrayList<HongLi> huoMianBaoZhang, Double zhuXiangBaoZhang,
			ArrayList<HongLi> zhongJiBaoFeiD) {
		// 保单年度为1的时候算出当年度的主险保障成本的值，再算出当年度的保单现金价值（年末）
		// 该for循环算出了第一年度最后一年的保单现金价值的值spareCash

		ArrayList<HongLi> ahl = new ArrayList<HongLi>();
		HongLi h = null;
		for (int k = 1; k <= 12; k++) {
			h = new HongLi();
			// 第一年度的第一个月的保单现金价值的计算,保单现金价值有低中高之分
			if (k == 1) {
				cash = Arithmetic4Double.sub(Arithmetic4Double.sub(
						Arithmetic4Double.sub(bF, Double.valueOf(chuShiFeiYong
								.get(0).getHongli())), Arithmetic4Double.div(
								zhuXiangBaoZhang, 12)), Arithmetic4Double.div(
						yiLiaoBaoZhang, 12));
			}
			// 第一年度的第二个月的保单现金价值的计算,保单现金价值有低中高之分
			else if (k == 2) {
				cash = Arithmetic4Double.sub(Arithmetic4Double.sub(
						Double.valueOf(oneCash),
						Arithmetic4Double.div(zhuXiangBaoZhang, 12)),
						Arithmetic4Double.div(yiLiaoBaoZhang, 12));
			}
			// 第一年度的第二个月的保单现金价值的计算,保单现金价值有低中高之分
			else if (k == 3) {
				cash = Arithmetic4Double.sub(Arithmetic4Double.sub(
						Double.valueOf(oneCash),
						Arithmetic4Double.div(zhuXiangBaoZhang, 12)),
						Arithmetic4Double.div(yiLiaoBaoZhang, 12));
			}
			// 第一年度的第4个月以后的保单现金价值的计算,保单现金价值有低中高之分
			// 通过这个算出第一年度的最后一个月的保单现金价值的值
			else if (k >= 4) {
				cash = Arithmetic4Double.sub(Arithmetic4Double.sub(
						Arithmetic4Double.sub(
								Arithmetic4Double.sub(Double.valueOf(oneCash),
										Arithmetic4Double.div(Double
												.valueOf(zhongJiBaoFeiD.get(0)
														.getHongli()), 9)),
								Arithmetic4Double.div(yiLiaoBaoZhang, 12)),
						Arithmetic4Double.div(Double.valueOf(huoMianBaoZhang
								.get(0).getHongli()), 9)), Arithmetic4Double
						.div(zhuXiangBaoZhang, 12));
			}
			if (hongli.getLevel().equals("low")) {
				oneCash = String.valueOf(Arithmetic4Double.multi(cash,
						Arithmetic4Double.add(1, Define.lowLiLv)));
			} else if (hongli.getLevel().equals("middle")) {
				oneCash = String.valueOf(Arithmetic4Double.multi(cash,
						Arithmetic4Double.add(1, Define.middleLiLv)));
			} else if (hongli.getLevel().equals("high")) {
				oneCash = String.valueOf(Arithmetic4Double.multi(cash,
						Arithmetic4Double.add(1, Define.highLiLv)));
			}
			h.setHongli(oneCash);
			ahl.add(h);
		}
		// for(HongLi hli : ahl){
		// Log.i("baodan", "第一年每个月的保单现金价值="+hli.getHongli());
		// }
		return oneCash;
	}

	/**
	 * 计算第二年年末的保单现金价值
	 * 
	 * @param bF
	 * @param fuJiaXian
	 * @param hongli
	 * @param c
	 * @param zhuXiangBaoZhang
	 * @param wanNeng
	 * @param baoDanXianJin
	 * @param z
	 *            表示第几年
	 * @return
	 */
	public String findSecondBaoDanXianJin(int bF, ArrayList<HongLi> fuJiaXian,
			HongLi hongli, Context c, Double zhuXiangBaoZhang, Double wanNeng,
			String baoDanXianJin, int z) {
		ArrayList<HongLi> ahonglilist = new ArrayList<HongLi>();
		HongLi harray = null;

		// 保单年度为2的时候算出当年度的主险保障成本的值，再算出当年度的保单现金价值（年末）
		// 该for循环算出了第二年度最后一年的保单现金价值的值spareCash
		for (int k = 1; k <= 12; k++) {
			harray = new HongLi();
			// 第二年度的第一个月的保单现金价值的计算,保单现金价值有低中高之分
			if (k == 1) {
				cash = Arithmetic4Double.sub(Arithmetic4Double.sub(
						Arithmetic4Double.add(Double.valueOf(baoDanXianJin),
								wanNeng), Arithmetic4Double.div(
								zhuXiangBaoZhang, 12)), Arithmetic4Double.div(
						Double.valueOf(fuJiaXian.get(z - 1).getHongli()), 12));
			}
			// 第二年度的第2个月以后的保单现金价值的计算,保单现金价值有低中高之分
			// 通过这个算出第一年度的最后一个月的保单现金价值的值
			else if (k >= 2) {
				cash = Arithmetic4Double.sub(Arithmetic4Double.sub(
						Double.valueOf(oneCash),
						Arithmetic4Double.div(zhuXiangBaoZhang, 12)),
						Arithmetic4Double.div(Double.valueOf(fuJiaXian.get(
								z - 1).getHongli()), 12));
			}
			if (hongli.getLevel().equals("low")) {
				oneCash = String.valueOf(Arithmetic4Double.multi(cash,
						Arithmetic4Double.add(1, Define.lowLiLv)));
			} else if (hongli.getLevel().equals("middle")) {
				oneCash = String.valueOf(Arithmetic4Double.multi(cash,
						Arithmetic4Double.add(1, Define.middleLiLv)));
			} else if (hongli.getLevel().equals("high")) {
				oneCash = String.valueOf(Arithmetic4Double.multi(cash,
						Arithmetic4Double.add(1, Define.highLiLv)));
			}
			harray.setHongli(oneCash);
			ahonglilist.add(harray);
		}
		return oneCash;
	}
}
