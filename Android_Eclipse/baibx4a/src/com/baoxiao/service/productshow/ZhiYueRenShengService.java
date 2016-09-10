package com.baoxiao.service.productshow;

import java.util.ArrayList;
import java.util.HashMap;
import com.baoxiao.model.HongLi;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;

public class ZhiYueRenShengService {
	/**
	 * 标题变化
	 * @param level
	 * @return
	 */
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


	/**
	 * 账户值显示数据
	 * @param listMap
	 * @param age
	 * @return
	 */
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

	/**
	 * 账户列表显示数据
	 * @param listMap
	 * @param start
	 * @param end
	 * @return
	 */
	public static String[] findHongLi(ArrayList<HongLi> listMap, int start,
			int end) {
		ArrayList<String> list = new ArrayList<String>();

		for (HongLi entity : listMap)

			if (entity.getAge() >= start && entity.getAge() <= end) {
				list.add(entity.getAge() + "岁账户累积：" + entity.getHongli() + "元");
			}
		return (String[]) list.toArray(new String[0]);
	}

	/**
	 * 等级
	 * @param level
	 * @return
	 */
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
	
	/**
	 * 获取意外伤害的值
	 * @param hongli
	 * @return
	 */
	public static Double getYiWaiShangHai(HongLi hongli){
		int age = hongli.getAge();
		int shangHai = hongli.getShangHaiBaoE();
		if(age < 65){
			Double shValue = Arithmetic4Double.multi(
			Arithmetic4Double.div(shangHai, 10000),17);
			return shValue;
		}
		return null;
	}
	
	/**
	 * 获取意外医疗的值
	 * @param hongli
	 * @return
	 */
	public static Double getYiWaiYiLiao(HongLi hongli){
		int age = hongli.getAge();
		int yiLiao = hongli.getYiWaiBaoE();
		if(age < 65){
			Double ylValue = 
					Arithmetic4Double.add(
					Arithmetic4Double.multi(
					Arithmetic4Double.div(
					Arithmetic4Double.sub(yiLiao, 1000),1000),4),19);
			return ylValue;
		}
		return null;
	}
	
	/**
	 * 获取豁免的值
	 * @param hongli
	 * @return
	 */
	public static ArrayList<HongLi> getHuoMian(HongLi hongli,
			HashMap<String, HongLi> hmFeiLv,ArrayList<HongLi> wxxsFeiLv,String huoMian){
		//交费年期
		int period = hongli.getYear();
		//初始年龄
		int age = hongli.getAge();
		//期交保费
		int qiJaoBaoFei = hongli.getZhuBaoF();
		//假设年龄为18
		ArrayList<HongLi> h = new ArrayList<HongLi>();
		HongLi hl = null;
		Double value = 0.0;
		Double hmfl = 0.0;
		if(!"".equals(huoMian)){
			for(int i = 1; i <= 104; i++){
				if(i <= period - 1){
					if(i <= 29){
						hl = new HongLi();
						//费率
						hmfl = hmFeiLv.get((hongli.getAge()+i-1)+hongli.getSex()).getFeiLv();
//						for (HongLi entity : hmFeiLv) {
//							if (entity.getAge() == age + i - 1 && entity.getSex().equals(hongli.getSex())) {
//								hmfl = entity.getFeiLv();
//								break;
//							}
//						}
						//危险系数
						Double wxxs = wxxsFeiLv.get(period-i).getFeiLv();
						//第一年
						if(i == 1){
							value = Arithmetic4Double.multi(
							Arithmetic4Double.multi(
							Arithmetic4Double.multi(hmfl, wxxs),
							Arithmetic4Double.div(qiJaoBaoFei, 1000)),0.75);
						}
						//第二年  
						else{
							value = 
								Arithmetic4Double.multi(
											Arithmetic4Double.multi(hmfl, wxxs),
											Arithmetic4Double.div(qiJaoBaoFei, 1000));
						}
						hl.setHongli(String.valueOf(value));
						h.add(hl);
					}
				}
			}
		}else{
			//无附加保障的值为0
			for(int i = 1; i <= 104; i++){
				if(i <= period - 1){
					hl = new HongLi();
					hl.setHongli("0");
					h.add(hl);
					}
				}
		}
		return h;
	}
	
	/**
	 * 获取初始费用
	 * @param hongli
	 * @return
	 */
	public static ArrayList<HongLi> getChuShi(HongLi hongli){
		ArrayList<HongLi> chuShiFeiYong = new ArrayList<HongLi>();
		int bF = hongli.getZhuBaoF();
		HongLi hong = null;
		Double BeginValue = 0.0;
		for (int i = 1; i <= hongli.getYear(); i++) {
			 hong = new HongLi();
			// 期交保费小于10000的初始费用
			if (bF <= 10000) {
					if (i == 1) {
							BeginValue = Arithmetic4Double.multi(bF, 0.5);
						} else if (i == 2) {
							BeginValue = Arithmetic4Double.multi(bF, 0.25);
						} else if (i == 3) {
							BeginValue = Arithmetic4Double.multi(bF, 0.15);
						} else if (i == 4 | i == 5) {
							BeginValue = Arithmetic4Double.multi(bF, 0.1);
						} else if (i >= 6) {
							BeginValue = Arithmetic4Double.multi(bF, 0.05);
						}
			}else{
					if (i == 1) {
						BeginValue = Arithmetic4Double.add(
								Arithmetic4Double.multi(10000, 0.5), 
								Arithmetic4Double.multi(
								Arithmetic4Double.sub(bF, 10000),0.03));
					} else if (i == 2) {
						BeginValue = Arithmetic4Double.add(
								Arithmetic4Double.multi(10000, 0.25),
								Arithmetic4Double.multi(
										Arithmetic4Double.sub(bF, 10000),0.03));
					} else if (i == 3) {
						BeginValue = Arithmetic4Double.add(
								Arithmetic4Double.multi(10000, 0.15), 
								Arithmetic4Double.multi(
										Arithmetic4Double.sub(bF, 10000),0.03));
					} else if (i == 4 || i == 5) {
						BeginValue = Arithmetic4Double.add(
								Arithmetic4Double.multi(10000, 0.1),
								Arithmetic4Double.multi(
										Arithmetic4Double.sub(bF, 10000),0.03));
					} else if (i >= 6) {
						BeginValue = Arithmetic4Double.add(
								Arithmetic4Double.multi(10000, 0.05), 
								Arithmetic4Double.multi(
										Arithmetic4Double.sub(bF, 10000),0.03));
					}
			}
			hong.setHongli(BeginValue.toString());
			chuShiFeiYong.add(hong);
		}	
		return chuShiFeiYong;
	}
	
	/**
	 * 获取进入万能账户的值
	 * @param hongli
	 * @param chuShiValue
	 * @return
	 */
	public static ArrayList<HongLi> getWanNengZhangHu(HongLi hongli,ArrayList<HongLi> chuShiValue){
		int baoF = hongli.getZhuBaoF();
		int year = hongli.getYear();
		ArrayList<HongLi> wanNengValue = new ArrayList<HongLi>();
		HongLi hong = null;
		String wanNeng = null;
		for(int i = 0; i < year; i++){
			hong = new HongLi();
			//初始费用
			Double chuShi = Double.valueOf(chuShiValue.get(i).getHongli());
			if(i==19){
				wanNeng = String.valueOf(Arithmetic4Double.add(
						Arithmetic4Double.sub(baoF, chuShi),
						Arithmetic4Double.multi(baoF, 0.2)));
			}else{
				//进入万能账户的值
				wanNeng = String.valueOf(Arithmetic4Double.sub(baoF, chuShi));
			}
			hong.setHongli(wanNeng);
			wanNengValue.add(hong);
		}
		return wanNengValue;
	}
	
	/**
	 * 第一年年初价值的值
	 * @return
	 */
	public static Double getFirstNianChuValue(ArrayList<HongLi> wanNengZhangHu){
		return Double.valueOf(wanNengZhangHu.get(0).getHongli());
	}
	
	/**
	 * 第一年度第一个月的主险保障成本
	 * @param hongli
	 * @param firstNianChuValue
	 * @param zxFeiLv
	 * @return
	 */
	public static Double getFirstZhuXian(HongLi hongli,Double firstNianChuValue,Double zxFeiLv){
		Double zxValue = 0.0;
		//分三种情况
		//B主险保额
		int zxBaoE = hongli.getBaoE(); 
		//J重疾保额
		int zjBaoE = hongli.getZhongJiBaoE();
		//1.05A年初保单现金价值（分第一年的，和第二年以后的）
		double ncValue = Arithmetic4Double.multi(firstNianChuValue, 1.05);
		//1.B>=J>1.05A
		if(zxBaoE >= zjBaoE && zjBaoE > ncValue){
			zxValue = Arithmetic4Double.div(
			Arithmetic4Double.multi(
			Arithmetic4Double.sub(zxBaoE, firstNianChuValue),zxFeiLv),
			12000);
		}
		//2.B>=1.05A>J
		else if(zxBaoE >= ncValue && ncValue > zjBaoE){
			zxValue = 
			Arithmetic4Double.div(
			Arithmetic4Double.multi(
			Arithmetic4Double.sub(zxBaoE,firstNianChuValue),zxFeiLv),12000);
		}
		//3.1.05A>=B>J
		else if(ncValue >= zxBaoE && zxBaoE > zjBaoE){
			zxValue = Arithmetic4Double.div(
					Arithmetic4Double.multi(
					Arithmetic4Double.multi(
							firstNianChuValue, 0.05),zxFeiLv),12000);
		}
		return zxValue;
	}
	
	/**
	 * 第一年度第二个月的主险保障成本
	 * @param hongli
	 * @param beforeNianChuValue 前一个月的保单现金价值
	 * @param zxFeiLv
	 * @return
	 */
	public static Double getSecondZhuXian(HongLi hongli,Double beforeNianChuValue,Double zxFeiLv,int i){
		Double zxValue = 0.0;
		//分三种情况
		//B主险保额
		int zxBaoE = hongli.getBaoE(); 
		//J重疾保额
		int zjBaoE = hongli.getZhongJiBaoE();
		//1.05A年初保单现金价值（分第一年的，和第二年以后的）
		double ncValue = Arithmetic4Double.multi(beforeNianChuValue, 1.05);
		
		//1.B>=J>1.05A
		if(zxBaoE >= zjBaoE && zjBaoE > ncValue){
			zxValue = Arithmetic4Double.div(
					Arithmetic4Double.multi(
							Arithmetic4Double.sub(zxBaoE, beforeNianChuValue),zxFeiLv),
							12000);
		}
		//2.B>=1.05A>J
		else if(zxBaoE >= ncValue && ncValue > zjBaoE){
			zxValue = 
					Arithmetic4Double.div(
							Arithmetic4Double.multi(
									Arithmetic4Double.sub(zxBaoE,beforeNianChuValue),zxFeiLv),12000);
		}
		//3.1.05A>=B>J
		else if(ncValue >= zxBaoE && zxBaoE > zjBaoE){
			zxValue = Arithmetic4Double.div(
					Arithmetic4Double.multi(
							Arithmetic4Double.multi(
									beforeNianChuValue, 0.05),zxFeiLv),12000);
		}
		return zxValue;
	}
	
	/**
	 * 第二年度以后的每个月的主险保障成本
	 * @param hongli
	 * @param beforeNianChuValue 前一个月的保单现金价值
	 * @param zxFeiLv
	 * @return
	 */
	public static Double getAfterZhuXian(HongLi hongli,Double beforeNianChuValue,Double zxFeiLv,int i){
		Double zxValue = 0.0;
		//分三种情况
		//B主险保额
		int zxBaoE = hongli.getBaoE(); 
		//J重疾保额
		int zjBaoE = hongli.getZhongJiBaoE();
		//1.05A年初保单现金价值（分第一年的，和第二年以后的）
		double ncValue = Arithmetic4Double.multi(beforeNianChuValue, 1.05);
		
		//1.B>=J>1.05A
		if(zxBaoE >= zjBaoE && zjBaoE > ncValue){
			zxValue = Arithmetic4Double.div(
					Arithmetic4Double.multi(
							Arithmetic4Double.sub(zxBaoE, beforeNianChuValue),zxFeiLv),
							12000);
		}
		//2.B>=1.05A>J
		else if(zxBaoE >= ncValue && ncValue > zjBaoE){
			zxValue = 
					Arithmetic4Double.div(
							Arithmetic4Double.multi(
									Arithmetic4Double.sub(zxBaoE,beforeNianChuValue),zxFeiLv),12000);
		}
		//3.1.05A>=B>J
		else if(ncValue >= zxBaoE && zxBaoE > zjBaoE){
			zxValue = Arithmetic4Double.div(
					Arithmetic4Double.multi(
							Arithmetic4Double.multi(
									beforeNianChuValue, 0.05),zxFeiLv),12000);
		}
		return zxValue;
	}
	
	
	/**
	 * 计算出第一年第12个月末的保单现金价值
	 * @param firstNianChuValue
	 * @param zhuXianValue
	 * @return
	 */
	public static Double getFirstBaoDanXianJin(HongLi hongli,double firstNianChu,
			ArrayList<HongLi> huoMian,
			double shangHai,double yiLiao,HashMap<String, HongLi> zxFeiLv,
			HashMap<String, HongLi> zjFeiLv){
//		(firstNianChu-zhuXian-shangHai/12-yiLiao/12)*Define.middleLiLv
		double firstZhuXianValue;
		double firstBaoDaoXianJin = 0.0;
		double secondZhuXianValue;
		double afterBaoDaoXianJin = 0.0;
		double threeZhuXianValue;
		double afterZhuXianValue;
		// =伤害/12+医疗/12
//		double totalValue= 
//				Arithmetic4Double.div(shangHai, 12)+
//		Arithmetic4Double.div(yiLiao, 12);
		
		//分三种情况
		//B主险保额
		int zxBaoE = hongli.getBaoE(); 
		double zhuXianFeiLv = 0.0;
		//J重疾保额
		int zjBaoE = hongli.getZhongJiBaoE();
		//1.05A年初保单现金价值（分第一年的，和第二年以后的）
		double ncValue = Arithmetic4Double.multi(firstNianChu, 1.05);
		//重疾
		double zhongJi = 0.0;
		double zhongJiFeiLv = 0.0;
		
		//利率（分低中高）
		double liLv = 0.0;
		if(hongli.getLevel().equals(Define.Level_Low)){
			liLv = Define.zyrsLowLiLv;
		}else if(hongli.getLevel().equals(Define.Level_Middle)){
			liLv = Define.zyrsMiddleLiLv;
		}else{
			liLv = Define.zyrsHighLiLv;
		}
		
		//查出当前年龄的主险费率
//		for (HongLi entity : zxFeiLv.) {
//			if (zxFeiLv.get(age+sex)) {
					zhuXianFeiLv = zxFeiLv.get(hongli.getAge()+hongli.getSex()).getFeiLv();
//					zxFeiLv.get(age+sex);
//				}
//				break;
//			}
//		}
		
		//查出当前年龄的重疾费率
					zhongJiFeiLv = zjFeiLv.get(hongli.getAge()+hongli.getSex()).getFeiLv();
//		for (HongLi entity : zjFeiLv) {
//			if (entity.getAge() == hongli.getAge()) {
//				if(entity.getSex().equals(hongli.getSex())){
//					zhongJiFeiLv = entity.getFeiLv();
//				}
//				break;
//			}
//		}
		
		for(int i = 1;i <= 12;i++){
			if(i == 1){
				//第一年的第一个月的主险保障成本
				firstZhuXianValue = getFirstZhuXian(hongli,firstNianChu,zhuXianFeiLv);
				//第一个月末的保单现金价值
				firstBaoDaoXianJin = 
					Arithmetic4Double.multi(
					Arithmetic4Double.sub(
					Arithmetic4Double.sub(Arithmetic4Double.sub(firstNianChu, firstZhuXianValue),
							Arithmetic4Double.div(shangHai, 12)),
							Arithmetic4Double.div(yiLiao, 12)),1+liLv);
			}else if(i == 2){
				//第二个月的主险保障成本
				secondZhuXianValue =
						getSecondZhuXian(hongli, firstBaoDaoXianJin, zhuXianFeiLv,i);
				//第二个月末的保单现金价值
				afterBaoDaoXianJin = 
						Arithmetic4Double.multi(
						Arithmetic4Double.sub(
						Arithmetic4Double.sub(Arithmetic4Double.sub(
								firstBaoDaoXianJin, secondZhuXianValue),
								Arithmetic4Double.div(shangHai, 12)),
								Arithmetic4Double.div(yiLiao, 12)),1+liLv);
			}
			else if(i == 3){
				//第三个月的主险保障成本
				threeZhuXianValue =
						getSecondZhuXian(hongli, Double.valueOf(afterBaoDaoXianJin), zhuXianFeiLv,i);
				//第三个月末的保单现金价值
				afterBaoDaoXianJin = 
						Arithmetic4Double.multi(
						Arithmetic4Double.sub(
						Arithmetic4Double.sub(Arithmetic4Double.sub(
								Double.valueOf(afterBaoDaoXianJin), threeZhuXianValue),
								Arithmetic4Double.div(shangHai, 12)),
								Arithmetic4Double.div(yiLiao, 12)),1+liLv)
//								)
								;
			}else{
					//1.B>=J>1.05A
					if(zxBaoE >= zjBaoE && zjBaoE > ncValue){
						zhongJi = 
								Arithmetic4Double.div(
								Arithmetic4Double.multi(
								Arithmetic4Double.sub(zjBaoE,
								Arithmetic4Double.div(
								Arithmetic4Double.multi(zjBaoE, Double.valueOf(afterBaoDaoXianJin)),
								zxBaoE)),zhongJiFeiLv),12000);
					}
					//2.B>=1.05A>J
					else if(zxBaoE >= ncValue && ncValue > zjBaoE){
//						(1.05A-1.05A*A/B)*重疾费率/12000
						// =1.05A
						Double value = Arithmetic4Double.multi(
								Double.valueOf(afterBaoDaoXianJin), 1.05);
						zhongJi =Arithmetic4Double.div(
								Arithmetic4Double.multi(
								Arithmetic4Double.sub(value, 
										Arithmetic4Double.div(
										Arithmetic4Double.multi(
												value, Double.valueOf(afterBaoDaoXianJin)),zxBaoE)),
												zhongJiFeiLv),12000);
					}
					//3.1.05A>=B>J
					else if(ncValue >= zxBaoE && zxBaoE > zjBaoE){
						//0.05A*重疾费率/12000
						zhongJi = 
								Arithmetic4Double.div(
								Arithmetic4Double.multi(
								Arithmetic4Double.multi(
								Double.valueOf(afterBaoDaoXianJin), 0.05),
								zhongJiFeiLv),12000);
					}
					
				//第四个月以后的主险保障成本
				afterZhuXianValue = 
						getSecondZhuXian(hongli, Double.valueOf(afterBaoDaoXianJin), zhuXianFeiLv,i);
				//第四个月末的保单现金价值
//				=(AL6-AO6-AR6-$DU6/12-$DV6/12-$DW6/9)*(1+$B$2)
				afterBaoDaoXianJin = 
								Arithmetic4Double.multi(
								Arithmetic4Double.sub(
								Arithmetic4Double.sub(
								Arithmetic4Double.sub(
								Arithmetic4Double.sub(
								Arithmetic4Double.sub(
										afterBaoDaoXianJin,
										afterZhuXianValue),zhongJi),
										Arithmetic4Double.div(shangHai, 12)),
										Arithmetic4Double.div(yiLiao, 12)),
										// =豁免保障/9
										Arithmetic4Double.div(Double.valueOf(huoMian.get(0).getHongli()),9)
										),1+liLv);
			}
		}
		double d = Double.valueOf(afterBaoDaoXianJin);
		return Double.valueOf(afterBaoDaoXianJin);
	}
	
	/**
	 * 计算第二年以后的月末保单现金价值
	 * @param hongli
	 * @param huoMian
	 * @param firstBaoDanXianJinValue
	 * @param shangHai
	 * @param yiLiao
	 * @param zxFeiLv
	 * @param zjFeiLv
	 * @param wanNengZhangHu
	 * @return
	 */
	public static ArrayList<HongLi> getAfterBaoDanXianJin(HongLi hongli,
			ArrayList<HongLi> huoMian,double firstBaoDanXianJinValue,
			double shangHai,double yiLiao,HashMap<String, HongLi> zxFeiLv,
			HashMap<String, HongLi> zjFeiLv,ArrayList<HongLi> wanNengZhangHu){
		ArrayList<HongLi> hl = new ArrayList<HongLi>();
		HongLi h = null;
		double nianChuValue = 0.0;
		double baoDanValue = 0.0;
		for(int i = 2; i <= 104 - hongli.getAge() + 1; i++){
			h = new HongLi();
			//计算年初保障现金价值
			if(i <= hongli.getYear()){
				//在交费年期之内的年初保单现金价值
				if(i == 2){
					nianChuValue = Arithmetic4Double.add(firstBaoDanXianJinValue, Double.valueOf(wanNengZhangHu.get(i-1).getHongli()));
				}else{
					nianChuValue = Arithmetic4Double.add(baoDanValue, Double.valueOf(wanNengZhangHu.get(i-1).getHongli()));
				}
			}else{
				nianChuValue = baoDanValue;
			}
			//该if语句判断被保人年龄是否大于64岁
			//是  --> 从此伤害和医疗都为0
			//否  --> 值为计算出来的值shangHai和yiLiao
			if(i<=hongli.getYear()-2){
				if(i <= 64-hongli.getAge() + 1){
					baoDanValue = getBaoDanXianJin(hongli,nianChuValue,
							huoMian,shangHai,yiLiao,zxFeiLv,zjFeiLv,i);
				}else{
					baoDanValue = getBaoDanXianJin(hongli,nianChuValue,
							huoMian,0.0,0.0,zxFeiLv,zjFeiLv,i);
				}
			}else{
				if(i <= 64-hongli.getAge() + 1){
					baoDanValue = getBaoDanXianJin(hongli,nianChuValue,
							0.0,shangHai,yiLiao,zxFeiLv,zjFeiLv,i);
				}else{
					baoDanValue = getBaoDanXianJin(hongli,nianChuValue,
							0.0,0.0,0.0,zxFeiLv,zjFeiLv,i);
				}
			}
			h.setHongli(String.valueOf(baoDanValue));
			hl.add(h);
		}
			return hl;
	}
	
	
	/**
	 * 计算出第二年以后每年第12个月的月末保单现金价值的工具方法
	 * 在有豁免的情况内
	 */
	public static Double getBaoDanXianJin(HongLi hongli,double nianChuValue,
			ArrayList<HongLi> huoMian,
			double shangHai,double yiLiao,HashMap<String, HongLi> zxFeiLv,
			HashMap<String, HongLi> zjFeiLv,int k){
		//		(firstNianChu-zhuXian-shangHai/12-yiLiao/12)*Define.middleLiLv
		//保单现金价值
		Double afterBaoDaoXianJin = nianChuValue;
		//主险保障
		double zhuXianValue;
		// =伤害/12+医疗/12
//		double totalValue= Arithmetic4Double.add(
//				Arithmetic4Double.div(shangHai, 12),
//		Arithmetic4Double.div(yiLiao, 12));
		
		//分三种情况
		//B主险保额
		int zxBaoE = hongli.getBaoE(); 
		double zhuXianFeiLv = 0.0;
		//查出(当前年龄+k-1)的主险费率
		zhuXianFeiLv = zxFeiLv.get((hongli.getAge()+k-1)+hongli.getSex()).getFeiLv();
//		for (HongLi entity : zxFeiLv) {
//			if (entity.getAge() == hongli.getAge()+k-1) {
//				if(entity.getSex().equals(hongli.getSex())){
//					zhuXianFeiLv = entity.getFeiLv();
//				}
//				break;
//			}
//		}
		//J重疾保额
		int zjBaoE = hongli.getZhongJiBaoE();
		double zhongJiFeiLv = 0.0;
		//重疾费率
		//查出(当前年龄+k-1)的重疾费率
		zhongJiFeiLv = zjFeiLv.get((hongli.getAge()+k-1)+hongli.getSex()).getFeiLv();
//		for (HongLi entity : zjFeiLv) {
//			if (entity.getAge() == hongli.getAge()+k-1) {
//				if(entity.getSex().equals(hongli.getSex())){
//					zhongJiFeiLv = entity.getFeiLv();
//				}
//				break;
//			}
//		}
		
		//1.05A年初保单现金价值
		double ncValue = Arithmetic4Double.multi(Double.valueOf(afterBaoDaoXianJin), 1.05);
		//重疾
		double zhongJi = 0.0;
		
		//利率（分低中高）
		double liLv = 0.0;
		if(hongli.getLevel().equals(Define.Level_Low)){
			liLv = Define.lowLiLv;
		}else if(hongli.getLevel().equals(Define.Level_Middle)){
			liLv = Define.middleLiLv;
		}else{
			liLv = Define.highLiLv;
		}
		
		for(int i = 1;i <= 12;i++){
			//主险保障成本的计算
			zhuXianValue = getAfterZhuXian(hongli, Double.valueOf(afterBaoDaoXianJin), zhuXianFeiLv,i);
			
			//重疾的计算
			//1.B>=J>1.05A
			if(zxBaoE >= zjBaoE && zjBaoE > ncValue){
				zhongJi = 
						Arithmetic4Double.div(
						Arithmetic4Double.multi(
						Arithmetic4Double.sub(zjBaoE,
						Arithmetic4Double.div(
						Arithmetic4Double.multi(zjBaoE, Double.valueOf(afterBaoDaoXianJin)),
						zxBaoE)),zhongJiFeiLv),12000);
			}
			//2.B>=1.05A>J
			else if(zxBaoE >= ncValue && ncValue > zjBaoE){
//						(1.05A-1.05A*A/B)*重疾费率/12000
				// =1.05A
				Double value = Arithmetic4Double.multi(
						Double.valueOf(afterBaoDaoXianJin), 1.05);
				zhongJi =Arithmetic4Double.div(
						Arithmetic4Double.multi(
						Arithmetic4Double.sub(value, 
								Arithmetic4Double.div(
								Arithmetic4Double.multi(
										value, Double.valueOf(afterBaoDaoXianJin)),zxBaoE)),
										zhongJiFeiLv),12000);
			}
			//3.1.05A>=B>J
			else if(ncValue >= zxBaoE && zxBaoE > zjBaoE){
				//0.05A*重疾费率/12000
				zhongJi = 
						Arithmetic4Double.div(
						Arithmetic4Double.multi(
						Arithmetic4Double.multi(
						Double.valueOf(afterBaoDaoXianJin), 0.05),
						zhongJiFeiLv),12000);
			}
				afterBaoDaoXianJin = 
						Arithmetic4Double.multi(
								Arithmetic4Double.sub(
										Arithmetic4Double.sub(
										Arithmetic4Double.sub(
												Arithmetic4Double.sub(
														Arithmetic4Double.sub(
																afterBaoDaoXianJin,
																zhuXianValue),zhongJi),
																Arithmetic4Double.div(shangHai, 12)),
																Arithmetic4Double.div(yiLiao, 12)),
																Arithmetic4Double.div(
																		Double.valueOf(huoMian.get(k).getHongli()),12)),
																		1+liLv);
//				Log.i("zhiyue", "主险保障=="+zhuXianValue+"--重疾=="+zhongJi+"--保单现金=="+afterBaoDaoXianJin);
		}
		return afterBaoDaoXianJin;
	}
	/**
	 * 计算出第二年以后每年第12个月的月末保单现金价值的工具方法
	 * 在没有豁免的情况内
	 */
	public static Double getBaoDanXianJin(HongLi hongli,double nianChuValue,
			double huoMian,
			double shangHai,double yiLiao,HashMap<String, HongLi> zxFeiLv,
			HashMap<String, HongLi> zjFeiLv,int k){
		//		(firstNianChu-zhuXian-shangHai/12-yiLiao/12)*Define.middleLiLv
		//保单现金价值
		Double afterBaoDaoXianJin = nianChuValue;
		//主险保障
		double zhuXianValue;
		// =伤害/12+医疗/12
//		double totalValue= Arithmetic4Double.add(
//				Arithmetic4Double.div(shangHai, 12),
//				Arithmetic4Double.div(yiLiao, 12));
		
		//分三种情况
		//B主险保额
		int zxBaoE = hongli.getBaoE(); 
		double zhuXianFeiLv = 0.0;
		//查出(当前年龄+k-1)的主险费率
		zhuXianFeiLv = zxFeiLv.get((hongli.getAge()+k-1)+hongli.getSex()).getFeiLv();
//		for (HongLi entity : zxFeiLv) {
//			if (entity.getAge() == hongli.getAge()+k-1) {
//				if(entity.getSex().equals(hongli.getSex())){
//					zhuXianFeiLv = entity.getFeiLv();
//				}
//				break;
//			}
//		}
		//J重疾保额
		int zjBaoE = hongli.getZhongJiBaoE();
		double zhongJiFeiLv = 0.0;
		//重疾费率
		//查出(当前年龄+k-1)的重疾费率
		zhongJiFeiLv = zjFeiLv.get((hongli.getAge()+k-1)+hongli.getSex()).getFeiLv();
//		for (HongLi entity : zjFeiLv) {
//			if (entity.getAge() == hongli.getAge()+k-1) {
//				if(entity.getSex().equals(hongli.getSex())){
//					zhongJiFeiLv = entity.getFeiLv();
//				}
//				break;
//			}
//		}
		
		//1.05A年初保单现金价值
		double ncValue = Arithmetic4Double.multi(Double.valueOf(afterBaoDaoXianJin), 1.05);
		//重疾
		double zhongJi = 0.0;
		
		//利率（分低中高）
		double liLv = 0.0;
		if(hongli.getLevel().equals(Define.Level_Low)){
			liLv = Define.lowLiLv;
		}else if(hongli.getLevel().equals(Define.Level_Middle)){
			liLv = Define.middleLiLv;
		}else{
			liLv = Define.highLiLv;
		}
		
		//主险打印
		ArrayList<HongLi> zxArrayList = new ArrayList<HongLi>();
		HongLi zxHongLiArray = null;
		//重疾打印
		ArrayList<HongLi> zjArrayList = new ArrayList<HongLi>();
		HongLi zjHongLiArray = null;
		//保单现金打印
		ArrayList<HongLi> bdArrayList = new ArrayList<HongLi>();
		HongLi bdHongLiArray = null;
		for(int i = 1;i <= 12;i++){
			zxHongLiArray = new HongLi();
			//主险保障成本的计算
			zhuXianValue =
					getAfterZhuXian(hongli, Double.valueOf(afterBaoDaoXianJin), zhuXianFeiLv,i);
//			zxHongLiArray.setHongli(String.valueOf(zhuXianValue));
//			zxArrayList.add(zxHongLiArray);
			
			//重疾的计算
			//1.B>=J>1.05A
			if(zxBaoE >= zjBaoE && zjBaoE > ncValue){
				zhongJi = 
					Arithmetic4Double.div(
							Arithmetic4Double.multi(
									Arithmetic4Double.sub(zjBaoE,
											Arithmetic4Double.div(
													Arithmetic4Double.multi(zjBaoE, Double.valueOf(afterBaoDaoXianJin)),
													zxBaoE)),zhongJiFeiLv),12000);
			}
			//2.B>=1.05A>J
			else if(zxBaoE >= ncValue && ncValue > zjBaoE){
//						(1.05A-1.05A*A/B)*重疾费率/12000
				// =1.05A
				Double value = Arithmetic4Double.multi(
						Double.valueOf(afterBaoDaoXianJin), 1.05);
				zhongJi = Arithmetic4Double.div(
						Arithmetic4Double.multi(
								Arithmetic4Double.sub(value, 
										Arithmetic4Double.div(
												Arithmetic4Double.multi(
														value, Double.valueOf(afterBaoDaoXianJin)),zxBaoE)),
														zhongJiFeiLv),12000);
			}
			//3.1.05A>=B>J
			else if(ncValue >= zxBaoE && zxBaoE > zjBaoE){
				//0.05A*重疾费率/12000
				zhongJi = 
					Arithmetic4Double.div(
							Arithmetic4Double.multi(
									Arithmetic4Double.multi(
											Double.valueOf(afterBaoDaoXianJin), 0.05),
											zhongJiFeiLv),12000);
			}
			afterBaoDaoXianJin = 
					Arithmetic4Double.multi(
							Arithmetic4Double.sub(
									Arithmetic4Double.sub(
									Arithmetic4Double.sub(
											Arithmetic4Double.sub(
													Arithmetic4Double.sub(
															afterBaoDaoXianJin,
															zhuXianValue),zhongJi),
															Arithmetic4Double.div(shangHai, 12)),
															Arithmetic4Double.div(yiLiao, 12)),
															huoMian),
																	1+liLv);
			
//			zjHongLiArray.setHongli(String.valueOf(zhongJi));
//			zjArrayList.add(zjHongLiArray);
			
//			bdHongLiArray.setHongli(String.valueOf(afterBaoDaoXianJin));
//			bdArrayList.add(bdHongLiArray);
		}
		return afterBaoDaoXianJin;
	}
}
