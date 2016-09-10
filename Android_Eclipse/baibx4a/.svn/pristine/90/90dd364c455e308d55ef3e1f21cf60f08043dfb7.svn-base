package com.baoxiao.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBUtil2 extends SQLiteOpenHelper
{

	private static String TB_NAME = null;

	private static String columnDeclare = null;

	public static final String DB_NAME = "baibx4a2.db";

	public static final int DB_VERSION = 1;

	public DBUtil2(Context context, String name, CursorFactory factory,
			int version, String tableName, String columnDeclare)
	{
		super(context, name, factory, version);
		DBUtil2.TB_NAME = tableName;
		DBUtil2.columnDeclare = columnDeclare;

	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE IF NOT EXISTS " +

		TB_NAME + "(" + columnDeclare

		+ ")";
		try
		{

			db.execSQL(sql);
		} catch (Exception e)
		{

			Log.e("Database",
					"onCreate sql=" + sql + " error message=" + e.getMessage());
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

		db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);

		onCreate(db);

		Log.e("Database", "onUpgrade");

	}

	public void updateColumn(SQLiteDatabase db, String oldColumn,
			String newColumn, String typeColumn)
	{

		try
		{

			db.execSQL("ALTER TABLE " +

			TB_NAME + " CHANGE " +

			oldColumn + " " + newColumn +

			" " + typeColumn);

		} catch (Exception e)
		{

			e.printStackTrace();

		}

	}

}