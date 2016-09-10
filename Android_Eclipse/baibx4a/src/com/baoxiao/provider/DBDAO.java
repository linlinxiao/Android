package com.baoxiao.provider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import com.baoxiao.model.HongLi;
import com.baoxiao.service.productshow.ShouHuXService;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBDAO {

	public final static String File_Name = "shouhux.db";
//	String fileName = "shouhux.db";
	static SQLiteDatabase sqliteDB=null;
//	public  static SQLiteDatabase getSqliteDB(Context c) {
//		
//		String packagename = c.getPackageName();
//		String destPath = "/data/data/" + packagename + "/baibx1.sqlite";
////		SQLiteDatabase sqliteDB=null;
//		FileOutputStream out = null;
//		InputStream in = null;
//		try {
//			File f = new File(destPath);
//			//TODO��������������Ҫע�͵� 
////			f.delete();
//			if (!f.exists()) {
//					out = new FileOutputStream(f);
//					// �����������fileName�е�����
//					in = c.getAssets().open(File_Name);
//
//					byte[] buffer = new byte[1024];
//					int readBytes = 0;
//					// ���������д������
//					while ((readBytes = in.read(buffer)) != -1) {
//						out.write(buffer, 0, readBytes);
//						// Toast.makeText(getApplicationContext(), "д��������",
//						// Toast.LENGTH_SHORT).show();
//					}				
//			}
//			if(sqliteDB==null){
//			sqliteDB = c.openOrCreateDatabase(destPath, Context.MODE_APPEND, null);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//			if(in!=null){
//			
//				in.close();
//			}
//			if(out!=null){
//			out.close();
//			}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		// �����ڳ�������������openOrCreateDatabase�����������ʹ�һ�����ݿ�		
//
//		return sqliteDB;
//	}
	public  static SQLiteDatabase getSqliteDB(Context c) {
		String packagename = c.getPackageName();
		String destPath = "/data/data/" + packagename + "/baibx1.sqlite";
		FileOutputStream out = null;
		InputStream in = null;
		try {
			File f = new File(destPath);
			if (f.exists()) {f.delete();}
			out = new FileOutputStream(f);
			in = c.getAssets().open(File_Name);
			byte[] buffer = new byte[1024];
			int readBytes = 0;
			while ((readBytes = in.read(buffer)) != -1) {
				out.write(buffer, 0, readBytes);
			}
			if(sqliteDB==null){
			sqliteDB = c.openOrCreateDatabase(destPath, Context.MODE_APPEND, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(in!=null){in.close();}
				if(out!=null){out.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqliteDB;
	}

	//��ѯ������ֵ
	public static ArrayList<HongLi> findHongli(HongLi hongLi, Context c,String type,String tableName) {
		ArrayList<HongLi> hongliList = null;
		Cursor cursor = null;
		String sql =
				 "SELECT payperiod,hongli from "+tableName+" where sex = '"+hongLi.getSex()+"' and age = "+hongLi.getAge()+" and year = "+hongLi.getYear()+" and level = '"+ShouHuXService.getLevel(hongLi.getLevel())+"' and type='"+type+"'";
		Log.d("hongliSql=", sql);
		try {
			hongliList = new ArrayList<HongLi>();
			SQLiteDatabase db = getSqliteDB(c);
//			cursor = db.query("hongli", null, null, null, null, null, null);// ��ѯ������α�
			
			cursor= db.rawQuery(sql, null);
			
//			cursor.moveToFirst();
			while (cursor.moveToNext()) {
//				hongli.setSex(cursor.getString(0));// ��ȡ��һ�е�ֵ
//				hongli.setAge(cursor.getInt(1));
//				hongli.setPayperoid(cursor.getInt(2));
				HongLi hongli=new HongLi();
				hongli.setYear(cursor.getInt(0));
//				hongli.setLevel(cursor.getString(4));
				hongli.setHongli(cursor.getString(1));
//				hongli.setType(cursor.getString(6));
//				hongli.setId(cursor.getInt(7));

				hongliList.add(hongli);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return hongliList;

	}
	
	//Ӯ��һ������ѯ�����ֽ��ֵ����level
	public static ArrayList<HongLi> findBaoDanXianJinJiaZhi(HongLi hongLi, Context c,String type,String tableName) {
		ArrayList<HongLi> hongliList = null;
		Cursor cursor = null;
		String sql =
				"SELECT payperiod,hongli from "+tableName+" where sex = '"+hongLi.getSex()+"' and age = "+hongLi.getAge()+" and year = "+hongLi.getYear()+" and type='"+type+"'";
//		Log.d("hongliSql=", sql);
		try {
			hongliList = new ArrayList<HongLi>();
			SQLiteDatabase db = getSqliteDB(c);
//			cursor = db.query("hongli", null, null, null, null, null, null);// ��ѯ������α�
			
			cursor= db.rawQuery(sql, null);
			
//			cursor.moveToFirst();
			while (cursor.moveToNext()) {
//				hongli.setSex(cursor.getString(0));// ��ȡ��һ�е�ֵ
//				hongli.setAge(cursor.getInt(1));
//				hongli.setPayperoid(cursor.getInt(2));
				HongLi hongli=new HongLi();
				hongli.setYear(cursor.getInt(0));
//				hongli.setLevel(cursor.getString(4));
				hongli.setHongli(cursor.getString(1));
//				hongli.setType(cursor.getString(6));
//				hongli.setId(cursor.getInt(7));
				
				hongliList.add(hongli);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return hongliList;
		
	}
	
	//����ʢ�Ľ���������ֵ
		public static ArrayList<HongLi> findJiaoQingBaoE(HongLi hongLi, Context c,String type,String tableName) {
			ArrayList<HongLi> hongliList = null;
			Cursor cursor = null;
			String sql = null;
//			select payperiod,baozhangyear,hongli from xinxianghongli where sex = '��' and age = 10 and year = 20 and baozhangyear = 60 and level = '��' and type='xinxiang';
			if(hongLi.getYear() == 20){
				sql =
						 "SELECT payperiod,hongli from "+tableName+" where sex = '"+hongLi.getSex()+"' and age = "+hongLi.getAge()+" and year = "+hongLi.getPayperoid()+" and baozhangyear = "+hongLi.getYear()+" and level = '"+ShouHuXService.getLevel(hongLi.getLevel())+"' and type='"+type+"'";
			}else{
				sql =
						"SELECT payperiod,hongli from "+tableName+" where sex = '"+hongLi.getSex()+"' and age = "+hongLi.getAge()+" and year = "+hongLi.getPayperoid()+" and baozhangyear = 60"+" and level = '"+ShouHuXService.getLevel(hongLi.getLevel())+"' and type='"+type+"'";
			}
//			Log.d("hongliSql=", sql);
			try {
				hongliList = new ArrayList<HongLi>();
				SQLiteDatabase db = getSqliteDB(c);
//				cursor = db.query("hongli", null, null, null, null, null, null);// ��ѯ������α�
				
				cursor= db.rawQuery(sql, null);
				
//				cursor.moveToFirst();
				while (cursor.moveToNext()) {
//					hongli.setSex(cursor.getString(0));// ��ȡ��һ�е�ֵ
//					hongli.setAge(cursor.getInt(1));
//					hongli.setPayperoid(cursor.getInt(2));
					HongLi hongli=new HongLi();
					hongli.setYear(cursor.getInt(0));
//					hongli.setLevel(cursor.getString(4));
					hongli.setHongli(cursor.getString(1));
//					hongli.setType(cursor.getString(6));
//					hongli.setId(cursor.getInt(7));

					hongliList.add(hongli);

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				cursor.close();
			}
			return hongliList;
		}
	
	public static String findBaoFei(HongLi hongLi, Context c ,String  type,SQLiteDatabase db) {
		String  baoFei = "";
		Cursor cursor = null;
//		String sql =
//				"SELECT baofei from baofei where sex = '��' and age = 0 and year = 10 and type='"+type+"'";
		String sql =
				"SELECT baofei from baofei where sex = '"+hongLi.getSex()+"' and age = "+hongLi.getAge()+" and year = "+hongLi.getYear()+" and type='"+type+"'" ;
		try {
//			cursor = db.query("hongli", null, null, null, null, null, null);// ��ѯ������α�
			
			cursor= db.rawQuery(sql, null);
			
//			cursor.moveToFirst();
			if (cursor.moveToNext()) {
				baoFei=cursor.getString(0);
//				

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return baoFei;

	}
	
	//�����Ǹ����յķ��ʲ�ѯ����
   //select * from zhinengxingfujiaxian where sex = '��' and age = 0 and type = 'zhinengxingzhongji';
	public static ArrayList<HongLi> findZhiNengXingFuJiaXianBaoFei(HongLi hongLi, Context c,String type) {
		ArrayList<HongLi> hl = new ArrayList<HongLi>();
		HongLi h = null;
		double  baoFei = 0;
		Cursor cursor = null;
		String sql =
				"SELECT sex,age,feilv from zhinengxingfujiaxian where type='"+type+"'";
		try {
			SQLiteDatabase db = getSqliteDB(c);
//			cursor = db.query("hongli", null, null, null, null, null, null);// ��ѯ������α�
			
			cursor= db.rawQuery(sql, null);
			
//			cursor.moveToFirst();
			while(cursor.moveToNext()) {
				h = new HongLi();
				h.setSex(cursor.getString(0));
				h.setAge(cursor.getInt(1));
				h.setFeiLv(cursor.getDouble(2));
				hl.add(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return hl;
		
	}
	public static double  findFuJiaXianBaoFei(HongLi hongLi, Context c,String type,SQLiteDatabase db) {
		double  baoFei = 0;
		Cursor cursor = null;
		String sql = 
				"SELECT feilv from fujiaxian where sex = '"+hongLi.getSex()+"' and age = "+hongLi.getAge()+" and year = "+hongLi.getYear()+" and type='"+type+"'";
		try {
//			cursor = db.query("hongli", null, null, null, null, null, null);// ��ѯ������α�
			cursor= db.rawQuery(sql, null);
//			cursor.moveToFirst();
			if (cursor.moveToNext()) {
				baoFei=cursor.getDouble(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return baoFei;

	}
	
	//������Σ��ϵ����ѯ����
	public static double findZhiNengXingWeiXian(HongLi hongLi,Context c,Double wxAge,String type) {
		double  baoFei = 0;
		Cursor cursor = null;
		String sql =
				"SELECT feilv from zhinengxingfujiaxian where age = "+wxAge+" and type='"+type+"'";
		try {
			SQLiteDatabase db = getSqliteDB(c);
			
			cursor= db.rawQuery(sql, null);
			
			if (cursor.moveToNext()) {
				baoFei=cursor.getDouble(0);
//				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return baoFei;
	}
	
	//�������������յķ��ʲ�ѯ����
		public static ArrayList<HongLi>  findZYRSFuJiaXianValue(HongLi hongLi, Context c,String type) {
			ArrayList<HongLi> h = new ArrayList<HongLi>();
			HongLi hongli = null;
			Cursor cursor = null;
			String sql =
					"SELECT sex,age,feilv from zhiyuerenshengfujiaxian where type = '"+type+"'";
			try {
				SQLiteDatabase db = getSqliteDB(c);
//				cursor = db.query("hongli", null, null, null, null, null, null);// ��ѯ������α�
				
				cursor= db.rawQuery(sql, null);
				
//				cursor.moveToFirst();
				while(cursor.moveToNext()) {
					hongli = new HongLi();
					hongli.setSex(cursor.getString(0));
					hongli.setAge(cursor.getInt(1));
					hongli.setFeiLv(cursor.getDouble(2));
					h.add(hongli);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				cursor.close();
			}
			return h;
			
		}
		//�������������յķ��ʲ�ѯ����(map��ʽ)
		public static HashMap<String, HongLi>  findZYRSFuJiaXianValueMap(HongLi hongLi, Context c,String type) {
			HashMap<String, HongLi> map = new HashMap<String, HongLi>();
			HongLi hongli = null;
			Cursor cursor = null;
			String sql =
					"SELECT sex,age,feilv from zhiyuerenshengfujiaxian where type = '"+type+"'";
			try {
				SQLiteDatabase db = getSqliteDB(c);

				cursor= db.rawQuery(sql, null);
				
//				cursor.moveToFirst();
				while(cursor.moveToNext()) {
					hongli = new HongLi();
					hongli.setSex(cursor.getString(0));
					hongli.setAge(cursor.getInt(1));
					hongli.setFeiLv(cursor.getDouble(2));
					map.put(hongli.getAge()+hongli.getSex(), hongli);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				cursor.close();
			}
			return map;
			
		}

}
