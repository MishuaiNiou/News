package com.example.service;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginService {

	private Context context;
	public LoginService(Context context){
		this.context=context;
	}
	
	public boolean saveLoginMessage(String name,String password){
		boolean flag=false;
		SharedPreferences preferences=context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
		Editor editor=preferences.edit();
		editor.putString("username", name);
		editor.putString("password", password);
		flag=editor.commit();
		return flag;
	}
	
	public boolean saveMessage(String filename,Map<String,Object> map){
		boolean flag=false;
		SharedPreferences preferences=context.getSharedPreferences(filename, context.MODE_PRIVATE);
		Editor editor=preferences.edit();
		for(Map.Entry<String, Object> entry:map.entrySet()){
			
			String key=entry.getKey();
			Object object=entry.getValue();
			
			
			if(object instanceof Boolean){
				Boolean new_name=(Boolean) object;
				editor.putBoolean(key, new_name);
			}else if(object instanceof Integer){
				Integer integer=(Integer) object;
				editor.putInt(key, integer);
			}else if(object instanceof Float){
				Float f=(float) object;
				editor.putFloat(key, f);
			}else if(object instanceof Long){
				Long l=(Long) object;
				editor.putLong(key, l);
			}else if(object instanceof String){
				String s=(String) object;
				editor.putString(key, s);
			}
			
			
			
		}
		
		flag=editor.commit();
		return flag;
	}
	
	public Map<String,?> getMessage(String filename){
		Map<String,?> map=null;
		SharedPreferences preferences=context.getSharedPreferences(filename, context.MODE_PRIVATE);
		map=preferences.getAll();
		return map;
	}
	
}
