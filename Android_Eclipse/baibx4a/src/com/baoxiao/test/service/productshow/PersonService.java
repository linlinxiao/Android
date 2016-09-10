package com.baoxiao.test.service.productshow;

import java.util.ArrayList;
import com.baoxiao.model.Entity;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;

public class PersonService{
	
	//分割符
//	public 

	public void sace(){
		String String = "123";
		Integer integer = new Integer(String);
	}
	
	public ArrayList<Entity>  findFanHuanJin4ShiJiTS(double baoE,int age){
   		
 		 ArrayList<Entity>   list =new ArrayList<Entity>();
 		baoE=baoE*10000;
 		int ageDisplay = 0;
 		for(int i=0;i<103;i++){
 			Entity  entity=new Entity();
 			if(ageDisplay>103){
 				break;
 			}
 			if(i>32){
 				if(i==33){
 					
 					entity.setAge(ageDisplay);
 					entity.setValue(".....");
 					list.add(entity );
 				}else if(i==34){
 					entity.setAge(ageDisplay);
 					entity.setValue("领到老");
 					list.add(entity );
 				}else{
 					break;
 				}
 			}else{
 			if(i==0){
 			ageDisplay  = ageDisplay+age+3;
 			entity.setAge(ageDisplay);
				entity.setValue(String.valueOf(
	 					Arithmetic4Double.round(Arithmetic4Double.multi(baoE, 0.12),Define.Round2)));
				list.add(entity );
 			
 			
 			}else{
 				ageDisplay  = ageDisplay+3;
 				entity.setAge(ageDisplay);
				entity.setValue(String.valueOf(
 						Arithmetic4Double.round(Arithmetic4Double.multi(baoE, 0.12),Define.Round2)));
				list.add(entity );
 				
 				if(ageDisplay>103){
 					i = 33;
 				}
 			}
 			
 			}

 		}
 		
 		return list;
 		
 	}


	public ArrayList<Entity> findFanHuanJin4XinLi(double baoE, int age) {
//		baoE = 10,age = 1;
		ArrayList<Entity> list = new ArrayList<Entity>();
		baoE = baoE * 10000;
		int ageDisplay = 0;
		double rst=0.0;
		rst=Arithmetic4Double.round(
				Arithmetic4Double.multi(baoE, 0.12), Define.Round2);
		int count = 0;
		count = (int) Math.round(105 - age);
		if (count >= 105) {
			count = 105;
		} else {
			
		}
		
		for (int i = 1; i <= count; i++) {
			Entity entity = new Entity();
			
			if (i == 1) {
				ageDisplay = ageDisplay + age + 1;
				entity.setAge(ageDisplay);
				entity.setValue(String.valueOf(rst));
				list.add(entity);
				
			} else {
				ageDisplay = ageDisplay + 1;
//				处理格子不填满80岁问题
				
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
}
