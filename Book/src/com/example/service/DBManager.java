package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

	private DBHelper helper=null;
//	private SQLiteDatabase database;
	
	private DBHelper1 helper1=null;
	
	public DBManager(Context context) {
		// TODO Auto-generated constructor stub
		helper=new DBHelper(context);
		
	}
	
	public DBManager(Context context,String name){
		helper1=new DBHelper1(context, name);
	}

	public boolean addBook(Object[] params){
		boolean flag=false;
		SQLiteDatabase database=null;	
		try {
			String sql="insert into bookInfo (bookname,introduce) values(?,?)";
			database=helper.getWritableDatabase();
			database.execSQL(sql, params);
			flag=true;;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return flag;
	}
	
	public boolean deleteBook(Object[] params){
		boolean flag=false;
		SQLiteDatabase database=null;
		try {
			String sql="delete from bookInfo where id=?";
			database=helper.getWritableDatabase();
			database.execSQL(sql, params);
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return flag;
	}
	
	public boolean updateBook(Object[] params){
		boolean flag=false;
		SQLiteDatabase database=null;
		try {
			String sql="update bookInfo set bookname=? ,introduce=? where id=? ";
			database=helper.getWritableDatabase();
			database.execSQL(sql, params);
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return flag;
	}
	
	public Map<String,String> viewBook(String[] selectionArgs){
		Map<String,String> map=new HashMap<String,String>();
		SQLiteDatabase database=null;
		try {
			String sql="select * from bookInfo where id=? ";
			database=helper.getReadableDatabase();
			Cursor cursor=database.rawQuery(sql, selectionArgs);
			int len=cursor.getColumnCount();
			while(cursor.moveToNext()){
				for(int i=0;i<len;i++){
					String col_name=cursor.getColumnName(i);
					String col_value=cursor.getString(cursor.getColumnIndex(col_name));
					if(col_value==null){
						col_value="";
					}
					map.put(col_name, col_value);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return map;
	}
	
	public List<Map<String,String>> viewBooks(String[] selectionArgs){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		SQLiteDatabase database=null;
		try {
			String sql="select * from bookInfo";
			database=helper.getReadableDatabase();
			Cursor cursor=database.rawQuery(sql, selectionArgs);
			int lens=cursor.getColumnCount();
			while(cursor.moveToNext()){
				Map<String,String> map=new HashMap<String,String>();
				for(int i=0;i<lens;i++){
					String col_name=cursor.getColumnName(i);
					String col_value=cursor.getString(cursor.getColumnIndex(col_name));
					if(col_value==null){
						col_value="";
					}
					map.put(col_name, col_value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	public boolean addBooks(String table, String nullColumnHack, ContentValues values){
		SQLiteDatabase database=null;
		
		boolean flag=false;
		try {
			database=helper1.getWritableDatabase();
			long count = database.insert(table, nullColumnHack, values);
			flag=(count>0?true:false);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return flag;
	}
	
	
	public boolean deleteBooks(String table,String whereClause, String[] whereArgs){
		boolean flag=true;
		SQLiteDatabase database=null;
		
		try {
			database=helper1.getWritableDatabase();
			int count=database.delete(table, whereClause, whereArgs);
			flag=(count>0?true:false);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return flag;
	}
	
	public boolean updateBooks(String table,ContentValues values, String whereClause,String[] whereArgs){
		boolean flag=false;
		SQLiteDatabase database=null;
		try {
			database=helper1.getWritableDatabase();
			int count=database.update(table, values, whereClause, whereArgs);
			flag=(count>0?true:false);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return flag;
	}
	
	public Map<String,String> viewMap(String table,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy,String limit){
		Map<String,String> map=new HashMap<String,String>();
		SQLiteDatabase database=null;
		try {
			database=helper1.getReadableDatabase();
			Cursor cursor = database.query(true, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
			int len=cursor.getColumnCount();
			while(cursor.moveToNext()){
				for(int i=0;i<len;i++){
					String col_name = cursor.getColumnName(i);
					String col_value = cursor.getString(cursor.getColumnIndex(col_name));
					if(col_value==null){
						col_value="";
					}
					map.put(col_name, col_value);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return map;
	}
	
	public List<Map<String,String>> listBooks(String table,String[] columns, String selection,String[] selectionArgs, String groupBy ,String having, String orderBy, String limit){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		SQLiteDatabase database=null;
		try {
			database=helper1.getReadableDatabase();
			Cursor cursor=database.query(false, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
			int len =cursor.getColumnCount();
			while(cursor.moveToNext()){
				Map<String,String> map=new HashMap<String,String>();
				for(int i=0;i<len;i++){
					String col_names=cursor.getColumnName(i);
					String col_values=cursor.getString(cursor.getColumnIndex(col_names));
					if(col_values==null){
						col_values="";
					}
					map.put(col_names, col_values);
				}
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return list;
	}
}
