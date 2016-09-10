package com.baoxiao.service.productshow;

import java.util.ArrayList;
import java.util.HashMap;
import com.baoxiao.model.HongLi;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;

public class ZhiYueRenShengService {
	/**
	 * ����仯
	 * @param level
	 * @return
	 */
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


	/**
	 * �˻�ֵ��ʾ����
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
	 * �˻��б���ʾ����
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
				list.add(entity.getAge() + "���˻��ۻ���" + entity.getHongli() + "Ԫ");
			}
		return (String[]) list.toArray(new String[0]);
	}

	/**
	 * �ȼ�
	 * @param level
	 * @return
	 */
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
	
	/**
	 * ��ȡ�����˺���ֵ
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
	 * ��ȡ����ҽ�Ƶ�ֵ
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
	 * ��ȡ�����ֵ
	 * @param hongli
	 * @return
	 */
	public static ArrayList<HongLi> getHuoMian(HongLi hongli,
			HashMap<String, HongLi> hmFeiLv,ArrayList<HongLi> wxxsFeiLv,String huoMian){
		//��������
		int period = hongli.getYear();
		//��ʼ����
		int age = hongli.getAge();
		//�ڽ�����
		int qiJaoBaoFei = hongli.getZhuBaoF();
		//��������Ϊ18
		ArrayList<HongLi> h = new ArrayList<HongLi>();
		HongLi hl = null;
		Double value = 0.0;
		Double hmfl = 0.0;
		if(!"".equals(huoMian)){
			for(int i = 1; i <= 104; i++){
				if(i <= period - 1){
					if(i <= 29){
						hl = new HongLi();
						//����
						hmfl = hmFeiLv.get((hongli.getAge()+i-1)+hongli.getSex()).getFeiLv();
//						for (HongLi entity : hmFeiLv) {
//							if (entity.getAge() == age + i - 1 && entity.getSex().equals(hongli.getSex())) {
//								hmfl = entity.getFeiLv();
//								break;
//							}
//						}
						//Σ��ϵ��
						Double wxxs = wxxsFeiLv.get(period-i).getFeiLv();
						//��һ��
						if(i == 1){
							value = Arithmetic4Double.multi(
							Arithmetic4Double.multi(
							Arithmetic4Double.multi(hmfl, wxxs),
							Arithmetic4Double.div(qiJaoBaoFei, 1000)),0.75);
						}
						//�ڶ���  
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
			//�޸��ӱ��ϵ�ֵΪ0
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
	 * ��ȡ��ʼ����
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
			// �ڽ�����С��10000�ĳ�ʼ����
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
	 * ��ȡ���������˻���ֵ
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
			//��ʼ����
			Double chuShi = Double.valueOf(chuShiValue.get(i).getHongli());
			if(i==19){
				wanNeng = String.valueOf(Arithmetic4Double.add(
						Arithmetic4Double.sub(baoF, chuShi),
						Arithmetic4Double.multi(baoF, 0.2)));
			}else{
				//���������˻���ֵ
				wanNeng = String.valueOf(Arithmetic4Double.sub(baoF, chuShi));
			}
			hong.setHongli(wanNeng);
			wanNengValue.add(hong);
		}
		return wanNengValue;
	}
	
	/**
	 * ��һ�������ֵ��ֵ
	 * @return
	 */
	public static Double getFirstNianChuValue(ArrayList<HongLi> wanNengZhangHu){
		return Double.valueOf(wanNengZhangHu.get(0).getHongli());
	}
	
	/**
	 * ��һ��ȵ�һ���µ����ձ��ϳɱ�
	 * @param hongli
	 * @param firstNianChuValue
	 * @param zxFeiLv
	 * @return
	 */
	public static Double getFirstZhuXian(HongLi hongli,Double firstNianChuValue,Double zxFeiLv){
		Double zxValue = 0.0;
		//���������
		//B���ձ���
		int zxBaoE = hongli.getBaoE(); 
		//J�ؼ�����
		int zjBaoE = hongli.getZhongJiBaoE();
		//1.05A��������ֽ��ֵ���ֵ�һ��ģ��͵ڶ����Ժ�ģ�
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
	 * ��һ��ȵڶ����µ����ձ��ϳɱ�
	 * @param hongli
	 * @param beforeNianChuValue ǰһ���µı����ֽ��ֵ
	 * @param zxFeiLv
	 * @return
	 */
	public static Double getSecondZhuXian(HongLi hongli,Double beforeNianChuValue,Double zxFeiLv,int i){
		Double zxValue = 0.0;
		//���������
		//B���ձ���
		int zxBaoE = hongli.getBaoE(); 
		//J�ؼ�����
		int zjBaoE = hongli.getZhongJiBaoE();
		//1.05A��������ֽ��ֵ���ֵ�һ��ģ��͵ڶ����Ժ�ģ�
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
	 * �ڶ�����Ժ��ÿ���µ����ձ��ϳɱ�
	 * @param hongli
	 * @param beforeNianChuValue ǰһ���µı����ֽ��ֵ
	 * @param zxFeiLv
	 * @return
	 */
	public static Double getAfterZhuXian(HongLi hongli,Double beforeNianChuValue,Double zxFeiLv,int i){
		Double zxValue = 0.0;
		//���������
		//B���ձ���
		int zxBaoE = hongli.getBaoE(); 
		//J�ؼ�����
		int zjBaoE = hongli.getZhongJiBaoE();
		//1.05A��������ֽ��ֵ���ֵ�һ��ģ��͵ڶ����Ժ�ģ�
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
	 * �������һ���12����ĩ�ı����ֽ��ֵ
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
		// =�˺�/12+ҽ��/12
//		double totalValue= 
//				Arithmetic4Double.div(shangHai, 12)+
//		Arithmetic4Double.div(yiLiao, 12);
		
		//���������
		//B���ձ���
		int zxBaoE = hongli.getBaoE(); 
		double zhuXianFeiLv = 0.0;
		//J�ؼ�����
		int zjBaoE = hongli.getZhongJiBaoE();
		//1.05A��������ֽ��ֵ���ֵ�һ��ģ��͵ڶ����Ժ�ģ�
		double ncValue = Arithmetic4Double.multi(firstNianChu, 1.05);
		//�ؼ�
		double zhongJi = 0.0;
		double zhongJiFeiLv = 0.0;
		
		//���ʣ��ֵ��иߣ�
		double liLv = 0.0;
		if(hongli.getLevel().equals(Define.Level_Low)){
			liLv = Define.zyrsLowLiLv;
		}else if(hongli.getLevel().equals(Define.Level_Middle)){
			liLv = Define.zyrsMiddleLiLv;
		}else{
			liLv = Define.zyrsHighLiLv;
		}
		
		//�����ǰ��������շ���
//		for (HongLi entity : zxFeiLv.) {
//			if (zxFeiLv.get(age+sex)) {
					zhuXianFeiLv = zxFeiLv.get(hongli.getAge()+hongli.getSex()).getFeiLv();
//					zxFeiLv.get(age+sex);
//				}
//				break;
//			}
//		}
		
		//�����ǰ������ؼ�����
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
				//��һ��ĵ�һ���µ����ձ��ϳɱ�
				firstZhuXianValue = getFirstZhuXian(hongli,firstNianChu,zhuXianFeiLv);
				//��һ����ĩ�ı����ֽ��ֵ
				firstBaoDaoXianJin = 
					Arithmetic4Double.multi(
					Arithmetic4Double.sub(
					Arithmetic4Double.sub(Arithmetic4Double.sub(firstNianChu, firstZhuXianValue),
							Arithmetic4Double.div(shangHai, 12)),
							Arithmetic4Double.div(yiLiao, 12)),1+liLv);
			}else if(i == 2){
				//�ڶ����µ����ձ��ϳɱ�
				secondZhuXianValue =
						getSecondZhuXian(hongli, firstBaoDaoXianJin, zhuXianFeiLv,i);
				//�ڶ�����ĩ�ı����ֽ��ֵ
				afterBaoDaoXianJin = 
						Arithmetic4Double.multi(
						Arithmetic4Double.sub(
						Arithmetic4Double.sub(Arithmetic4Double.sub(
								firstBaoDaoXianJin, secondZhuXianValue),
								Arithmetic4Double.div(shangHai, 12)),
								Arithmetic4Double.div(yiLiao, 12)),1+liLv);
			}
			else if(i == 3){
				//�������µ����ձ��ϳɱ�
				threeZhuXianValue =
						getSecondZhuXian(hongli, Double.valueOf(afterBaoDaoXianJin), zhuXianFeiLv,i);
				//��������ĩ�ı����ֽ��ֵ
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
//						(1.05A-1.05A*A/B)*�ؼ�����/12000
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
						//0.05A*�ؼ�����/12000
						zhongJi = 
								Arithmetic4Double.div(
								Arithmetic4Double.multi(
								Arithmetic4Double.multi(
								Double.valueOf(afterBaoDaoXianJin), 0.05),
								zhongJiFeiLv),12000);
					}
					
				//���ĸ����Ժ�����ձ��ϳɱ�
				afterZhuXianValue = 
						getSecondZhuXian(hongli, Double.valueOf(afterBaoDaoXianJin), zhuXianFeiLv,i);
				//���ĸ���ĩ�ı����ֽ��ֵ
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
										// =���Ᵽ��/9
										Arithmetic4Double.div(Double.valueOf(huoMian.get(0).getHongli()),9)
										),1+liLv);
			}
		}
		double d = Double.valueOf(afterBaoDaoXianJin);
		return Double.valueOf(afterBaoDaoXianJin);
	}
	
	/**
	 * ����ڶ����Ժ����ĩ�����ֽ��ֵ
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
			//������������ֽ��ֵ
			if(i <= hongli.getYear()){
				//�ڽ�������֮�ڵ���������ֽ��ֵ
				if(i == 2){
					nianChuValue = Arithmetic4Double.add(firstBaoDanXianJinValue, Double.valueOf(wanNengZhangHu.get(i-1).getHongli()));
				}else{
					nianChuValue = Arithmetic4Double.add(baoDanValue, Double.valueOf(wanNengZhangHu.get(i-1).getHongli()));
				}
			}else{
				nianChuValue = baoDanValue;
			}
			//��if����жϱ����������Ƿ����64��
			//��  --> �Ӵ��˺���ҽ�ƶ�Ϊ0
			//��  --> ֵΪ���������ֵshangHai��yiLiao
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
	 * ������ڶ����Ժ�ÿ���12���µ���ĩ�����ֽ��ֵ�Ĺ��߷���
	 * ���л���������
	 */
	public static Double getBaoDanXianJin(HongLi hongli,double nianChuValue,
			ArrayList<HongLi> huoMian,
			double shangHai,double yiLiao,HashMap<String, HongLi> zxFeiLv,
			HashMap<String, HongLi> zjFeiLv,int k){
		//		(firstNianChu-zhuXian-shangHai/12-yiLiao/12)*Define.middleLiLv
		//�����ֽ��ֵ
		Double afterBaoDaoXianJin = nianChuValue;
		//���ձ���
		double zhuXianValue;
		// =�˺�/12+ҽ��/12
//		double totalValue= Arithmetic4Double.add(
//				Arithmetic4Double.div(shangHai, 12),
//		Arithmetic4Double.div(yiLiao, 12));
		
		//���������
		//B���ձ���
		int zxBaoE = hongli.getBaoE(); 
		double zhuXianFeiLv = 0.0;
		//���(��ǰ����+k-1)�����շ���
		zhuXianFeiLv = zxFeiLv.get((hongli.getAge()+k-1)+hongli.getSex()).getFeiLv();
//		for (HongLi entity : zxFeiLv) {
//			if (entity.getAge() == hongli.getAge()+k-1) {
//				if(entity.getSex().equals(hongli.getSex())){
//					zhuXianFeiLv = entity.getFeiLv();
//				}
//				break;
//			}
//		}
		//J�ؼ�����
		int zjBaoE = hongli.getZhongJiBaoE();
		double zhongJiFeiLv = 0.0;
		//�ؼ�����
		//���(��ǰ����+k-1)���ؼ�����
		zhongJiFeiLv = zjFeiLv.get((hongli.getAge()+k-1)+hongli.getSex()).getFeiLv();
//		for (HongLi entity : zjFeiLv) {
//			if (entity.getAge() == hongli.getAge()+k-1) {
//				if(entity.getSex().equals(hongli.getSex())){
//					zhongJiFeiLv = entity.getFeiLv();
//				}
//				break;
//			}
//		}
		
		//1.05A��������ֽ��ֵ
		double ncValue = Arithmetic4Double.multi(Double.valueOf(afterBaoDaoXianJin), 1.05);
		//�ؼ�
		double zhongJi = 0.0;
		
		//���ʣ��ֵ��иߣ�
		double liLv = 0.0;
		if(hongli.getLevel().equals(Define.Level_Low)){
			liLv = Define.lowLiLv;
		}else if(hongli.getLevel().equals(Define.Level_Middle)){
			liLv = Define.middleLiLv;
		}else{
			liLv = Define.highLiLv;
		}
		
		for(int i = 1;i <= 12;i++){
			//���ձ��ϳɱ��ļ���
			zhuXianValue = getAfterZhuXian(hongli, Double.valueOf(afterBaoDaoXianJin), zhuXianFeiLv,i);
			
			//�ؼ��ļ���
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
//						(1.05A-1.05A*A/B)*�ؼ�����/12000
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
				//0.05A*�ؼ�����/12000
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
//				Log.i("zhiyue", "���ձ���=="+zhuXianValue+"--�ؼ�=="+zhongJi+"--�����ֽ�=="+afterBaoDaoXianJin);
		}
		return afterBaoDaoXianJin;
	}
	/**
	 * ������ڶ����Ժ�ÿ���12���µ���ĩ�����ֽ��ֵ�Ĺ��߷���
	 * ��û�л���������
	 */
	public static Double getBaoDanXianJin(HongLi hongli,double nianChuValue,
			double huoMian,
			double shangHai,double yiLiao,HashMap<String, HongLi> zxFeiLv,
			HashMap<String, HongLi> zjFeiLv,int k){
		//		(firstNianChu-zhuXian-shangHai/12-yiLiao/12)*Define.middleLiLv
		//�����ֽ��ֵ
		Double afterBaoDaoXianJin = nianChuValue;
		//���ձ���
		double zhuXianValue;
		// =�˺�/12+ҽ��/12
//		double totalValue= Arithmetic4Double.add(
//				Arithmetic4Double.div(shangHai, 12),
//				Arithmetic4Double.div(yiLiao, 12));
		
		//���������
		//B���ձ���
		int zxBaoE = hongli.getBaoE(); 
		double zhuXianFeiLv = 0.0;
		//���(��ǰ����+k-1)�����շ���
		zhuXianFeiLv = zxFeiLv.get((hongli.getAge()+k-1)+hongli.getSex()).getFeiLv();
//		for (HongLi entity : zxFeiLv) {
//			if (entity.getAge() == hongli.getAge()+k-1) {
//				if(entity.getSex().equals(hongli.getSex())){
//					zhuXianFeiLv = entity.getFeiLv();
//				}
//				break;
//			}
//		}
		//J�ؼ�����
		int zjBaoE = hongli.getZhongJiBaoE();
		double zhongJiFeiLv = 0.0;
		//�ؼ�����
		//���(��ǰ����+k-1)���ؼ�����
		zhongJiFeiLv = zjFeiLv.get((hongli.getAge()+k-1)+hongli.getSex()).getFeiLv();
//		for (HongLi entity : zjFeiLv) {
//			if (entity.getAge() == hongli.getAge()+k-1) {
//				if(entity.getSex().equals(hongli.getSex())){
//					zhongJiFeiLv = entity.getFeiLv();
//				}
//				break;
//			}
//		}
		
		//1.05A��������ֽ��ֵ
		double ncValue = Arithmetic4Double.multi(Double.valueOf(afterBaoDaoXianJin), 1.05);
		//�ؼ�
		double zhongJi = 0.0;
		
		//���ʣ��ֵ��иߣ�
		double liLv = 0.0;
		if(hongli.getLevel().equals(Define.Level_Low)){
			liLv = Define.lowLiLv;
		}else if(hongli.getLevel().equals(Define.Level_Middle)){
			liLv = Define.middleLiLv;
		}else{
			liLv = Define.highLiLv;
		}
		
		//���մ�ӡ
		ArrayList<HongLi> zxArrayList = new ArrayList<HongLi>();
		HongLi zxHongLiArray = null;
		//�ؼ���ӡ
		ArrayList<HongLi> zjArrayList = new ArrayList<HongLi>();
		HongLi zjHongLiArray = null;
		//�����ֽ��ӡ
		ArrayList<HongLi> bdArrayList = new ArrayList<HongLi>();
		HongLi bdHongLiArray = null;
		for(int i = 1;i <= 12;i++){
			zxHongLiArray = new HongLi();
			//���ձ��ϳɱ��ļ���
			zhuXianValue =
					getAfterZhuXian(hongli, Double.valueOf(afterBaoDaoXianJin), zhuXianFeiLv,i);
//			zxHongLiArray.setHongli(String.valueOf(zhuXianValue));
//			zxArrayList.add(zxHongLiArray);
			
			//�ؼ��ļ���
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
//						(1.05A-1.05A*A/B)*�ؼ�����/12000
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
				//0.05A*�ؼ�����/12000
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
