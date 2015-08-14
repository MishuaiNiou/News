package com.example.book;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment3 extends Fragment {
	
	private Button bn0;
	private Button bn1;
	private Button bn2;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		/*handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==0x123){
					Intent intent=new Intent(getActivity(),home.class);
					startActivity(intent);
				}
			}
			
		};*/
		
       }
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		View view=inflater.inflate(R.layout.splash3,null);
		bn0=(Button)view.findViewById(R.id.b0);
		bn0.setOnClickListener(new View.OnClickListener(){
			
		  public void onClick(View v) {
			Intent intent=new Intent(getActivity(),MainTabActivity.class);
			startActivity(intent);
			// TODO Auto-generated method stub
			 /* Message ms=new Message();
			  ms.what=0x123;
			handler.sendMessage(ms);*/
		}
	});
		
		
		bn1=(Button)view.findViewById(R.id.b1);
		bn1.setOnClickListener(new View.OnClickListener(){
			
		  public void onClick(View v) {
			Intent intent=new Intent(getActivity(),register.class);
			startActivity(intent);
		  }
		});
		
		
		bn2=(Button)view.findViewById(R.id.b2);
		bn2.setOnClickListener(new View.OnClickListener(){
			
		  public void onClick(View v) {
			Intent intent=new Intent(getActivity(),login.class);
			startActivity(intent);
		  }
		});
		
		return view; 
	}
	
	
	@Override
	public void onPause(){
		super.onPause();
	}

}
