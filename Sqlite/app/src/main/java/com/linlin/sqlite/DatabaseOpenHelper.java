package com.linlin.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by linlin on 8/15/16.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public DatabaseOpenHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    //数据库创建的时候调用，可以在里面进行建表等的初始化操作
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user_table(_id integer primary key autoincrement, _name text not null, _age integer not null, _sex text not null);");
    }

    //数据库更新的时候调用，可以备份数据
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
