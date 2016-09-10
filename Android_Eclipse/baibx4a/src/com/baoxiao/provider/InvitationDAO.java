package com.baoxiao.provider;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.baoxiao.model.Invitation;
import com.baoxiao.util.Define;

public class InvitationDAO
{
	private final String TB_NAME = "invitation";

	private final String columnDeclare = Define.InvitaId
			+ " integer primary key," + Define.InvitaContent + " varchar,"
			+ Define.InvitaUserid + " varchar," + Define.InvitaType
			+ " varchar";
	private SQLiteDatabase db;

	private DBUtil2 dbHelper;

	public InvitationDAO(Context context)
	{

		dbHelper = new DBUtil2(context, DBUtil2.DB_NAME, null, DBUtil2.DB_VERSION,
				TB_NAME, columnDeclare);

		db = dbHelper.getWritableDatabase();

	}

	public void close()
	{

		db.close();

		dbHelper.close();

	}

	// 查该用户下的邀约话术
	public List<Invitation> GetUserList(String userId,String type)
	{
		Cursor cursor = null;
		List<Invitation> userList = null;

		try
		{
			userList = new ArrayList<Invitation>();

			cursor = db.query(TB_NAME, null, Define.InvitaUserid + "=? and "+Define.InvitaType+"=?",
					new String[]
					{ userId,type }, null,

					null, Define.InvitaId + " ASC");

			cursor.moveToFirst();

			while (!cursor.isAfterLast() && (cursor.getString(1) != null))
			{

				Invitation user = new Invitation();

				user.setId(cursor.getInt(0));

				user.setContent(cursor.getString(1));

				user.setUserid(cursor.getString(2));

				user.setType(cursor.getString(3));

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
	 * 根据content查询
	 * @param id
	 * @return
	 */
	public Invitation HaveInvitaC(String content)
	{
		Invitation user;
		Cursor cursor = null;
		Boolean b = false;
		try
		{

			cursor = db.query(TB_NAME, null, Define.InvitaContent

			+ "=?", new String[]
			{ content }, null, null, null);

			b = cursor.moveToFirst();

			user = new Invitation();

			user.setId(cursor.getInt(0));

			user.setContent(cursor.getString(1));

			user.setUserid(cursor.getString(2));

			user.setType(cursor.getString(3));
			
			Log.e("HavePrice", b.toString());

			cursor.close();
		} finally
		{
			close();
		}
		return user;

	}
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Invitation HaveInvitation(String id)
	{
		Invitation user;
		Cursor cursor = null;
		Boolean b = false;
		try
		{

			cursor = db.query(TB_NAME, null, Define.InvitaId

			+ "=?", new String[]
			{ id }, null, null, null);

			b = cursor.moveToFirst();

			user = new Invitation();

			user.setId(cursor.getInt(0));

			user.setContent(cursor.getString(1));

			user.setUserid(cursor.getString(2));

			user.setType(cursor.getString(3));
			
			Log.e("HavePrice", b.toString());

			cursor.close();
		} finally
		{
			close();
		}
		return user;

	}

	public int UpdateInvitation(Invitation user)
	{
		int id = 0;
		try
		{
			ContentValues values = new ContentValues();

			values.put(Define.InvitaContent, user.getContent());

			values.put(Define.InvitaUserid, user.getUserid());

			values.put(Define.InvitaType, user.getType());

			id = db.update(TB_NAME, values, Define.InvitaId + "="

			+ user.getId(), null);

			Log.e("UpdatePrice", id + "");
		} finally
		{
			close();
		}
		return id;
	}

	public Long SaveInvitation(Invitation user)
	{
		Long uid = 0L;
		try
		{
			ContentValues values = new ContentValues();

			values.put(Define.InvitaContent, user.getContent());

			values.put(Define.InvitaUserid, user.getUserid());

			values.put(Define.InvitaType, user.getType());

			uid = db.insert(TB_NAME, Define.InvitaId, values);

			Log.e("SavePrice", uid + "");

		} finally
		{
			close();
		}
		return uid;
	}

	public int DelPrice(String Id)
	{
		int id = 0;
		try
		{

			id = db.delete(TB_NAME,

			Define.InvitaId + "=?", new String[]
			{ Id });

			Log.e("DelPrice", id + "");
		} finally
		{
			close();
		}
		return id;

	}

}
