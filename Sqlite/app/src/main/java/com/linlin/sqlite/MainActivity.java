package com.linlin.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //方式一：直接执行SQL语句来操作数据库，不建议使用，如果数据库语句写的不对很容易出错
//        SQLiteDatabase database = openOrCreateDatabase("userManager", MODE_PRIVATE, null);
//        database.execSQL("create table user_table(_id integer primary key autoincrement, _name text not null, _age integer not null, _sex text not null);");

        //方式三：使用SQLiteOpenHelper，来管理数据库的创建以及建表
        SQLiteDatabase database = new DatabaseOpenHelper(this, "studentManager").getWritableDatabase();
        database.execSQL("insert into user_table values(1,'张三',23,'男');");
        database.execSQL("insert into user_table values(2,'张三封',23,'男');");
        database.execSQL("insert into user_table values(3,'王二',44,'男');");
        database.execSQL("insert into user_table values(4,'麻子',23,'女');");
        database.execSQL("insert into user_table values(5,'霍启刚',23,'男');");

        //方式二：使用安卓内置函数操作数据库
        ContentValues values = new ContentValues();
        values.put("_name", "张三丰");
        values.put("_age", 12);
        values.put("_sex", "女");
        database.insert("user_table", null, values);

        values.clear();
        values.put("_name", "张三疯");
        values.put("_age", 30);
        values.put("_sex", "男");

        values.clear();
        values.put("_sex", "男");
        database.update("user_table", values, "_id > ?", new String[]{"5"});

        database.delete("user_table", "_name like ?", new String[]{"%丰%"});

        Cursor cursor = database.rawQuery("select * from user_table", null);
        if (cursor != null) {
            String[] columns = cursor.getColumnNames();
            while (cursor.moveToNext()) {
                for (int i = 0; i < columns.length; i++) {
                    String columnName = columns[i];
                    Log.i(TAG, columnName + "=" + cursor.getString(cursor.getColumnIndex(columnName)));
                }
            }

            cursor.close();
        }
        database.close();
    }
}
