package com.example.book;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.service.LoginService;

public class register extends Activity {
	private Button button;
	private EditText edt1;
	private EditText edt2;
	private EditText edt3;
	private ProgressDialog dialog;
//	private LoginService service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		edt1=(EditText)this.findViewById(R.id.ed1);
		edt2=(EditText)this.findViewById(R.id.ed2);
		edt3=(EditText)this.findViewById(R.id.ed3);
	//	service=new LoginService(this);
		
		dialog=new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("正在注册，请稍后。。。");
		
		button =(Button)this.findViewById(R.id.register);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String e1=edt1.getText().toString().trim();
				String e2=edt2.getText().toString().trim();
				String e3=edt3.getText().toString().trim();
				if(e1.isEmpty()){
				Toast.makeText(register.this, "请输入用户名！", 2).show();
				}else if(e2.isEmpty()){
					Toast.makeText(register.this, "密码不能为空！", 2).show();
				}else if(e3.isEmpty()){
					Toast.makeText(register.this, "请确认密码！", 2).show();
				}else if(!e3.equals(e2)){	
					Toast.makeText(register.this, "密码不符，请重新输入！", 2).show();
				}else{
					dialog.show();	
				//	service.saveLoginMessage(e1, e2);
								
					Intent intent=new Intent(register.this,classify.class);
					startActivity(intent);
					dialog.dismiss();
					Toast.makeText(getApplicationContext(), "注册成功！", 2).show();
				}
					
			}
					
				
				
			
		});
		}

}
