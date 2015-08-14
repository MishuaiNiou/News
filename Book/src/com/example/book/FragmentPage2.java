package com.example.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import json.JsonTools;
import Http.HttpUtils;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.service.DBHelper;
import com.example.service.DBManager;
import com.example.service.DownLoadImage;
import com.example.service.FileService;
import com.example.service.DownLoadImage.ImageCallBack1;
import com.example.service.ImageCache;

	public class FragmentPage2 extends Fragment {
		
	/*	int[] imageIds = new int[]{R.drawable.html5 , R.drawable.android, R.drawable.web, R.drawable.java,R.drawable.linus,R.drawable.ios,R.drawable.javascript};
		private String[] books = new String[]{ "HTML5", "Android", "Web", "JAVA","Linux","IOS","JavaScript"};*/

		private String URLPATH="http://100.64.17.145:8080/Http/BookJsonAction?action_flag=list";
		private String ImagePath="http://100.64.17.145:8080/Http/upload/";
		private ProgressDialog dialog;
		private int TEXT_FINISHED=1;
		private int IMG_FINISHED=2;
		private GridView grid;
		private List<Map<String,Object>> list=null;
		private BaseAdapter adapter;
	   
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView=inflater.inflate(R.layout.fragment_2, container,false);
		//	grid=(GridView)rootView.findViewById(R.id.grid);
			
			return rootView;		
		}	
		
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState){
			super.onActivityCreated(savedInstanceState);
			grid=(GridView)getView().findViewById(R.id.grid);

			final Handler handler=new Handler(){
				public void handleMessage(Message message) {
					list=(List<Map<String,Object>>) message.obj;
					Log.i("------>>", list.toString());
					if(message.what==TEXT_FINISHED){
						adapter=new BaseAdapter() {
							
							@Override
							public View getView(final int position, View convertView, ViewGroup parent) {
								// TODO Auto-generated method stub
								View view=null;
								if(convertView==null){
									view=LayoutInflater.from(getActivity()).inflate(R.layout.grid_item, null);
								}else{
									view=convertView;
								}
								TextView books=(TextView)view.findViewById(R.id.books11);
								books.setText(list.get(position).get("name").toString());
								
							/*	DBManager manager=new DBManager(getActivity());
								Object[] params={list.get(position).get("name").toString()};
								manager.addBook(params);*/
								
							//	DBManager manager=new DBManager(getActivity(), "textCache1.db");
							//	Object[] params={list.get(position).get("name").toString()};
							//	manager.addBook(params);
								
							/*	final ImageView imageView=(ImageView)view.findViewById(R.id.imageView11);
								DownLoadImage downLoadImage=new DownLoadImage(ImagePath+list.get(position).get("img").toString());
								downLoadImage.loadImage1(new ImageCallBack1() {
									
									@Override
									public void getBitmap(byte[] data) {
										// TODO Auto-generated method stub
										Bitmap bitmap=BitmapFactory.decodeByteArray(data, 0, data.length);
										imageView.setImageBitmap(bitmap);
										ImageCache.SaveImageCache(list.get(position).get("img").toString(), data);
									}
								});*/
								
								final ImageView imageView=(ImageView)view.findViewById(R.id.imageView11);
								DownLoadImage downLoadImage=new DownLoadImage(ImagePath+list.get(position).get("img").toString());
								downLoadImage.loadImage1(new ImageCallBack1() {
									
									@Override
									public void getBitmap(byte[] data) {
										// TODO Auto-generated method stub
										FileService fileService=new FileService(getActivity());
										if(fileService.readImageCache(list.get(position).get("img").toString())!=null){
											Bitmap bitmap=fileService.readImageCache(list.get(position).get("img").toString());
											imageView.setImageBitmap(bitmap);
											
										}else{
											
											Bitmap bitmap=BitmapFactory.decodeByteArray(data, 0, data.length);
											imageView.setImageBitmap(bitmap);
										//	ImageCache.SaveImageCache(list.get(position).get("img").toString(), data);  //保存到外部存储
											fileService.saveCacheImg(list.get(position).get("img").toString(), data);   //保存到内部存储
										}
										
									}
								});
								
								
								return view;
							}
							
							@Override
							public long getItemId(int position) {
								// TODO Auto-generated method stub
								return position;
							}
							
							@Override
							public Object getItem(int position) {
								// TODO Auto-generated method stub
								return list.get(position);
							}
							
							@Override
							public int getCount() {
								// TODO Auto-generated method stub
								return list.size();
							}
						};
						
						grid.setAdapter(adapter);
						adapter.notifyDataSetChanged();
				}
					
					
				};
			};
			
			
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Looper.prepare();
				//	dialog.show();
					
					List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
					String json=HttpUtils.sendPostMethod(URLPATH, "utf-8");
					list=JsonTools.parseJsonMaps(json);					
					Message message=Message.obtain();
					message.obj=list;
					message.what=TEXT_FINISHED;
					handler.sendMessage(message);
					
					Looper.loop();
				}
			}).start();
			
			
		/*	new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Looper.prepare();
					byte[] data=null;
					data=HttpUtils.sendPostMethod1(ImagePath, "utf-8");
					Message message=Message.obtain();
					message.obj=data;
					message.what=IMG_FINISHED;
					handler.sendMessage(message);
					Looper.loop();
				}
			}).start();*/
		
		
		}
            
		    
		
	
}