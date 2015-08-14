package com.example.book;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainTabActivity extends FragmentActivity {
	
	private FragmentTabHost mTabHost;
	
	private LayoutInflater layoutInflater;
	
	private Class fragmentArray[] = {FragmentPage1.class,FragmentPage2.class,FragmentPage3.class,FragmentPage4.class};
	
	private int mImageViewArray[] = {R.drawable.tab_recommend_btn,R.drawable.tab_book_btn,R.drawable.tab_friends_btn,
			 R.drawable.tab_store_btn};
	
	private String mTextviewArray[] = {"推荐", "书架", "好友", "书城"};
	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_layout);
        
        initView();
    }
	
	private void initView(){
		//实例化布局对象
		layoutInflater = LayoutInflater.from(this);
		
	
    mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
    mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);	
    
    int count = fragmentArray.length;	
    
    
    for(int i = 0; i < count; i++){	
		//为每一个Tab按钮设置图标、文字和内容
		TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
		//将Tab按钮添加进Tab选项卡中
		mTabHost.addTab(tabSpec, fragmentArray[i], null);
		//设置Tab按钮的背景
		mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
	       }
      }
	
	private View getTabItemView(int index){
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);
	
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);
		
		TextView textView = (TextView) view.findViewById(R.id.textview);		
		textView.setText(mTextviewArray[index]);
	
		return view;
	}

}
