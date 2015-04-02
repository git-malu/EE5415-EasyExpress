package com.example.ouyanggang.myapplication2.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by malu on 4/2/15.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String mDatabaseName = MyDatabase.DATABASE_NAME;
    public static final int mDataBaseVersion = 1;
    //create table tableName(_ID integer primary key, user_name text, user_pass text);
    public static final String CREATE_TABLE = "create table "+MyDatabase.UserInfo.TABLE_NAME+" ("+MyDatabase.UserInfo._ID+" text primary key, "+MyDatabase.UserInfo.USER_NAME+" text, "+MyDatabase.UserInfo.USER_PASS + " text);";

    public MySQLiteHelper(Context context) {
        super(context, mDatabaseName, null, mDataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long registerUserInfo(SQLiteOpenHelper helper, String user_phone, String user_name, String user_pass){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyDatabase.UserInfo._ID,user_phone);
        cv.put(MyDatabase.UserInfo.USER_NAME,user_name);
        cv.put(MyDatabase.UserInfo.USER_PASS,user_pass);
        return db.insert(MyDatabase.UserInfo.TABLE_NAME,null,cv);
    }

    public void queryUserInfo(SQLiteOpenHelper helper){

    }
}
