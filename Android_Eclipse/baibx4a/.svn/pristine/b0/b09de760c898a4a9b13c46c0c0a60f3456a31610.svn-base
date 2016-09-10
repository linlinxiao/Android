package com.baoxiao.provider;

import java.util.ArrayList;
import java.util.List;
import com.baoxiao.model.Groupuser;
import com.baoxiao.util.Define;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GroupDAO
{
	private final String TB_NAME = "groupuser";

	private final String columnDeclare = Define.GroupID
			+ " integer primary key," + Define.GroupName + " varchar,"
			+ Define.GroupPhoneid + " varchar," + Define.GroupSex + " varchar,"
			+ Define.GroupAge + " varchar," + Define.GroupBirth + " varchar,"
			+ Define.GroupAddress + " varchar," + Define.GroupInsurer
			+ " varchar," + Define.GroupCompany + " varchar,"
			+ Define.GroupType + " varchar," + Define.GroupOccupation
			+ " varchar," + Define.GroupRemarks + " varchar,"
			+ Define.GroupUsertag + " varchar," + Define.GroupGrouptype
			+ " varchar," + Define.GroupUserid + " varchar,"
			+ Define.GroupLastTime + " varchar," + Define.GroupNextTime
			+ " varchar";

	private SQLiteDatabase db;

	private DBUtil dbHelper;

	public GroupDAO(Context context)
	{

		dbHelper = new DBUtil(context, DBUtil.DB_NAME, null, DBUtil.DB_VERSION,
				TB_NAME, columnDeclare);

		db = dbHelper.getWritableDatabase();

	}

	public void close()
	{

		db.close();

		dbHelper.close();

	}

	/**
	 * 查询全部
	 * 
	 * @return
	 */
	public List<Groupuser> getUserList()
	{
		Cursor cursor = null;
		List<Groupuser> userList = null;

		try
		{
			userList = new ArrayList<Groupuser>();

			cursor = db.query(TB_NAME, null, null, null, null,

			null, Define.GroupID + " ASC");

			cursor.moveToFirst();

			while (!cursor.isAfterLast() && (cursor.getString(1) != null))
			{

				Groupuser user = new Groupuser();

				user.setId(cursor.getInt(0));

				user.setName(cursor.getString(1));

				user.setPhoneid(cursor.getString(2));

				user.setSex(cursor.getString(3));

				user.setAge(cursor.getString(4));

				user.setBirth(cursor.getString(5));

				user.setAddress(cursor.getString(6));

				user.setInsurer(cursor.getString(7));

				user.setCompany(cursor.getString(8));

				user.setType(cursor.getString(9));

				user.setOccupation(cursor.getString(10));

				user.setRemarks(cursor.getString(11));

				user.setUsertag(cursor.getString(12));

				user.setGrouptype(cursor.getString(13));

				user.setUserid(cursor.getString(14));

				user.setLasttime(cursor.getString(15));

				user.setNexttime(cursor.getString(16));

				userList.add(user);

				cursor.moveToNext();

			}
		} finally
		{
			close();
			cursor.close();
		}

		return userList;

	}

	/**
	 * 根据姓名模糊查询群信息
	 * 
	 * @return
	 */
	public List<Groupuser> getSearchList(String name)
	{
		Cursor cursor = null;
		List<Groupuser> userList = null;

		try
		{
			userList = new ArrayList<Groupuser>();

			cursor = db.query(TB_NAME, null, Define.GroupName + " like ?",
					new String[]
					{ "%" + name + "%" }, null, null, Define.GroupID + " ASC");

			cursor.moveToFirst();

			while (!cursor.isAfterLast() && (cursor.getString(1) != null))
			{

				Groupuser user = new Groupuser();

				user.setId(cursor.getInt(0));

				user.setName(cursor.getString(1));

				user.setPhoneid(cursor.getString(2));

				user.setSex(cursor.getString(3));

				user.setAge(cursor.getString(4));

				user.setBirth(cursor.getString(5));

				user.setAddress(cursor.getString(6));

				user.setInsurer(cursor.getString(7));

				user.setCompany(cursor.getString(8));

				user.setType(cursor.getString(9));

				user.setOccupation(cursor.getString(10));

				user.setRemarks(cursor.getString(11));

				user.setUsertag(cursor.getString(12));

				user.setGrouptype(cursor.getString(13));

				user.setUserid(cursor.getString(14));

				user.setLasttime(cursor.getString(15));

				user.setNexttime(cursor.getString(16));

				userList.add(user);

				cursor.moveToNext();

			}
		} finally
		{
			close();
			cursor.close();
		}

		return userList;

	}

	/**
	 * 根据用户手机号码查询
	 * 
	 * @param Phoneid
	 * @return
	 */
	public Boolean phoneGroup(String Phoneid)
	{
		Cursor cursor = null;
		Boolean b = false;
		try
		{
			cursor = db.query(TB_NAME, null, Define.GroupPhoneid

			+ "=?", new String[]
			{ Phoneid }, null, null, null);

			b = cursor.moveToFirst();

			Log.e("HavePrice", b.toString());

			cursor.close();
		} finally
		{
			// close();
		}
		return b;
	}

	/**
	 * 根据姓名查询
	 * 
	 * @param Name
	 * @return
	 */
	public Groupuser HaveGroup(String Name)
	{
		Cursor cursor = null;
		Boolean b = false;
		Groupuser user;
		try
		{

			cursor = db.query(TB_NAME, null, Define.GroupName

			+ "=?", new String[]
			{ Name }, null, null, null);

			b = cursor.moveToFirst();

			user = new Groupuser();

			user.setId(cursor.getInt(0));

			user.setName(cursor.getString(1));

			user.setPhoneid(cursor.getString(2));

			user.setSex(cursor.getString(3));

			user.setAge(cursor.getString(4));

			user.setBirth(cursor.getString(5));

			user.setAddress(cursor.getString(6));

			user.setInsurer(cursor.getString(7));

			user.setCompany(cursor.getString(8));

			user.setType(cursor.getString(9));

			user.setOccupation(cursor.getString(10));

			user.setRemarks(cursor.getString(11));

			user.setUsertag(cursor.getString(12));

			user.setGrouptype(cursor.getString(13));

			user.setUserid(cursor.getString(14));

			user.setLasttime(cursor.getString(15));

			user.setNexttime(cursor.getString(16));

			Log.e("HavePrice", b.toString());

			cursor.close();
		} finally
		{
			// close();
		}
		return user;
	}

	/**
	 * 根据群类型查询
	 * 
	 * @param GroupType
	 * @return
	 */
	public List<Groupuser> getUserList(String GroupType)
	{
		Cursor cursor = null;
		List<Groupuser> userList = null;

		try
		{
			userList = new ArrayList<Groupuser>();

			cursor = db.query(TB_NAME, null, Define.GroupGrouptype

			+ "=?", new String[]
			{ GroupType }, null,

			null, Define.GroupID + " ASC");

			cursor.moveToFirst();

			while (!cursor.isAfterLast() && (cursor.getString(1) != null))
			{

				Groupuser user = new Groupuser();

				user.setId(cursor.getInt(0));

				user.setName(cursor.getString(1));

				user.setPhoneid(cursor.getString(2));

				user.setSex(cursor.getString(3));

				user.setAge(cursor.getString(4));

				user.setBirth(cursor.getString(5));

				user.setAddress(cursor.getString(6));

				user.setInsurer(cursor.getString(7));

				user.setCompany(cursor.getString(8));

				user.setType(cursor.getString(9));

				user.setOccupation(cursor.getString(10));

				user.setRemarks(cursor.getString(11));

				user.setUsertag(cursor.getString(12));

				user.setGrouptype(cursor.getString(13));

				user.setUserid(cursor.getString(14));

				user.setLasttime(cursor.getString(15));

				user.setNexttime(cursor.getString(16));

				userList.add(user);

				cursor.moveToNext();

			}
		} finally
		{
			close();
			cursor.close();
		}

		return userList;

	}

	/**
	 * 修改
	 * 
	 * @param user
	 * @return
	 */
	public int UpdateGroup(Groupuser user)
	{
		int id = 0;
		try
		{
			ContentValues values = new ContentValues();

			values.put(Define.GroupName, user.getName());

			values.put(Define.GroupPhoneid, user.getPhoneid());

			values.put(Define.GroupSex, user.getSex());

			values.put(Define.GroupAge, user.getAge());

			values.put(Define.GroupBirth, user.getBirth());

			values.put(Define.GroupAddress, user.getAddress());

			values.put(Define.GroupInsurer, user.getCompany());

			values.put(Define.GroupCompany, user.getCompany());

			values.put(Define.GroupType, user.getType());

			values.put(Define.GroupOccupation, user.getOccupation());

			values.put(Define.GroupRemarks, user.getRemarks());

			values.put(Define.GroupUsertag, user.getUsertag());

			values.put(Define.GroupGrouptype, user.getGrouptype());

			values.put(Define.GroupUserid, user.getUserid());

			values.put(Define.GroupLastTime, user.getLasttime());

			values.put(Define.GroupNextTime, user.getNexttime());

			id = db.update(TB_NAME, values, Define.GroupID + "="

			+ user.getId(), null);

			Log.e("UpdatePrice", id + "");
		} finally
		{
			close();
		}
		return id;
	}

	/**
	 * 增加
	 * 
	 * @param user
	 * @return
	 */
	public Long SaveGroup(Groupuser user)
	{
		Long uid = 0L;
		try
		{
			ContentValues values = new ContentValues();

			values.put(Define.GroupName, user.getName());

			values.put(Define.GroupPhoneid, user.getPhoneid());

			values.put(Define.GroupSex, user.getSex());

			values.put(Define.GroupAge, user.getAge());

			values.put(Define.GroupBirth, user.getBirth());

			values.put(Define.GroupAddress, user.getAddress());

			values.put(Define.GroupInsurer, user.getInsurer());

			values.put(Define.GroupCompany, user.getCompany());

			values.put(Define.GroupType, user.getType());

			values.put(Define.GroupOccupation, user.getOccupation());

			values.put(Define.GroupRemarks, user.getRemarks());

			values.put(Define.GroupUsertag, user.getUsertag());

			values.put(Define.GroupGrouptype, user.getGrouptype());

			values.put(Define.GroupUserid, user.getUserid());

			values.put(Define.GroupLastTime, user.getLasttime());

			values.put(Define.GroupNextTime, user.getNexttime());

			uid = db.insert(TB_NAME, Define.GroupID, values);

			Log.e("SavePrice", uid + "");

		} finally
		{
			close();
		}
		return uid;

	}

	/**
	 * 删除
	 * 
	 * @param Id
	 * @return
	 */
	public int DelPrice(String Phoneid)
	{
		int id = 0;
		try
		{

			id = db.delete(TB_NAME,

			Define.GroupPhoneid + "=?", new String[]
			{ Phoneid });

			Log.e("DelPrice", Phoneid + "");
		} finally
		{
			close();
		}
		return id;

	}

}
