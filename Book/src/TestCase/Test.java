package TestCase;

import com.example.service.DBHelper;
import com.example.service.FileService;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

public class Test extends AndroidTestCase {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	public void save(){
		FileService fileService=new FileService(getContext());
	//	fileService.saveCacheText("hello.txt", "hello beautiful!");
		boolean flag=fileService.saveContentToFile("hh.txt", Context.MODE_PRIVATE, "hahahahaha".getBytes());
		Log.i("--->>", "---->>"+flag);
	}
	
	public void read(){
		FileService fileService=new FileService(getContext());
		String msg=fileService.readContentFromFile("hh.txt");
		Log.i("--->>","--->>"+ msg);
	}
	


}
