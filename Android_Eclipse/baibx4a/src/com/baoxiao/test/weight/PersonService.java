package com.baoxiao.test.weight;

import java.util.ArrayList;

import com.baoxiao.model.Entity;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;



public class PersonService{

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
 					entity.setValue("�쵽��");
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
}
