package com.example.service;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static String name="textCache.db";
	private static int version=1;
	
	public DBHelper(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

/*	public DBHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}
*/
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
         String sql="create table bookInfo(id integer primary key autoincrement, bookname varchar(11), introduce varchar(100) )";
         db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
