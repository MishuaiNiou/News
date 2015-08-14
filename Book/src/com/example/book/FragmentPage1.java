package com.example.book;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import json.JsonTools;
import Http.HttpUtils;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.service.DBManager;
import com.example.service.DownLoadImage;
import com.example.service.DownLoadImage.ImageCallBack;
import com.example.service.DownLoadImage.ImageCallBack1;
import com.example.service.FileService;
import com.example.service.ImageCache;

public class FragmentPage1 extends Fragment {
	
	private String URLPATH="http://100.64.17.145:8080/Http/BookJsonAction?action_flag=list";
	private String ImagePath="http://100.64.17.145:8080/Http/upload/";
	private ListView listview;
	private MyAdapter adapter;
	private ProgressDialog dialog;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_1, container,false);
		listview=(ListView)rootView.findViewById(R.id.listView);
	//	listview.setAdapter(adapter);
		
		return rootView;		
	}	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
	//	listview=(ListView)getView().findViewById(R.id.listView);	
		dialog=new ProgressDialog(getActivity());
		dialog.setTitle("attention");
		dialog.setMessage("loading.....");
		
		new MyTask().execute(URLPATH);
	//	listview.setAdapter(adapter);
	//	adapter.notifyDataSetChanged();
	}
	
	class MyTask extends AsyncTask<String, Void, List<Map<String,Object>>>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}
		
		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			String json = HttpUtils.sendPostMethod(params[0], "utf-8");
			list=JsonTools.parseJsonMaps(json);
			return list;
		}
		
		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			adapter=new MyAdapter(result);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
	//		Log.i("---->>", result.toString());
			
			dialog.dismiss();
		}
		
	}
	
	
	class MyAdapter extends BaseAdapter{

		private List<Map<String,Object>> list;
		
		public MyAdapter(List<Map<String,Object>> list){
			this.list=list;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view=null;
			if(convertView==null){
				view=LayoutInflater.from(getActivity()).inflate(R.layout.list_item,null);
			}else{
				view=convertView;
			}
			
			TextView name=(TextView)view.findViewById(R.id.name);
			TextView introduce=(TextView)view.findViewById(R.id.introduce);
			
			DBManager manager=new DBManager(getActivity());
			if(manager.viewBooks(null)!=null&&!manager.viewBooks(null).isEmpty()){
				List<Map<String,String>> list1 = manager.viewBooks(null);
				name.setText(list1.get(position).get("bookname").toString());
				introduce.setText(list1.get(position).get("introduce").toString());
				Log.i("-list1-", "--->>"+list1);
			}else{
				name.setText(list.get(position).get("name").toString());		
				introduce.setText(list.get(position).get("introduce").toString());
								
				Object[] params={list.get(position).get("name").toString(),list.get(position).get("introduce").toString()};
				boolean flag=manager.addBook(params);
				Log.i("-list-", "---"+flag);
			}
			
			
			
			
		/*	final ImageView imageView=(ImageView)view.findViewById(R.id.imageView1);
			DownLoadImage downLoadImage=new DownLoadImage(ImagePath+list.get(position).get("img").toString());
			downLoadImage.loadImage(new ImageCallBack() {
				
				@Override
				public void getDrawable(Drawable drawable) {
					// TODO Auto-generated method stub
					imageView.setImageDrawable(drawable);
					BitmapDrawable bitmapDrawable=(BitmapDrawable) drawable;
					Bitmap bitmap=bitmapDrawable.getBitmap();
					ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, arrayOutputStream);
					byte[] data=arrayOutputStream.toByteArray();
					ImageCache.SaveImageCache(list.get(position).get("img").toString(), data);  
				    
			
				}
			});*/
			
			final ImageView imageView=(ImageView)view.findViewById(R.id.imageView1);
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
		
	}

}
