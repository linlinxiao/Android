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
	// ��������������ȫ���յı���
	public Double findBaoFei(HongLi hongli, Context c, String type,SQLiteDatabase db) {
		double baoFei = 0;
		baoFei = Double.valueOf(DBDAO.findBaoFei(hongli, c, type,db));
		baoFei = Arithmetic4Double.multi(baoFei, hongli.getBaoE());
		return baoFei;
	}

	// �۲Ʊ������˻��ı���仯
	public static String getWanNengZhangHuLevel(String level) {
		String zhongShenFenHongLevel = null;
		if (level.equals(Define.Level_Low_display)) {
			zhongShenFenHongLevel = "�����ֽ��ֵ�˻����͵ȼ��㣩��";
		} else if (level.equals(Define.Level_Middle_display)) {
			zhongShenFenHongLevel = "�����ֽ��ֵ�˻����еȼ��㣩";
		} else {
			zhongShenFenHongLevel = "�����ֽ��ֵ�˻����ߵȼ��㣩";
		}
		return zhongShenFenHongLevel;

	}

	// // �۲Ʊ������˻�ֵ����
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

	// �۲Ʊ������˻�ֵ��ʾ����
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

	// �۲Ʊ������˻��б���ʾ����
	public static String[] findHongLi(ArrayList<HongLi> listMap, int start,
			int end) {
		ArrayList<String> list = new ArrayList<String>();

		for (HongLi entity : listMap)

			if (entity.getAge() >= start && entity.getAge() <= end) {
				list.add(entity.getAge() + "���˻��ۻ���" + entity.getHongli() + "Ԫ");
			}
		return (String[]) list.toArray(new String[0]);
	}

	public static int lessCount61 = 0;

	// �������������е���ֵ�ĵ�һ����
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
								+ "��"
								+ Arithmetic4Double
										.doubleToString(Arithmetic4Double
												.multi(baoE, 0.15)));
				rst = rst
						+ i
						+ "��"
						+ Arithmetic4Double.doubleToString(Arithmetic4Double
								.multi(baoE, 0.15)) + " ";
			} else {
				if (i >= 71) {
					map.put(j, "�쵽��");
					rst = rst + i + "��" + "�쵽��" + " ";
					j = j + 1;
					break;
				} else {
					if (i + age + 3 == 104) {
						map.put(j, "�쵽��");
						rst = rst + i + "��" + "�쵽��" + " ";
						j = j + 1;
						break;
					} else {
						map.put(j,
								(i + age + 3)
										+ "��"
										+ Arithmetic4Double
												.doubleToString(Arithmetic4Double
														.multi(baoE, 0.18)));
						rst = rst
								+ i
								+ "��"
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
	 * ���ܣ�������������ĵ�һ���ֶ�����ʾ
	 * 
	 * @param fanHuanJinList
	 *            �����TextView�Ŀؼ���ʼ���ļ���
	 * @param map
	 *            �����ݿ��ȡ��Ҫ��ʾ�ڷ����ϵ����ݼ���
	 * @param ani
	 *            ����Ҫ��ʾ�Ķ���
	 * @param tv1
	 *            ���������ť
	 * @param tv2
	 *            �ұ�������ť
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
	 * ���ܣ�������������ĵڶ����ֶ�����ʾ
	 * 
	 * @param fanHuanJinList
	 *            �����TextView�Ŀؼ���ʼ���ļ���
	 * @param map
	 *            �����ݿ��ȡ��Ҫ��ʾ�ڷ����ϵ����ݼ���
	 * @param ani
	 *            ����Ҫ��ʾ�Ķ���
	 * @param tv1
	 *            ���������ť
	 * @param tv2
	 *            �ұ�������ť
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

	// ���������ĵڶ����ַ��������
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
				list.add(entity.getAge() + "���ۼ������" + entity.getValue() + "Ԫ");
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
			grade = "��";
		} else if (level.equals("middle")) {
			grade = "��";
		} else {
			grade = "��";
		}
		return grade;

	}

	// �������ؼ����ϳɱ��ļ���
	public ArrayList<HongLi> findZhongJiBaoFei(HongLi hongli, Context c,
			ArrayList<HongLi> zjFeiLv) {
		ArrayList<HongLi> hl = new ArrayList<HongLi>();
		double baoFei = 0;
		// �ؼ����ϳɱ��ļ��㹫ʽ
		for (int i = 1; i <= 104 - hongli.getAge() + 1; i++) {
			HongLi hong = new HongLi();
			// ��Ϊ������ÿ���������仯��
			// ���ʵĲ��ҹ�ʽ
			for (HongLi entity : zjFeiLv) {
				if (entity.getAge() == hongli.getAge() + i - 1) {
					if (entity.getSex().equals(hongli.getSex())) {
						baoFei = entity.getFeiLv();
						break;
					}
				}
			}
			if (i == 1) {
				// ��һ�꣨����/1000*�ؼ����*0.75
				baoFei = Arithmetic4Double.multi(
						Arithmetic4Double.multi(
								Arithmetic4Double.div(baoFei, 1000),
								hongli.getZhongJiBaoE()), 0.75);
			} else {
				// �ڶ����Լ��Ժ� ����/1000*�ؼ�����
				baoFei = Arithmetic4Double.multi(
						Arithmetic4Double.div(baoFei, 1000),
						hongli.getZhongJiBaoE());
			}
			hong.setHongli(String.valueOf(baoFei));
			hl.add(hong);
		}
		return hl;
	}

	// ����������ҽ�Ʊ��ϳɱ��ļ���
	public Double findYiLiaoBaoFei(String baoE, Context c) {
		Double yiwai = 0.0;
		// �����ֵ=31+(ҽ�Ʊ���-1000)/1000*8
		yiwai = Arithmetic4Double.add(
				Arithmetic4Double.multi(
						Arithmetic4Double.div(Arithmetic4Double.sub(
								Double.valueOf(baoE), 1000), 1000), 8), 31);
		return yiwai;
	}

	// �����ǻ��Ᵽ�ϳɱ��ļ���
	public ArrayList<HongLi> findHuoMianBaoFei(HongLi hongli, Context c,
			ArrayList<HongLi> hmFL, int year, ArrayList<HongLi> wxxsFL, int baoF) {
		ArrayList<HongLi> hongLi = new ArrayList<HongLi>();
		HongLi hl = null;
		Double huoMian = 0.0;
		// �������
		Double hmFeiLv = 0.0;
		// Σ��ϵ��
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
				// �������
				for (HongLi entity : hmFL) {
					if (entity.getAge() == hongli.getAge() + i - 1) {
						if (entity.getSex().equals(hongli.getSex())) {
							hmFeiLv = entity.getFeiLv();
							break;
						}
					}
				}
				// Σ��ϵ���Ĳ�ѯ
				for (HongLi entity : wxxsFL) {
					if (entity.getAge() == hongli.getYear() - i) {
						wxxsFeiLv = entity.getFeiLv();
						break;
					}
				}

				if (i == 1) {
					// �����ֵ=(�������*Σ��ϵ��*�ڽ�����)/1000 * 0.75
					huoMian = Arithmetic4Double.multi(
							Arithmetic4Double.div(
									Arithmetic4Double.multi(Arithmetic4Double
											.multi(hmFeiLv, wxxsFeiLv), baoF),
									1000), 0.75);
				} else {
					// �����ֵ=(�������*Σ��ϵ��*�ڽ�����)/1000
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

	// �����͸��ӱ��յ�ֵ=�ؼ����ϳɱ�+ҽ�Ʊ��ϳɱ�+���Ᵽ�ϳɱ�
	public ArrayList<HongLi> findFuJiaXianBaoFei(HongLi hongli,
			ArrayList<HongLi> zhongJiBaoFeiD, Double yiLiaoBaoZhang,
			ArrayList<HongLi> huoMianBaoZhang, int year) {
		ArrayList<HongLi> hongLiList = new ArrayList<HongLi>();
		HongLi hl = null;
		int i = 0;
		Double fuJiaXian = 0.0;
		for (HongLi h : zhongJiBaoFeiD) {
			hl = new HongLi();
			// ҽ�Ʊ��ϳɱ�ֻ��65��
			if (hongli.getAge() + i <= 65) {
				// ����ֻ�ڽ�����������
				if (i < year - 1) {
					// �ڽ�����������ֵ
					fuJiaXian = Arithmetic4Double.add(Arithmetic4Double.add(
							Double.valueOf(h.getHongli()), yiLiaoBaoZhang),
							Double.valueOf(huoMianBaoZhang.get(i).getHongli()));
				} else {
					// �����������ڣ�����Ϊ0
					fuJiaXian = Arithmetic4Double.add(
							Double.valueOf(h.getHongli()), yiLiaoBaoZhang);
				}
			}
			// 65����ҽ�Ʊ��ϳɱ�Ϊ0
			else {
				fuJiaXian = Double.valueOf(h.getHongli());
			}
			i++;
			hl.setHongli(String.valueOf(fuJiaXian));
			hongLiList.add(hl);
		}
		// for(HongLi entity : hongLiList){
		// Log.i("zhineng", "�����Ǹ���="+entity.getHongli());
		// }
		return hongLiList;
	}

	String oneCash;
	private Double cash = 0.0;

	// ��һ������ձ��ϳɱ���ֵ�������һ��ı����ֽ��ֵ����ĩ��
	public Double findZhuXianBaoFei(int bF, ArrayList<HongLi> chuShiFeiYong,
			ArrayList<HongLi> fuJiaXian, Double zxFeiLv) {
		Double zxBaoZhang = 0.0;
		int j = 0;
		// ��һ������ձ��ϳɱ�
		// ��һ������ձ��ϳɱ�=(�ڽ�����-��ʼ����-�����ձ��ϳɱ�/12)*������/1000
		zxBaoZhang = Arithmetic4Double.div(Arithmetic4Double.multi(
				Arithmetic4Double.sub(Arithmetic4Double.sub(bF,
						Double.valueOf(chuShiFeiYong.get(j).getHongli())),
						Arithmetic4Double.div(
								Double.valueOf(fuJiaXian.get(j).getHongli()),
								12)), zxFeiLv), 1000);
		return zxBaoZhang;
	}

	// �����ۻ�����
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

	// �����һ����ĩ�ı����ֽ��ֵ
	public String findFirstBaoDanXianJin(int bF,
			ArrayList<HongLi> chuShiFeiYong, ArrayList<HongLi> fuJiaXian,
			Double yiLiaoBaoZhang, HongLi hongli, Context c, int year,
			ArrayList<HongLi> huoMianBaoZhang, Double zhuXiangBaoZhang,
			ArrayList<HongLi> zhongJiBaoFeiD) {
		// �������Ϊ1��ʱ���������ȵ����ձ��ϳɱ���ֵ�����������ȵı����ֽ��ֵ����ĩ��
		// ��forѭ������˵�һ������һ��ı����ֽ��ֵ��ֵspareCash

		ArrayList<HongLi> ahl = new ArrayList<HongLi>();
		HongLi h = null;
		for (int k = 1; k <= 12; k++) {
			h = new HongLi();
			// ��һ��ȵĵ�һ���µı����ֽ��ֵ�ļ���,�����ֽ��ֵ�е��и�֮��
			if (k == 1) {
				cash = Arithmetic4Double.sub(Arithmetic4Double.sub(
						Arithmetic4Double.sub(bF, Double.valueOf(chuShiFeiYong
								.get(0).getHongli())), Arithmetic4Double.div(
								zhuXiangBaoZhang, 12)), Arithmetic4Double.div(
						yiLiaoBaoZhang, 12));
			}
			// ��һ��ȵĵڶ����µı����ֽ��ֵ�ļ���,�����ֽ��ֵ�е��и�֮��
			else if (k == 2) {
				cash = Arithmetic4Double.sub(Arithmetic4Double.sub(
						Double.valueOf(oneCash),
						Arithmetic4Double.div(zhuXiangBaoZhang, 12)),
						Arithmetic4Double.div(yiLiaoBaoZhang, 12));
			}
			// ��һ��ȵĵڶ����µı����ֽ��ֵ�ļ���,�����ֽ��ֵ�е��и�֮��
			else if (k == 3) {
				cash = Arithmetic4Double.sub(Arithmetic4Double.sub(
						Double.valueOf(oneCash),
						Arithmetic4Double.div(zhuXiangBaoZhang, 12)),
						Arithmetic4Double.div(yiLiaoBaoZhang, 12));
			}
			// ��һ��ȵĵ�4�����Ժ�ı����ֽ��ֵ�ļ���,�����ֽ��ֵ�е��и�֮��
			// ͨ����������һ��ȵ����һ���µı����ֽ��ֵ��ֵ
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
		// Log.i("baodan", "��һ��ÿ���µı����ֽ��ֵ="+hli.getHongli());
		// }
		return oneCash;
	}

	/**
	 * ����ڶ�����ĩ�ı����ֽ��ֵ
	 * 
	 * @param bF
	 * @param fuJiaXian
	 * @param hongli
	 * @param c
	 * @param zhuXiangBaoZhang
	 * @param wanNeng
	 * @param baoDanXianJin
	 * @param z
	 *            ��ʾ�ڼ���
	 * @return
	 */
	public String findSecondBaoDanXianJin(int bF, ArrayList<HongLi> fuJiaXian,
			HongLi hongli, Context c, Double zhuXiangBaoZhang, Double wanNeng,
			String baoDanXianJin, int z) {
		ArrayList<HongLi> ahonglilist = new ArrayList<HongLi>();
		HongLi harray = null;

		// �������Ϊ2��ʱ���������ȵ����ձ��ϳɱ���ֵ�����������ȵı����ֽ��ֵ����ĩ��
		// ��forѭ������˵ڶ�������һ��ı����ֽ��ֵ��ֵspareCash
		for (int k = 1; k <= 12; k++) {
			harray = new HongLi();
			// �ڶ���ȵĵ�һ���µı����ֽ��ֵ�ļ���,�����ֽ��ֵ�е��и�֮��
			if (k == 1) {
				cash = Arithmetic4Double.sub(Arithmetic4Double.sub(
						Arithmetic4Double.add(Double.valueOf(baoDanXianJin),
								wanNeng), Arithmetic4Double.div(
								zhuXiangBaoZhang, 12)), Arithmetic4Double.div(
						Double.valueOf(fuJiaXian.get(z - 1).getHongli()), 12));
			}
			// �ڶ���ȵĵ�2�����Ժ�ı����ֽ��ֵ�ļ���,�����ֽ��ֵ�е��и�֮��
			// ͨ����������һ��ȵ����һ���µı����ֽ��ֵ��ֵ
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
