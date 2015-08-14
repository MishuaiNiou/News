package com.example.service;

import java.net.URL;

import Http.HttpUtils;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class DownLoadImage {

	private String ImagePath;
	private int IMG_FINISHED=2;
	public DownLoadImage(String ImagePath) {
		// TODO Auto-generated constructor stub
		this.ImagePath=ImagePath;
	}

	public void loadImage(final ImageCallBack callBack){
		final Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				callBack.getDrawable((Drawable) msg.obj);
			}
		};
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Drawable drawable=Drawable.createFromStream(new URL(ImagePath).openStream(), "");
					Message message=Message.obtain();
					message.obj=drawable;
					handler.sendMessage(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	
	public void loadImage1(final ImageCallBack1 callBack1){
		final Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				byte[] data = (byte[]) msg.obj;
				callBack1.getBitmap(data);
			}
		};
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				byte[] data=HttpUtils.sendPostMethod1(ImagePath, "utf-8");
				Message message=Message.obtain();
				message.obj=data;
				message.what=IMG_FINISHED;
				handler.sendMessage(message);
			}
		}).start();
	}
	
	
	
	
	
	
	
	public interface ImageCallBack{
		public void getDrawable(Drawable drawable);
	}
	
	
	
	public interface ImageCallBack1{
		public void getBitmap(byte[] data);
	}
}
