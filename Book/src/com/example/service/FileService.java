package com.example.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class FileService {

	private Context context;
	
	public FileService(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	
	public boolean saveContentToFile(String fileName,int mode,byte[] data){
		boolean flag=false;
		FileOutputStream fileOutputStream=null;
		try {
			fileOutputStream = context.openFileOutput(fileName, mode);
			fileOutputStream.write(data, 0, data.length);
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(fileOutputStream!=null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	public String readContentFromFile(String fileName){
		boolean flag=false;
		FileInputStream fileInputStream=null;
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		try {
			fileInputStream=context.openFileInput(fileName);
			int len=0;
			byte[] data=new byte[1024];
			while((len=fileInputStream.read(data))!=-1){
				byteArrayOutputStream.write(data, 0, len);
			}
			return new String(byteArrayOutputStream.toByteArray());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
	}
	
	
	
	
	
	
	public boolean saveCacheText(String fileName,String text){
		boolean flag=false;
		FileOutputStream fileOutputStream=null;
		File file=context.getFilesDir();
		try {
			File folder=new File(file.getAbsolutePath()+"/text");
			if(!folder.exists()){
				folder.mkdirs();
			}
			fileOutputStream=new FileOutputStream(folder.getAbsolutePath()+"/"+fileName);
			byte[] data=text.getBytes();
			fileOutputStream.write(data, 0, data.length);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(fileOutputStream!=null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	
	public boolean saveCacheImg(String fileName,byte[] data){
		boolean flag=false;
		File file=context.getFilesDir();
		FileOutputStream fileOutputStream=null;
		try {
			File folder=new File(file.getAbsolutePath()+"/img");
			if(!folder.exists()){
				folder.mkdirs();
			}
			fileOutputStream=new FileOutputStream(folder.getAbsolutePath()+"/"+fileName);
			fileOutputStream.write(data, 0, data.length);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(fileOutputStream!=null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	public Bitmap readImageCache(String fileName){
		FileInputStream fileInputStream=null;
		Bitmap bitmap=null;
		File file=context.getFilesDir();
		try {
			File folder=new File(file.getAbsolutePath()+"/"+"img");
			if(folder.exists()&&fileName!=null){
				fileInputStream=context.openFileInput(fileName);
				bitmap=BitmapFactory.decodeStream(fileInputStream);
			}else{
				bitmap=null;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bitmap;

	}
	
	public byte[] readImageCache1(String fileName){
		FileInputStream fileInputStream=null;
		byte[] data=null;
		File file=context.getFilesDir();
		ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
		try {
			File folder=new File(file.getAbsolutePath()+"/"+"img");
			if(folder.exists()&&fileName!=null){
			fileInputStream=context.openFileInput(fileName);
			int len=0;
			byte[] data1=new byte[1024];
			while((len=fileInputStream.read(data1))!=-1){
				arrayOutputStream.write(data, 0, len);
	            data=arrayOutputStream.toByteArray();
			   }
			}else{
				data=null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return data;
		
	}

}
