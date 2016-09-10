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

public class ZunYuRSService {

	//��������������ȫ���յı���
	public Double findBaoFei(HongLi hongli,Context c,String type,SQLiteDatabase db){
		double baoFei = 0;
		baoFei = Double.valueOf(DBDAO.findBaoFei(hongli, c, type,db));
		baoFei = Arithmetic4Double.multi(baoFei, hongli.getBaoE());
		return baoFei;
	}
	
	// �۲Ʊ������˻��ı���仯
		public static String getWanNengZhangHuLevel(String level)
		{
			String zhongShenFenHongLevel = null;
			if (level.equals(Define.Level_Low_display))
			{
				zhongShenFenHongLevel = "�۲Ʊ������˻����͵ȼ��㣩��";
			} else if (level.equals(Define.Level_Middle_display))
			{
				zhongShenFenHongLevel = "�۲Ʊ������˻����еȼ��㣩";
			} else
			{
				zhongShenFenHongLevel = "�۲Ʊ������˻����ߵȼ��㣩";
			}
			return zhongShenFenHongLevel;

		}
			
		//�۲Ʊ������˻�ֵ����
		public ArrayList<HongLi> findHongLi(HongLi hongli,Context c,String type) {
			ArrayList<HongLi> list = DBDAO.findHongli(hongli, c, type,
					Define.tableName_zyrsHongLi);
			
			for(HongLi hl : list){
				double value = 0;
				hl.setAge(hongli.getAge() + hl.getYear());
				value = Arithmetic4Double.div(Double.valueOf(hl.getHongli()), 100);
				value = value * hongli.getFenShu();
				hl.setHongli(Arithmetic4Double.doubleToString(value));
//				Log.d("hongli",
//						"age" + hl.getAge() + "," + "hongLi=" + hl.getHongli());
			}
			return list;
		}
		//�۲Ʊ������˻�ֵ��ʾ����
		public String getHongLiByAge(ArrayList<HongLi> listMap, int age) {
				String value = "";
				for(HongLi entity : listMap){
					
					if(entity.getAge() == age){
						value = entity.getHongli();
						break;
					}
				}
				return value;
			}
		//�۲Ʊ������˻��б���ʾ����
		public static String[] findHongLi(ArrayList<HongLi> listMap, int start, int end) {
			ArrayList<String> list = new ArrayList<String>();

			for (HongLi entity : listMap)

				if (entity.getAge() >= start && entity.getAge() <= end) {
					list.add(entity.getAge() + "���˻��ۻ���" + entity.getHongli() + "Ԫ");
				}
			return (String[]) list.toArray(new String[0]);
		}

		public static int lessCount61 = 0;

		//�������������е���ֵ�ĵ�һ����
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
											.doubleToString(Arithmetic4Double.multi(
															baoE, 0.15)));
					rst = rst
							+ i
							+ "��"
							+ Arithmetic4Double.doubleToString(
									Arithmetic4Double.multi(baoE, 0.15))
							+ " ";
				} else {
					if (i >= 71) {
						map.put(j, "�쵽��");
						rst = rst + i + "��" + "�쵽��" + " ";
						j = j + 1;
						break;
					} else {
						if(i + age + 3==104){
							map.put(j, "�쵽��");
							rst = rst + i + "��" + "�쵽��" + " ";
							j = j + 1;
							break;
						}else{
						map.put(j,
								(i + age + 3)
										+ "��"
										+ Arithmetic4Double
												.doubleToString(Arithmetic4Double.multi(
														baoE, 0.18)));
						rst = rst
								+ i
								+ "��"
								+ Arithmetic4Double
										.doubleToString(Arithmetic4Double.multi(
												baoE, 0.18)) + " ";
						}
					}
				}

				j = j + 1;
			}

			return map;

		}

		int j = 0;
		Handler handler;
//		public Timer timer1;
		
		/**
		 * ���ܣ�������������ĵ�һ���ֶ�����ʾ
		 * @param fanHuanJinList �����TextView�Ŀؼ���ʼ���ļ���
		 * @param map �����ݿ��ȡ��Ҫ��ʾ�ڷ����ϵ����ݼ���
		 * @param ani ����Ҫ��ʾ�Ķ���
		 * @param tv1  ���������ť
		 * @param tv2  �ұ�������ť
		 */
		public void showFanHuanJin61(final TextView[] fanHuanJinList,
				Map<Integer, String> map, final Animation ani,
				final TextView tv1, final TextView tv2) {
			final Timer timer = new Timer();
//			timer1 = timer;
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
				}, 0, 200);
		}

		/**
		 * ���ܣ�������������ĵڶ����ֶ�����ʾ
		 * @param fanHuanJinList �����TextView�Ŀؼ���ʼ���ļ���
		 * @param map �����ݿ��ȡ��Ҫ��ʾ�ڷ����ϵ����ݼ���
		 * @param ani ����Ҫ��ʾ�Ķ���
		 * @param tv1  ���������ť
		 * @param tv2  �ұ�������ť
		 */
		public void showFanHuanJinMore61(final TextView[] fanHuanJinList,
				final Map<Integer, String> map, final Animation ani,
				final TextView tv1, final TextView tv2) {
			final Timer timer = new Timer();
//			timer1 = timer;
			j = lessCount61;
			tv1.setEnabled(false);
			tv2.setEnabled(false);
			for (int i = 0; i < map.keySet().size(); i++) {
				if (i >= lessCount61) {
					map.get(i);
					fanHuanJinList[i].setText(map.get(i));
					fanHuanJinList[i].setBackgroundResource(R.drawable.zhrs_fangkuai2);
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
			}, 0, 200);
			
		}
		
		//���������ĵڶ����ַ��������
		public void hideFanHuanJinMore61(final TextView[] fanHuanJinList,
				final Map<Integer, String> map, final int visible)
		{
			for (int k = lessCount61; k < fanHuanJinList.length; k++)
			{
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
}
