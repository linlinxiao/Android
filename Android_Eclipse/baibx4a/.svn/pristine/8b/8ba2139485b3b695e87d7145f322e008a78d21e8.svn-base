package com.baoxiao.test.weight;

import java.util.ArrayList;

import com.baoxiao.model.Entity;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;

public class Test {
	
	
	
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
   		
//		 ArrayList<Entity>   list =new ArrayList<Entity>();
//		baoE=baoE*10000;
//		int ageDisplay = 0;
//		
//		int count=0;
//		count=(int) Math.round((103-age)/3);
//		if(count>=33){
//			count=33;
//		}else{
//			
//		}
//		
//		for(int i=1;i<=count;i++){
//			Entity  entity=new Entity();
////			if(ageDisplay>=103){
////				break;
////			}
////			if(i>33 ){
////				
////				if(i==34){
////					ageDisplay=ageDisplay+3;
////					entity.setAge(ageDisplay);
////					entity.setValue(".....");
////					list.add(entity );
////				}else if(i==35){
////					ageDisplay=ageDisplay+3;
////					entity.setAge(ageDisplay);
////					entity.setValue("�쵽��");
////					list.add(entity );
////				}else{
////					break;
////				}
//				
//
//			
//			if(i==1){
//			ageDisplay  = ageDisplay+age+3;
//			entity.setAge(ageDisplay);
//				entity.setValue(String.valueOf(
//	 					Arithmetic4Double.round(Arithmetic4Double.multi(baoE, 0.12),Define.Round2)));
//				list.add(entity );
//			
//			
//			}else{
//				ageDisplay  = ageDisplay+3;
//				entity.setAge(ageDisplay);
//				entity.setValue(String.valueOf(
//						Arithmetic4Double.round(Arithmetic4Double.multi(baoE, 0.12),Define.Round2)));
//				list.add(entity );
//				
//				
//			}
			
			
			
//			}

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ArrayList<Entity> list = findLeiJiFanHuanJin4ShiJiTS(10,3);
  	   for(Entity e:list){
  		 System.out.println( "age="+e.getAge()+"value="+e.getValue());
  	   }
	}

}
