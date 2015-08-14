package TestCase;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.test.AndroidTestCase;
import android.util.Log;

import com.example.service.DBHelper;
import com.example.service.DBHelper1;
import com.example.service.DBManager;

public class sqliteTest extends AndroidTestCase {

	public sqliteTest() {
		// TODO Auto-generated constructor stub
	}

	public void sqlite(){
		DBHelper helper=new DBHelper(getContext());
		helper.getWritableDatabase();
	}
	
	public void sqlite1(){
		DBHelper1 helper1=new DBHelper1(getContext(), "ReadedCache.db");
		helper1.getWritableDatabase();
	}
	
	public void addbook(){
		DBManager manager=new DBManager(getContext());
		Object[] params={"android1","Android1"};
		boolean flag=manager.addBook(params);
		Log.i("--->>", "--"+flag);
	}
	
	public void deletebook(){
		DBManager manager=new DBManager(getContext());
		Object[] params={2};
		boolean flag=manager.deleteBook(params);
		Log.i("-->>", "--"+flag);
	}
	
	public void updatebook(){
		DBManager manager=new DBManager(getContext());
		Object[] params ={"ios1","IOS1", 1};
		boolean flag=manager.updateBook(params);
		Log.i("--->>", "--"+flag);
	}
	
	public void viewbook(){
		DBManager manager=new DBManager(getContext());
		String[] selectionArgs={"1"};
		Map<String,String> map=manager.viewBook(selectionArgs);
		Log.i("--->>", "--"+map);
	}
	
	public void viewbooks(){
		DBManager manager=new DBManager(getContext());
		String[] selectionArgs={};
		List<Map<String,String>> list=manager.viewBooks(selectionArgs);
		Log.i("--->>", "--"+list);
	}
	
	public void insert(){
		DBManager manager=new DBManager(getContext(), "ReadedCache.db");
		ContentValues values=new ContentValues();
	//	values.put("bookname", "android");
		values.put("bookname", "xiaomi");
		boolean flag=manager.addBooks("bookReaded", null, values);
		Log.i("---", "--"+flag);
	}
	
	public void delete(){
		DBManager manager=new DBManager(getContext(), "ReadedCache.db");
		String whereClause="bookname=?";
		String[] whereArgs={"xiaomi"};
		boolean flag=manager.deleteBooks("bookReaded", whereClause, whereArgs);
		Log.i("---", "--"+flag);
	}
	
	public void update(){
		DBManager manager=new DBManager(getContext(), "ReadedCache.db");
		ContentValues values=new ContentValues();
		values.put("bookname", "ios");
		String whereClause="bookname=?";
		String[] whereArgs={"xiaomi"};
		boolean flag=manager.updateBooks("bookReaded", values, whereClause, whereArgs);
		Log.i("---", "--"+flag);
	}
	
	public void viewMap(){
		DBManager manager=new DBManager(getContext(), "ReadedCache.db");
		String selection="bookname=?";
		String[] selectionArgs={"ios"};
		Map<String,String> map = manager.viewMap("bookReaded", null, selection, selectionArgs, null, null, null, null);
		Log.i("--", "-->"+map);
	}
	
	public void listBooks(){
		DBManager manager=new DBManager(getContext(), "ReadedCache.db");
		List<Map<String,String>> list=manager.listBooks("bookReaded", null, null, null, null, null, null, null);
		Log.i("---", "--"+list);
	}
}
