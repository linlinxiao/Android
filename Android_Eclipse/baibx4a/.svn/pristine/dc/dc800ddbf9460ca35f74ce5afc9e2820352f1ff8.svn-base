package com.baoxiao.service.productshow;

import java.util.ArrayList;
import com.baoxiao.model.HongLi;
import com.baoxiao.provider.DBDAO;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class  HuShenFuService {
	
	public Double findHuoMianBaoE(HongLi hongli, Context c,String type,Double ywBaoFei,
			Double zhuBaoE,Double zjBaoE,SQLiteDatabase db){
//		{（Y主险-Z副）*F主费率}+D长期意外）;
		Double baoFei;
		String baof = DBDAO.findBaoFei(hongli, c, type,db);
		if (baof == null || "".equals(baof)) {baof = "0";}
		baoFei = Double.valueOf(baof);
		
		baoFei = Arithmetic4Double.add(
			Arithmetic4Double.multi(
					Arithmetic4Double.sub(zhuBaoE, zjBaoE), baoFei),
					ywBaoFei);
		return baoFei;
	}

	/**
	 * 查询出保单分红累积交清增额账户的值
	 * @param hongli
	 * @param c
	 * @param type
	 * @return
	 */
	public ArrayList<HongLi> findHongLi(HongLi hongli, Context c, String type) {

		ArrayList<HongLi> list = DBDAO.findHongli(hongli, c, type,Define.tableName_huShenFuHongLi);

		for (HongLi hl : list) {
			double value = 0;
			hl.setAge(hongli.getAge() + hl.getYear());
			value = Arithmetic4Double.div(Double.valueOf(hl.getHongli()), 100);
			value = value * hongli.getFenShu();
			hl.setHongli(Arithmetic4Double.doubleToString(value));
//			Log.d("hongli",
//					"age" + hl.getAge() + "," + "hongLi=" + hl.getHongli());

		}

		return list;
	}

	public String[] findHongLi(ArrayList<HongLi> listMap, int start, int end) {
		ArrayList<String> list = new ArrayList<String>();

		for (HongLi entity : listMap)

			if (entity.getAge() >= start && entity.getAge() <= end) {
				list.add(entity.getAge() + "保单累积交清增额：" + entity.getHongli() + "元");
			}
		return (String[]) list.toArray(new String[0]);
	}

	public String getHongLiByAge(ArrayList<HongLi> listMap, int age) {
		String value = "";
		for (HongLi entity : listMap)

			if (entity.getAge() == age) {
				value = entity.getHongli();
				break;
			}

		return value;
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
	
	public static String getZhongShenFenHongLevel(String level)
	{
		String zhongShenFenHongLevel = null;
		if (level.equals(Define.Level_Low_display))
		{
			zhongShenFenHongLevel = "保单累积交情增额保额(低等计算)";
		} else if (level.equals(Define.Level_Middle_display))
		{
			zhongShenFenHongLevel = "保单累积交情增额保额(中等计算)";
		} else
		{
			zhongShenFenHongLevel = "保单累积交情增额保额(高等计算)";
		}
		return zhongShenFenHongLevel;

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
}
