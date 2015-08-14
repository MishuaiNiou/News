package com.example.book;

import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {
	
	private ViewPager viewpager;
	private MyAdapter adapter;
	private FragmentManager manager;
	private List<Fragment> list=null;
	//private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewpager=(ViewPager)this.findViewById(R.id.viewpager);
		manager=getSupportFragmentManager();
		list=new ArrayList<Fragment>();
		list.add(new Fragment1());
		list.add(new Fragment2());
		list.add(new Fragment3());
		//transaction =manager.beginTransaction();
		adapter=new MyAdapter(manager);
		viewpager.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	public class MyAdapter extends FragmentPagerAdapter{
		
		public MyAdapter(FragmentManager fm){
			super(fm);
		}
		
		public Fragment getItem(int position){
			return list.get(position);
		}
		
		public int getCount(){
			return list.size();
		}
		
	}
}