package com.baoxiao.test.weight;

import java.util.ArrayList;

import android.test.AndroidTestCase;
import android.util.Log;

import com.baoxiao.model.Entity;
import com.baoxiao.service.productshow.ShouHuXService;

public class PersonServiceTest {

	//��Ԫ���Է����ķ���������Ҫ��test��ͷ
       public void testSave() throws Throwable
       {
    	   PersonService personService = new PersonService();
    	   personService.sace();
       }
       public static  void testMap() throws Throwable
       {
    	   ShouHuXService shouhuxService = new ShouHuXService();
    	   ArrayList<Entity> list = shouhuxService.findFanHuanJin4ShiJiTS(10, 100);
    	   for(Entity e:list){
    		 System.out.println( "age="+e.getAge()+"value="+e.getValue());
    	   }
//    	   int m = map.size();
//    	   map.get("ageDisplay");
    	   
//    	   Log.i("map", "----");
       }
       
   	public static void main(String[] args) {
		// TODO Auto-generated method stub
   		
   	 try {
		testMap();
	} catch (Throwable e) {
		e.printStackTrace();
	}
//    System.out.println("test");
	}

}
