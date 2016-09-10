package com.baoxiao.service.productshow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

public class XinXiangService {

	public ArrayList<HongLi> findHongLi(HongLi hongli, Context c, String type) {

		ArrayList<HongLi> list = DBDAO.findJiaoQingBaoE(hongli, c, type,Define.tableName_xingXiangHongLi);
		for (HongLi hl : list) {
			double value = 0;

			// hl.setAge(hl.getYear());
			hl.setAge(hongli.getAge() + hl.getYear());
			value = Double.valueOf(hl.getHongli())/20;
			hl.setHongli(Arithmetic4Double.doubleToString(value));
			Log.d("hongli",
					"age" + hl.getAge() + "," + "hongLi=" + hl.getHongli());

		}

		return list;
	}

	public String[] findHongLi(ArrayList<HongLi> listMap, int start, int end) {
		ArrayList<String> list = new ArrayList<String>();

		for (HongLi entity : listMap)

			if (entity.getAge() >= start && entity.getAge() <= end) {
				list.add(entity.getAge() + "岁累计交清增额：" + entity.getHongli() + "元");
			}

		// String[] array=new String[list.size()];
		// for(int j=0;j<list.size();j++){
		// array[j]=list.get(j);
		// }

		return (String[]) list.toArray(new String[0]);
		// return array;
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
	
	//累积交清增额显示数据
		public String getBaoDanXianJinByAge(ArrayList<HongLi> listMap, int age) {
			String value = "";
			for(HongLi hongli : listMap){
				if(hongli.getAge() == age){
					value = hongli.getHongli();
				}
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
	
	public double findhuoMianBaoE(HongLi hongli,Context c, String type
			,int baoE,int zhongJiBaoE,SQLiteDatabase db) {
		double baoFei = 0;
		//查询出主险的费率
		baoFei = Double.valueOf(DBDAO.findBaoFei(hongli, c, type,db));
		baoFei = Arithmetic4Double.round(
				Arithmetic4Double.multi(
				Arithmetic4Double.sub(baoE, zhongJiBaoE)
				, baoFei), Define.Round2) ;
				
		return baoFei;
	}

	public static int lessCount22 = 0;

	public Map<Integer, String> findFanHuanJin(double baoE, int age) {
		lessCount22 = (22 - (age + 3)) + 1;
		Map<Integer, String> map = new HashMap<Integer, String>();
		baoE = baoE * 10000;
		String rst = "";
		int c = 72;
		int j = 0;

		for (int i = 0; i < 111; i++) {
			if (i + age + 3 <= 22) {
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

	public void showFanHuanJin22(TextView[] fanHuanJinList,
			Map<Integer, String> map, Animation ani, int visible) {
		for (int i = 0; i < map.keySet().size(); i++) {
			if (i < lessCount22) {
				map.get(i);

				fanHuanJinList[i].setText(map.get(i));
				fanHuanJinList[i].setVisibility(visible);
				fanHuanJinList[i].setBackgroundColor(Color
						.parseColor("#FF3399"));
				if (visible == View.VISIBLE) {
					fanHuanJinList[i].startAnimation(ani);
				}

				// try {
				// Thread.sleep(500);
				// } catch (InterruptedException e) {
				// }
			}
		}
	}

	public void showFanHuanJinMore22(TextView[] fanHuanJinList,
			Map<Integer, String> map, Animation ani, int visible) {
		for (int i = 0; i < map.keySet().size(); i++) {
			if (i >= lessCount22) {
				map.get(i);
				fanHuanJinList[i].setText(map.get(i));
				fanHuanJinList[i].setVisibility(visible);
				fanHuanJinList[i].setBackgroundColor(Color
						.parseColor("#FF9900"));
				if (visible == View.VISIBLE) {
					fanHuanJinList[i].startAnimation(ani);
				}
			}
		}
		// lessCount22=0;
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

//	public void setLevelBtn(String level,TextView tv) {
//		String grade = "";
//		if (level.equals("low")) {
//			tv.setBackgroundResource(R.drawable.xinxiang_bottombtn);
//		} else if (level.equals("middle")) {
//			tv.setBackgroundResource(R.drawable.xinxiang_centerbtn);
//		} else {
//			tv.setBackgroundResource(R.drawable.xinxiang_topbtn);
//		}
//	}

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
			zhongShenFenHongLevel = "（以假定低等收益累计计算）";
		} else if (level.equals(Define.Level_Middle_display)) {
			zhongShenFenHongLevel = "（以假定中等收益累计计算）";
		} else {
			zhongShenFenHongLevel = "（以假定高等收益累计计算）";
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

	

	//世纪天使累计返还金
	
	public  static ArrayList<Entity>  findLeiJiFanHuanJin4ShiJiTS(double baoE,int age){
		 ArrayList<Entity>   list =new ArrayList<Entity>();
			baoE=baoE*10000;
			double begMoney=0.0;
			double returnMoney=0.0;
			double endMoney=0.0;
			int displayAge=0;
			for(int i=0;i<=103-age;i++){
				displayAge=age+i-1;
				if(i<=3){
					
				}else{
					if((i-1)%3==0){
						returnMoney=Arithmetic4Double.multi(baoE, 0.12);
					}else{
						returnMoney=0.0;
					}
					
					begMoney=Arithmetic4Double.round(Arithmetic4Double.add(returnMoney, endMoney),Define.Round2);
					endMoney=Arithmetic4Double.multi(begMoney,1.03);
					
					Entity entity=new Entity();
					entity.setAge(displayAge);
					entity.setValue((Arithmetic4Double.doubleToString(begMoney)));
					list.add(entity);
				}
				
			}
			
//			int count=0;
			
			return list;
	}

	
	
// 鑫利返还金
public ArrayList<Entity> findFanHuanJin4XinLi(double baoE, int age) {
	
	ArrayList<Entity> list = new ArrayList<Entity>();
	baoE = baoE * 10000;
	int ageDisplay = 0;
	double rst=0.0;
	rst=Arithmetic4Double.round(
			Arithmetic4Double.multi(baoE, 0.07), Define.Round2);
	int count = 0;
	count = (int) Math.round((80 - age) / 2);
	if (count >= 40) {
		count = 40;
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
			ageDisplay = ageDisplay + 2;
//			处理格子不填满80岁问题
			
			entity.setAge(ageDisplay);
			if(ageDisplay==80){
				entity.setValue(String.valueOf(Arithmetic4Double.multi(baoE, 2)+rst));
			}else{
				entity.setValue(String.valueOf(rst));
			}
			list.add(entity);
			
		}
		
		// }
		
	}
	 if(ageDisplay==79){
	Entity entity1 = new Entity();
	entity1.setAge(80);
	entity1.setValue(String.valueOf(Arithmetic4Double.multi(baoE, 2)));
	list.add(entity1);
	}
	
	
	
	
	return list;
	
}

//鑫利显示返还金
public void showXinLiFanHuanJin(TextView[] fanHuanJinList,
		ArrayList<Entity> list, Animation ani, int visible) {
	for (int i = 0; i < list.size(); i++) {
//              for(Entity entity : list){
//		if(i>list.size()-2){
//			fanHuanJinList[i].setText(list.get(i).getValue());
//		}else{
			fanHuanJinList[i].setText(list.get(i).getAge()+"岁"+Arithmetic4Double.doubleToString(Double.valueOf(list.get(i).getValue()))+"元");
//		}
		fanHuanJinList[i].setVisibility(visible);
		fanHuanJinList[i].setBackgroundColor(Color
				.parseColor("#FF6900"));
//              }
		if (visible == View.VISIBLE) {
			fanHuanJinList[i].startAnimation(ani);
		}
	}
}



//鑫利累计返还金

public  static ArrayList<Entity>  findLeiJiFanHuanJin4XinLi(double baoE,int age){
	ArrayList<Entity>   list =new ArrayList<Entity>();
	baoE=baoE*10000;
	double begMoney=0.0;
	double returnMoney=0.0;
	double endMoney=0.0;
	int displayAge=0;
	for(int i=0;i<=(80-age)+1;i++){
		displayAge=age+i-1;
//		displayAge=age+i;
		if(i<=2){
//			if(i<2){
			
		}else{

					if((i-1)%2==0){
					
		
						returnMoney=Arithmetic4Double.multi(baoE, 0.07);
//						if(displayAge==78){
//							System.out.println("test");
//						}
						if(displayAge==80){
							returnMoney=Arithmetic4Double.add(returnMoney, Arithmetic4Double.multi(baoE, 2));
						}
						
					}else{
						if(displayAge==80){
							returnMoney= Arithmetic4Double.multi(baoE, 2);
						}else{
						returnMoney=0.0;
						}
					}
			
			begMoney=Arithmetic4Double.round(Arithmetic4Double.add(returnMoney, endMoney),Define.Round2);
			endMoney=Arithmetic4Double.multi(begMoney,Define.totalLiLv);
			
			Entity entity=new Entity();
			entity.setAge(displayAge);
			entity.setValue((Arithmetic4Double.doubleToString(begMoney)));
			list.add(entity);
		}
		
	}
	
//	80岁返还2*保额
//	Entity entity=new Entity();
//	entity.setAge(80);
//	entity.setValue((Arithmetic4Double.doubleToString(Arithmetic4Double.add(endMoney, Arithmetic4Double.multi(baoE, 2)))));
//	list.add(entity);
//	


return list;
}
}
