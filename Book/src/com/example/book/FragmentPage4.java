package com.example.book;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.ImageView.ScaleType;

public class FragmentPage4 extends Fragment {
	
	private ViewPager imagepager;
	private ImageAdapter adapter;
	private List<ImageView> imageview=null;
	private int[] imagesId=new int[]{R.drawable.jobs,R.drawable.jiagou,R.drawable.goldtimes,R.drawable.luolita,R.drawable.wuwangwo};
	
	
	private String[] books = new String[]{ "HTML5", "Android", "Web", "JAVA","Linux","IOS","JavaScript"};
	private String[] descs = new String[]
{ "在本书中，你可以找到HTML5的一切", "以情景方式对Android的源代码进行深入分析", "原书第四版，学习web开发必备", "Java入门经典教程，从Java语言基础到完整项目开发","Linux必备宝典","Object-C编程","JavaScript高级程序语言设计"};	
	private int[] imageIDs = new int[]
		{ R.drawable.html5 , R.drawable.android, R.drawable.web, R.drawable.java,R.drawable.linus,R.drawable.ios,R.drawable.javascript};
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_4,container,false);		
	}	
	
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		imageview=new ArrayList<ImageView>();
		imagepager=(ViewPager)getActivity().findViewById(R.id.imagepager);
		for (int i = 0; i < imagesId.length; i++) {
			ImageView v= new ImageView(getActivity());
			v.setImageResource(imagesId[i]);
			v.setScaleType(ScaleType.CENTER_CROP);
			imageview.add(v);
		}
		imagepager.setAdapter(new ImageAdapter());
		

		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < books.length; i++)
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("images",imageIDs[i]);
			listItem.put("books", books[i]);
			listItem.put("descs", descs[i]);
			listItems.add(listItem);
		}
		
		SimpleAdapter simpleadapter = new SimpleAdapter(getActivity(), listItems,  R.layout.list_item, 
				new String[] { "images","books", "descs" },  
		        new int[] { R.id.imageView1, R.id.name,R.id.introduce });  
		    ListView listview=(ListView)getActivity().findViewById(R.id.list);
		    listview.setAdapter(simpleadapter);
	
	}
	
	
  public class ImageAdapter extends PagerAdapter{
		
	  public int getCount() {
			// TODO Auto-generated method stub
			return imagesId.length;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			((ViewPager) container).addView(imageview.get(position));
			return imageview.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		
	}
}
