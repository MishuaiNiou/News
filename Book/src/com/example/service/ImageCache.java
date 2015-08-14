package com.example.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

public class ImageCache {

	public static String SaveImageCache(String fileName, byte[] data){
		File file=Environment.getExternalStorageDirectory();
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			
		FileOutputStream outputStream=null;
		
		try {
			outputStream=new FileOutputStream(new File(file, fileName));
			outputStream.write(data, 0, data.length);
			return file.getAbsolutePath()+"/"+fileName;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		}
		return null;
		
	}
	
	

}
