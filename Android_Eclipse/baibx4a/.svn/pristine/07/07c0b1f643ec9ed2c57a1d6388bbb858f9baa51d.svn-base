//package com.baoxiao.test.activity.productshow;
//
//import java.util.ArrayList;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.ActivityInfo;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.baoxiao.R;
//import com.baoxiao.activity.productshow.hushenfu.HuShenFuPlanA;
//import com.baoxiao.model.HongLi;
//import com.baoxiao.provider.DBDAO;
//import com.baoxiao.service.productshow.HuShenFuService;
//import com.baoxiao.service.productshow.ZhiNengXingService;
//import com.baoxiao.util.Arithmetic4Double;
//import com.baoxiao.util.Define;
//
//public class TestZhiNengXingShowA extends Activity {
//
//	private HongLi hongli;
//	private ZhiNengXingService znx;
//	private TextView TextView_city;
//	//bFΪ�ڽ����ѣ�bEΪ���ձ���
//	int age = 1,year = 15,bF = 4000,bE = 100000,zj = 50000;
//	String sex = "��",level = "middle";
//	String baoDanXianJin;
//	Double zhuXiangBaoZhang;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		// ���ú�����ȫ�����ޱ���
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		setContentView(R.layout.copyofps_shousuxshow);
//
//		TextView_city = (TextView) findViewById(R.id.TextView_city);
//
//		hongli = new HongLi();
//		znx = new ZhiNengXingService();
//		hongli.setAge(age);
//		hongli.setSex(sex);
//		hongli.setYear(year);
//		hongli.setBaoE(bF);
//		hongli.setFenShu(zj);
//		hongli.setLevel(level);
//		// �ؼ����ϳɱ��ļ���
//		ArrayList<HongLi> zhongJiBaoFeiD = znx.findZhongJiBaoFei(hongli,
//				TestZhiNengXingShowA.this,
//				Define.DB_Type_FuJiaBaoFei_ZhiNengXingZhongJi);
//
//		for (HongLi hl : zhongJiBaoFeiD) {
//			StringBuffer s = new StringBuffer();
//			s.append(s + "\n" + hl.getHongli());
//			TextView_city.setText(s);
//		}
//		
//		//ҽ�Ʊ��ϳɱ��ļ���
//		Double yiLiaoBaoZhang = znx.findYiLiaoBaoFei(String.valueOf(10000), this);	
//		
//		hongli.setAge(age);
//		//���Ᵽ�ϳɱ�
//		ArrayList<HongLi> huoMianBaoZhang = znx.findHuoMianBaoFei(hongli,this,
//				Define.DB_Type_FuJiaBaoFei_ZhiNengXingHuoMian,hongli.getYear(),
//				Define.DB_Type_FuJiaBaoFei_ZhiNengXingWeiXianXiShu,hongli.getBaoE());
//	
//		//���ӱ��յ�ֵ=�ؼ����ϳɱ�+ҽ�Ʊ��ϳɱ�+���Ᵽ�ϳɱ�
//		ArrayList<HongLi> fuJiaXian = znx.findFuJiaXianBaoFei(zhongJiBaoFeiD,yiLiaoBaoZhang,huoMianBaoZhang,year);
//		for (HongLi hl : zhongJiBaoFeiD) {
//			Log.i("fujiaxianValue", "hongli="+hl.getHongli());
//		}
//		
//		//���ձ��ϳɱ��ļ���
//		int j = 1;
//		ArrayList<HongLi> chuShiFeiYong = new ArrayList<HongLi>();
//		Double BeginValue = 0.0;
//		for (int i = 1; i <= year; i++) {
//			HongLi hong = new HongLi();
//			// �ڽ�����С��10000�ĳ�ʼ����
//			if (bF <= 10000) {
//					if (i == 1) {
//							BeginValue = Arithmetic4Double.multi(bF, 0.5);
//						} else if (i == 2) {
//							BeginValue = Arithmetic4Double.multi(bF, 0.25);
//						} else if (i == 3) {
//							BeginValue = Arithmetic4Double.multi(bF, 0.15);
//						} else if (i == 4 | i == 5) {
//							BeginValue = Arithmetic4Double.multi(bF, 0.1);
//						} else if (i >= 6) {
//							BeginValue = Arithmetic4Double.multi(bF, 0.05);
//						}
//			}else{
//					if (i == 1) {
//						BeginValue = Arithmetic4Double.multi(
//								Arithmetic4Double.multi(bF, 0.5), 0.03);
//					} else if (i == 2) {
//						BeginValue = Arithmetic4Double.multi(
//								Arithmetic4Double.multi(bF, 0.25), 0.03);
//					} else if (i == 3) {
//						BeginValue = Arithmetic4Double.multi(
//								Arithmetic4Double.multi(bF, 0.15), 0.03);
//					} else if (i == 4 | i == 5) {
//						BeginValue = Arithmetic4Double.multi(
//								Arithmetic4Double.multi(bF, 0.1), 0.03);
//					} else if (i >= 6) {
//						BeginValue = Arithmetic4Double.multi(
//								Arithmetic4Double.multi(bF, 0.05), 0.03);
//					}
//			}
//			hong.setHongli(BeginValue.toString());
//			chuShiFeiYong.add(hong);
//		}	
//		for (HongLi hl : chuShiFeiYong) {
//			Log.i("chuShiFeiYong", "hongli="+hl.getHongli());
//		}
//		
//		//�����ۻ����ѵ�ֵ
//		ArrayList<HongLi> arrayHL = znx.findLeiJiBaoFei(bF,year);
//		
//		//Ҫ��������һ������ 
//		hongli.setAge(age);
//		
//		//���ձ��ϼ���
//		ArrayList<HongLi> zxbzArray = new ArrayList<HongLi>();
//		//�����ֽ𼯺�
//		ArrayList<HongLi> bdxjArray = new ArrayList<HongLi>();
//		HongLi zxbzHL = null,bdxjHL = null;
//		Double wanNeng = 0.0;
//		for(int l = age; l <= 103 - age + 1; l++){
//			zxbzHL = new HongLi();
//			bdxjHL = new HongLi();
//			// ���յķ��ʣ�������ı仯���仯
//			Double zxFeiLv = DBDAO.findZhiNengXingFuJiaXianBaoFei(hongli,
//					this, Define.DB_Type_FuJiaBaoFei_ZhiNengXing);
//			hongli.setAge(hongli.getAge() + 1);
//			if(l == age){
//				//���ձ��ϳɱ�=(�ڽ�����-��ʼ����-�����ձ��ϳɱ�/12)*������/1000
//				zhuXiangBaoZhang = 
//						znx.findZhuXianBaoFei(bF,chuShiFeiYong,fuJiaXian,zxFeiLv);
//				
//				//�����һ��ĩ�ı����ֽ��ֵ
//				baoDanXianJin = znx.findFirstBaoDanXianJin(bF,chuShiFeiYong,fuJiaXian,
//						yiLiaoBaoZhang,hongli,Define.DB_Type_FuJiaBaoFei_ZhiNengXing,
//						this,year,huoMianBaoZhang,zhuXiangBaoZhang);
//			}
//			
//			else {
//				//18����ǰ�����ձ��ϼ�ֵ�ļ���
//				if(l < 18){
//					// �ڶ�������ձ��ϳɱ�
//					// �õ�һ��ȵ����һ��ı����ֽ��ֵ���
//					// ���ۻ�����-��һ��ĩ�ı����ֽ��ֵ-���������˻��ļ�ֵ-�����ձ��ϳɱ�/12��*�ڶ������շ���/1000
//					//���������˻���ֵ
//					if(l < year){
//					    wanNeng = Arithmetic4Double.sub(bF, Double
//									.valueOf(chuShiFeiYong.get(l-1)
//											.getHongli()));
//					}else{
//						wanNeng = 0.0;
//					}
//						zhuXiangBaoZhang = Arithmetic4Double.div(
//									Arithmetic4Double.multi(
//									Arithmetic4Double.sub(
//									Arithmetic4Double.sub(
//									Arithmetic4Double.sub(Double.valueOf(arrayHL.get(l-1).getHongli()), Double.valueOf(baoDanXianJin)),
//									wanNeng
//									),Arithmetic4Double.div(Double.valueOf(fuJiaXian.get(l-1).getHongli()),12)),
//									zxFeiLv),1000);
//			}
//				//18���Ժ�����ձ��ϼ�ֵ�ļ���
//				else{
//					//18�������ձ��ϼ�ֵ = ������ *���ձ���/1000
//					zhuXiangBaoZhang = Arithmetic4Double.div(Arithmetic4Double.multi(zxFeiLv, bE), 1000);
//			}
//			//����ڶ���ĩ�ı����ֽ��ֵ
//			//�ֵ�һ���ºͺ����·ݵ�����
//			baoDanXianJin = znx.findSecondBaoDanXianJin(bF,chuShiFeiYong,fuJiaXian,
//					yiLiaoBaoZhang,hongli,Define.DB_Type_FuJiaBaoFei_ZhiNengXing,
//					this,year,huoMianBaoZhang,zhuXiangBaoZhang,wanNeng,baoDanXianJin);	
//			}
//			zxbzHL.setHongli(zhuXiangBaoZhang.toString());
//			bdxjHL.setHongli(baoDanXianJin);
//			zxbzArray.add(zxbzHL);
//			bdxjArray.add(bdxjHL);
//		}
//		for (HongLi hl : zxbzArray) {
//			Log.i("���ձ���", "hongli="+hl.getHongli());
//		}
//
//		for (HongLi hl : bdxjArray) {
//			Log.i("�����ֽ�", "hongli="+hl.getHongli());
//		}
//	}
//}
